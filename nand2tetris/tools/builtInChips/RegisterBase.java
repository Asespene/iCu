package builtInChips;

import java.awt.Component;
import java.awt.Panel;

import Hack.Gates.BuiltInGateWithGUI;
import Hack.Gates.GateException;

abstract class RegisterBase extends BuiltInGateWithGUI {
    protected short current;
    protected short next;
    private boolean clockHigh;

    protected void reCompute() {
        outputPins[0].set(current);
    }

    protected void clockUp() {
        next = inputPins[1].get() == 0 ? current : inputPins[0].get();
        clockHigh = true;
    }

    protected void clockDown() {
        current = next;
        clockHigh = false;
        outputPins[0].set(current);
    }

    public Component getGUIComponent() {
        return new Panel();
    }

    public short getValueAt(int index) throws GateException {
        if (index != 0) {
            throw new GateException("Register index out of range");
        }
        return clockHigh ? next : current;
    }

    public void setValueAt(int index, short value) throws GateException {
        if (index != 0) {
            throw new GateException("Register index out of range");
        }
        current = value;
        next = value;
        outputPins[0].set(current);
    }
}
