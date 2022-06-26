package final_project;

import java.io.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class resultController {
	@FXML
	Text Score;
	@FXML
	Text HP;
	@FXML
	Text Sum;
	@FXML
	Text Time;
	@FXML
	Text Total;
	@FXML
	Text Grade;
	
	public int brickNumber;
	public double standardPoint;
	public int timeExtraPoint;
	public int total;
	
//	levelListController listController=new levelListController();
	
	public void passValue(int level,int score,int hp,int timeLeft,boolean isPass) throws IOException {
		if(isPass) {
			if(level==1) brickNumber=levelDetail.level1BricksNumber;
			else if(level==2) brickNumber=levelDetail.level2BricksNumber;
			else if(level==3) brickNumber=levelDetail.level3BricksNumber;
			else if(level==4) brickNumber=levelDetail.level4BricksNumber;
			else if(level==5) brickNumber=levelDetail.level5BricksNumber;
			else if(level==6) brickNumber=levelDetail.level6BricksNumber;
			else if(level==7) brickNumber=levelDetail.level7BricksNumber;
			else if(level==8) brickNumber=levelDetail.level8BricksNumber;
			else if(level==9) brickNumber=levelDetail.level9BricksNumber;
			else if(level==10) brickNumber=levelDetail.level10BricksNumber;
			
			if(timeLeft>=250) timeExtraPoint=2000;
			else if(timeLeft<250&&timeLeft>=220) timeExtraPoint=1800;
			else if(timeLeft<220&&timeLeft>=190) timeExtraPoint=1500;
			else if(timeLeft<190&&timeLeft>=160) timeExtraPoint=1000;
			else if(timeLeft<160&&timeLeft>=130) timeExtraPoint=500;
			else if(timeLeft<130&&timeLeft>=100) timeExtraPoint=300;
			else if(timeLeft<100) timeExtraPoint=0;
			
			Score.setText(Integer.toString(score));
			HP.setText(Integer.toString(hp));
			Sum.setText(Integer.toString(score*hp));
			if(timeLeft%60<10) Time.setText(Integer.toString(timeLeft/60)+":0"+Integer.toString(timeLeft%60)+"  ("+Integer.toString(timeExtraPoint)+")");
			else Time.setText(Integer.toString(timeLeft/60)+":"+Integer.toString(timeLeft%60)+"  ("+Integer.toString(timeExtraPoint)+")");
			if(score*hp!=0 && timeLeft!=0) {Total.setText(Integer.toString(score*hp+timeExtraPoint)); total=score*hp+timeExtraPoint;}
			else {Total.setText("0"); total=0;}
			standardPoint=brickNumber*originElements.basicPoint*3;
			if(total>=standardPoint*10) Grade.setText("Legend");
			else if(total>=standardPoint*7&&total<standardPoint*10) Grade.setText("SSS");
			else if(total>=standardPoint*5&&total<standardPoint*7) Grade.setText("SS");
			else if(total>=standardPoint*3&&total<standardPoint*5) Grade.setText("S");
			else if(total>=standardPoint*2.6&&total<standardPoint*3) Grade.setText("AAA");
			else if(total>=standardPoint*2.3&&total<standardPoint*2.6) Grade.setText("AA");
			else if(total>=standardPoint*2&&total<standardPoint*2.3) Grade.setText("A");
			else if(total>=standardPoint&&total<standardPoint*2) Grade.setText("B");
			else if(total>=standardPoint*0.8&&total<standardPoint) Grade.setText("C");
			else if(total<standardPoint*0.8) Grade.setText("F");
		}
		else {
			total=0;
			Score.setText(Integer.toString(score));
			HP.setText(Integer.toString(hp));
			Sum.setText(Integer.toString(score*hp));
			if(timeLeft==0) Time.setText("已超時 ("+(-score*hp)+")");
			else Time.setText("未完成 (0)");
			Total.setText("0");
			Grade.setText("F");
		}
		
		String[] gradeText=new String[11];
		Integer[] scoreText=new Integer[11];
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/final_project/record.txt"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i=0;i<10;i++){
			String[] temp=new String[3];
			temp=reader.readLine().split(" ");
			gradeText[i]=temp[1];
			scoreText[i]=Integer.parseInt(temp[2]);
		}
		if(total>scoreText[level-1]){
			gradeText[level-1]=Grade.getText();
			scoreText[level-1]=total;
		}
		try {
			FileWriter record=new FileWriter("src/final_project/record.txt",false);
			for(int i=0;i<10;i++){
				record.write((i+1)+" "+gradeText[i]+" "+Integer.toString(scoreText[i])+"\n");
			}
			record.flush();
			record.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	@FXML
	public void backToMenu() throws IOException{
		musicPlayer.clickSound();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("levelList.fxml"));
		Parent menu=loader.load();
		levelListController controller=loader.getController();
		controller.setGrade();
		Scene menuScene=new Scene(menu,600,480,Color.GRAY);
		menuScene.getRoot().requestFocus();
		welcome.currentStage.setScene(menuScene);
	}
	@FXML
	public void backToHomepage() throws IOException{
		musicPlayer.clickSound();
		Parent welcomeAgain=FXMLLoader.load(getClass().getResource("welcome.fxml"));
		Scene welcomeScene=new Scene(welcomeAgain,600,480,Color.GRAY);
		welcomeScene.getRoot().requestFocus();
		welcome.currentStage.setScene(welcomeScene);
	}
}
