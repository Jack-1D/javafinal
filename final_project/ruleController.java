package final_project;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class ruleController {
	@FXML
	public void onBackPressed() {
		musicPlayer.clickSound();
		Parent welcomeAgain;
		try {
			welcomeAgain = FXMLLoader.load(getClass().getResource("welcome.fxml"));
			Scene welcomeScene=new Scene(welcomeAgain,600,480,Color.GRAY);
			welcomeScene.getRoot().requestFocus();
			welcome.currentStage.setScene(welcomeScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
