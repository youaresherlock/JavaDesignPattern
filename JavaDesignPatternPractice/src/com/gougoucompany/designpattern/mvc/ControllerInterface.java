package com.gougoucompany.designpattern.mvc;
/*
�������ǲ��ԣ����ǽ�������������ͼ�Ĺ������У�
��ͼ���Ե����������еķ�����ʵ����ControllerInterface����һϵ���㷨�أ�
����û��������������ʱ�滻��ͬ��������ʵ����
*/
public interface ControllerInterface {
	void sart();
	void stop();
	void increaseBPM();
	void decreaseBPM();
	void setBPM(int bpm);
}
