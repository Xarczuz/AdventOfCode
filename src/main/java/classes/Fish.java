package classes;

public class Fish {
    public int internalTimer;
    public int unSynckedTimer;
    public int unSynckedFish;
    public long fishCount;
    public long totalInsync;
    public int[] unsyncFishesTime;
    public long[] unsyncFishes;

    public Fish(int internalTimer) {
        this.internalTimer = internalTimer;
        this.unSynckedFish = 0;
        fishCount = 0;
        totalInsync = 1;
        unsyncFishesTime = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
        unsyncFishes = new long[] {0, 0, 0, 0, 0, 0, 0, 0, 0};

    }
}
