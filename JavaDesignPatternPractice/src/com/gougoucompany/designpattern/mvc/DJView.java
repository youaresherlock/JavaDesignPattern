package com.gougoucompany.designpattern.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//DJView是一个观察者，同时关心实时节拍和BPM的改变

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class DJView implements ActionListener, BeatObserver, BPMObserver {
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
		//Create all Swing components here
		viewPanel = new JPanel(new GridLayout(1,  2));
		viewFrame = new JFrame("View");
		viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//viewFrame.setSize(new Dimension(500,  400));
		bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
		beatBar = new BeatBar();
		beatBar.setValue(0);
		JPanel bpmPanel = new JPanel(new GridLayout(2,  1));
		bpmPanel.add(beatBar);
		bpmPanel.add(bpmOutputLabel);
		viewPanel.add(bpmPanel);
		viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
		viewFrame.pack();
		viewFrame.setSize(600, 400);
		viewFrame.setVisible(true);
	}
	
	//包含其他用户控制的组件的创建
	public void createControls() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		controlFrame = new JFrame("Control");
		controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//controlFrame.setSize(new Dimension(100, 80));
		
		controlPanel = new JPanel(new GridLayout(1, 2));
		
		menuBar = new JMenuBar();
		menu = new JMenu("DJ Control"); //菜单
		startMenuItem = new JMenuItem("Start");
		menu.add(startMenuItem); //添加开始菜单
		startMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.start(); ////控制器调用start方法，模型开始控制播放音乐过程的逻辑
			}
		});
		stopMenuItem = new JMenuItem("Stop");
		menu.add(stopMenuItem); //添加停止菜单项
		stopMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.stop();
			}
		});
		JMenuItem exit = new JMenuItem("Quit"); //添加退出菜单
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		menu.add(exit);
		menuBar.add(menu);
		controlFrame.setJMenuBar(menuBar);
		
		bpmTextField = new JTextField(2);
		bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
		setBPMButton = new JButton("Set");
		setBPMButton.setSize(new Dimension(10, 40));
		increaseBPMButton = new JButton(">>");
		decreaseBPMButton = new JButton("<<");
		//注册本类监听set,<<,>>点击事件的监听
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
		
		bpmLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		controlFrame.getRootPane().setDefaultButton(setBPMButton);
		controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);
		
		controlFrame.pack();
		controlFrame.setSize(800, 400);
		controlFrame.setVisible(true);
	}

	/*
            模型BPM状态改变时，updateBPM()方法会被调用。
            这时候我们更新当前BPM的显示。我们可以通过直接请求模型而得到这个值
    */
	@Override
	public void updateBPM() {
		// TODO Auto-generated method stub
		if(model != null) {
			int bpm = model.getBPM();
			if(bpm == 0) {
				if(bpmOutputLabel != null) {
					bpmOutputLabel.setText("offline");
				}
			} else {
				if(bpmOutputLabel != null) {
					bpmOutputLabel.setText("Current BPM: " + model.getBPM());
				}
			}
		}
	}

	/**
	 * 模型开始一个新的节拍时，updateBeat()方法会被调用。这时候，我们让脉动柱跳一下，我们设置
	 * 为100，让脉动柱自行处理动画部分。
	 */
	@Override
	public void updateBeat() {
		// TODO Auto-generated method stub
		if(beatBar != null) {
			beatBar.setValue(100);
		}
	}
	
	/*
	 * 这些方法将菜单中的start和stop项变成enable或disable,
	 * 控制器可以利用这些接口方法改变用户界面
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
	
	//不同按钮被单击，将信息传给控制器来进一步改变模型的
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == setBPMButton) {
			//如果bpmTextField不为空，并且内容不是空字符串
			if(bpmTextField != null &&  !bpmTextField.getText().isEmpty()) {
				int bpm = Integer.parseInt(bpmTextField.getText());
				controller.setBPM(bpm);
			} 
		} else if(e.getSource() == increaseBPMButton) {
			controller.increaseBPM();
		} else if(e.getSource() == decreaseBPMButton) {
			controller.decreaseBPM();
		}
	}
}












