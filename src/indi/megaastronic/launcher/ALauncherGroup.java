package indi.megaastronic.launcher;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.DI;
import indi.megaastronic.util.ElementUtils;

public abstract class ALauncherGroup {
	/**
	 * 两次发射之间间隔
	 */
	protected long interval = 50;
	/**
	 * 发射器的存在时间
	 */
	protected long duration = 70;

	protected ScheduledExecutorService sES = (ScheduledExecutorService) DI.di().get("sES");
	protected MyCanvas staticCanvas = (MyCanvas) DI.di().get("staticCanvas");
	protected ElementUtils mEU = (ElementUtils) DI.di().get("mEU");
	/**
	 * 发射器设置
	 */
	protected Consumer<Launcher> launcherConfig=null;
	public ScheduledExecutorService getsES() {
		return sES;
	}
	public MyCanvas getStaticCanvas() {
		return staticCanvas;
	}
	public ElementUtils getmEU() {
		return mEU;
	} 
	public abstract void execute();
	
	
	public void delayExecute(long delay){
		sES.schedule(()->{
			execute();
		}, delay, TimeUnit.MILLISECONDS);
	}

	public long getInterval() {
		return interval;
	}
	public void setInterval(long interval) {
		this.interval = interval;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	
	public Consumer<Launcher> getLauncherConfig() {
		return launcherConfig;
	}
	public void setLauncherConfig(Consumer<Launcher> launcherConfig) {
		this.launcherConfig = launcherConfig;
	}

	protected void configLauncher(Launcher launcher){
		if(launcherConfig!=null) 
			launcherConfig.accept(launcher);
	}

}
