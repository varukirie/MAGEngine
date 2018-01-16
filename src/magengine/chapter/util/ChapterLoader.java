package magengine.chapter.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.HiddenBullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.bullet.impl.StarBullet;
import magengine.element.InitBeforeLoadChapter;
import magengine.element.impl.BombCircleArea;
import magengine.game.LogicExecutor;
import magengine.game.MoveHandler;
import magengine.launcher.Launcher;
import magengine.paint.MyCanvas;
import magengine.paint.MyCanvasSwitcher;
import magengine.util.DI;
import magengine.util.ElementUtils;
import magengine.util.PackageScan;
import magengine.util.SoundUtil;
/**
 * 此类负责装载游戏关卡
 * 此类不可实例化
 * @author Astronic
 *
 */
public class ChapterLoader {
	private static MyCanvas staticCanvas =null;
	private static ElementUtils mEU = null;
	private ChapterLoader(){
		
	}
	
	private static ScheduledExecutorService scheduleES=null;
	
	public static void init(MyCanvas staticCanvas){
		ChapterLoader.staticCanvas = staticCanvas ;
		ChapterLoader.mEU = (ElementUtils) DI.di().get("mEU");
		ChapterLoader.scheduleES=Executors.newScheduledThreadPool(4);
		DI.di().put("sES", scheduleES);
		loadMusicResource();
	}
	public static void loadChapter(AChapter c){
		MoveHandler.setDeltaTimeFactor(0);
		long start = System.currentTimeMillis();
		Launcher l = new Launcher(1, 1, Math.PI/2, 70, 800);
		l.setBulletType(HiddenBullet.class);
		mEU.add(new Random().nextLong()+"", l);
		c.design(LogicExecutor.getLogicExecutor(),staticCanvas,mEU);
		long end = System.currentTimeMillis();
		System.out.println("装载关卡使用 "+(end-start)+" 毫秒");
		MoveHandler.setDeltaTimeFactor(MoveHandler.PRESET_DELTA_TIME_FACTOR);
		
		scanPackage4CanvasConfig();
		Platform.runLater(() -> {
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(StarBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(ArrowBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(PlayerBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(HexagonBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(BombCircleArea.class, (canvas) -> {
				canvas.setEffect(new BoxBlur(5, 5, 1));
//				canvas.setEffect(new Bloom(1));
			});
//			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		});
		
		
	}
	
	public static void loadChapter(String chapterName) throws Exception{
		Class<?> cls = Class.forName(chapterName);
		loadChapter((AChapter) cls.newInstance());
	}

	
	public static ScheduledExecutorService getScheduledExecutorService() {
		return scheduleES;
	}

	private static void scanPackage4CanvasConfig(){
		PackageScan.doScan(InitBeforeLoadChapter.class,"magengine").forEach((cls)->{
			if(!cls.isInterface()){
				try {
					cls.getMethod("initWhenChapterLoad", new Class[0]).invoke(cls.newInstance(), new Object[0]);
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private static void loadMusicResource(){
		SoundUtil.getInstance().loadResource("glass", ChapterLoader.class.getResource("/music/glass.wav").toString());
		SoundUtil.getInstance().loadResource("hit", ChapterLoader.class.getResource("/music/hit.wav").toString());
	}
	
	
	
	

}
