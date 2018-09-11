package com.gougoucompany.designpattern.compoundfirst;

/*
工厂模式:
我们看到鸭子对象必须被装饰者装饰之后才可以获得计数的行为，我们现在将创建鸭子和装饰鸭子两部分包装起来
需要保证每个鸭子都是被装饰者装饰过的，因此我们要创建一个工厂，创建装饰过的鸭子,这个工厂要生产各种不同类型
的鸭子的产品家族，因此要用抽象工厂模式
*/
//抽象工厂
public abstract class AbstractDuckFactory {
	public abstract Quackable createMallardDuck();
	public abstract Quackable createRedheadDuck();
	public abstract Quackable createDuckCall();
	public abstract Quackable createRubberDuck();
}
