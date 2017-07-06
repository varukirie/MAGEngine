package indi.megaastronic.paint;

public class PInfo {
	
	private static double playerX;
	private static double playerY;
	private static double mouseX;
	private static double mouseY;
	
	
	public static double getPlayerX() {
		return playerX;
	}


	public static void setPlayerX(double playerX) {
		PInfo.playerX = playerX;
	}


	public static double getPlayerY() {
		return playerY;
	}


	public static void setPlayerY(double playerY) {
		PInfo.playerY = playerY;
	}


	public static double getMouseX() {
		return mouseX;
	}


	public static void setMouseX(double mouseX) {
		PInfo.mouseX = mouseX;
	}


	public static double getMouseY() {
		return mouseY;
	}


	public static void setMouseY(double mouseY) {
		PInfo.mouseY = mouseY;
	}


	private PInfo(){
		
	}
}
