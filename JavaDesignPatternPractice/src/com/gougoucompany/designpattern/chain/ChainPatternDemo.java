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
























