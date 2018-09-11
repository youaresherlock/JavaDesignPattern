package com.gougoucompany.designpattern.compoundfirst;

/*
 * Observable就是被观察的对象，需要注册删除和通知观察者的方法，这里简单起见我们只加入注册和通知观察者方法
 * QuackObservable是一个接口，任何想被观察的Quackable都必须实现QuackObservable接口
 */
public interface QuackObservable {
	/**
	 * 具有注册观察者的方法，任何实现了Observer接口的观察者都可以监听呱呱叫
	 * <p>Title: registerObserver</p>  
	 * <p>Description: </p>  
	 * @param observer
	 */
	public void registerObserver(Observer observer); 
	public void notifyObservers(); //通知观察者的方法
	
}
