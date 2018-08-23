/*
* @Author: Clarence
* @Date:   2018-08-18 23:59:11
* @Last Modified by:   Clarence
* @Last Modified time: 2018-08-23 22:18:01
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
	
	//调用者不需要知道集合的具体存储方式，只需要知道可以返回对应类型的迭代器对象来遍历集合就可以了
	public Iterator createIterator() {
		return new DinerMenuIterator(menuItems);
	}

	//不需要这个方法，会暴露我们内部的实现
//	public MenuItem[] getMenuItems(){
//		return menuItems;
//	}

	//这里还有其他的方法依赖数组的存储方式
}

//PancakeHouseMenuIterator迭代器实现
package com.gougoucompany.designpattern.iteratorandcombination;

import java.util.ArrayList;

public class PancakeHouseMenuIterator implements Iterator{
	ArrayList<MenuItem> menuItems;
	int position = 0; //记录遍历的位置
	
	public PancakeHouseMenuIterator(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public boolean hasNext() {
		// 这里其实不用判断元素是否是MenuIteme类向上转型的对象，因为泛型做出了限制
		if(position >= menuItems.size() || !(menuItems.get(position) instanceof MenuItem)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Object next() {
		MenuItem menuItem = menuItems.get(position);
		position += 1;
		return menuItem;
	}

}

//加入生成迭代器方法的类
package com.gougoucompany.designpattern.iteratorandcombination;

import java.util.ArrayList;

//两家餐厅的实现方式

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

	//其他方法依赖于ArrayList存储方式
}


//我们的服务员拿到菜单不需要知道菜单的存储方式可以通过拿到菜单迭代器来遍历集合中的对象
package com.gougoucompany.designpattern.iteratorandcombination;


public class Waitress{
	PancakeHouseMenuWithIterator pancakeHouseMenu;
	DinerMenuWithIterator dinerMenu;

	public Waitress(PancakeHouseMenuWithIterator pancakeHouseMenu, DinerMenuWithIterator dinerMenu){
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}

	public void printMenu(){
		Iterator pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator dinerMenuIterator = dinerMenu.createIterator();
		System.out.println("MENU\n----------\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerMenuIterator);
	}

	private void printMenu(Iterator iterator){
		while(iterator.hasNext()){
			MenuItem menuItem = (MenuItem) iterator.next(); //注意这里要向下转型 
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}

//测试程序
package com.gougoucompany.designpattern.iteratorandcombination;
public class MenuTestDrive{
	public static void main(String args[]){
		PancakeHouseMenuWithIterator pancakeHouseMenu = new PancakeHouseMenuWithIterator();
		DinerMenuWithIterator dinerMenu = new DinerMenuWithIterator();
		
//		Object obj = (Object)dinerMenu;
//		
//		System.out.println(dinerMenu instanceof DinerMenuWithIterator);
//		System.out.println(obj instanceof DinerMenuWithIterator);
//		
//		System.out.println(DinerMenuWithIterator.class.isInstance(dinerMenu));
//		System.out.println(DinerMenuWithIterator.class.isInstance(obj));

		Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);

		waitress.printMenu();
	}
}

//MENU
//----------
//BREAKFAST
//K&B's Pancake Breakfast, 2.99 -- Pancakes with scrambled eggs, and toast
//Regular Pancake Breakfast, 2.99 -- Pancakes with fried eggs, sausage
//BlueBerry Pancakes, 3.49 -- Pancakes made with fresh blueberries
//Waffles, 3.59 -- Waffles, with your choice of blueberries or strawberries
//
//LUNCH
//Vegetarian BLT, 2.99 -- (Fakin') Bacon with lettuce & tomato on whole wheat
//BLT, 2.99 -- Bacon with lettuce & tomato on whole wheat
//Soup of the day, 3.29 -- Soup of the day, with a side of potato salad
//Hotdog, 3.05 -- A hot dog, with saurkraut, relish, onions, topped with cheese




//上面的迭代器方法可以让Waitress女招待从具体实现中解耦，不需要知道菜单项的具体实现方式，
//不同的菜单都实现createIterator()方法来创建属于自己的迭代器对象，但是并没有实现相同的接口
//我们将修改这一点，让女招待不依赖具体的菜单
//我们将使用java.util.Iterator自带的迭代器接口
package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//为DinerMenu实现Iterator
public class DinerMenuIterator implements Iterator<MenuItem>{
	MenuItem[] items;
	int position = 0; //position记录当前数组遍历的位置

	//构造器需要传入一个菜单项的数组当做参数
	public DinerMenuIterator(MenuItem[] items){ 
		this.items = items;
	}

	@Override
	public boolean hasNext() {
		//检查是否越界以及是否不存在MenuItem对象
				if(position >= items.length || items[position] == null){
					return false;
				} else {
					return true;
				}
	}

	@Override
	public MenuItem next() {
		MenuItem menuItem = items[position];
		position = position + 1;
		return menuItem;
	}
	
	/**
	 * remove允许我们从聚合中删除由next方法返回的最后一项
	 */
	@Override
	public void remove() {
		//如果不需要remove()方法，可以抛出java.lang.UnsupportedOperationException运行时异常
		//RuntimeException运行时异常是不需要捕获的，当异常出现时，虚拟机会处理，常见的有空指针异常
		if(position <= 0) {
			throw new IllegalStateException
				("You can't remove an item until you've done at least one next()");
		}
		if(items[position - 1] != null) {
			//覆盖掉position位置的元素
			for(int i = position -1; i < (items.length - 1); i++) {
				items[i] = items[i + 1];
			}
			items[items.length - 1] = null; //删除元素前移后要覆盖最后一项元素，防止重合
		}
	}
}



//两家餐厅的实现方式

package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//另一家餐厅类

public class DinerMenuWithIterator implements Menu{
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
	
	//调用者不需要知道集合的具体存储方式，只需要知道可以返回对应类型的迭代器对象来遍历集合就可以了
	public Iterator<MenuItem> createIterator() {
		return new DinerMenuIterator(menuItems);
	}

	//不需要这个方法，会暴露我们内部的实现
//	public MenuItem[] getMenuItems(){
//		return menuItems;
//	}

	//这里还有其他的方法依赖数组的存储方式
}



package com.gougoucompany.designpattern.iteratorsecond;

import java.util.ArrayList;
import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//两家餐厅的实现方式

public class PancakeHouseMenuWithIterator implements Menu{
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
	
	public Iterator<MenuItem> createIterator() {
		return menuItems.iterator(); //不创建自己的迭代器，而调用菜单项ArrayList的iterator方法
	}

	//其他方法依赖于ArrayList存储方式
}


//提供一个公共的菜单接口，不同的菜单可以调用返回他们的迭代器
package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;
import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//给菜单一个共同的几口，让调用者取得一个菜单项的迭代器
public interface Menu {
	public Iterator<MenuItem> createIterator();
}


//女招待类 不依赖于具体的菜单类，因为使用了Menu接口，可以不论什么菜单，只需要调用接口的方法返回迭代器遍历就可以了
package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//我们的服务员拿到菜单不需要知道菜单的存储方式可以通过拿到菜单迭代器来遍历集合中的对象
public class Waitress{
	Menu pancakeHouseMenu;
	Menu dinerMenu;

	//将具体菜单类改成接口
	public Waitress(Menu pancakeHouseMenu, Menu dinerMenu){
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}

	public void printMenu(){
		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator<MenuItem> dinerMenuIterator = dinerMenu.createIterator();
		System.out.println("MENU\n----------\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerMenuIterator);
	}

	private void printMenu(Iterator<MenuItem> iterator){
		while(iterator.hasNext()){
			MenuItem menuItem = (MenuItem) iterator.next(); //注意这里要向下转型 
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}



//测试类
package com.gougoucompany.designpattern.iteratorsecond;

//测试程序
public class MenuTestDrive{
	public static void main(String args[]){
		PancakeHouseMenuWithIterator pancakeHouseMenu = new PancakeHouseMenuWithIterator();
		DinerMenuWithIterator dinerMenu = new DinerMenuWithIterator();
		
//		Object obj = (Object)dinerMenu;
//		
//		System.out.println(dinerMenu instanceof DinerMenuWithIterator);
//		System.out.println(obj instanceof DinerMenuWithIterator);
//		
//		System.out.println(DinerMenuWithIterator.class.isInstance(dinerMenu));
//		System.out.println(DinerMenuWithIterator.class.isInstance(obj));

		Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);

		waitress.printMenu();
	}
}

/*
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
*/
/*
这样编程的好处，两个菜单都实现了Menu接口，女招待可以利用接口，而不是具体类引用每一个
菜单对象，通过"针对接口编程，而不是针对实现编程"可以减少女招待和具体类之间的依赖。
定义迭代器模式:
	迭代器模式提供一种方法顺序访问一个聚合对象中的各个元素，而又不暴露其内部的表示。
迭代器模式把元素之间的游走的责任交给迭代器，而不是聚合对象。这不仅让聚合的接口和实现
变得更加简洁，也可以让聚合更专注在它所应该专注的事情上面，而不必去理会遍历的事情。

单一责任:
	一个类应该只有一个引起变化的原因，比如菜单类，实现管理某种集合，把遍历的责任交给迭代器去完成，
	这样该类就不容易错误，也容易修改。
内聚: 它用来度量一个类或模块紧密地达到单一目的或责任。 当一个模块或一个类被设计成只支持一组相关的功能时，
我们说它具有高内聚。内聚是一个比单一责任更普遍的概念，但两者关系密切。高内聚的类更容易维护。
*/



//我们又有新的需求，需要合并咖啡厅的菜单
//咖啡厅的菜单
package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Hashtable;
import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//我们在原来的早餐和午餐菜单中加入了晚餐菜单，这时候迭代器的优点就可以体现出来了
public class CafeMenu {
	Hashtable<String, MenuItem> menuItems = new Hashtable<String, MenuItem>();
	
	public CafeMenu() {
	
	}
	
	public void addItem(String name, String description,
			boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.put(menuItem.getName(), menuItem);
	}
	
	public Hashtable<String, MenuItem> getItems() {
		return menuItems;
	}
	
}
//重做咖啡厅代码

package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Hashtable;
import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//我们在原来的早餐和午餐菜单中加入了晚餐菜单，这时候迭代器的优点就可以体现出来了
public class CafeMenu implements Menu{
	Hashtable<String, MenuItem> menuItems = new Hashtable<String, MenuItem>();
	
	public CafeMenu() {
		addItem("Veggie Burger and Air Fries",
				"Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
				true, 3.99);
		addItem("Soup of the day",
				"A cup of the soup of the day, with a side salad",
				false, 3.69);
		addItem("Burrito",
				"A large burrito, with whole pinto beans, salsa, guacamole",
				true, 4.29);
	}
	
	public void addItem(String name, String description,
			boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.put(menuItem.getName(), menuItem);
	}
	
	public Hashtable<String, MenuItem> getItems() {
		return menuItems;
	}

	@Override
	public Iterator<MenuItem> createIterator() {
		// TODO Auto-generated method stub
		return menuItems.values().iterator(); //取得值的那部分迭代器
	}
	
}

//修改女招待类
package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//我们的服务员拿到菜单不需要知道菜单的存储方式可以通过拿到菜单迭代器来遍历集合中的对象
public class Waitress{
	Menu pancakeHouseMenu;
	Menu dinerMenu;
	Menu cafeMenu;

	//将具体菜单类改成接口
	public Waitress(Menu pancakeHouseMenu, Menu dinerMenu, Menu cafeMenu){
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
		this.cafeMenu = cafeMenu;
	}

	public void printMenu(){
		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator<MenuItem> dinerMenuIterator = dinerMenu.createIterator();
		Iterator<MenuItem> cafeMenuIterator = cafeMenu.createIterator();
		System.out.println("MENU\n----------\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerMenuIterator);
		System.out.println("\nDINNER");
		printMenu(cafeMenuIterator);
	}

	private void printMenu(Iterator<MenuItem> iterator){
		while(iterator.hasNext()){
			MenuItem menuItem = (MenuItem) iterator.next(); //注意这里要向下转型 
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}
//测试类

package com.gougoucompany.designpattern.iteratorsecond;

//测试程序
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



/*
Java5中，所有的集合都已经新增了对遍历的支持，所以你甚至不需要请求迭代器了
for/in这可以让你在一个集合或者一个数组中遍历，而且不需要显示创建迭代器。
for(Object obj : collection){
	....
}

每次我们有新的菜单加入，就必须打开女招待实现并加入更多的代码，而且print方法代码时重复的。
我们仍然将菜单成分离而独立的对象--我们需要一种一起管理他们的方法。
*/
package com.gougoucompany.designpattern.iteratorsecond;

import java.util.ArrayList;
import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

public class WaitressSecond {
	ArrayList<Menu> menus;
	
	public WaitressSecond(ArrayList<Menu> menus) {
		this.menus = menus;
	}
	
	public void printMenu() {
//		Iterator<MenuItem> menuIterator = menus.iterator(); 
//		while(menuIterator.hasNext()) {
//			Menu menu = (Menu)menuIterator.next();
//			printMenu(menu.createIterator());
//		}
		
		for(Menu menu : menus) {
			printMenu(menu.createIterator());
		}
	}
	
	private void printMenu(Iterator<MenuItem> iterator) {
		while(iterator.hasNext()) {
			MenuItem menuItem = (MenuItem) iterator.next(); //注意这里要向下转型 
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}



/*
需求又变更了，我们希望在餐厅菜单中加入一个甜点菜单，但是我们不能直接将甜点菜单赋值给菜单项数组，
类型不同，又要修改了。实际上，我们已经到达了一个复杂级别，如果现在不重新设计，就无法容纳未来增加
的菜单或子菜单等需求。

我们要介绍另一个模式解决这个难题-->组合模式
组合模式:
	允许你将对象组合成树形结构来表现"整体/部分"层次结构。组合能让客户以一致的方式处理个别对象以及对象组合。
也就是说我们能把相同的操作应用在组合和个别对象上。
*/



/*
利用组合设计菜单
我们需要创建一个组件接口来作为菜单和菜单项的共同接口，让我们能够用统一的做法来处理菜单和菜单项。 
类组成:
Waitress女招待类: 将使用菜单组件接口访问菜单和菜单项
MenuComponent接口类: 菜单组件提供了一个接口，让菜单项和菜单共同使用。我们希望能为他们提供默认的实现，
所以在这里定义成抽象类。
MenuComponent方法: getName(), getDescription(), getPrice(), isVegetarian(), print(), add(Component), remove(Component), getChild(int)
菜单项类MenuItem: getName(), getDescription(), getPrice(), isVegetarian(), print()
Menu类: 属性->menuComponents 方法->getName(), getDescription(), print(), add(Component), remove(Component), getChild(int)
*/

//实现菜单组件
/*
MenuComponent对每个方法都提供默认的实现，因为有些方法只对菜单项有意义，而有些只对菜单有意义，默认实现是抛出UnsupportedOperationException
异常。这样，如果菜单项或菜单不支持某个操作，他们就不需做任何事情，直接继承默认实现就可以了
*/
package com.gougoucompany.designpattern.iteratorthird;

public abstract class MenuComponent {
	
	public void add(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	
	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		throw new UnsupportedOperationException();
	}
	
	public String getDescription() {
		throw new UnsupportedOperationException();
	}
	
	public double getPrice() {
		throw new UnsupportedOperationException();
	}
	
	public boolean isVegetarian() {
		throw new UnsupportedOperationException();
	}
	
	public void print() {
		throw new UnsupportedOperationException();
	}
}

//菜单项类，这是组合类图里的叶子节点，它实现组合内元素的行为

package com.gougoucompany.designpattern.iteratorthird;
public class MenuItem extends MenuComponent{
	String name;
	String description;
	boolean vegetarian;
	double price;
	
	public MenuItem(String name, String description,
			boolean vegetarian, double price) {
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public double getPrice() {
		return price;
	}
	
	public void print() {
		System.out.print("  " +  getName());
		if(isVegetarian()) {
			System.out.print("(v)");
		}
		System.out.println(", " + getPrice());
		System.out.println("   -- " + getDescription());
	}
}

//实现组合菜单，此组合菜单可以持有菜单项或其他菜单

package com.gougoucompany.designpattern.iteratorthird;

import java.util.ArrayList;

public class Menu extends MenuComponent {
	ArrayList<MenuComponent> menuComponents = new ArrayList<>();
	String name;
	String description;
	
	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void add(MenuComponent menuComponent) {
		menuComponents.add(menuComponent);
	}
	
	public void remove(MenuComponent menuComponent) {
		menuComponents.remove(menuComponent);
	}
	
	public MenuComponent getChild(int i) {
		return (MenuComponent)menuComponents.get(i);
	}
	
	//注: 我们没有覆盖getPrice()和getVegetarian()，因为这些方法可能对Menu没有意义，如果调用这些方法，会抛出UnsupportedOperationException异常
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	//打印菜单的名称和描述
	public void print() {
		System.out.print("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("-----------------------");
	}
}

//我们Menu类print()方法必须打印出它包含的一切，可以使用递归的方式来完成。我们修正一下这个print()
public void print() {
		System.out.print("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("-----------------------");
		
		Iterator<MenuComponent> iterator = menuComponents.iterator();
		while(iterator.hasNext()) {
			MenuComponent menuComponent = (MenuComponent)iterator.next();
			menuComponent.print(); //如果碰到菜单项直接打印出菜单项信息，如果不是打印出菜单信息并且继续递归直到叶节点
		}
	}


//Waitress类
package com.gougoucompany.designpattern.iteratorthird;

public class Waitress {
	MenuComponent allMenus;
	
	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
	
	public void printMenu() {
		allMenus.print(); //只需调用最顶层菜单的print()
	}
}


//测试程序
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





















































































