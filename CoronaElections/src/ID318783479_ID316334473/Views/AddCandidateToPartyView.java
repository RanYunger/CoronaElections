package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddCandidateToPartyView extends View {
	// Constants

	// Fields
	private VBox vBox;
	private HBox mainHBox, row1HBox;
	private ImageView candidateImageView;
	private Label headerLabel, candidateIDLabel, candidateNameLabel;
	private TextField candidateIDTextField, candidateNameTextField;
	private TableView<CitizenModel> citizensTableView;
	private Button submitButton;

	// Properties (Getters and Setters)
	public TextField getCandidateIDTextField() {
		return candidateIDTextField;
	}

	public TextField getCandidateNameTextField() {
		return candidateNameTextField;
	}

	public TableView<CitizenModel> getCitizensTableView() {
		return citizensTableView;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	// Constructors
	public AddCandidateToPartyView(Stage stage, ObservableList<CitizenModel> citizens) {
		super(stage);

		buildScene();
	}

	// Methods
	@Override
	@SuppressWarnings("unchecked")
	protected void buildScene() {
		TableColumn<CitizenModel, Number> citizenIDTableColumn;
		TableColumn<CitizenModel, String> citizenNameTableColumn;
		double sceneWidth = 850, sceneHeight = 500, fontSize = 40;

		vBox = new VBox();
		mainHBox = new HBox();
		row1HBox = new HBox();
		candidateImageView = UIHandler.buildImage("Candidate.png", sceneHeight * 0.6, sceneHeight * 0.6);
		headerLabel = new Label("New Candidate");
		candidateIDLabel = new Label("ID:");
		candidateNameLabel = new Label("Full Name:");
		candidateIDTextField = new TextField();
		candidateNameTextField = new TextField();
		citizensTableView = new TableView<CitizenModel>();
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(20));
		candidateIDLabel.setFont(new Font(20));
		candidateNameLabel.setFont(new Font(20));
		candidateIDTextField.setMinWidth(150);
		candidateNameTextField.setMinWidth(150);
		submitButton.setFont(new Font(20));

		citizensTableView.getSelectionModel().selectFirst();

		citizenIDTableColumn = new TableColumn<CitizenModel, Number>("ID");
		citizenIDTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableID());
		citizenIDTableColumn.setMinWidth(225);

		citizenNameTableColumn = new TableColumn<CitizenModel, String>("Full Name");
		citizenNameTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableFullName());
		citizenNameTableColumn.setMinWidth(225);

		citizensTableView.getColumns().addAll(citizenIDTableColumn, citizenNameTableColumn);
		for (TableColumn<?, ?> tableColumn : citizensTableView.getColumns()) {
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setSortable(false);
			tableColumn.setResizable(false);
		}
		citizensTableView.setOpacity(0.8);

		row1HBox.getChildren().addAll(candidateIDLabel, candidateIDTextField, candidateNameLabel,
				candidateNameTextField);
		HBox.setMargin(candidateIDLabel, new Insets(0, 5, 0, 5));
		HBox.setMargin(candidateIDTextField, new Insets(0, 5, 0, 5));
		HBox.setMargin(candidateNameLabel, new Insets(0, 5, 0, 15));
		HBox.setMargin(candidateNameTextField, new Insets(0, 5, 0, 5));

		vBox.getChildren().addAll(headerLabel, row1HBox, citizensTableView, submitButton);
		vBox.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(headerLabel, new Insets(sceneHeight * 0.2, 0, 15, 0));
		VBox.setMargin(row1HBox, new Insets(0, 0, 5, 0));
		VBox.setMargin(citizensTableView, new Insets(0, 5, 5, 5));
		VBox.setMargin(submitButton, new Insets(0, 0, 105, 0));

		mainHBox.getChildren().addAll(vBox, candidateImageView);
		HBox.setMargin(candidateImageView, new Insets(sceneHeight * 0.2, 0, sceneHeight * 0.2, sceneHeight * 0.15));

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

	public void refreshCitizensTableView(ObservableList<CitizenModel> citizens) {
		citizensTableView.setItems(citizens);
	}
}
