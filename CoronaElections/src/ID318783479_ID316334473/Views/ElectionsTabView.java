package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.ElectionsTabModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class ElectionsTabView {
	// Constants

	// Fields
	private Group root;
	private GridPane gridPane;
	private Button runElectionsButton, showResultsButton;
	private HBox hBox;
	private TableView<String> finalResultsTableView;
	private BarChart<String, Number> resultsByBallotBarChart;

	// Properties (Getters and Setters)

	// Constructors
	public ElectionsTabView() {
		root = new Group();

		buildScene();
	}

	// Methods

	public void refresh(ElectionsTabModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene() {
		gridPane = new GridPane();
		runElectionsButton = new Button("Run Elections");
		showResultsButton = new Button("Show Results");
		hBox = new HBox();
		finalResultsTableView = new TableView<String>();
		resultsByBallotBarChart = new BarChart<String, Number>(new CategoryAxis(), new NumberAxis());

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(10);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(90);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(20);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(40);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(2).setPercentWidth(40);

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(runElectionsButton, showResultsButton);
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

		gridPane.add(hBox, 0, 0, 3, 1);
		gridPane.add(finalResultsTableView, 0, 1, 1, 1);
		gridPane.add(resultsByBallotBarChart, 1, 1, 2, 1);

		GridPane.setMargin(hBox, new Insets(70, 0, 0, 0));
		GridPane.setMargin(finalResultsTableView, new Insets(10, 0, 370, 0));
		GridPane.setMargin(resultsByBallotBarChart, new Insets(10, 0, 350, 0));
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
