package indi.megaastronic.helper;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

import indi.megaastronic.chapter.util.QuickDanmuku;
import indi.megaastronic.element.Initializable;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.util.DI;
import indi.megaastronic.util.ElementUtils;

public class PloygonalLineHelper extends Helper implements Initializable{
	public PloygonalLineHelper(double[][] ploygonalLine,double speed){
		super(1, 1);
		setPloygon(ploygonalLine);
		setSpeed(speed);
	}
	
	private double speed=1;
	private double[][] ploygon = null;
	private ScheduledExecutorService ses = (ScheduledExecutorService) DI.di().get("sES");
	private QuickDanmuku quick=QuickDanmuku.getQuickDanmuku();
	private Consumer<PloygonalLineHelper> endEvent = null;
	
	
	@Override
	public void initWhenAdd() {
		this.setX(this.ploygon[0][0]);
		this.setY(this.ploygon[1][0]);
		this.setVelocityX(getSpeed());
		double lastX=this.ploygon[0][0];
		double lastY=this.ploygon[1][0];
		long nextChangeTime=1;

		for(int i=1;i<this.ploygon[0].length;i++){
			int curI=i;
			double s = Math.sqrt((this.ploygon[0][i]-this.ploygon[0][i-1])*(this.ploygon[0][i]-this.ploygon[0][i-1])+
						(this.ploygon[1][i]-this.ploygon[1][i-1])*(this.ploygon[1][i]-this.ploygon[1][i-1]));
			long duration = (long) ((s/this.speed)*(1/MoveHandler.timeSpeed));
//			System.out.println("i="+i+" s="+s+" duration="+duration);
			ses.schedule(()->{
				quick.VTo(this, ploygon[0][curI], ploygon[1][curI]);
//				System.out.println("call "+curI+" this.x="+this.getX()+" this.y="+this.getY());
			}, nextChangeTime, TimeUnit.MILLISECONDS);
			nextChangeTime+=duration;
		}
		this.setDuration(nextChangeTime);
		ses.schedule(()->{
			if(getEndEvent()!=null){
				getEndEvent().accept(this);
			}
		}, nextChangeTime, TimeUnit.MILLISECONDS);
	}
	public double[][] getPloygon() {
		return ploygon;
	}
	public void setPloygon(double[][] ploygon) {
		//TODO  ploygon验证数据正确性
		this.ploygon = ploygon;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Consumer<PloygonalLineHelper> getEndEvent() {
		return endEvent;
	}
	public void setEndEvent(Consumer<PloygonalLineHelper> endEvent) {
		this.endEvent = endEvent;
	}
	
	
	
	
}
