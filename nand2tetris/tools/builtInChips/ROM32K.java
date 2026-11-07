package builtInChips;

import java.awt.Component;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Hack.Gates.BuiltInGateWithGUI;
import Hack.Gates.GateException;

public class ROM32K extends BuiltInGateWithGUI {
    private final short[] memory = new short[32768];

    protected void reCompute() {
        outputPins[0].set(memory[inputPins[0].get() & 0x7fff]);
    }

    protected void clockUp() {
    }

    protected void clockDown() {
    }

    public Component getGUIComponent() {
        return new Panel();
    }

    public short getValueAt(int index) throws GateException {
        if (index < 0 || index >= memory.length) {
            throw new GateException("ROM32K index out of range");
        }
        return memory[index];
    }

    public void setValueAt(int index, short value) throws GateException {
        if (index < 0 || index >= memory.length) {
            throw new GateException("ROM32K index out of range");
        }
        memory[index] = value;
    }

    public void doCommand(String[] command) throws GateException {
        String fileName = command[command.length - 1];
        loadFile(resolve(fileName));
        outputPins[0].set(memory[inputPins[0].get() & 0x7fff]);
    }

    private File resolve(String fileName) throws GateException {
        File file = new File(fileName);
        if (file.isFile()) {
            return file;
        }
        File projects05 = new File("../projects/05", fileName);
        if (projects05.isFile()) {
            return projects05;
        }
        throw new GateException("Cannot find ROM file: " + fileName);
    }

    private void loadFile(File file) throws GateException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int address = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                memory[address++] = (short) Integer.parseInt(line, 2);
            }
        } catch (IOException | NumberFormatException e) {
            throw new GateException("Cannot load ROM file: " + file.getPath());
        }
    }
}
