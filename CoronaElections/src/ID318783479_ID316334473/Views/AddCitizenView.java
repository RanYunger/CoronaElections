package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddCitizenModel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddCitizenView {
	// Constants
	
	// Fields
	private Group root;
	private GridPane gridPane;
	private Label headerLabel;
	
	// Properties (Getters and Setters)
	
	// Constructors
	public AddCitizenView(Stage stage) {
		root = new Group();
		
		buildScene(stage);
	}
	
	// Methods
	public void refresh(AddCitizenModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}
	
	private void buildScene(Stage stage) {
		double sceneWidth = 400, sceneHeight = 700;
		
		gridPane = new GridPane();
		headerLabel = new Label("Fill in the form");
		
		headerLabel.setFont(new Font(30));
		
		stage.setTitle("Corona Elections");
		stage.setResizable(false);
		stage.getIcons().add(UIHandler.buildImage("Elections.jpg", 0, 0).getImage());
		stage.setScene(new Scene(UIHandler.buildBackground(gridPane, sceneWidth, sceneHeight), sceneWidth, sceneHeight));
		stage.show();	
	}
}
