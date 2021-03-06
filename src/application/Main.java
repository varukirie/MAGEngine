package application;

import javafx.application.Application;
import javafx.stage.Stage;
import magengine.ui.SceneManager;


public class Main extends Application {
	public static final boolean DEBUG = false;
	public static boolean DEBUG_NO_FAILURE = false;
	public static final boolean DEBUG_ElementCreate = false;
	public static final boolean DEBUG_LOGIC_BENCH=false;
	public static final boolean DEBUG_RENDER_BENCH=false;
	public static final boolean DEBUG_COLLISION=false;
	public static final boolean ACC_ENABLE=true;
	public static final boolean DEBUG_COLLISION_AREA=false;

	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.init(primaryStage);
			SceneManager.getInstance().startMenu();
//			SceneManager.getInstance().loadMulplaySelectScene();
			primaryStage.show();
//			SceneManager.shutdownGame();
//			SceneManager.startGame(primaryStage);	
//			ScheduledExecutorService ses = (ScheduledExecutorService) DI.di().get("sES");
//			ses.schedule(()->{
//				SceneManager.shutdownGame();
//				SceneManager.startGame(primaryStage);
//			}, 3000, TimeUnit.MILLISECONDS);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
