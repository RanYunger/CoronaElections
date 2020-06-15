package ID318783479_ID316334473.Views;

import java.time.LocalDate;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ComplaintView extends View {
	// Constants

	// Fields
	private VBox vBox;
	private HBox row1HBox, row2HBox, row3HBox;
	private Label headerLabel, windowNameLabel, controlNameLabel, descriptionLabel;
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
		LocalDate electionsDate = UIHandler.getElectionsDate();
		String[] windowNames = { "About", "Ballots", "Citizens", "Elections", "Parties" };
		double sceneWidth = 700, sceneHeight = 400;

		vBox = new VBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		row3HBox = new HBox();

		headerLabel = new Label("Complaint Form");
		windowNameLabel = new Label("Window Name:");
		controlNameLabel = new Label("Control/s Name:");
		descriptionLabel = new Label("Describe the bug/s:");
		windowNameComboBox = new ComboBox<String>(FXCollections.observableArrayList(windowNames));
		controlNameTextField = new TextField();
		descriptionTextArea = new TextArea();
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(30));
		windowNameLabel.setFont(new Font(20));
		controlNameLabel.setFont(new Font(20));
		descriptionLabel.setFont(new Font(20));
		windowNameComboBox.setMinWidth(400);
		controlNameTextField.setMinWidth(400);
		submitButton.setFont(new Font(20));

		row1HBox.setAlignment(Pos.CENTER_LEFT);
		row1HBox.getChildren().addAll(windowNameLabel, windowNameComboBox);
		HBox.setMargin(windowNameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(windowNameComboBox, new Insets(0, 10, 0, 45));

		row2HBox.setAlignment(Pos.CENTER_LEFT);
		row2HBox.getChildren().addAll(controlNameLabel, controlNameTextField);
		HBox.setMargin(controlNameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(controlNameTextField, new Insets(0, 10, 0, 35));

		row3HBox.setAlignment(Pos.TOP_LEFT);
		row3HBox.getChildren().addAll(descriptionLabel, descriptionTextArea);
		HBox.setMargin(descriptionLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(descriptionTextArea, new Insets(0, 10, 0, 10));

		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.getChildren().addAll(headerLabel, row1HBox, row2HBox, row3HBox, submitButton);
		VBox.setMargin(headerLabel, new Insets(2, 0, 30, 0));
		VBox.setMargin(row1HBox, new Insets(10, 0, 8, 0));
		VBox.setMargin(row2HBox, new Insets(10, 0, 8, 0));
		VBox.setMargin(row3HBox, new Insets(10, 0, 8, 0));
		VBox.setMargin(submitButton, new Insets(10, 0, 8, 0));

		stage.setTitle(String.format("Corona Elections [%s %d]", electionsDate.getMonth().toString(),
				electionsDate.getYear()));
		stage.setResizable(false);
		stage.setScene(new Scene(vBox, sceneWidth, sceneHeight));

		UIHandler.setIcon(stage);
		UIHandler.addCursorEffectsToNode(stage.getScene(), submitButton);

		stage.show();
	}

	@Override
	protected void addEffects() {
		// TODO Auto-generated method stub
		
	}
}
