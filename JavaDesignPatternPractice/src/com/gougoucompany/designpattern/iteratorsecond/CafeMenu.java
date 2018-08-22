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
