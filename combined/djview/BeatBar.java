package headfirst.designpatterns.combined.djview;
  
import javax.swing.*;

public class BeatBar extends JProgressBar implements Runnable { 
	private static final long serialVersionUID = 2L;
	/*
	A component that visually displays the progress of some task. As the task progresses 
	towards completion, the progress bar displays the task's percentage of completion. 
	This percentage is typically represented visually by a rectangle which starts out empty
	 and gradually becomes filled in as the task progresses. In addition, the progress bar
	  can display a textual representation of this percentage.
	*/
    JProgressBar progressBar;
	Thread thread;

	public BeatBar() {
		thread = new Thread(this); //public Thread​(Runnable target)创建一个子线程
		//继承下来的方法，设置最大值
		setMaximum(100);
		thread.start();
	}

	public void run() {
		for(;;) {
			int value = getValue();
			value = (int)(value * .75); //乘数因子是0.75，因此是从100开始缩减到下一个节拍
			setValue(value);
			//Repaints this component. repaint()方法继承自public abstract class Component抽象类
			repaint();
			try {
				Thread.sleep(50); //50milliseconds per check to update
			} catch (Exception e) {};
		}
	}
}
