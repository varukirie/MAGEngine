package magengine.game;


import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import application.Main;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.ChapterLoader;
import magengine.chapter.util.QuickDanmuku;
import magengine.control.PlayerControlHandler;
import magengine.control.PlayerLaunchHandler;
import magengine.element.BaseElement;
import magengine.element.impl.BloodBar;
import magengine.element.impl.BombPainting;
import magengine.element.impl.DisplayMessage;
import magengine.element.impl.EmBloodBar;
import magengine.element.impl.Player;
import magengine.mulplay.Client;
import magengine.mulplay.Server;
import magengine.mulplay.Transport;
import magengine.paint.BackgroundUtil;
import magengine.paint.MyCanvas;
import magengine.ui.SceneManager;
import magengine.util.C;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class GameSession {

	public static GameSession instance = null;
	private Level level = Level.NORMAL;
	private static Random rand=null;
	public static Random rand() {
		return rand;
	}
	
	public static final int BOMB_LIMIT = 3;
	public static final int HEALTH_LIMIT = 10;
	public static final int POWER_LIMIT = 100;

	public static final int PRESET_BOMB = 3;
	public static final int PRESET_HEALTH = 4;
	public static final int PRESET_POWER = 0;
	private int bomb = PRESET_BOMB;
	private int health = PRESET_HEALTH;
	private int power = PRESET_POWER;

	public final double PRESET_PLAYER_POSITION_X=MyCanvas.CANVAS_WIDTH/2;
	public final double PRESET_PLAYER_POSITION_Y=MyCanvas.CANVAS_HEIGHT-100;
	public final double MULPLAY_PLAYER_POSITION_DELTA_X=100;
	
	public boolean mulplay = false;
	public boolean mulplayServer=false;
	public BloodBar bb;
	public EmBloodBar emBb;
	public BombPainting bp;
	public static final int PORT = 10231;
	public static String remoteHost = "127.0.0.1";
	private NioEventLoopGroup loopGroup;
	private Channel mulplayChannel;
	private Server server;
	private Client client;
	private Transport clientOrServer ;
	
	public static GameSession startGameSession(){
		if(instance!=null)
			throw new IllegalStateException("GameSession已经存在 需要先调用closeGameSession");
		else{
			instance = new GameSession();
		}
			
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
			bp.paint(getBomb());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean haveBomb(){
		return bomb>0;
	}

	/**
	 * 减少生命值 如果生命值不足则死亡
	 * 
	 * @return 如果死亡了返回true，如果没死返回false
	 */
	public boolean decHealthAndCheck() {
		if (health > 0) {
			health--;
			bb.paint(this.getHealth(), PRESET_HEALTH);
			return false;
		} else {
			return true;
		}
	}


	private BackgroundUtil backgroundUtil = new BackgroundUtil();
	private long  lastTime4bench=0;
	public void loadGameScene() {
//		Canvas canvas = new Canvas(900, 700);  
//	    GraphicsContext gc = canvas.getGraphicsContext2D();
		this.bb = new BloodBar(600,40,200,30);
		emBb = new EmBloodBar(870,0,30,700);
		this.bp = new BombPainting(600,120);
		bp.paint(getBomb());
		bb.paint(this.getHealth(), PRESET_HEALTH);
		Stage primaryStage=SceneManager.getInstance().getPrimaryStage();
		BorderPane gArea = new BorderPane();
		BackgroundImage bimg = new BackgroundImage(new Image("/img/starbackground.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//		gArea.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));
		gArea.setBackground(new Background(bimg));
		FlowPane gDataArea = new FlowPane(Orientation.VERTICAL,10,50);//未设置node gap
//		gDataArea.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));
		gDataArea.setPrefWrapLength(200);
//		Label label1 = new Label();
//		label1.setFont(Font.font("力量"));
//		power.getChildren().add(label1);字体样式测试
//		VBox power = new VBox(new Label("力量"));
//		VBox bomb = new VBox(new Label("炸弹"));
//		VBox life = new VBox(new Label("生命"));
//		VBox power = new VBox(setTextFont("POWER"));
		VBox bomb = new VBox(setTextFont("BOMB"));
		VBox life = new VBox(setTextFont("LIFE"));
		StackPane root = new StackPane();
		BackgroundUtil bu = backgroundUtil;
		MyCanvas bgCanvas = bu.getBGCanvas();
		Image gamebgimg=null;
		try {
			gamebgimg = new Image(this.getClass().getResourceAsStream("/img/gameplaybackground.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		bu.setBackgroundImg(gamebgimg);
		MyCanvas moveableCanvas = new MyCanvas();
		MyCanvas staticCanvas = new MyCanvas();
		MyCanvas secondaryMCanvas = new MyCanvas(moveableCanvas.getWantPaintMap());
		root.getChildren().add(bgCanvas);
		root.getChildren().add(staticCanvas);
		root.getChildren().add(moveableCanvas);
		root.getChildren().add(secondaryMCanvas);
		
		gDataArea.getChildren().addAll(life,bomb);
		gDataArea.setAlignment(Pos.TOP_LEFT);;
		gArea.setLeft(root);
		gArea.setCenter(gDataArea);
		DI.di().put("staticCanvas", staticCanvas); 
//		staticCanvas.getWantPaintMap().put("indicator", Player.getPlayer());
//		staticCanvas.repaint();
		Scene scene=new Scene(gArea,900,700);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MAGEngine!");
		gArea.getChildren().add(bb.getBcanvas());
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
				backgroundUtil.paintBackground(1);
				mh.callRepaint();
				moveableElementUtils.getSwitcher().repaint();
				if(Main.DEBUG_RENDER_BENCH){
					System.out.println("2.渲染"+(System.currentTimeMillis()-lastTime4bench)+"ms");
					lastTime4bench=System.currentTimeMillis();
				}
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
		Player player1 = Player.getPlayer1(PRESET_PLAYER_POSITION_X,PRESET_PLAYER_POSITION_Y);
		mh.addCollisionElement("player1", player1);


		//绑定玩家与键盘控制
		PlayerControlHandler PCH= PlayerControlHandler.getPlayerControlHandler(moveableElementUtils, player1);
		PCH.bindEvent(scene);
		//
		moveableElementUtils.add("player1", player1);
		
		
		moveableElementUtils.add("displayMessage", new DisplayMessage(1, MyCanvas.CANVAS_HEIGHT-20));
		ChapterLoader.init(staticCanvas);
		if(this.mulplay){
			if(this.mulplayServer){
				server = new Server(PORT);
				clientOrServer=server;
				server.start();//监听端口
				setLoopGroup(server.getBossLoop());
				player1.setX(PRESET_PLAYER_POSITION_X-MULPLAY_PLAYER_POSITION_DELTA_X);
				player1.setY(PRESET_PLAYER_POSITION_Y);
				Player player2 = Player.getPlayer2(PRESET_PLAYER_POSITION_X+MULPLAY_PLAYER_POSITION_DELTA_X,PRESET_PLAYER_POSITION_Y);
				moveableElementUtils.add("player2", player2);
				
			}else{
				client = new Client(remoteHost, PORT);
				clientOrServer=client;
				client.start();//client完成连接
				setLoopGroup(client.getGroup());
				if(client.getChannel()==null){
					System.out.println("连接失败");
				}
				player1.setX(PRESET_PLAYER_POSITION_X+MULPLAY_PLAYER_POSITION_DELTA_X);
				player1.setY(PRESET_PLAYER_POSITION_Y);
				Player player2 = Player.getPlayer2(PRESET_PLAYER_POSITION_X-MULPLAY_PLAYER_POSITION_DELTA_X, PRESET_PLAYER_POSITION_Y);
				moveableElementUtils.add("player2", player2);
			}
			
		}
		PlayerLaunchHandler.shootSchedule();
//		ChapterLoader.loadChapter(new ChapterDemo());
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
		try {
			if(mulplay){
				clientOrServer.close();
				if(loadChapterFuture!=null){
					try {
						loadChapterFuture.get();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	private Future<?> loadChapterFuture;
	
	public Text setTextFont(String s){
		Text text = new Text(s);
		text.setFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT, new
		         Stop[]{new Stop(0, Color.RED), new Stop(0.5f, Color.BLUE)}));
		text.setFont(Font.font("黑体", FontWeight.BOLD,30));//斜体
		text.setStrokeWidth(0.5);
		text.setStroke(Color.WHITE);
		return text;
	}
	
	public void loadChapter(AChapter chapter){
		rand=new Random(C.SEED+chapter.getClass().getSimpleName().hashCode());
		loadChapterFuture = ChapterLoader.getScheduledExecutorService()
				.submit(()->{
			if(mulplay&&(mulplayServer)){
				System.out.println("waiting connection...");
				long delay=server.getDelay();//server等待连接 并获取延迟
				System.out.println("delay:"+delay+" ms");
				try {
					Thread.sleep(delay/2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ChapterLoader.loadChapter(chapter);
			}else if(mulplay&&(!mulplayServer)){
				System.out.println("waiting server pong");
				client.waitPing();
				ChapterLoader.loadChapter(chapter);
			}else{
				//single
				ChapterLoader.loadChapter(chapter);
			}
			
		});
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
//		System.out.println("gethealth");
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
		Platform.runLater(()->{
			System.out.println("failure!");
			getFailureEvent().run();
		});
		
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
	
	public void setMulplay(boolean mulplay) {
		this.mulplay = mulplay;
	}
	public void setMulplayServer(boolean mulplayServer) {
		this.mulplayServer = mulplayServer;
	}
	public NioEventLoopGroup getLoopGroup() {
		return loopGroup;
	}
	public void setLoopGroup(NioEventLoopGroup loopGroup) {
		this.loopGroup = loopGroup;
	}
	
	public void setMulplayChannel(Channel mulplayChannel) {
		this.mulplayChannel = mulplayChannel;
	}
	public Channel getMulplayChannel() {
		return mulplayChannel;
	}
	
	public BackgroundUtil getBackgroundUtil() {
		return backgroundUtil;
	}
}
