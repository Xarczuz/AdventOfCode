package adventOfCode.Year2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import util.FileUtil;
import util.TimeUtil;

public class Day20 {

  public static void main(String[] args) throws IOException, URISyntaxException {
    List<String> l = FileUtil.readfile(Day20.class);
    List<String> l2 = FileUtil.readfileExempel(Day20.class);
    TimeUtil.startTime();
    oneStar(l);
    oneStar(l2);
    TimeUtil.endTime();

//    TimeUtil.startTime();
//    twoStar(l);
//    twoStar(l2);
//    TimeUtil.endTime();
  }

  private static void twoStar(List<String> l) {

  }

  private static void oneStar(List<String> l) {
    ArrayList<Sensor> sensorArrayList = new ArrayList<>();
    for (String s : l) {
      if (s.contains("%")) {
        final String[] split = s.split("->");
        final String substring = split[0].substring(1).trim();
        final FlipFlop flipFlop = new FlipFlop(substring);
        sensorArrayList.add(flipFlop);
      } else if (s.contains("&")) {
        final String[] split = s.split("->");
        final String substring = split[0].substring(1).trim();
        final Conjunction flipFlop = new Conjunction(substring);
        sensorArrayList.add(flipFlop);
      }
    }
    for (String s : l) {
      if (s.contains("%") || s.contains("&")) {
        final String[] split = s.split("->");
        final String substring = split[0].substring(1).trim();
        final String[] split1 = split[1].split(",");
        Sensor flipFlopFound = null;
        for (Sensor flipFlop : sensorArrayList) {
          if (flipFlop.getName().equals(substring)) {
            flipFlopFound = flipFlop;
            break;
          }
        }
        for (String string : split1) {
          Sensor flipFlopOut = null;
          for (Sensor flipFlop : sensorArrayList) {
            if (flipFlop.getName().equals(string.trim())) {
              flipFlopOut = flipFlop;
              break;
            }
          }
          flipFlopFound.getSignalsOut().add(flipFlopOut);
        }
      }
    }
    System.out.println(sensorArrayList);
    for (String s : l) {
      if (s.contains("broad")) {
        final String[] split = s.split("->");
        final String[] split1 = split[1].split(",");
        for (String string : split1) {
//          broadcasters.add(new Broadcaster(string.trim()));
        }
      }
    }
    System.out.println(l);
//      System.out.println(broadcasters);

  }

  enum PulseType {
    HIGH,
    LOW
  }

  interface Sensor {

    ArrayList<Sensor> send(PulseSignal pulse);

    String getName();

    ArrayList<Sensor> getSignalsOut();
  }

  private static class PulseSignal {

    PulseType pulseType;
    ArrayList<Sensor> orgin;

  }

  private static class Broadcaster implements Sensor {

    String name;
    ArrayList<Sensor> signalsOut = new ArrayList<>();

    public Broadcaster(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public ArrayList<Sensor> getSignalsOut() {
      return signalsOut;
    }

    @Override
    public ArrayList<Sensor> send(PulseSignal pulse) {
      return new ArrayList<>();
    }

    @Override
    public String toString() {
      return "Broadcaster{" + "name='" + name + '\'' + ", signalsOut=" + signalsOut + '}';
    }
  }

  private static class FlipFlop implements Sensor {

    boolean on;
    String name;
    ArrayList<Sensor> signalsOut = new ArrayList<>();

    public FlipFlop(String name) {
      this.name = name;
    }

    @Override
    public ArrayList<Sensor> getSignalsOut() {
      return signalsOut;
    }

    public void setSignalsOut(ArrayList<Sensor> signalsOut) {
      this.signalsOut = signalsOut;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public ArrayList<Sensor> send(PulseSignal pulse) {
      return new ArrayList<>();
    }
  }

  private static class Conjunction implements Sensor {

    ArrayList<Sensor> signalsIn = new ArrayList<>();
    ArrayList<Sensor> signalsOut = new ArrayList<>();
    String name;

    public Conjunction(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return name;
    }

    public ArrayList<Sensor> getSignalsOut() {
      return signalsOut;
    }

    public void setSignalsOut(ArrayList<Sensor> signalsOut) {
      this.signalsOut = signalsOut;
    }

    @Override
    public ArrayList<Sensor> send(PulseSignal pulse) {
      return new ArrayList<>();
    }
  }

}
