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





















