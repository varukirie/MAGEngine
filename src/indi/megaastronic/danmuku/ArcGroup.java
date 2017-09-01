package indi.megaastronic.danmuku;

import java.util.UUID;

import indi.megaastronic.launcher.Launcher;

public class ArcGroup extends AGroupLauncher {

	private double direction = Math.PI / 2;
	private double angle = Math.PI/3;
	private int amount = 10;
	private double midX = 200;
	private double midY = 200;

	
	public ArcGroup(double midX, double midY) {
		super();
		this.midX = midX;
		this.midY = midY;
	}
	


	public ArcGroup(double midX, double midY,double direction, double angle, int amount) {
		super();
		this.direction = direction;
		this.angle = angle;
		this.amount = amount;
		this.midX = midX;
		this.midY = midY;
	}



	@Override
	public void execute() {
		for (int i = 0; i < amount; i++) {
			Launcher launcher = new Launcher(midX, midY, (direction - angle / 2) + i * (angle / (amount - 1)),interval, duration);
			configLauncher(launcher);
			mEU.add(UUID.randomUUID().toString(), launcher);
		}
	}
	
}
