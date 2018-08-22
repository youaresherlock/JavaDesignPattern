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
