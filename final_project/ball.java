package final_project;

import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ball{
	ArrayList<Timeline> effectTime=new ArrayList<>();
	private double radius;
	private double dirX;
	private double dirY;
	public Circle ball=new Circle();
	public ball() {
		dirX=0.06;
		dirY=-0.06;
		radius=originElements.radius;
		ball.setLayoutX(originElements.initBallX);
		ball.setLayoutY(originElements.initBallY);
		ball.setRadius(10);
		ball.setFill(Color.BLUE);
	}
	//Bar §PÂ_¦h¥[2(barHeight) , ¨ä¥Lª«¥ó»~®t§PÂ_2
	public void ballMove(double posi, double speed,double times,double duration) {
		ball.setLayoutX(ball.getLayoutX()+dirX*times*duration);
		ball.setLayoutY(ball.getLayoutY()+dirY*times*duration);
//		¤WÀð¾À
		if(ball.getLayoutY()-radius>=0&&ball.getLayoutY()-radius<2&&getDirY()<0) {
			setDirY(-dirY);
			musicPlayer.ballSound();
		}
//		Bar
		if(ball.getLayoutY()+radius<=originElements.barY&&
				ball.getLayoutY()+radius>=originElements.barY-originElements.barHeight/2-3&&
				Math.abs(ball.getLayoutX()-posi)<=originElements.barLength/2+3&&
				dirY>0) {
			setDirX(Math.abs(getDirX())<3*originElements.initBallDirX? getDirX()+speed*originElements.fk:getDirX());
			setDirY(-dirY);
			musicPlayer.ballSound();
		}
//		Bar¥ª¶ê©·
		else if(Math.abs(ball.getLayoutY()-originElements.barY)<=2+originElements.barHeight/2+radius&&
				ball.getLayoutX()<=posi-originElements.barLength/2&&
				Math.sqrt(Math.pow(ball.getLayoutX()-posi+originElements.barLength/2,2)+Math.pow(ball.getLayoutY()-originElements.barY,2))<=radius+originElements.barHeight+2&&
				Math.sqrt(Math.pow(ball.getLayoutX()-posi+originElements.barLength/2,2)+Math.pow(ball.getLayoutY()-originElements.barY,2))>=radius+originElements.barHeight&&
				dirX>0) {
			speedTransformer(posi-originElements.barLength/2);
			setDirX(Math.abs(getDirX())<3*originElements.initBallDirX? getDirX()+speed*originElements.fk:getDirX());
			musicPlayer.ballSound();
		}
//		Bar¥k¶ê©·
		else if(Math.abs(ball.getLayoutY()-originElements.barY)<=2+originElements.barHeight/2+radius&&
				ball.getLayoutX()>=posi+originElements.barLength/2&&
				Math.sqrt(Math.pow(ball.getLayoutX()-posi-originElements.barLength/2,2)+Math.pow(ball.getLayoutY()-originElements.barY,2))<=radius+originElements.barHeight+2&&
				Math.sqrt(Math.pow(ball.getLayoutX()-posi-originElements.barLength/2,2)+Math.pow(ball.getLayoutY()-originElements.barY,2))>=radius+originElements.barHeight&&
				dirX<0) {
			speedTransformer(posi+originElements.barLength/2);
			setDirX(Math.abs(getDirX())<3*originElements.initBallDirX? getDirX()+speed*originElements.fk:getDirX());
			musicPlayer.ballSound();
		}
//		¥ªÀð¾À
		if(ball.getLayoutX()-radius>=-10&&ball.getLayoutX()-radius<2&&getDirX()<0) {
			setDirX(getDirX()>-originElements.initBallDirX-0.015? -dirX:-dirX*0.8);
			musicPlayer.ballSound();
		}
//		¥kÀð¾À
		if(ball.getLayoutX()+radius<=originElements.width+10&&ball.getLayoutX()+radius>originElements.width-2&&getDirX()>0) {
			setDirX(getDirX()<originElements.initBallDirX+0.015? -dirX:-dirX*0.8);
			musicPlayer.ballSound();
		}
		gravity();
	}
	public void setDirX(double judge) {
		dirX=judge;
	}
	public void setDirY(double judge) {
		dirY=judge;
	}
	public double getDirX() {
		return dirX;
	}
	public double getDirY() {
		return dirY;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double r) {
		radius=r;
	}
	public void gravity() {
		dirY+=originElements.g;
	}
	public void speedTransformer(double cornerX) {
		double deltaX=cornerX-ball.getLayoutX();
		double deltaY=originElements.barY-ball.getLayoutY();
		double kX=(deltaX*dirX+deltaY*dirY)/(Math.pow(deltaX,2)+Math.pow(deltaY,2))*deltaX;
		double kY=(deltaX*dirX+deltaY*dirY)/(Math.pow(deltaX,2)+Math.pow(deltaY,2))*deltaY;
		setDirX(dirX-2*kX);
		setDirY(dirY-2*kY);
	}
	public void reload(int color) {
		dirX=originElements.initBallDirX;
		dirY=originElements.initBallDirY;
		ball.setLayoutX(originElements.initBallX);
		ball.setLayoutY(originElements.initBallY);
		radius=originElements.radius;
		ball.setRadius(originElements.radius);
		ball.setFill(Color.web(levelDetail.ballColor[color]));
	}
}
