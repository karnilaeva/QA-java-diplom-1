package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    private final Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "test-name", 1);

    @Test
    public void getPrice() {
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void getName() {
        assertEquals("test-name", ingredient.getName());
    }

    @Test
    public void getType() {
        assertEquals(1, ingredient.getPrice(), 0);
    }
}