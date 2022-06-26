package final_project;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class brick{
	private boolean destroyed;
	private int HP;
	public Rectangle brick = new Rectangle();
	public brick(double x,double y) {
		HP=3;
		brick.setLayoutX(x);
		brick.setLayoutY(y);
		brick.setHeight(originElements.brickHeight);
		brick.setWidth(originElements.brickWidth);
		brick.setFill(Color.BROWN);
		destroyed=false;
	}
	public boolean isDestroyed() {
		return destroyed;
	}
	public void setDestroyed(boolean val) {
		destroyed=val;
	}
	public double getX() {
		return brick.getLayoutX();
	}
	public double getY() {
		return brick.getLayoutY();
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int HP) {
		this.HP=HP;
	}
}
