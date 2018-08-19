package com.gougoucompany.designpattern.iteratorandcombination;

import java.util.ArrayList;

//两家餐厅的实现方式

public class PancakeHouseMenu{
	ArrayList<MenuItem> menuItems;

	public PancakeHouseMenu(){
		menuItems = new ArrayList<>();

		addItem("K&B's Pancake Breakfast", 
			"Pancakes with scrambled eggs, and toast",
			true,
			2.99);

		addItem("Regular Pancake Breakfast",
			"Pancakes with fried eggs, sausage",
			false,
			2.99);

		addItem("Bleberry Pancakes", 
			"Pancakes made with fresh blueberries",
			true,
			3.49);

		addItem("Waffles", 
			"Waffles, with your choice of blueberries or strawberries",
			true,
			3.59);
	}

	public void addItem(String name, String description,
		boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.add(menuItem);
	}

	public ArrayList<MenuItem> getMenuItems(){
		return menuItems;
	}

	//其他方法依赖于ArrayList存储方式
}
