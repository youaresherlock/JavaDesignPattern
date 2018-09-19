/*
(1) 亨元模式
	亨元模式(Flyweight Pattern)主要用于减少创建对象的数量，以减少内存占用和提高性能。这种类型的设计
属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
	在亨元模式中可以共享的相同内容成为内部状态(Intrinsic State),而那些需要外部环境来设置的不能共享的
内容成为外部状态(Extrinsic State),其中外部状态和内部状态是相互独立的，外部状态的变化不会引起内部
状态的变化。由于区分了内部状态和外部状态，因此可以通过设置不同的外部状态使得相同的对象可以具有一些
不同的特征，而相同的内部状态是可以共享的。也就是说亨元模式就是把一个对象的状态分成内部状态和外部状态，
内部状态即是不变的，外部状态是变化的；然后通过共享不变的部分，达到减少对象数量并节约内存的目的。
	在亨元模式中通常会出现工厂模式，需要创建一个亨元工厂来负责维护一个亨元池(Flyweight Pool)(用于
存储具有相同内部状态的亨元对象)。在亨元模式中，共享的亨元对象的内部状态，外部状态需要通过环境来设置。在
实际使用中，能够共享的内部状态是有限的，因此亨元对象一般都设计为较小的对象，它所包含的内部状态较少，这种对象
也成为细粒度对象。亨元模式的目的就是使用共享技术来实现大量细粒度对象的复用。
	由于亨元模式要求能够共享的对象必须是细粒度对象，因此它又称为轻量级模式，它是一种对象结构型模式。

(2) 模式所涉及的角色
Flyweight: 亨元接口，通过这个接口传入外部状态并作用于外部状态
ConcreteFlyweight: 具体的亨元实现对象，必须是可共享的，需要封装亨元对象的内部状态
UnsharedConcreteFlyWeight: 非共享的亨元实现对象，并不是所有的亨元对象都可以共享，非
	共享的亨元对象通常是亨元对象的组合对象。
FlyweightFactory: 亨元工厂，主要用来创建并管理共享的亨元对象，并对外提供访问共享亨元的接口。

(3) 亨元模式尝试重用现有的同类对象，如果未找到匹配的对象，则创建新对象。我们将通过创建5个对象来画出20
个分布不同位置的圆来演示这种模式。由于只有5种可用的颜色，所以color属性被用来检查现有的Circle对象
意图: 
	运用共享技术有效地支持大量细粒度的对象
主要解决: 
	在有大量对象时，有可能会造成内存溢出，我们把其中共同的部分抽象出来，如果有相同的业务请求，直接
返回在内存中已有的对象，避免重新创建。
何时使用: 
	1.系统中有大量对象。 2.这些对象消耗大量内存 3.这些对象的状态大部分可以外部化 4.这些对象可以按照
内蕴状态(内部状态，是存储在亨元对象内部的，并且使不会随环境的改变而有所不同)分为很多组，当把外蕴对象从对象
中剔除出来，每一组对象都可以用一个对象来替代。 5.系统不依赖于这些对象身份，这些对象是不可分辨的
如何解决: 用唯一标识码判断，如果在内存中有，则返回这个唯一标识码所标识的对象。
关键代码: 用HashMap存储这些对象
应用实例:
	1.Java中的String，如果有则返回，如果没有则创建一个字符串保存在字符串缓存池里面(Java字符串常量池，
python有小整数常量池和字符串常量池)，下个月可能会看本Java虚拟机的书，会总结这方面的东西
	2.数据库的数据池。 池这个东西缓冲池、常量池、数据库连接池、线程池、进程池、会话池，到处可见为了性能减少对象创建和
	销毁的优化方法。

优点: 大大减少对象的创建，降低系统的内存，使效率提高。
缺点: 提高了系统的复杂度，需要分离出外部状态和内部状态，而且外部状态具有固有化的性质，不应该随着内部状态的变化而变化，
	否则会造成系统的混乱。
使用场景: 1.系统有大量相似对象 2.需要缓冲池的场景
注意事项: 1.注意划分外部状态和内部状态，否则可能会引起线程安全问题
		 2.这些类必须有一个工厂对象加以控制。

实现
	我们将创建一个Shape接口和实现了Shape接口的实体类Circle.下一步是定义工厂类ShapeFactory
ShapeFactory有一个Circle的HashMap,其中键名为Circle对象的颜色。无论何时接受到请求，都会创建一个特定
颜色的圆。ShapeFactory检查它的HashMap中的circle对象，如果找到Circle对象，则返回该对象，否则将创建一个存储
在hashmap中以备后续使用的新对象，并把该的对象返回到客户端。
FlyWeightPatternDemo,我们的演示类使用ShapeFactory来获取Shape对象。它将向ShapeFactory传递信息(red/green/blue/black/
white),以便获取它所需对象的颜色。
*/

//步骤1 创建一个接口
package com.gougoucompany.designpattern.flyweight;

//创建一个接口
public interface Shape {
	void draw();
}

//步骤2 创建实现接口的实体类
package com.gougoucompany.designpattern.flyweight;

public class Circle implements Shape {
	private String color;
	private int x;
	private int y;
	private int radius;
	
	public Circle(String color) {
		this.color = color;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Cicle: Draw() [Color : " + color
				+ ", x: " + x + ", y: " + y + ", radius: " + radius);
	}

}


//步骤3 创建一个工厂，生成基于给定信息的实体类对象
package com.gougoucompany.designpattern.flyweight;

import java.util.HashMap;

public class ShapeFactory {
	//HashTable是线程安全的，其实现方式是在对应的方法上加上synchronized关键字，效率不高
	private static final HashMap<String, Shape> circleMap = 
			new HashMap<>();
	
	public static Shape getCircle(String color) {
		/*
		 Returns the value to which the specified key 
		 is mapped, or null if this map contains no mapping
		 for the key.
		 */
		Circle circle = (Circle)circleMap.get(color);
		
		//如果没有缓存在，则重新创建，这个和安卓开发的RecyclerView缓存类似
		if(circle == null) {
			circle = new Circle(color);
			circleMap.put(color, circle);
			System.out.println("Creating circle of color : " + color);
		}
		
		return circle;
	}
}


//步骤4 使用该工厂，通过传递颜色信息来获取实体类的对象
package com.gougoucompany.designpattern.flyweight;

public class FlyweightPatternDemo {
	private static final String colors[] =
		{"Red", "Green", "Blue", "White", "Black"};
	
	public static void main(String args[]) {
		for(int i = 0; i < 20; ++i) {
			Circle circle = 
					(Circle)ShapeFactory.getCircle(getRandomColor());
			circle.setX(getRandomX());
			circle.setY(getRandomY());
			circle.setRadius(100);
			circle.draw();
		}
	}
	
	private static String getRandomColor() {
		return colors[(int)(Math.random() * colors.length)];
	}
	
	private static int getRandomX() {
		return (int)(Math.random() * 100);
	}
	
	private static int getRandomY() {
		return (int)(Math.random() * 100);
	}

}


//测试结果
/*
result:
Creating circle of color : White
Cicle: Draw() [Color : White, x: 56, y: 56, radius: 100
Creating circle of color : Green
Cicle: Draw() [Color : Green, x: 85, y: 5, radius: 100
Creating circle of color : Red
Cicle: Draw() [Color : Red, x: 92, y: 95, radius: 100
Cicle: Draw() [Color : Red, x: 72, y: 88, radius: 100
Cicle: Draw() [Color : Green, x: 61, y: 48, radius: 100
Cicle: Draw() [Color : Green, x: 26, y: 85, radius: 100
Cicle: Draw() [Color : Green, x: 39, y: 31, radius: 100
Cicle: Draw() [Color : Green, x: 53, y: 77, radius: 100
Creating circle of color : Black
Cicle: Draw() [Color : Black, x: 81, y: 15, radius: 100
Cicle: Draw() [Color : Green, x: 41, y: 70, radius: 100
Creating circle of color : Blue
Cicle: Draw() [Color : Blue, x: 87, y: 36, radius: 100
Cicle: Draw() [Color : Blue, x: 72, y: 80, radius: 100
Cicle: Draw() [Color : White, x: 61, y: 28, radius: 100
Cicle: Draw() [Color : Red, x: 97, y: 20, radius: 100
Cicle: Draw() [Color : White, x: 16, y: 99, radius: 100
Cicle: Draw() [Color : Green, x: 91, y: 35, radius: 100
Cicle: Draw() [Color : Blue, x: 66, y: 64, radius: 100
Cicle: Draw() [Color : White, x: 3, y: 75, radius: 100
Cicle: Draw() [Color : Green, x: 4, y: 82, radius: 100
Cicle: Draw() [Color : Green, x: 58, y: 39, radius: 100

*/














































