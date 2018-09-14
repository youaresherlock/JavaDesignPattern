package headfirst.designpatterns.combined.djview;
  
public class DJTestDrive {

    public static void main (String[] args) {
    	//创建模型
        BeatModelInterface model = new BeatModel();
        //创建控制器，传入模型，而视图是由控制器在自己构造函数中实现的
		ControllerInterface controller = new BeatController(model);
    }
}
