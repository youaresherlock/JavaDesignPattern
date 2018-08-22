package com.gougoucompany.designpattern.iteratorsecond;

//≤‚ ‘≥Ã–Ú
public class MenuTestDrive{
	public static void main(String args[]){
		PancakeHouseMenuWithIterator pancakeHouseMenu = new PancakeHouseMenuWithIterator();
		DinerMenuWithIterator dinerMenu = new DinerMenuWithIterator();
		CafeMenu cafeMenu = new CafeMenu();
		
//		Object obj = (Object)dinerMenu;
//		
//		System.out.println(dinerMenu instanceof DinerMenuWithIterator);
//		System.out.println(obj instanceof DinerMenuWithIterator);
//		
//		System.out.println(DinerMenuWithIterator.class.isInstance(dinerMenu));
//		System.out.println(DinerMenuWithIterator.class.isInstance(obj));

		Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu, cafeMenu);

		waitress.printMenu();
	}
}

/*
Hotdog, 3.05 -- A hot dog, with saurkraut, relish, onions, topped with cheese

MENU
----------
BREAKFAST
K&B's Pancake Breakfast, 2.99 -- Pancakes with scrambled eggs, and toast
Regular Pancake Breakfast, 2.99 -- Pancakes with fried eggs, sausage
BlueBerry Pancakes, 3.49 -- Pancakes made with fresh blueberries
Waffles, 3.59 -- Waffles, with your choice of blueberries or strawberries

LUNCH
Vegetarian BLT, 2.99 -- (Fakin') Bacon with lettuce & tomato on whole wheat
BLT, 2.99 -- Bacon with lettuce & tomato on whole wheat
Soup of the day, 3.29 -- Soup of the day, with a side of potato salad
Hotdog, 3.05 -- A hot dog, with saurkraut, relish, onions, topped with cheese

DINNER
Soup of the day, 3.69 -- A cup of the soup of the day, with a side salad
Burrito, 4.29 -- A large burrito, with whole pinto beans, salsa, guacamole
Veggie Burger and Air Fries, 3.99 -- Veggie burger on a whole wheat bun, lettuce, tomato, and fries

*/