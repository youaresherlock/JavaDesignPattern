package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;
import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//���˵�һ����ͬ�ļ��ڣ��õ�����ȡ��һ���˵���ĵ�����
public interface Menu {
	public Iterator<MenuItem> createIterator();
}
