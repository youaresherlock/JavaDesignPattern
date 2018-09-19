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






























