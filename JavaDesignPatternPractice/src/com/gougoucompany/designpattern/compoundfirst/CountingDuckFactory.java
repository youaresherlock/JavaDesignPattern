package com.gougoucompany.designpattern.compoundfirst;

//此工厂创建被装饰过的鸭子产品  CountingDuckFactory扩展自抽象工厂
public class CountingDuckFactory extends AbstractDuckFactory{

	/**
	 * 每个方法都会先用叫声计数器装饰者将Quackable包装起来，模拟器并不知道有何不同，只知道它
	 * 实现了Quackable接口
	 */
	@Override
	public Quackable createMallardDuck() {
		return new QuackCounter(new MallardDuck());
	}

	@Override
	public Quackable createRedheadDuck() {
		return new QuackCounter(new RedheadDuck());
	}

	@Override
	public Quackable createDuckCall() {
		return new QuackCounter(new DuckCall());
	}

	@Override
	public Quackable createRubberDuck() {
		return new QuackCounter(new RubberDuck());
	}

}
