package com.gougoucompany.designpattern.compoundfirst;

/*
 * Observable���Ǳ��۲�Ķ�����Ҫע��ɾ����֪ͨ�۲��ߵķ�����������������ֻ����ע���֪ͨ�۲��߷���
 * QuackObservable��һ���ӿڣ��κ��뱻�۲��Quackable������ʵ��QuackObservable�ӿ�
 */
public interface QuackObservable {
	public void registerObserver(Observer observer);
	public void notifyObservers();
	
}
