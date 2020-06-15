package ID318783479_ID316334473.Views;

import java.util.ArrayList;
import java.util.Collections;

import ID318783479_ID316334473.TBN;
import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Citizens.CandidateModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Models.Citizens.SickCandidateModel;
import ID318783479_ID316334473.Models.Citizens.SickCitizenModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class CitizensTabView extends View {
	// Constants

	// Fields
	private GridPane gridPane;
	private Button addCitizenButton;
	private HBox hBox;

	private ObservableList<CitizenModel> citizens;
	private TableView<CitizenModel> citizensTableView;

	// Properties (Getters and Setters)
	public ObservableList<CitizenModel> getAllCitizens() {
		return citizens;
	}

	public TableView<CitizenModel> getCitizensTableView() {
		return citizensTableView;
	}
	
	public Button getAddCitizenButton() {
		return addCitizenButton;
	}

	// Constructors
	public CitizensTabView(Stage stage) {
		super(stage);
		
		buildScene();
	}

	// Methods
	@Override
	@SuppressWarnings("unchecked")
	protected void buildScene() {
		TableColumn<CitizenModel, Number> citizenIDTableColumn, citizenYearOfBirthTableColumn,
				citizenAssociatedBallotTableColumn;
		TableColumn<CitizenModel, String> citizenNameTableColumn;

		citizens = FXCollections.observableArrayList();
		gridPane = new GridPane();
		addCitizenButton = new Button("Add Citizen");
		hBox = new HBox();
		citizensTableView = new TableView<CitizenModel>();
		citizensTableView.setItems(citizens);

		addCitizenButton.setMinWidth(100);

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(20);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(80);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(100);

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(addCitizenButton);

		citizenIDTableColumn = new TableColumn<CitizenModel, Number>("ID");
		citizenIDTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableID());
		citizenIDTableColumn.setMinWidth(200);

		citizenNameTableColumn = new TableColumn<CitizenModel, String>("Full Name");
		citizenNameTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableFullName());
		citizenNameTableColumn.setMinWidth(300);

		citizenYearOfBirthTableColumn = new TableColumn<CitizenModel, Number>("Birth");
		citizenYearOfBirthTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableYearOfBirth());
		citizenYearOfBirthTableColumn.setMinWidth(100);

		citizenAssociatedBallotTableColumn = new TableColumn<CitizenModel, Number>("Associated Ballot ID");
		citizenAssociatedBallotTableColumn
				.setCellValueFactory(cell -> cell.getValue().getActualAssociatedBallot().getObservableID());
		citizenAssociatedBallotTableColumn.setMinWidth(150);

		citizensTableView.getColumns().addAll(citizenIDTableColumn, citizenNameTableColumn,
				citizenYearOfBirthTableColumn, citizenAssociatedBallotTableColumn,
				UIHandler.buildStatusTableColumn(745));
		for (TableColumn<?, ?> tableColumn : citizensTableView.getColumns()) {
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setSortable(false);
			tableColumn.setResizable(false);
		}
		citizensTableView.setOpacity(0.8);

		gridPane.add(hBox, 0, 0, 1, 1);
		gridPane.add(citizensTableView, 0, 1, 1, 1);

		GridPane.setMargin(hBox, new Insets(110, 0, 0, 0));
		GridPane.setMargin(citizensTableView, new Insets(10, 0, 145, 0));
	}

	public Node asNode() {
		return (Node) gridPane;
	}

	public void addEffects(Stage stage) {
		Scene scene = stage.getScene();

		UIHandler.addCursorEffectsToNode(scene, addCitizenButton);
	}

	public void addCitizen(CitizenModel citizen) {
		citizens.add(citizen);
	}

	public CitizenModel getCitizenByID(int citizenID) {
		ArrayList<CitizenModel> sortedVoters = new ArrayList<CitizenModel>(citizens);
		Collections.sort(sortedVoters);
		return TBN.binarySearch(sortedVoters, citizenID);
	}

	public CandidateModel morphCitizenToCandidate(CitizenModel citizen) {
		int index = citizens.indexOf(getCitizenByID(citizen.getNumericID()));

		if (index != -1) {
			CandidateModel candidate;
			if (citizen instanceof CitizenModel) { // "morphs" the CitizenModel into a CandidateModel
				candidate = new CandidateModel(citizen.getNumericID(), citizen.getTextualFullName(),
						citizen.getNumericYearOfBirth(), citizen.getNumericDaysOfSickness(),
						citizen.getActualAssociatedBallot(), citizen.isIsolated(), citizen.isWearingSuit());
				citizens.set(index, candidate);

				return candidate;
			} else if (citizen instanceof SickCitizenModel) {
				candidate = new SickCandidateModel(citizen.getNumericID(), citizen.getTextualFullName(),
						citizen.getNumericYearOfBirth(), citizen.getNumericDaysOfSickness(),
						citizen.getActualAssociatedBallot(), citizen.isIsolated(), citizen.isWearingSuit());
				citizens.set(index, candidate);

				return candidate;
			} else if (citizen instanceof CandidateModel)
				return (CandidateModel) citizen;
		}
		return null;
	}

	@Override
	protected void addEffects() {
		UIHandler.addCursorEffectsToNode(stage.getScene(), addCitizenButton);
	}
}
