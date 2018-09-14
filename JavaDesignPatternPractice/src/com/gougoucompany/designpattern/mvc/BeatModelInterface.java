package com.gougoucompany.designpattern.mvc;

/*
模型使用观察者模式，我们需要让对象注册成为观察者并发送出通知
*/
public interface BeatModelInterface {
	
	//下面四个方法，是供控制器调用的，控制器根据用户在界面上的操作而对模型做出适当的处理
	void initialize();
	
	void on();
	
	void off();
	
	void setBPM(int bpm);
	
	//下面这些方法允许视图和控制器取得状态，并变成观察者
	int getBPM();
	
	void registerObserver(BeatObserver o);//注册成为节拍观察者
	
	void removeObserver(BeatObserver o);
	
	void registerObserver(BPMObserver o); //注册成为BPM观察者
	
	void removeObserver(BPMObserver o);
}
































