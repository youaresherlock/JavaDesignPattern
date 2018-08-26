package com.gougoucompany.designpattern.iteratorthird;

import java.awt.MenuContainer;
import java.util.Iterator;

//菜单项类，这是组合类图里的叶子节点，它实现组合内元素的行为
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

	@Override
	public Iterator<MenuComponent> createIterator() {
		return new NullIterator();
	}
}





















