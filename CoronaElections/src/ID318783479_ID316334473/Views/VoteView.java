package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.Models.VoteModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VoteView {
	// Constants
	
	// Fields
	private Group root;
	private VBox vBox;
	private Label messageLabel;
	private TableView<String> partiesTableView;
	
	// Properties (Getters and Setters)
	
	// Constructors
	public VoteView(Stage stage) {
		root = new Group();

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

	public void refresh(VoteModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(Stage stage) {
		vBox = new VBox();
		messageLabel = new Label("Vote for your chosen party");
		partiesTableView = new TableView<String>();
		
		messageLabel.setFont(new Font(30));

		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(messageLabel, partiesTableView);
		VBox.setMargin(messageLabel, new Insets(20));
		VBox.setMargin(partiesTableView, new Insets(20));
		
		stage.setTitle("Corona Elections");
		stage.setResizable(false);
		// TODO: Set icon + background image
		stage.setScene(new Scene(vBox, 500, 400));
		stage.show();
	}
}
