package magengine.chapter.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.DefaultBullet;
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
		MoveHandler.setDeltaTimeFactor(1);
		
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
				canvas.setEffect(new BoxBlur(2.5, 2.5, 1));
//				canvas.setEffect(new Bloom(1));
			});
			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
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
		File root = new File(ChapterLoader.class.getResource("/").getPath());
		String prefix = "magengine";
		scanClass(root, Arrays.asList(root),prefix, (cls)->{
			boolean initBeforeLoad = Arrays.asList(cls.getInterfaces()).contains(InitBeforeLoadChapter.class);
			if(initBeforeLoad){
				try {
					cls.getMethod("initWhenChapterLoad", new Class[0]).invoke(cls.newInstance(), new Object[0]);
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private static void scanClass(File root,List<File> files,String prefix,Consumer<Class<?>> run){
		files.stream().filter(File::isFile).forEach(e->{
			String rPath = e.getAbsolutePath().substring(root.getAbsolutePath().length()+1);
			rPath = rPath.replace('\\', '.');
			if(rPath.contains(".class")){
				String className = rPath.substring(0, rPath.length()-6);
				try {
					Class<?> cls = Class.forName(className);
					run.accept(cls);
				} catch (ClassNotFoundException  e1) {
					e1.printStackTrace();
				}
			}
		});
		files.stream().filter(File::isDirectory).forEach(e->{
			scanClass(root, Arrays.asList(e.listFiles()),prefix, run);
		});
	}
	
	

}
