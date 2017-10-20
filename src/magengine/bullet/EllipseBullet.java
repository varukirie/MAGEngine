package magengine.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 画菱形子弹 继承多边形
 * 
 * 
 */
public class EllipseBullet extends APolygonBullet {

	public EllipseBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public EllipseBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
		// TODO Auto-generated constructor stub
	}

	public EllipseBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public EllipseBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
		// TODO Auto-generated constructor stub
	}

	public EllipseBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	public EllipseBullet(double x, double y) {
		super(x, y); // 中心坐标
		// TODO Auto-generated constructor stub
	}

	public static final double[][] origin = new double[][] {

			{

					0, 1, 1.1, 1.15, 1.2, 1.25, 1.3, 1.35, 1.4, 1.45, 1.5, 1.55, 1.6, 1.65, 1.7, 1.75, 1.8, 1.85, 1.9,
					1.95, 2, 2.1, 2.15, 2.2, 2.25, 2.3, 2.35, 2.4, 2.4, 2.5, 2.55, 2.6, 2.65, 2.7, 2.75, 2.8, 2.85, 2.9,
					2.95, 3, 3.1, 3.15, 3.2, 3.25, 3.3, 3.35, 3.4, 3.45, 3.5, 3.55, 3.6, 3.65, 3.7, 3.75, 3.8, 3.85,
					3.9, 3.95, 4, 4.1, 4.15, 4.2, 4.25, 4.3, 4.35, 4.4, 4.45, 4.5, 4.55, 4.6, 4.65, 4.7, 4.75, 4.8,
					4.85, 4.9, 4.95, 5,
					
					-0, 1, -1.1, -1.15, -1.2, -1.25, -1.3, -1.35, -1.4, -1.45, -1.5, -1.55, -1.6,-1.65, -1.7, -1.75, -1.8, -1.85, -1.9,
					-1.95, -2, -2.1,-2.15, -2.2, -2.25, -2.3, -2.35, -2.4, -2.4, -2.5, -2.55, -2.6, -2.65, -2.7, -2.75, -2.8, -2.85, -2.9,
					-2.95, -3, -3.1, -3.15, -3.2, -3.25, -3.3, -3.35, -3.4, -3.45, -3.5, -3.55, -3.6, -3.65, -3.7, -3.75, -3.8, -3.85,
					-3.9, -3.95, -4, -4.1, -4.15, -4.2, -4.25, -4.3, -4.35, -4.4,-4.45, -4.5, -4.55, -4.6, -4.65, -4.7, -4.75, -4.8,
					-4.85, -4.9, -4.95, -5,
					
					-0, 1, -1.1, -1.15, -1.2, -1.25, -1.3, -1.35, -1.4, -1.45, -1.5, -1.55, -1.6,-1.65, -1.7, -1.75, -1.8, -1.85, -1.9,
					-1.95, -2, -2.1,-2.15, -2.2, -2.25, -2.3, -2.35, -2.4, -2.4, -2.5, -2.55, -2.6, -2.65, -2.7, -2.75, -2.8, -2.85, -2.9,
					-2.95, -3, -3.1, -3.15, -3.2, -3.25, -3.3, -3.35, -3.4, -3.45, -3.5, -3.55, -3.6, -3.65, -3.7, -3.75, -3.8, -3.85,
					-3.9, -3.95, -4, -4.1, -4.15, -4.2, -4.25, -4.3, -4.35, -4.4,-4.45, -4.5, -4.55, -4.6, -4.65, -4.7, -4.75, -4.8,
					-4.85, -4.9, -4.95, -5,
					
					0, 1, 1.1, 1.15, 1.2, 1.25, 1.3, 1.35, 1.4, 1.45, 1.5, 1.55, 1.6, 1.65, 1.7, 1.75, 1.8, 1.85, 1.9,
					1.95, 2, 2.1, 2.15, 2.2, 2.25, 2.3, 2.35, 2.4, 2.4, 2.5, 2.55, 2.6, 2.65, 2.7, 2.75, 2.8, 2.85, 2.9,
					2.95, 3, 3.1, 3.15, 3.2, 3.25, 3.3, 3.35, 3.4, 3.45, 3.5, 3.55, 3.6, 3.65, 3.7, 3.75, 3.8, 3.85,
					3.9, 3.95, 4, 4.1, 4.15, 4.2, 4.25, 4.3, 4.35, 4.4, 4.45, 4.5, 4.55, 4.6, 4.65, 4.7, 4.75, 4.8,
					4.85, 4.9, 4.95, 5
					
					

		
			
			},
			// {0,-2,-9,-4,-6,0,6,4,9,3},{-9,-3,-3,1,8,4,8,1,-3,-3}};
			{ 
					20, 19.59591794, 19.50999744, 19.46381258, 19.41545776, 19.36491673, 19.31217233, 19.25720644, 19.2,
					19.14053291, 19.07878403, 19.01473113, 18.94835085, 18.87961864, 18.80850871, 18.734994,
					18.65904606, 18.58063508, 18.49972973, 18.41629713, 18.33030278, 18.15048209, 18.05657775,
					17.95995546, 17.8605711, 17.7583783, 17.6533283, 17.54536976, 17.54536976, 17.32050808, 17.20348802,
					17.0833252, 16.95995283, 16.83330033, 16.70329309, 16.56985214, 16.43289384, 16.29232948,
					16.1480649, 16, 15.6920362, 15.53190265, 15.36749817, 15.19868415, 15.02531198, 14.84722196,
					14.66424222, 14.47618734, 14.28285686, 14.08403351, 13.87948126, 13.6689429, 13.45213738,
					13.22875656, 12.99846145, 12.76087771, 12.51559028, 12.26213684, 12, 11.44727042, 11.15526781,
					10.85172797, 10.53565375, 10.20588066, 9.861034428, 9.49947367, 9.119210492, 8.717797887,
					8.292164977, 7.838367177, 7.35119038, 6.823488844, 6.244997998, 5.6, 4.862098312, 3.979949748,
					2.821347196, 0,
					
					20, 19.59591794, 19.50999744, 19.46381258, 19.41545776, 19.36491673, 19.31217233, 19.25720644, 19.2,
					19.14053291, 19.07878403, 19.01473113, 18.94835085, 18.87961864, 18.80850871, 18.734994,
					18.65904606, 18.58063508, 18.49972973, 18.41629713, 18.33030278, 18.15048209, 18.05657775,
					17.95995546, 17.8605711, 17.7583783, 17.6533283, 17.54536976, 17.54536976, 17.32050808, 17.20348802,
					17.0833252, 16.95995283, 16.83330033, 16.70329309, 16.56985214, 16.43289384, 16.29232948,
					16.1480649, 16, 15.6920362, 15.53190265, 15.36749817, 15.19868415, 15.02531198, 14.84722196,
					14.66424222, 14.47618734, 14.28285686, 14.08403351, 13.87948126, 13.6689429, 13.45213738,
					13.22875656, 12.99846145, 12.76087771, 12.51559028, 12.26213684, 12, 11.44727042, 11.15526781,
					10.85172797, 10.53565375, 10.20588066, 9.861034428, 9.49947367, 9.119210492, 8.717797887,
					8.292164977, 7.838367177, 7.35119038, 6.823488844, 6.244997998, 5.6, 4.862098312, 3.979949748,
					2.821347196, 0,
					
					
					-20, 19.59591794, -19.50999744,          -19.46381258, -19.41545776, -19.36491673, -19.31217233, -19.25720644, -19.2,
					-19.14053291, -19.07878403, -19.01473113, -18.94835085, -18.87961864, -18.80850871, -18.734994,
					-18.65904606, -18.58063508, -18.49972973, -18.41629713, -18.33030278, -18.15048209, -18.05657775,
					-17.95995546, -17.8605711,  -17.7583783,  -17.6533283, -17.54536976, -17.54536976, -17.32050808, -17.20348802,
					-17.0833252, -16.95995283,  -16.83330033, -16.70329309, -16.56985214, -16.43289384, -16.29232948,
					-16.1480649,   -16,         -15.6920362,   -15.53190265, -15.36749817, -15.19868415, -15.02531198, -14.84722196,
					-14.66424222, -14.47618734, -14.28285686,  -14.08403351, -13.87948126, -13.6689429, -13.45213738,
					-13.22875656, -12.99846145, -12.76087771,  -12.51559028, -12.26213684, -12, 11.44727042, -11.15526781,
					-10.85172797, -10.53565375, -10.20588066,  -9.861034428, -9.49947367, -9.119210492, -8.717797887,
					-8.292164977, -7.838367177, -7.35119038,   -6.823488844, -6.244997998, -5.6, -4.862098312, -3.979949748,
					-2.821347196, 0,

					-20, 19.59591794, -19.50999744,          -19.46381258, -19.41545776, -19.36491673, -19.31217233, -19.25720644, -19.2,
					-19.14053291, -19.07878403, -19.01473113, -18.94835085, -18.87961864, -18.80850871, -18.734994,
					-18.65904606, -18.58063508, -18.49972973, -18.41629713, -18.33030278, -18.15048209, -18.05657775,
					-17.95995546, -17.8605711,  -17.7583783,  -17.6533283, -17.54536976, -17.54536976, -17.32050808, -17.20348802,
					-17.0833252, -16.95995283,  -16.83330033, -16.70329309, -16.56985214, -16.43289384, -16.29232948,
					-16.1480649,   -16,         -15.6920362,   -15.53190265, -15.36749817, -15.19868415, -15.02531198, -14.84722196,
					-14.66424222, -14.47618734, -14.28285686,  -14.08403351, -13.87948126, -13.6689429, -13.45213738,
					-13.22875656, -12.99846145, -12.76087771,  -12.51559028, -12.26213684, -12, 11.44727042, -11.15526781,
					-10.85172797, -10.53565375, -10.20588066,  -9.861034428, -9.49947367, -9.119210492, -8.717797887,
					-8.292164977, -7.838367177, -7.35119038,   -6.823488844, -6.244997998, -5.6, -4.862098312, -3.979949748,
					-2.821347196, 0
					
					

					
					
			} };

	@Override
	protected double[][] getOrigin() {
		return origin;
	}

	public void paint(GraphicsContext gc) {
		gc.setFill(Color.LIGHTGOLDENRODYELLOW);
		super.paint(gc);
	}

}
