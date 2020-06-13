package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.CandidateModel;
import ID318783479_ID316334473.Models.PartiesTabModel;
import ID318783479_ID316334473.Models.PartyModel;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

public class PartiesTabView {
	// Constants

	// Fields
	private Group root;
	private GridPane gridPane;
	private Button addPartyButton, removePartyButton, addCandidateToPartyButton;
	private VBox vBox;
	private HBox row1HBox, row2HBox;
	private Label partiesLabel, candidatesInPartyLabel;
	private TableView<PartyModel> partiesTableView;
	private TableView<CandidateModel> candidatesInPartyTableView;

	// Properties (Getters and Setters)
	public void setRoot(Group root) {
		this.root = root;
	}

	// Constructors
	public PartiesTabView() {
		setRoot(new Group());

		buildScene();
	}

	// Methods
	public void refresh(PartiesTabModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene() {
		TableColumn<PartyModel, Integer> partyYearOfFoundationTableColumn;
		TableColumn<PartyModel, String> partyNameTableColumn, partyWingTableColumn;
		TableColumn<CandidateModel, Integer> candidateIDTableColumn, candidateRankTableColumn;
		TableColumn<CandidateModel, String> candidateNameTableColumn;

		gridPane = new GridPane();
		addPartyButton = new Button("Add Party");
		removePartyButton = new Button("Remove Party");
		addCandidateToPartyButton = new Button("Add Candidate to Party");
		vBox = new VBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		partiesLabel = new Label("Parties");
		candidatesInPartyLabel = new Label("Candidates in Party");
		partiesTableView = new TableView<PartyModel>();
		candidatesInPartyTableView = new TableView<CandidateModel>();

		addPartyButton.setMinWidth(150);
		addCandidateToPartyButton.setMinWidth(150);
		removePartyButton.setMinWidth(150);
		partiesLabel.setFont(new Font(30));
		candidatesInPartyLabel.setFont(new Font(30));
		partiesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		candidatesInPartyTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(20);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(80);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(50);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(50);

		row1HBox.setAlignment(Pos.CENTER);
		row1HBox.getChildren().addAll(addPartyButton, addCandidateToPartyButton, removePartyButton);
		HBox.setMargin(addPartyButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(addCandidateToPartyButton, new Insets(0, 10, 0, 10));
		HBox.setMargin(removePartyButton, new Insets(0, 0, 0, 10));

		row2HBox.setAlignment(Pos.CENTER);
		row2HBox.getChildren().addAll(partiesLabel, candidatesInPartyLabel);
		HBox.setMargin(partiesLabel, new Insets(0, 250, 0, 50));
		HBox.setMargin(candidatesInPartyLabel, new Insets(0, 0, 0, 350));

		vBox.getChildren().addAll(row1HBox, row2HBox);

		partyNameTableColumn = new TableColumn<PartyModel, String>("Name");
		partyNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		partyNameTableColumn.setMinWidth(450);

		partyWingTableColumn = new TableColumn<PartyModel, String>("Wing");
		partyWingTableColumn.setCellValueFactory(new PropertyValueFactory<>("Wing"));
		partyWingTableColumn.setMinWidth(150);

		partyYearOfFoundationTableColumn = new TableColumn<PartyModel, Integer>("Foundation");
		partyYearOfFoundationTableColumn.setCellValueFactory(new PropertyValueFactory<>("Foundation"));
		partyYearOfFoundationTableColumn.setMinWidth(150);

		candidateIDTableColumn = new TableColumn<CandidateModel, Integer>("ID");
		candidateIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		candidateIDTableColumn.setMinWidth(100);

		candidateNameTableColumn = new TableColumn<CandidateModel, String>("Full Name");
		candidateNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("FullName"));
		candidateNameTableColumn.setMinWidth(150);

		candidateRankTableColumn = new TableColumn<CandidateModel, Integer>("Rank");
		candidateRankTableColumn.setCellValueFactory(new PropertyValueFactory<>("Rank"));
		candidateRankTableColumn.setMinWidth(50);

		partiesTableView.getColumns().addAll(partyNameTableColumn, partyWingTableColumn,
				partyYearOfFoundationTableColumn);
		for (TableColumn<?, ?> tableColumn : partiesTableView.getColumns()) {
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setResizable(false);
		}
		partiesTableView.setOpacity(0.8);
		candidatesInPartyTableView.getColumns().addAll(candidateIDTableColumn, candidateNameTableColumn,
				candidateRankTableColumn, UIHandler.buildStatusTableColumn(410));
		for (TableColumn<?, ?> tableColumn : candidatesInPartyTableView.getColumns()) {
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setResizable(false);
		}
		candidatesInPartyTableView.setOpacity(0.8);

		gridPane.add(vBox, 0, 0, 2, 1);
		gridPane.add(partiesTableView, 0, 1, 1, 1);
		gridPane.add(candidatesInPartyTableView, 1, 1, 1, 1);

		GridPane.setMargin(vBox, new Insets(110, 0, 0, 0));
		GridPane.setMargin(partiesTableView, new Insets(0, 10, 373.5, 0));
		GridPane.setMargin(candidatesInPartyTableView, new Insets(0, 0, 373.5, 10));
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
		
		UIHandler.addCursorEffectsToNode(scene, addPartyButton);
		UIHandler.addCursorEffectsToNode(scene, addCandidateToPartyButton);
		UIHandler.addCursorEffectsToNode(scene, removePartyButton);
	}
}
