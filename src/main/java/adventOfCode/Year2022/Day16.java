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
//        twoStar(l);
        TimeUtil.endTime();
    }

    private static void oneStar(List<String> l) {
        ArrayList<Valve> valves = parseStringIntoValves(l);
        Session session = new Session();
        HashMap<String, Valve> valvesMap = new HashMap<>();
        long sum = 0;
        HashMap<Integer, ArrayList<Long>> results = new HashMap<>();
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

    private static long startOpeningValves(Session session, HashMap<String, Valve> valves, long sum, HashMap<Integer, ArrayList<Long>> results) {
        LinkedList<Session> sessions = new LinkedList<>();
        sessions.add(session);
        while (!sessions.isEmpty()) {
            Session currentSession = sessions.removeFirst();
            currentSession.time++;
            if (currentSession.currentlyOpening != null && currentSession.tick == 1) {
                currentSession.opened.add(currentSession.currentlyOpening.name);
                currentSession.pressureFlowRate += currentSession.currentlyOpening.flowRate;
                currentSession.currentlyOpening = null;
            } else if (currentSession.currentlyOpening != null && currentSession.tick == 0) {
                currentSession.tick++;
            }
            currentSession.totalRelease += currentSession.pressureFlowRate;

            if (currentSession.time == 30) {
                sum = Math.max(currentSession.totalRelease, sum);
            } else {
                if (currentSession.currentlyOpening != null && currentSession.tick == 1) {
                    sessions.addLast(currentSession);
                    continue;
                } else {
                    currentSession.tick = 0;
                }
                ArrayList<Long> orDefault = results.getOrDefault(currentSession.time, new ArrayList<>());
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
                for (String leadsToValves : currentSession.position.LeadsToValves) {
                    Valve valve = valves.get(leadsToValves);
                    Session newSession = currentSession.deepcopy();
                    if (!currentSession.opened.contains(valve.name)) {
                        if (valve.flowRate != 0) {
                            newSession.position = valve;
                            newSession.currentlyOpening = valve;
                            sessions.addLast(newSession);
                        }
                    }
                    newSession = currentSession.deepcopy();
                    newSession.position = valve;
                    sessions.addLast(newSession);
                }
            }
        }
        return sum;
    }

    private static void twoStar(List<String> l) {
        ArrayList<Valve> valves = parseStringIntoValves(l);
        Session session = new Session();
        HashMap<String, Valve> valvesMap = new HashMap<>();
        long sum = 0;
        HashMap<Integer, ArrayList<Long>> results = new HashMap<>();
        for (Valve valve : valves) {
            valvesMap.put(valve.name, valve);
        }
        for (Valve valve : valves) {
            if (valve.name.equals("AA")) {
                session.position = valve;
                session.position2 = valve;
                sum = startOpeningValves2(session, valvesMap, sum, results);
                break;
            }
        }
        System.out.println(valves);
        System.out.println("Result: " + sum);
    }

    private static long startOpeningValves2(Session session, HashMap<String, Valve> valvesMap, long sum, HashMap<Integer, ArrayList<Long>> results) {
        LinkedList<Session> sessions = new LinkedList<>();
        sessions.add(session);
        while (!sessions.isEmpty()) {
            Session currentSession = sessions.removeFirst();
            currentSession.time++;
            if (currentSession.currentlyOpening != null && currentSession.tick == 1) {
                currentSession.opened.add(currentSession.currentlyOpening.name);
                currentSession.pressureFlowRate += currentSession.currentlyOpening.flowRate;
                currentSession.currentlyOpening = null;
                currentSession.tick = 0;
            } else if (currentSession.currentlyOpening != null && currentSession.tick == 0) {
                currentSession.tick++;
            }
            if (currentSession.currentlyOpening2 != null && currentSession.tick2 == 1) {
                currentSession.opened.add(currentSession.currentlyOpening2.name);
                currentSession.pressureFlowRate += currentSession.currentlyOpening2.flowRate;
                currentSession.currentlyOpening2 = null;
                currentSession.tick2 = 0;
            } else if (currentSession.currentlyOpening2 != null && currentSession.tick2 == 0) {
                currentSession.tick2++;
            }
            currentSession.totalRelease += currentSession.pressureFlowRate;
            if (currentSession.time == 26) {
                sum = Math.max(currentSession.totalRelease, sum);
            } else {
                ArrayList<Long> orDefault = results.getOrDefault(currentSession.time, new ArrayList<>());
                if (orDefault.size() <= 600) {
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
                for (String leadsToValves : currentSession.position.LeadsToValves) {
                    for (String leadsToValves2 : currentSession.position2.LeadsToValves) {
                        Session newSession = currentSession.deepcopy();
                        Valve valve = valvesMap.get(leadsToValves);
                        Valve valve2 = valvesMap.get(leadsToValves2);
                        if (!currentSession.opened.contains(valve.name)) {
                            if (valve.flowRate != 0 && isSame(newSession, valve) && currentSession.currentlyOpening == null && currentSession.tick < 1) {
                                newSession.position = valve;
                                newSession.currentlyOpening = valve;
                            }
                        }
                        if (!currentSession.opened.contains(valve2.name)) {
                            if (valve2.flowRate != 0 && isSame2(newSession, valve2) && currentSession.currentlyOpening2 == null && currentSession.tick2 < 1) {
                                newSession.position2 = valve2;
                                newSession.currentlyOpening2 = valve2;
                            }
                        }
                        if (newSession.currentlyOpening != null && newSession.currentlyOpening2 != null) {
                            sessions.addLast(newSession);
                        } else if (newSession.currentlyOpening != null) {
                            Session variant = newSession.deepcopy();
                            variant.position2 = valve2;
                            sessions.addLast(variant);
                        } else if (newSession.currentlyOpening2 != null) {
                            Session variant = newSession.deepcopy();
                            variant.position = valve;
                            sessions.addLast(variant);
                        }
                        newSession = currentSession.deepcopy();
                        newSession.position = valve;
                        newSession.position2 = valve2;
                        sessions.addLast(newSession);
                    }
                }

            }
        }
        return sum;
    }

    private static boolean isSame(Session newSession, Valve valve) {
        if (newSession.currentlyOpening2 == null) {
            return true;
        }
        return !newSession.currentlyOpening2.name.equals(valve.name);
    }

    private static boolean isSame2(Session newSession, Valve valve2) {
        if (newSession.currentlyOpening == null) {
            return true;
        }
        return !newSession.currentlyOpening.name.equals(valve2.name);
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
                valve.LeadsToValves.add(valvesString.trim());
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
        long totalRelease = 0;
        Valve currentlyOpening;
        Valve currentlyOpening2;
        int tick = 0;
        int tick2 = 0;
        HashSet<String> opened = new HashSet<>();

        Session deepcopy() {
            Session session = new Session();
            session.position = this.position;
            session.position2 = this.position2;
            session.time = this.time;
            session.pressureFlowRate = this.pressureFlowRate;
            session.totalRelease = this.totalRelease;
            session.currentlyOpening = this.currentlyOpening;
            session.currentlyOpening2 = this.currentlyOpening2;
            session.tick = this.tick;
            session.tick2 = this.tick2;
            session.opened = new HashSet<>();
            session.opened.addAll(opened);
            return session;
        }
    }

    private static class Valve {
        int flowRate;
        List<String> LeadsToValves = new ArrayList<>();
        String name;

        @Override
        public String toString() {
            return "Valve{" + "flowRate=" + flowRate + ", LeadsToValves=" + LeadsToValves + ", name='" + name + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Valve valve = (Valve) o;

            if (flowRate != valve.flowRate) return false;
            if (!Objects.equals(LeadsToValves, valve.LeadsToValves)) return false;
            return Objects.equals(name, valve.name);
        }

        @Override
        public int hashCode() {
            int result = flowRate;
            result = 31 * result + (LeadsToValves != null ? LeadsToValves.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }
}
