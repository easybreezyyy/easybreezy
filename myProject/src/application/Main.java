package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			System.setProperty("prism.lcdtext", "false");
			Font.loadFont(getClass().getResourceAsStream("/resources/NanumSquareR.ttf"), 15);

			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Our Closet");
			primaryStage.getIcons().add(new Image("image/icons8_closet_30px.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	public static void main(String[] args) {
		launch(args);
	}

}
