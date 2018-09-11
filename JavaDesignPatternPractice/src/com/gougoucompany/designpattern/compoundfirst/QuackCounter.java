package com.gougoucompany.designpattern.compoundfirst;

/*我们想在一群鸭子中，计算鸭子呱呱叫的次数,如何在不变化鸭子类的情况下，计算呱呱叫的次数
我们需要创建一个装饰者，通过把鸭子包装进装饰者对象，给鸭子一些新的行为(计算呱呱叫的次数的行为，而不需要修改鸭子的代码)
*/
//需要实现目标接口，QuackCounter是一个装饰者
public class QuackCounter implements Quackable {
	Quackable duck; //用一个实例变量来记录被装饰的呱呱叫者
	static int numberOfQuacks; //静态变量跟踪所有的呱呱叫的次数
	
	//将被装饰者传入构造器
	public QuackCounter(Quackable duck) {
		this.duck = duck;
	}

	@Override
	public void quack() {
		duck.quack(); //当调用quack()方法，鸭子鸣叫，因为鸭子具体类已经在quack()中调用了通知观察者的方法，因此不需要在此方法中添加
		numberOfQuacks++;
	}
	
	public static int getQuacks() {
		return numberOfQuacks;
	}

	/**
	 * 被装饰者装饰的鸭子也实现了Quackable接口，所以也是被观察者QuackObservable
	 */
	@Override
	public void registerObserver(Observer observer) {
		duck.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		duck.notifyObservers();
	}
}
