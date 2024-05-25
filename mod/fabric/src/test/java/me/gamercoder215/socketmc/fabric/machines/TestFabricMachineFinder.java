package me.gamercoder215.socketmc.fabric.machines;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.Machine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestFabricMachineFinder {

    @Test
    @DisplayName("Test FabricMachineFinder#getMachine")
    public void testGetMachine() {
        Assertions.assertTrue(Instruction.ids().length > 0);

        for (String id : Instruction.ids()) {
            Machine m = FabricMachineFinder.getMachine(id);
            Assertions.assertNotNull(m, "Machine for id '" + id + "' not found");
            Assertions.assertDoesNotThrow(() -> m.getClass().getDeclaredField("MACHINE"));
        }
    }

}