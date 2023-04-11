package com.questforholygrail.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LocationTest {

    @Test
    public void testGetDirections() {
        // Arrange
        Location location = new Location();
        Map<String, String> expectedDirections = new HashMap<>();
        expectedDirections.put("north", "Some direction to the north.");

        // Act
        location.setDirections(expectedDirections);

        // Assert
        Assertions.assertEquals(expectedDirections, location.getDirections());
    }


}

