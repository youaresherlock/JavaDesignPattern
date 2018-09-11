package com.gougoucompany.designpattern.compoundfirst;

/*
 * Observable���Ǳ��۲�Ķ�����Ҫע��ɾ����֪ͨ�۲��ߵķ�����������������ֻ����ע���֪ͨ�۲��߷���
 * QuackObservable��һ���ӿڣ��κ��뱻�۲��Quackable������ʵ��QuackObservable�ӿ�
 */
public interface QuackObservable {
	/**
	 * ����ע��۲��ߵķ������κ�ʵ����Observer�ӿڵĹ۲��߶����Լ������ɽ�
	 * <p>Title: registerObserver</p>  
	 * <p>Description: </p>  
	 * @param observer
	 */
	public void registerObserver(Observer observer); 
	public void notifyObservers(); //֪ͨ�۲��ߵķ���
	
}
