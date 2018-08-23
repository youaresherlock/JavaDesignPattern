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
	
	//ע: ����û�и���getPrice()��getVegetarian()����Ϊ��Щ�������ܶ�Menuû�����壬���������Щ���������׳�UnsupportedOperationException�쳣
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	//��ӡ�˵������ƺ�����
	public void print() {
		System.out.print("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("-----------------------");
	}
}





















