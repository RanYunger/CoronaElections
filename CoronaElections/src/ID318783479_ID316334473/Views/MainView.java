package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.Models.MainModel;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MainView {
	// Constants

	// Fields
	private Group root;
	private TabPane tabPane;
	private GridPane mainGridPane, ballotsGridPane, citizensGridPane, partiesGridPane;
	private Button runElectionsButton, showResultsButton, addBallotButton, removeBallotButton, addCitizenButton,
			removeCitizenButton, addPartyButton, removePartyButton;
	private HBox mainHBox, ballotsHBox, citizensHBox, partiesHBox;
	private TableView<String> finalResultsTableView, ballotsTableView, citizensTableView, partiesTableView;
	private BarChart<String, Number> resultsByBallotBarChart;

	// Properties (Getters and Setters)

	// Constructors
	public MainView(Stage stage) {
		root = new Group();

		buildScene(stage);
	}

	// Methods
	public void refresh(MainModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(Stage stage) {
		String[] tabNames = new String[] { "Main", "Ballots", "Citizens", "Parties" };
		Node[] tabContents = { buildMainTab(), buildBallotsTab(), buildCitizensTab(), buildPartiesTab() };

		tabPane = new TabPane();
		for (int i = 0; i < tabNames.length; i++)
			tabPane.getTabs().add(new Tab(tabNames[i], tabContents[i]));

		stage.setTitle("Corona Elections");
		stage.setResizable(false);
		// TODO: Set icon + background image
		stage.setScene(new Scene(tabPane, 1500, 900));
		stage.show();
	}

	private HBox buildStatusHBox() {
		HBox statusHBox = new HBox();
		CheckBox isolatedCheckBox = new CheckBox("Isolated"), wearingSuitCheckBox = new CheckBox("Wearing suit"),
				soldierCheckBox = new CheckBox("Soldier"), carryingWeaponCheckBox = new CheckBox("Carrying weapon");

		statusHBox.getChildren().addAll(isolatedCheckBox, wearingSuitCheckBox, soldierCheckBox, carryingWeaponCheckBox);
		HBox.setMargin(isolatedCheckBox, new Insets(0, 10, 0, 0));
		HBox.setMargin(wearingSuitCheckBox, new Insets(0, 10, 0, 10));
		HBox.setMargin(soldierCheckBox, new Insets(0, 10, 0, 10));
		HBox.setMargin(carryingWeaponCheckBox, new Insets(0, 0, 0, 10));
		statusHBox.setAlignment(Pos.CENTER);

		return statusHBox;
	}

	private Node buildMainTab() {
		mainGridPane = new GridPane();
		runElectionsButton = new Button("Run Elections");
		showResultsButton = new Button("Show Results");
		mainHBox = new HBox();
		finalResultsTableView = new TableView<String>();
		resultsByBallotBarChart = new BarChart<String, Number>(new CategoryAxis(), new NumberAxis());

		mainGridPane.getRowConstraints().add(new RowConstraints());
		mainGridPane.getRowConstraints().get(0).setPercentHeight(10);
		mainGridPane.getRowConstraints().add(new RowConstraints());
		mainGridPane.getRowConstraints().get(1).setPercentHeight(90);

		mainGridPane.getColumnConstraints().add(new ColumnConstraints());
		mainGridPane.getColumnConstraints().get(0).setPercentWidth(20);
		mainGridPane.getColumnConstraints().add(new ColumnConstraints());
		mainGridPane.getColumnConstraints().get(1).setPercentWidth(40);
		mainGridPane.getColumnConstraints().add(new ColumnConstraints());
		mainGridPane.getColumnConstraints().get(2).setPercentWidth(40);

		mainHBox.setAlignment(Pos.CENTER);
		mainHBox.getChildren().addAll(runElectionsButton, showResultsButton);
		HBox.setMargin(runElectionsButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(showResultsButton, new Insets(0, 0, 0, 10));

		finalResultsTableView.getColumns().add(new TableColumn<String, String>("Party"));
		finalResultsTableView.getColumns().get(0).setMinWidth(200);
		finalResultsTableView.getColumns().add(new TableColumn<String, Number>("Votes"));
		finalResultsTableView.getColumns().get(1).setMinWidth(100);
		finalResultsTableView.setOpacity(10); // for the background image to be seen

		resultsByBallotBarChart.setTitle("Votes by Ballots");
		resultsByBallotBarChart.getXAxis().setLabel("Ballot ID");
		resultsByBallotBarChart.getYAxis().setLabel("Votes");
		resultsByBallotBarChart.setOpacity(10); // for the background image to be seen

		mainGridPane.add(mainHBox, 0, 0, 3, 1);
		mainGridPane.add(finalResultsTableView, 0, 1, 1, 1);
		mainGridPane.add(resultsByBallotBarChart, 1, 1, 2, 1);

		return mainGridPane;
	}

	private Node buildBallotsTab() {
		ObservableList<TableColumn<String, ?>> voterColumns;

		ballotsGridPane = new GridPane();
		addBallotButton = new Button("Add Ballot");
		removeBallotButton = new Button("Remove Ballot");
		ballotsHBox = new HBox();
		ballotsTableView = new TableView<String>();

		ballotsGridPane.getRowConstraints().add(new RowConstraints());
		ballotsGridPane.getRowConstraints().get(0).setPercentHeight(10);
		ballotsGridPane.getRowConstraints().add(new RowConstraints());
		ballotsGridPane.getRowConstraints().get(1).setPercentHeight(90);

		ballotsGridPane.getColumnConstraints().add(new ColumnConstraints());
		ballotsGridPane.getColumnConstraints().get(0).setPercentWidth(100);

		ballotsHBox.setAlignment(Pos.CENTER);
		ballotsHBox.getChildren().addAll(addBallotButton, removeBallotButton);
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

		ballotsGridPane.add(ballotsHBox, 0, 0, 1, 1);
		ballotsGridPane.add(ballotsTableView, 0, 1, 1, 1);

		return ballotsGridPane;
	}

	private Node buildCitizensTab() {
		citizensGridPane = new GridPane();
		addCitizenButton = new Button("Add Citizen");
		removeCitizenButton = new Button("Remove Citizen");
		citizensHBox = new HBox();
		citizensTableView = new TableView<String>();

		citizensGridPane.getRowConstraints().add(new RowConstraints());
		citizensGridPane.getRowConstraints().get(0).setPercentHeight(10);
		citizensGridPane.getRowConstraints().add(new RowConstraints());
		citizensGridPane.getRowConstraints().get(1).setPercentHeight(90);

		citizensGridPane.getColumnConstraints().add(new ColumnConstraints());
		citizensGridPane.getColumnConstraints().get(0).setPercentWidth(100);

		citizensHBox.setAlignment(Pos.CENTER);
		citizensHBox.getChildren().addAll(addCitizenButton, removeCitizenButton);
		HBox.setMargin(addCitizenButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(removeCitizenButton, new Insets(0, 0, 0, 10));

		citizensTableView.getColumns().add(new TableColumn<String, String>("ID"));
		citizensTableView.getColumns().get(0).setMinWidth(200);
		citizensTableView.getColumns().add(new TableColumn<String, String>("Full Name"));
		citizensTableView.getColumns().get(1).setMinWidth(300);
		citizensTableView.getColumns().add(new TableColumn<String, Number>("Birth"));
		citizensTableView.getColumns().get(2).setMinWidth(100);
		citizensTableView.getColumns().add(new TableColumn<String, Number>("Associated Ballot"));
		citizensTableView.getColumns().get(3).setMinWidth(150);
		citizensTableView.getColumns().add(new TableColumn<String, String>("Status"));
		// use setStatusHBox() as the column's content
		citizensTableView.getColumns().get(4).setMinWidth(800);
		citizensTableView.setOpacity(10); // for the background image to be seen

		citizensGridPane.add(citizensHBox, 0, 0, 1, 1);
		citizensGridPane.add(citizensTableView, 0, 1, 1, 1);

		return citizensGridPane;
	}

	private Node buildPartiesTab() {
		ObservableList<TableColumn<String, ?>> candidateColumns;

		partiesGridPane = new GridPane();
		addPartyButton = new Button("Add Party");
		removePartyButton = new Button("Remove Party");
		partiesHBox = new HBox();
		partiesTableView = new TableView<String>();

		partiesGridPane.getRowConstraints().add(new RowConstraints());
		partiesGridPane.getRowConstraints().get(0).setPercentHeight(10);
		partiesGridPane.getRowConstraints().add(new RowConstraints());
		partiesGridPane.getRowConstraints().get(1).setPercentHeight(90);

		partiesGridPane.getColumnConstraints().add(new ColumnConstraints());
		partiesGridPane.getColumnConstraints().get(0).setPercentWidth(100);

		partiesHBox.setAlignment(Pos.CENTER);
		partiesHBox.getChildren().addAll(addPartyButton, removePartyButton);
		HBox.setMargin(addPartyButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(removePartyButton, new Insets(0, 0, 0, 10));

		partiesTableView.getColumns().add(new TableColumn<String, String>("Name"));
		partiesTableView.getColumns().get(0).setMinWidth(300);
		partiesTableView.getColumns().add(new TableColumn<String, String>("Wing"));
		partiesTableView.getColumns().get(1).setMinWidth(200);
		partiesTableView.getColumns().add(new TableColumn<String, Number>("Foundation"));
		partiesTableView.getColumns().get(2).setMinWidth(100);
		partiesTableView.getColumns().add(new TableColumn<String, String>("Candidates"));
		partiesTableView.getColumns().get(3).setMinWidth(950);
		partiesTableView.setOpacity(10); // for the background image to be seen

		candidateColumns = partiesTableView.getColumns().get(3).getColumns();
		candidateColumns.add(new TableColumn<String, String>("ID"));
		candidateColumns.get(0).setMinWidth(150);
		candidateColumns.add(new TableColumn<String, String>("Full Name"));
		candidateColumns.get(1).setMinWidth(200);
		candidateColumns.add(new TableColumn<String, String>("Rank"));
		candidateColumns.get(2).setMinWidth(50);
		candidateColumns.add(new TableColumn<String, Boolean>("Status"));
		// use setStatusHBox() as the column's content
		candidateColumns.get(3).setMinWidth(550);

		partiesGridPane.add(partiesHBox, 0, 0, 1, 1);
		partiesGridPane.add(partiesTableView, 0, 1, 1, 1);

		return partiesGridPane;
	}
}
