package com.gougoucomany.designpattern.dynamicproxy;

/*
�������ǽ�ʹ��Java API��java.lang.reflect���еĴ���֧�֣�������ʱ��̬�Ĵ���һ��������(����Ȥ�Ŀ����о�һ��Java����)
ʵ��һ�������ӿڣ����������ĵ���ת��������ָ�����ࡣ����ʵ�ʵĴ������������е�ʱ�򴴽��ģ�������Java������֮Ϊ��̬����
���Ǳ�����һ��PersonBean�ӿ��࣬�������û��߻��һ���˵���Ϣ�����ڲ�ͬ���˿���������õķ�����ͬ�������Ҫ��̬������������
��������:
1.��������InvokeHandler
	���ṩ�������õ�ʱ�򣬻ᴥ��Handler�е�invoke�������Դ�������Ȩ������
2.д���봴����̬����
3.�����ʵ��Ĵ�������װPersonBean����
*/
public interface PersonBean {
	String getName();
	
	String getGender();
	
	String getInterests();
	
	int getHotOrNotRating();
	
	void setName(String name);
	
	void setGender(String gender);
	
	void setInterests(String interests);
	
	void setHotOrNotRating(int rating);
}




















