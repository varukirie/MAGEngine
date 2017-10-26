package magengine.launcher;

import java.util.UUID;

public class OvalLauncherGroup extends ALauncherGroup {

	private double midX=200;
	private double midY=200;
	private int amount=32;
	private double startAngle = 0;
	
	
	public OvalLauncherGroup(double midX, double midY) {
		super();
		this.midX = midX;
		this.midY = midY;
	}
	public OvalLauncherGroup(double midX, double midY,int amount) {
		super();
		this.midX = midX;
		this.midY = midY;
		setAmount(amount);
	}
	public OvalLauncherGroup(double midX, double midY,int amount,double startAngle) {
		this(midX,midY,amount);
		setStartAngle(startAngle);
	}

	@Override
	public void execute() {
		ArcLauncherGroup alg=new ArcLauncherGroup(midX, midY, startAngle, Math.PI*2-(Math.PI*2/amount), amount);
		alg.setLauncherConfig(this.getLauncherConfig());
		alg.execute();
	}


	public double getMidX() {
		return midX;
	}


	public void setMidX(double midX) {
		this.midX = midX;
	}


	public double getMidY() {
		return midY;
	}


	public void setMidY(double midY) {
		this.midY = midY;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getStartAngle() {
		return startAngle;
	}
	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}
	
}
