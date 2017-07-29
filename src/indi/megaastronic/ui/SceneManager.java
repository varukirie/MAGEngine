package indi.megaastronic.ui;

import indi.megaastronic.chapter.Chapter1;
import indi.megaastronic.chapter.ChapterLoader;
import indi.megaastronic.chapter.TestChapter;
import indi.megaastronic.control.PlayerControlHandler;
import indi.megaastronic.element.impl.Bullet;
import indi.megaastronic.element.impl.DisplayTime;
import indi.megaastronic.element.impl.Player;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * 负责Scene切换
 * @author Astronic
 *
 */
public class SceneManager {
	public static void startGame(Stage primaryStage){
		StackPane root = new StackPane();
		MyCanvas moveableCanvas = new MyCanvas();
		MyCanvas staticCanvas = new MyCanvas();
		MyCanvas secondaryMCanvas = new MyCanvas(moveableCanvas.getWantPaintMap());
		root.getChildren().add(staticCanvas);
		root.getChildren().add(moveableCanvas);
		root.getChildren().add(secondaryMCanvas);
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MAEngine!");
		primaryStage.show();
	

		//运行 线程MoveHandle
		MoveHandler mh = new MoveHandler(moveableCanvas,secondaryMCanvas);
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				mh.callRepaint();
			}
		};
		timer.start();
		
		Thread mhThread = new Thread(mh);
		mhThread.setPriority(Thread.MAX_PRIORITY);
		mhThread.start();
		//关闭窗口时关闭所有线程
	
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				mh.keepRun=false;//关闭MoveHandler
				ChapterLoader.getScheduledExecutorService().shutdownNow();//关闭关卡计划任务线程
			}
		});
		
		
		//创建玩家
		Player player = Player.getPlayer(210,600);
		
		ElementUtils moveableElementUtils = new ElementUtils(mh, moveableCanvas);
		
		//
		staticCanvas.getWantPaintMap().put("indicator", new Bullet(50, 50));
		staticCanvas.repaint();
		//绑定玩家与键盘控制
		PlayerControlHandler PCH= PlayerControlHandler.getPlayerControlHandler(moveableElementUtils, player);
		PCH.bindEvent(scene);
		//
		/*
		moveableCanvas.getWantPaintMap().put("player", player);//让MyCanvas管理player
		mh.getWantMoveMap().put("player", player);//让MoveHandler管理player
		*/
		moveableElementUtils.add("player", player);
		
		ChapterLoader.init(staticCanvas, moveableElementUtils);
		moveableElementUtils.add("showTime", new DisplayTime(1, MyCanvas.CANVAS_HEIGHT-7));

		
//			ChapterLoader.loadChapter(new Chapter1());
			ChapterLoader.loadChapter(new TestChapter());
		
	}
}
