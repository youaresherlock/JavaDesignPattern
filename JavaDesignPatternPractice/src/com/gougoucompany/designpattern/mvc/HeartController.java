package com.gougoucompany.designpattern.mvc;

public class HeartController implements ControllerInterface {
	HeartModelInterface model;
	DJView view;
	
	public HeartController(HeartModelInterface model) {
		this.model = model;
		//HeartModel����ֱ�ӽ�����ͼ������Ҫ������������װ�����С�
		view = new DJView(this,  new HeartAdapter(model));
		view.createView();
		view.createControls();
		//��ť������Ҫ������Ϊdisabled
		view.disableStopMenuItem();
		view.disableStartMenuItem();
	}

	//�����������û��ʵ�ʵ����ã���˺�������Ϊ��
	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseBPM() {
		// TODO Auto-generated method stub

	}

	@Override
	public void decreaseBPM() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBPM(int bpm) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
