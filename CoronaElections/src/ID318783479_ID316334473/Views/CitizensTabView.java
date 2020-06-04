package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.CitizensTabModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

public class CitizensTabView {
	// Constants

	// Fields
	private Group root;
	private GridPane gridPane;
	private Button addCitizenButton, removeCitizenButton;
	private HBox hBox;
	private TableView<String> citizensTableView;

	// Properties (Getters and Setters)

	// Constructors
	public CitizensTabView() {
		root = new Group();

		buildScene();
	}

	// Methods

	public void refresh(CitizensTabModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene() {
		gridPane = new GridPane();
		addCitizenButton = new Button("Add Citizen");
		removeCitizenButton = new Button("Remove Citizen");
		hBox = new HBox();
		citizensTableView = new TableView<String>();

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(10);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(90);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(100);

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(addCitizenButton, removeCitizenButton);
		HBox.setMargin(addCitizenButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(removeCitizenButton, new Insets(0, 0, 0, 10));

		citizensTableView.getColumns().add(new TableColumn<String, String>("ID"));
		citizensTableView.getColumns().get(0).setMinWidth(200);
		citizensTableView.getColumns().add(new TableColumn<String, String>("Full Name"));
		citizensTableView.getColumns().get(1).setMinWidth(300);
		citizensTableView.getColumns().add(new TableColumn<String, Number>("Birth"));
		citizensTableView.getColumns().get(2).setMinWidth(100);
		citizensTableView.getColumns().add(new TableColumn<String, Number>("Associated Ballot"));
		citizensTableView.getColumns().get(3).setMinWidth(150);
		citizensTableView.getColumns().add(new TableColumn<String, String>("Status"));
		// use setStatusHBox() as the column's content
		citizensTableView.getColumns().get(4).setMinWidth(800);
		citizensTableView.setOpacity(10); // for the background image to be seen

		gridPane.add(hBox, 0, 0, 1, 1);
		gridPane.add(citizensTableView, 0, 1, 1, 1);
		
		GridPane.setMargin(hBox, new Insets(70, 0, 0, 0));
		GridPane.setMargin(citizensTableView, new Insets(10, 0, 370, 0));
	}

	public Node asNode() {
		return (Node) gridPane;
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
}
