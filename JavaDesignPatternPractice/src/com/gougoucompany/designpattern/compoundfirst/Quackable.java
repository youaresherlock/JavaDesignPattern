package com.gougoucompany.designpattern.compoundfirst;

//我们将介绍一个鸭子的程序，会用到各种不同的模式，以便更加深刻理解实际应用
//创建一个Quackable接口，赋予具体类呱呱叫的能力
public interface Quackable extends QuackObservable{
	public void quack(); 
}
