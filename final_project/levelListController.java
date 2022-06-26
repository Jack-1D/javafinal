package final_project;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class levelListController{
    ArrayList<Image> batchList = new ArrayList<>();
    String[] gradeText = new String[11];
    Integer[] scoreText = new Integer[11];

    Image batchSSS = new Image("final_project\\batch_SSS.png");
    Image batchSS = new Image("final_project\\batch_SS.png");
    Image batchS = new Image("final_project\\batch_S.png");
    Image batchAAA = new Image("final_project\\batch_AAA.png");
    Image batchAA = new Image("final_project\\batch_AA.png");
    Image batchA = new Image("final_project\\batch_A.png");
    Image batchB = new Image("final_project\\batch_B.png");
    Image batchC = new Image("final_project\\batch_C.png");
    Image batchF = new Image("final_project\\batch_F.png");

    Image rainbow1 = new Image("final_project\\rainbow1.PNG");
    Image rainbow2 = new Image("final_project\\rainbow2.PNG");
    Image rainbow3 = new Image("final_project\\rainbow3.PNG");
    Image rainbow4 = new Image("final_project\\rainbow4.PNG");
    Image rainbow5 = new Image("final_project\\rainbow5.PNG");
    Image rainbow6 = new Image("final_project\\rainbow6.PNG");
    Image rainbow7 = new Image("final_project\\rainbow7.PNG");
    Image rainbow8 = new Image("final_project\\rainbow8.PNG");
    Image rainbow9 = new Image("final_project\\rainbow9.PNG");
    Image rainbow10 = new Image("final_project\\rainbow10.PNG");


    @FXML
    Pane Background;
    @FXML
    Button Button1;
    @FXML
    Button Button2;
    @FXML
    Button Button3;
    @FXML
    Button Button4;
    @FXML
    Button Button5;
    @FXML
    Button Button6;
    @FXML
    Button Button7;
    @FXML
    Button Button8;
    @FXML
    Button Button9;
    @FXML
    Button Button10;
    @FXML
    Button Back;
    
    Button[] buttonList=new Button[10];
    

    public int[] maxScore = new int[10];


    public void setGrade() throws IOException {
        buttonList = new Button[]{Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, Button10};
        ImageView rainbowView1 = new ImageView(rainbow1);
        rainbowView1.setFitHeight(39.8);
        rainbowView1.setFitWidth(87.2);
        ImageView rainbowView2 = new ImageView(rainbow2);
        rainbowView2.setFitHeight(39.8);
        rainbowView2.setFitWidth(87.2);
        ImageView rainbowView3 = new ImageView(rainbow3);
        rainbowView3.setFitHeight(39.8);
        rainbowView3.setFitWidth(87.2);
        ImageView rainbowView4 = new ImageView(rainbow4);
        rainbowView4.setFitHeight(39.8);
        rainbowView4.setFitWidth(87.2);
        ImageView rainbowView5 = new ImageView(rainbow5);
        rainbowView5.setFitHeight(39.8);
        rainbowView5.setFitWidth(87.2);
        ImageView rainbowView6 = new ImageView(rainbow6);
        rainbowView6.setFitHeight(39.8);
        rainbowView6.setFitWidth(87.2);
        ImageView rainbowView7 = new ImageView(rainbow7);
        rainbowView7.setFitHeight(39.8);
        rainbowView7.setFitWidth(87.2);
        ImageView rainbowView8 = new ImageView(rainbow8);
        rainbowView8.setFitHeight(39.8);
        rainbowView8.setFitWidth(87.2);
        ImageView rainbowView9 = new ImageView(rainbow9);
        rainbowView9.setFitHeight(39.8);
        rainbowView9.setFitWidth(87.2);
        ImageView rainbowView10 = new ImageView(rainbow10);
        rainbowView10.setFitHeight(39.8);
        rainbowView10.setFitWidth(87.2);
        ImageView[] rainbowList = {rainbowView1, rainbowView2, rainbowView3, rainbowView4, rainbowView5, rainbowView6, rainbowView7, rainbowView8, rainbowView9, rainbowView10};

        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);

        Lighting lighting = new Lighting();
        lighting.setSpecularConstant(3000);
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        BufferedReader reader = null;
        try {
            File file=new File("src/final_project/record.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileInputStream fileInputStream=new FileInputStream(file);
            BufferedReader tryReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            String t=tryReader.readLine();
            tryReader.close();
            if(t==null){
                FileOutputStream fileOutput=new FileOutputStream(file,true);
                for(int i=0;i<10;i++) fileOutput.write(((i+1)+" G -1\n").getBytes(StandardCharsets.UTF_8));
                fileOutput.close();
            }
            fileInputStream=new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            String[] temp = new String[3];
            temp = reader.readLine().split(" ");
            gradeText[i] = temp[1];
            scoreText[i] = Integer.parseInt(temp[2]);
        }
        for (int i = 0; i < 10; i++) {
            switch (gradeText[i]) {
                case "Legend":
                    buttonList[i].setText("");
                    buttonList[i].setGraphic(rainbowList[i]);
                    buttonList[i].setEffect(lighting);
                    break;
                case "SSS":
                    ImageView batchSSSView = new ImageView(batchSSS);
                    batchSSSView.setFitHeight(39.8);
                    batchSSSView.setFitWidth(28);
                    buttonList[i].setGraphic(batchSSSView);
                    buttonList[i].setStyle("-fx-background-color: Gold");
                    buttonList[i].setEffect(lighting);
                    break;
                case "SS":
                    ImageView batchSSView = new ImageView(batchSS);
                    batchSSView.setFitHeight(39.8);
                    batchSSView.setFitWidth(28);
                    buttonList[i].setGraphic(batchSSView);
                    buttonList[i].setStyle("-fx-background-color: Gold");
                    buttonList[i].setEffect(lighting);
                    break;
                case "S":
                    ImageView batchSView=new ImageView(batchS);
                    batchSView.setFitHeight(39.8);
                    batchSView.setFitWidth(28);
                    buttonList[i].setGraphic(batchSView);
                    buttonList[i].setStyle("-fx-background-color: Gold");
                    buttonList[i].setEffect(lighting);
                    break;
                case "AAA":
                    ImageView batchAAAView=new ImageView(batchAAA);
                    batchAAAView.setFitHeight(39.8);
                    batchAAAView.setFitWidth(28);
                    buttonList[i].setGraphic(batchAAAView);
                    buttonList[i].setStyle("-fx-background-color: Silver");
                    buttonList[i].setEffect(lighting);
                    break;
                case "AA":
                    ImageView batchAAView=new ImageView(batchAA);
                    batchAAView.setFitHeight(39.8);
                    batchAAView.setFitWidth(28);
                    buttonList[i].setGraphic(batchAAView);
                    buttonList[i].setStyle("-fx-background-color: Silver");
                    buttonList[i].setEffect(lighting);
                    break;
                case "A":
                    ImageView batchAView=new ImageView(batchA);
                    batchAView.setFitHeight(39.8);
                    batchAView.setFitWidth(28);
                    buttonList[i].setGraphic(batchAView);
                    buttonList[i].setStyle("-fx-background-color: Silver");
                    buttonList[i].setEffect(lighting);
                    break;
                case "B":
                    ImageView batchBView=new ImageView(batchB);
                    batchBView.setFitHeight(39.8);
                    batchBView.setFitWidth(28);
                    buttonList[i].setGraphic(batchBView);
                    buttonList[i].setStyle("-fx-background-color: #D26900");
                    buttonList[i].setEffect(lighting);
                    break;
                case "C":
                    ImageView batchCView=new ImageView(batchC);
                    batchCView.setFitHeight(39.8);
                    batchCView.setFitWidth(28);
                    buttonList[i].setGraphic(batchCView);
                    buttonList[i].setStyle("-fx-background-color: #FF8EFF");
                    buttonList[i].setEffect(lighting);
                    break;
                case "F":
                    ImageView batchFView=new ImageView(batchF);
                    batchFView.setFitHeight(39.8);
                    batchFView.setFitWidth(28);
                    buttonList[i].setGraphic(batchFView);
                    buttonList[i].setStyle("-fx-background-color: #A6FFA6");
                    buttonList[i].setEffect(lighting);
                    break;
            }
        }
    }
    @FXML
    public void onBackPressed() throws IOException {
    	musicPlayer.clickSound();
		Parent welcomeAgain=FXMLLoader.load(getClass().getResource("welcome.fxml"));
		Scene welcomeScene=new Scene(welcomeAgain,600,480,Color.GRAY);
		welcomeScene.getRoot().requestFocus();
		welcome.currentStage.setScene(welcomeScene);
    }
    
    @FXML
    public void onOnePressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelOne = FXMLLoader.load(getClass().getResource("level_1.fxml"));
        Scene levelOneScene = new Scene(levelOne, 600, 480, Color.GRAY);
        levelOneScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelOneScene);
    }

    @FXML
    public void onTwoPressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelTwo = FXMLLoader.load(getClass().getResource("level_2.fxml"));
        Scene levelTwoScene = new Scene(levelTwo, 600, 480, Color.GRAY);
        levelTwoScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelTwoScene);
    }

    @FXML
    public void onThreePressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelThree = FXMLLoader.load(getClass().getResource("level_3.fxml"));
        Scene levelThreeScene = new Scene(levelThree, 600, 480, Color.GRAY);
        levelThreeScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelThreeScene);
    }
    @FXML
    public void onFourPressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelFour = FXMLLoader.load(getClass().getResource("level_4.fxml"));
        Scene levelFourScene = new Scene(levelFour, 600, 480, Color.GRAY);
        levelFourScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelFourScene);
    }
    @FXML
    public void onFivePressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelFive = FXMLLoader.load(getClass().getResource("level_5.fxml"));
        Scene levelFiveScene = new Scene(levelFive, 600, 480, Color.GRAY);
        levelFiveScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelFiveScene);
    }
    @FXML
    public void onSixPressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelSix = FXMLLoader.load(getClass().getResource("level_6.fxml"));
        Scene levelSixScene = new Scene(levelSix, 600, 480, Color.GRAY);
        levelSixScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelSixScene);
    }
    @FXML
    public void onSevenPressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelSeven = FXMLLoader.load(getClass().getResource("level_7.fxml"));
        Scene levelSevenScene = new Scene(levelSeven, 600, 480, Color.GRAY);
        levelSevenScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelSevenScene);
    }
    @FXML
    public void onEightPressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelEight = FXMLLoader.load(getClass().getResource("level_8.fxml"));
        Scene levelEightScene = new Scene(levelEight, 600, 480, Color.GRAY);
        levelEightScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelEightScene);
    }
    @FXML
    public void onNinePressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelNine = FXMLLoader.load(getClass().getResource("level_9.fxml"));
        Scene levelNineScene = new Scene(levelNine, 600, 480, Color.GRAY);
        levelNineScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelNineScene);
    }
    @FXML
    public void onTenPressed() throws IOException {
    	musicPlayer.clickSound();
        Parent levelTen = FXMLLoader.load(getClass().getResource("level_10.fxml"));
        Scene levelTenScene = new Scene(levelTen, 600, 480, Color.GRAY);
        levelTenScene.getRoot().requestFocus();
        welcome.currentStage.setScene(levelTenScene);
    }
}
