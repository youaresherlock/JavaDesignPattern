package com.gougoucompany.designpattern.compoundfirst;

//�۲��߼̳�Observer�ӿ�
public class Quackologist implements Observer {

	@Override
	public void update(QuackObservable duck) {
		System.out.println("Quackologist: " + duck.getClass().getSimpleName() + " just quacked.");
	}
}
