package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddBallotModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddBallotView {
	// Constants

	// Fields
	private Group root;
	private Stage stage;
	private VBox vBox;
	private HBox mainHBox, row1HBox, row2HBox;
	private ImageView ballotImageView;
	private Label headerLabel, addressLabel, typeLabel;
	private TextField addressTextField;
	private ComboBox<String> typeComboBox;
	private Button submitButton;

	// Properties (Getters and Setters)
	public void setRoot(Group root) {
		this.root = root;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// Constructors
	public AddBallotView(Stage stage, LocalDate electionsDate) {
		setRoot(new Group());
		setStage(stage);
		
		buildScene(electionsDate);
	}

	// Methods
	public void refresh(AddBallotModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(LocalDate electionsDate) {
		String[] ballotTypes = { "Regular (Citizens / Candidates)", "Military (Soldiers)", "Sick Citizens",
				"Sick Candidates", "Sick Soldiers" };
		double sceneWidth = 600, sceneHeight = 260, fontSize = 30;

		vBox = new VBox();
		mainHBox = new HBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		ballotImageView = UIHandler.buildImage("Ballot.png", sceneHeight * 0.6, sceneHeight * 0.6);
		headerLabel = new Label("New Ballot");
		addressLabel = new Label("Address:");
		typeLabel = new Label("Type:");
		addressTextField = new TextField();
		typeComboBox = new ComboBox<String>(FXCollections.observableArrayList(ballotTypes));
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(30));
		addressLabel.setFont(new Font(20));
		typeLabel.setFont(new Font(20));
		addressTextField.setMinWidth(210);
		submitButton.setFont(new Font(20));

		row1HBox.getChildren().addAll(addressLabel, addressTextField);
		HBox.setMargin(addressLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(addressTextField, new Insets(0, 10, 0, 10));

		row2HBox.getChildren().addAll(typeLabel, typeComboBox);
		HBox.setMargin(typeLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(typeComboBox, new Insets(0, 10, 0, 40));

		vBox.getChildren().addAll(headerLabel, row1HBox, row2HBox, submitButton);
		vBox.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(headerLabel, new Insets(sceneHeight * 0.2, 0, 0, 0));

		mainHBox.getChildren().addAll(vBox, ballotImageView);
		HBox.setMargin(ballotImageView, new Insets(sceneHeight * 0.2, 0, sceneHeight * 0.2, sceneHeight * 0.3));

		stage.setTitle(String.format("Corona Elections [%s %d]", electionsDate.getMonth().toString(), electionsDate.getYear()));
		stage.setResizable(false);
		UIHandler.setIcon(stage);
		stage.setScene(new Scene(UIHandler.buildBackground(mainHBox, sceneWidth, sceneHeight, fontSize, false), sceneWidth, sceneHeight));
		stage.show();
	}

	public Node getNodeByName(String nodeName) {
		try {
			return (Node) getClass().getDeclaredField(nodeName).get(this);
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}

		return null;
	}

	public Object getPropertyByName(String nodeName, String propertyName) {
		Node node = getNodeByName(nodeName);

		return node.getProperties().get(propertyName);
	}

	public void addEventHandlerToButton(String buttonName, EventHandler<ActionEvent> eventHandler) {
		Button requiredButton = (Button) getNodeByName(buttonName);

		requiredButton.setOnAction(eventHandler);
	}
}
