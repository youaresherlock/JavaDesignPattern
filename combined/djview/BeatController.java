package headfirst.designpatterns.combined.djview;

/*
控制器实现ControllerInterface接口
*/  
public class BeatController implements ControllerInterface {
	/*
	控制器在MVC设计中起到桥梁的作用，所以它拥有模型和视图的实例
	*/
	BeatModelInterface model;
	DJView view;
   
   /*
   将模型传入控制器的构造器中
   */
	public BeatController(BeatModelInterface model) {
		this.model = model;
		//创建实例并初始化视图和模型
		view = new DJView(this, model);
        view.createView();
        view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
		model.initialize();
	}
  
  //用户从菜单中选择start,触发点击事件，然后视图中会调用start控制模型和视图，下面同理
	public void start() {
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}
  
	public void stop() {
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
    
	public void increaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm + 1);
	}
    
	public void decreaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm - 1);
  	}
  
 	public void setBPM(int bpm) {
		model.setBPM(bpm);
	}
}
