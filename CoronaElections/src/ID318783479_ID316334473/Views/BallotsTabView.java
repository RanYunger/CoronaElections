package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.BallotModel;
import ID318783479_ID316334473.Models.BallotsTabModel;
import ID318783479_ID316334473.Models.CitizenModel;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BallotsTabView {
	// Constants

	// Fields
	private Group root;
	private GridPane gridPane;
	private Button addBallotButton, removeBallotButton;
	private VBox vBox;
	private HBox row1HBox, row2HBox;
	private Label ballotsLabel, votersInBallotLabel;
	private TableView<BallotModel<?>> ballotsTableView;
	private TableView<CitizenModel> votersInBallotTableView;

	// Properties (Getters and Setters)
	public void setRoot(Group root) {
		this.root = root;
	}

	// Constructors
	public BallotsTabView() {
		setRoot(new Group());

		buildScene();
	}

	// Methods
	public void refresh(BallotsTabModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene() {
		TableColumn<BallotModel<?>, Integer> ballotIDTableColumn;
		TableColumn<BallotModel<?>, String> ballotTypeTableColumn, ballotAddressTableColumn;
		TableColumn<CitizenModel, Integer> voterIDTableColumn, voterYearOfBirthTableColumn;
		TableColumn<CitizenModel, String> voterNameTableColumn;

		gridPane = new GridPane();
		addBallotButton = new Button("Add Ballot");
		removeBallotButton = new Button("Remove Ballot");
		vBox = new VBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		ballotsLabel = new Label("Ballots");
		votersInBallotLabel = new Label("Voters in Ballot");
		ballotsTableView = new TableView<BallotModel<?>>();
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

		row1HBox.setAlignment(Pos.CENTER);
		row1HBox.getChildren().addAll(addBallotButton, removeBallotButton);
		HBox.setMargin(addBallotButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(removeBallotButton, new Insets(0, 0, 0, 10));

		row2HBox.setAlignment(Pos.CENTER);
		row2HBox.getChildren().addAll(ballotsLabel, votersInBallotLabel);
		HBox.setMargin(ballotsLabel, new Insets(0, 300, 0, 50));
		HBox.setMargin(votersInBallotLabel, new Insets(0, 0, 0, 350));

		vBox.getChildren().addAll(row1HBox, row2HBox);

		ballotIDTableColumn = new TableColumn<BallotModel<?>, Integer>("ID");
		ballotIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		ballotIDTableColumn.setMinWidth(100);

		ballotTypeTableColumn = new TableColumn<BallotModel<?>, String>("Type");
		ballotTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
		ballotTypeTableColumn.setMinWidth(200);

		ballotAddressTableColumn = new TableColumn<BallotModel<?>, String>("Address");
		ballotAddressTableColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
		ballotAddressTableColumn.setMinWidth(450);

		voterIDTableColumn = new TableColumn<CitizenModel, Integer>("ID");
		voterIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		voterIDTableColumn.setMinWidth(100);

		voterNameTableColumn = new TableColumn<CitizenModel, String>("Full Name");
		voterNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("FullName"));
		voterNameTableColumn.setMinWidth(150);

		voterYearOfBirthTableColumn = new TableColumn<CitizenModel, Integer>("Birth");
		voterYearOfBirthTableColumn.setCellValueFactory(new PropertyValueFactory<>("YearOfBirth"));
		voterYearOfBirthTableColumn.setMinWidth(50);

		ballotsTableView.getColumns().addAll(ballotIDTableColumn, ballotTypeTableColumn, ballotAddressTableColumn);
		for (TableColumn<?, ?> tableColumn : ballotsTableView.getColumns()) {
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setResizable(false);
		}
		ballotsTableView.setOpacity(0.8);

		votersInBallotTableView.getColumns().addAll(voterIDTableColumn, voterNameTableColumn, voterYearOfBirthTableColumn,
				UIHandler.buildStatusTableColumn(410));
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
}
