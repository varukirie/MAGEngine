package indi.megaastronic.ui;

import indi.megaastronic.chapter.TestChapter;
import indi.megaastronic.chapter.util.ChapterLoader;
import indi.megaastronic.control.PlayerControlHandler;
import indi.megaastronic.element.impl.DisplayTime;
import indi.megaastronic.element.impl.Player;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.DI;
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
		DI.di().put("staticCanvas", staticCanvas);
		//
		staticCanvas.getWantPaintMap().put("indicator", Player.getPlayer());
		staticCanvas.repaint();
		
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MAEngine!");
		primaryStage.show();
	

		//运行 线程MoveHandle
		MoveHandler mh = new MoveHandler(moveableCanvas,secondaryMCanvas);
		DI.di().put("mh", mh);
		
		Thread mhThread = new Thread(mh);
		mhThread.setPriority(Thread.MAX_PRIORITY);
		mhThread.start();
		
		ElementUtils moveableElementUtils = new ElementUtils(mh, moveableCanvas,root);
		mh.setmEU(moveableElementUtils);
		DI.di().put("mEU", moveableElementUtils);
		DI.di().put("switcher", moveableElementUtils.getSwitcher());
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
//				long lasttime=System.currentTimeMillis();
				mh.callRepaint();
				moveableElementUtils.getSwitcher().repaint();
//				if(Main.DEBUG_BENCH){
//					System.out.println("2.渲染"+(System.currentTimeMillis()-lasttime)+"ms");
//				}
			}
		};
		timer.start();
		DI.di().put("animationTimer", timer);
		//关闭窗口时关闭所有线程
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				shutdownGame();
			}
		});

		
		
		//创建玩家
		Player player = Player.getPlayer(210,600);
		


		//绑定玩家与键盘控制
		PlayerControlHandler PCH= PlayerControlHandler.getPlayerControlHandler(moveableElementUtils, player);
		PCH.bindEvent(scene);
		//
		moveableElementUtils.add("player", player);
		
		
		moveableElementUtils.add("displayTime", new DisplayTime(1, MyCanvas.CANVAS_HEIGHT-7));
		ChapterLoader.init(staticCanvas);
		
//			ChapterLoader.loadChapter(new Chapter1());
			ChapterLoader.loadChapter(new TestChapter());
		
	}
	public static void shutdownGame(){
		((MoveHandler)(DI.di().get("mh"))).keepRun=false;//关闭MoveHandler
		ChapterLoader.getScheduledExecutorService().shutdownNow();//关闭关卡计划任务线程
		((AnimationTimer)(DI.di().get("animationTimer"))).stop();
		DI.di().clear();
	}
	
}
