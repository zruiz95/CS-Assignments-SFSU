package interpreter;

import java.util.ArrayList;
import java.util.HashMap;

import interpreter.bytecode.AddressLabel;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.LabelCode;

public class Program {

    private ArrayList<ByteCode> program;

    public Program() {
        program = new ArrayList<>();
    }


    public Program(ArrayList<ByteCode> loadedByteCodes) {
        program = loadedByteCodes;
    }

    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    public int getSize() {
        return this.program.size();
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-structure bytecodes are stored in.
     */
    public void resolveAddrs() {
        HashMap<String, Integer> addresses = new HashMap<>();

        // Store addresses of labels in HashMap
        for (int i = 0; i < program.size(); i++) {
            if (program.get(i) instanceof LabelCode) {
                addresses.put(((LabelCode) program.get(i)).getLabel(), i);
            }
        }

        for (ByteCode bc : program) {
            if (bc instanceof AddressLabel) {

                ((AddressLabel) bc).setAddress(addresses.get(((AddressLabel) bc).getLabel()));
            }
        }
    }
}
