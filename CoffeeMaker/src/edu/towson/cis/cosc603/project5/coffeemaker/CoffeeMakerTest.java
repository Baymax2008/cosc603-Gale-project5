package edu.towson.cis.cosc603.project5.coffeemaker;

import junit.framework.TestCase;

/**
 *
 */
public class CoffeeMakerTest extends TestCase {
	private CoffeeMaker cm;
	private Inventory i;
	private Recipe r1, r2, r3, r4, r5, r6;

	public void setUp() {
		cm = new CoffeeMaker();
		i = cm.checkInventory();

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
	}

	public void testAddRecipe1() {
		assertTrue(cm.addRecipe(r1));
	}
	
	public void testAddRecipe2() {
		cm.addRecipe(r1);
		assertFalse(cm.addRecipe(r1));
	}
	
	public void testAddRecipe3() {
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.addRecipe(r3);
		cm.addRecipe(r4);
		assertFalse(cm.addRecipe(r5));
	}

	public void testDeleteRecipe1() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}
	
	public void testDeleteRecipe2() {
		cm.addRecipe(r1);
		assertFalse(cm.deleteRecipe(r2));
	}
	
	public void testDeleteRecipe3() {
		cm.addRecipe(r1);
		assertFalse(cm.deleteRecipe(r6));
	}

	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}
	
	public void testEditRecipe2() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r5;
		newRecipe.setAmtSugar(2);
		assertFalse(cm.editRecipe(r1, newRecipe));
	}

	public void testCheckOptions(){
		// not tested
	}
	
	public void testAddInventory1(){
		assertTrue(cm.addInventory(10, 15, 20, 5));
		
		assertSame(20, i.getChocolate());
		assertSame(25, i.getCoffee());
		assertSame(30, i.getMilk());
		assertSame(35, i.getSugar());
		
	}
	
	public void testAddInventory2(){
		
		assertFalse(cm.addInventory(-1, 15, 20, 5));
		
	}
	
	public void testAddInventory3(){
		
		assertFalse(cm.addInventory(10, -1, 20, 5));

	}
	
	public void testAddInventory4(){
		
		assertFalse(cm.addInventory(10, 15, -1, 5));
		
	}
	
	public void testAddInventory5(){
		
		assertFalse(cm.addInventory(10, 15, 20, -1));
		
	}
	
	public void testGetRecipeForName1(){
		
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.addRecipe(r3);
		cm.addRecipe(r4);
		r6 = cm.getRecipeForName("White Chocolate Coffee");
		
		assertSame(r4, r6);
		
	}
	
public void testGetRecipeForName2(){
		
		cm.addRecipe(r1);
		r6 = cm.getRecipeForName("White Chocolate Coffee");
		
		assertNotSame(r4, r6);
		
	}
	
	public void testCheckInventory(){
		
		assertSame(15, i.getChocolate());
		assertSame(15, i.getCoffee());
		assertSame(15, i.getMilk());
		assertSame(15, i.getSugar());
		
	}
	
	public void testPurchaseBeverage1(){
		
		assertSame(10, cm.makeCoffee(r1, 60));
		assertSame(10, i.getChocolate());
		assertSame(9, i.getCoffee());
		assertSame(14, i.getMilk());
		assertSame(14, i.getSugar());
		
	}
	public void testPurchaseBeverage2(){
		
		assertSame(40, cm.makeCoffee(r1, 40));
		
	}
	public void testPurchaseBeverage3(){
		
		i.setCoffee(-5);
		assertSame(60, cm.makeCoffee(r1, 60));
		
	}
	public void testPurchaseBeverage4(){
		
		i.setChocolate(-10);
		assertSame(60, cm.makeCoffee(r3, 60));
		
	}
	
	public void testPurchaseBeverage5(){
		
		i.setMilk(-10);
		assertSame(60, cm.makeCoffee(r4, 60));
		
	}
	
	public void testPurchaseBeverage6(){
		
		i.setSugar(-10);
		assertSame(60, cm.makeCoffee(r3, 60));
		
	}	
	
	public void testPurchaseBeverage7(){
		
		r1.setPrice(-7);
		assertSame(60, cm.makeCoffee(r1, 60));
		
	}	
	
}