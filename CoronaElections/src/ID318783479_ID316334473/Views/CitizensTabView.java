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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class CitizensTabView {
	// Constants

	// Fields
	private GridPane gridPane;
	private Button addCitizenButton, removeCitizenButton;
	private HBox hBox;

	private ObservableList<CitizenModel> citizens;
	private TableView<CitizenModel> citizensTableView;

	// Properties (Getters and Setters)
	public ObservableList<CitizenModel> getAllCitizens() {
		return citizens;
	}

	// Constructors
	public CitizensTabView() {
		buildScene();
	}

	// Methods
	@SuppressWarnings("unchecked")
	private void buildScene() {
		TableColumn<CitizenModel, Number> citizenIDTableColumn, citizenYearOfBirthTableColumn,
				citizenAssociatedBallotTableColumn;
		TableColumn<CitizenModel, String> citizenNameTableColumn;

		citizens = FXCollections.observableArrayList();
		gridPane = new GridPane();
		addCitizenButton = new Button("Add Citizen");
		removeCitizenButton = new Button("Remove Citizen");
		hBox = new HBox();
		citizensTableView = new TableView<CitizenModel>();
		citizensTableView.setItems(citizens);

		addCitizenButton.setMinWidth(100);
		removeCitizenButton.setMinWidth(100);

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(20);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(80);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(100);

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(addCitizenButton, removeCitizenButton);
		HBox.setMargin(addCitizenButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(removeCitizenButton, new Insets(0, 0, 0, 10));

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
				UIHandler.buildStatusTableColumn(750));
		for (TableColumn<?, ?> tableColumn : citizensTableView.getColumns()) {
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
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

	public void addEffects(Stage stage) {
		Scene scene = stage.getScene();

		UIHandler.addCursorEffectsToNode(scene, addCitizenButton);
		UIHandler.addCursorEffectsToNode(scene, removeCitizenButton);
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
			if (citizen.getClass() == CitizenModel.class) { // "morphs" the CitizenModel into a CandidateModel
				candidate = new CandidateModel(citizen.getNumericID(), citizen.getTextualFullName(),
						citizen.getNumericYearOfBirth(), citizen.getNumericDaysOfSickness(),
						citizen.getActualAssociatedBallot(), citizen.isIsolated(), citizen.isWearingSuit());
				citizens.set(index, candidate);
				return candidate;
			} else if (citizen.getClass() == SickCitizenModel.class) {
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
}
