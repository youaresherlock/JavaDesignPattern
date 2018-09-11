package com.gougoucompany.designpattern.compoundfirst;

//�˹���������װ�ι���Ѽ�Ӳ�Ʒ  CountingDuckFactory��չ�Գ��󹤳�
public class CountingDuckFactory extends AbstractDuckFactory{

	/**
	 * ÿ�������������ý���������װ���߽�Quackable��װ������ģ��������֪���кβ�ͬ��ֻ֪����
	 * ʵ����Quackable�ӿ�
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
