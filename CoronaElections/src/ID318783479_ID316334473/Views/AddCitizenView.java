package ID318783479_ID316334473.Views;

import java.time.LocalDate;
import java.util.ArrayList;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddCitizenView extends View {
	// Constants

	// Fields
	private VBox vBox;
	private HBox mainHBox, row1HBox, row2HBox, row3HBox, row4HBox, row5HBox, row6HBox;
	private ImageView citizenImageView;
	private Label headerLabel, IDLabel, nameLabel, yearOfBirthLabel, daysOfSicknessLabel, associatedBallotLabel,
			statusLabel;
	private TextField citizenIDTextField, citizenFullNameTextField;
	private ComboBox<Number> citizenYearOfBirthComboBox, citizenDaysOfSicknessComboBox, citizenAssociatedBallotComboBox;
	private GridPane gridPane;
	private CheckBox citizenIsIsolatedCheckBox, citizenIsWearingSuitCheckBox, citizenIsSoldierCheckBox,
			citizenIsCarryingWeaponCheckBox;
	private Button submitButton;

	// Properties (Getters and Setters)
	public TextField getCitizenIDTextField() {
		return citizenIDTextField;
	}

	public TextField getCitizenFullNameTextField() {
		return citizenFullNameTextField;
	}

	public ComboBox<Number> getCitizenYearOfBirthComboBox() {
		return citizenYearOfBirthComboBox;
	}

	public ComboBox<Number> getCitizenDaysOfSicknessComboBox() {
		return citizenDaysOfSicknessComboBox;
	}

	public ComboBox<Number> getCitizenAssociatedBallotComboBox() {
		return citizenAssociatedBallotComboBox;
	}

	public CheckBox getCitizenIsIsolatedCheckBox() {
		return citizenIsIsolatedCheckBox;
	}

	public CheckBox getCitizenIsWearingSuitCheckBox() {
		return citizenIsWearingSuitCheckBox;
	}

	public CheckBox getCitizenIsSoldierCheckBox() {
		return citizenIsSoldierCheckBox;
	}

	public CheckBox getCitizenIsCarryingWeaponCheckBox() {
		return citizenIsCarryingWeaponCheckBox;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	// Constructors
	public AddCitizenView() {
		super();
		
		buildScene();

		addEffects();
	}

	// Methods
	protected void buildScene() {
		LocalDate electionsDate = UIHandler.getElectionsDate();
		int maxBorderYear = LocalDate.now().getYear() - 18, minBorderYear = maxBorderYear - 82;
		ArrayList<Integer> years = new ArrayList<Integer>(), daysOfSickness = new ArrayList<Integer>();
		double sceneWidth = 950, sceneHeight = 500, fontSize = 50;

		for (int currentYear = maxBorderYear; currentYear >= minBorderYear; currentYear--)
			years.add(currentYear);
		for (int i = 1; i <= 14; i++)
			daysOfSickness.add(i);

		vBox = new VBox();
		mainHBox = new HBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		row3HBox = new HBox();
		row4HBox = new HBox();
		row5HBox = new HBox();
		row6HBox = new HBox();
		citizenImageView = UIHandler.buildImage("Citizen.png", sceneHeight * 0.6, sceneHeight * 0.6);
		headerLabel = new Label("New Citizen");
		IDLabel = new Label("ID:");
		nameLabel = new Label("Full Name:");
		yearOfBirthLabel = new Label("Year of Birth:");
		daysOfSicknessLabel = new Label("Days of Sickness:");
		associatedBallotLabel = new Label("Associated Ballot:");
		statusLabel = new Label("Status:");
		citizenIDTextField = new TextField();
		citizenFullNameTextField = new TextField();
		citizenYearOfBirthComboBox = new ComboBox<Number>(FXCollections.observableArrayList(years));
		citizenDaysOfSicknessComboBox = new ComboBox<Number>(FXCollections.observableArrayList(daysOfSickness));
		citizenAssociatedBallotComboBox = new ComboBox<Number>();
		gridPane = new GridPane();
		citizenIsIsolatedCheckBox = new CheckBox("Isolated");
		citizenIsWearingSuitCheckBox = new CheckBox("Wearing Suit");
		citizenIsSoldierCheckBox = new CheckBox("Soldier");
		citizenIsCarryingWeaponCheckBox = new CheckBox("Carrrying Weapon");
		submitButton = new Button("Submit");

		refreshAssociatedBallotComboBox(false, false);
		// TODO: FIX THIS
//		citizenYearOfBirthComboBox.getSelectionModel().selectFirst();
//		citizenAssociatedBallotComboBox.getSelectionModel().selectFirst();
//		citizenIsSoldierCheckBox.setSelected(true);

		headerLabel.setFont(new Font(30));
		IDLabel.setFont(new Font(20));
		nameLabel.setFont(new Font(20));
		yearOfBirthLabel.setFont(new Font(20));
		daysOfSicknessLabel.setFont(new Font(20));
		associatedBallotLabel.setFont(new Font(20));
		statusLabel.setFont(new Font(20));
		citizenIDTextField.setMinWidth(210);
		citizenIDTextField.setTooltip(new Tooltip("Format: 9 digits"));
		citizenFullNameTextField.setMinWidth(210);
		citizenFullNameTextField.setTooltip(new Tooltip("Format: firstname surename (capitalized) (i.e. John Doe)"));
		citizenYearOfBirthComboBox.setMinWidth(210);
		citizenDaysOfSicknessComboBox.setMinWidth(210);
		citizenAssociatedBallotComboBox.setMinWidth(210);
		citizenDaysOfSicknessComboBox.setDisable(true);
		citizenIsSoldierCheckBox.setDisable(true);
		citizenIsWearingSuitCheckBox.setDisable(true);
		citizenIsCarryingWeaponCheckBox.setDisable(true);
		submitButton.setFont(new Font(20));

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(50);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(50);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(50);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(50);

		gridPane.add(citizenIsIsolatedCheckBox, 0, 0);
		gridPane.add(citizenIsWearingSuitCheckBox, 0, 1);
		gridPane.add(citizenIsSoldierCheckBox, 1, 0);
		gridPane.add(citizenIsCarryingWeaponCheckBox, 1, 1);

		row1HBox.getChildren().addAll(IDLabel, citizenIDTextField);
		HBox.setMargin(IDLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(citizenIDTextField, new Insets(0, 10, 0, 145));

		row2HBox.getChildren().addAll(nameLabel, citizenFullNameTextField);
		HBox.setMargin(nameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(citizenFullNameTextField, new Insets(0, 10, 0, 75));

		row3HBox.getChildren().addAll(yearOfBirthLabel, citizenYearOfBirthComboBox);
		HBox.setMargin(yearOfBirthLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(citizenYearOfBirthComboBox, new Insets(0, 10, 0, 55));

		row4HBox.getChildren().addAll(daysOfSicknessLabel, citizenDaysOfSicknessComboBox);
		HBox.setMargin(daysOfSicknessLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(citizenDaysOfSicknessComboBox, new Insets(0, 10, 0, 20));

		row5HBox.getChildren().addAll(associatedBallotLabel, citizenAssociatedBallotComboBox);
		HBox.setMargin(associatedBallotLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(citizenAssociatedBallotComboBox, new Insets(0, 10, 0, 15));

		row6HBox.getChildren().addAll(statusLabel, gridPane);
		HBox.setMargin(statusLabel, new Insets(0, 40, 0, 10));
		HBox.setMargin(gridPane, new Insets(0, 10, 0, 80));

		vBox.getChildren().addAll(headerLabel, row1HBox, row2HBox, row3HBox, row4HBox, row5HBox, row6HBox,
				submitButton);
		vBox.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(headerLabel, new Insets(sceneHeight * 0.2, 0, 15, 0));
		VBox.setMargin(row6HBox, new Insets(0, 0, 5, 0));

		mainHBox.getChildren().addAll(vBox, citizenImageView);
		HBox.setMargin(citizenImageView, new Insets(sceneHeight * 0.2, 0, sceneHeight * 0.2, sceneHeight * 0.35));

		UIHandler.setGeneralFeatures(stage);
		stage.setScene(new Scene(UIHandler.buildBackground(mainHBox, sceneWidth, sceneHeight, fontSize, false),
				sceneWidth, sceneHeight));
		addEffects();

		stage.show();
	}

	public void refreshAssociatedBallotComboBox(boolean isIsolated, boolean isSoldier) {
		ArrayList<Integer> ballotIDs = new ArrayList<Integer>();
		ObservableList<BallotModel> allBallots = ((BallotsTabView) UIHandler.getViewByName("BallotsTabView"))
				.getAllBallots();

		for (BallotModel ballotModel : allBallots) {
			int currentBallotID = ballotModel.getNumericID();
			if (!ballotIDs.contains(currentBallotID)) {
				if ((ballotModel.isCoronaBallot() == isIsolated) && (ballotModel.isMilitaryBallot() == isSoldier))
					ballotIDs.add(currentBallotID);
			}
		}

		citizenAssociatedBallotComboBox.getSelectionModel().clearSelection();
		citizenAssociatedBallotComboBox.setItems(FXCollections.observableArrayList(ballotIDs));
	}

	@Override
	protected void addEffects() {
		UIHandler.addCursorEffectsToNode(stage.getScene(), submitButton);
		UIHandler.addAudioToImageView(stage.getScene(), citizenImageView, "ToiletFlush.mp3");
	}
}
