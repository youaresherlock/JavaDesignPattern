package com.gougoucompany.designpattern.mvc;

public class HeartAdapter implements BeatModelInterface {
	HeartModelInterface heart;
	
	public HeartAdapter(HeartModelInterface heart) {
		this.heart = heart;
	}
	
	//我们不知道下面这三个方法将对心脏做些什么，但是看起来很可怕，因此函数体为空
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public void on() {
		// TODO Auto-generated method stub

	}

	@Override
	public void off() {
		// TODO Auto-generated method stub

	}

	//不希望产生任何效果
	@Override
	public void setBPM(int bpm) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getBPM() {
		// TODO Auto-generated method stub
		return heart.getHeartRate();
	}

	@Override
	public void registerObserver(BeatObserver o) {
		// TODO Auto-generated method stub
		heart.registerObserver(o);
	}

	@Override
	public void removeObserver(BeatObserver o) {
		// TODO Auto-generated method stub
		heart.removeObserver(o);
	}

	@Override
	public void registerObserver(BPMObserver o) {
		// TODO Auto-generated method stub
		heart.registerObserver(o);
	}

	@Override
	public void removeObserver(BPMObserver o) {
		// TODO Auto-generated method stub
		heart.removeObserver(o);
	}

}
