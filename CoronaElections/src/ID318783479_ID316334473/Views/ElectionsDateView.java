package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.Models.ElectionsDateModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ElectionsDateView {
	// Constants

	// Fields
	private Group root;
	private Stage stage;
	private VBox vBox;
	private Label lblElectionsDate;
	private DatePicker dtpElectionsDate;
	private Button btnEnter;
	
	// Properties
	public DatePicker getDtpElectionsDate() {
		return dtpElectionsDate;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// Constructors
	public ElectionsDateView(Stage stage) {
		root = new Group();

		setStage(stage);
		buildScene(stage);
	}

	// Methods
	public void refresh(ElectionsDateModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	public void buildScene(Stage stage) {
		vBox = new VBox();
		lblElectionsDate = new Label("Elections Date");
		dtpElectionsDate = new DatePicker();
		btnEnter = new Button("Enter");
		
		lblElectionsDate.setFont(new Font(30));
		// TODO: Limit MaxDate to now() and MinDate to 14.5.1948 XD
		dtpElectionsDate.setValue(LocalDate.now());
		btnEnter.setFont(new Font(20));

		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(lblElectionsDate, dtpElectionsDate, btnEnter);
		VBox.setMargin(lblElectionsDate, new Insets(20));
		VBox.setMargin(dtpElectionsDate, new Insets(20));
		VBox.setMargin(btnEnter, new Insets(20));

		stage.setTitle("Welcome to our system!");
		stage.setResizable(false);
		// TODO: Set icon
		stage.setScene(new Scene(vBox, 400, 300));
		stage.show();
	}
	
	public void addListenerToEnterButton(EventHandler<ActionEvent> listener) {
		btnEnter.setOnAction(listener);
	}

	public void close() {
		stage.close();	
	} 
}
