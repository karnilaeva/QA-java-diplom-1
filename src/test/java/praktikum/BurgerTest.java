package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1;

    @Mock
    Ingredient ingredient2;

    @Test
    public void setBuns() {
        Burger burger = new Burger();

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredients() {
        Burger burger = new Burger();

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        assertEquals(List.of(ingredient1, ingredient2), burger.ingredients);
    }

    @Test
    public void moveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(1, 0);

        assertEquals(List.of(ingredient2, ingredient1), burger.ingredients);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveAbsentIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);

        burger.moveIngredient(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveIngredientToWrongPosition() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);

        burger.moveIngredient(0, 1);
    }

    @Test
    public void removeIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(1);

        assertEquals(List.of(ingredient1), burger.ingredients);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAbsentIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);

        burger.removeIngredient(1);
    }

    @Test
    public void getPriceForIngredients() {
        Mockito.when(ingredient1.getPrice()).thenReturn(200f);
        Mockito.when(ingredient2.getPrice()).thenReturn(300f);

        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        assertEquals(500, burger.getPrice(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void getEmptyPrice() {
        Burger burger = new Burger();

        burger.getPrice();
    }

    @Test
    public void getReceipt() {
        Mockito.when(bun.getName()).thenReturn("White");
        Mockito.when(bun.getPrice()).thenReturn(100f);

        Mockito.when(ingredient1.getName()).thenReturn("Beef cutlet");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient1.getPrice()).thenReturn(300f);

        Mockito.when(ingredient2.getName()).thenReturn("Ketchup");
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient2.getPrice()).thenReturn(200f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String expected =
                "(==== White ====)\n" +
                "= filling Beef cutlet =\n" +
                "= sauce Ketchup =\n" +
                "(==== White ====)\n" +
                "\n" +
                "Price: 700,000000\n";

        assertEquals(expected, burger.getReceipt());
    }

    @Test(expected = IllegalStateException.class)
    public void getReceiptForEmptyBurger() {
        Burger burger = new Burger();

        burger.getReceipt();
    }
}