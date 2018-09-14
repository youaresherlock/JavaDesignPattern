package com.gougoucompany.designpattern.mvc;
/*
控制器是策略，我们将控制器传入视图的构造器中，
视图可以调用下面所有的方法，实现了ControllerInterface就是一系列算法簇，
这样没有依赖，可以随时替换不同控制器的实现类
*/
public interface ControllerInterface {
	void sart();
	void stop();
	void increaseBPM();
	void decreaseBPM();
	void setBPM(int bpm);
}
