package adventOfCode.Year2022;

import util.FileUtil;
import util.TimeUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Day16 {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        List<String> l = FileUtil.readfile(Day16.class);
        List<String> l2 = FileUtil.readfileExempel(Day16.class);

        TimeUtil.startTime();
//        oneStar(l2); //1651
//        oneStar(l); //1720
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l2); //1707
        TimeUtil.endTime();
        TimeUtil.startTime();
        twoStar(l); // wrong to low:2577-2578 2581  ,  2597 2671
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        ArrayList<Valve> valves = parseStringIntoValves(l);
        Session session = new Session();
        HashMap<String, Valve> valvesMap = new HashMap<>();
        long sum = 0;
        HashMap<Integer, ArrayList<Integer>> results = new HashMap<>();
        for (Valve valve : valves) {
            valvesMap.put(valve.name, valve);
        }
        for (Valve valve : valves) {
            if (valve.name.equals("AA")) {
                session.position = valve;
                sum = startOpeningValves(session, valvesMap, sum, results);
                break;
            }
        }
        System.out.println(valves);
        System.out.println("Result: " + sum);
    }

    private static long startOpeningValves(Session session, HashMap<String, Valve> valves, long sum, HashMap<Integer, ArrayList<Integer>> results) {
        LinkedList<Session> sessions = new LinkedList<>();
        sessions.add(session);
        while (!sessions.isEmpty()) {
            Session currentSession = sessions.removeFirst();
            currentSession.time++;
            if (currentSession.currentlyOpening1 != null && currentSession.tick1 == 1) {
                currentSession.opened.add(currentSession.currentlyOpening1.name);
                currentSession.pressureFlowRate += currentSession.currentlyOpening1.flowRate;
                currentSession.currentlyOpening1 = null;
            } else if (currentSession.currentlyOpening1 != null && currentSession.tick1 == 0) {
                currentSession.tick1++;
            }
            currentSession.totalRelease += currentSession.pressureFlowRate;

            if (currentSession.time == 30) {
                sum = Math.max(currentSession.totalRelease, sum);
            } else {
                if (currentSession.currentlyOpening1 != null && currentSession.tick1 == 1) {
                    sessions.addLast(currentSession);
                    continue;
                } else {
                    currentSession.tick1 = 0;
                }
                ArrayList<Integer> orDefault = results.getOrDefault(currentSession.time, new ArrayList<>());
                if (orDefault.size() <= 3500) {
                    orDefault.add(currentSession.totalRelease);
                } else {
                    boolean foundSmaller = false;
                    int i = 0;
                    for (; i < orDefault.size(); i++) {
                        if (orDefault.get(i) < currentSession.totalRelease) {
                            foundSmaller = true;
                            break;
                        }
                    }
                    if (!foundSmaller) {
                        continue;
                    } else {
                        orDefault.remove(i);
                        orDefault.add(currentSession.totalRelease);
                    }
                }
                results.put(currentSession.time, orDefault);
                visitOne(valves, currentSession, sessions);
            }
        }
        return sum;
    }

    private static void visitOne(HashMap<String, Valve> valves, Session currentSession, Deque<Session> sessions) {
        for (String leadsToValves : currentSession.position.leadsToValves) {
            Valve valve = valves.get(leadsToValves);
            Session newSession = currentSession.deepCopy();
            if (!currentSession.opened.contains(valve.name)) {
                if (valve.flowRate != 0) {
                    newSession.position = valve;
                    newSession.currentlyOpening1 = valve;
                    sessions.addLast(newSession);
                }
            }
            newSession = currentSession.deepCopy();
            newSession.position = valve;
            sessions.addLast(newSession);
        }
    }

    private static void twoStar(List<String> l) {
        ArrayList<Valve> valves = parseStringIntoValves(l);
        Session session = new Session();
        HashMap<String, Valve> valvesMap = new HashMap<>();
        long sum = 0;
        HashMap<Integer, Integer[]> results = new HashMap<>();
        for (Valve valve : valves) {
            valvesMap.put(valve.name, valve);
        }
        HashSet<Session> visited = new HashSet<>(100000);
        for (Valve valve : valves) {
            if (valve.name.equals("AA")) {
                session.position = valve;
                session.position2 = valve;
                sum = startOpeningValves2(session, valvesMap, sum, results, visited);
                break;
            }
        }
        System.out.println(valves);
        System.out.println("Result: " + sum);
    }

    private static long startOpeningValves2(Session session, HashMap<String, Valve> valvesMap, long sum, HashMap<Integer, Integer[]> results, HashSet<Session> visited) {
        ArrayDeque<Session> sessions = new ArrayDeque<>(100000);
        sessions.add(session);
        int prevTime = 0;
        while (!sessions.isEmpty()) {
            Session currentSession = sessions.removeFirst();
            currentSession.time++;
            if (prevTime != currentSession.time) {
                visited.clear();
            }
            addPressureToSession(currentSession);
            currentSession.totalRelease += currentSession.pressureFlowRate;
            if (currentSession.time == 26) {
                sum = Math.max(currentSession.totalRelease, sum);
            } else {
                if (branchKiller(results, currentSession)) {
                    continue;
                }
                if (currentSession.tick1 == 1 && currentSession.tick2 == 1) {
                    addIfNotVisited(visited, currentSession, sessions);
                    continue;
                }
                branchingValvesOpenings(valvesMap, visited, currentSession, sessions);
            }
            prevTime = currentSession.time;
        }
        return sum;
    }

    private static void branchingValvesOpenings(HashMap<String, Valve> valvesMap, HashSet<Session> visited, Session currentSession, ArrayDeque<Session> sessions) {
        for (String leadsToValves : currentSession.position.leadsToValves) {
            for (String leadsToValves2 : currentSession.position2.leadsToValves) {
                if (leadsToValves2.equals(leadsToValves)) {
                    continue;
                }
                Session newSession = currentSession.deepCopy();
                Valve valve = valvesMap.get(leadsToValves);
                Valve valve2 = valvesMap.get(leadsToValves2);
                a1(newSession, valve, currentSession);
                a2(newSession, valve2, currentSession);
                if (newSession.currentlyOpening1 != null && newSession.currentlyOpening2 != null || newSession.currentlyOpening1 != null || newSession.currentlyOpening2 != null) {
                    addIfNotVisited(visited, newSession, sessions);
                } else {
                    newSession.position = valve;
                    newSession.position2 = valve2;
                    addIfNotVisited(visited, newSession, sessions);
                }
            }
        }
    }

    private static boolean branchKiller(HashMap<Integer, Integer[]> results, Session currentSession) {
        Integer[] orDefault = results.getOrDefault(currentSession.time, initArray());
        int i = 50;
        if (orDefault[currentSession.totalRelease] < i) {
            orDefault[currentSession.totalRelease]++;
        } else if (orDefault[currentSession.totalRelease] >= i) {
            return true;
        }
        results.put(currentSession.time, orDefault);
        return false;
    }

    private static Integer[] initArray() {
        Integer[] integers = new Integer[3000];
        Arrays.fill(integers, 0);
        return integers;
    }

    private static void addPressureToSession(Session currentSession) {
        if (currentSession.currentlyOpening1 != null && currentSession.tick1 == 1) {
            currentSession.opened.add(currentSession.currentlyOpening1.name);
            currentSession.pressureFlowRate += currentSession.currentlyOpening1.flowRate;
            currentSession.currentlyOpening1 = null;
            currentSession.tick1 = 0;
        } else if (currentSession.currentlyOpening1 != null && currentSession.tick1 == 0) {
            currentSession.tick1++;
        }
        if (currentSession.currentlyOpening2 != null && currentSession.tick2 == 1) {
            currentSession.opened.add(currentSession.currentlyOpening2.name);
            currentSession.pressureFlowRate += currentSession.currentlyOpening2.flowRate;
            currentSession.currentlyOpening2 = null;
            currentSession.tick2 = 0;
        } else if (currentSession.currentlyOpening2 != null && currentSession.tick2 == 0) {
            currentSession.tick2++;
        }
    }

    private static void a2(Session newSession, Valve valve2, Session currentSession) {
        if (!newSession.opened.contains(valve2.name)) {
            if (valve2.flowRate != 0 && isSame2(newSession, valve2) && currentSession.currentlyOpening2 == null) {
                newSession.position2 = valve2;
                newSession.currentlyOpening2 = valve2;
            } else if (valve2.flowRate == 0 && isSame2(newSession, valve2) && currentSession.currentlyOpening2 == null) {
                newSession.position = valve2;
            }
        }
    }

    private static void a1(Session newSession, Valve valve, Session currentSession) {
        if (!newSession.opened.contains(valve.name)) {
            if (valve.flowRate != 0 && isSame(newSession, valve) && currentSession.currentlyOpening1 == null) {
                newSession.position = valve;
                newSession.currentlyOpening1 = valve;
            } else if (valve.flowRate == 0 && isSame(newSession, valve) && currentSession.currentlyOpening1 == null) {
                newSession.position = valve;
            }
        }
    }

    private static void addIfNotVisited(HashSet<Session> visited, Session newSession, ArrayDeque<Session> sessions) {
        if (!visited.contains(newSession)) {
            sessions.addLast(newSession);
            visited.add(newSession);
        }
    }


    private static boolean isSame(Session newSession, Valve valve) {
        if (newSession.currentlyOpening2 == null) {
            return true;
        }
        return !newSession.currentlyOpening2.name.equals(valve.name);
    }

    private static boolean isSame2(Session newSession, Valve valve2) {
        if (newSession.currentlyOpening1 == null) {
            return true;
        }
        return !newSession.currentlyOpening1.name.equals(valve2.name);
    }

    private static ArrayList<Valve> parseStringIntoValves(List<String> l) {
        ArrayList<Valve> valves = new ArrayList<>();
        for (String s : l) {
            String[] strings = s.split(" ");
            Valve valve = new Valve();
            valve.name = strings[1];
            valve.flowRate = Integer.parseInt(strings[4].replace("rate=", "").replace(";", ""));
            String[] valvesStrings = s.substring(s.lastIndexOf("valve") + 6).split(",");
            for (String valvesString : valvesStrings) {
                valve.leadsToValves.add(valvesString.trim());
            }
            valves.add(valve);
        }
        return valves;
    }

    private static class Session {
        Valve position;
        Valve position2;
        int time = 0;
        int pressureFlowRate = 0;
        int totalRelease = 0;
        Valve currentlyOpening1;
        Valve currentlyOpening2;
        int tick1 = 0;
        int tick2 = 0;
        HashSet<String> opened = new HashSet<>();

        Session deepCopy() {
            Session session = new Session();
            session.position = this.position;
            session.position2 = this.position2;
            session.time = this.time;
            session.pressureFlowRate = this.pressureFlowRate;
            session.totalRelease = this.totalRelease;
            session.currentlyOpening1 = this.currentlyOpening1;
            session.currentlyOpening2 = this.currentlyOpening2;
            session.tick1 = this.tick1;
            session.tick2 = this.tick2;
            session.opened = new HashSet<>();
            session.opened.addAll(opened);
            return session;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Session session = (Session) o;

            if (time != session.time) return false;
            if (pressureFlowRate != session.pressureFlowRate) return false;
            if (totalRelease != session.totalRelease) return false;
            if (tick1 != session.tick1) return false;
            if (tick2 != session.tick2) return false;
            if (!Objects.equals(position, session.position)) return false;
            if (!Objects.equals(position2, session.position2)) return false;
            if (!Objects.equals(currentlyOpening1, session.currentlyOpening1)) return false;
            if (!Objects.equals(currentlyOpening2, session.currentlyOpening2)) return false;
            return Objects.equals(opened, session.opened);
        }

        @Override
        public int hashCode() {
            int result = position != null ? position.hashCode() : 0;
            result = 31 * result + (position2 != null ? position2.hashCode() : 0);
            result = 31 * result + time;
            result = 31 * result + pressureFlowRate;
            result = 31 * result + (int) (totalRelease ^ (totalRelease >>> 32));
            result = 31 * result + (currentlyOpening1 != null ? currentlyOpening1.hashCode() : 0);
            result = 31 * result + (currentlyOpening2 != null ? currentlyOpening2.hashCode() : 0);
            result = 31 * result + tick1;
            result = 31 * result + tick2;
            result = 31 * result + (opened != null ? opened.hashCode() : 0);
            return result;
        }
    }

    private static class Valve {
        int flowRate;
        List<String> leadsToValves = new ArrayList<>();
        String name;

        @Override
        public String toString() {
            return "Valve{" + "flowRate=" + flowRate + ", LeadsToValves=" + leadsToValves + ", name='" + name + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Valve valve = (Valve) o;

            if (flowRate != valve.flowRate) return false;
            if (!Objects.equals(leadsToValves, valve.leadsToValves)) return false;
            return Objects.equals(name, valve.name);
        }

        @Override
        public int hashCode() {
            int result = flowRate;
            result = 31 * result + (leadsToValves != null ? leadsToValves.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

}
