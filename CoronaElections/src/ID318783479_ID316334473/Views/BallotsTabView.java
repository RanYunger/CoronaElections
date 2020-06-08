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
		TableColumn<String, String> ballotIDTableColumn, ballotTypeTableColumn, ballotAddressTableColumn, ballotVotersTableColumn;
		TableColumn<String, String> voterIDTableColumn, voterNameTableColumn, voterYearOfBirthTableColumn, voterStatusTableColumn;
		ObservableList<TableColumn<String, ?>> voterNestedTableColumns;

		gridPane = new GridPane();
		addBallotButton = new Button("Add Ballot");
		removeBallotButton = new Button("Remove Ballot");
		hBox = new HBox();
		ballotsTableView = new TableView<String>();

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(20);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(80);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(100);

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(addBallotButton, removeBallotButton);
		HBox.setMargin(addBallotButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(removeBallotButton, new Insets(0, 0, 0, 10));

		ballotIDTableColumn = new TableColumn<String, String>("ID");
		ballotIDTableColumn.setMinWidth(100);
		ballotIDTableColumn.setReorderable(false);
		ballotIDTableColumn.setResizable(false);

		ballotTypeTableColumn = new TableColumn<String, String>("Type");
		ballotTypeTableColumn.setMinWidth(200);
		ballotTypeTableColumn.setReorderable(false);
		ballotTypeTableColumn.setResizable(false);

		ballotAddressTableColumn = new TableColumn<String, String>("Address");
		ballotAddressTableColumn.setMinWidth(400);
		ballotAddressTableColumn.setReorderable(false);
		ballotAddressTableColumn.setResizable(false);

		ballotVotersTableColumn = new TableColumn<String, String>("Voters");
		ballotVotersTableColumn.setMinWidth(850);
		ballotVotersTableColumn.setReorderable(false);
		ballotVotersTableColumn.setResizable(false);

		voterNestedTableColumns = ballotVotersTableColumn.getColumns();		
		voterIDTableColumn = new TableColumn<String, String>("ID");
		voterIDTableColumn.setMinWidth(150);
		voterIDTableColumn.setReorderable(false);
		voterIDTableColumn.setResizable(false);
		
		voterNameTableColumn = new TableColumn<String, String>("Full Name");
		voterNameTableColumn.setMinWidth(200);
		voterNameTableColumn.setReorderable(false);
		voterNameTableColumn.setResizable(false);
		
		voterYearOfBirthTableColumn = new TableColumn<String, String>("Birth");
		voterYearOfBirthTableColumn.setMinWidth(50);
		voterYearOfBirthTableColumn.setReorderable(false);
		voterYearOfBirthTableColumn.setResizable(false);
		
		voterStatusTableColumn = new TableColumn<String, String>("Status");
		// use setStatusHBox() as the column's content
		voterStatusTableColumn.setMinWidth(370);
		voterStatusTableColumn.setReorderable(false);
		voterStatusTableColumn.setResizable(false);
		
		voterNestedTableColumns.addAll(voterIDTableColumn, voterNameTableColumn, voterYearOfBirthTableColumn, voterStatusTableColumn);

		ballotsTableView.getColumns().addAll(ballotIDTableColumn, ballotTypeTableColumn, ballotAddressTableColumn, ballotVotersTableColumn);
		ballotsTableView.setOpacity(0.8);

		gridPane.add(hBox, 0, 0, 1, 1);
		gridPane.add(ballotsTableView, 0, 1, 1, 1);

		GridPane.setMargin(hBox, new Insets(110, 0, 0, 0));
		GridPane.setMargin(ballotsTableView, new Insets(10, 0, 145, 0));
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
