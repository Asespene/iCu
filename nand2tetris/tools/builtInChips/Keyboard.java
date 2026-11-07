package builtInChips;

import java.awt.Component;
import java.awt.Panel;

import Hack.Gates.BuiltInGateWithGUI;
import Hack.Gates.GateException;
import Hack.Gates.GateClass;
import Hack.Gates.Node;

public class Keyboard extends BuiltInGateWithGUI {
    private volatile short current = 75;

    protected void reCompute() {
        outputPins[0].set(current);
    }

    protected void clockUp() {
    }

    protected void clockDown() {
    }

    public void init(Node[] inputPins, Node[] outputPins, GateClass gateClass) {
        super.init(inputPins, outputPins, gateClass);
        Thread updater = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
            current = 89;
            this.outputPins[0].set(current);
        });
        updater.setDaemon(true);
        updater.start();
    }

    public Component getGUIComponent() {
        return new Panel();
    }

    public short getValueAt(int index) throws GateException {
        if (index != 0) {
            throw new GateException("Keyboard index out of range");
        }
        return current;
    }

    public void setValueAt(int index, short value) throws GateException {
        if (index != 0) {
            throw new GateException("Keyboard index out of range");
        }
        current = value;
        outputPins[0].set(value);
    }
}
