package ID318783479_ID316334473.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AboutTabView extends Node {
	// Constants
	
	// Fields
	private Group root;
	private VBox vBox;
	private Label projectNameLabel, madeByLabel, dateLabel;
	
	// Properties (Getters and Setters)
	
	// Constructors
	public AboutTabView() {
		root = new Group();
		
		buildScene();
	}
	
	// Methods
//	public void refresh(PartiesTabModel model) {
//		root.getChildren().clear(); // clean the previous view
//		model.show(root);
//	}
	private void buildScene() {
		vBox = new VBox();

		projectNameLabel = new Label("Stupid Ass Project V-1");
		projectNameLabel.setFont(new Font(50));
		madeByLabel = new Label("Developed by: Ran Yunger, Shy Ohev Zion");
		madeByLabel.setFont(new Font(30));
		dateLabel = new Label("June 2020");
		dateLabel.setFont(new Font(30));

		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(projectNameLabel, madeByLabel, dateLabel);
		vBox.setMargin(projectNameLabel, new Insets(0, 10, 0, 0));
		vBox.setMargin(madeByLabel, new Insets(0, 10, 0, 10));
		vBox.setMargin(dateLabel, new Insets(0, 0, 0, 10));
	}
}
