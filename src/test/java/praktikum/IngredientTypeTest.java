package praktikum;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void sauce() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void filling() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

}
