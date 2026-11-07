package builtInChips;

import Hack.Gates.BuiltInGate;

public class Nand extends BuiltInGate {
    protected void reCompute() {
        short a = inputPins[0].get();
        short b = inputPins[1].get();
        outputPins[0].set((short) (1 - (a & b)));
    }
}
