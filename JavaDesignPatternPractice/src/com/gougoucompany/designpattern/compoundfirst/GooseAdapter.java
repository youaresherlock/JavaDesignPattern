package com.gougoucompany.designpattern.compoundfirst;

//���Ѽ�Ӷ���У�����Ϊ����ģ������ʹ�ö죬����ʹ��������ģʽ�����������Ѽ��
//��������ʵ��Ŀ��ӿ�
public class GooseAdapter implements Quackable {
	Goose goose;
	
	//������Ҫ����Ҫ����Ķ����
	public GooseAdapter(Goose goose) {
		this.goose = goose;
	}

	//������quack()ʱ����ί�е����honk()����
	@Override
	public void quack() {
		goose.honk();
	}

}
