package indi.megaastronic.danmuku;

import java.util.UUID;

import indi.megaastronic.launcher.BulletEvent;
import indi.megaastronic.launcher.Launcher;

public class OvalGroup extends AGroupLauncher {

	private double midX=200;
	private double midY=200;
	private double amount=32;

	
	
	public OvalGroup(double midX, double midY) {
		super();
		this.midX = midX;
		this.midY = midY;
	}


	@Override
	public void execute() {

		for(int i=0;i<amount;i++){
			Launcher launcher = new Launcher(midX, midY, 2*Math.PI/amount*i, interval, duration);
			configLauncher(launcher);
			mEU.add(UUID.randomUUID().toString(), launcher);
		}
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


	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
