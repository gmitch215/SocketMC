package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.screen.DefaultScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
