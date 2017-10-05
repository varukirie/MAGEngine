package magengine.danmuku;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import magengine.element.BaseElement;
import magengine.util.DI;
import magengine.util.ElementUtils;

public abstract class ADanmuku {
	/**
	 * 弹幕的主人，指的是存放该弹幕的AEnemy
	 * 如果通过AEnemy来管理弹幕的会自动设置上去
	 */
	protected BaseElement sourceElement= null;
	/**
	 * 弹幕的持续时间，可以为空
	 */
	private long danmukuDuration = 0;
	/**
	 * 执行延迟。如果使用AEnemy管理，那么是从敌人出现时开始计时
	 */
	private long delay =0;
	/**
	 * 描述弹幕
	 */
	public abstract void executeDanmuku() ;
	private ElementUtils mEU = (ElementUtils) DI.di().get("mEU");
	private ScheduledExecutorService sES = (ScheduledExecutorService) DI.di().get("sES");

	public void setSourceElement(BaseElement sourceElement) {
		this.sourceElement = sourceElement;
	}
	
	public BaseElement getSourceElement() {
		return sourceElement;
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
	/**
	 * 在delay毫秒后 调用executeDanmuku()
	 */
	public void delayExecute(){
		sES.schedule(() -> {
			this.executeDanmuku();
		}, getDelay(), TimeUnit.MILLISECONDS);
	}

	
}
