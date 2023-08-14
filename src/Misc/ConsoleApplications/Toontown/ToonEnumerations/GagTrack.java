package Misc.ConsoleApplications.Toontown.ToonEnumerations;

/**
 * An object representation of the gag track that any particular
 * gag belongs to.
 */
public enum GagTrack {
    ToonUp(0),
    Trap(1),
    Lure(2),
    Sound(3),
    Throw(4),
    Squirt(5),
    Drop(6);

    private int progressIndex;

    GagTrack(int progressIndex) {
        this.progressIndex = progressIndex;
    }

    /**
     * Gets the progress index of the current track.
     *
     * @return progress index
     */
    public int getProgressIndex() {
        return progressIndex;
    }
}
