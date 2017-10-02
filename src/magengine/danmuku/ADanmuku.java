package magengine.danmuku;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import magengine.element.BaseElement;
import magengine.util.DI;
import magengine.util.ElementUtils;

public abstract class ADanmuku {
	protected BaseElement sourceElement= null;
	private long danmukuDuration = 0;
	private long delay =0;
	public abstract void executeDanmuku() ;
	private ElementUtils mEU = (ElementUtils) DI.di().get("mEU");
	private ScheduledExecutorService sES = (ScheduledExecutorService) DI.di().get("sES");
	public ADanmuku(BaseElement sourceElement){
		this.sourceElement=sourceElement;
	}
	
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
	public long getDanmukuDuration() {
		return danmukuDuration;
	}
	public void setDanmukuDuration(long danmukuDuration) {
		this.danmukuDuration = danmukuDuration;
	}
	public void delayExecute(){
		sES.schedule(() -> {
			this.executeDanmuku();
		}, getDelay(), TimeUnit.MILLISECONDS);
	}

	
}
