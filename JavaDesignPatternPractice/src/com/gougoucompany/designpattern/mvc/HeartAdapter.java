package com.gougoucompany.designpattern.mvc;

public class HeartAdapter implements BeatModelInterface {
	HeartModelInterface heart;
	
	public HeartAdapter(HeartModelInterface heart) {
		this.heart = heart;
	}
	
	//���ǲ�֪��������������������������Щʲô�����ǿ������ܿ��£���˺�����Ϊ��
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

	//��ϣ�������κ�Ч��
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
