package indi.megaastronic.control;

import indi.megaastronic.paint.Player;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class PlayerKBControlHandler {
	//速度V
	public static final int V=2;
	private Player player = null;
	
	public PlayerKBControlHandler() {
	}
	
	public void bindEvent(Scene scene,Player player){
		this.player= player;
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				//System.out.println("tri");
				pressHandle(e);
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				releaseHandle(e);
			}
		});
	}
	
	//顺序是上、右、下、左
	private boolean[] pressed = new boolean[]{false,false,false,false};
	@SuppressWarnings("incomplete-switch")
	public void pressHandle(KeyEvent e) {
		
		switch (e.getCode()) {
		case UP:
			player.setVelocityY(-V);
			pressed[0]=true;
			break;
		case RIGHT:
			player.setVelocityX(V);
			pressed[1]=true;
			break;
		case DOWN:
			player.setVelocityY(V);
			pressed[2]=true;
			break;
		case LEFT:
			player.setVelocityX(-V);
			pressed[3]=true;
			break;
		}
	}
	@SuppressWarnings("incomplete-switch")
	public void releaseHandle(KeyEvent e){
		switch (e.getCode()) {
		case UP:
			if(pressed[2]==false)
				player.setVelocityY(0);
			else 
				player.setVelocityY(V);
			pressed[0]=false;
			break;
		case RIGHT:
			if(pressed[3]!=true)
				player.setVelocityX(0);
			else
				player.setVelocityX(-V);
			
			pressed[1]=false;
			break;
		case DOWN:
			if(pressed[0]!=true)
				player.setVelocityY(0);
			else
				player.setVelocityY(-V);
				
			pressed[2]=false;
			break;
		case LEFT:
			if(pressed[1]!=true)
				player.setVelocityX(0);
			else
				player.setVelocityX(V);
			pressed[3]=false;
			break;
		}
	}
}
