package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddPartyModel;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddPartyView {
	// Constants

	// Fields
	private Group root;
	private VBox vBox;
	private HBox mainHBox, row1HBox, row2HBox, row3HBox;
	private ImageView partyImageView;
	private Label headerLabel, partyNameLabel, partyWingLabel, partyFoundationLabel;
	private TextField partyNameTextField;
	private ComboBox<String> partyWingComboBox;
	private DatePicker partyFoundationDatePicker;
	private Button submitButton;

	// Properties (Getters and Setters)

	// Constructors
	public AddPartyView(Stage stage) {
		root = new Group();

		buildScene(stage);
	}

	// Methods
	public void refresh(AddPartyModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(Stage stage) {
		double sceneWidth = 700, sceneHeight = 350;
		LocalDate minDate = LocalDate.of(1948, 5, 14), maxDate = LocalDate.now();
		String[] wings = { "Left", "Center", "Right" };

		vBox = new VBox();
		mainHBox = new HBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		row3HBox = new HBox();
		partyImageView = UIHandler.buildImage("Party.png", sceneHeight, sceneHeight);
		headerLabel = new Label("Fill in the form");
		partyNameLabel = new Label("Name:");
		partyWingLabel = new Label("Wing:");
		partyFoundationLabel = new Label("Foundation:");
		partyNameTextField = new TextField();
		partyWingComboBox = new ComboBox<String>(FXCollections.observableArrayList(wings));
		partyFoundationDatePicker = new DatePicker();
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(30));
		partyNameLabel.setFont(new Font(20));
		partyWingLabel.setFont(new Font(20));
		partyFoundationLabel.setFont(new Font(20));
		submitButton.setFont(new Font(20));
		partyNameTextField.setMinWidth(175);
		partyWingComboBox.setMinWidth(175);
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

		row1HBox.getChildren().addAll(partyNameLabel, partyNameTextField);
		HBox.setMargin(partyNameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(partyNameTextField, new Insets(0, 10, 0, 60));

		row2HBox.getChildren().addAll(partyWingLabel, partyWingComboBox);
		HBox.setMargin(partyWingLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(partyWingComboBox, new Insets(0, 10, 0, 65));

		row3HBox.getChildren().addAll(partyFoundationLabel, partyFoundationDatePicker);
		HBox.setMargin(partyFoundationLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(partyFoundationDatePicker, new Insets(0, 10, 0, 10));

		vBox.getChildren().addAll(headerLabel, row1HBox, row2HBox, row3HBox, submitButton);
		vBox.setAlignment(Pos.TOP_CENTER);

		mainHBox.getChildren().addAll(vBox, partyImageView);

		VBox.setMargin(headerLabel, new Insets(0, 0, sceneHeight * 0.1, 0));
		VBox.setMargin(row1HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row2HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row3HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(submitButton, new Insets(10, 0, 10, 0));

		stage.setTitle("Corona Elections");
		stage.setResizable(false);
		stage.getIcons().add(UIHandler.buildImage("Elections.jpg", 0, 0).getImage());
		stage.setScene(new Scene(/* UIHandler.buildBackground(gridPane, sceneWidth, sceneHeight) */ mainHBox,
				sceneWidth, sceneHeight));
		stage.show();
	}
}
