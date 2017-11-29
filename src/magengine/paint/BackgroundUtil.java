package magengine.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackgroundUtil {

	private Image bgimg = null;
	public void setBackgroundImg(Image img){
		bgimg=img;
	}
	private int pxmark=0;
	private MyCanvas bgCanvas = new MyCanvas();
	
	public void  paintBackground(int pxdelta){
		if(bgimg!=null){
			int totolHeight = (int) bgimg.getHeight();
			int width = MyCanvas.CANVAS_WIDTH;
			int height = MyCanvas.CANVAS_HEIGHT;
			GraphicsContext gc = bgCanvas.getGraphicsContext2D();
			gc.drawImage(bgimg, 0, totolHeight-height-pxmark, width, height, 0, 0, width, height);
			pxmark=(pxmark+pxdelta)%(totolHeight-height);
		}
	}
	public MyCanvas getBGCanvas() {
		return bgCanvas;
	}
}
