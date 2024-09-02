package xyz.gmitch215.socketmc.neoforge.machines;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.gmitch215.socketmc.screen.DefaultScreen;

public class TestOpenScreenMachine {

    @Test
    @DisplayName("Test OpenScreenMachine#defaultScreen")
    public void testDefaultScreen() {
        for (DefaultScreen screen : DefaultScreen.getAllScreens()) {
            try {
                Assertions.assertNotNull(OpenScreenMachine.defaultScreen(screen, null));
            } catch (NullPointerException ignored) {
                // Found Screen, needs "minecraft"
            }
        }
    }

}
