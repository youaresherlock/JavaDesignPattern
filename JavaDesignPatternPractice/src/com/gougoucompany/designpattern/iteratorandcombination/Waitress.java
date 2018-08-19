package com.gougoucompany.designpattern.iteratorandcombination;

//���ǵķ���Ա�õ��˵�����Ҫ֪���˵��Ĵ洢��ʽ����ͨ���õ��˵������������������еĶ���
public class Waitress{
	PancakeHouseMenuWithIterator pancakeHouseMenu;
	DinerMenuWithIterator dinerMenu;

	public Waitress(PancakeHouseMenuWithIterator pancakeHouseMenu, DinerMenuWithIterator dinerMenu){
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}

	public void printMenu(){
		Iterator pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator dinerMenuIterator = dinerMenu.createIterator();
		System.out.println("MENU\n----------\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerMenuIterator);
	}

	private void printMenu(Iterator iterator){
		while(iterator.hasNext()){
			MenuItem menuItem = (MenuItem) iterator.next(); //ע������Ҫ����ת�� 
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}