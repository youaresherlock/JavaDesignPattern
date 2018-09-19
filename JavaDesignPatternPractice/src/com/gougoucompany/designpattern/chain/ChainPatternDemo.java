package com.gougoucompany.designpattern.chain;

/*
 * ������ͬ���͵ļ�¼�����������ǲ�ͬ�Ĵ��󼶱𣬲���ÿ����¼����������һ����¼����
 * ÿ����¼���е���һ����¼�������������һ����
 */
public class ChainPatternDemo {
	
	private static AbstractLogger getChainOfLoggers() {
		AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
		//������������־����˳����321���򣬴����û��趨����־���𽫱�����(Ҳ�ɴ������ݿ��logname.log���ļ�)
		//�������ǿ�������С���û��趨����־���𽫱����ԣ�����ֻ�Ǿٸ�����,����Խ��������<=�ĳ�>=�Ϳ�����,�������������־����
		//����ֻ��stderr��׼��������ӡ�����ش���
		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger); 
		
		return errorLogger;
	}
	
	public static void main(String args[]) {
		AbstractLogger loggerChain = getChainOfLoggers();
		
		loggerChain.logMessage(AbstractLogger.INFO,
				"This is an information."); //1��
		
		loggerChain.logMessage(AbstractLogger.DEBUG,
				"This is an debug level information.");//2��
		
		loggerChain.logMessage(AbstractLogger.ERROR,
				"This is an error information.");//3��
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
























