package final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class welcome extends Application {
	public static Stage currentStage;
	public static Scene welcomeScene;
	@Override
	public void start(Stage primaryStage) throws Exception{
		currentStage=primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
		welcomeScene=new Scene(root,600,480,Color.GRAY);
		currentStage.setTitle("Brickout");
		currentStage.setScene(welcomeScene);
//		currentStage.setWidth(1000);
//		currentStage.setHeight(480);
		currentStage.setResizable(false);
		currentStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
