package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.PartiesTabModel;
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

public class PartiesTabView {
	// Constants

	// Fields
	private Group root;
	private GridPane gridPane;
	private Button addPartyButton, removePartyButton, addCandidateToPartyButton;
	private HBox hBox;
	private TableView<String> partiesTableView;

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
		TableColumn<String, String> partyNameTableColumn, partyWingTableColumn, partyYearOfFoundationTableColumn, partyCandidatesTableColumn;
		TableColumn<String, String> candidateIDTableColumn, candidateNameTableColumn, candidateRankTableColumn, candidateStatusTableColumn;
		ObservableList<TableColumn<String, ?>> candidateNestedTableColumns;

		gridPane = new GridPane();
		addPartyButton = new Button("Add Party");
		removePartyButton = new Button("Remove Party");
		addCandidateToPartyButton = new Button("Add Candidate to Party");
		hBox = new HBox();
		partiesTableView = new TableView<String>();

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(20);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(80);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(100);

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(addPartyButton, addCandidateToPartyButton, removePartyButton);
		HBox.setMargin(addPartyButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(addCandidateToPartyButton, new Insets(0, 10, 0, 10));
		HBox.setMargin(removePartyButton, new Insets(0, 0, 0, 10));

		partyNameTableColumn = new TableColumn<String, String>("Name");
		partyNameTableColumn.setMinWidth(300);
		
		partyWingTableColumn = new TableColumn<String, String>("Wing");
		partyWingTableColumn.setMinWidth(200);
		
		partyYearOfFoundationTableColumn = new TableColumn<String, String>("Foundation");
		partyYearOfFoundationTableColumn.setMinWidth(100);
		
		partyCandidatesTableColumn = new TableColumn<String, String>("Candidates");
		partyCandidatesTableColumn.setMinWidth(950);

		candidateNestedTableColumns = partyCandidatesTableColumn.getColumns();
		candidateIDTableColumn = new TableColumn<String, String>("ID");
		candidateIDTableColumn.setMinWidth(150);
		
		candidateNameTableColumn = new TableColumn<String, String>("Full Name");
		candidateNameTableColumn.setMinWidth(200);
		
		candidateRankTableColumn = new TableColumn<String, String>("Rank");
		candidateRankTableColumn.setMinWidth(50);
		
		candidateStatusTableColumn = new TableColumn<String, String>("Status");
		candidateStatusTableColumn.setMinWidth(470);
		
		candidateNestedTableColumns.addAll(candidateIDTableColumn, candidateNameTableColumn, candidateRankTableColumn, candidateStatusTableColumn);
		for (TableColumn<?, ?> tableColumn : candidateNestedTableColumns) {
			tableColumn.setReorderable(false);
			tableColumn.setResizable(false);
		}
		partiesTableView.getColumns().addAll(partyNameTableColumn, partyWingTableColumn, partyYearOfFoundationTableColumn, partyCandidatesTableColumn);
		for (TableColumn<?, ?> tableColumn : partiesTableView.getColumns()) {
			tableColumn.setReorderable(false);
			tableColumn.setResizable(false);
		}
		partiesTableView.setOpacity(0.8);
		
		gridPane.add(hBox, 0, 0, 1, 1);
		gridPane.add(partiesTableView, 0, 1, 1, 1);
		
		GridPane.setMargin(hBox, new Insets(110, 0, 0, 0));
		GridPane.setMargin(partiesTableView, new Insets(10, 0, 145, 0));
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
