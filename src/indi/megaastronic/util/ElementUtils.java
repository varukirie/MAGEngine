package indi.megaastronic.util;

import indi.megaastronic.element.Moveable;
import indi.megaastronic.element.Paintable;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.MyCanvas;

public class ElementUtils {
	private MoveHandler mh = null;
	private MyCanvas myCanvas =null;
	public ElementUtils(MoveHandler mh, MyCanvas myCanvas) {
		super();
		this.mh = mh;
		this.myCanvas = myCanvas;
	}

	
	public void add(String key,Object value){
		if(mh!=null)
			mh.getWantMoveMap().put(key, (Moveable) value);
		if(myCanvas!=null)
			myCanvas.getWantPaintMap().put(key, (Paintable) value);
	}
	
}
