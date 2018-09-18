/*
* @Author: Clarence
* @Date:   2018-09-18 15:47:55
* @Last Modified by:   Clarence
* @Last Modified time: 2018-09-18 20:41:15
*/
/*
之前关于软件开发中的大多数设计模式我们都基本想过了，但是还有一些比较不常用的没有讲过
下面我们来讲解Java监听者模式
Java监听者模式，以与回调函数和观察者模式之间的关系

什么是回调函数?
所谓的回调，用于回调的函数。回调函数只是一个功能片段，由用户按照回调函数调用约定来实现
的一个函数。
*/


//回调接口(ICallBack)
package com.gougoucompany.designpattern.monitor;

/*
这里有两个实体: 回调抽象接口、调用者
回调接口(ICallBack)
*/
public interface ICallBack {
	public void callBack();
}


//回调者(用于调用回调函数的类)
package com.gougoucompany.designpattern.monitor;

//回调者(用于调用回调函数的类)
public class Caller {
	public void call(ICallBack callBack) {
		System.out.println("Start...");
		callBack.callBack();
		System.out.println("End...");
	}
}

//回调测试类
package com.gougoucompany.designpattern.monitor;

//回调测试
package com.gougoucompany.designpattern.callback;

//回调测试
public class CallTestDrive {
	public static void main(String args[]) {
		Caller call = new Caller();
		//第一种写法
//		call.call(new ICallBack() {
//			
//			@Override
//			public void callBack() {
//				// TODO Auto-generated method stub
//				System.out.println("回调函数调用成功!");
//			}
//		});
		
		//第二种写法
		ICallBack callBack = new ICallBack() {
			@Override
			public void callBack() {
				// TODO Auto-generated method stub
				System.out.println("回调函数回调成功!");
			}
		};
		call.call(callBack);
		
		/*
		 第三种写法，实现这个ICallBack接口类
		 class CallBackC implements ICallBack{
		 	public void callBack(){
		 	System.out.println("回调函数回调成功!");
		 	}
		 }
		 call.call(new CallBackC());
		 */
	}
}

/*
这两种用法我就不细讲了，总之就是匿名内部类和具体类实例化，没什么要讲的
结合我们之前讲过的观察者模式可以看得出: 回调函数应该属于观察者模式的一种，目的是为了替代轮询机制，将组件之间的耦合性降低，。
观察者模式里面目标类维护了所有观察者的引用，而回调里面只是维护了一个引用。
什么是事件监听器?
监听器将监听自己感兴趣的事件，一旦该事件被触发或改变，立即得到通知，做出响应。
java的事件监听机制可概括为3点:
1.java的事件监听机制涉及到事件源、事件监听器、事件对象三个组件，监听器一般是接口，用来约定调用的方式。
2.当事件源对象上发生操作时，它会将调用事件监听器的一个方法，并在调用该方法时传递事件对象过去。
3.事件监听器实现类通常是由开发人员编写，开发人员通过事件对象拿到事件源，从而对事件源上的操作进行处理。
*/
//事件监听器接口
package com.gougoucompany.designpattern.monitor;

import java.util.EventListener;
import java.util.EventObject;
// EventListener: A tagging interface that all event listener interfaces must extend.
//所有事件监听器接口都要继承这个标签接口
public interface MonitorListener extends EventListener{
	/*
	The root class from which all event state objects shall be derived.
	All Events are constructed with a reference to the object, the "source"
	, that is logically deemed to be the object upon which the Event in
	question initially occurred upon.
	*/
	public void handleEvent(PrintEvent event);
}


//事件对象
package com.gougoucompany.designpattern.monitor;

import java.util.EventObject;
//事件对象
public class PrintEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	public PrintEvent(Object source) {
		super(source);
	}
	
	public void doEvent() {
		//getSource The object on which the Event initially occurred.
		System.out.println("通知一个事件源 source: " + this.getSource());
	}

}


//事件源
package com.gougoucompany.designpattern.monitor;

import java.util.Iterator;
import java.util.Vector;

/*
 事件源
 事件源是是事件对象的入口，包含监听器的注册、撤销、通知
 */
public class EventSource {
	//监听器列表，如果监听事件源的事件，注册监听器可以加入此列表
	private Vector<MonitorListener> listenerList = new Vector<>();
	
	//注册监听器
	public void addListener(MonitorListener eventListener) {
		listenerList.add(eventListener);
	}
	
	//删除监听器
	public void removeListener(MonitorListener eventListener) {
		int i = listenerList.indexOf(eventListener);
		if(i >= 0) {
			listenerList.remove(eventListener);
		}
	}
	
	//接受外部事件，通知所有的监听器
	public void notifyListenerEvents(PrintEvent event) {
//		Iterator<MonitorListener> iterator = listenerList.iterator();
//		while(iterator.hasNext()) {
//			MonitorListener monitorListener = (MonitorListener)iterator.next();
//			monitorListener.handleEvent(event);
//		}
		for(MonitorListener moniterListener : listenerList) {
			moniterListener.handleEvent(event);
		}
	}
}


//测试执行
package com.gougoucompany.designpattern.monitor;

import java.util.EventObject;

//测试类
public class MonitorTestDrive {
	public static void main(String[] args) {
		EventSource eventSource = new EventSource();
		eventSource.addListener(new MonitorListener() {
			
			@Override
			public void handleEvent(PrintEvent event) {
				// TODO Auto-generated method stub
				event.doEvent();
				if(event.getSource().equals("openWindows")) {
					System.out.println("doOpen");
				}
				if(event.getSource().equals("closeWindows")){
					System.out.println("doClose");
				}
			}
		});
		
		/*
		 * 传入openWindows事件，通知所有的事件监听器
		 * 对open事件感兴趣的listener将会执行
		 */
		eventSource.notifyListenerEvents(new PrintEvent("openWindows"));
	}
}


/*
result:
通知一个事件源 source: openWindows
doOpen
这就是事件监听模式
回调接口类: MonitorListener
回调函数接口: handleEvent
通过回调模型，EventSource事件源便可回调具体监听器动作
*/

//有了了解后，这里还可以做一些变动。对特定的事件提供特定的关注方法和事件触发

public class EventSource {
     ...

	public void addCloseWindowListener(MonitorListener eventListener) {
		System.out.println("关注关闭窗口事件");
		listenerList.add(eventListener);
	}
	
	public void doCloseWindows() {
		this.notifyListenerEvents(new PrintEvent("closeWindows"));
	}
    ...
}

//更改测试类
package com.gougoucompany.designpattern.monitor;

//测试类
public class MonitorTestDrive {
	public static void main(String[] args) {
		EventSource eventSource = new EventSource();
		eventSource.addListener(new MonitorListener() {
			
			@Override
			public void handleEvent(PrintEvent event) {
				// TODO Auto-generated method stub
				event.doEvent();
				//根据不同的事件源执行不同的逻辑代码
				if(event.getSource().equals("openWindows")) {
					System.out.println("doOpen");
				}
				if(event.getSource().equals("closeWindows")){
					System.out.println("doClose");
				}
			}
		});
		
		/*
		 * 传入openWindows事件，通知所有的事件监听器
		 * 对open事件感兴趣的listener将会执行
		 */
		eventSource.notifyListenerEvents(new PrintEvent("openWindows"));
	
		// 有了了解后，这里还可以做一些变动。对特定的事件提供特定的关注方法和事件触发
		//关注关闭事件，实现回调接口
		EventSource windows = new EventSource();
		windows.addCloseWindowListener(new MonitorListener() {
			
			@Override
			public void handleEvent(PrintEvent event) {
				// TODO Auto-generated method stub
				event.doEvent();
				if(event.getSource().equals("closeWindows")) {
					System.out.println("通过addCloseWindowListener来关注关闭窗口事件，并执行成功"
							+ "closeWindows");
				}
			}
		});
		
		//窗口关闭动作,现在是不是类似按钮注册监听器，然后点击触发点击事件，执行监听器中对应事件的动作
		windows.doCloseWindows();
	}
}

/*
result:
通知一个事件源 source: openWindows
doOpen
关注关闭窗口事件
通知一个事件源 source: closeWindows
通过onCloseWindows来关注关闭窗口事件，并执行成功closeWindows

我们还不得不将一下重新提一下观察者模式
观察者模式原理和监听器一样的，使用的关键在搞清楚什么是观察者、什么是被观察者
	观察者(Observer)相当于事件监听器。有个微博模型比较理解(典型的订阅和发布模式)，A用户关注B用户，则A是B的观察者，
	B是一个被观察者，一旦B发表任何言论，A便可以获得
	被观察者(Observable)相当于事件源和事件，执行事件源通知逻辑时，将会回调Observer的回调方法update()

记得之前的我的一片博客是Python sax解析xml，基本原理也大致相同，都是事件驱动模型。
再延伸下，消息中间件是什么一个模型？ 将生产者+服务中心（事件源）和消费者（监听器）通过消息队列解耦掉.

消息这相当于具体的事件对象，只是存储在一个队列里（有消峰填谷的作用），服务中心回调消费者接口通过拉或取的模型响应。 想必基于这个模型，实现一个简单的消息中间件也是可以的。
还比如gava ListenableFuture，采用监听器模式就解决了future.get()一直阻塞等待返回结果的问题。
我接触过一点kafka，和ActiveMQ类似的中间件，但是只能说了解而已，以后我可能会写一些文章来分享。
*/
















