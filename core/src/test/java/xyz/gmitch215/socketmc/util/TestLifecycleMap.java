package xyz.gmitch215.socketmc.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class TestLifecycleMap {

    @Test
    @DisplayName("Test LifecycleMap#put")
    public void testPut() {
        LifecycleMap<Integer> map = new LifecycleMap<>();

        map.put(1, 0, 1000);
        map.put(2, 1000, 1000);
        map.put(3, 2000, 1000);

        Assertions.assertEquals(3, map.size());
        for (int i = 1; i <= 3; i++) {
            Assertions.assertTrue(map.containsKey(i));
            Assertions.assertEquals(1000, map.getDuration(i));
        }
    }

    @Test
    @DisplayName("Test LifecycleMap#run")
    public void testRun() {
        LifecycleMap<Integer> map = new LifecycleMap<>();

        map.put(1, 0, 1000);
        map.put(2, 1000, 1000);
        map.put(3, 2000, 1000);

        Assertions.assertEquals(3, map.size());

        while (map.size() > 0) {
            map.run();
        }

        Assertions.assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Test LifecycleMap#store")
    public void testStore() {
        LifecycleMap<Function<Integer, Integer>> map = new LifecycleMap<>();

        map.store(i -> i + 1, 500);
        map.store(i -> i * 2, 1000);
        map.store(i -> i * i, 1500);

        Assertions.assertEquals(3, map.size());

        while (map.size() > 0) {
            map.run();
            map.forEach(f -> f.apply(0));
        }

        Assertions.assertEquals(0, map.size());
    }

}
