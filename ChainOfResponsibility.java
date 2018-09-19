/*
* @Author: Clarence
* @Date:   2018-09-19 10:53:07
* @Last Modified by:   Clarence
* @Last Modified time: 2018-09-19 16:20:25
*/
/*
责任链模式
责任链模式(Chain of Responsibility Pattern)为请求创建了一个接受者对象的链。这种模式给与请求的类型，对请求的发送者和接受者进行解耦
。这种类型的设计模式属于行为型模式。
在这个模式中，通常每个接受者都包含对另一个接受者的引用。如果一个对象不能处理该请求，那么他会把相同的请求传给下一个接受者，依此类推。
意图: 避免请求发送者与接受者耦合在一起，让多个对象都有可能接受请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理
它为止。
主要解决: 职责链上的处理者负责请求处理请求，客户只需要将请求发送到职责链上即可，无需关心请求的处理细节和请求的传递，所以职责链将请求
的发送者和请求的处理者解耦了。
何时使用: 在处理消息的时候以过滤很多道
如何解决: 拦截的类都实现统一接口
关键代码: Handler里面聚合它自己，在HandlerRequest里判断是否合适，如果没达到条件则向下传递，向谁传递之前set进去。
纯与不纯的职责链模式:

纯的职责链模式：一个具体处理者角色处理只能对请求作出两种行为中的一个：一个是自己处理（承担责任），另一个是把责任推给下家。
不允许出现某一个具体处理者对象在承担了一部分责任后又将责任向下传的情况。请求在责任链中必须被处理，不能出现无果而终的结局。
反之就是不纯的职责链模式。  
在一个纯的职责链模式里面，一个请求必须被某一个处理者对象所接收；在一个不纯的职责链模式里面，一个请求可以最终不被任何接收端对象所接收。

应用实例: 
1.JS中的事件冒泡(在一个对象上触发某类事件，如单击onClick事件,如果此对象定义了此事件的处理程序，那么此事件就会调用这个处理程序，如果没有
定义此事件处理程序或者事件返回true,那么这个事件会向这个对象的父级对象传播，从里到外，直到它被处理(父级对象所有同类事件都将被激活)，或者它到达了对象
层次的最顶层，即document对象。)
2.异常处理 异常处理机制，我们捕获了一个异常之后可以选择自己去处理掉这个异常，也可以选择往上抛出异常
3.Java Web中的Apache Tomcat对Encoding的处理，Struts2的拦截器，jsp servlet的Filter等应用
4.我之前写一个Python进线程管理工具的时候，就用到了Python logging日志模块(五个级别)，写了一个系统日志记录工具，有些代码不能展示出来，
因此大家自己去看logging实现代码

优点: 
	1.降低耦合度。它将请求的发送者和接受者解耦 否则你只能用if else了
	2.简化了对象，使得对象不需要知道链的结构
	3.增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。
	4.增加新的请求处理类很方便
缺点: 
	1.由于一个请求没有明确的接受者，那么就不能保证它一定会被处理，该请求可能一直到链的末端都得不到处理；
一个请求也可能因职责链没有被正确配置而得不到处置
	2.对于比较长的职责链，请求的处理可能涉及多个处理对象，系统性能将会受到一定影响，而且在进行代码调试时不太方便。
	3.如果建链不当，可能会造成循环调用，将导致系统陷入死循环
*/

/*
实现
创建抽象类AbstractLogger,带有详细的日志记录级别。然后我们创建三种类型的记录器，都扩展了AbstractLogger.每个记录消息的级别
是否属于自己的级别，如果是相应地打印出来，否则将不打印并把消息传给下一个记录器。
*/

//创建抽象的记录器类
package com.gougoucompany.designpattern.chain;
//创建抽象的记录器类
public abstract class AbstractLogger {
	public static int INFO = 1;
	public static int DEBUG = 2;
	public static int ERROR = 3;
	
	protected int level;
	
	//责任链中的下一个元素
	protected AbstractLogger nextLogger;
	
	public void setNextLogger(AbstractLogger nextLogger) {
		this.nextLogger = nextLogger;
	}
	
	public void logMessage(int level, String message) {
		//我用python的logging模块封装成系统日志模块，其中就可以设置日志级别，级别不够是不能
		//在控制台上打印的，只能保存到日志数据库或者生成日志文件
		if(this.level <= level) {
			write(message);
		}
		if(nextLogger != null) {
			nextLogger.logMessage(level,  message);
		}
	}
	
	protected abstract void write(String message);
	
}


//创建扩展了该记录器类的实体类
package com.gougoucompany.designpattern.chain;

public class ConsoleLogger extends AbstractLogger {
	public ConsoleLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		// TODO Auto-generated method stub
		System.out.println("Standard Console::Logger: " + message);
	}
}


package com.gougoucompany.designpattern.chain;

public class ErrorLogger extends AbstractLogger{

	public ErrorLogger(int level) {
		this.level = level;
	}
	
	@Override
	protected void write(String message) {
		// TODO Auto-generated method stub
		System.out.println("Error Console::Logger: " + message);
	}

}


package com.gougoucompany.designpattern.chain;

public class FileLogger extends AbstractLogger {
 
   public FileLogger(int level){
      this.level = level;
   }
 
   @Override
   protected void write(String message) {    
      System.out.println("File::Logger: " + message);
   }
}

//创建不同类型的记录器，赋予他们不同的错误级别，并在每个记录器中设置下一个记录器。每个记录器中的下一个记录器代表的是链的一部分。
package com.gougoucompany.designpattern.chain;

/*
 * 创建不同类型的记录器。赋予他们不同的错误级别，并在每个记录器中设置下一个记录器。
 * 每个记录器中的下一个记录器代表的是链的一部分
 */
public class ChainPatternDemo {
	
	private static AbstractLogger getChainOfLoggers() {
		AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
		//责任链处理日志级别顺序是321降序，大于用户设定的日志级别将被忽略(也可存入数据库或logname.log等文件)
		//这里我们开发中是小于用户设定的日志级别将被忽略，这里只是举个例子,你可以将抽象基类<=改成>=就可以了,这样控制你的日志精度
		//比如只在stderr标准错误流打印最严重错误
		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger); 
		
		return errorLogger;
	}
	
	public static void main(String args[]) {
		AbstractLogger loggerChain = getChainOfLoggers();
		
		loggerChain.logMessage(AbstractLogger.INFO,
				"This is an information."); //1次
		
		loggerChain.logMessage(AbstractLogger.DEBUG,
				"This is an debug level information.");//2次
		
		loggerChain.logMessage(AbstractLogger.ERROR,
				"This is an error information.");//3次
	}
}

/*
result:
Standard Console::Logger: This is an information.
File::Logger: This is an debug level information.
Standard Console::Logger: This is an debug level information.
Error Console::Logger: This is an error information.
File::Logger: This is an error information.
Standard Console::Logger: This is an error information.
*/
