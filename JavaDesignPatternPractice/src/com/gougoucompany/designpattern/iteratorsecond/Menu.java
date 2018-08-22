package com.gougoucompany.designpattern.iteratorsecond;

import java.util.Iterator;
import com.gougoucompany.designpattern.iteratorfirst.MenuItem;

//给菜单一个共同的几口，让调用者取得一个菜单项的迭代器
public interface Menu {
	public Iterator<MenuItem> createIterator();
}
