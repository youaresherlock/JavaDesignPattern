package com.gougoucompany.designpattern.mvc;

public class BeatController implements ControllerInterface {

	/*
	控制器在MVC设计中起到桥梁的作用，所以它拥有模型和视图的实例
	*/
	BeatModelInterface model;
	DJView view;
	
	public BeatController(BeatModelInterface model) {
		this.model = model;
		//创建实例并初始化视图和模型
		view = new DJView(this, model);
		view.createView();
		view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
		model.initialize();
	}
	
	@Override
	public void sart() {
		// TODO Auto-generated method stub
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}

	@Override
	public void increaseBPM() {
		// TODO Auto-generated method stub
		int bpm = model.getBPM();
		model.setBPM(bpm + 1);
	}

	@Override
	public void decreaseBPM() {
		// TODO Auto-generated method stub
		int bpm = model.getBPM();
		model.setBPM(bpm - 1);
	}

	@Override
	public void setBPM(int bpm) {
		// TODO Auto-generated method stub
		model.setBPM(bpm);
	}

}
