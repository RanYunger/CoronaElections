package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddBallotModel;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddBallotView {
	// Constants

	// Fields
	private Group root;
	private VBox vBox;
	private HBox row1HBox, row2HBox;
	private Label headerLabel, ballotAdressLabel, ballotTypeLabel;
	private TextField ballotAddressTextField;
	private ComboBox<String> ballotTypeComboBox;
	private Button submitButton;

	// Properties (Getters and Setters)

	// Constructors
	public AddBallotView(Stage stage) {
		root = new Group();

		buildScene(stage);
	}

	// Methods
	public void refresh(AddBallotModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(Stage stage) {
		double sceneWidth = 400, sceneHeight = 400;
		String[] ballotTypes = { "Regular (Citizens / Candidates)", "Military (Soldiers)", "Sick Citizens",
				"Sick Candidates", "Sick Soldiers" };

		vBox = new VBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		headerLabel = new Label("Fill in the form");
		ballotAdressLabel = new Label("Ballot Adress:");
		ballotTypeLabel = new Label("Ballot Type:");
		ballotAddressTextField = new TextField("");
		ballotTypeComboBox = new ComboBox<String>(FXCollections.observableArrayList(ballotTypes));
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(30));
		headerLabel.setAlignment(Pos.CENTER);

		row1HBox.setAlignment(Pos.CENTER);
		row1HBox.getChildren().addAll(ballotAdressLabel, ballotAddressTextField);
		HBox.setMargin(ballotAdressLabel, new Insets(0, 10, 0, 0));
		HBox.setMargin(ballotAddressTextField, new Insets(0, 0, 0, 10));

		row2HBox.setAlignment(Pos.CENTER);
		row2HBox.getChildren().addAll(ballotTypeLabel, ballotTypeComboBox);
		HBox.setMargin(ballotTypeLabel, new Insets(0, 10, 0, 0));
		HBox.setMargin(ballotTypeComboBox, new Insets(0, 0, 0, 10));

		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(headerLabel, row1HBox, row2HBox, submitButton);
		VBox.setMargin(headerLabel, new Insets(0, 0, sceneHeight * 0.3, 0));
		VBox.setMargin(row1HBox, new Insets(30, 0, 30, 0));
		VBox.setMargin(row2HBox, new Insets(30, 0, 30, 0));

		stage.setTitle("Corona Elections");
		stage.setResizable(false);
		stage.getIcons().add(UIHandler.buildImage("Elections.jpg", 0, 0).getImage());
		stage.setScene(new Scene(/* UIHandler.buildBackground(gridPane, sceneWidth, sceneHeight) */ vBox, sceneWidth,
				sceneHeight));
		stage.show();
	}
}
