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
*/























