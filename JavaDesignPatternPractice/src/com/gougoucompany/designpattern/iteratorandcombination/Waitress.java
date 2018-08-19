package com.gougoucompany.designpattern.iteratorandcombination;

//我们的服务员拿到菜单不需要知道菜单的存储方式可以通过拿到菜单迭代器来遍历集合中的对象
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
			MenuItem menuItem = (MenuItem) iterator.next(); //注意这里要向下转型 
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}