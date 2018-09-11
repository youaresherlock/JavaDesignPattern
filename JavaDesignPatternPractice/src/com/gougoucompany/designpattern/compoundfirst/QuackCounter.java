package com.gougoucompany.designpattern.compoundfirst;

/*��������һȺѼ���У�����Ѽ�����ɽеĴ���,����ڲ��仯Ѽ���������£��������ɽеĴ���
������Ҫ����һ��װ���ߣ�ͨ����Ѽ�Ӱ�װ��װ���߶��󣬸�Ѽ��һЩ�µ���Ϊ(�������ɽеĴ�������Ϊ��������Ҫ�޸�Ѽ�ӵĴ���)
*/
//��Ҫʵ��Ŀ��ӿڣ�QuackCounter��һ��װ����
public class QuackCounter implements Quackable {
	Quackable duck; //��һ��ʵ����������¼��װ�ε����ɽ���
	static int numberOfQuacks; //��̬�����������е����ɽеĴ���
	
	//����װ���ߴ��빹����
	public QuackCounter(Quackable duck) {
		this.duck = duck;
	}

	@Override
	public void quack() {
		duck.quack();
		numberOfQuacks++;
	}
	
	public static int getQuacks() {
		return numberOfQuacks;
	}
}
