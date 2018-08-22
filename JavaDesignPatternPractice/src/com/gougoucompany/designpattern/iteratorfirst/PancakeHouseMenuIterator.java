package com.gougoucompany.designpattern.iteratorfirst;

import java.util.ArrayList;

public class PancakeHouseMenuIterator implements Iterator{
	ArrayList<MenuItem> menuItems;
	int position = 0; //记录遍历的位置
	
	public PancakeHouseMenuIterator(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public boolean hasNext() {
		// 这里其实不用判断元素是否是MenuIteme类向上转型的对象，因为泛型做出了限制
		if(position >= menuItems.size() || !(menuItems.get(position) instanceof MenuItem)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Object next() {
		MenuItem menuItem = menuItems.get(position);
		position += 1;
		return menuItem;
	}

}
