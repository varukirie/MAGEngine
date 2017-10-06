package magengine.control;

import magengine.paint.MoveHandler;
import magengine.util.DI;

public class PlayerLaunchHandler implements Runnable {

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		MoveHandler mh= (MoveHandler) DI.di().get("mh");
		while(mh.keepRun){
			if(PlayerControlHandler.getExistedPlayerControlHandler().playerLauncher==true){
				PlayerControlHandler.getExistedPlayerControlHandler().playerShooting();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
