package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//为DinerMenu实现Iterator
public class DinerMenuIterator implements Iterator<MenuItem>{
	MenuItem[] items;
	int position = 0; //position记录当前数组遍历的位置

	//构造器需要传入一个菜单项的数组当做参数
	public DinerMenuIterator(MenuItem[] items){ 
		this.items = items;
	}

	@Override
	public boolean hasNext() {
		//检查是否越界以及是否不存在MenuItem对象
				if(position >= items.length || items[position] == null){
					return false;
				} else {
					return true;
				}
	}

	@Override
	public MenuItem next() {
		MenuItem menuItem = items[position];
		position = position + 1;
		return menuItem;
	}
	
	/**
	 * remove允许我们从聚合中删除由next方法返回的最后一项
	 */
	@Override
	public void remove() {
		//如果不需要remove()方法，可以抛出java.lang.UnsupportedOperationException运行时异常
		//RuntimeException运行时异常是不需要捕获的，当异常出现时，虚拟机会处理，常见的有空指针异常
		if(position <= 0) {
			throw new IllegalStateException
				("You can't remove an item until you've done at least one next()");
		}
		if(items[position - 1] != null) {
			//覆盖掉position位置的元素
			for(int i = position -1; i < (items.length - 1); i++) {
				items[i] = items[i + 1];
			}
			items[items.length - 1] = null; //删除元素前移后要覆盖最后一项元素，防止重合
		}
	}
}

