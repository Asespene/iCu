package builtInChips;

import java.awt.Component;
import java.awt.Panel;

import Hack.Gates.BuiltInGateWithGUI;
import Hack.Gates.GateException;

public class PC extends BuiltInGateWithGUI {
    private short current;
    private short next;

    protected void reCompute() {
        outputPins[0].set(current);
    }

    protected void clockUp() {
        if (inputPins[3].get() != 0) {
            next = 0;
        } else if (inputPins[1].get() != 0) {
            next = inputPins[0].get();
        } else if (inputPins[2].get() != 0) {
            next = (short) (current + 1);
        } else {
            next = current;
        }
    }

    protected void clockDown() {
        current = next;
        outputPins[0].set(current);
    }

    public Component getGUIComponent() {
        return new Panel();
    }

    public short getValueAt(int index) throws GateException {
        if (index != 0) {
            throw new GateException("PC index out of range");
        }
        return current;
    }

    public void setValueAt(int index, short value) throws GateException {
        if (index != 0) {
            throw new GateException("PC index out of range");
        }
        current = value;
        next = value;
        outputPins[0].set(current);
    }
}
