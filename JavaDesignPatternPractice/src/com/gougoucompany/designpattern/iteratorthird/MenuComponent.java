package com.gougoucompany.designpattern.iteratorthird;

import java.util.Iterator;

//ʵ�ֲ˵����
/*
MenuComponent��ÿ���������ṩĬ�ϵ�ʵ�֣���Ϊ��Щ����ֻ�Բ˵��������壬����Щֻ�Բ˵������壬Ĭ��ʵ�����׳�UnsupportedOperationException
�쳣������������˵����˵���֧��ĳ�����������ǾͲ������κ����飬ֱ�Ӽ̳�Ĭ��ʵ�־Ϳ�����
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
	
	//���Ǻ�������ĵ��������󷽷���������ͻ����ض�����
	public abstract Iterator<MenuComponent> createIterator();
}

























