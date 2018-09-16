package com.gougoucompany.designpattern.mvc;

import javax.swing.JProgressBar;

//脉动柱组件
public class BeatBar extends JProgressBar implements Runnable{
	private static final long serialVersionUID = 2L;
	Thread thread;
	JProgressBar progressBar;
	
	public BeatBar() {
		thread = new Thread(this);
		//设置最大值，继承下来的方法
		setMaximum(100);
		thread.start();
	}

	//线程的run方法是一个死循环，因此始终监听脉动柱的数值变化，来进行进度条的设置
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;;) {
			int value = getValue();
			System.out.println("进度条的值是:" + value);
			value = (int)(value * .75); //从设置的值开始缩减到接近0
			setValue(value);
			repaint();
			try {
				Thread.sleep(50);//每50毫秒更新一次
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}

}
