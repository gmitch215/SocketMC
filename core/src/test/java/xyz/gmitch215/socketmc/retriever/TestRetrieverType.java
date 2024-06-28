package xyz.gmitch215.socketmc.retriever;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestRetrieverType {

    @Test
    @DisplayName("Test RetrieverType#values")
    public void testIds() {
        Assertions.assertNotNull(RetrieverType.values());
        Assertions.assertTrue(RetrieverType.values().length > 0);
    }

    @Test
    @DisplayName("Test RetrieverType")
    public void testRetrieverType() {
        for (RetrieverType<?> type : RetrieverType.values()) {
            Assertions.assertNotNull(type);
            Assertions.assertNotNull(type.getId());

            Assertions.assertDoesNotThrow(() -> RetrieverType.class.getDeclaredField(type.getId().toUpperCase()), "RetrieverType ID does not match field: " + type.getId());
        }
    }

    @Test
    @DisplayName("Test RetrieverType#getPermission")
    public void testGetPermission() {
        for (RetrieverType<?> type : RetrieverType.values()) {
            Assertions.assertNotNull(type.getPermission());
        }
    }

}
