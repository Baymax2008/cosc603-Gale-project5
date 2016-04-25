package edu.towson.cis.cosc603.project5.coffeemaker;

import junit.framework.TestCase;

/**
 *
 */
public class CoffeeMakerTest extends TestCase {
	private CoffeeMaker cm;
	private Inventory i;
	private Recipe r1;

	public void setUp() {
		cm = new CoffeeMaker();
		i = cm.checkInventory();

		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(6);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);
	}

	public void testAddRecipe1() {
		assertTrue(cm.addRecipe(r1));
	}

	public void testDeleteRecipe1() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}

	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}

	public void testCheckOptions(){
		
	}
	
	public void testAddInventory1(){
		assertTrue(cm.addInventory(10, 15, 20, 5));
		
		assertSame(20, i.getChocolate());
		assertSame(25, i.getCoffee());
		assertSame(30, i.getMilk());
		assertSame(35, i.getSugar());
		
	}
	
	public void testCheckInventory(){
		
		assertSame(15, i.getChocolate());
		assertSame(15, i.getCoffee());
		assertSame(15, i.getMilk());
		assertSame(15, i.getSugar());
		
	}
	
	public void testPurchaseBeverage1(){
		
		assertSame(10, cm.makeCoffee(r1, 60));
		
	}
	public void testPurchaseBeverage2(){
		
		assertSame(40, cm.makeCoffee(r1, 40));
		
	}
	public void testPurchaseBeverage3(){
		
		i.setCoffee(5);
		assertSame(60, cm.makeCoffee(r1, 60));
		
	}
}