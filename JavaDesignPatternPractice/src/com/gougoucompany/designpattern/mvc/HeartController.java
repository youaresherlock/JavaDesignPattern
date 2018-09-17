package com.gougoucompany.designpattern.mvc;

public class HeartController implements ControllerInterface {
	HeartModelInterface model;
	DJView view;
	
	public HeartController(HeartModelInterface model) {
		this.model = model;
		//HeartModel不能直接交给视图，必须要先用适配器包装过才行。
		view = new DJView(this,  new HeartAdapter(model));
		view.createView();
		view.createControls();
		//按钮都不需要，设置为disabled
		view.disableStopMenuItem();
		view.disableStartMenuItem();
	}

	//下面五个方法没有实际的作用，因此函数体中为空
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
