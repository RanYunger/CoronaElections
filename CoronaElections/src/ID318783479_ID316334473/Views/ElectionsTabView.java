package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.ElectionsTabModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class ElectionsTabView {
	// Constants

	// Fields
	private Group root;
	private GridPane gridPane;
	private Button runElectionsButton, showResultsButton;
	private HBox hBox;
	private PieChart finalResultsPieChart;
	private BarChart<String, Number> resultsByBallotBarChart;

	// Properties (Getters and Setters)
	public void setRoot(Group root) {
		this.root = root;
	}

	// Constructors
	public ElectionsTabView() {
		setRoot(new Group());
		
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
		finalResultsPieChart = new PieChart();
		resultsByBallotBarChart = new BarChart<String, Number>(new CategoryAxis(), new NumberAxis());

		runElectionsButton.setMinWidth(100);
		showResultsButton.setMinWidth(100);
		
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(20);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(80);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(30);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(35);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(2).setPercentWidth(35);

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(runElectionsButton, showResultsButton);
		HBox.setMargin(runElectionsButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(showResultsButton, new Insets(0, 0, 0, 10));

		finalResultsPieChart.setTitle("Final Results");
		finalResultsPieChart.setOpacity(0.8);
		finalResultsPieChart.setOpacity(0.8);

		resultsByBallotBarChart.setTitle("Votes by Ballots");
		resultsByBallotBarChart.getXAxis().setLabel("Ballot IDs");
		resultsByBallotBarChart.getYAxis().setLabel("Votes");
		resultsByBallotBarChart.setOpacity(0.8);

		gridPane.add(hBox, 0, 0, 3, 1);
		gridPane.add(finalResultsPieChart, 0, 1, 1, 1);
		gridPane.add(resultsByBallotBarChart, 1, 1, 2, 1);

		GridPane.setMargin(hBox, new Insets(110, 0, 0, 0));
		GridPane.setMargin(finalResultsPieChart, new Insets(10, 0, 145, 0));
		GridPane.setMargin(resultsByBallotBarChart, new Insets(10, 0, 145, 0));
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
		
		UIHandler.addCursorEffectsToNode(scene, runElectionsButton);
		UIHandler.addCursorEffectsToNode(scene, showResultsButton);
	}
}
