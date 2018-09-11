package com.gougoucompany.designpattern.compoundfirst;

//观察者继承Observer接口
public class Quackologist implements Observer {

	@Override
	public void update(QuackObservable duck) {
		System.out.println("Quackologist: " + duck.getClass().getSimpleName() + " just quacked.");
	}
}
