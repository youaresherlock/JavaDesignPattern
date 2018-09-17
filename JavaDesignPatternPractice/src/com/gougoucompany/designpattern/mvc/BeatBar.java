package com.gougoucompany.designpattern.mvc;

import javax.swing.JProgressBar;

//���������
public class BeatBar extends JProgressBar implements Runnable{
	Thread thread;
	
	public BeatBar() {
		thread = new Thread(this);
		//�������ֵ���̳������ķ���
		setMaximum(100);
		thread.start();
	}

	//�̵߳�run������һ����ѭ�������ʼ�ռ�������������ֵ�仯�������н�����������
	@Override
	public void run() {
		for(;;) {
			int value = getValue();
			System.out.println("��������ֵ��:" + value);
			value = (int)(value * .75); //�����õ�ֵ��ʼ�������ӽ�0
			setValue(value);
			repaint();
			try {
				Thread.sleep(50);//ÿ50�������һ��
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}

}
