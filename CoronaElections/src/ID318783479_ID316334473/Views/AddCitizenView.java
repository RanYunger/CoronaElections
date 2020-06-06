package ID318783479_ID316334473.Views;

import java.time.YearMonth;
import java.util.ArrayList;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddCitizenModel;
import ID318783479_ID316334473.Models.BallotModel;
import ID318783479_ID316334473.Models.BallotsTabModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddCitizenView {
	// Constants

	// Fields
	private Group root;
	private VBox vBox;
	private HBox mainHBox, row1HBox, row2HBox, row3HBox, row4HBox, row5HBox, row6HBox;
	private ImageView citizenImageView;
	private Label headerLabel, IDLabel, nameLabel, yearOfBirthLabel, daysOfSicknessLabel, associatedBallotLabel,
			statusLabel;
	private TextField IDTextField, nameTextField;
	private ComboBox<Integer> yearOfBirthComboBox, daysOfSicknessComboBox, associatedBallotComboBox;
	private GridPane gridPane;
	private CheckBox isolatedCheckBox, wearingSuitCheckBox, soldierCheckBox, carryingWeaponCheckBox;
	private Button submitButton;

	// Properties (Getters and Setters)

	// Constructors
	public AddCitizenView(Stage stage, YearMonth electionsDate) {
		root = new Group();

		buildScene(stage, electionsDate);
	}

	// Methods
	public void refresh(AddCitizenModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(Stage stage, YearMonth electionsDate) {
		double sceneWidth = 950, sceneHeight = 500;
		int maxBorderYear = YearMonth.now().getYear() - 18, minBorderYear = maxBorderYear - 82;
		ArrayList<Integer> years = new ArrayList<Integer>(), daysOfSickness = new ArrayList<Integer>();

		for (int currentYear = maxBorderYear; currentYear >= minBorderYear; currentYear--)
			years.add(currentYear);
		for (int i = 1; i <= 100; i++)
			daysOfSickness.add(i);

		vBox = new VBox();
		mainHBox = new HBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		row3HBox = new HBox();
		row4HBox = new HBox();
		row5HBox = new HBox();
		row6HBox = new HBox();
		citizenImageView = UIHandler.buildImage("Citizen.png", sceneHeight, sceneHeight);
		headerLabel = new Label("New Citizen");
		IDLabel = new Label("ID:");
		nameLabel = new Label("Full Name:");
		yearOfBirthLabel = new Label("Year of Birth:");
		daysOfSicknessLabel = new Label("Days of Sickness:");
		associatedBallotLabel = new Label("Associated Ballot:");
		statusLabel = new Label("Status:");
		IDTextField = new TextField();
		nameTextField = new TextField();
		yearOfBirthComboBox = new ComboBox<Integer>(FXCollections.observableArrayList(years));
		daysOfSicknessComboBox = new ComboBox<Integer>(FXCollections.observableArrayList(daysOfSickness));
		associatedBallotComboBox = new ComboBox<Integer>();
		gridPane = new GridPane();
		isolatedCheckBox = new CheckBox("Isolated");
		wearingSuitCheckBox = new CheckBox("Wearing Suit");
		soldierCheckBox = new CheckBox("Soldier");
		carryingWeaponCheckBox = new CheckBox("Carrrying Weapon");
		submitButton = new Button("Submit");

		refreshAssociatedBallotComboBox(false, false);
		headerLabel.setFont(new Font(30));
		IDLabel.setFont(new Font(20));
		nameLabel.setFont(new Font(20));
		yearOfBirthLabel.setFont(new Font(20));
		daysOfSicknessLabel.setFont(new Font(20));
		associatedBallotLabel.setFont(new Font(20));
		statusLabel.setFont(new Font(20));
		IDTextField.setMinWidth(210);
		nameTextField.setMinWidth(210);
		yearOfBirthComboBox.setMinWidth(210);
		daysOfSicknessComboBox.setMinWidth(210);
		associatedBallotComboBox.setMinWidth(210);
		daysOfSicknessComboBox.setDisable(true);
		soldierCheckBox.setDisable(true);
		carryingWeaponCheckBox.setDisable(true);
		submitButton.setFont(new Font(20));

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(50);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(50);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(50);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(50);

		gridPane.add(isolatedCheckBox, 0, 0);
		gridPane.add(wearingSuitCheckBox, 0, 1);
		gridPane.add(soldierCheckBox, 1, 0);
		gridPane.add(carryingWeaponCheckBox, 1, 1);

		row1HBox.getChildren().addAll(IDLabel, IDTextField);
		HBox.setMargin(IDLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(IDTextField, new Insets(0, 10, 0, 145));

		row2HBox.getChildren().addAll(nameLabel, nameTextField);
		HBox.setMargin(nameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(nameTextField, new Insets(0, 10, 0, 75));

		row3HBox.getChildren().addAll(yearOfBirthLabel, yearOfBirthComboBox);
		HBox.setMargin(yearOfBirthLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(yearOfBirthComboBox, new Insets(0, 10, 0, 55));

		row4HBox.getChildren().addAll(daysOfSicknessLabel, daysOfSicknessComboBox);
		HBox.setMargin(daysOfSicknessLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(daysOfSicknessComboBox, new Insets(0, 10, 0, 20));

		row5HBox.getChildren().addAll(associatedBallotLabel, associatedBallotComboBox);
		HBox.setMargin(associatedBallotLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(associatedBallotComboBox, new Insets(0, 10, 0, 15));

		row6HBox.getChildren().addAll(statusLabel, gridPane);
		HBox.setMargin(statusLabel, new Insets(0, 40, 0, 10));
		HBox.setMargin(gridPane, new Insets(0, 10, 0, 80));

		vBox.getChildren().addAll(headerLabel, row1HBox, row2HBox, row3HBox, row4HBox, row5HBox, row6HBox,
				submitButton);
		vBox.setAlignment(Pos.TOP_CENTER);

		mainHBox.getChildren().addAll(vBox, citizenImageView);

		VBox.setMargin(headerLabel, new Insets(0, 0, sceneHeight * 0.1, 0));
		VBox.setMargin(row1HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row2HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row3HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row4HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row5HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row6HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(submitButton, new Insets(10, 0, 10, 0));

		stage.setTitle(String.format("Corona Elections [%s %d]", electionsDate.getMonth().toString(), electionsDate.getYear()));
		stage.setResizable(false);
		stage.getIcons().add(UIHandler.buildImage("Elections.jpg", 0, 0).getImage());
		stage.setScene(new Scene(/* UIHandler.buildBackground(gridPane, sceneWidth, sceneHeight) */ mainHBox,
				sceneWidth, sceneHeight));
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

	public void addEventHandlerToComboBox(String comboBoxName, EventHandler<ActionEvent> eventHandler) {
		ComboBox<?> requiredComboBox = (ComboBox<?>) getNodeByName(comboBoxName);

		requiredComboBox.setOnAction(eventHandler);
	}

	public void addEventHandlerToCheckBox(String checkBoxName, EventHandler<ActionEvent> eventHandler) {
		CheckBox requiredCheckBox = (CheckBox) getNodeByName(checkBoxName);

		requiredCheckBox.setOnAction(eventHandler);
	}

	public void refreshAssociatedBallotComboBox(boolean isIsolated, boolean isSoldier) {
		ArrayList<Integer> ballotIDs = new ArrayList<Integer>();
		BallotsTabModel ballotsTabModel = (BallotsTabModel) UIHandler.getModelByName("BallotsTabModel");
		ArrayList<BallotModel<?>> allBallots = ballotsTabModel.getAllBallots();
		int currentBallotID;

		for (BallotModel<?> ballotModel : allBallots) {
			currentBallotID = ballotModel.getID();
			if (!ballotIDs.contains(currentBallotID)) {
				if ((isIsolated) && (ballotModel.isCoronaBallot()))
					ballotIDs.add(currentBallotID);
				if ((isSoldier) && (ballotModel.isMilitaryBallot()))
					ballotIDs.add(currentBallotID);
			}
		}

		associatedBallotComboBox.getSelectionModel().clearSelection();
		associatedBallotComboBox.setItems(FXCollections.observableArrayList(ballotIDs));
	}
}
