package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void testLocationCreation() {
        Location location = new Location(5, 10);
        assertEquals(5, location.getX());
        assertEquals(10, location.getY());
    }

    @Test
    void testLocationEquality() {
        Location loc1 = new Location(3, 4);
        Location loc2 = new Location(3, 4);
        Location loc3 = new Location(3, 5);

        assertEquals(loc1, loc2);
        assertNotEquals(loc1, loc3);
        assertEquals(loc1.hashCode(), loc2.hashCode());
    }

    @Test
    void testToString() {
        Location location = new Location(7, 8);
        assertEquals("(7,8)", location.toString());
    }
}