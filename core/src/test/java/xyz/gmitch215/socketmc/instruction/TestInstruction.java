package xyz.gmitch215.socketmc.instruction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.gmitch215.socketmc.instruction.Instruction;

public final class TestInstruction {

    @Test
    @DisplayName("Test Instruction#ids")
    public void testIds() {
        Assertions.assertNotNull(Instruction.ids());
        Assertions.assertTrue(Instruction.ids().length > 0);
    }

    @Test
    @DisplayName("Test Instruction#getPermission")
    public void testGetPermission() {
        for (String id : Instruction.ids()) {
            Assertions.assertNotNull(Instruction.getPermission(id));
        }
    }

}
