package magengine.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import magengine.chapter.ChapterDemo;
import magengine.chapter.TestChapter;
import magengine.chapter.TestChapter1;
import magengine.chapter.util.ChapterLoader;
import magengine.chapter.util.QuickDanmuku;
import magengine.control.PlayerControlHandler;
import magengine.control.PlayerLaunchHandler;
import magengine.element.impl.DisplayTime;
import magengine.element.impl.Player;
import magengine.paint.MoveHandler;
import magengine.paint.MyCanvas;
import magengine.util.DI;
import magengine.util.LogicExecutor;
import magengine.util.ElementUtils;
/**
 * 负责Scene切换
 * @author Astronic
 *
 */
public class SceneManager {
	public static void init(Stage primaryStage){
		instance.setPrimaryStage(primaryStage);
	}
	public static SceneManager getInstance(){
		return instance;
	}
	private static SceneManager instance = new SceneManager();
	private Stage primaryStage;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	public void startGame(){
		
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
		primaryStage.setTitle("MAGEngine!");
		primaryStage.show();
	
		LogicExecutor deltaExecutor=LogicExecutor.getLogicExecutor();
		DI.di().put("deltaExecutor",deltaExecutor);
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
		mh.addCollisionElement("player", player);
		

		//绑定玩家与键盘控制
		PlayerControlHandler PCH= PlayerControlHandler.getPlayerControlHandler(moveableElementUtils, player);
		PCH.bindEvent(scene);
		//
		moveableElementUtils.add("player", player);
		
		
		moveableElementUtils.add("displayTime", new DisplayTime(1, MyCanvas.CANVAS_HEIGHT-7));
		ChapterLoader.init(staticCanvas);
		
		
//		ChapterLoader.loadChapter(new ChapterDemo());
		new Thread(new PlayerLaunchHandler()).start();
		ChapterLoader.loadChapter(new TestChapter());
	}
	public void shutdownGame(){
//		ChapterLoader.getScheduledExecutorService().shutdownNow();
		MoveHandler mh=((MoveHandler)(DI.di().get("mh")));
		if(mh!=null){
			mh.keepRun=false;//关闭MoveHandler
			mh.clear();
		}
		ChapterLoader.getScheduledExecutorService().shutdownNow();//关闭关卡计划任务线程
		AnimationTimer timer = ((AnimationTimer)(DI.di().get("animationTimer")));
		if(timer!=null){
			timer.stop();
		}
		LogicExecutor deltaExecutor= (LogicExecutor) DI.di().get("deltaExecutor");
		if(deltaExecutor!=null){
			deltaExecutor.shutdownNow();
		}
		LogicExecutor.clear();
		
		DI.di().clear();
		QuickDanmuku.clear();
		Player.clear();
		PlayerControlHandler.clear();
	}
	
	public void loadSceneTest(){
		StackPane root = new StackPane();
		Canvas canvas = new Canvas(200,300);
		canvas.getGraphicsContext2D().fillOval(10, 10, 20, 20);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.setOnKeyReleased((e)->{
			if(KeyCode.ENTER.equals(e.getCode())){
				SceneManager.getInstance().startGame();
			}
		});
		primaryStage.setScene(scene);
	}
	
}
