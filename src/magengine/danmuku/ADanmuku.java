package magengine.danmuku;

import java.util.concurrent.ScheduledExecutorService;

import magengine.util.DI;
import magengine.util.ElementUtils;

public abstract class ADanmuku {
	private long delay =0;
	public abstract void executeDanmuku() ;
	private ElementUtils mEU = (ElementUtils) DI.di().get("mEU");
	private ScheduledExecutorService sES = (ScheduledExecutorService) DI.di().get("sES");
	protected  ElementUtils getmEU(){
		return this.mEU;
	}
	protected  ScheduledExecutorService getsES(){
		return this.sES;
	}
	public long getDelay() {
		return delay;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	

	
}
