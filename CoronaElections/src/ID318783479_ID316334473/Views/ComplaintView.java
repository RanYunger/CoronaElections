package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ComplaintView extends View {
	// Constants

	// Fields
	private VBox vBox;
	private HBox criticismHBox, windowNameHBox, controlNameHBox, dscriptionHBox;
	private Label headerLabel, criticismLabel, windowNameLabel, controlNameLabel, descriptionLabel;
	private ImageView bugsImageView;
	private ComboBox<String> windowNameComboBox;
	private TextField controlNameTextField;
	private TextArea descriptionTextArea;
	private Button submitButton;

	// Properties (Getters and Setters)
	public ComboBox<String> getWindowNameComboBox() {
		return windowNameComboBox;
	}

	public TextField getControlNameTextField() {
		return controlNameTextField;
	}

	public TextArea getDescriptionTextArea() {
		return descriptionTextArea;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	// Constructors
	public ComplaintView() {
		super();

		buildScene();
	}

	// Methods
	@Override
	protected void buildScene() {
		String[] windowNames = { "About", "Ballots", "Citizens", "Elections", "Parties" };
		double sceneWidth = 700, sceneHeight = 520;

		vBox = new VBox();
		criticismHBox = new HBox();
		windowNameHBox = new HBox();
		controlNameHBox = new HBox();
		dscriptionHBox = new HBox();

		bugsImageView = UIHandler.buildImage("Bugs.png", 100, 100);
		headerLabel = new Label("Legit Complaint Form");
		criticismLabel = new Label(
				"Your criticism is important to us.\r\nTell us how we can improve so we can provide you with\r\nthe best and highest quality software products.");
		windowNameLabel = new Label("Window Name:");
		controlNameLabel = new Label("Control/s Name:");
		descriptionLabel = new Label("Describe the bug/s:");
		windowNameComboBox = new ComboBox<String>(FXCollections.observableArrayList(windowNames));
		controlNameTextField = new TextField();
		descriptionTextArea = new TextArea();
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(30));
		criticismLabel.setFont(new Font(20));
		windowNameLabel.setFont(new Font(20));
		controlNameLabel.setFont(new Font(20));
		descriptionLabel.setFont(new Font(20));
		windowNameComboBox.setMinWidth(400);
		controlNameTextField.setMinWidth(400);
		submitButton.setFont(new Font(20));

		criticismHBox.setAlignment(Pos.CENTER_LEFT);
		criticismHBox.getChildren().addAll(criticismLabel, bugsImageView);
		HBox.setMargin(criticismLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(bugsImageView, new Insets(0, 10, 0, 45));
		
		windowNameHBox.setAlignment(Pos.CENTER_LEFT);
		windowNameHBox.getChildren().addAll(windowNameLabel, windowNameComboBox);
		HBox.setMargin(windowNameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(windowNameComboBox, new Insets(0, 10, 0, 45));

		controlNameHBox.setAlignment(Pos.CENTER_LEFT);
		controlNameHBox.getChildren().addAll(controlNameLabel, controlNameTextField);
		HBox.setMargin(controlNameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(controlNameTextField, new Insets(0, 10, 0, 35));

		dscriptionHBox.setAlignment(Pos.TOP_LEFT);
		dscriptionHBox.getChildren().addAll(descriptionLabel, descriptionTextArea);
		HBox.setMargin(descriptionLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(descriptionTextArea, new Insets(0, 10, 0, 10));

		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.getChildren().addAll(headerLabel, criticismHBox, windowNameHBox, controlNameHBox, dscriptionHBox, submitButton);
		VBox.setMargin(headerLabel, new Insets(2, 0, 8, 0));
		VBox.setMargin(criticismHBox, new Insets(10, 0, 8, 0));
		VBox.setMargin(windowNameHBox, new Insets(10, 0, 8, 0));
		VBox.setMargin(controlNameHBox, new Insets(10, 0, 8, 0));
		VBox.setMargin(dscriptionHBox, new Insets(10, 0, 8, 0));
		VBox.setMargin(submitButton, new Insets(10, 0, 8, 0));

		UIHandler.setGeneralFeatures(stage);
		stage.setScene(new Scene(vBox, sceneWidth, sceneHeight));
		addEffects();

		stage.show();
	}

	@Override
	protected void addEffects() {
		UIHandler.addCursorEffectsToNode(stage.getScene(), submitButton);
	}
}
