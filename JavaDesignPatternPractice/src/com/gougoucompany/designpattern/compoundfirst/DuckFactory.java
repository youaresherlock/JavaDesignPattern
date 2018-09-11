package com.gougoucompany.designpattern.compoundfirst;

//此工厂创建没有装饰者的鸭子 DuckFactory扩展抽象工厂
public class DuckFactory extends AbstractDuckFactory {

	/**
	 * 每个方法创建一个产品，一种特定种类的Quackable
	 */
	@Override
	public Quackable createMallardDuck() {
		return new MallardDuck();
	}

	@Override
	public Quackable createRedheadDuck() {
		return new RedheadDuck();
	}

	@Override
	public Quackable createDuckCall() {
		return new DuckCall();
	}

	@Override
	public Quackable createRubberDuck() {
		return new RubberDuck();
	}

}
