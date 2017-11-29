package magengine.launcher;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import magengine.element.BaseElement;
import magengine.game.LogicExecutor;
import magengine.paint.MyCanvas;
import magengine.util.DI;
import magengine.util.ElementUtils;

public abstract class ALauncherGroup {
	/**
	 * 两次发射之间间隔
	 */
	protected long interval = 20;
	/**
	 * 发射器的存在时间
	 */
	protected long duration = 20;

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
		LogicExecutor.getLogicExecutor().schedule(()->{
			execute();
		}, delay, TimeUnit.MILLISECONDS);
	}
	public void delayExecuteCheck(long delay,BaseElement sourceElement){
		LogicExecutor.getLogicExecutor().schedule(()->{
			if(!sourceElement.getDeleted()){
				execute();
			}
		}, delay, TimeUnit.MILLISECONDS);
	}
	
	public void delayExecuteCheckAndWith(long delay,BaseElement sourceElement,Runnable task){
		LogicExecutor.getLogicExecutor().schedule(()->{
			if(!sourceElement.getDeleted()){
				if(task!=null)
					task.run();
				execute();
			}
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
	/**
	 * 
	 * @param launcherConfig
	 * @return 返回自身
	 */
	public ALauncherGroup setLauncherConfig(Consumer<Launcher> launcherConfig) {
		this.launcherConfig = launcherConfig;
		return this;
	}

	protected void configLauncher(Launcher launcher){
		if(launcherConfig!=null) 
			launcherConfig.accept(launcher);
	}

}
