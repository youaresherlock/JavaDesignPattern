package com.gougoucompany.designpattern.iteratorthird;

public class MenuTestDrive {
	public static void main(String[] args) {
		MenuComponent pancakeHouseMenu =
				new Menu("PANCAKE HOUSE MENU", "Breakfast");
		MenuComponent dinerMenu =
				new Menu("DINER MENU", "Lunch");
		MenuComponent cafeMenu =
				new Menu("CAFE MENU", "Diner");
		MenuComponent dessertMenu =
				new Menu("DESSERT MENU", "Dessert of course!");
		
		MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
		
		allMenus.add(pancakeHouseMenu);
		allMenus.add(dinerMenu);
		allMenus.add(cafeMenu);
		
		//这里加入其它菜单项
		
		dinerMenu.add(new MenuItem(
				"Pasta", 
				"Spaghetti with Marinara Sauce, and a slice of sourdough bread",
				true,
				3.89));
		
		dinerMenu.add(dessertMenu);
		
		dessertMenu.add(new MenuItem(
				"Apple Pie", 
				"Apple pie with a flakey crust, topped with vanilla ice cream",
				true,
				1.59));
		
		//在这里加入更多菜单项
		
		Waitress waitress = new Waitress(allMenus);
		waitress.printMenu();	
		
	}
}


/*
从结果来分析，我们根节点下面有三个菜单结点，只有dinerMenu有一个菜单项,和一个菜单，另外两个菜单项没有子节点
递归顺序就是前序遍历的顺序，先是pancakeHouseMenu,dinerMenu,子菜单Pasta,dessertMenu,菜单项Apple pie,，最后是cafeMenu
ALL MENUS, All menus combined
-----------------------

PANCAKE HOUSE MENU, Breakfast
-----------------------

DINER MENU, Lunch
-----------------------
  Pasta(v), 3.89
   -- Spaghetti with Marinara Sauce, and a slice of sourdough bread

DESSERT MENU, Dessert of course!
-----------------------
  Apple Pie(v), 1.59
   -- Apple pie with a flakey crust, topped with vanilla ice cream

CAFE MENU, Diner
-----------------------
 */



















