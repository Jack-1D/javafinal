package final_project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
public class level_8 implements Initializable{
	ArrayList<brick> brickList=new ArrayList<>();
	ArrayList<ImageView> HPList=new ArrayList<>();
	ArrayList<String> skillList=new ArrayList<>();
	ArrayList<ImageView> skillImage=new ArrayList<>();
	ArrayList<Label> effectNameList=new ArrayList<>();
	ArrayList<Text> effectTimerList=new ArrayList<>();
	ArrayList<Double> iceSpeed=new ArrayList<>();
	ArrayList<AtomicInteger> counterList=new ArrayList<>();
	@FXML
	Pane Background;
	@FXML
	Line Bar;
	@FXML
	Pane BrickPane;
	@FXML
	Text TotalScore;
	@FXML
	Pane Skill1;
	@FXML
	Pane Skill2;
	@FXML
	Pane Skill3;
	@FXML
	Pane Skill4;
	@FXML
	Text Timer;
	@FXML
	Line PauseLine1;
	@FXML
	Line PauseLine2;
	@FXML
	Polygon PlayTriangle;
	@FXML
	Button Back;
	
	final AtomicInteger bigTimer=new AtomicInteger(300);
	public boolean isStart=false;
	public boolean isdie=false;
	public boolean isPause=false;
	public double formerMouseX;
	public double currentMouseX;
	public double duration=1;
	public double times=5;
	public double speed;
	private int counter=0;
	private int counterCount=0;
	public int iceTimes=0;
	public int bonusTimes=0;
	private ball newBall;
	public int currentColor;
	public int totalScore;
	public int HP;
	Image heart=new Image("final_project\\heart.png");
	ImageView heartView=new ImageView(heart);
	Image big=new Image("final_project\\big.png");
	ImageView bigView=new ImageView(big);
	Image ice=new Image("final_project\\ice.png");
	ImageView iceView=new ImageView(ice);
	Image bonus=new Image("final_project\\bonus.png");
	ImageView bonusView=new ImageView(bonus);
	Image hp=new Image("final_project\\hp.png");
	ImageView hpView=new ImageView(hp);
	private Timeline gameTimeline;
	
	public void barMove(MouseEvent e) {
		if(!isdie&&isStart&&!isPause) {
			Bar.setLayoutX(e.getX()+originElements.barLength/2);
			//4¬°»~®t­È
			if(e.getX()<originElements.barLength/2) Bar.setLayoutX(originElements.barLength);
			else if(e.getX()>originElements.width-originElements.barLength/2) Bar.setLayoutX(originElements.width);
		}
	}
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		currentColor=0;
		HP=3;
		totalScore=0;
		if(bigTimer.get()%60<10) Timer.setText(Integer.toString(bigTimer.get()/60)+":"+"0"+Integer.toString(bigTimer.get()%60));
		else Timer.setText(Integer.toString(bigTimer.get()/60)+":"+Integer.toString(bigTimer.get()%60));
		TotalScore.setText(Integer.toString(totalScore));
		for(int i=0;i<HP;i++) {
			ImageView heartView=new ImageView(heart);
			heartView.setFitHeight(originElements.heartHeight);
			heartView.setFitWidth(originElements.heartWidth);
			heartView.setX(levelDetail.heartPositionX[i]);
			heartView.setY(levelDetail.heartPositionY[i]);
			HPList.add(heartView);
		}
		for(int i=0;i<HP;i++) BrickPane.getChildren().add(HPList.get(i));
		
		for(int i = 0;i<levelDetail.level8BricksNumber;i++)
			brickList.add(new brick(levelDetail.level8BricksX[i],levelDetail.level8BricksY[i]));
		for(int i=0;i<levelDetail.level8BricksNumber;i++) BrickPane.getChildren().add(brickList.get(i).brick);
		newBall=new ball();
		Background.getChildren().add(newBall.ball);
		formerMouseX=originElements.barX-originElements.barLength/2;
		Bar.setLayoutX(originElements.barX);
		Bar.setLayoutY(originElements.barY);
		
		gameTimeline=new Timeline(new KeyFrame(Duration.millis(duration),e->{
			counter++;
			counterCount++;
			
			if(counter>=100/duration) {
				counter=0;
				speed=(Bar.getLayoutX()-formerMouseX)/(100*duration);
				formerMouseX=Bar.getLayoutX();
			}
			if(counterCount>=1000/duration) {
				counterCount=0;
				int currentTime=bigTimer.decrementAndGet();
				if(currentTime%60<10) Timer.setText(Integer.toString(currentTime/60)+":"+"0"+Integer.toString(currentTime%60));
				else Timer.setText(Integer.toString(currentTime/60)+":"+Integer.toString(currentTime%60));
			}
			if(bigTimer.get()<=60) Timer.setFill(Color.RED);
			
			
			TotalScore.setText(Integer.toString(totalScore));
			
			for(int i=0;i<effectTimerList.size();i++) {
				if(effectTimerList.get(i).getText().equals("0")) {
					effectNameList.remove(i);
					effectTimerList.remove(i);
					newBall.effectTime.get(i).stop();
					newBall.effectTime.remove(i);
					for(int j=0;j<effectNameList.size();j++) {
						effectNameList.get(j).setLayoutX(levelDetail.effectPositionX[j]);
						effectNameList.get(j).setLayoutY(levelDetail.effectPositionY[j]);
						effectTimerList.get(j).setLayoutX(levelDetail.effectTimerX[j]);
						effectTimerList.get(j).setLayoutY(levelDetail.effectTimerY[j]);
					}
				}
			}
			backgroundClear();
			for(int i=0;i<HP;i++) BrickPane.getChildren().add(HPList.get(i));
			for(int i=0;i<brickList.size();i++) BrickPane.getChildren().add(brickList.get(i).brick);
			putSkill();
			for(int i=0;i<effectNameList.size();i++) BrickPane.getChildren().add(effectNameList.get(i));
			for(int i=0;i<effectTimerList.size();i++) BrickPane.getChildren().add(effectTimerList.get(i));
			newBall.ballMove(Bar.getLayoutX()-originElements.barLength/2,speed,times,duration);
			collision(newBall.ball.getLayoutX(),newBall.ball.getLayoutY(),newBall);
			
			
			isdie=isDie(newBall);
			setNextFrame();
		}));
	}
	private void setNextFrame() {
		//¿é
		if(brickList.size()==0) {
			gameTimeline.stop();
			try {
				win();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(isdie) {
			gameTimeline.stop();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("result.fxml"));
			Parent result;
			try {
				result = loader.load();
				resultController controller=loader.getController();
				controller.passValue(8,totalScore, HP,bigTimer.get(),false);
				Scene resultScene=new Scene(result);
				resultScene.getRoot().requestFocus();
				welcome.currentStage.setScene(resultScene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void putSkill() {
		switch(skillImage.size()) {
		case 1:
			Skill1.getChildren().add(skillImage.get(0));
			break;
		case 2:
			Skill1.getChildren().add(skillImage.get(0));
			Skill2.getChildren().add(skillImage.get(1));
			break;
		case 3:
			Skill1.getChildren().add(skillImage.get(0));
			Skill2.getChildren().add(skillImage.get(1));
			Skill3.getChildren().add(skillImage.get(2));
			break;
		case 4:
			Skill1.getChildren().add(skillImage.get(0));
			Skill2.getChildren().add(skillImage.get(1));
			Skill3.getChildren().add(skillImage.get(2));
			Skill4.getChildren().add(skillImage.get(3));
			break;
		}
	}
	public void backgroundClear() {
		BrickPane.getChildren().clear();
		Skill1.getChildren().clear();
		Skill2.getChildren().clear();
		Skill3.getChildren().clear();
		Skill4.getChildren().clear();
	}
	public void start() {
		gameTimeline.setCycleCount(Animation.INDEFINITE);
		gameTimeline.play();
	}
	public void win() throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("result.fxml"));
		Parent result=loader.load();
		resultController controller=loader.getController();
		controller.passValue(8,totalScore, HP,bigTimer.get(),true);
		Scene resultScene=new Scene(result);
		resultScene.getRoot().requestFocus();
		welcome.currentStage.setScene(resultScene);
	}
	public boolean isDie(ball b) {
		if(b.ball.getLayoutY()>480+b.getRadius()) {
			gameTimeline.pause();
			HP--;
			HPList.remove(HP);
			backgroundClear();
			for(int i=0;i<HP;i++) BrickPane.getChildren().add(HPList.get(i));
			for(int i=0;i<brickList.size();i++) BrickPane.getChildren().add(brickList.get(i).brick);
			putSkill();
			bonusTimes=0;
			iceTimes=0;
			for(int i=0;i<counterList.size();i++) counterList.get(i).set(-1);
			for(int i=0;i<effectNameList.size();i++) {
				effectNameList.clear();
				effectTimerList.clear();
				b.effectTime.clear();
			}
			if(HP==0) return true;
			else{
				isStart=false;
				Bar.setLayoutX(originElements.barX);
				Bar.setLayoutY(originElements.barY);
				b.reload(currentColor);
				for(Timeline t:b.effectTime) {
					t.stop();
				}
				b.effectTime.clear();
			}
		}
		if(bigTimer.get()==0) return true;
		return false;
	}
	public boolean brickChangeColor(int i) {
		if(brickList.get(i).getHP()==2) {
			brickList.get(i).brick.setFill(Color.RED);
		}
		else if(brickList.get(i).getHP()==1) {
			brickList.get(i).brick.setFill(Color.GREEN);
		}
		else if(brickList.get(i).getHP()==0) {
			brickList.get(i).setDestroyed(true);
			brickList.remove(i);
			return true;
		}
		return false;
	}
	
	void speedTransformer(double cornerX,double cornerY,ball b) {
		double deltaX=cornerX-b.ball.getLayoutX();
		double deltaY=cornerY-b.ball.getLayoutY();
		double kX=(deltaX*b.getDirX()+deltaY*b.getDirY())/(Math.pow(deltaX,2)+Math.pow(deltaY,2))*deltaX;
		double kY=(deltaX*b.getDirX()+deltaY*b.getDirY())/(Math.pow(deltaX,2)+Math.pow(deltaY,2))*deltaY;
		b.setDirX((b.getDirX()-2*kX)*0.8);
		b.setDirY((b.getDirY()-2*kY)*0.8);
	}
	void getNewSkill() {
		ImageView bigView=new ImageView(big);
		bigView.setFitHeight(28);
		bigView.setFitWidth(28);
		ImageView iceView=new ImageView(ice);
		iceView.setFitHeight(28);
		iceView.setFitWidth(28);
		ImageView bonusView=new ImageView(bonus);
		bonusView.setFitHeight(30);
		bonusView.setFitWidth(30);
		ImageView hpView=new ImageView(hp);
		hpView.setFitHeight(28);
		hpView.setFitWidth(28);
		int newNum1=new Random().nextInt(4);
		int newNum2=new Random().nextInt(9);
		int newNum3=new Random().nextInt(9);
		int newNum4=new Random().nextInt(29);
		if(newNum1==0&&skillList.size()<4) {
			skillImage.add(bigView);
			skillList.add("big");
		}
		else if(newNum2==0&&skillList.size()<4) {
			skillImage.add(iceView);
			skillList.add("ice");
		}
		else if(newNum3==0&&skillList.size()<4) {
			skillImage.add(bonusView);
			skillList.add("bonus");
		}
		else if(newNum4==0&&skillList.size()<4) {
			skillImage.add(hpView);
			skillList.add("hp");
		}
		
	}
	//»~®t®e³\­È 2
	public void collision(double x,double y,ball b) {
		for(int i=0;i<brickList.size();i++) {
			double centerX=brickList.get(i).getX()+originElements.brickWidth/2;
			double centerY=brickList.get(i).getY()+originElements.brickHeight/2;
			
//			¤W
			if(centerY-originElements.brickHeight/2-2-b.getRadius()<=y&&
					centerY-originElements.brickHeight/2-b.getRadius()>=y&&
					Math.abs(centerX-x)<=originElements.brickWidth/2&&
					b.getDirY()>0) {
				b.setDirY(b.getDirY()<originElements.initBallDirY+0.075?-b.getDirY():-b.getDirY()*0.8);
				brickList.get(i).setHP(brickList.get(i).getHP()-1);
				i -= ( brickChangeColor(i) ? 1 : 0 );
				BrickPane.setId(BrickPane.getId() == "black" ? "white" : "black");
				totalScore+=Math.pow(2, bonusTimes)*originElements.basicPoint;
				getNewSkill();
				musicPlayer.brickSound();
			}
//			¤U
			else if(centerY+originElements.brickHeight/2+2+b.getRadius()>=y&&
					centerY+originElements.brickHeight/2+b.getRadius()<=y&&
					Math.abs(centerX-x)<=originElements.brickWidth/2&&
					b.getDirY()<0) {
				b.setDirY(b.getDirY()>-originElements.initBallDirY-0.075? -b.getDirY():-b.getDirY()*0.8);
				brickList.get(i).setHP(brickList.get(i).getHP()-1);
				i -= ( brickChangeColor(i) ? 1 : 0 );
				BrickPane.setId(BrickPane.getId() == "black" ? "white" : "black");
				totalScore+=Math.pow(2, bonusTimes)*originElements.basicPoint;
				getNewSkill();
				musicPlayer.brickSound();
			}
//			¥ª
			else if(centerX-originElements.brickWidth/2-2-b.getRadius()<=x&&
					centerX-originElements.brickWidth/2-b.getRadius()>=x&&
					Math.abs(y-centerY)<=originElements.brickHeight/2&&
					b.getDirX()>0) {
				b.setDirX(b.getDirX()<originElements.initBallDirX+0.075? -b.getDirX():-b.getDirX()*0.8);
				brickList.get(i).setHP(brickList.get(i).getHP()-1);
				i -= ( brickChangeColor(i) ? 1 : 0 );
				BrickPane.setId(BrickPane.getId() == "black" ? "white" : "black");
				totalScore+=Math.pow(2, bonusTimes)*originElements.basicPoint;
				getNewSkill();
				musicPlayer.brickSound();
			}
//			¥k
			else if(centerX+originElements.brickWidth/2+2+b.getRadius()>=x&&
					centerX+originElements.brickWidth/2+b.getRadius()<=x&&
					Math.abs(y-centerY)<=originElements.brickHeight/2&&
					b.getDirX()<0) {
				b.setDirX(b.getDirX()>-originElements.initBallDirX-0.075? -b.getDirX():-b.getDirX()*0.8);
				brickList.get(i).setHP(brickList.get(i).getHP()-1);
				i -= ( brickChangeColor(i) ? 1 : 0 );
				BrickPane.setId(BrickPane.getId() == "black" ? "white" : "black");
				totalScore+=Math.pow(2, bonusTimes)*originElements.basicPoint;
				getNewSkill();
				musicPlayer.brickSound();
			}
//			¿j¶ô¥ª¤W¨¤
			else if(Math.sqrt(Math.pow(x-(centerX-originElements.brickWidth/2),2)+Math.pow(y-(centerY-originElements.brickHeight/2),2))<=2+b.getRadius()&&
					Math.sqrt(Math.pow(x-(centerX-originElements.brickWidth/2),2)+Math.pow(y-(centerY-originElements.brickHeight/2),2))>=b.getRadius()) {
				speedTransformer(centerX-originElements.brickWidth/2,centerY-originElements.brickHeight/2,b);
				brickList.get(i).setHP(brickList.get(i).getHP()-1);
				i -= ( brickChangeColor(i) ? 1 : 0 );
				BrickPane.setId(BrickPane.getId() == "black" ? "white" : "black");
				totalScore+=Math.pow(2, bonusTimes)*originElements.basicPoint;
				getNewSkill();
				musicPlayer.brickSound();
			}
//			¿j¶ô¥k¤W¨¤
			else if(Math.sqrt(Math.pow(x-(centerX+originElements.brickWidth/2),2)+Math.pow(y-(centerY-originElements.brickHeight/2),2))<=2+b.getRadius()&&
					Math.sqrt(Math.pow(x-(centerX+originElements.brickWidth/2),2)+Math.pow(y-(centerY-originElements.brickHeight/2),2))>=b.getRadius()) {
				speedTransformer(centerX+originElements.brickWidth/2,centerY-originElements.brickHeight/2,b);
				brickList.get(i).setHP(brickList.get(i).getHP()-1);
				i -= ( brickChangeColor(i) ? 1 : 0 );
				BrickPane.setId(BrickPane.getId() == "black" ? "white" : "black");
				totalScore+=Math.pow(2, bonusTimes)*originElements.basicPoint;
				getNewSkill();
				musicPlayer.brickSound();
			}
//			¿j¶ô¥ª¤U¨¤
			else if(Math.sqrt(Math.pow(x-(centerX-originElements.brickWidth/2),2)+Math.pow(y-(centerY+originElements.brickHeight/2),2))<=2+b.getRadius()&&
					Math.sqrt(Math.pow(x-(centerX-originElements.brickWidth/2),2)+Math.pow(y-(centerY+originElements.brickHeight/2),2))>=b.getRadius()) {
				speedTransformer(centerX-originElements.brickWidth/2,centerY+originElements.brickHeight/2,b);
				brickList.get(i).setHP(brickList.get(i).getHP()-1);
				i -= ( brickChangeColor(i) ? 1 : 0 );
				BrickPane.setId(BrickPane.getId() == "black" ? "white" : "black");
				totalScore+=Math.pow(2, bonusTimes)*originElements.basicPoint;
				getNewSkill();
				musicPlayer.brickSound();
			}
//			¿j¶ô¥k¤U¨¤
			else if(Math.sqrt(Math.pow(x-(centerX+originElements.brickWidth/2),2)+Math.pow(y-(centerY+originElements.brickHeight/2),2))<=2+b.getRadius()&&
					Math.sqrt(Math.pow(x-(centerX+originElements.brickWidth/2),2)+Math.pow(y-(centerY+originElements.brickHeight/2),2))>=b.getRadius()) {
				speedTransformer(centerX+originElements.brickWidth/2,centerY+originElements.brickHeight/2,b);
				brickList.get(i).setHP(brickList.get(i).getHP()-1);
				i -= ( brickChangeColor(i) ? 1 : 0 );
				BrickPane.setId(BrickPane.getId() == "black" ? "white" : "black");
				totalScore+=Math.pow(2, bonusTimes)*originElements.basicPoint;
				getNewSkill();
				musicPlayer.brickSound();
			}
		}
	}
	void triggerSkill(int code) {
		if(skillList.get(code)=="big") {
			musicPlayer.effectSound();
			Label label=new Label("Big");
			label.setTextFill(Color.RED);
			label.setFont(new Font(16));
			label.setLayoutX(levelDetail.effectPositionX[effectNameList.size()]);
			label.setLayoutY(levelDetail.effectPositionY[effectNameList.size()]);
			effectNameList.add(label);
			Text text=new Text(Integer.toString(15));
			text.setFill(Color.web("#00b1ff"));
			text.setFont(new Font(24));
			text.setLayoutX(levelDetail.effectTimerX[effectTimerList.size()]);
			text.setLayoutY(levelDetail.effectTimerY[effectTimerList.size()]);
			effectTimerList.add(text);
			
			newBall.setRadius(newBall.getRadius()+10);
			newBall.ball.setRadius(newBall.getRadius());
			final AtomicInteger counter=new AtomicInteger(15);
			counterList.add(counter);
			Timeline countDown=new Timeline(new KeyFrame(Duration.seconds(1),e->{
				if(counter.decrementAndGet()==0) {
					newBall.setRadius(newBall.getRadius()-10);
					newBall.ball.setRadius(newBall.getRadius());
					text.setText(Integer.toString(counter.get()));
				}
				else {
					text.setText(Integer.toString(counter.get()));
				}
			}));
			countDown.setCycleCount(15);
			countDown.play();
			newBall.effectTime.add(countDown);
		}
		if(skillList.get(code)=="ice") {
			musicPlayer.effectSound();
			Label label=new Label("Ice");
			label.setTextFill(Color.RED);
			label.setFont(new Font(16));
			label.setLayoutX(levelDetail.effectPositionX[effectNameList.size()]);
			label.setLayoutY(levelDetail.effectPositionY[effectNameList.size()]);
			effectNameList.add(label);
			Text text=new Text(Integer.toString(5));
			text.setFill(Color.web("#00b1ff"));
			text.setFont(new Font(24));
			text.setLayoutX(levelDetail.effectTimerX[effectTimerList.size()]);
			text.setLayoutY(levelDetail.effectTimerY[effectTimerList.size()]);
			effectTimerList.add(text);
			
			newBall.ball.setFill(Color.LIGHTBLUE);
			iceTimes++;
			times-=0.95;
			final AtomicInteger counter=new AtomicInteger(5);
			counterList.add(counter);
			Timeline countDown=new Timeline(new KeyFrame(Duration.seconds(1),e->{
				if(counter.decrementAndGet()==0) {
					iceTimes--;
					if(iceTimes==0) newBall.ball.setFill(Color.web(levelDetail.ballColor[currentColor]));
					text.setText(Integer.toString(counter.get()));
					times+=0.95;
				}
				else {
					text.setText(Integer.toString(counter.get()));
				}
			}));
			countDown.setCycleCount(5);
			countDown.play();
			newBall.effectTime.add(countDown);
		}
		if(skillList.get(code)=="bonus") {
			musicPlayer.effectSound();
			Label label=new Label("Bonus");
			label.setTextFill(Color.RED);
			label.setFont(new Font(16));
			label.setLayoutX(levelDetail.effectPositionX[effectNameList.size()]);
			label.setLayoutY(levelDetail.effectPositionY[effectNameList.size()]);
			effectNameList.add(label);
			Text text=new Text(Integer.toString(10));
			text.setFill(Color.web("#00b1ff"));
			text.setFont(new Font(24));
			text.setLayoutX(levelDetail.effectTimerX[effectTimerList.size()]);
			text.setLayoutY(levelDetail.effectTimerY[effectTimerList.size()]);
			effectTimerList.add(text);
			
			newBall.ball.setFill(Color.GOLD);
			bonusTimes++;
			final AtomicInteger counter=new AtomicInteger(10);
			counterList.add(counter);
			Timeline countDown=new Timeline(new KeyFrame(Duration.seconds(1),e->{
				if(counter.decrementAndGet()==0) {
					bonusTimes--;
					if(bonusTimes==0) newBall.ball.setFill(Color.web(levelDetail.ballColor[currentColor]));
					text.setText(Integer.toString(counter.get()));
				}
				else {
					text.setText(Integer.toString(counter.get()));
				}
			}));
			countDown.setCycleCount(10);
			countDown.play();
			newBall.effectTime.add(countDown);
		}
		if(skillList.get(code)=="hp") {
			musicPlayer.effectSound();
			if(HP<5) {
				ImageView heartView=new ImageView(heart);
				heartView.setFitHeight(originElements.heartHeight);
				heartView.setFitWidth(originElements.heartWidth);
				heartView.setX(levelDetail.heartPositionX[HPList.size()]);
				heartView.setY(levelDetail.heartPositionY[HPList.size()]);
				HP++;
				HPList.add(heartView);
			}
		}
	}
	public void backToMenu() {
		musicPlayer.clickSound();
		gameTimeline.stop();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("levelList.fxml"));
		Parent menu = null;
		try {
			menu = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		levelListController controller=loader.getController();
		try {
			controller.setGrade();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene menuScene=new Scene(menu,600,480,Color.GRAY);
		menuScene.getRoot().requestFocus();
		welcome.currentStage.setScene(menuScene);
	}
	public void triggerStart(KeyEvent e) {
		if(e.getCode()==KeyCode.SPACE&&isStart) pauseGame();
		if(e.getCode()==KeyCode.SPACE&&!isStart) {
			isStart=true;
			start();
		}
		if((e.getCode()==KeyCode.DIGIT1||e.getCode()==KeyCode.NUMPAD1)&&skillList.size()>=1&&isStart&&effectNameList.size()<5&&!isPause) {
			triggerSkill(0);
			skillImage.remove(0);
			skillList.remove(0);
		}
		if((e.getCode()==KeyCode.DIGIT2||e.getCode()==KeyCode.NUMPAD2)&&skillList.size()>=2&&isStart&&effectNameList.size()<5&&!isPause) {
			triggerSkill(1);
			skillImage.remove(1);
			skillList.remove(1);
		}
		if((e.getCode()==KeyCode.DIGIT3||e.getCode()==KeyCode.NUMPAD3)&&skillList.size()>=3&&isStart&&effectNameList.size()<5&&!isPause) {
			triggerSkill(2);
			skillImage.remove(2);
			skillList.remove(2);
		}
		if((e.getCode()==KeyCode.DIGIT4||e.getCode()==KeyCode.NUMPAD4)&&skillList.size()>=4&&isStart&&effectNameList.size()<5&&!isPause) {
			triggerSkill(3);
			skillImage.remove(3);
			skillList.remove(3);
		}
		if((e.getCode()==KeyCode.UP||e.getCode()==KeyCode.LEFT)&&iceTimes==0&&bonusTimes==0) {
			if(currentColor==0) currentColor=levelDetail.ballColor.length-1;
			else currentColor-=1;
			newBall.ball.setFill(Color.web(levelDetail.ballColor[currentColor]));
		}
		if((e.getCode()==KeyCode.DOWN||e.getCode()==KeyCode.RIGHT)&&iceTimes==0&&bonusTimes==0) {
			if(currentColor==levelDetail.ballColor.length-1) currentColor=0;
			else currentColor+=1;
			newBall.ball.setFill(Color.web(levelDetail.ballColor[currentColor]));
		}
	}
	public void pauseGame() {
		if(isPause) {
			isPause=false;
			PauseLine1.setVisible(true);
			PauseLine2.setVisible(true);
			PlayTriangle.setVisible(false);
			BrickPane.requestFocus();
			for(int i=0;i<newBall.effectTime.size();i++) newBall.effectTime.get(i).play();
			gameTimeline.play();
		}
		else {
			isPause=true;
			PauseLine1.setVisible(false);
			PauseLine2.setVisible(false);
			PlayTriangle.setVisible(true);
			BrickPane.requestFocus();
			for(int i=0;i<newBall.effectTime.size();i++) newBall.effectTime.get(i).pause();
			gameTimeline.pause();
		}
	}
}
