package application;
	
import indi.megaastronic.MyCanvas;
import indi.megaastronic.control.PlayerKBControlHandler;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.Player;
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
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
