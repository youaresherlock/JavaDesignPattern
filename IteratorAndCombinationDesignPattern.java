/*
* @Author: Clarence
* @Date:   2018-08-18 23:59:11
* @Last Modified by:   Clarence
* @Last Modified time: 2018-08-19 23:55:11
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


//另一家餐厅类

public class DinerMenu{
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;
	//使用一个数组可以控制菜单的长度，取出菜单项的时候不需要向下转型
	MenuItem[] menuItems; 

	public DinerMenu(){
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


//如果要遍历两个餐厅提供的所有菜单，只能逐个实现，而且依赖于各自集合的存储数据结构，调用者必须知道菜单项的存储方式，不符合封装的思想

import java.util.ArrayList;

public class PrintMenuTest {
	
	static PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
	static ArrayList<MenuItem> breakfastItems = pancakeHouseMenu.getMenuItems();
	
	static DinerMenu dinerMenu = new DinerMenu();
	static MenuItem[] lunchItems = dinerMenu.getMenuItems();
	
	public static void main(String args[]) {
		printPancakeHouseMenu();
		printDinerMenu();
	}
	
	public static void printPancakeHouseMenu() {
		for(int i = 0; i < breakfastItems.size(); i ++) {
			MenuItem menuItem = (MenuItem)breakfastItems.get(i);
			System.out.print(menuItem.getName() + " ");
			System.out.println(menuItem.getPrice() + " ");
			System.out.println(menuItem.getDescription());
		}
	}
	
	public static void printDinerMenu() {
		for(int i = 0; i < lunchItems.length; i ++) {
			MenuItem menuItem = lunchItems[i];
			if(menuItem != null) {
				System.out.print(menuItem.getName() + " ");
				System.out.println(menuItem.getPrice() + " ");
				System.out.println(menuItem.getDescription());
			}
		}
	}
}


//在这里发生变化的是由不同集合类型所造成的遍历，我们可以想这些变化封装起来，我们可以利用迭代器接口生成具体的迭代器来复合不同形式的存储方式的餐厅类

//迭代器接口
public interface Iterator{
	boolean hasNext(); //返回一个布尔值，是否有下一个元素
	Object next(); // 返回下一个元素
}

//为DinerMenu实现Iterator
public class DinerMenuIterator implements Iterator{
	MenuItem[] items;
	int position = 0; //position记录当前数组遍历的位置

	//构造器需要传入一个菜单项的数组当做参数
	public DinerMenuIterator(MenuItem[] items){ 
		this.items = items;
	}

	//返回数组中下一项，并递增其位置
	public Object next(){
		MenuItem menuItem = items[position];
		position = position + 1;
		return menuItem;
	}

	public boolean hasNext(){
		//检查是否越界以及是否不存在MenuItem对象
		if(position >= items.length || items[position] == null){
			return false;
		} else {
			return true;
		}
	}
}

//用迭代器改写DinerMenu菜单，我们创建一个新的类DinerMenuWithIterator


























































































































