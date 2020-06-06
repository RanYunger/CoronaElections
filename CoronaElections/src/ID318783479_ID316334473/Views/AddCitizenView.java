package ID318783479_ID316334473.Views;

import java.time.YearMonth;
import java.util.ArrayList;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddCitizenModel;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
	private HBox mainHBox, row1HBox, row2HBox, row3HBox, row4HBox;
	private ImageView citizenImageView;
	private Label headerLabel, citizenIDLabel, citizenNameLabel, citizenYearOfBirthLabel, citizenStatusLabel;
	private TextField citizenIDTextField, citizenNameTextField;
	private ComboBox<Integer> citizenYearOfBirthComboBox;
	private GridPane gridPane;
	private CheckBox isolatedCheckBox, wearingSuitCheckBox, soldierCheckBox, carryingWeaponCheckBox;
	private Button submitButton;

	// Properties (Getters and Setters)

	// Constructors
	public AddCitizenView(Stage stage) {
		root = new Group();

		buildScene(stage);
	}

	// Methods
	public void refresh(AddCitizenModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(Stage stage) {
		double sceneWidth = 750, sceneHeight = 350;
		int currentYear = YearMonth.now().getYear();
		ArrayList<Integer> years = new ArrayList<Integer>();

		for (int i = 0; i < 18; i++)
			years.add(currentYear--);

		vBox = new VBox();
		mainHBox = new HBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		row3HBox = new HBox();
		row4HBox = new HBox();
		citizenImageView = UIHandler.buildImage("Citizen.png", sceneHeight, sceneHeight);
		headerLabel = new Label("Fill in the form");
		citizenIDLabel = new Label("ID:");
		citizenNameLabel = new Label("Full Name:");
		citizenYearOfBirthLabel = new Label("Year of Birth:");
		citizenStatusLabel = new Label("Status:");
		citizenIDTextField = new TextField();
		citizenNameTextField = new TextField();
		citizenYearOfBirthComboBox = new ComboBox<Integer>(FXCollections.observableArrayList(years));
		gridPane = new GridPane();
		isolatedCheckBox = new CheckBox("Isolated");
		wearingSuitCheckBox = new CheckBox("Wearing Suit");
		soldierCheckBox = new CheckBox("Soldier");
		carryingWeaponCheckBox = new CheckBox("Carrrying Weapon");
		submitButton = new Button("Submit");

		headerLabel.setFont(new Font(30));
		citizenIDLabel.setFont(new Font(20));
		citizenNameLabel.setFont(new Font(20));
		citizenYearOfBirthLabel.setFont(new Font(20));
		citizenStatusLabel.setFont(new Font(20));
		citizenIDTextField.setMinWidth(210);
		citizenNameTextField.setMinWidth(210);
		citizenYearOfBirthComboBox.setMinWidth(210);
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

		row1HBox.getChildren().addAll(citizenIDLabel, citizenIDTextField);
		HBox.setMargin(citizenIDLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(citizenIDTextField, new Insets(0, 10, 0, 105));

		row2HBox.getChildren().addAll(citizenNameLabel, citizenNameTextField);
		HBox.setMargin(citizenNameLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(citizenNameTextField, new Insets(0, 10, 0, 35));

		row3HBox.getChildren().addAll(citizenYearOfBirthLabel, citizenYearOfBirthComboBox);
		HBox.setMargin(citizenYearOfBirthLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(citizenYearOfBirthComboBox, new Insets(0, 10, 0, 15));

		row4HBox.getChildren().addAll(citizenStatusLabel, gridPane);
		HBox.setMargin(citizenStatusLabel, new Insets(0, 40, 0, 10));
		HBox.setMargin(gridPane, new Insets(0, 10, 0, 40));

		vBox.getChildren().addAll(headerLabel, row1HBox, row2HBox, row3HBox, row4HBox, submitButton);
		vBox.setAlignment(Pos.TOP_CENTER);

		mainHBox.getChildren().addAll(vBox, citizenImageView);

		VBox.setMargin(headerLabel, new Insets(0, 0, sceneHeight * 0.1, 0));
		VBox.setMargin(row1HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row2HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row3HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(row4HBox, new Insets(10, 0, 10, 0));
		VBox.setMargin(submitButton, new Insets(10, 0, 10, 0));

		stage.setTitle("Corona Elections");
		stage.setResizable(false);
		stage.getIcons().add(UIHandler.buildImage("Elections.jpg", 0, 0).getImage());
		stage.setScene(new Scene(/* UIHandler.buildBackground(gridPane, sceneWidth, sceneHeight) */ mainHBox,
				sceneWidth, sceneHeight));
		stage.show();
	}
}
