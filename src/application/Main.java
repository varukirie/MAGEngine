package application;
	
import indi.megaastronic.chapter.Chapter1;
import indi.megaastronic.chapter.ChapterLoader;
import indi.megaastronic.control.PlayerControlHandler;
import indi.megaastronic.element.Ball;
import indi.megaastronic.element.Player;
import indi.megaastronic.element.ShowTime;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	public static final boolean DEBUG = true;

	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = new StackPane();
			MyCanvas moveableCanvas = new MyCanvas();
			MyCanvas staticCanvas = new MyCanvas();
			MyCanvas secondaryMCanvas = new MyCanvas(moveableCanvas.getWantPaintMap());
			root.getChildren().add(staticCanvas);
			root.getChildren().add(moveableCanvas);
			root.getChildren().add(secondaryMCanvas);
			Scene scene=new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MySTGEngine!");
			primaryStage.show();
		
			//运行 线程MoveHandle
			MoveHandler mh = new MoveHandler(moveableCanvas,secondaryMCanvas);
			Thread mhThread = new Thread(mh);
			mhThread.setPriority(Thread.MAX_PRIORITY);
			mhThread.start();
			//关闭窗口时关闭所有线程
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					mh.keepRun=false;
				}
			});
			
			//创建玩家
			Player player = new Player(10,10);
			
			ElementUtils moveableElementUtils = new ElementUtils(mh, moveableCanvas);
			
			//
			staticCanvas.getWantPaintMap().put("indicator", new Ball(50, 50));
			staticCanvas.repaint();
			//绑定玩家与键盘控制
			PlayerControlHandler PCH= new PlayerControlHandler(moveableElementUtils,player);
			PCH.bindEvent(scene);
			//
			/*
			moveableCanvas.getWantPaintMap().put("player", player);//让MyCanvas管理player
			mh.getWantMoveMap().put("player", player);//让MoveHandler管理player
			*/
			moveableElementUtils.add("player", player);
			
			ChapterLoader.init(staticCanvas, moveableElementUtils);
			moveableElementUtils.add("showTime", new ShowTime(200, 200));
			ChapterLoader.loadChapter(new Chapter1());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
