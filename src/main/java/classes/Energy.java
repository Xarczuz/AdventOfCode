package classes;

public class Energy {

    public boolean flashed;
    public int lightLevel;

    public Energy(int lightLevel) {
        this.lightLevel = lightLevel;
        flashed = false;
    }

    @Override
    public String toString() {
        return lightLevel+"";
    }


}
