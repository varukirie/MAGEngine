package application;
	
import indi.megaastronic.MyCanvas;
import indi.megaastronic.control.PlayerKBControlHandler;
import indi.megaastronic.object.Enemy;
import indi.megaastronic.object.Player;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.util.ObjectUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = new StackPane();
			MyCanvas canvas = new MyCanvas();
			root.getChildren().add(canvas);
			Scene scene=new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Canvas Demo!");
			primaryStage.show();
			
			//运行 线程MoveHandle
			MoveHandler mh = new MoveHandler(canvas);
			new Thread(mh).start();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					mh.keepRun=false;
				}
			});
			//
			Player player = new Player(10,10);

			//
			new PlayerKBControlHandler().bindEvent(scene, player);
			//
			canvas.getWantPaintMap().put("player", player);//让MyCanvas管理player
			mh.getWantMoveMap().put("player", player);//让MoveHandler管理player
			//
			ObjectUtils ou = new ObjectUtils(mh, canvas);
			Enemy enemy = new Enemy(50, 50);
			enemy.setVelocityX(1.5);
			enemy.setVelocityY(1.12);
			ou.putObject("enemy1", enemy);
			//
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
