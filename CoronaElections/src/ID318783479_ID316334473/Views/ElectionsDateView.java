package ID318783479_ID316334473.Views;

import java.time.LocalDate;
import java.time.YearMonth;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.ElectionsDateModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ElectionsDateView {
	// Constants

	// Fields
	private Group root;
	private Stage stage;
	private VBox vBox;
	private Label electionsDateLabel;
	private DatePicker electionsDateDatePicker;
	private Button enterButton;

	// Properties

	// Constructors
	public ElectionsDateView(Stage stage) {
		root = new Group();
		this.stage = stage;

		buildScene(stage);
	}

	// Methods
	public Node getControlByName(String controlName) {
		return root.lookup(controlName);
	}

	public Object getPropertyByName(String controlName, String propertyName) {
		Node control = getControlByName(controlName);

		return control.getProperties().get(propertyName);
	}

	public YearMonth getElectionsDate() {
		return YearMonth.from(electionsDateDatePicker.getValue());
	}

	public void refresh(ElectionsDateModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	public void buildScene(Stage stage) {
		LocalDate minDate = LocalDate.of(2020, 2, 27), maxDate = LocalDate.now();

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
		stage.setResizable(false);
		stage.getIcons().add(UIHandler.buildImage("Elections.jpg", 0, 0).getImage());
		stage.setScene(new Scene(vBox, 400, 300));
		stage.show();
	}

	public void addEventHandlerToEnterButton(EventHandler<ActionEvent> listener) {
		enterButton.setOnAction(listener);
	}

	public void close() {
		stage.close();
	}
}
