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