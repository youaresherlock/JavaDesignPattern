package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//���ǵķ���Ա�õ��˵�����Ҫ֪���˵��Ĵ洢��ʽ����ͨ���õ��˵������������������еĶ���
public class Waitress{
	Menu pancakeHouseMenu;
	Menu dinerMenu;

	//������˵���ĳɽӿ�
	public Waitress(Menu pancakeHouseMenu, Menu dinerMenu){
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}

	public void printMenu(){
		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator<MenuItem> dinerMenuIterator = dinerMenu.createIterator();
		System.out.println("MENU\n----------\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerMenuIterator);
	}

	private void printMenu(Iterator<MenuItem> iterator){
		while(iterator.hasNext()){
			MenuItem menuItem = (MenuItem) iterator.next(); //ע������Ҫ����ת�� 
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}