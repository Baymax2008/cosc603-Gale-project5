package edu.towson.cis.cosc603.project5.coffeemaker;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoffeeMakerTest {
	private CoffeeMaker cm;
	private Inventory i;
	private Inventory i2;
	private Recipe r1, r2, r3, r4, r5, r6, r7, r8;

	@Before
	public void setUp() throws Exception {
		cm = new CoffeeMaker();
		i = cm.checkInventory();
		i2 = new Inventory();

		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(6);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(5);
		
		r2 = new Recipe();
		r2.setName("Carmel Coffee");
		r2.setPrice(50);
		r2.setAmtCoffee(-6);
		r2.setAmtMilk(1);
		r2.setAmtSugar(1);
		r2.setAmtChocolate(0);
		
		r3 = new Recipe();
		r3.setName("Mocha Coffee");
		r3.setPrice(50);
		r3.setAmtCoffee(6);
		r3.setAmtMilk(-1);
		r3.setAmtSugar(1);
		r3.setAmtChocolate(5);
		
		r4 = new Recipe();
		r4.setName("White Chocolate Coffee");
		r4.setPrice(50);
		r4.setAmtCoffee(6);
		r4.setAmtMilk(1);
		r4.setAmtSugar(-1);
		r4.setAmtChocolate(-6);
		
		r5 = new Recipe();
		r5.setName("Latte Coffee");
		r5.setPrice(50);
		r5.setAmtCoffee(6);
		r5.setAmtMilk(1);
		r5.setAmtSugar(1);
		r5.setAmtChocolate(0);
		
		r7 = new Recipe();
		r7.setName("Chocolate Coffee");
		r7.setPrice(50);
		r7.setAmtCoffee(5);
		r7.setAmtMilk(5);
		r7.setAmtSugar(5);
		r7.setAmtChocolate(5);
		
		r8 = new Recipe();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddRecipeGood() {
		assertTrue(cm.addRecipe(r1));
	}
	
	@Test
	public void testAddRecipeCopy() {
		cm.addRecipe(r1);
		assertFalse(cm.addRecipe(r1));
	}
	
	@Test
	public void testAddRecipeAddTooMany() {
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.addRecipe(r3);
		cm.addRecipe(r4);
		assertFalse(cm.addRecipe(r5));
	}

	@Test
	public void testDeleteRecipeGood() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}

	@Test	
	public void testDeleteRecipeNotThere() {
		cm.addRecipe(r1);
		assertFalse(cm.deleteRecipe(r2));
	}

	@Test	
	public void testDeleteRecipeNull() {
		cm.addRecipe(r1);
		assertFalse(cm.deleteRecipe(r6));
	}

	@Test
	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}

	@Test	
	public void testEditRecipe2() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r5;
		newRecipe.setAmtSugar(2);
		assertFalse(cm.editRecipe(r1, newRecipe));
	}

	@Test
	public void testAddInventory1(){
		assertTrue(cm.addInventory(10, 15, 20, 5));
		
		assertSame(20, i.getChocolate());
		assertSame(25, i.getCoffee());
		assertSame(30, i.getMilk());
		assertSame(35, i.getSugar());
		
	}
	
	@Test
	public void testAddInventoryFailNegCoffee(){
		
		assertFalse(cm.addInventory(-1, 15, 20, 5));
		
	}
	
	@Test
	public void testAddInventoryPass0Coffee(){
		
		assertTrue(cm.addInventory(0, 15, 20, 5));
		
	}
	
	@Test
	public void testAddInventoryFailNegMilk(){
		
		assertFalse(cm.addInventory(10, -1, 20, 5));

	}
	
	@Test
	public void testAddInventoryPass0Milk(){
		
		assertTrue(cm.addInventory(10, 0, 20, 5));
		
	}
	
	@Test
	public void testAddInventoryNegSugar(){
		
		assertFalse(cm.addInventory(10, 15, -1, 5));
		
	}
	
	@Test
	public void testAddInventoryPass0Sugar(){
		
		assertTrue(cm.addInventory(10, 15, 0, 5));
		
	}
	
	@Test
	public void testAddInventoryNegChocolate(){
		
		assertFalse(cm.addInventory(10, 15, 20, -1));
		
	}
	
	@Test
	public void testAddInventoryPass0Chocolate(){
		
		assertTrue(cm.addInventory(10, 15, 20, 0));
		
	}

	@Test
	public void testCheckInventory() {
		assertSame(15, i.getChocolate());
		assertSame(15, i.getCoffee());
		assertSame(15, i.getMilk());
		assertSame(15, i.getSugar());
	}
	
	@Test
	public void testCheckInventorySetChocolate0() {
		
		i.setChocolate(-10);
		assertSame(0, i.getChocolate());
	}
	
	@Test
	public void testCheckInventorySetCoffeeNeg10() {
		
		i.setCoffee(-10);
		assertSame(0, i.getCoffee());
	}
	
	@Test
	public void testCheckInventorySetMilkNeg10() {
		
		i.setMilk(-10);
		assertSame(0, i.getMilk());
	}
	
	@Test
	public void testCheckInventorySetSugarNeg10() {
		
		i.setSugar(-10);
		assertSame(0, i.getSugar());
	}

	@Test
	public void testPurchaseBeverageGoodBuyWithChange(){
		
		assertSame(10, cm.makeCoffee(r1, 60));
		assertSame(10, i.getChocolate());
		assertSame(9, i.getCoffee());
		assertSame(14, i.getMilk());
		assertSame(14, i.getSugar());
		
	}

	@Test
	public void testPurchaseBeverageGppdBuyExactChange(){
		
		assertSame(0, cm.makeCoffee(r1, 50));
		assertSame(10, i.getChocolate());
		assertSame(9, i.getCoffee());
		assertSame(14, i.getMilk());
		assertSame(14, i.getSugar());
		
	}
	
	@Test
	public void testPurchaseBeverageBadBuyNotEnoughMoney(){
		
		assertSame(40, cm.makeCoffee(r1, 40));
		
	}	

	@Test
	public void testPurchaseBeverage3(){
		
		i.setCoffee(-5);
		assertSame(60, cm.makeCoffee(r1, 60));
		
	}

	@Test
	public void testPurchaseBeverage4(){
		
		i.setChocolate(-10);
		assertSame(60, cm.makeCoffee(r3, 60));
		
	}

	@Test
	public void testPurchaseBeverage5(){
		
		i.setMilk(-10);
		assertSame(60, cm.makeCoffee(r4, 60));
		
	}

	@Test
	public void testPurchaseBeverageSugarNeg(){
		
		i.setSugar(-10);
		assertSame(60, cm.makeCoffee(r3, 60));
		
	}
	
	@Test
	public void testPurchaseBeverageSugar0(){
		
		i.setSugar(0);
		assertSame(60, cm.makeCoffee(r3, 60));
		
	}	
	
	@Test
	public void testPurchaseBeverageSugarTooLow(){
		
		i.setSugar(2);
		assertSame(60, cm.makeCoffee(r7, 60));
		
	}	

	@Test
	public void testPurchaseBeveragePriceNeg(){
		
		r1.setPrice(-7);
		assertSame(60, cm.makeCoffee(r1, 60));
		
	}

	@Test
	public void testGetRecipes() {
		
		Recipe[] listOfRecipe; 
		
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.addRecipe(r3);
		cm.addRecipe(r4);
		
		listOfRecipe = cm.getRecipes();
		
		assertSame(r4, listOfRecipe[0]);
		assertSame(r3, listOfRecipe[1]);
		assertSame(r2, listOfRecipe[2]);
		assertSame(r1, listOfRecipe[3]);
	}

	@Test
	public void testGetRecipeForName1(){
		
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.addRecipe(r3);
		cm.addRecipe(r4);
		r6 = cm.getRecipeForName("White Chocolate Coffee");
		
		assertSame(r4, r6);
		
	}

	@Test
	public void testGetRecipeForName2(){
		
		cm.addRecipe(r1);
		r6 = cm.getRecipeForName("White Chocolate Coffee");
		
		assertNotSame(r4, r6);
		
	}
	
	//Testing Recipe
	
	@Test
	public void testGetAmtChocolate() {
		assertSame(5, r7.getAmtChocolate());
	}

	@Test
	public void testSetAmtChocolatePos() {
		r7.setAmtChocolate(15);
		assertSame(15, r7.getAmtChocolate());
		r7.setAmtChocolate(10);
		assertSame(10, r7.getAmtChocolate());
		r7.setAmtChocolate(5);
		assertSame(5, r7.getAmtChocolate());
		r7.setAmtChocolate(4);
		assertSame(4, r7.getAmtChocolate());
		r7.setAmtChocolate(3);
		assertSame(3, r7.getAmtChocolate());
		r7.setAmtChocolate(2);
		assertSame(2, r7.getAmtChocolate());
		r7.setAmtChocolate(1);
		assertSame(1, r7.getAmtChocolate());
	}
	
	@Test
	public void testSetAmtChocolate0() {
		r7.setAmtChocolate(0);
		assertSame(0, r7.getAmtChocolate());
	}
	
	@Test
	public void testSetAmtChocolateNeg() {
		r7.setAmtChocolate(-1);
		assertSame(0, r7.getAmtChocolate());
		r7.setAmtChocolate(-2);
		assertSame(0, r7.getAmtChocolate());
		r7.setAmtChocolate(-3);
		assertSame(0, r7.getAmtChocolate());
		r7.setAmtChocolate(-4);
		assertSame(0, r7.getAmtChocolate());
		r7.setAmtChocolate(-5);
		assertSame(0, r7.getAmtChocolate());
		r7.setAmtChocolate(-10);
		assertSame(0, r7.getAmtChocolate());
		r7.setAmtChocolate(-15);
		assertSame(0, r7.getAmtChocolate());
	}

	@Test
	public void testGetAmtCoffee() {
		assertSame(5, r7.getAmtCoffee());
	}

	@Test
	public void testSetAmtCoffeePos() {
		r7.setAmtCoffee(15);
		assertSame(15, r7.getAmtCoffee());
	}
	
	@Test
	public void testSetAmtCoffee0() {
		r7.setAmtCoffee(0);
		assertSame(0, r7.getAmtCoffee());
	}
	
	@Test
	public void testSetAmtCoffeeNeg() {
		r7.setAmtCoffee(-15);
		assertSame(0, r7.getAmtCoffee());
	}

	@Test
	public void testGetAmtMilk() {
		assertSame(5, r7.getAmtMilk());
	}

	@Test
	public void testSetAmtMilkPos() {
		r7.setAmtMilk(15);
		assertSame(15, r7.getAmtMilk());
	}

	@Test
	public void testSetAmtMilko() {
		r7.setAmtMilk(0);
		assertSame(0, r7.getAmtMilk());
	}
	
	@Test
	public void testSetAmtMilkNeg() {
		r7.setAmtMilk(-15);
		assertSame(0, r7.getAmtMilk());
	}
	
	@Test
	public void testGetAmtSugar() {
		assertSame(5, r7.getAmtSugar());
	}

	@Test
	public void testSetAmtSugarPos() {
		r7.setAmtSugar(15);
		assertSame(15, r7.getAmtSugar());
	}

	@Test
	public void testSetAmtSugar0() {
		r7.setAmtSugar(0);
		assertSame(0, r7.getAmtSugar());
	}

	@Test
	public void testSetAmtSugarNeg() {
		r7.setAmtSugar(-15);
		assertSame(0, r7.getAmtSugar());
	}

	@Test
	public void testGetName() {
		assertSame("Chocolate Coffee", r7.getName());
	}

	@Test
	public void testSetName() {
		
		r7.setName("Coffee");
		assertSame("Coffee", r7.getName());
	}

	@Test
	public void testGetPrice() {
		assertSame(50, r7.getPrice());
	}

	@Test
	public void testSetPricePos() {
		r7.setPrice(40);
		assertSame(40, r7.getPrice());
	}
	
	@Test
	public void testSetPrice0() {
		r7.setPrice(0);
		assertSame(0, r7.getPrice());
	}
	
	@Test
	public void testSetPriceNeg() {
		r7.setPrice(-40);
		assertSame(0, r7.getPrice());
	}

	@Test
	public void testEqualsRecipeSame() {
		assertTrue(r7.equals(r7));
	}
	
	@Test
	public void testEqualsRecipeThisNull() {
		assertFalse(r8.equals(r7));
	}
	
	//Inventory Test
	
	@Test
	public void testGetChocolate() {
		assertSame(15, i2.getChocolate());
	}

	@Test
	public void testSetChocolateWithPos() {
		i2.setChocolate(10);
		assertSame(10, i2.getChocolate());
	}
	
	public void testSetChocolateWith1() {
		i2.setChocolate(1);
		assertSame(1, i2.getChocolate());
	}
	
	@Test
	public void testSetChocolateWith0() {
		i2.setChocolate(0);
		assertSame(0, i2.getChocolate());
	}
	
	public void testSetChocolateWithNeg1() {
		i2.setChocolate(-1);
		assertSame(0, i2.getChocolate());
	}
		
	@Test
	public void testSetChocolateWithNeg() {
		i2.setChocolate(-10);
		assertSame(0, i2.getChocolate());
	}

	@Test
	public void testGetCoffee() {
		assertSame(15, i2.getCoffee());
	}

	@Test
	public void testSetCoffeeWithPos() {
		i2.setCoffee(10);
		assertSame(10, i2.getCoffee());
	}
	
	@Test
	public void testSetCoffeeWith0() {
		i2.setCoffee(0);
		assertSame(0, i2.getCoffee());
	}
	
	@Test
	public void testSetCoffeeWithNeg() {
		i2.setCoffee(-10);
		assertSame(0, i2.getCoffee());
	}

	@Test
	public void testGetMilk() {
		assertSame(15, i2.getMilk());
	}

	@Test
	public void testSetMilkWithPos() {
		i2.setMilk(10);
		assertSame(10, i2.getMilk());
	}
	
	@Test
	public void testSetMilkWith0() {
		i2.setMilk(0);
		assertSame(0, i2.getMilk());
	}
	
	@Test
	public void testSetMilkWithNeg() {
		i2.setMilk(-10);
		assertSame(0, i2.getMilk());
	}

	@Test
	public void testGetSugar() {
		assertSame(15, i2.getSugar());
	}

	@Test
	public void testSetSugarWithPos() {
		i2.setSugar(10);
		assertSame(10, i2.getSugar());
	}
	
	@Test
	public void testSetSugarWith0() {
		i2.setSugar(0);
		assertSame(0, i2.getSugar());
	}
	
	@Test
	public void testSetSugarWithNeg() {
		i2.setSugar(-10);
		assertSame(0, i2.getSugar());
	}

	@Test
	public void testEnoughIngredientsGood() {
		assertTrue(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadCoffeeNeg() {
		i2.setCoffee(-10);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadCoffee0() {
		i2.setCoffee(0);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadCoffee3() {
		i2.setCoffee(3);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsGoodCoffee5() {
		i2.setCoffee(5);
		assertTrue(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadChocolateNeg() {
		i2.setChocolate(-10);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadChocolate0() {
		i2.setChocolate(0);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadChocolate3() {
		i2.setChocolate(3);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsGoodChocolate5() {
		i2.setChocolate(5);
		assertTrue(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadMilkNeg() {
		i2.setMilk(-10);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadMilk0() {
		i2.setMilk(0);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadMilk3() {
		i2.setMilk(3);
		assertFalse(i2.enoughIngredients(r7));
	}	
	
	@Test
	public void testEnoughIngredientsBadMilk5() {
		i2.setMilk(5);
		assertTrue(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadSugarNeg() {
		i2.setSugar(-10);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadSugar0() {
		i2.setSugar(0);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadSugar3() {
		i2.setSugar(3);
		assertFalse(i2.enoughIngredients(r7));
	}
	
	@Test
	public void testEnoughIngredientsBadSugar5() {
		i2.setSugar(5);
		assertTrue(i2.enoughIngredients(r7));
	}

}
