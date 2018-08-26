package com.gougoucompany.designpattern.iteratorthird;

import java.util.Iterator;

public class NullIterator implements Iterator<MenuComponent>{

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	
	//�յ�������֧��remove
	public void remove() {
		throw new UnsupportedOperationException();
	}


	@Override
	public MenuComponent next() {
		// TODO Auto-generated method stub
		return null;
	}
}
