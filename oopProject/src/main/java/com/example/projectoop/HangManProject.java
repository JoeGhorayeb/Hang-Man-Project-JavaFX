package com.example.projectoop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;


import javafx.scene.media.Media;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.function.UnaryOperator;

import static javafx.application.Application.launch;
public class HangManProject extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //constants used throughout the program
    private static final String FONT_PATH = "/fonts/BungeeTint-Regular.ttf";

    private static final String BTN_CLICK = "/audios/button_clicking.mp3";
    Media mediaClick = new Media(getClass().getResource(BTN_CLICK).toExternalForm());
    MediaPlayer click = new MediaPlayer(mediaClick);

    private int wrongGuessCount = 0;
    private ArrayList<String> ltrs = new ArrayList<>();


    //primary stage launch
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, URISyntaxException {
        //creating the background music
        String musicFile = "/audios/background_music.mp3";
        Media media = new Media(getClass().getResource(musicFile).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.7);
        mediaPlayer.play();


        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);



        //using the vbox to create the home view, title and home buttons
        homeView(vbox);
        titleLabel(vbox);
        homeButtons(vbox,mediaPlayer);

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setTitle("HangMan");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void homeView(VBox vbox) {
        try {
            //background img
            Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());

            BackgroundImage background = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, true)
            );

            vbox.setBackground(new Background(background));
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }
    }
    private void titleLabel(VBox vbox) throws FileNotFoundException, URISyntaxException {
        //title label styling
        Label title = new Label("HangMan!");
        title.setTextFill(Color.SADDLEBROWN);
        title.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), new Insets(-5))));


        File fontFile = new File(getClass().getResource(FONT_PATH).toURI());
        FileInputStream fontInputStream = new FileInputStream(fontFile);


        Font customFont = Font.loadFont(fontInputStream, 60);
        title.setFont(customFont);


        vbox.getChildren().add(title);

    }
    private void homeButtons(VBox vbox, MediaPlayer mediaPlayer) throws FileNotFoundException, URISyntaxException {

        //setting the button values
        Button botBtn = new Button("VS CPU");
        Button pvpBtn = new Button("2 PLAYERS");

        Image closeImg = new Image(getClass().getResource("/images/close.png").toExternalForm());
        ImageView closeImgView = new ImageView(closeImg);
        closeImgView.setFitWidth(15);
        closeImgView.setFitHeight(15);

        Button closeBtn = new Button("Close" , closeImgView);

        Image image = new Image(getClass().getResourceAsStream("/images/music_icon.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Button musicBtn = new Button("Music", imageView);



        File fontFile = new File(getClass().getResource(FONT_PATH).toURI());
        FileInputStream fontInputStream = new FileInputStream(fontFile);
        Font customFont = Font.loadFont(fontInputStream, 20);

        //styling home buttons
        setButtonStyle(botBtn, customFont);
        setButtonStyle(pvpBtn, customFont);
        setButtonStyle(closeBtn, customFont);
        setButtonStyle(musicBtn, customFont);

        botBtn.setMaxSize(200, 50);
        pvpBtn.setMaxSize(200, 50);
        closeBtn.setMaxSize(150, 40);
        musicBtn.setMaxSize(150, 40);

        //hover effects
        closeBtn.setOnMouseEntered(e->{
            closeBtn.setStyle("-fx-background-color: #6E3510;-fx-text-fill:white;");
            closeBtn.setFont(customFont);
        });
        closeBtn.setOnMouseExited(e->{
            closeBtn.setStyle("-fx-background-color: saddleBrown; -fx-text-fill: white;");
            closeBtn.setFont(customFont);
        });
        botBtn.setOnMouseEntered(e->{
           botBtn.setStyle("-fx-background-color: #6E3510; -fx-text-fill: white;");
           botBtn.setFont(customFont);
        });
        botBtn.setOnMouseExited(e->{
            botBtn.setStyle("-fx-background-color: saddleBrown; -fx-text-fill: white;");
            botBtn.setFont(customFont);
        });
        pvpBtn.setOnMouseEntered(e->{
            pvpBtn.setStyle("-fx-background-color: #6E3510; -fx-text-fill: white;");
            pvpBtn.setFont(customFont);
        });
        pvpBtn.setOnMouseExited(e->{
            pvpBtn.setStyle("-fx-background-color: saddleBrown; -fx-text-fill: white;");
            pvpBtn.setFont(customFont);
        });
        musicBtn.setOnMouseEntered(e->{
           musicBtn.setStyle("-fx-background-color: #6E3510; -fx-text-fill: white;");
           musicBtn.setFont(customFont);
        });
        musicBtn.setOnMouseExited(e->{
            musicBtn.setStyle("-fx-background-color: saddleBrown; -fx-text-fill: white;");
            musicBtn.setFont(customFont);
        });

        //their event handlers
        closeBtn.setOnAction(e -> {
            Stage stage = (Stage) closeBtn.getScene().getWindow();
            click.stop();
            click.play();
            stage.close();
        });
        botBtn.setOnAction(e -> {
            Stage stage = (Stage) botBtn.getScene().getWindow();
            click.stop();
            click.play();
            botGame(stage, customFont);
        });
        pvpBtn.setOnAction(e -> {
            Stage stage = (Stage) pvpBtn.getScene().getWindow();
            click.stop();
            click.play();
            pvpGame(stage,customFont);
        });
        musicBtn.setOnAction(e -> {
            click.stop();
            click.play();
            if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                musicBtn.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
            } else if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
                mediaPlayer.play();
                musicBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(0))));

            }
        });


        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(botBtn, pvpBtn,musicBtn,closeBtn);
    }
    private void setButtonStyle(Button button, Font font) {
        //method for button styling
        button.setStyle("-fx-background-color: saddleBrown; -fx-text-fill: white;");
        button.setFont(font);
    }

    private void botGame(Stage primaryStage, Font customFont) {
        //vs bot method (pre playing)
        VBox vbox = new VBox();
        Stage stage = new Stage();
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);

        Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        vbox.setBackground(new Background(background));

        //back button to go back to the home view
        Button backBtn = new Button(" < Back");
        setButtonStyle(backBtn, customFont);
        VBox.setMargin(backBtn, new Insets(5));

        backBtn.setOnMouseEntered(e->{
            backBtn.setStyle("-fx-background-color: #6E3510; -fx-text-fill: white;");
            backBtn.setFont(customFont);
        });
        backBtn.setOnMouseExited(e->{
            backBtn.setStyle("-fx-background-color: saddleBrown; -fx-text-fill: white;");
            backBtn.setFont(customFont);
        });

        backBtn.setOnAction(e -> {
            stage.close();
            primaryStage.show();
            click.stop();
            click.play();
        });

        //title label
        Label title = new Label("VS CPU");
        title.setTextFill(Color.SADDLEBROWN);
        title.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), new Insets(-5))));

        Font titleFont = Font.font(customFont.getFamily(), 60);

        title.setFont(titleFont);
        title.setAlignment(Pos.CENTER);

        StackPane titleContainer = new StackPane(title);
        titleContainer.setAlignment(Pos.CENTER);
        VBox.setVgrow(titleContainer, Priority.ALWAYS);

        vbox.getChildren().addAll(backBtn, titleContainer);

        HBox size = new HBox();

        //choosing the size of the word with radio buttons
        RadioButton wordSize = new RadioButton("4");
        RadioButton wordSize2 = new RadioButton("5");
        RadioButton wordSize3 = new RadioButton("6");


        Button playBtn = new Button("Play!");

        size.setMargin(wordSize, new Insets(7));
        size.setMargin(wordSize2, new Insets(7));
        size.setMargin(wordSize3, new Insets(7));

        wordSize.setFont(customFont);
        wordSize2.setFont(customFont);
        wordSize3.setFont(customFont);

        //creating a toggle group for the radio buttons
        ToggleGroup group = new ToggleGroup();

        wordSize.setToggleGroup(group);
        wordSize2.setToggleGroup(group);
        wordSize3.setToggleGroup(group);

        wordSize2.setSelected(true);

        //storing the toggle buttons in a single pane to be stacked in the vbox
        StackPane sizeContainer = new StackPane(size);
        sizeContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), new Insets(-5))));
        sizeContainer.setMaxWidth(Double.MAX_VALUE);
        sizeContainer.setMaxHeight(Double.MAX_VALUE);

        setButtonStyle(playBtn, customFont);
        VBox.setMargin(playBtn, new Insets(10, 0, 100, 350));
        playBtn.setDefaultButton(true);

        Label sizeLabel = new Label("Word Size:");
        sizeLabel.setFont(customFont);


        size.getChildren().addAll(wordSize, wordSize2, wordSize3);
        size.setAlignment(Pos.CENTER);
        size.setSpacing(10);

        playBtn.setOnMouseEntered(e->{
            playBtn.setStyle("-fx-background-color: #6E3510; -fx-text-fill: white;");
            playBtn.setFont(customFont);
        });
        playBtn.setOnMouseExited(e->{
            playBtn.setStyle("-fx-background-color: saddleBrown; -fx-text-fill: white;");
            playBtn.setFont(customFont);
        });

        playBtn.setOnAction(e -> {
            click.stop();
            click.play();

            //takes us to the method linked to its word size on the play button click
            int selected = 0;
            if (wordSize.isSelected()) {
                selected = 4;
            } else if (wordSize2.isSelected()) {
                selected = 5;
            } else if (wordSize3.isSelected()) {
                selected = 6;
            }
            try {
                botPlay(selected,primaryStage);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            stage.close();
        });

        //storing the size label inside a label container to be stored in the vbox
        StackPane labelContainer = new StackPane(sizeLabel);
        labelContainer.setAlignment(Pos.CENTER);

        vbox.getChildren().add(labelContainer);
        vbox.getChildren().add(size);
        vbox.getChildren().add(playBtn);

        stage.setScene(scene);
        primaryStage.close();
        stage.show();
    }
    private void botPlay(int size,Stage primaryStage) throws URISyntaxException, FileNotFoundException {
        //method for playing vs bot

        //resetting the wrong guess count
        wrongGuessCount = 0;

        Stage stage = new Stage();
        stage.setTitle("PLAYING");
        VBox vbox = new VBox();
        vbox.setMinWidth(Region.USE_PREF_SIZE);
        vbox.setMinHeight(Region.USE_PREF_SIZE);
        vbox.setMaxWidth(Double.MAX_VALUE);
        vbox.setMaxHeight(Double.MAX_VALUE);




        //the empty hang img
        Image image = new Image(getClass().getResource("/images/hang.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(226.249);
        imageView.setFitHeight(319.999);
        StackPane imageContainer = new StackPane(imageView);

        imageContainer.setMargin(imageView, new Insets(5));

        vbox.setAlignment(Pos.CENTER);



        //getting the row of the word chosen
        int wordNb = getWord(size);
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);

        File fontFile = new File(getClass().getResource(FONT_PATH).toURI());
        FileInputStream fontInputStream = new FileInputStream(fontFile);
        Font customFont = Font.loadFont(fontInputStream, 70);

        //creating an array to store the word, and an array list to store their significant labels
        char[] wordQst = new char[size];
        List<Label> labelList = new ArrayList<>();

        //2d arrays containing the words, and all the styling and border bottoms of each letter in each word size
        if(size==4) {
            char[][] word = new char[10][4];
            word[0][0] = 'B';word[0][1] = 'A';word[0][2] = 'C';word[0][3] = 'K';
            word[1][0] = 'T';word[1][1] = 'A';word[1][2] = 'P';word[1][3] = 'E';
            word[2][0] = 'F';word[2][1] = 'I';word[2][2] = 'N';word[2][3] = 'D';
            word[3][0] = 'S';word[3][1] = 'O';word[3][2] = 'U';word[3][3] = 'L';
            word[4][0] = 'F';word[4][1] = 'O';word[4][2] = 'L';word[4][3] = 'D';
            word[5][0] = 'D';word[5][1] = 'O';word[5][2] = 'R';word[5][3] = 'M';
            word[6][0] = 'F';word[6][1] = 'A';word[6][2] = 'C';word[6][3] = 'T';
            word[7][0] = 'O';word[7][1] = 'A';word[7][2] = 'T';word[7][3] = 'S';
            word[8][0] = 'C';word[8][1] = 'I';word[8][2] = 'T';word[8][3] = 'Y';
            word[9][0] = 'I';word[9][1] = 'D';word[9][2] = 'E';word[9][3] = 'A';

            char ltr1 =word[wordNb][0];
            char ltr2 =word[wordNb][1];
            char ltr3 =word[wordNb][2];
            char ltr4 =word[wordNb][3];

            System.out.print(ltr1);
            System.out.print(ltr2);
            System.out.print(ltr3);
            System.out.println(ltr4);

            wordQst[0] = ltr1;wordQst[1] = ltr2;wordQst[2] = ltr3;wordQst[3] = ltr4;

            Label label1 = new Label(String.valueOf(ltr1));
            Label label2 = new Label(String.valueOf(ltr2));
            Label label3 = new Label(String.valueOf(ltr3));
            Label label4 = new Label(String.valueOf(ltr4));

            label1.setFont(customFont);
            label2.setFont(customFont);
            label3.setFont(customFont);
            label4.setFont(customFont);


            StackPane pane1 = new StackPane(label1);
            StackPane pane2 = new StackPane(label2);
            StackPane pane3 = new StackPane(label3);
            StackPane pane4 = new StackPane(label4);


            pane1.setPrefSize(100, 50);
            pane2.setPrefSize(100, 50);
            pane3.setPrefSize(100, 50);
            pane4.setPrefSize(100, 50);


            pane1.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane2.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane3.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane4.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");

            label1.setManaged(false);
            label1.setVisible(false);
            label2.setManaged(false);
            label2.setVisible(false);
            label3.setManaged(false);
            label3.setVisible(false);
            label4.setManaged(false);
            label4.setVisible(false);

            labelList.add(label1);
            labelList.add(label2);
            labelList.add(label3);
            labelList.add(label4);



            hbox.getChildren().addAll(pane1, pane2, pane3, pane4);



        }else if(size==5) {
            char[][] word = new char[10][5];
            word[0][0] = 'S';word[0][1] = 'T';word[0][2] = 'A';word[0][3] = 'C';word[0][4] = 'K';
            word[1][0] = 'S';word[1][1] = 'T';word[1][2] = 'A';word[1][3] = 'I';word[1][4] = 'R';
            word[2][0] = 'B';word[2][1] = 'L';word[2][2] = 'A';word[2][3] = 'Z';word[2][4] = 'E';
            word[3][0] = 'B';word[3][1] = 'E';word[3][2] = 'A';word[3][3] = 'C';word[3][4] = 'H';
            word[4][0] = 'G';word[4][1] = 'I';word[4][2] = 'A';word[4][3] = 'N';word[4][4] = 'T';
            word[5][0] = 'M';word[5][1] = 'U';word[5][2] = 'S';word[5][3] = 'I';word[5][4] = 'C';
            word[6][0] = 'H';word[6][1] = 'O';word[6][2] = 'R';word[6][3] = 'S';word[6][4] = 'E';
            word[7][0] = 'S';word[7][1] = 'C';word[7][2] = 'O';word[7][3] = 'R';word[7][4] = 'E';
            word[8][0] = 'V';word[8][1] = 'I';word[8][2] = 'D';word[8][3] = 'E';word[8][4] = 'O';
            word[9][0] = 'W';word[9][1] = 'O';word[9][2] = 'M';word[9][3] = 'A';word[9][4] = 'N';

            char ltr1 =word[wordNb][0];
            char ltr2 =word[wordNb][1];
            char ltr3 =word[wordNb][2];
            char ltr4 =word[wordNb][3];
            char ltr5 =word[wordNb][4];

            System.out.print(ltr1);
            System.out.print(ltr2);
            System.out.print(ltr3);
            System.out.print(ltr4);
            System.out.println(ltr5);

            wordQst[0] = ltr1;wordQst[1] = ltr2;wordQst[2] = ltr3;wordQst[3] = ltr4;wordQst[4] = ltr5;


            Label label1 = new Label(String.valueOf(ltr1));
            Label label2 = new Label(String.valueOf(ltr2));
            Label label3 = new Label(String.valueOf(ltr3));
            Label label4 = new Label(String.valueOf(ltr4));
            Label label5 = new Label(String.valueOf(ltr5));

            label1.setFont(customFont);
            label2.setFont(customFont);
            label3.setFont(customFont);
            label4.setFont(customFont);
            label5.setFont(customFont);


            StackPane pane1 = new StackPane(label1);
            StackPane pane2 = new StackPane(label2);
            StackPane pane3 = new StackPane(label3);
            StackPane pane4 = new StackPane(label4);
            StackPane pane5 = new StackPane(label5);

            pane1.setPrefSize(100, 50);
            pane2.setPrefSize(100, 50);
            pane3.setPrefSize(100, 50);
            pane4.setPrefSize(100, 50);
            pane5.setPrefSize(100, 50);


            pane1.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane2.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane3.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane4.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane5.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");

            label1.setManaged(false);
            label1.setVisible(false);
            label2.setManaged(false);
            label2.setVisible(false);
            label3.setManaged(false);
            label3.setVisible(false);
            label4.setManaged(false);
            label4.setVisible(false);
            label5.setManaged(false);
            label5.setVisible(false);

            labelList.add(label1);
            labelList.add(label2);
            labelList.add(label3);
            labelList.add(label4);
            labelList.add(label5);

            hbox.getChildren().addAll(pane1, pane2, pane3, pane4, pane5);



        }else if(size==6){
            char[][] word = new char[10][6];
            word[0][0] = 'C';word[0][1] = 'O';word[0][2] = 'U';word[0][3] = 'P';word[0][4] = 'L';word[0][5] = 'E';
            word[1][0] = 'T';word[1][1] = 'A';word[1][2] = 'B';word[1][3] = 'L';word[1][4] = 'E';word[1][5] = 'S';
            word[2][0] = 'C';word[2][1] = 'H';word[2][2] = 'A';word[2][3] = 'N';word[2][4] = 'G';word[2][5] = 'E';
            word[3][0] = 'C';word[3][1] = 'O';word[3][2] = 'L';word[3][3] = 'U';word[3][4] = 'M';word[3][5] = 'N';
            word[4][0] = 'B';word[4][1] = 'E';word[4][2] = 'A';word[4][3] = 'U';word[4][4] = 'T';word[4][5] = 'Y';
            word[5][0] = 'B';word[5][1] = 'R';word[5][2] = 'I';word[5][3] = 'D';word[5][4] = 'G';word[5][5] = 'E';
            word[6][0] = 'C';word[6][1] = 'A';word[6][2] = 'S';word[6][3] = 'T';word[6][4] = 'L';word[6][5] = 'E';
            word[7][0] = 'D';word[7][1] = 'O';word[7][2] = 'M';word[7][3] = 'A';word[7][4] = 'I';word[7][5] = 'N';
            word[8][0] = 'B';word[8][1] = 'E';word[8][2] = 'H';word[8][3] = 'I';word[8][4] = 'N';word[8][5] = 'D';
            word[9][0] = 'C';word[9][1] = 'O';word[9][2] = 'U';word[9][3] = 'P';word[9][4] = 'L';word[9][5] = 'E';

            char ltr1 =word[wordNb][0];
            char ltr2 =word[wordNb][1];
            char ltr3 =word[wordNb][2];
            char ltr4 =word[wordNb][3];
            char ltr5 =word[wordNb][4];
            char ltr6 =word[wordNb][5];

            System.out.print(ltr1);
            System.out.print(ltr2);
            System.out.print(ltr3);
            System.out.print(ltr4);
            System.out.print(ltr5);
            System.out.println(ltr6);

            wordQst[0] = ltr1;wordQst[1] = ltr2;wordQst[2] = ltr3;wordQst[3] = ltr4;wordQst[4] = ltr5;wordQst[5] = ltr6;


            Label label1 = new Label(String.valueOf(ltr1));
            Label label2 = new Label(String.valueOf(ltr2));
            Label label3 = new Label(String.valueOf(ltr3));
            Label label4 = new Label(String.valueOf(ltr4));
            Label label5 = new Label(String.valueOf(ltr5));
            Label label6 = new Label(String.valueOf(ltr6));

            label1.setFont(customFont);
            label2.setFont(customFont);
            label3.setFont(customFont);
            label4.setFont(customFont);
            label5.setFont(customFont);
            label6.setFont(customFont);


            StackPane pane1 = new StackPane(label1);
            StackPane pane2 = new StackPane(label2);
            StackPane pane3 = new StackPane(label3);
            StackPane pane4 = new StackPane(label4);
            StackPane pane5 = new StackPane(label5);
            StackPane pane6 = new StackPane(label6);

            pane1.setPrefSize(100, 50);
            pane2.setPrefSize(100, 50);
            pane3.setPrefSize(100, 50);
            pane4.setPrefSize(100, 50);
            pane5.setPrefSize(100, 50);
            pane6.setPrefSize(100, 50);


            pane1.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane2.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane3.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane4.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane5.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            pane6.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");

            label1.setManaged(false);
            label1.setVisible(false);
            label2.setManaged(false);
            label2.setVisible(false);
            label3.setManaged(false);
            label3.setVisible(false);
            label4.setManaged(false);
            label4.setVisible(false);
            label5.setManaged(false);
            label5.setVisible(false);
            label6.setManaged(false);
            label6.setVisible(false);

            labelList.add(label1);
            labelList.add(label2);
            labelList.add(label3);
            labelList.add(label4);
            labelList.add(label5);
            labelList.add(label6);

            hbox.getChildren().addAll(pane1, pane2, pane3, pane4, pane5, pane6);


        }




        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);

        Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true));
        vbox.setBackground(new Background(background));

        vbox.getChildren().add(imageContainer);
        vbox.setAlignment(Pos.CENTER);

        //white container vbox to store the labels and text fields while fixing the contrast of colors to be more eye comforting
        VBox whiteBackgroundContainer = new VBox();
        whiteBackgroundContainer.setStyle("-fx-background-color: rgba(225,225,225,0.7); -fx-border-color: rgb(90,48,58) transparent transparent transparent; -fx-border-width: 10px  ");
        VBox.setVgrow(whiteBackgroundContainer, Priority.ALWAYS);

        //text field of max entry size of 1
        TextField txtFld = new TextField();
        txtFld.setMaxWidth(300);
        int maxLength = 1;
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (change.getControlNewText().length() > maxLength) {
                return null;
            }
            return change;
        };

        txtFld.setTextFormatter(new TextFormatter<>(filter));


        txtFld.setPromptText("Enter Letter");
        txtFld.setAlignment(Pos.CENTER);
        VBox.setMargin(txtFld,new Insets(10,0,0,0) );

        File fontFile2 = new File(getClass().getResource(FONT_PATH).toURI());
        FileInputStream fontInputStream2 = new FileInputStream(fontFile2);
        Font smallerFont = Font.loadFont(fontInputStream2, 30);
        txtFld.setFont(smallerFont);

        //send button to enter the letter
        Button sendBtn = new Button(">>");
        sendBtn.setPrefSize(150, 50);
        sendBtn.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, new CornerRadii(20), new Insets(-5))));
        sendBtn.setFont(smallerFont);
        sendBtn.setStyle("-fx-padding: 10px");
        VBox.setMargin(sendBtn,new Insets(10,10,10,10));
        sendBtn.setDefaultButton(true);

        whiteBackgroundContainer.setAlignment(Pos.CENTER);
        whiteBackgroundContainer.getChildren().add(hbox);
        whiteBackgroundContainer.getChildren().add(txtFld);
        whiteBackgroundContainer.getChildren().add(sendBtn);
        vbox.getChildren().add(whiteBackgroundContainer);


        sendBtn.setOnAction(event -> {
            click.stop();
            click.play();
            String ansLtr=txtFld.getText();

            //checks if the letter is used
            boolean used = checkIfLetterIsUsed(ansLtr);
            if(used == true){

                //if the letter is used a red border shows up on screen and changes the prompt text
                txtFld.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
                txtFld.setPromptText("Letter is used!");

            }else if(txtFld.getText().isEmpty() ||txtFld.getText().matches(" ")){

                //if the field is empty a red border shows up on screen and changes the prompt text
                txtFld.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
                txtFld.setPromptText("Enter a Letter!");
            }else if(!ansLtr.matches("[a-zA-Z]")){

                //if field doesn't contain a letter, error
                txtFld.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
                txtFld.setPromptText("Only Letters!");
            }else if (size == 4) {
                try {
                    //check if letter is correct
                    checkLetter4(ansLtr, wordQst, labelList,imageView,primaryStage,stage);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                try {
                    //announces if you won
                    announceStatus4(labelList,primaryStage,stage,whiteBackgroundContainer);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                //resets the text field styling
                txtFld.setPromptText("Enter a Letter!");
                txtFld.setStyle("-fx-border-color: transparent; -fx-border-width: 0px;");

            }else if(size == 5) {
                try {
                    //same as size 4
                    checkLetter5(ansLtr, wordQst, labelList,imageView,primaryStage,stage);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                try {
                    announceStatus5(labelList,primaryStage,stage,whiteBackgroundContainer);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                txtFld.setPromptText("Enter a Letter!");
                txtFld.setStyle("-fx-border-color: transparent; -fx-border-width: 0px;");
            }else if (size == 6) {
                try {
                    //same as size 4
                    checkLetter6(ansLtr, wordQst, labelList,imageView,primaryStage,stage);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                try {
                    announceStatus6(labelList,primaryStage,stage,whiteBackgroundContainer);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                txtFld.setPromptText("Enter a Letter!");
                txtFld.setStyle("-fx-border-color: transparent; -fx-border-width: 0px;");
            }

            //empties the text field
            txtFld.setText("");
        });


        stage.show();
    }

    private void pvpGame(Stage primaryStage, Font customFont) {

        //method for 2 players(pre playing)
        BorderPane root = new BorderPane();
        Stage stage = new Stage();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        root.setBackground(new Background(background));

        Button backBtn = new Button(" < Back");
        setButtonStyle(backBtn, customFont);
        BorderPane.setMargin(backBtn, new Insets(30));

        backBtn.setOnMouseEntered(e->{
            backBtn.setStyle("-fx-background-color: #6E3510; -fx-text-fill: white;");
            backBtn.setFont(customFont);
        });
        backBtn.setOnMouseExited(e->{
            backBtn.setStyle("-fx-background-color: saddleBrown; -fx-text-fill: white;");
            backBtn.setFont(customFont);
        });


        backBtn.setOnAction(e -> {
            click.stop();
            click.play();
            stage.close();
            primaryStage.show();
        });

        HBox topBar = new HBox(backBtn);
        topBar.setAlignment(Pos.TOP_LEFT);
        VBox.setMargin(backBtn, new Insets(10, 0,0 , 0));
        root.setTop(topBar);

        //title label for 2 players
        Label title = new Label("2 PLAYERS");
        title.setTextFill(Color.SADDLEBROWN);
        title.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), new Insets(-5))));
        title.setFont(Font.font(customFont.getFamily(), 60));
        title.setAlignment(Pos.CENTER);

        StackPane titleContainer = new StackPane(title);
        titleContainer.setAlignment(Pos.CENTER);

        //text field to enter the word chosen
        TextField txtFld = new TextField();
        txtFld.setPromptText("Enter Word");
        txtFld.setFont(customFont);
        txtFld.setPrefHeight(30);
        txtFld.setMaxWidth(450);
        txtFld.setAlignment(Pos.CENTER);

        StackPane txtFldContainer = new StackPane(txtFld);
        StackPane.setMargin(txtFld, new Insets(10, 30, 10, 30));

        //max entry length of 7 characters
        int maxLength = 7;
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (change.getControlNewText().length() > maxLength) {
                return null;
            }
            return change;
        };
        txtFld.setTextFormatter(new TextFormatter<>(filter));

        //enter button styling
        Button enterBtn = new Button("ENTER >");
        enterBtn.setStyle("-fx-padding: 10px");
        setButtonStyle(enterBtn, customFont);
        VBox.setMargin(enterBtn, new Insets(10, 5, 70, 5));
        enterBtn.setDefaultButton(true);

        VBox centerBox = new VBox(20, titleContainer, txtFldContainer, enterBtn);
        centerBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(centerBox, new Insets(50));
        root.setCenter(centerBox);

        enterBtn.setOnMouseEntered(e->{
            enterBtn.setStyle("-fx-background-color: #6E3510; -fx-text-fill: white;");
            enterBtn.setFont(customFont);
        });
        enterBtn.setOnMouseExited(e->{
            enterBtn.setStyle("-fx-background-color: saddleBrown; -fx-text-fill: white;");
            enterBtn.setFont(customFont);
        });

        enterBtn.setOnAction(e -> {
            click.stop();
            click.play();
            String wordChosen = txtFld.getText();
            txtFld.setText("");


            if (wordChosen.length() < 3) {

                //if the word entered is below 3 characters, error
                txtFld.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
                txtFld.setPromptText("Between 3 and 7 letters!");
                return;
            }

            for (int i = 0; i < wordChosen.length(); i++) {
                char c = wordChosen.charAt(i);
                if (!Character.toString(c).matches("[a-zA-Z]")) {

                    //if a character of the word entered isn't a letter , error
                    txtFld.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
                    txtFld.setPromptText("No Spaces or Special Characters!");
                    return;
                }
            }

            try {
                pvpPlay(wordChosen, customFont, primaryStage);
            } catch (URISyntaxException | FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            stage.close();
        });

        primaryStage.close();
        stage.show();

    }
    private void pvpPlay(String word, Font customFont, Stage primaryStage) throws URISyntaxException, FileNotFoundException {
        //playing 2 players

        //resets wrong guess count
        wrongGuessCount = 0;

        //array to store the letters
        String[] wordLtrs = new String[word.length()];
        HBox hbox = new HBox();

        //array lists to store the labels and their stack pane (so we could generalize for all size and not make a loop for each size)
        ArrayList<Label> labelList = new ArrayList<>();
        ArrayList<StackPane> stackPaneList = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            wordLtrs[i] = String.valueOf(word.charAt(i));

            Label letterLabel = new Label(wordLtrs[i]);
            letterLabel.setFont(Font.font(customFont.getFamily(), 50));
            letterLabel.setTextFill(Color.BLACK);
            letterLabel.setVisible(false);

            //sets the letter label in a container and gives it a border bottom
            StackPane letterContainer = new StackPane(letterLabel);
            letterContainer.setPrefSize(75, 75);
            letterContainer.setStyle("-fx-border-color: black; -fx-border-width: 0 0 4px 0;");
            letterContainer.setAlignment(Pos.CENTER);
            letterContainer.setVisible(true);

            labelList.add(letterLabel);
            stackPaneList.add(letterContainer);
            hbox.getChildren().add(letterContainer);
        }

        Stage stage = new Stage();
        stage.setTitle("PLAYING");
        VBox vbox = new VBox();
        vbox.setMinWidth(Region.USE_PREF_SIZE);
        vbox.setMinHeight(Region.USE_PREF_SIZE);
        vbox.setMaxWidth(Double.MAX_VALUE);
        vbox.setMaxHeight(Double.MAX_VALUE);

        //bg image
        Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        vbox.setBackground(new Background(background));

        Image image = new Image(getClass().getResource("/images/hang.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(226.249);
        imageView.setFitHeight(319.999);
        StackPane imageContainer = new StackPane(imageView);
        vbox.getChildren().add(imageContainer);

        imageContainer.setMargin(imageView, new Insets(5));

        vbox.setAlignment(Pos.CENTER);

        //white container same use as in  vs bot
        VBox whiteBackgroundContainer = new VBox();
        whiteBackgroundContainer.setStyle("-fx-background-color: rgba(225,225,225,0.7); -fx-border-color: rgb(90,48,58) transparent transparent transparent; -fx-border-width: 10px  ");
        VBox.setVgrow(whiteBackgroundContainer, Priority.ALWAYS);

        TextField txtFld = new TextField();
        txtFld.setMaxWidth(350);

        //max entry size 1
        int maxLength = 1;
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (change.getControlNewText().length() > maxLength) {
                return null;
            }
            return change;
        };

        txtFld.setTextFormatter(new TextFormatter<>(filter));


        txtFld.setPromptText("Enter Letter");
        txtFld.setAlignment(Pos.CENTER);
        VBox.setMargin(txtFld,new Insets(10,0,0,0) );

        File fontFile2 = new File(getClass().getResource(FONT_PATH).toURI());
        FileInputStream fontInputStream2 = new FileInputStream(fontFile2);
        Font smallerFont = Font.loadFont(fontInputStream2, 30);
        txtFld.setFont(smallerFont);

        //enter button styling
        Button sendBtn = new Button(">>");
        sendBtn.setPrefSize(150, 50);
        sendBtn.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, new CornerRadii(20), new Insets(-5))));
        sendBtn.setFont(smallerFont);
        sendBtn.setStyle("-fx-padding: 10px");
        VBox.setMargin(sendBtn,new Insets(10,10,10,10));
        sendBtn.setDefaultButton(true);

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);

        whiteBackgroundContainer.setAlignment(Pos.CENTER);
        whiteBackgroundContainer.getChildren().add(hbox);
        whiteBackgroundContainer.getChildren().add(txtFld);
        whiteBackgroundContainer.getChildren().add(sendBtn);
        vbox.getChildren().add(whiteBackgroundContainer);

        sendBtn.setOnAction(event -> {
            click.stop();
            click.play();
            String ansLtr = txtFld.getText();
            txtFld.setText("");

            if (ansLtr.isEmpty() || ansLtr.matches(" ")) {

                //if field is empty, error
                txtFld.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
                txtFld.setPromptText("Enter a Letter!");
                return;
            }else if(!ansLtr.matches("[a-zA-Z]")){

                //if field doesn't contain a letter, error
                txtFld.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
                txtFld.setPromptText("Only Letters!");
                return;
            }

            boolean used = checkIfLetterIsUsed(ansLtr);
            if (used) {
                //if the letter is usedd, error
                txtFld.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
                txtFld.setPromptText("Letter is used!");
            } else {
                try {
                    //checks if letter is correct
                    checkLetter(ansLtr, wordLtrs, labelList, imageView, stage, primaryStage);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                try {
                    //announces status
                    announceStatus(labelList,primaryStage,stage,whiteBackgroundContainer);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                txtFld.setPromptText("Enter a Letter!");
                txtFld.setStyle("-fx-border-color: transparent; -fx-border-width: 0px;");
            }
        });


        stage.setScene(new Scene(vbox, 800, 600));
        stage.show();
    }
    private int getWord(int size) {

        //randomizer to get number of a row(random word)
        return new Random().nextInt(10);
    }
    private void checkLetter4(String ansLtr, char[] wordQst, List<Label> labels,ImageView imageView,Stage primaryStage,Stage stage) throws FileNotFoundException, URISyntaxException {
        boolean correctGuess = false;
        //checks if letter is correct, it sets its visibility to true
        for (int i = 0; i < wordQst.length; i++) {
            if (String.valueOf(wordQst[i]).equalsIgnoreCase(ansLtr)) {
                labels.get(i).setVisible(true);
                labels.get(i).setManaged(true);
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            //if not wrong guess count increases by 1
            wrongGuessCount++;
            hangManImg(wrongGuessCount, imageView,primaryStage,stage,wordQst);
        }
    }
    private void checkLetter5(String ansLtr, char[] wordQst, List<Label> labels, ImageView imageView,Stage primaryStage,Stage stage) throws FileNotFoundException, URISyntaxException {
        //same as 4
        boolean correctGuess = false;
        for (int i = 0; i < wordQst.length; i++) {
            if (String.valueOf(wordQst[i]).equalsIgnoreCase(ansLtr)) {
                labels.get(i).setVisible(true);
                labels.get(i).setManaged(true);
                correctGuess = true;
            }

        }
        if (!correctGuess) {
            wrongGuessCount++;
            hangManImg(wrongGuessCount, imageView,primaryStage,stage,wordQst);
        }

    }
    private void checkLetter6(String ansLtr, char[] wordQst, List<Label> labels, ImageView imageView,Stage primaryStage,Stage stage) throws FileNotFoundException, URISyntaxException {
        //same as 4
        boolean correctGuess = false;
        for (int i = 0; i < wordQst.length; i++) {
            if (String.valueOf(wordQst[i]).equalsIgnoreCase(ansLtr)) {
                labels.get(i).setVisible(true);
                labels.get(i).setManaged(true);
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            wrongGuessCount++;
            hangManImg(wrongGuessCount, imageView,primaryStage,stage,wordQst);
        }
    }
    private void checkLetter(String ansLtr,String[] wordLtrs,ArrayList<Label> labelList,ImageView imageView,Stage stage,Stage primaryStage ) throws FileNotFoundException, URISyntaxException {
        //same as 4
        boolean correctGuess = false;
        for (int i = 0; i < wordLtrs.length; i++) {
            if(wordLtrs[i].equalsIgnoreCase(ansLtr)){
                Label currentLabel = labelList.get(i);
                currentLabel.setVisible(true);
                currentLabel.setManaged(true);
                correctGuess = true;
            }
        }
        char[] wordQst = new char[wordLtrs.length];
        for (int i = 0; i < wordLtrs.length; i++) {
            wordQst[i] = wordLtrs[i].charAt(0);
        }
        if (!correctGuess) {
            wrongGuessCount++;
            hangManImg(wrongGuessCount, imageView,primaryStage,stage,wordQst);
        }

    }
    private void announceStatus4(List<Label>labels, Stage primaryStage,Stage stage,VBox whiteContainer) throws FileNotFoundException, URISyntaxException {
        //audio when winning
        String winFile = "/audios/victory_sound.mp3";
        Media win = new Media(getClass().getResource(winFile).toExternalForm());
        MediaPlayer winPlay = new MediaPlayer(win);

        if(labels.get(0).isVisible()&&labels.get(1).isVisible()&&labels.get(2).isVisible()&&labels.get(3).isVisible()){
            //if all labels are visible, new stage to announce win
            winPlay.play();
            Label winMsg = new Label("Victory!");
            Button winBtn = new Button("Back Home");
            winBtn.setMaxSize(200 , 50);
            winMsg.setTextFill(Color.WHITE);
            winMsg.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(20), new Insets(-5))));

            File fontFile = new File(getClass().getResource(FONT_PATH).toURI());
            FileInputStream fontInputStream = new FileInputStream(fontFile);

            Font customFont = Font.loadFont(fontInputStream, 60);
            winMsg.setFont(customFont);
            setButtonStyle(winBtn,customFont);
            winBtn.setFont(Font.font(customFont.getFamily(), 24));
            winBtn.setDefaultButton(true);


            Scene scene = stage.getScene();
            stage.setMaxHeight(700);
            stage.setMaxWidth(900);

            VBox vbox = new VBox();
            try {
                //background img
                Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());

                BackgroundImage background = new BackgroundImage(
                        backgroundImage,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, true, true)
                );

                vbox.setBackground(new Background(background));
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
            }

            vbox.setAlignment(Pos.CENTER);

            vbox.getChildren().add(winMsg);
            vbox.getChildren().add(winBtn);
            VBox.setMargin(winBtn,new Insets(30) );

            scene.setRoot(vbox);
            stage.centerOnScreen();

            //get you back to home screen
            winBtn.setOnAction(e ->{
                click.stop();
                click.play();
                stage.close();
                primaryStage.show();
                ltrs.clear();
                winPlay.stop();
            });

        }

    }
    private void announceStatus5(List<Label>labels, Stage primaryStage,Stage stage,VBox whiteContainer) throws URISyntaxException, FileNotFoundException {
        //same as 4
        String winFile = "/audios/victory_sound.mp3";
        Media win = new Media(getClass().getResource(winFile).toExternalForm());
        MediaPlayer winPlay = new MediaPlayer(win);

        if (labels.get(0).isVisible() && labels.get(1).isVisible() && labels.get(2).isVisible() && labels.get(3).isVisible() && labels.get(4).isVisible()){
            winPlay.play();
        Label winMsg = new Label("Victory!");
        Button winBtn = new Button("Back Home");
        winBtn.setMaxSize(200, 50);
        winMsg.setTextFill(Color.WHITE);
        winMsg.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(20), new Insets(-5))));


        File fontFile = new File(getClass().getResource(FONT_PATH).toURI());
        FileInputStream fontInputStream = new FileInputStream(fontFile);

        Font customFont = Font.loadFont(fontInputStream, 60);
        winMsg.setFont(customFont);
        setButtonStyle(winBtn, customFont);
        winBtn.setFont(Font.font(customFont.getFamily(), 24));
        winBtn.setDefaultButton(true);


        Scene scene = stage.getScene();
        stage.setMaxHeight(700);
        stage.setMaxWidth(900);

        VBox vbox = new VBox();
        try {
            //background img
            Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());

            BackgroundImage background = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, true)
            );

            vbox.setBackground(new Background(background));
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }

        vbox.setAlignment(Pos.CENTER);


        vbox.getChildren().add(winMsg);
        vbox.getChildren().add(winBtn);
        VBox.setMargin(winBtn, new Insets(30));

        scene.setRoot(vbox);
        stage.centerOnScreen();

        winBtn.setOnAction(e -> {
            click.stop();
            click.play();
            stage.close();
            primaryStage.show();
            ltrs.clear();
            winPlay.stop();
        });
    }
    }
    private void announceStatus6(List<Label>labels, Stage primaryStage, Stage stage, VBox whiteContainer) throws URISyntaxException, FileNotFoundException {
        //same as 4
        String winFile = "/audios/victory_sound.mp3";
        Media win = new Media(getClass().getResource(winFile).toExternalForm());
        MediaPlayer winPlay = new MediaPlayer(win);

        if(labels.get(0).isVisible()&&labels.get(1).isVisible()&&labels.get(2).isVisible()&&labels.get(3).isVisible()&&labels.get(4).isVisible()&&labels.get(5).isVisible()) {
            winPlay.play();
            Label winMsg = new Label("Victory!");
            Button winBtn = new Button("Back Home");
            winBtn.setMaxSize(200, 50);
            winMsg.setTextFill(Color.WHITE);
            winMsg.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(20), new Insets(-5))));

            File fontFile = new File(getClass().getResource(FONT_PATH).toURI());
            FileInputStream fontInputStream = new FileInputStream(fontFile);

            Font customFont = Font.loadFont(fontInputStream, 60);
            winMsg.setFont(customFont);
            setButtonStyle(winBtn, customFont);
            winBtn.setFont(Font.font(customFont.getFamily(), 24));
            winBtn.setDefaultButton(true);


            Scene scene = stage.getScene();
            stage.setMaxHeight(700);
            stage.setMaxWidth(900);

            VBox vbox = new VBox();
            try {
                //background img
                Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());

                BackgroundImage background = new BackgroundImage(
                        backgroundImage,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, true, true)
                );

                vbox.setBackground(new Background(background));
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
            }

            vbox.setAlignment(Pos.CENTER);

            whiteContainer.setVisible(false);
            vbox.getChildren().add(winMsg);
            vbox.getChildren().add(winBtn);
            VBox.setMargin(winBtn, new Insets(30));

            scene.setRoot(vbox);
            stage.centerOnScreen();

            winBtn.setOnAction(e -> {
                click.stop();
                click.play();
                stage.close();
                primaryStage.show();
                ltrs.clear();
                winPlay.stop();
            });
        }
    }
    private void announceStatus(ArrayList<Label> labels, Stage primaryStage, Stage stage, VBox whiteContainer) throws URISyntaxException, FileNotFoundException {
        //same as 4
        int nb = 0;
        String winFile = "/audios/victory_sound.mp3";
        Media win = new Media(getClass().getResource(winFile).toExternalForm());
        MediaPlayer winPlay = new MediaPlayer(win);

        for(int i = 0; i<labels.size();i++){
            if(labels.get(i).isVisible()){
                nb++;
            }
        }
        if(nb == labels.size()){
            Label winMsg = new Label("Victory!");
            Button winBtn = new Button("Back Home");
            winPlay.play();
            winBtn.setMaxSize(200, 50);
            winMsg.setTextFill(Color.WHITE);
            winMsg.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(20), new Insets(-5))));

            File fontFile = new File(getClass().getResource(FONT_PATH).toURI());
            FileInputStream fontInputStream = new FileInputStream(fontFile);

            Font customFont = Font.loadFont(fontInputStream, 60);
            winMsg.setFont(customFont);
            setButtonStyle(winBtn, customFont);
            winBtn.setFont(Font.font(customFont.getFamily(), 24));
            winBtn.setDefaultButton(true);


            Scene scene = stage.getScene();
            stage.setMaxHeight(700);
            stage.setMaxWidth(900);

            VBox vbox = new VBox();
            try {
                //background img
                Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());

                BackgroundImage background = new BackgroundImage(
                        backgroundImage,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, true, true)
                );

                vbox.setBackground(new Background(background));
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
            }

            vbox.setAlignment(Pos.CENTER);

            whiteContainer.setVisible(false);
            vbox.getChildren().add(winMsg);
            vbox.getChildren().add(winBtn);
            VBox.setMargin(winBtn, new Insets(30));

            scene.setRoot(vbox);
            stage.centerOnScreen();

            winBtn.setOnAction(e -> {
                click.stop();
                click.play();
                stage.close();
                primaryStage.show();
                ltrs.clear();
                winPlay.stop();
            });
        }
    }
    private void  hangManImg(int nb, ImageView img,Stage primaryStage, Stage stage, char[] wordQst) throws URISyntaxException, FileNotFoundException {
        System.out.println(nb);
        switch (nb) {
            case 1:
                img.setImage(new Image(getClass().getResource("/images/HEAD.png").toExternalForm()));
                break;
            case 2:
                img.setImage(new Image(getClass().getResource("/images/BODY.png").toExternalForm()));
                break;
            case 3:
                img.setImage(new Image(getClass().getResource("/images/LEFT_ARM.png").toExternalForm()));
                break;
            case 4:
                img.setImage(new Image(getClass().getResource("/images/RIGHT_ARM.png").toExternalForm()));
                break;
            case 5:
                img.setImage(new Image(getClass().getResource("/images/LEFT_LEG.png").toExternalForm()));
                break;
            case 6:
                img.setImage(new Image(getClass().getResource("/images/RIGHT_LEG.png").toExternalForm()));
                break;

        }
        if(nb==6){
            String lossSound = "/audios/loss_audio.mp3";
            Media loss = new Media(getClass().getResource(lossSound).toExternalForm());
            MediaPlayer lossPlay = new MediaPlayer(loss);
            lossPlay.play();


            Label lossMsg = new Label("You Lost!");
            Label wordWas = new Label("Your Word Was: ");
            String fullWord = new String(wordQst);
            Label revealedWord = new Label(fullWord);

            Button lossBtn = new Button("Back Home");
            lossBtn.setMaxSize(200, 50);
            lossMsg.setTextFill(Color.WHITE);
            lossMsg.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(20), new Insets(-5))));

            File fontFile = new File(getClass().getResource(FONT_PATH).toURI());
            FileInputStream fontInputStream = new FileInputStream(fontFile);

            Font customFont = Font.loadFont(fontInputStream, 60);
            lossMsg.setFont(customFont);
            wordWas.setFont(Font.font(customFont.getFamily(), 24));
            revealedWord.setFont(Font.font(customFont.getFamily(), 24));

            setButtonStyle(lossBtn, customFont);
            lossBtn.setFont(Font.font(customFont.getFamily(), 24));


            Scene scene = stage.getScene();
            stage.setMaxHeight(700);
            stage.setMaxWidth(900);

            VBox vbox = new VBox();
            try {
                //background img
                Image backgroundImage = new Image(getClass().getResource("/images/bg_image.jpg").toExternalForm());

                BackgroundImage background = new BackgroundImage(
                        backgroundImage,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, true, true)
                );

                vbox.setBackground(new Background(background));
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
            }

            vbox.setAlignment(Pos.CENTER);
            ArrayList<Label> wordLtrLabel = new ArrayList<>();

            vbox.getChildren().add(img);
            vbox.getChildren().add(lossMsg);
            vbox.getChildren().add(wordWas);
            vbox.getChildren().add(revealedWord);
            vbox.getChildren().add(lossBtn);
            VBox.setMargin(lossBtn, new Insets(30));
            VBox.setMargin(wordWas, new Insets(30, 0 , 10 ,0));

            scene.setRoot(vbox);
            stage.centerOnScreen();

            lossBtn.setOnAction(e -> {
                click.stop();
                click.play();
                stage.close();
                primaryStage.show();
                ltrs.clear();
                lossPlay.stop();
            });
        }

    }
    private boolean checkIfLetterIsUsed(String ltr) {

        for (int i = 0; i < ltrs.size(); i++) {
            if (ltrs.get(i).equals(ltr)) {
                return true;
            }
        }
        ltrs.add(ltr);
        return false;

    }

}
