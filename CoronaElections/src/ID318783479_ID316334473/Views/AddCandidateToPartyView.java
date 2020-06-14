package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddCandidateToPartyView {
	// Constants

	// Fields
	private Stage stage;
	private VBox vBox;
	private HBox mainHBox, row1HBox;
	private ImageView candidateImageView;
	private Label headerLabel, candidateIDLabel, candidateNameLabel;
	private TextField candidateIDTextField, candidateNameTextField;
	private TableView<CitizenModel> citizensTableView;
	private Button submitButton;

	// Properties (Getters and Setters)
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// Constructors
	public AddCandidateToPartyView(Stage stage, LocalDate electionsDate, ObservableList<CitizenModel> citizens) {
		setStage(stage);

		buildScene(electionsDate, citizens);
	}

	// Methods
	@SuppressWarnings("unchecked")
	private void buildScene(LocalDate electionsDate, ObservableList<CitizenModel> citizens) {
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
		citizensTableView.setItems(citizens);
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(20));
		candidateIDLabel.setFont(new Font(20));
		candidateNameLabel.setFont(new Font(20));
		candidateIDTextField.setMinWidth(150);
		candidateNameTextField.setMinWidth(150);
		submitButton.setFont(new Font(20));

		citizenIDTableColumn = new TableColumn<CitizenModel, Number>("ID");
		citizenIDTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableID());
		citizenIDTableColumn.setMinWidth(225);

		citizenNameTableColumn = new TableColumn<CitizenModel, String>("Full Name");
		citizenNameTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableFullName());
		citizenNameTableColumn.setMinWidth(225);

		citizensTableView.getColumns().addAll(citizenIDTableColumn, citizenNameTableColumn);
		for (TableColumn<?, ?> tableColumn : citizensTableView.getColumns()) {
			tableColumn.setReorderable(false);
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

		stage.setTitle(String.format("Corona Elections [%s %d]", electionsDate.getMonth().toString(),
				electionsDate.getYear()));
		stage.setResizable(false);
		stage.setScene(new Scene(UIHandler.buildBackground(mainHBox, sceneWidth, sceneHeight, fontSize, false),
				sceneWidth, sceneHeight));

		UIHandler.setIcon(stage);
		UIHandler.addCursorEffectsToNode(stage.getScene(), submitButton);

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

	public void addChangeListenerToTextField(String textFieldName, ChangeListener<String> changeListener) {
		TextField requiredTextField = (TextField) getNodeByName(textFieldName);

		requiredTextField.textProperty().addListener(changeListener);
	}

	public void close() {
		stage.close();
	}
}
