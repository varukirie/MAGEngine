package magengine.chapter.util;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import magengine.bullet.HiddenBullet;
import magengine.game.LogicExecutor;
import magengine.launcher.Launcher;
import magengine.paint.MyCanvas;
import magengine.ui.SceneManager;
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
		ChapterLoader.scheduleES=Executors.newScheduledThreadPool(1);
		DI.di().put("sES", scheduleES);
	}
	public static void loadChapter(AChapter c){
		long start = LogicExecutor.gameTime();
		Launcher l = new Launcher(1, 1, Math.PI/2, 70, 800);
		l.setBulletType(HiddenBullet.class);
		mEU.add(new Random().nextLong()+"", l);
		c.design(scheduleES,staticCanvas,mEU);
		long end = LogicExecutor.gameTime();
		System.gc();
		System.out.println("装载关卡使用 "+(end-start)+" 毫秒");
	}
	
	public static void loadChapter(String chapterName) throws Exception{
		Class<?> cls = Class.forName(chapterName);
		loadChapter((AChapter) cls.newInstance());
	}

	
	public static ScheduledExecutorService getScheduledExecutorService() {
		return scheduleES;
	}

	
	

}
