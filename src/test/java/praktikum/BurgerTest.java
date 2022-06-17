package praktikum;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BurgerTest {

    @Test
    public void setBuns() {
        Bun bun = new Bun("White", 100);
        Burger burger = new Burger();

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredients() {
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Beef cutlet", 300);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 200);
        Burger burger = new Burger();

        burger.addIngredient(filling);
        burger.addIngredient(sauce);

        assertEquals(List.of(filling, sauce), burger.ingredients);
    }

    @Test
    public void moveIngredient() {
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Beef cutlet", 300);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 200);
        Burger burger = new Burger();
        burger.addIngredient(filling);
        burger.addIngredient(filling);
        burger.addIngredient(filling);
        burger.addIngredient(sauce);

        burger.moveIngredient(3, 1);

        assertEquals(List.of(filling, sauce, filling, filling), burger.ingredients);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveAbsentIngredient() {
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Beef cutlet", 300);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 200);
        Burger burger = new Burger();
        burger.addIngredient(filling);

        burger.moveIngredient(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveIngredientToWrongPosition() {
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Beef cutlet", 300);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 200);
        Burger burger = new Burger();
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);
    }

    @Test
    public void removeIngredient() {
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Beef cutlet", 300);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 200);
        Burger burger = new Burger();
        burger.addIngredient(filling);
        burger.addIngredient(filling);
        burger.addIngredient(sauce);

        burger.removeIngredient(1);

        assertEquals(List.of(filling, sauce), burger.ingredients);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAbsentIngredient() {
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Beef cutlet", 300);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 200);
        Burger burger = new Burger();
        burger.addIngredient(filling);

        burger.removeIngredient(1);
    }

    @Test
    public void getPriceForIngredients() {
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Beef cutlet", 300);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 200);

        Burger burger = new Burger();
        burger.addIngredient(filling);
        burger.addIngredient(sauce);

        assertEquals(500, burger.getPrice(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void getEmptyPrice() {
        Burger burger = new Burger();

        burger.getPrice();
    }

    @Test
    public void getReceipt() {
        Bun bun = new Bun("White", 100);
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Beef cutlet", 300);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 200);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(filling);
        burger.addIngredient(sauce);

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