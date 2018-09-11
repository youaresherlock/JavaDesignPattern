package com.gougoucompany.designpattern.compoundfirst;

public class RubberDuck implements Quackable{
	Observable observable;
	
	public RubberDuck() {
		observable = new Observable(this);
	}
	
	public void quack() {
		System.out.println("Squeak");
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}
}













