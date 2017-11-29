package magengine.element.impl;

import javafx.scene.canvas.GraphicsContext;
import magengine.paint.MyCanvas;

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
		ans[0][in[0].length - 1] = ans[0][in[0].length - 2] * 0.99;
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
	public void paint(GraphicsContext gc) {
		super.paint(gc);
		System.out.println("before last x = "+super.getPolygon().getVertices()[super.getPolygon().getVertices().length-16]);
		System.out.println("first x="+super.getPolygon().getVertices()[0]+" last x = "+super.getPolygon().getVertices()[super.getPolygon().getVertices().length-14]);
	}
}
