package com.gougoucompany.designpattern.compoundfirst;

public class MallardDuck implements Quackable{
	Observable observable; 
	
	public MallardDuck() {
		observable = new Observable(this); //构造器中传入被观察者的引用
	}
	
	public void quack() {
		System.out.println("Quack");
		notifyObservers(); //被观察者发生呱呱叫行为就通知所有注册的观察者
	}

	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}

}
