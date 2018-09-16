package headfirst.designpatterns.combined.djview;
    
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
DJView是一个观察者，同时关心实时节拍和BPM的改变
*/
/*
The listener interface for receiving action events. The class that is interested in processing 
an action event implements this interface, and the object created with that class is registered
 with a component, using the component's addActionListener method. When the action event occurs
 , that object's actionPerformed method is 
ActionListener接口有抽象方法actionPerformed(ActionEvent event),点击按钮等操作会调用这个方法传入事件对象
*/
public class DJView implements ActionListener,  BeatObserver, BPMObserver {
    //视图持有模型和控制器的引用
	BeatModelInterface model;
	ControllerInterface controller;

    //包含模型视图界面的组件
    JFrame viewFrame;
    JPanel viewPanel;
	BeatBar beatBar;
	JLabel bpmOutputLabel;
    //包含其他用户控制的界面的组件
    JFrame controlFrame; 
    JPanel controlPanel;
    JLabel bpmLabel; //bpm显示标签
    JTextField bpmTextField; //bpm文本框
    JButton setBPMButton; //设置bpm按钮
    JButton increaseBPMButton; //增加bpm按钮
    JButton decreaseBPMButton; //减少bpm按钮
    JMenuBar menuBar; //菜单栏
    JMenu menu; //菜单
    JMenuItem startMenuItem; //开始菜单项
    JMenuItem stopMenuItem; //停止菜单项

    public DJView(ControllerInterface controller, BeatModelInterface model) {	
		this.controller = controller;
		this.model = model;
        //注册成为BeatObserver和BPMObserver观察者
		model.registerObserver((BeatObserver)this);
		model.registerObserver((BPMObserver)this);
    }
    
    //创建所有包含模型视图界面的组件
    public void createView() {
		// Create all Swing components here
        viewPanel = new JPanel(new GridLayout(1, 2)); 
        viewFrame = new JFrame("View");
        //Exit the application using the System exit method. Use this only in applications.
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        viewFrame.setSize(new Dimension(100, 80)); //Resizes this component so that it has width d.width and height d.height.
        bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER); //"offline"字符串在标签中居中显示
		beatBar = new BeatBar();
		beatBar.setValue(0); 
        JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
		bpmPanel.add(beatBar);
        bpmPanel.add(bpmOutputLabel);
        viewPanel.add(bpmPanel);
        viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);  //最终是两行一列的布局
        viewFrame.pack();
        viewFrame.setVisible(true);
	}
  
  //包含其他用户控制的组件的创建
    public void createControls() {
		// Create all Swing components here
        /*
        Provides a hint as to whether or not newly created JFrames should have 
        their Window decorations (such as borders, widgets to close the window, 
        title...) provided by the current look and feel. 
        */
        JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control"); //框架
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(100, 80));

        controlPanel = new JPanel(new GridLayout(1, 2));//控制面板是一行两列的网格布局

        menuBar = new JMenuBar();
        menu = new JMenu("DJ Control"); //菜单
        startMenuItem = new JMenuItem("Start");
        menu.add(startMenuItem); //添加开始菜单项
        startMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.start(); //控制器调用start方法，模型开始控制播放音乐过程的逻辑
            }
        });
        stopMenuItem = new JMenuItem("Stop");
        menu.add(stopMenuItem); //添加停止菜单项
        stopMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.stop();
            }
        });
        JMenuItem exit = new JMenuItem("Quit"); //添加退出菜单 
        //注册监听器，退出如有点击事件，则退出虚拟机
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        menu.add(exit);
        menuBar.add(menu); //菜单栏加入菜单
        controlFrame.setJMenuBar(menuBar);

        bpmTextField = new JTextField(2);
        bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
        setBPMButton = new JButton("Set");
        setBPMButton.setSize(new Dimension(10,40));
        increaseBPMButton = new JButton(">>");
        decreaseBPMButton = new JButton("<<");
        //注册本类监听set、<<、>>点击事件的监听
        setBPMButton.addActionListener(this); 
        increaseBPMButton.addActionListener(this); 
        decreaseBPMButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		buttonPanel.add(decreaseBPMButton);
		buttonPanel.add(increaseBPMButton);

        JPanel enterPanel = new JPanel(new GridLayout(1, 2));
        enterPanel.add(bpmLabel);
        enterPanel.add(bpmTextField);
        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        insideControlPanel.add(enterPanel);
        insideControlPanel.add(setBPMButton);
        insideControlPanel.add(buttonPanel);
        controlPanel.add(insideControlPanel);
        
        /*
        In general, when you want to set a border on a standard Swing component
        other than JPanel or JLabel, we recommend that you put the component in a JPanel and set the border on the JPanel.
        凡是实现了JComponent接口的组件都可以设置边框，建议在JPanel上设置边框
        可以看到BorderFactory是一个标准边框对象的工厂类,返回的对象是实现了Border接口的对象，Border接口下有具体的各种标准边框类
        createEmptyBorder():
        Creates an empty border that takes up space but which does no drawing, specifying the width of the top,
        left, bottom, and right side
        */
        bpmLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        /*
        Sets the defaultButton property, which determines the current default button for this JRootPane. 
        The default button is the button which will be activated when a UI-defined activation event 
        (typically the Enter key) occurs in the root pane regardless of whether or not the button has keyboard
        focus (unless there is another component within the root pane which consumes the activation event, such as a
        JTextPane). For default activation to work, the button must be an enabled descendent of the root pane when 
        activation occurs. To remove a default button from this root pane, set this property to null.
        */
        controlFrame.getRootPane().setDefaultButton(setBPMButton);
        controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        controlFrame.pack();
        controlFrame.setVisible(true);
    }

    /*
    这些方法将菜单中的Start和Stop项变成enable或disable.控制器可以利用这些接口方法改变用户界面
    */
	public void enableStopMenuItem() {
    	stopMenuItem.setEnabled(true);
	}

	public void disableStopMenuItem() {
    	stopMenuItem.setEnabled(false);
	}

	public void enableStartMenuItem() {
    	startMenuItem.setEnabled(true);
	}

	public void disableStartMenuItem() {
    	startMenuItem.setEnabled(false);
	}

    /*
    不同按钮被单击，将信息传给控制器来进一步改变模型的
    */
    public void actionPerformed(ActionEvent event) {
		if (event.getSource() == setBPMButton) {
            //Parses the string argument as a signed decimal integer.
			int bpm = Integer.parseInt(bpmTextField.getText());
        	controller.setBPM(bpm);
		} else if (event.getSource() == increaseBPMButton) {
			controller.increaseBPM();
		} else if (event.getSource() == decreaseBPMButton) {
			controller.decreaseBPM();
		}
    }

    /*
    模型BPM状态改变时，updateBPM()方法会被调用。
    这时候我们更新当前BPM的显示。我们可以通过直接请求模型而得到这个值
    */
	public void updateBPM() {
		if (model != null) {
			int bpm = model.getBPM();
			if (bpm == 0) {
				if (bpmOutputLabel != null) {
        			bpmOutputLabel.setText("offline");
				}
			} else {
				if (bpmOutputLabel != null) {
        			bpmOutputLabel.setText("Current BPM: " + model.getBPM());
				}
			}
		}
	}
  
  /*
  模型开始一个新的节拍时，updateBeat()方法会被调用。这时候，我们让脉动柱跳一下，
  我们设置为100，让脉动柱自行处理动画部分
  */
	public void updateBeat() {
		if (beatBar != null) {
			 beatBar.setValue(100);
		}
	}
}
