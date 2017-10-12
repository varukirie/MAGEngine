package magengine.game;

public enum Level {
	EASY(0.7),NORMAL(1),HARD(1.5);
	private double levelFactor ;
	private Level(double levelFactor){
		this.levelFactor=levelFactor;
	}
	public double getFactor() {
		return this.levelFactor;
	}
}
