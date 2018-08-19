package com.gougoucompany.designpattern.iteratorandcombination;

//另一家餐厅类

public class DinerMenuWithIterator{
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;
	//使用一个数组可以控制菜单的长度，取出菜单项的时候不需要向下转型
	MenuItem[] menuItems; 

	public DinerMenuWithIterator(){
		menuItems = new MenuItem[MAX_ITEMS];

		addItem("Vegetarian BLT", 
			"(Fakin') Bacon with lettuce & tomato on whole wheat",
			true,
			2.99);

		addItem("BLT",
			"Bacon with lettuce & tomato on whole wheat",
			false,
			2.99);

		addItem("Soup of the day", 
			"Soup of the day, with a side of potato salad",
			false,
			3.29);

		addItem("Hotdog", 
			"A hot dog, with saurkraut, relish, onions, topped with cheese",
			false,
			3.05);
	}

	public void addItem(String name, String description,
		boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		if (numberOfItems >= MAX_ITEMS){
			System.err.println("Sorry, menu is full! Can't add item to menu");
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems = numberOfItems + 1;
		}
	}

	public MenuItem[] getMenuItems(){
		return menuItems;
	}

	//这里还有其他的方法依赖数组的存储方式
}