package com.gougoucompany.designpattern.iteratorthird;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements Iterator<MenuComponent>{
	Stack<Iterator<MenuComponent>> Stack = new Stack<>();
	
	public CompositeIterator(Iterator<MenuComponent> iterator) {
		Stack.push(iterator); //将要遍历的顶层组合的迭代器传入，压入堆栈
	}

	@Override
	public boolean hasNext() {
		//是否有下一个元素，如果堆栈为空，没有下一个元素
		if(Stack.empty()) {
			return false;
		} else {
			//否则， 从栈顶取出迭代器，如果它没有下一个元素，则弹出堆栈，递归调用haxNext()
			Iterator<MenuComponent> iterator = (Iterator<MenuComponent>) Stack.peek(); //查看堆栈中第一个首元素
			if(!iterator.hasNext()) {
				Stack.pop();
				return hasNext();
			} else {
				return true; //否则表示还有下一个元素，返回true
			}
		}
	}

	@Override
	public MenuComponent next() {
		//客户要取下一个元素的时候，先用hasNext()确认是否还有下一个
		if(hasNext()) {
			Iterator<MenuComponent> iterator = (Iterator<MenuComponent>) Stack.peek();
			MenuComponent component = (MenuComponent) iterator.next();
			if (component instanceof Menu) {
				//如果元素是一个菜单，则我们有了另一个需要被包含进遍历的组合，所有的子集合的迭代器，压入堆栈。
				Stack.push(component.createIterator()); //这种类似于深度优先遍历
			}
			return (MenuComponent) component;
		} else {
			return null;
		}
		
	}
	
	public void remove() {
		throw new UnsupportedOperationException(); //不支持删除，只遍历
	}

}
