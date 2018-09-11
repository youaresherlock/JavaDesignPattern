package com.gougoucompany.designpattern.compoundfirst;

//�˹�������û��װ���ߵ�Ѽ�� DuckFactory��չ���󹤳�
public class DuckFactory extends AbstractDuckFactory {

	/**
	 * ÿ����������һ����Ʒ��һ���ض������Quackable
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
