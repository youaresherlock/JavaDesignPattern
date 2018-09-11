package com.gougoucompany.designpattern.compoundfirst;

/*
����ģʽ:
���ǿ���Ѽ�Ӷ�����뱻װ����װ��֮��ſ��Ի�ü�������Ϊ���������ڽ�����Ѽ�Ӻ�װ��Ѽ�������ְ�װ����
��Ҫ��֤ÿ��Ѽ�Ӷ��Ǳ�װ����װ�ι��ģ��������Ҫ����һ������������װ�ι���Ѽ��,�������Ҫ�������ֲ�ͬ����
��Ѽ�ӵĲ�Ʒ���壬���Ҫ�ó��󹤳�ģʽ
*/
//���󹤳�
public abstract class AbstractDuckFactory {
	public abstract Quackable createMallardDuck();
	public abstract Quackable createRedheadDuck();
	public abstract Quackable createDuckCall();
	public abstract Quackable createRubberDuck();
}
