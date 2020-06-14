package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
	private Stage stage;
	private VBox vBox;
	private HBox mainHBox, row1HBox, row2HBox, row3HBox;
	private ImageView partyImageView;
	private Label headerLabel, nameLabel, wingLabel, foundationLabel;
	private TextField nameTextField;
	private ComboBox<String> wingComboBox;
	private DatePicker foundationDatePicker;
	private Button submitButton;

	// Properties (Getters and Setters)

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// Constructors
	public AddPartyView(Stage stage, LocalDate electionsDate) {
		setStage(stage);

		buildScene(electionsDate);
	}

	// Methods
	private void buildScene(LocalDate electionsDate) {
		LocalDate minDate = LocalDate.of(1948, 5, 14), maxDate = electionsDate;
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
		nameTextField = new TextField();
		wingComboBox = new ComboBox<String>(FXCollections.observableArrayList(wings));
		foundationDatePicker = new DatePicker();
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(30));
		nameLabel.setFont(new Font(20));
		wingLabel.setFont(new Font(20));
		foundationLabel.setFont(new Font(20));
		submitButton.setFont(new Font(20));
		nameTextField.setMinWidth(175);
		wingComboBox.setMinWidth(175);
		foundationDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
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
		foundationDatePicker.setValue(maxDate);

		row1HBox.getChildren().addAll(nameLabel, nameTextField);
		HBox.setMargin(nameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(nameTextField, new Insets(0, 10, 0, 60));

		row2HBox.getChildren().addAll(wingLabel, wingComboBox);
		HBox.setMargin(wingLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(wingComboBox, new Insets(0, 10, 0, 65));

		row3HBox.getChildren().addAll(foundationLabel, foundationDatePicker);
		HBox.setMargin(foundationLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(foundationDatePicker, new Insets(0, 10, 0, 10));

		vBox.getChildren().addAll(headerLabel, row1HBox, row2HBox, row3HBox, submitButton);
		vBox.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(headerLabel, new Insets(sceneHeight * 0.2, 0, 15, 0));
		VBox.setMargin(row3HBox, new Insets(0, 0, 5, 0));

		mainHBox.getChildren().addAll(vBox, partyImageView);
		HBox.setMargin(partyImageView, new Insets(sceneHeight * 0.2, 0, sceneHeight * 0.2, sceneHeight * 0.4));

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

	public void close() {
		stage.close();
	}
}
