package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BallotsTabView {
	// Constants

	// Fields
	private GridPane gridPane;
	private Button addBallotButton, removeBallotButton;
	private VBox vBox;
	private HBox addRemoveBottons, tableLables;
	private Label ballotsLabel, votersInBallotLabel;
	private ObservableList<BallotModel> allBallots;
	private TableView<BallotModel> ballotsTableView;
	private TableView<CitizenModel> votersInBallotTableView;

	// Properties (Getters and Setters)
	public ObservableList<BallotModel> getAllBallots() {
		return allBallots;
	}

	// Constructors
	public BallotsTabView() {
		buildScene();
	}

	// Methods
	@SuppressWarnings("unchecked")
	private void buildScene() {
		TableColumn<BallotModel, Number> ballotIDTableColumn;
		TableColumn<BallotModel, String> ballotTypeTableColumn, ballotAddressTableColumn;
		TableColumn<CitizenModel, Number> voterIDTableColumn, voterYearOfBirthTableColumn;
		TableColumn<CitizenModel, String> voterNameTableColumn;

		allBallots = FXCollections.observableArrayList();
		gridPane = new GridPane();
		addBallotButton = new Button("Add Ballot");
		removeBallotButton = new Button("Remove Ballot");
		vBox = new VBox();
		addRemoveBottons = new HBox();
		tableLables = new HBox();
		ballotsLabel = new Label("Ballots");
		votersInBallotLabel = new Label("Voters in Ballot");
		ballotsTableView = new TableView<BallotModel>();
		ballotsTableView.setItems(allBallots);
		votersInBallotTableView = new TableView<CitizenModel>();

		addBallotButton.setMinWidth(100);
		removeBallotButton.setMinWidth(100);
		ballotsLabel.setFont(new Font(30));
		votersInBallotLabel.setFont(new Font(30));
		ballotsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		votersInBallotTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(20);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(80);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(50);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(50);

		addRemoveBottons.setAlignment(Pos.CENTER);
		addRemoveBottons.getChildren().addAll(addBallotButton, removeBallotButton);
		HBox.setMargin(addBallotButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(removeBallotButton, new Insets(0, 0, 0, 10));

		tableLables.setAlignment(Pos.CENTER);
		tableLables.getChildren().addAll(ballotsLabel, votersInBallotLabel);
		HBox.setMargin(ballotsLabel, new Insets(0, 300, 0, 50));
		HBox.setMargin(votersInBallotLabel, new Insets(0, 0, 0, 350));

		vBox.getChildren().addAll(addRemoveBottons, tableLables);

		ballotIDTableColumn = new TableColumn<BallotModel, Number>("ID");
		ballotIDTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableID());
		ballotIDTableColumn.setMinWidth(100);

		ballotTypeTableColumn = new TableColumn<BallotModel, String>("Type");
		ballotTypeTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableType());
		ballotTypeTableColumn.setMinWidth(200);

		ballotAddressTableColumn = new TableColumn<BallotModel, String>("Address");
		ballotAddressTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableAddress());
		ballotAddressTableColumn.setMinWidth(450);

		voterIDTableColumn = new TableColumn<CitizenModel, Number>("ID");
		voterIDTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableID());
		voterIDTableColumn.setMinWidth(100);

		voterNameTableColumn = new TableColumn<CitizenModel, String>("Full Name");
		voterNameTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableFullName());
		voterNameTableColumn.setMinWidth(150);

		voterYearOfBirthTableColumn = new TableColumn<CitizenModel, Number>("Birth");
		voterYearOfBirthTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableYearOfBirth());
		voterYearOfBirthTableColumn.setMinWidth(50);

		ballotsTableView.getColumns().addAll(ballotIDTableColumn, ballotTypeTableColumn, ballotAddressTableColumn);
		for (TableColumn<?, ?> tableColumn : ballotsTableView.getColumns()) {
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setResizable(false);
		}
		ballotsTableView.setOpacity(0.8);

		votersInBallotTableView.getColumns().addAll(voterIDTableColumn, voterNameTableColumn,
				voterYearOfBirthTableColumn, UIHandler.buildStatusTableColumn(410));
		for (TableColumn<?, ?> tableColumn : votersInBallotTableView.getColumns()) {
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setResizable(false);
		}
		votersInBallotTableView.setOpacity(0.8);

		gridPane.add(vBox, 0, 0, 2, 1);
		gridPane.add(ballotsTableView, 0, 1, 1, 1);
		gridPane.add(votersInBallotTableView, 1, 1, 1, 1);

		GridPane.setMargin(vBox, new Insets(110, 0, 0, 0));
		GridPane.setMargin(ballotsTableView, new Insets(0, 10, 373.5, 0));
		GridPane.setMargin(votersInBallotTableView, new Insets(0, 0, 373.5, 10));
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

	public <T> void addEventHandlerToTableView(String tableViewName, ChangeListener<? super Number> changeListener) {
		TableView<?> requiredTableView = (TableView<?>) getNodeByName(tableViewName);

		requiredTableView.getSelectionModel().selectedIndexProperty().addListener(changeListener);
	}

	public void addEffects(Stage stage) {
		Scene scene = stage.getScene();

		UIHandler.addCursorEffectsToNode(scene, addBallotButton);
		UIHandler.addCursorEffectsToNode(scene, removeBallotButton);
	}

	public void addBallot(BallotModel ballot) {
		allBallots.add(ballot);
	}
}
