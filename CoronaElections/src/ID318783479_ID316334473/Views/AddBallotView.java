package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddBallotView extends View {
	// Constants

	// Fields
	private VBox vBox;
	private HBox mainHBox, ballotAddressHBox, ballotTypeHBox;
	private ImageView ballotImageView;
	private Label headerLabel, addressLabel, typeLabel;
	private TextField ballotAddressTextField;
	private ComboBox<String> ballotTypeComboBox;
	private Button submitButton;

	// Properties (Getters and Setters)
	public TextField getBallotAddressTextField() {
		return ballotAddressTextField;
	}

	public ComboBox<String> getBallotTypeComboBox() {
		return ballotTypeComboBox;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	// Constructors
	public AddBallotView() {
		super();

		buildScene();
	}

	// Methods
	protected void buildScene() {
		String[] ballotTypes = { "Regular (Citizens / Candidates)", "Military (Soldiers)", "Sick Citizens",
				"Sick Soldiers" };
		double sceneWidth = 600, sceneHeight = 260, fontSize = 30;

		vBox = new VBox();
		mainHBox = new HBox();
		ballotAddressHBox = new HBox();
		ballotTypeHBox = new HBox();
		ballotImageView = UIHandler.buildImage("Ballot.png", sceneHeight * 0.6, sceneHeight * 0.6);
		headerLabel = new Label("New Ballot");
		addressLabel = new Label("Address:");
		typeLabel = new Label("Type:");
		ballotAddressTextField = new TextField();
		ballotTypeComboBox = new ComboBox<String>(FXCollections.observableArrayList(ballotTypes));
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(30));
		addressLabel.setFont(new Font(20));
		typeLabel.setFont(new Font(20));
		ballotAddressTextField.setMinWidth(210);
		ballotAddressTextField.setTooltip(new Tooltip("Format: number street, city (capitalized) (i.e. 21 Jump Street, Metropolis)"));
		submitButton.setFont(new Font(20));
		ballotTypeComboBox.getSelectionModel().selectFirst();

		ballotAddressHBox.getChildren().addAll(addressLabel, ballotAddressTextField);
		HBox.setMargin(addressLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(ballotAddressTextField, new Insets(0, 10, 0, 10));

		ballotTypeHBox.getChildren().addAll(typeLabel, ballotTypeComboBox);
		HBox.setMargin(typeLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(ballotTypeComboBox, new Insets(0, 10, 0, 40));

		vBox.getChildren().addAll(headerLabel, ballotAddressHBox, ballotTypeHBox, submitButton);
		vBox.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(headerLabel, new Insets(sceneHeight * 0.2, 0, 0, 0));

		mainHBox.getChildren().addAll(vBox, ballotImageView);
		HBox.setMargin(ballotImageView, new Insets(sceneHeight * 0.2, 0, sceneHeight * 0.2, sceneHeight * 0.3));
		
		UIHandler.setGeneralFeatures(stage);
		stage.setScene(new Scene(UIHandler.buildBackground(mainHBox, sceneWidth, sceneHeight, fontSize, false),
				sceneWidth, sceneHeight));
		addEffects();

		stage.show();
	}

	@Override
	protected void addEffects() {
		UIHandler.addCursorEffectsToNode(stage.getScene(), submitButton);
	}
}
