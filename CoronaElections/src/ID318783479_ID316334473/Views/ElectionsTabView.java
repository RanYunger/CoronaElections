package ID318783479_ID316334473.Views;

import java.util.ArrayList;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ElectionsTabView extends View {
	// Fields
	private GridPane gridPane;
	private Button runElectionsButton, showResultsButton;
	private HBox hBox;
	private PieChart finalResultsPieChart;
	private BarChart<String, Number> resultsByBallotBarChart;

	// Properties (Getters and Setters)
	public Button getRunElectionsButton() {
		return runElectionsButton;
	}

	public Button getShowResultsButton() {
		return showResultsButton;
	}

	public PieChart getFinalResultsPieChart() {
		return finalResultsPieChart;
	}

	public BarChart<String, Number> getResultsByBallotBarChart() {
		return resultsByBallotBarChart;
	}

	public void setResultsByBallotBarChart(BarChart<String, Number> resultsByBallotBarChart) {
		this.resultsByBallotBarChart = resultsByBallotBarChart;

		gridPane.add(resultsByBallotBarChart, 1, 1, 2, 1);

		GridPane.setMargin(resultsByBallotBarChart, new Insets(10, 0, 145, 0));
	}

	// Constructors
	public ElectionsTabView(Stage stage) {
		super(stage);

		buildScene();
	}

	// Methods
	@Override
	protected void buildScene() {
		gridPane = new GridPane();
		runElectionsButton = new Button("Run Elections");
		showResultsButton = new Button("Show Results");
		hBox = new HBox();
		finalResultsPieChart = new PieChart();
		resultsByBallotBarChart = new BarChart<String, Number>(new CategoryAxis(), new NumberAxis());

		runElectionsButton.setMinWidth(100);
		showResultsButton.setMinWidth(100);
		resultsByBallotBarChart.setOpacity(0); // invisible at first

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

		finalResultsPieChart.setOpacity(0.8);
		finalResultsPieChart.setOpacity(0.8);

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

	@Override
	protected void addEffects() {
		UIHandler.addCursorEffectsToNode(runElectionsButton);
		UIHandler.addCursorEffectsToNode(showResultsButton);
	}

	public void buildResultsByBallotBarChart(ObservableList<BallotModel> ballots, ObservableList<PartyModel> parties) {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
		ArrayList<Series<String, Number>> partySeries = new ArrayList<XYChart.Series<String, Number>>();
		Series<String, Number> currentPartySeries;
		String currentPartyName;

		// Building the Bar chart
		barChart.setTitle("Votes by Ballots");
		barChart.setOpacity(0.6);

		// Building the axis
		xAxis.setCategories(getAllBallotIDs(ballots));
		xAxis.setLabel("Ballot IDs");
		xAxis.setTickLabelFont(new Font(15));
		yAxis.setLabel("Votes");
		yAxis.setTickLabelFont(new Font(15));

		// Setting the chart data
		for (PartyModel party : parties) {
			currentPartySeries = new Series<String, Number>();
			currentPartySeries.setName(currentPartyName = party.getTextualName());
			for (int i = 0; i < ballots.size(); i++) {
				if (ballots.get(i).getResults().get(currentPartyName) > 0)
					currentPartySeries.getData().add(new XYChart.Data<>(xAxis.getCategories().get(i),
							ballots.get(i).getResults().get(currentPartyName)));
			}
			if (!currentPartySeries.getData().isEmpty())
				partySeries.add(currentPartySeries);
		}

		barChart.getData().addAll(partySeries);

		setResultsByBallotBarChart(barChart);
	}

	private ObservableList<String> getAllBallotIDs(ObservableList<BallotModel> allBallots) {
		ArrayList<String> ballotIDs = new ArrayList<String>();

		for (BallotModel ballot : allBallots)
			ballotIDs.add(
					String.format("#%d (%.2f%% voted)", ballot.getNumericID(), ballot.getNumericVotersPercentage()));

		return FXCollections.observableList(ballotIDs);
	}
}
