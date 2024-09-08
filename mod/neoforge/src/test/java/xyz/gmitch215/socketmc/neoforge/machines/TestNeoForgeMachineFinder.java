package xyz.gmitch215.socketmc.neoforge.machines;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.machines.MachineFinder;

public class TestNeoForgeMachineFinder {

    @Test
    @DisplayName("Test MachineFinder#getMachine (NeoForge)")
    public void testGetMachine() {
        Assertions.assertTrue(Instruction.ids().length > 0);

        for (String id : Instruction.ids()) {
            Machine m = MachineFinder.getMachine(NeoForgeMachineFinder.MACHINES, id);
            Assertions.assertNotNull(m, "NeoForge Machine for id '" + id + "' not found");
            Assertions.assertDoesNotThrow(() -> m.getClass().getDeclaredField("MACHINE"), "Missing 'MACHINE' field in forge machine for id '" + id + "'");
        }
    }

}
