package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class AddPartyView extends View {
	// Fields
	private VBox vBox;
	private HBox mainHBox, row1HBox, row2HBox, row3HBox;
	private ImageView partyImageView;
	private Label headerLabel, nameLabel, wingLabel, foundationLabel;
	private TextField partyNameTextField;
	private ComboBox<String> partyWingComboBox;
	private DatePicker partyFoundationDatePicker;
	private Button submitButton;

	// Properties (Getters and Setters)
	public TextField getPartyNameTextField() {
		return partyNameTextField;
	}

	public ComboBox<String> getPartyWingComboBox() {
		return partyWingComboBox;
	}

	public DatePicker getPartyFoundationDatePicker() {
		return partyFoundationDatePicker;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	// Constructors
	public AddPartyView() {
		super();

		buildScene();
	}

	// Methods
	@Override
	protected void buildScene() {
		LocalDate minDate = LocalDate.of(1948, 5, 14), maxDate = UIHandler.getElectionsDate();
		String[] wings = { "Left", "Center", "Right" };
		double sceneWidth = 700, sceneHeight = 350, fontSize = 40;

		vBox = new VBox();
		mainHBox = new HBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		row3HBox = new HBox();
		partyImageView = UIHandler.buildImage("Party.png", sceneHeight * 0.6, sceneHeight * 0.6);
		headerLabel = new Label("New Party");
		nameLabel = new Label("Name:");
		wingLabel = new Label("Wing:");
		foundationLabel = new Label("Foundation:");
		partyNameTextField = new TextField();
		partyWingComboBox = new ComboBox<String>(FXCollections.observableArrayList(wings));
		partyFoundationDatePicker = new DatePicker();
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(30));
		nameLabel.setFont(new Font(20));
		wingLabel.setFont(new Font(20));
		foundationLabel.setFont(new Font(20));
		submitButton.setFont(new Font(20));
		partyNameTextField.setMinWidth(175);
		partyNameTextField.setTooltip(new Tooltip("Format: name (capitalized) (i.e. Halikud, Blue And White)"));
		partyWingComboBox.setMinWidth(175);

		partyWingComboBox.getSelectionModel().select("Center");
		partyFoundationDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.equals(minDate))
							setTooltip(new Tooltip("הכרזת העצמאות והקמת מדינת ישראל"));
						if ((item.compareTo(minDate) < 0) || (item.compareTo(maxDate) > 0)) {
							setStyle("-fx-background-color: #ff4444;");
							setText("X");
							setDisable(true);
						}
					}
				};
			}
		});
		partyFoundationDatePicker.setValue(maxDate);

		row1HBox.getChildren().addAll(nameLabel, partyNameTextField);
		HBox.setMargin(nameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(partyNameTextField, new Insets(0, 10, 0, 60));

		row2HBox.getChildren().addAll(wingLabel, partyWingComboBox);
		HBox.setMargin(wingLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(partyWingComboBox, new Insets(0, 10, 0, 65));

		row3HBox.getChildren().addAll(foundationLabel, partyFoundationDatePicker);
		HBox.setMargin(foundationLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(partyFoundationDatePicker, new Insets(0, 10, 0, 10));

		vBox.getChildren().addAll(headerLabel, row1HBox, row2HBox, row3HBox, submitButton);
		vBox.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(headerLabel, new Insets(sceneHeight * 0.2, 0, 15, 0));
		VBox.setMargin(row3HBox, new Insets(0, 0, 5, 0));

		mainHBox.getChildren().addAll(vBox, partyImageView);
		HBox.setMargin(partyImageView, new Insets(sceneHeight * 0.2, 0, sceneHeight * 0.2, sceneHeight * 0.4));

		UIHandler.setGeneralFeatures(stage);
		stage.setScene(new Scene(UIHandler.buildBackground(mainHBox, sceneWidth, sceneHeight, fontSize, false),
				sceneWidth, sceneHeight));
		addEffects();

		stage.show();
	}

	@Override
	protected void addEffects() {
		stage.setOnCloseRequest(closing -> UIHandler.getMainView().disableAllButtons(false));
		UIHandler.addCursorEffectsToNode(submitButton);
	}
}
