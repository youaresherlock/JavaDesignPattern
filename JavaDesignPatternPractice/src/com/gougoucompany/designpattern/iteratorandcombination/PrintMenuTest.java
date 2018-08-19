//package com.gougoucompany.designpattern.iteratorandcombination;
//
//import java.util.ArrayList;
//
////如果要遍历两个餐厅提供的所有菜单，只能逐个实现，而且依赖于各自集合的存储数据结构，调用者必须知道菜单项的存储方式，不符合封装的思想
//public class PrintMenuTest {
//	
//	static PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
//	static ArrayList<MenuItem> breakfastItems = pancakeHouseMenu.getMenuItems();
//	
//	static DinerMenu dinerMenu = new DinerMenu();
//	static MenuItem[] lunchItems = dinerMenu.getMenuItems();
//	
//	public static void main(String args[]) {
//		printPancakeHouseMenu();
//		printDinerMenu();
//	}
//	
//	public static void printPancakeHouseMenu() {
//		for(int i = 0; i < breakfastItems.size(); i ++) {
//			MenuItem menuItem = (MenuItem)breakfastItems.get(i);
//			System.out.print(menuItem.getName() + " ");
//			System.out.println(menuItem.getPrice() + " ");
//			System.out.println(menuItem.getDescription());
//		}
//	}
//	
//	public static void printDinerMenu() {
//		for(int i = 0; i < lunchItems.length; i ++) {
//			MenuItem menuItem = lunchItems[i];
//			if(menuItem != null) {
//				System.out.print(menuItem.getName() + " ");
//				System.out.println(menuItem.getPrice() + " ");
//				System.out.println(menuItem.getDescription());
//			}
//		}
//	}
//}
