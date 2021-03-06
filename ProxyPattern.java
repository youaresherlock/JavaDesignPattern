/*
* @Author: Clarence
* @Date:   2018-09-01 21:43:05
* @Last Modified by:   Clarence
* @Last Modified time: 2018-09-09 21:31:36
*/

/*

如果需要对服务的访问进行控制，代理就是控制和管理访问
在上次的状态模式中的代码进行修改
我们要可以给用户一份糖果机报告(糖果机糖果数量，糖果机状态，糖果机位置)
*/
public class GumballMachine{
	//其他实例变量
	String location;

	public GumballMachine(String location, int count){
		//构造器内其他的代码
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	//其他方法
}

//我们创建另一个类，GumballMonitor糖果监听器，以便取得及其的位置，糖果的库存量以及当前的机器状态，并打印出报告(实际开发生成日志如stdout,txt文件等)
public class GumballMonitor{
	GumballMachine gumballMachine;

	public GumballMonitor(GumballMachine machine){
		this.gumballMachine = machine;
	}

	public void report(){
		System.out.println("Gumball Machine: " + machine.getLocation())	
		System.out.pritnln("Current inventory: " + machine.getCount() + " gumballs")
		System.out.println("Current state: " + machine.getState());
	}
}

//测试生成报告33
package com.gougoucompany.designpattern.proxyfirst;

public class GumballMachineTestDrive {
	public static void main(String[] args) {
//		int count = 0;
//		
//		if(args.length < 2) {
//			System.out.println("GumballMachine <name> <inventory>");
//			System.exit(1);
//		}
//		
//		count = Integer.parseInt(args[1]);
		GumballMachine gumballMachine = new GumballMachine("hello",  10);
		
		GumballMonitor monitor = new GumballMonitor(gumballMachine);
		
		monitor.report();
	}
}

/*
之前的糖果机和监控器在同一个JVM上面运行，现在需求发生了变化，我们需要远程监控糖果机，然后生成报告，
我们需要用到代理，代理就像是糖果机对象，其实幕后是它利用网络和一个远程的真正糖果机进行沟通。
必须确定糖果机能够通过网络接受请求并提供服务,也需要让监视器有办法取得代理对象的引用，客户对象监视器所做的就像是在
做远程方法调用，但其实只是调用本地堆中的"代理"对象上的方法，再由代理处理所有网络通信的底层细节。
RMI: 远程方法调用(Remote method invoker)
RMI将客户辅助对象称为stub,服务辅助对象称为skeleton,客户和服务是通过它们两个进行通信和数据交换的
客户端 
	客户调用客户辅助对象上的方法，仿佛客户辅助对象就是真正的服务，客户服务对象再负责为我们转发这些请求
	客户辅助对象会联系服务器，传入方法调用信息(方法名称，变量等)，然后等待服务器的响应
服务端	
	服务辅助对象从客户辅助对象中接受请求，通过socket连接，将调用的信息解包，然后调用真正服务对象上的真正方法，
	服务器辅助对象从服务中得到返回值，将它打包，然后运回到客户辅助对象(通过网络socket输出流)，客户辅助对象将信息解包，最后将返回值交给客户对象。
虽然调用远程方法就如同调用本地方法一样，但是客户辅助对象会通过网络发送方法调用，所以网络和IO的确是存在的， 可能存在一定的风险，随时抛出异常，客户端必须
意识到风险的存在
制作远程服务
步骤一:
	制作远程接口MyService.java，接口定义了供客户调用的远程方法
	stub和实际的服务器都实现此接口
步骤二:
	制作远程的实现MyServiceImpl.java，真正的服务，它实现远程接口，这是客户真正想要调用方法的对象(本例子是远程糖果机对象)
步骤三:
	利用jdk中的rmic产生stub和skeleton
	这是客户和服务的辅助类，你不需自己创建这些类，甚至连生成他们的代码都不用看，因为当你运行rmic工具时，这都会自动处理
	% rmic MyServiceImpl 会产生两个新类，作为辅助对象，MyServiceImpl_Stub.class MyServiceImpl_Skel.class字节码文件
步骤四:
	启动RMI registry (rmiregistry)
	RMI服务器在同一主机上使用它来讲远程对象绑定到名称，然后，本地和远程主机上的客户机可以查找到远程对象并进行远程方法调用
步骤五:
	开始远程服务
	你必须让远程服务对象开始运行，你的服务实现类会去实例化一个服务对象的实例，比将这个服务注册到RMI registry,注册之后，这个服务就可以供客户使用了
*/

//步骤一制作远程接口
package com.gougoucompany.designpattern.proxyfirst;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
1.Remote是一个标记接口，没有方法
2.声明所有的方法都会抛出RemoteException，用户会了解到这个是有风险的方法
3.确定变量和返回值是属于基本数据类型或者可序列化类型。自己的类要实现Serializable接口,因为要服务端要序列化通过网络传输给客户
*/
public interface MyRemote extends Remote{
	public String sayHello() throws RemoteException;
}

//步骤二制作远程实现
package com.gougoucompany.designpattern.proxyfirst;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
1.实现远程接口
服务必须实现远程接口，也即是客户将要调用的方法的接口
2.扩展UnicastRemoteObject
为了成为远程服务对象，你的对象需要某些"远程的"功能，扩展java.rmi.server.UnicastRemoteObject,让超类来实现
3.设计一个不带参数的构造器，并声明RemoteException
4.用RMI Registry注册此服务
这时候已经有一个远程服务了，必须让他可以被远程客户调用，你需要做的是将此服务实例化，然后放进RMI Registry中，要先确保RMI 
Registry正在运行，否则注册会失败。

*/
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//当类被实例化的时候，超类的构造器总会被调用，如果超类的构造器抛出异常，那么你只能声明子类的构造器也抛出异常
	protected MyRemoteImpl() throws RemoteException {
		//子类每一个构造方法的第一条语句默认都是:super()
		super();
	}

	@Override
	public String sayHello() throws RemoteException {
		return "Server says, 'Hey'";
	}
	
	public static void main(String[] args) {
		try {
			/**
			 * 为你的服务命名，好让客户用来在注册表中再找它，并在RMI registry中注册此名字和此服务，
			 * 当你绑定(bind)服务对象时，RMI会把服务换成stub,然后把stub放到registry中 
			 */
			MyRemote service = new MyRemoteImpl();
			Naming.rebind("RemoteHello", service);
		} catch(Exception ex) {}
	}
	
}

//客户端的实现代码
package com.gougoucompany.designpattern.proxyfirst;

import java.rmi.*;

public class MyRemoteClient {
	public static void main(String[] args) {
		new MyRemoteClient().go();
	}
	
	public void go() {
		try {
			MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
			String s = service.sayHello();
			
			System.out.println(s);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

//步骤三： 产生Stub和Skeleton	 	rmic MyRemoteImpl
//步骤四: 执行rmiregistry 	rmicregistry 
//步骤五: 启动服务  java MyRemoteImpl
/*
JDK5.0以后，这两个类就不需要rmic来编译产生了，而是由JVM进行自动处理，但实际上它们还是存在的
在这里我自己用命令行来执行整个操作非常的吃力，感觉真的很不友好，有些地方不支持或者错误很多，所以
直接用eclipse中的插件来进行执行。
http://www.genady.net/rmi/v20/docs/installation/update_install.html这里面有eclipse下载安装的文档，大家可以看看
直接创建了三个RMI文件，分别是客户端、服务端和公共接口的代码，大家可以具体运行看这篇博客比较详细，我的eclipse有问题暂时运行不了
https://blog.csdn.net/sima1989/article/details/41045249
这里我的命令行终于弄好了，但是eclipse插件一直使用错误

cmd运行过程:
首先不用eclipse创建项目，自己一个类一个类写，三个(接口，服务程序，客户端程序)放入同一个文件夹下面
1.产生Stub和skeleton
C:\Users\Clarence\Desktop\rmitest>rmic MyRemoteImpl
警告: 为 JRMP 生成和使用骨架及静态存根
已过时。骨架不再必要, 而静态存根
已由动态生成的存根取代。建议用户
不再使用rmic来生成骨架和静态存根。
请参阅 java.rmi.server.UnicastRemoteObject 的文档。
2.启动注册程序
C:\Users\Clarence\Desktop\rmitest>rmiregistry
3.启动服务
C:\Users\Clarence\Desktop\rmitest>java MyRemoteImpl
4.启动客户端
C:\Users\Clarence\Desktop\rmitest>java MyRemoteClient
Server says, 'Hey'
可以看到客户端调用sayHello()方法结果打印出东西

整个的实现过程可以分为下面几个步骤:
1.创建远程对象  Server端实例化对象ServiceImpl
2.在rmiregistry注册远程对象
3.Client访问服务端并查找相应的远程对象(通过IP+端口号+字符串的形式)
4.rmiregistry返回服务器远程对象的存根
5.客户端Client调用服务端的方法 
6.客户端的本地存根stub和服务端的骨架skeleton通信
7.骨架代理skeleton调用对应的方法
8.骨架返回给存根
9.存根返回给客户端
其中存根stub和skeleton通过socket通信进行数据的交换，数据必须是可序列化的
接下来我们重新建立一个包com.gougoucompany.designpattern.proxysecond来完善我们之前分布式的糖果机监测程序
*/
package com.gougoucompany.designpattern.proxysecond;

import java.rmi.Remote;
import java.rmi.*;

public interface GumballMachineRemote extends Remote {
	
	/*
	 1.我们需要为GumballMachine创建一个远程接口，该接口提供了一些可以远程调用的方法
	 2.确定接口的所有返回类型都是可序列化的
	 3.在一个具体类中，实现此接口
	*/
	public int getCount() throws RemoteException;
	public String getLocation() throws RemoteException;
	public State getState() throws RemoteException;
}

//将返回的State类型改成可序列化的类型
package com.gougoucompany.designpattern.proxysecond;

import java.io.Serializable;

public interface State extends Serializable{
	
	//所有的状态的动作接口
	void InsertQuarter();
	
	void ejectQuarter();
	
	void turnCrank();
	
	void dispense();
}

//将所有的糖果机状态类中的糖果机引用添加transient关键字，此属性不能被序列化

//GumballMachine实现GumballMachineRemote接口


//在RMi registry中注册糖果机对象
package com.gougoucompany.designpattern.proxysecond;

import java.net.MalformedURLException;
import java.rmi.*;

public class GumballMachineTestDrive {
	public static void main(String[] args) {
//		int count = 0;
//		
//		if(args.length < 2) {
//			System.out.println("GumballMachine <name> <inventory>");
//			System.exit(1);
//		}
//		
//		count = Integer.parseInt(args[1]);
		try {
			String name = "rmitest";
			GumballMachine gumballMachine = new GumballMachine(name, 10);
			Naming.rebind("//" + name + "/gumballmachine" , gumballMachine);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	
	}
}

//修改GumballMonitor适应多种类型的糖果机
package com.gougoucompany.designpattern.proxysecond;

import java.rmi.RemoteException;

//我们创建另一个类，GumballMonitor糖果监听器，以便取得及其的位置，糖果的库存量以及当前的机器状态，并打印出报告(实际开发生成日志如stdout,txt文件等)
public class GumballMonitor{
	GumballMachineRemote gumballMachine; //使用多种类型的糖果机

	public GumballMonitor(GumballMachineRemote machine){
		this.gumballMachine = machine;
	}

	public void report(){
		try {
			System.out.println("Gumball Machine: " + gumballMachine.getLocation());
			System.out.println("Current inventory: " + gumballMachine.getCount() + " gumballs");
			System.out.println("Current state: " + gumballMachine.getState());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

//客户端得到服务端所有糖果机对象，并打印出报告
package com.gougoucompany.designpattern.proxysecond;

import java.rmi.Naming;

public class GumballMonitorTestDrive {
	
	public static void main(String args[]) {
		String[] location = {"rmi://rmitest/gumballmachine",
				"rmi://rmitest/gumballmachine",
				"rmi://rmitest/gumballmachine"};
		
		GumballMonitor[] monitors = new GumballMonitor[location.length];
		
		for(int i = 0; i < location.length; i++) {
			try {
				GumballMachineRemote machine = (GumballMachineRemote)Naming.lookup(location[i]);
				monitors[i] = new GumballMonitor(machine);
				System.out.println(monitors[i]);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < location.length; i++) {
			monitors[i].report();
		}
	}

}


/*
我们前面的所有例子都是远程代理的实现方法
但代理模式的变体很多
代理模式: 
	为另一个对象提供一个替身或占位符以控制对这个对象的访问
远程代理控制访问远程对象
虚拟代理控制访问开销大的资源
保护代理基于权限控制对资源的访问
如果有学习过安卓开发的话，绘制图片时，会在主线程中开启一个单独的线程来负责网络请求和结果处理，
主线程根据请求到的结果来进行界面的绘制，这时候我们可以会用另一个图片或字符串设置字体后来显示在界面上，
提醒用户进行等待。简单的方式是利用虚拟代理，虚拟代理可以代理Icon,管理背景的加载，并在加载未完成前显示提示字符串，加载完成之后显示
加载的图片。
具体的代码见项目com.gougoucompany.designpattern.virtualproxy包
*/

/*
下面我们将使用Java API中java.lang.reflect包中的代理支持，在运行时动态的创建一个代理类(感兴趣的可以研究一下Java反射)
实现一个或多个接口，并将方法的调用转发到你所指定的类。由于实际的代理类是在运行的时候创建的，因此这个Java技术称之为动态代理
我们比如有一个PersonBean接口类，允许设置或者获得一个人的信息。由于不同的人可能允许调用的方法不同，因此需要动态创建两个代理，
创建步骤:
1.创建两个InvokeHandler
	在提供方法调用的时候，会触发Handler中的invoke方法，以此来进行权限设置
2.写代码创建动态代理
3.利用适当的代理来包装PersonBean对象
*/
package com.gougoucomany.designpattern.dynamicproxy;
public interface PersonBean {
	String getName();
	
	String getGender();
	
	String getInterests();
	
	int getHotOrNotRating();
	
	void setName(String name);
	
	void setGender(String gender);
	
	void setInterests(String interests);
	
	void setHotOrNotRating(int rating);
}
//接口实现类
package com.gougoucomany.designpattern.dynamicproxy;


public class PersonBeanImpl implements PersonBean {

	String name;
	String gender;
	String interests;
	int rating;
	int ratingCount = 0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	@Override
	public int getHotOrNotRating() {
		if(ratingCount == 0) return 0;
		return (rating / ratingCount);
	}
	@Override
	public void setHotOrNotRating(int rating) {
		this.rating = rating;
		ratingCount ++;
		
	}
}
//两个Handler动态代理帮助类
package com.gougoucomany.designpattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.print.attribute.standard.PrinterStateReason;

public class OwnerInvocationHandler implements InvocationHandler{
	
	PersonBean person;
	
	public OwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			if(method.getName().startsWith("get")) {
				//自己可以调用get类型的方法
				return method.invoke(person, args);
			} else if(method.getName().equals("setHotOrNotRating")) {
				//自己不能调用这个方法,如果调用报出没有访问权限异常
				throw new IllegalAccessException();
			} else if(method.getName().startsWith("set")) {
				return method.invoke(person, args);
			}
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}


package com.gougoucomany.designpattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NonOwnerInvocationHandler implements InvocationHandler{
	
	PersonBean person;
	
	public NonOwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			if(method.getName().startsWith("get")) {
				return method.invoke(person, args);
			} else if(method.getName().equals("setHotOrNotRating")) {
				return method.invoke(person, args); 
			} else if(method.getName().startsWith("set")) {
				throw new IllegalAccessException();
			}
		} catch (InvocationTargetException e) {
		}
		return null;
	}

}

//测试类
package com.gougoucomany.designpattern.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;

public class MatchMakingTestDrive {
	HashMap<String, PersonBean> datingDB = new HashMap<>();
	
	public static void main(String[] args) {
		MatchMakingTestDrive test = new MatchMakingTestDrive();
		test.drive();
	}
	
	public void drive() {
		PersonBean joe = getPersonFromDatabase("joe Javabean");
		PersonBean ownerProxy = getOwnerProxy(joe);
		System.out.println("Name is " + ownerProxy.getName());
		ownerProxy.setInterests("bowling, Go");
		System.out.println("Interests set from owner proxy");
		try {
			ownerProxy.setHotOrNotRating(10);
		} catch (Exception e) {
			System.out.println("Can't set rating from owner proxy");
		}
		System.out.println("Rating is " + ownerProxy.getHotOrNotRating());
		
		PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println("Name is " + nonOwnerProxy.getName());
		try {
			nonOwnerProxy.setInterests("bolling, Go");
		} catch (Exception e) {
			System.out.println("Can't set interests from non owner proxy");
		}
		nonOwnerProxy.setHotOrNotRating(3);
		System.out.println("Rating set from non owner proxy");
		System.out.println("Raing is " + nonOwnerProxy.getHotOrNotRating());
		
	}
	
	public MatchMakingTestDrive() {
		initializeDatabase();
	}
	
	//需要一个person对象，然后返回它的代理，利用Proxy类的静态newProxyInstance()创建代理
	PersonBean getOwnerProxy(PersonBean person) {
		
		return (PersonBean) Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new OwnerInvocationHandler(person));
	}
	
	PersonBean getNonOwnerProxy(PersonBean person) {
		
		return (PersonBean)Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new NonOwnerInvocationHandler(person));
	}
	
	PersonBean getPersonFromDatabase(String name) {
		return (PersonBean)datingDB.get(name);
	}
	
	void initializeDatabase() {
		PersonBean joe = new PersonBeanImpl();
		joe.setName("joe Javabean");
		joe.setInterests("cars, computers, music");
		joe.setHotOrNotRating(7);
		datingDB.put(joe.getName(), joe);
		
		PersonBean kelly = new PersonBeanImpl();
		kelly.setName("Kelly klosure");
		kelly.setInterests("ebay, movies, music");
		kelly.setHotOrNotRating(6);
		datingDB.put(kelly.getName(), kelly);
	}
}
































































































































