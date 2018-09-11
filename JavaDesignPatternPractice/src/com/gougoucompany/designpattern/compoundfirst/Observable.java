package com.gougoucompany.designpattern.compoundfirst;

import java.util.ArrayList;
import java.util.Iterator;

/*
需要在实现Quackable接口的具体类中实现具体的成为被观察者的抽象方法
但是我们要实现的被观察者的抽象方法都是相同的，因此我们将QuackObservable所有的调用都委托给
Observable辅助类
*/
//Observable辅助类,实现了所有必要的功能，将其插入一个类，就可以让该类将工作委托给Observable

public class Observable implements QuackObservable {
	ArrayList<Observer> observers = new ArrayList<>(); //存储所有的观察者对象
	QuackObservable duck; //被观察者对象
	
	public Observable(QuackObservable duck) {
		this.duck = duck; //需要记录当前是哪个被观察者对象在呱呱叫
	}

	//注册观察者的代码
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	//通知观察者的代码
	@Override
	public void notifyObservers() {
		Iterator<Observer> iterator = observers.iterator();
		while(iterator.hasNext()) {
			Observer observer = iterator.next();
			observer.update(duck);
		}
	
	}
}







