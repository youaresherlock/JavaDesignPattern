/*
* @Author: Clarence
* @Date:   2018-08-18 23:59:11
* @Last Modified by:   Clarence
* @Last Modified time: 2018-08-19 13:21:35
*/

/*
你可以有很多方法把对象堆起来放入一个集合中，可以使用数组、堆栈、列表或者是散列表等数据结构来存储对象，
我们将学习如何遍历这些集合中的对象，又不暴露对象的存储方式。也将学习如何将多种存储方式的集合来统一遍历。
举个例子:
两家餐厅对象村餐厅和对象村煎饼屋都有自己存储菜单项对象的数据结构，直接看代码
*/

// 菜单项类
public class MenuItem{
	String name;
	String description;
	boolean vegetarian;
	double price;

	public MenuItem(String name, String description, 
		boolean vegetarian, double price){
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public boolean getVegetarian(){
		return vegetarian;
	}

	public double getPrice(){
		return price;
	}
}

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






































































































































