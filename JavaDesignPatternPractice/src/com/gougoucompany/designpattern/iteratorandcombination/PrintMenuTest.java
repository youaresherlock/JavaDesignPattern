package com.gougoucompany.designpattern.iteratorandcombination;

import java.util.ArrayList;

public class PrintMenuTest {
	
	static PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
	static ArrayList breakfastItems = pancakeHouseMenu.getMenuItems();
	
	static DinerMenu dinerMenu = new DinerMenu();
	static MenuItem[] lunchItems = dinerMenu.getMenuItems();
	
	public static void main(String args[]) {
		printPancakeHouseMenu();
		printDinerMenu();
	}
	
	public static void printPancakeHouseMenu() {
		for(int i = 0; i < breakfastItems.size(); i ++) {
			MenuItem menuItem = (MenuItem)breakfastItems.get(i);
			System.out.print(menuItem.getName() + " ");
			System.out.println(menuItem.getPrice() + " ");
			System.out.println(menuItem.getDescription());
		}
	}
	
	public static void printDinerMenu() {
		for(int i = 0; i < lunchItems.length; i ++) {
			MenuItem menuItem = lunchItems[i];
			if(menuItem != null) {
				System.out.print(menuItem.getName() + " ");
				System.out.println(menuItem.getPrice() + " ");
				System.out.println(menuItem.getDescription());
			}
		}
	}
}
