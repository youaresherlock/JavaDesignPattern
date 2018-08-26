package com.gougoucompany.designpattern.iteratorthird;

import java.util.Iterator;

//public class Waitress {
//	MenuComponent allMenus;
//	
//	public Waitress(MenuComponent allMenus) {
//		this.allMenus = allMenus;
//	}
//	
//	public void printMenu() {
//		allMenus.print(); //ֻ��������˵���print()
//	}
//}

//ʹ������������֮���Waitress
public class Waitress{
	MenuComponent allMenus;

	public Waitress(MenuComponent allMenus){
		this.allMenus = allMenus;
	}

	public void printMenu(){
		allMenus.print();
	}

	public void printVegetarainMenu(){
		//ֻ��ӡ�߲˵Ĳ˵���Ϣ
		Iterator<MenuComponent> iterator = allMenus.createIterator();
		System.out.println("\nVEGETARIAN MENU\n---------")
		while(iterator.hasNext()){
			MenuComponent menuComponent = 
			(MenuComponent) iterator.next();
			//ֻ�в˵�����Ե���isVegetarain()�������˵��ᱨ���쳣���ǲ����ñ�����������
			try{
				if(menuComponent.isVegetarian()){
					menuComponent.print();
				}
			} catch(UnsupportedOperationException e){}
		}
	}
}