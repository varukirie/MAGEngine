package magengine.enemy;

import java.util.concurrent.atomic.AtomicInteger;

import magengine.element.BaseElement;
import magengine.element.Initializable;
import magengine.paint.EmBloodBar;

public abstract class AEnemy extends BaseElement implements Initializable{
	@Override
	public void onRemove() {
		super.onRemove();
		this.emBb.clear();
	}

	public AEnemy(double x, double y, double velocityX, double velocityY, double ax, double ay) {
		super(x, y, velocityX, velocityY, ax, ay);
	}

	public AEnemy(double x, double y, double velocityX, double velocityY) {
		super(x, y, velocityX, velocityY);
	}

	public AEnemy(double x, double y) {
		super(x, y);	
	}
	
	private AtomicInteger HP=new AtomicInteger(10);
	private boolean bloodState = true;
	private EmBloodBar emBb = new EmBloodBar(870,0,30,700);
	public EmBloodBar getEmBb() {
		return emBb;
	}

	public void setEmBb(EmBloodBar emBb) {
		this.emBb = emBb;
	}

	public boolean isBloodState() {
		return bloodState;
	}

	public void setBloodState(boolean bloodState) {
		this.bloodState = bloodState;
	}

	private int presetHP = 5;
	
	public AEnemy setHP(int HP){
		this.HP.set(HP);
		this.presetHP = HP;
		return this;
	}
	
	public int getHP(){
		return this.HP.get();
	}
	
	public int addAndGetHP(int delta){
		return HP.addAndGet(delta);
	}
	
	public void paintBloodBar(EmBloodBar bb){
		if(bloodState){
		bb.paint(this.getHP(),this.presetHP);
		}
	}

	
}
