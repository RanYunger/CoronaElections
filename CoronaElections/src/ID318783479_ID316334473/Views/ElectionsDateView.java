package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ElectionsDateView extends View {
	// Fields
	private VBox vBox;
	private Label electionsDateLabel;
	private DatePicker electionsDateDatePicker;
	private Button enterButton;

	// Properties
	public LocalDate getElectionsDate() {
		return electionsDateDatePicker.getValue();
	}

	public Button getEnterButton() {
		return enterButton;
	}

	// Constructors
	public ElectionsDateView(Stage stage) {
		super(stage);

		buildScene();
	}

	// Methods
	@Override
	protected void buildScene() {
		LocalDate minDate = LocalDate.of(2020, 2, 27), maxDate = LocalDate.now();
		double sceneWidth = 400, sceneHeight = 300;

		vBox = new VBox();
		electionsDateLabel = new Label("Elections Date");
		electionsDateDatePicker = new DatePicker();
		enterButton = new Button("Enter");

		electionsDateLabel.setFont(new Font(30));
		electionsDateDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.equals(minDate))
							setTooltip(new Tooltip("התפרצות נגיף הקורונה בישראל"));
						if ((item.compareTo(minDate) < 0) || (item.compareTo(maxDate) > 0)) {
							setStyle("-fx-background-color: #ff4444;");
							setText("X");
							setDisable(true);
						}
					}
				};
			}
		});
		electionsDateDatePicker.setValue(maxDate);
		enterButton.setFont(new Font(20));

		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(electionsDateLabel, electionsDateDatePicker, enterButton);
		VBox.setMargin(electionsDateLabel, new Insets(20));
		VBox.setMargin(electionsDateDatePicker, new Insets(20));
		VBox.setMargin(enterButton, new Insets(20));

		stage.setTitle("Welcome to our system!");
		UIHandler.setIcon(stage);
		stage.setScene(new Scene(vBox, sceneWidth, sceneHeight));
		stage.setResizable(false);
		addEffects();

		stage.show();
	}

	@Override
	protected void addEffects() {
		UIHandler.addCursorEffectsToNode(enterButton);
	}
}