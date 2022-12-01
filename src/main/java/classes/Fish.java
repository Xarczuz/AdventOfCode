package classes;

public class Fish {
    public int internalTimer;
    public long[] unsyncFishes;

    public Fish(int internalTimer) {
        this.internalTimer = internalTimer;
        unsyncFishes = new long[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
    }
}
