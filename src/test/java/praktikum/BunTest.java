package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {

    private final Bun bun = new Bun("test-name", 1);

    @Test
    public void getName() {
        assertEquals("test-name", bun.getName());
    }

    @Test
    public void getPrice() {
        assertEquals(1, bun.getPrice(), 0);
    }
}