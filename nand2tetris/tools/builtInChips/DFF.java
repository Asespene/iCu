package builtInChips;

import Hack.Gates.BuiltInGate;

public class DFF extends BuiltInGate {
    private short current;
    private short next;

    protected void reCompute() {
        outputPins[0].set(current);
    }

    protected void clockUp() {
        next = inputPins[0].get();
    }

    protected void clockDown() {
        current = next;
        outputPins[0].set(current);
    }
}
