package com.gougoucompany.designpattern.iteratorthird;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements Iterator<MenuComponent>{
	Stack<Iterator<MenuComponent>> Stack = new Stack<>();
	
	public CompositeIterator(Iterator<MenuComponent> iterator) {
		Stack.push(iterator); //��Ҫ�����Ķ�����ϵĵ��������룬ѹ���ջ
	}

	@Override
	public boolean hasNext() {
		//�Ƿ�����һ��Ԫ�أ������ջΪ�գ�û����һ��Ԫ��
		if(Stack.empty()) {
			return false;
		} else {
			//���� ��ջ��ȡ���������������û����һ��Ԫ�أ��򵯳���ջ���ݹ����haxNext()
			Iterator<MenuComponent> iterator = (Iterator<MenuComponent>) Stack.peek(); //�鿴��ջ�е�һ����Ԫ��
			if(!iterator.hasNext()) {
				Stack.pop();
				return hasNext();
			} else {
				return true; //�����ʾ������һ��Ԫ�أ�����true
			}
		}
	}

	@Override
	public MenuComponent next() {
		//�ͻ�Ҫȡ��һ��Ԫ�ص�ʱ������hasNext()ȷ���Ƿ�����һ��
		if(hasNext()) {
			Iterator<MenuComponent> iterator = (Iterator<MenuComponent>) Stack.peek();
			MenuComponent component = (MenuComponent) iterator.next();
			if (component instanceof Menu) {
				//���Ԫ����һ���˵���������������һ����Ҫ����������������ϣ����е��Ӽ��ϵĵ�������ѹ���ջ��
				Stack.push(component.createIterator()); //����������������ȱ���
			}
			return (MenuComponent) component;
		} else {
			return null;
		}
		
	}
	
	public void remove() {
		throw new UnsupportedOperationException(); //��֧��ɾ����ֻ����
	}

}
