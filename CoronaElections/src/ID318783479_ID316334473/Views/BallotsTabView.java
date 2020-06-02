package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.BallotsTabModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

public class BallotsTabView {
	// Constants

	// Fields
	private Group root;
	private GridPane gridPane;
	private Button addBallotButton, removeBallotButton;
	private HBox hBox;
	private TableView<String> ballotsTableView;

	// Properties (Getters and Setters)

	// Constructors
	public BallotsTabView() {
		root = new Group();

		buildScene();
	}

	// Methods
	public void refresh(BallotsTabModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene() {
		ObservableList<TableColumn<String, ?>> voterColumns;

		gridPane = new GridPane();
		addBallotButton = new Button("Add Ballot");
		removeBallotButton = new Button("Remove Ballot");
		hBox = new HBox();
		ballotsTableView = new TableView<String>();

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(10);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(90);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(100);

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(addBallotButton, removeBallotButton);
		HBox.setMargin(addBallotButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(removeBallotButton, new Insets(0, 0, 0, 10));

		ballotsTableView.getColumns().add(new TableColumn<String, String>("ID"));
		ballotsTableView.getColumns().get(0).setMinWidth(100);
		ballotsTableView.getColumns().add(new TableColumn<String, String>("Type"));
		ballotsTableView.getColumns().get(1).setMinWidth(200);
		ballotsTableView.getColumns().add(new TableColumn<String, Number>("Address"));
		ballotsTableView.getColumns().get(2).setMinWidth(400);
		ballotsTableView.getColumns().add(new TableColumn<String, String>("Voters"));
		ballotsTableView.getColumns().get(3).setMinWidth(850);
		ballotsTableView.setOpacity(10); // for the background image to be seen

		voterColumns = ballotsTableView.getColumns().get(3).getColumns();
		voterColumns.add(new TableColumn<String, String>("ID"));
		voterColumns.get(0).setMinWidth(150);
		voterColumns.add(new TableColumn<String, String>("Full Name"));
		voterColumns.get(1).setMinWidth(200);
		voterColumns.add(new TableColumn<String, String>("Birth"));
		voterColumns.get(2).setMinWidth(50);
		voterColumns.add(new TableColumn<String, Boolean>("Status"));
		// use setStatusHBox() as the column's content
		voterColumns.get(3).setMinWidth(450);

		gridPane.add(hBox, 0, 0, 1, 1);
		gridPane.add(ballotsTableView, 0, 1, 1, 1);
		
		GridPane.setMargin(hBox, new Insets(70, 0, 0, 0));
		GridPane.setMargin(ballotsTableView, new Insets(10, 0, 370, 0));
	}

	public Node asNode() {
		return (Node) gridPane;
	}

	public Node getNodeByName(String nodeName) {
		try {
			return (Node) getClass().getDeclaredField(nodeName).get(this);
		} catch (Exception ex) {
			UIHandler.alertUser("Error", "An unexpected error occured", ex.getMessage(), AlertType.ERROR);
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
