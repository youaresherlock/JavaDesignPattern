package com.gougoucompany.designpattern.compoundfirst;


//观察者接口，只有一个抽象方法update(),需要传入正在呱呱叫的对象(QuackObservable)
public interface Observer {
	public void update(QuackObservable duck);
}
