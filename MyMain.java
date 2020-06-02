package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application {
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {


		FXMLLoader loader = new
				FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load();

		MenuBar menuBar = createMenu();
		rootNode.getChildren().add(0,menuBar);

		Scene scene = new Scene(rootNode, 350, 250);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Hello World");
		primaryStage.show();

	}
	public MenuBar createMenu(){
		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem quitMenuItem = new MenuItem("Quit");

		quitMenuItem.setOnAction(event -> {
			Alert alert = new Alert(Alert.AlertType.NONE);
			alert.setTitle("Warning");
			alert.setHeaderText("Do you really want to exit the application");
			alert.setContentText("Press Yes to exit and press No to retrieve");

			ButtonType yesButton = new ButtonType("Yes");
			ButtonType noButton = new ButtonType("No");

			alert.getButtonTypes().setAll(yesButton,noButton);

			Optional<ButtonType> clickedBtn = alert.showAndWait();

			if(clickedBtn.isPresent() && clickedBtn.get() == yesButton){
				Platform.exit();
				System.exit(0);
			}

		});

		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		fileMenu.getItems().addAll(newMenuItem,separatorMenuItem,quitMenuItem);


		Menu helpMenu = new Menu("Help");
		MenuItem aboutAppMenuItem = new MenuItem("About");
		aboutAppMenuItem.setOnAction(event -> {
			aboutApp();

		});
		helpMenu.getItems().addAll(aboutAppMenuItem);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu,helpMenu);
		return menuBar;
	}

	private void aboutApp() {
	}
}
