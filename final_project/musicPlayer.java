package final_project;

import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class musicPlayer {
	static ArrayList<MediaPlayer> brickPlayerList=new ArrayList<>();
	static ArrayList<MediaPlayer> ballPlayerList=new ArrayList<>();
	static ArrayList<MediaPlayer> effectPlayerList=new ArrayList<>();
	static MediaPlayer brickPlayer;
	static MediaPlayer ballPlayer;
	static MediaPlayer effectPlayer;
	static MediaPlayer clickPlayer;
	static public int brickPlayerNum=0;
	static public int ballPlayerNum=0;
	static public int effectPlayerNum=0;
	
	static public void brickSound() {
		brickPlayerNum=brickPlayerNum==9? 0:brickPlayerNum;
		if(brickPlayerList.isEmpty()) {
			Media m=new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "src/final_project/brickEffect.mp3");
			for(int i=0;i<10;i++) {
				brickPlayer=new MediaPlayer(m);
				brickPlayerList.add(brickPlayer);
			}
		}
		brickPlayerList.get(brickPlayerNum).seek(Duration.ZERO);
		brickPlayerList.get(brickPlayerNum).play();
		brickPlayerNum++;
	}
	static public void ballSound() {
		ballPlayerNum=ballPlayerNum==9? 0:ballPlayerNum;
		if(ballPlayerList.isEmpty()) {
			Media m=new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "src/final_project/ballEffect.mp3");
			for(int i=0;i<10;i++) {
				ballPlayer=new MediaPlayer(m);
				ballPlayerList.add(ballPlayer);
			}
		}
		ballPlayerList.get(brickPlayerNum).seek(Duration.ZERO);
		ballPlayerList.get(brickPlayerNum).play();
		ballPlayerNum++;
	}
	static public void effectSound() {
		effectPlayerNum=effectPlayerNum==9? 0:effectPlayerNum;
		if(effectPlayerList.isEmpty()) {
			Media m=new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "src/final_project/skillEffect.mp3");
			for(int i=0;i<10;i++) {
				effectPlayer=new MediaPlayer(m);
				effectPlayerList.add(effectPlayer);
			}
		}
		effectPlayerList.get(effectPlayerNum).seek(Duration.ZERO);
		effectPlayerList.get(effectPlayerNum).play();
		effectPlayerNum++;
	}
	static public void clickSound() {
		if(clickPlayer==null) {
			Media m=new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "src/final_project/buttonEffect.mp3");
			clickPlayer=new MediaPlayer(m);
		}
		clickPlayer.seek(Duration.ZERO);
		clickPlayer.play();
	}

}
