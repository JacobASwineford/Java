package Misc.CustomClasses.Uno;

public enum Precedence {
    // Colored card precedence.
    NUMBER_PRECEDENCE(5),
    DRAW_2_PRECEDENCE(4),
    REVERSE_PRECEDENCE(3),
    SKIP_PRECEDENCE(3),

    // Wild card precedence.
    WILD_PRECEDENCE(2),
    WILD_DRAW_4_PRECEDENCE(1);

    private final int pre;

    Precedence(int pre) {
        this.pre = pre;
    }

    public int getPrecedenceValue() {return pre;}
}
