//package com.gougoucompany.designpattern.iteratorandcombination;
//
//import java.util.ArrayList;
//
////���Ҫ�������������ṩ�����в˵���ֻ�����ʵ�֣����������ڸ��Լ��ϵĴ洢���ݽṹ�������߱���֪���˵���Ĵ洢��ʽ�������Ϸ�װ��˼��
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
