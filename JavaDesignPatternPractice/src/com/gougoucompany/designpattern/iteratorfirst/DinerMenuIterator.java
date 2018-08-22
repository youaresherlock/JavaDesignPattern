package com.gougoucompany.designpattern.iteratorfirst;

//ΪDinerMenuʵ��Iterator
public class DinerMenuIterator implements Iterator{
	MenuItem[] items;
	int position = 0; //position��¼��ǰ���������λ��

	//��������Ҫ����һ���˵�������鵱������
	public DinerMenuIterator(MenuItem[] items){ 
		this.items = items;
	}

	//������������һ���������λ��
	public Object next(){
		MenuItem menuItem = items[position];
		position = position + 1;
		return menuItem;
	}

	public boolean hasNext(){
		//����Ƿ�Խ���Լ��Ƿ񲻴���MenuItem����
		if(position >= items.length || items[position] == null){
			return false;
		} else {
			return true;
		}
	}
}

