package com.gougoucompany.designpattern.iteratorthird;

public class Waitress {
	MenuComponent allMenus;
	
	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
	
	public void printMenu() {
		allMenus.print(); //只需调用最顶层菜单的print()
	}
}
