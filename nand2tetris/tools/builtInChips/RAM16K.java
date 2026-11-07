package builtInChips;

import java.awt.Component;
import java.awt.Panel;

import Hack.Gates.BuiltInGateWithGUI;
import Hack.Gates.GateException;

public class RAM16K extends BuiltInGateWithGUI {
    private final short[] memory = new short[16384];
    private int nextAddress;
    private short nextValue;
    private boolean shouldLoad;

    protected void reCompute() {
        outputPins[0].set(memory[address()]);
    }

    protected void clockUp() {
        nextAddress = address();
        nextValue = inputPins[0].get();
        shouldLoad = inputPins[1].get() != 0;
    }

    protected void clockDown() {
        if (shouldLoad) {
            memory[nextAddress] = nextValue;
        }
        outputPins[0].set(memory[address()]);
    }

    private int address() {
        return inputPins[2].get() & 0x3fff;
    }

    public Component getGUIComponent() {
        return new Panel();
    }

    public short getValueAt(int index) throws GateException {
        if (index < 0 || index >= memory.length) {
            throw new GateException("RAM16K index out of range");
        }
        return memory[index];
    }

    public void setValueAt(int index, short value) throws GateException {
        if (index < 0 || index >= memory.length) {
            throw new GateException("RAM16K index out of range");
        }
        memory[index] = value;
        outputPins[0].set(memory[address()]);
    }
}
