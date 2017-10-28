package magengine.game;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.ChapterLoader;
import magengine.chapter.util.QuickDanmuku;
import magengine.control.PlayerControlHandler;
import magengine.control.PlayerLaunchHandler;
import magengine.element.impl.DisplayMessage;
import magengine.element.impl.Player;
import magengine.paint.MyCanvas;
import magengine.ui.SceneManager;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class GameSession {
	public static GameSession instance = null;
	
	private Level level = Level.NORMAL;
	public static final int BOMB_LIMIT = 3;
	public static final int HEALTH_LIMIT = 10;
	public static final int POWER_LIMIT = 100;

	public static final int PRESET_BOMB = 2;
	public static final int PRESET_HEALTH = 2;
	public static final int PRESET_POWER = 0;
	private int bomb = PRESET_BOMB;
	private int health = PRESET_HEALTH;
	private int power = PRESET_POWER;


	public static GameSession startGameSession(){
		if(instance!=null)
			throw new IllegalStateException("GameSession已经存在 需要先调用closeGameSession");
		else
			instance = new GameSession();
		return instance;
	}
	public static GameSession getGameSession(){
		if(instance==null)
			throw new IllegalStateException("GameSession不存在 需要先调用startGameSession");
		else
			return instance;
	}
	public static void closeGameSession(){
		if(instance!=null){
			instance.shutdownGame();
		}
		instance=null;
	}
	


	public void resetByPreset() {
		reset(PRESET_HEALTH, PRESET_POWER, PRESET_BOMB);
	}

	public void reset(int health, int power, int bomb) {
		this.bomb = bomb;
		this.health = health;
		this.power = power;
		this.shutdownGame();
		this.loadGameScene();
	}

	public boolean useBomb() {
		if (bomb > 0) {
			bomb--;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 减少生命值 如果生命值不足则死亡
	 * 
	 * @return 如果死亡了返回true，如果没死返回false
	 */
	public boolean decHealthAndCheck() {
		if (health > 0) {
			health--;
			return false;
		} else {
			return true;
		}
	}


	
	public void loadGameScene() {
		Stage primaryStage=SceneManager.getInstance().getPrimaryStage();
		StackPane root = new StackPane();
		MyCanvas moveableCanvas = new MyCanvas();
		MyCanvas staticCanvas = new MyCanvas();
		MyCanvas secondaryMCanvas = new MyCanvas(moveableCanvas.getWantPaintMap());
		root.getChildren().add(staticCanvas);
		root.getChildren().add(moveableCanvas);
		root.getChildren().add(secondaryMCanvas);
		DI.di().put("staticCanvas", staticCanvas);
		//
//		staticCanvas.getWantPaintMap().put("indicator", Player.getPlayer());
//		staticCanvas.repaint();
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MAGEngine!");
		primaryStage.show();
		
		LogicExecutor logicExecutor=LogicExecutor.getLogicExecutor();
		DI.di().put("logicExecutor",logicExecutor);
		//运行 线程MoveHandle
		MoveHandler mh = new MoveHandler(moveableCanvas,secondaryMCanvas);
		DI.di().put("mh", mh);
		

		
		Thread mhThread = new Thread(mh);
//		mhThread.setPriority(Thread.MAX_PRIORITY);
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
		
		
		moveableElementUtils.add("displayMessage", new DisplayMessage(1, MyCanvas.CANVAS_HEIGHT-20));
		ChapterLoader.init(staticCanvas);
		
		
//		ChapterLoader.loadChapter(new ChapterDemo());
		new Thread(new PlayerLaunchHandler()).start();
	}
	private void shutdownGame(){
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
		LogicExecutor logicExecutor= (LogicExecutor) DI.di().get("logicExecutor");
		if(logicExecutor!=null){
			logicExecutor.shutdownNow();
		}
		LogicExecutor.clear();
		
		DI.di().clear();
		QuickDanmuku.clear();
		Player.clear();
		PlayerControlHandler.clear();
	}
	
	public void loadChapter(AChapter chapter){
		ChapterLoader.loadChapter(chapter);
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public int getBomb() {
		return bomb;
	}
	public int getHealth() {
		return health;
	}
	public void incrPower(int delta) {
		setPower(getPower() + delta);
	}

	public void setPower(int power) {
		if (power < 0)
			throw new IllegalArgumentException("Power不能小于0");
		if (power <= POWER_LIMIT) {
			this.power = power;
		} else {
			this.power = POWER_LIMIT;
		}
	}

	public int getPower() {
		return power;
	}

	public void setBomb(int bomb) {
		if (bomb < 0)
			throw new IllegalArgumentException("bomb不能小于0");
		if (bomb <= BOMB_LIMIT) {
			this.bomb = bomb;
		} else {
			this.bomb = BOMB_LIMIT;
		}
	}

	public void setHealth(int health) {
		if (health < 0)
			throw new IllegalArgumentException("health不能小于0");
		if (health <= HEALTH_LIMIT) {
			this.health = health;
		} else {
			this.health = HEALTH_LIMIT;
		}
	}
	private Runnable failureEvent = ()->{};
	public void failure(){
		System.out.println("failure!");
		getFailureEvent().run();
	}
	public void setFailureEvent(Runnable failureEvent) {
		this.failureEvent = failureEvent;
	}
	
	public Runnable getFailureEvent() {
		return failureEvent;
	}
	@Override
	public String toString() {
		return "GameSession [level=" + level + ", bomb=" + bomb + ", health=" + health + ", power=" + power + "]";
	}
	
}
