package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;

import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//ΪDinerMenuʵ��Iterator
public class DinerMenuIterator implements Iterator<MenuItem>{
	MenuItem[] items;
	int position = 0; //position��¼��ǰ���������λ��

	//��������Ҫ����һ���˵�������鵱������
	public DinerMenuIterator(MenuItem[] items){ 
		this.items = items;
	}

	@Override
	public boolean hasNext() {
		//����Ƿ�Խ���Լ��Ƿ񲻴���MenuItem����
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
	 * remove�������ǴӾۺ���ɾ����next�������ص����һ��
	 */
	@Override
	public void remove() {
		//�������Ҫremove()�����������׳�java.lang.UnsupportedOperationException����ʱ�쳣
		//RuntimeException����ʱ�쳣�ǲ���Ҫ����ģ����쳣����ʱ��������ᴦ���������п�ָ���쳣
		if(position <= 0) {
			throw new IllegalStateException
				("You can't remove an item until you've done at least one next()");
		}
		if(items[position - 1] != null) {
			//���ǵ�positionλ�õ�Ԫ��
			for(int i = position -1; i < (items.length - 1); i++) {
				items[i] = items[i + 1];
			}
			items[items.length - 1] = null; //ɾ��Ԫ��ǰ�ƺ�Ҫ�������һ��Ԫ�أ���ֹ�غ�
		}
	}
}

