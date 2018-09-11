package com.gougoucompany.designpattern.compoundfirst;

//鹅和鸭子都会叫，我们为了在模拟器中使用鹅，可以使用适配器模式，将鹅适配成鸭子
//适配器会实现目标接口
public class GooseAdapter implements Quackable {
	Observable observable;
	Goose goose;
	
	//构造器要传入要适配的鹅对象
	public GooseAdapter(Goose goose) {
		this.goose = goose;
		observable = new Observable(this);
	}

	//当调用quack()时，会委托到鹅的honk()方法
	@Override
	public void quack() {
		goose.honk();
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
