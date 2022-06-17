package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private final float bunPrice;
    private final List<Float> ingredientsPrices;
    private final float expectedPrice;

    public BurgerPriceTest(Float bunPrice, List<Float> ingredientPrices, Float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientsPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {100f, List.of(), 200f},
                {200f, List.of(100f, 400f), 900f},
                {0f, List.of(100f, 400f), 500f},
                {0f, List.of(0f, 100f), 100f},
                {100f, List.of(0f), 200f},
                {-100f, List.of(100f), -100f},
        };
    }

    @Test
    public void testPrice() {
        Burger burger = new Burger();

        Bun bun = new Bun("White", bunPrice);
        burger.setBuns(bun);

        for (float ingredientPrice : ingredientsPrices) {
            Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", ingredientPrice);
            burger.addIngredient(ingredient);
        }

        float actual = burger.getPrice();
        assertEquals(expectedPrice, actual, 0);
    }
}
