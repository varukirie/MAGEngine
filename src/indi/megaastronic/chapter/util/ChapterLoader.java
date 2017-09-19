package indi.megaastronic.chapter.util;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import indi.megaastronic.bullet.HiddenBullet;
import indi.megaastronic.launcher.Launcher;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.ui.SceneManager;
import indi.megaastronic.util.DI;
import indi.megaastronic.util.ElementUtils;
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
		ChapterLoader.scheduleES=Executors.newScheduledThreadPool(5);
		DI.di().put("sES", scheduleES);
	}
	public static void loadChapter(AChapter c){
		long start = System.currentTimeMillis();
		Launcher l = new Launcher(1, 1, Math.PI/2, 70, 800);
		l.setBulletType(HiddenBullet.class);
		mEU.add(new Random().nextLong()+"", l);
		c.design(scheduleES,staticCanvas,mEU);
		long end = System.currentTimeMillis();
		System.out.println("装载关卡使用 "+(end-start)+" 毫秒");
		System.gc();
	}
	
	public static void loadChapter(String chapterName) throws Exception{
		Class<?> cls = Class.forName(chapterName);
		loadChapter((AChapter) cls.newInstance());
	}

	
	public static ScheduledExecutorService getScheduledExecutorService() {
		return scheduleES;
	}

	
	

}
