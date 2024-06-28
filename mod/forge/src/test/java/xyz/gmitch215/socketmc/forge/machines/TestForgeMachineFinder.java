package xyz.gmitch215.socketmc.forge.machines;

import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.machines.MachineFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestForgeMachineFinder {

    @Test
    @DisplayName("Test MachineFinder#getMachine (Forge)")
    public void testGetMachine() {
        Assertions.assertTrue(Instruction.ids().length > 0);

        for (String id : Instruction.ids()) {
            Machine m = MachineFinder.getMachine(ForgeMachineFinder.MACHINES, id);
            Assertions.assertNotNull(m, "Forge Machine for id '" + id + "' not found");
            Assertions.assertDoesNotThrow(() -> m.getClass().getDeclaredField("MACHINE"), "Missing 'MACHINE' field in forge machine for id '" + id + "'");
        }
    }

}
