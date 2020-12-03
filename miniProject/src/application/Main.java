package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * 항시 이게 기본 와꾸!!!
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LoginPane.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Java Jive Coffee");
			primaryStage.getIcons().add(new Image("image/icons8_j_50px.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
