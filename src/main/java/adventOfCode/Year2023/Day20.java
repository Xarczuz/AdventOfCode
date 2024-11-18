package adventOfCode.Year2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import util.FileUtil;
import util.TimeUtil;

public class Day20 {

  public static void main(String[] args) throws IOException, URISyntaxException {
    List<String> l = FileUtil.readfile(Day19.class);
    List<String> l2 = FileUtil.readfileExempel(Day19.class);
    TimeUtil.startTime();
//    oneStar(l);
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

  }

  enum Pulse {
    HIGH,
    LOW
  }

  interface Sensor {

    Sensor[] send(Pulse pulse);
  }

  private class Broadcaster implements Sensor {

    String name;
    Sensor[] signalsOut;

    public Broadcaster(String name) {
      this.name = name;
    }

    @Override
    public Sensor[] send(Pulse pulse) {
      return new Sensor[0];
    }
  }

  private class FlipFlop implements Sensor {
    boolean on;
    String name;
    Sensor[] signalsOut;

    public FlipFlop(boolean on, String name) {
      this.on = on;
      this.name = name;
    }

    @Override
    public Sensor[] send(Pulse pulse) {
      return new Sensor[0];
    }
  }

  private class Conjunction implements Sensor {

    Sensor[] signalsIn;
    Sensor[] signalsOut;
    String name;

    public Conjunction(Sensor[] signalsIn) {
      this.signalsIn = signalsIn;
    }

    public Conjunction(String name, Sensor[] signalsOut, Sensor[] signalsIn) {
      this.name = name;
      this.signalsOut = signalsOut;
      this.signalsIn = signalsIn;
    }

    @Override
    public Sensor[] send(Pulse pulse) {
      return new Sensor[0];
    }
  }

}
