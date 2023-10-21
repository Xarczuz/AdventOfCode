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
        oneStar(l2);
//        oneStar(l );
        TimeUtil.endTime();
//        TimeUtil.startTime();
//        twoStar(l2);
//        twoStar(l);
//        TimeUtil.endTime();
    }

    static long sum = 0;
    static LinkedList<Integer> topTenFlowRate = new LinkedList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

    private static void oneStar(List<String> l) {
        HashMap<String, Valve> valves = parseStringIntoValves(l);
        Session session = new Session();
        for (Valve valve : valves.values()) {
            session.position = valve;
            startOpeningValves(session, valves);
        }
        System.out.println(valves);
        System.out.println("Resulr: " + sum);
    }

    private static void startOpeningValves(Session session, HashMap<String, Valve> valves) {
        session.time++;
        if (session.currentlyOpening != null) {
            session.opened.add(session.currentlyOpening);
            session.pressureFlowRate += session.currentlyOpening.flowRate;
            session.currentlyOpening = null;
        }
        boolean lower = false;
        if (session.time > 20) {

            for (Integer i : topTenFlowRate) {
                if (session.totalRelease < i) {
                    lower = true;
                    break;
                }
            }
            if (lower) {
                return;
            } else {
                topTenFlowRate.add((int) session.totalRelease);
                topTenFlowRate.sort(Integer::compareTo);
                topTenFlowRate.removeFirst();
            }
        }
        session.totalRelease += session.pressureFlowRate;
        if (session.time == 30) {
            sum = Math.max(session.totalRelease, sum);
            return;
        }

        for (String leadsToValves : session.position.LeadsToValves) {
            Valve valve = valves.get(leadsToValves);
            if (session.opened.contains(valve)) {
                Session newSession = session.deepcopy();
                newSession.position = valve;
                startOpeningValves(newSession, valves);
            } else {
                Session newSession = session.deepcopy();
                newSession.position = valve;
                newSession.currentlyOpening = valve;
                startOpeningValves(newSession, valves);
                newSession = session.deepcopy();
                newSession.position = valve;
                startOpeningValves(newSession, valves);
            }
        }
    }

    private static HashMap<String, Valve> parseStringIntoValves(List<String> l) {
        HashMap<String, Valve> valves = new HashMap<>();
        for (String s : l) {
            String[] strings = s.split(" ");
            Valve valve = new Valve();
            valve.name = strings[1];
            valve.flowRate = Integer.parseInt(strings[4].replace("rate=", "").replace(";", ""));
            String[] valvesStrings = s.substring(s.lastIndexOf("valve") + 6).split(",");
            for (String valvesString : valvesStrings) {
                valve.LeadsToValves.add(valvesString.trim());
            }
            valves.put(valve.name, valve);
        }
        return valves;
    }

    private static class Session {
        Valve position;
        int time = 1;
        int pressureFlowRate = 0;
        long totalRelease = 0;
        Valve currentlyOpening;
        HashSet<Valve> opened = new HashSet<>();

        Session deepcopy() {
            Session session = new Session();
            session.position = this.position;
            session.time = this.time;
            session.pressureFlowRate = this.pressureFlowRate;
            session.totalRelease = this.totalRelease;
            session.opened = new HashSet<>(opened);
            session.currentlyOpening = this.currentlyOpening;
            return session;
        }
    }

    private static class Valve {
        int flowRate;
        List<String> LeadsToValves = new ArrayList<>();
        String name;

        @Override
        public String toString() {
            return "Valve{" +
                    "flowRate=" + flowRate +
                    ", LeadsToValves=" + LeadsToValves +
                    ", name='" + name +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Valve valve = (Valve) o;

            if (flowRate != valve.flowRate) return false;
            if (!Objects.equals(LeadsToValves, valve.LeadsToValves))
                return false;
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
