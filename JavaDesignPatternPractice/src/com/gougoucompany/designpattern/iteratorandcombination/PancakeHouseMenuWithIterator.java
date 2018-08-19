package com.gougoucompany.designpattern.iteratorandcombination;

import java.util.ArrayList;

//���Ҳ�����ʵ�ַ�ʽ

public class PancakeHouseMenuWithIterator{
	ArrayList<MenuItem> menuItems;

	public PancakeHouseMenuWithIterator(){
		menuItems = new ArrayList<>();

		addItem("K&B's Pancake Breakfast", 
			"Pancakes with scrambled eggs, and toast",
			true,
			2.99);

		addItem("Regular Pancake Breakfast",
			"Pancakes with fried eggs, sausage",
			false,
			2.99);

		addItem("BlueBerry Pancakes", 
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

//	public ArrayList<MenuItem> getMenuItems(){
//		return menuItems;
//	}
	
	public Iterator createIterator() {
		return new PancakeHouseMenuIterator(menuItems);
	}

	//��������������ArrayList�洢��ʽ
}
