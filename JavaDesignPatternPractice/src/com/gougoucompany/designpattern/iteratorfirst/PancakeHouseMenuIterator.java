package com.gougoucompany.designpattern.iteratorfirst;

import java.util.ArrayList;

public class PancakeHouseMenuIterator implements Iterator{
	ArrayList<MenuItem> menuItems;
	int position = 0; //��¼������λ��
	
	public PancakeHouseMenuIterator(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public boolean hasNext() {
		// ������ʵ�����ж�Ԫ���Ƿ���MenuIteme������ת�͵Ķ�����Ϊ��������������
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
