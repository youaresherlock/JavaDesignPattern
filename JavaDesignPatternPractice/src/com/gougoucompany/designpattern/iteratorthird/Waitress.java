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
//		allMenus.print(); //只需调用最顶层菜单的print()
//	}
//}

//使用组合与迭代器之后的Waitress
public class Waitress{
	MenuComponent allMenus;

	public Waitress(MenuComponent allMenus){
		this.allMenus = allMenus;
	}

	public void printMenu(){
		allMenus.print();
	}

	public void printVegetarainMenu(){
		//只打印蔬菜的菜单信息
		Iterator<MenuComponent> iterator = allMenus.createIterator();
		System.out.println("\nVEGETARIAN MENU\n---------")
		while(iterator.hasNext()){
			MenuComponent menuComponent = 
			(MenuComponent) iterator.next();
			//只有菜单项可以调用isVegetarain()方法，菜单会报出异常我们捕获让遍历继续进行
			try{
				if(menuComponent.isVegetarian()){
					menuComponent.print();
				}
			} catch(UnsupportedOperationException e){}
		}
	}
}