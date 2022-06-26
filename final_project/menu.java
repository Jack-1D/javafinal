package final_project;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class menu {
	@FXML
	public void onRulePressed() {
		musicPlayer.clickSound();
        Parent rule;
		try {
			rule = FXMLLoader.load(getClass().getResource("rule.fxml"));
			Scene ruleScene = new Scene(rule, 600, 480, Color.GRAY);
	        ruleScene.getRoot().requestFocus();
	        welcome.currentStage.setScene(ruleScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void onStartPressed() throws IOException{
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
	public void onExitPressed() {
		musicPlayer.clickSound();
		welcome.currentStage.close();
	}
}
