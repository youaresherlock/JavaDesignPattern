package com.gougoucompany.designpattern.iteratorfirst;

//为DinerMenu实现Iterator
public class DinerMenuIterator implements Iterator{
	MenuItem[] items;
	int position = 0; //position记录当前数组遍历的位置

	//构造器需要传入一个菜单项的数组当做参数
	public DinerMenuIterator(MenuItem[] items){ 
		this.items = items;
	}

	//返回数组中下一项，并递增其位置
	public Object next(){
		MenuItem menuItem = items[position];
		position = position + 1;
		return menuItem;
	}

	public boolean hasNext(){
		//检查是否越界以及是否不存在MenuItem对象
		if(position >= items.length || items[position] == null){
			return false;
		} else {
			return true;
		}
	}
}

