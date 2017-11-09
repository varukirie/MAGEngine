package magengine.element.impl;

import java.util.Arrays;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magengine.paint.MyCanvas;
import magengine.util.Transform;
//TODO 
public class InvertCircleArea extends CircleArea {

	public InvertCircleArea(double x, double y, double r) {
		super(x, y, r);
	}

	@Override
	protected double[][] getOrigin() {
		double[][] in = super.getOrigin();
		double[][] ans = new double[2][in[0].length + 6];
		for (int i = 0; i < in[0].length; i++) {
			ans[0][i] = in[0][i];
			ans[1][i] = in[1][i];
		}
		ans[0][in[0].length - 1] = ans[0][in[0].length - 2] * 0.2;
		int t = in[0].length;
		ans[0][t] = ans[0][t - 1];
		ans[1][t] = ans[1][t] - MyCanvas.CANVAS_HEIGHT;
		t++;
		ans[0][t] = ans[0][t - 1] + MyCanvas.CANVAS_WIDTH;
		ans[1][t] = ans[1][t - 1];
		t++;
		ans[0][t] = ans[0][t - 1];
		ans[1][t] = ans[1][t - 1] + 2 * MyCanvas.CANVAS_HEIGHT;
		t++;
		ans[0][t] = ans[0][t - 1] - 2 * MyCanvas.CANVAS_WIDTH;
		ans[1][t] = ans[1][t - 1];
		t++;
		ans[0][t] = ans[0][t - 1];
		ans[1][t] = ans[1][t - 1] - 2 * MyCanvas.CANVAS_HEIGHT;
		t++;
		ans[0][t] = ans[0][0];
		ans[1][t] = ans[1][0] - MyCanvas.CANVAS_HEIGHT;
		return ans;
	}
	
	
	@Override
	protected double[][] transformVAndScaleAndDelta(double[][] origin){
		//do not transformV
		double[][] ans= Transform.martixInTransform(scaleMartix, origin);
		Transform.delta(ans, getX(), getY());
		return ans;
	}
	@Override
	public void paint(GraphicsContext gc) {
		double[][] ans=handleCollision();
		if(Main.DEBUG_COLLISION_AREA){
			gc.setFill(Color.rgb(230, 50, 50, 0.3));
			gc.fillPolygon(ans[0],ans[1], getOrigin()[0].length);
		}
		System.out.println(Arrays.toString(super.getPolygon().getVertices()));
	}
}
