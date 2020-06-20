package ID318783479_ID316334473.Views;

import java.time.LocalDate;
import java.util.List;

import ID318783479_ID316334473.SearchHandler;
import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Citizens.CandidateModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;

public class PartiesTabView extends View {
	// Fields
	private GridPane gridPane;
	private Button addPartyButton, addCandidateToPartyButton;
	private VBox vBox;
	private HBox row1HBox, row2HBox;
	private Label partiesLabel, candidatesInPartyLabel;
	private ObservableList<PartyModel> allParties;
	private TableView<PartyModel> partiesTableView;
	private TableView<CandidateModel> candidatesInPartyTableView;

	// Properties (Getters and Setters)
	public ObservableList<PartyModel> getAllParties() {
		return allParties;
	}

	public TableView<CandidateModel> getCandidatesInPartyTableView() {
		return candidatesInPartyTableView;
	}

	public Button getAddPartyButton() {
		return addPartyButton;
	}

	public void setAddPartyButton(Button addPartyButton) {
		this.addPartyButton = addPartyButton;
	}

	public Button getAddCandidateToPartyButton() {
		return addCandidateToPartyButton;
	}

	public void setAddCandidateToPartyButton(Button addCandidateToPartyButton) {
		this.addCandidateToPartyButton = addCandidateToPartyButton;
	}

	public TableView<PartyModel> getPartiesTableView() {
		return partiesTableView;
	}

	// Constructors
	public PartiesTabView(Stage stage) {
		super(stage);

		buildScene();
	}

	// Methods
	@SuppressWarnings("unchecked")
	protected void buildScene() {
		TableColumn<PartyModel, String> partyFoundationTableColumn;
		TableColumn<PartyModel, String> partyNameTableColumn, partyWingTableColumn;
		TableColumn<CandidateModel, Number> candidateIDTableColumn;
		TableColumn<CandidateModel, String> candidateNameTableColumn, candidateRankTableColumn;

		gridPane = new GridPane();
		addPartyButton = new Button("Add Party");
		addCandidateToPartyButton = new Button("Add Candidate to Party");
		vBox = new VBox();
		row1HBox = new HBox();
		row2HBox = new HBox();
		partiesLabel = new Label("Parties");
		candidatesInPartyLabel = new Label("Candidates in Party");
		partiesTableView = new TableView<PartyModel>();
		partiesTableView.setItems(allParties);
		candidatesInPartyTableView = new TableView<CandidateModel>();

		addPartyButton.setMinWidth(150);
		addCandidateToPartyButton.setMinWidth(150);
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
		row1HBox.getChildren().addAll(addPartyButton, addCandidateToPartyButton);
		HBox.setMargin(addPartyButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(addCandidateToPartyButton, new Insets(0, 0, 0, 10));

		row2HBox.setAlignment(Pos.CENTER);
		row2HBox.getChildren().addAll(partiesLabel, candidatesInPartyLabel);
		HBox.setMargin(partiesLabel, new Insets(0, 250, 0, 50));
		HBox.setMargin(candidatesInPartyLabel, new Insets(0, 0, 0, 350));

		vBox.getChildren().addAll(row1HBox, row2HBox);

		partyNameTableColumn = new TableColumn<PartyModel, String>("Name");
		partyNameTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableName());
		partyNameTableColumn.setMinWidth(437);

		partyWingTableColumn = new TableColumn<PartyModel, String>("Wing");
		partyWingTableColumn.setCellValueFactory(new PropertyValueFactory<>("Wing"));
		partyWingTableColumn.setMinWidth(150);

		partyFoundationTableColumn = new TableColumn<PartyModel, String>("Foundation");
		partyFoundationTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableFoundationDate());
		partyFoundationTableColumn.setMinWidth(150);

		candidateIDTableColumn = new TableColumn<CandidateModel, Number>("ID");
		candidateIDTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableID());
		candidateIDTableColumn.setMinWidth(100);

		candidateNameTableColumn = new TableColumn<CandidateModel, String>("Full Name");
		candidateNameTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableFullName());
		candidateNameTableColumn.setMinWidth(150);

		candidateRankTableColumn = new TableColumn<CandidateModel, String>("Rank");
		candidateRankTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableRank());
		candidateRankTableColumn.setMinWidth(50);

		partiesTableView.getColumns().addAll(partyNameTableColumn, partyWingTableColumn, partyFoundationTableColumn);
		for (TableColumn<?, ?> tableColumn : partiesTableView.getColumns()) {
			tableColumn.setStyle("-fx-alignment: CENTER;");
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setSortable(false);
			tableColumn.setResizable(false);
		}
		partiesTableView.setOpacity(0.8);
		candidatesInPartyTableView.getColumns().addAll(candidateIDTableColumn, candidateNameTableColumn,
				candidateRankTableColumn, UIHandler.buildStatusTableColumn(392));
		for (TableColumn<?, ?> tableColumn : candidatesInPartyTableView.getColumns()) {
			tableColumn.setStyle("-fx-alignment: CENTER;");
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setSortable(false);
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

	public void initParties() {
		allParties = FXCollections.observableArrayList();

		// Initiates 4 parties
		allParties.add(new PartyModel("Halikud", PartyModel.PartyAssociation.Right, LocalDate.of(1973, 9, 13)));
		allParties.add(new PartyModel("Blue and White", PartyModel.PartyAssociation.Center, LocalDate.of(2019, 2, 21)));
		allParties.add(
				new PartyModel("Israel is Our Home", PartyModel.PartyAssociation.Center, LocalDate.of(1999, 3, 29)));
		allParties.add(
				new PartyModel("Israeli Labor Party", PartyModel.PartyAssociation.Left, LocalDate.of(1968, 1, 21)));

		// Adds the candidates to their parties
		List<CitizenModel> allCitizens = UIHandler.getMainView().getAllCitizens();
		allParties.get(0).addCandidate((CandidateModel) SearchHandler.getCitizenByID(678901234, allCitizens)); // Halikud->Benjamin Netnyahu
		allParties.get(0).addCandidate((CandidateModel) SearchHandler.getCitizenByID(789012345, allCitizens)); // Halikud->Miri Regev
		allParties.get(1).addCandidate((CandidateModel) SearchHandler.getCitizenByID(890123456, allCitizens)); // Blue and White->Benny Gantz
		allParties.get(1).addCandidate((CandidateModel) SearchHandler.getCitizenByID(901234567, allCitizens)); // Blue and White->Yait Lapid
		allParties.get(2).addCandidate((CandidateModel) SearchHandler.getCitizenByID(901234568, allCitizens)); // IIOH->Avigdor Liberman
		allParties.get(2).addCandidate((CandidateModel) SearchHandler.getCitizenByID(901234566, allCitizens)); // IIOH->Oded Forer
		allParties.get(3).addCandidate((CandidateModel) SearchHandler.getCitizenByID(901234569, allCitizens)); // ILP->Tamar Zandberg
		allParties.get(3).addCandidate((CandidateModel) SearchHandler.getCitizenByID(901234565, allCitizens)); // ILP->Nitzan Horowitz

		partiesTableView.setItems(allParties);
	}

	public Node asNode() {
		return (Node) gridPane;
	}

	public void addParty(PartyModel party) {
		allParties.add(party);
	}

	@Override
	protected void addEffects() {
		UIHandler.addCursorEffectsToNode(addPartyButton);
		UIHandler.addCursorEffectsToNode(addCandidateToPartyButton);
	}
}
