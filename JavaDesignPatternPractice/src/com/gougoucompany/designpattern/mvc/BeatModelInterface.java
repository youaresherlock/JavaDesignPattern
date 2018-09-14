package com.gougoucompany.designpattern.mvc;

/*
ģ��ʹ�ù۲���ģʽ��������Ҫ�ö���ע���Ϊ�۲��߲����ͳ�֪ͨ
*/
public interface BeatModelInterface {
	
	//�����ĸ��������ǹ����������õģ������������û��ڽ����ϵĲ�������ģ�������ʵ��Ĵ���
	void initialize();
	
	void on();
	
	void off();
	
	void setBPM(int bpm);
	
	//������Щ����������ͼ�Ϳ�����ȡ��״̬������ɹ۲���
	int getBPM();
	
	void registerObserver(BeatObserver o);//ע���Ϊ���Ĺ۲���
	
	void removeObserver(BeatObserver o);
	
	void registerObserver(BPMObserver o); //ע���ΪBPM�۲���
	
	void removeObserver(BPMObserver o);
}
































