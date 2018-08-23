package com.gougoucompany.designpattern.iteratorthird;

import java.util.ArrayList;
import java.util.Iterator;

public  class Menu extends MenuComponent {
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
//	public void print() {
//		System.out.print("\n" + getName());
//		System.out.println(", " + getDescription());
//		System.out.println("-----------------------");
//	}
	
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

	@Override
	public Iterator<?> createIterator() {
		return null;
		//return new CompositeIterator(menuComponents.iterator());
	}

}





















