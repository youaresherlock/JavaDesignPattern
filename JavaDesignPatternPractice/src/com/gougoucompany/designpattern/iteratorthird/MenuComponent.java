package com.gougoucompany.designpattern.iteratorthird;

import java.util.Iterator;

//实现菜单组件
/*
MenuComponent对每个方法都提供默认的实现，因为有些方法只对菜单项有意义，而有些只对菜单有意义，默认实现是抛出UnsupportedOperationException
异常。这样，如果菜单项或菜单不支持某个操作，他们就不需做任何事情，直接继承默认实现就可以了
*/
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
	
	//我们后来加入的迭代器抽象方法，来满足客户的特定需求
	public abstract Iterator<MenuComponent> createIterator();
}

























