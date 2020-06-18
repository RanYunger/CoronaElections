package ID318783479_ID316334473.Views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

import ID318783479_ID316334473.SearchHandler;
import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Citizens.CandidateModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Models.Citizens.SickCandidateModel;
import ID318783479_ID316334473.Models.Citizens.SickCitizenModel;
import ID318783479_ID316334473.Models.Citizens.SoldierModel;
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

	private ObservableList<CitizenModel> allCitizens;
	private TableView<CitizenModel> citizensTableView;

	// Properties (Getters and Setters)
	public ObservableList<CitizenModel> getAllCitizens() {
		return allCitizens;
	}

	public ObservableList<CitizenModel> getOnlyCitizens() {
		ObservableList<CitizenModel> onlyCitizens = allCitizens;

		onlyCitizens.removeIf(new Predicate<CitizenModel>() {

			@Override
			public boolean test(CitizenModel citizen) {
				return citizen instanceof CandidateModel;
			}
		});

		return onlyCitizens;
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

		gridPane = new GridPane();
		addCitizenButton = new Button("Add Citizen");
		hBox = new HBox();
		citizensTableView = new TableView<CitizenModel>();
		citizensTableView.setItems(allCitizens);

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
			tableColumn.setStyle("-fx-alignment: CENTER;");
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

	public void initCitizens() {
		allCitizens = FXCollections.observableArrayList();

		// Initiates 5 citizen
		allCitizens.add(new CitizenModel(123456789, "Charles Foster Kane", 1941, 0, SearchHandler.getBallotByID(1),
				false, false));
		allCitizens.add(
				new CitizenModel(234567890, "Donald John Trump", 1946, 1, SearchHandler.getBallotByID(4), true, true));
		allCitizens
				.add(new CitizenModel(345678901, "Tonny Stark", 1970, 0, SearchHandler.getBallotByID(1), false, false));
		allCitizens.add(
				new SickCitizenModel(456789012, "Steve Rogers", 1918, 1, SearchHandler.getBallotByID(1), true, true));
		allCitizens.add(new SoldierModel(567890123, "Childish Gambino", 2001, 1, SearchHandler.getBallotByID(3), false,
				false, true));

		// Initiates 8 candidates (2 per party)
		allCitizens.add(new CandidateModel(678901234, "Benjamin Netanyahu", 1949, 50, SearchHandler.getBallotByID(5),
				true, false));
		allCitizens.add(
				new SickCandidateModel(789012345, "Miri Regev", 1965, 38, SearchHandler.getBallotByID(5), true, false));
		allCitizens.add(
				new SickCandidateModel(890123456, "Benny Gantz", 1959, 40, SearchHandler.getBallotByID(4), true, true));
		allCitizens.add(
				new SickCandidateModel(901234567, "Yair Lapid", 1963, 1, SearchHandler.getBallotByID(4), true, true));
		allCitizens.add(new SickCandidateModel(901234568, "Avigdor Lieberman", 1958, 1, SearchHandler.getBallotByID(3),
				true, true));
		allCitizens.add(
				new CandidateModel(901234566, "Oded Forer", 1977, 1, SearchHandler.getBallotByID(2), false, false));
		allCitizens.add(
				new CandidateModel(901234569, "Tamar Zandberg", 1976, 1, SearchHandler.getBallotByID(2), false, false));
		allCitizens.add(new CandidateModel(901234565, "Nitzan Horowitz", 1965, 1, SearchHandler.getBallotByID(2), false,
				false));

		citizensTableView.setItems(allCitizens);
	}

	public Node asNode() {
		return (Node) gridPane;
	}

	public void addEffects(Stage stage) {
		Scene scene = stage.getScene();

		UIHandler.addCursorEffectsToNode(scene, addCitizenButton);
	}

	public void addCitizen(CitizenModel citizen) {
		allCitizens.add(citizen);
	}

	public CitizenModel getCitizenByID(int citizenID) {
		ArrayList<CitizenModel> sortedVoters = new ArrayList<CitizenModel>(allCitizens);
		Collections.sort(sortedVoters);
		return SearchHandler.binarySearch(sortedVoters, citizenID);
	}

	public CandidateModel morphCitizenToCandidate(CitizenModel citizen) {
		int index = allCitizens.indexOf(getCitizenByID(citizen.getNumericID()));

		if (index != -1) {
			CandidateModel candidate;
			if (citizen instanceof CitizenModel) { // "morphs" the CitizenModel into a CandidateModel
				candidate = new CandidateModel(citizen.getNumericID(), citizen.getTextualFullName(),
						citizen.getNumericYearOfBirth(), citizen.getNumericDaysOfSickness(),
						citizen.getActualAssociatedBallot(), citizen.isIsolated(), citizen.isWearingSuit());
				allCitizens.set(index, candidate);

				return candidate;
			} else if (citizen instanceof SickCitizenModel) {
				candidate = new SickCandidateModel(citizen.getNumericID(), citizen.getTextualFullName(),
						citizen.getNumericYearOfBirth(), citizen.getNumericDaysOfSickness(),
						citizen.getActualAssociatedBallot(), citizen.isIsolated(), citizen.isWearingSuit());
				allCitizens.set(index, candidate);

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
