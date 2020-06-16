package ID318783479_ID316334473.Controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Views.ElectionsTabView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class ElectionsTabController {
	// Constants

	// Fields
	private ElectionsTabView tabView;
	private boolean electionsOccoured;

	// Properties (Getters and Setters)
	public ElectionsTabView getElectionsTabView() {
		return tabView;
	}

	public void setElectionsTabView(ElectionsTabView electionsTabView) {
		this.tabView = electionsTabView;
	}

	public boolean isElectionsOccoured() {
		return electionsOccoured;
	}

	public void setElectionsOccoured(boolean electionsOccoured) {
		this.electionsOccoured = electionsOccoured;
	}

	// Constructors
	public ElectionsTabController(ElectionsTabView view) {
		setElectionsTabView(view);
		setElectionsOccoured(false);

		EventHandler<ActionEvent> runElectionsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// Validations
					if (electionsOccoured) {
						UIHandler.showError("Election process already complete. Try again next time.");

						return;
					} else {
						ObservableList<BallotModel> allBallots = UIHandler.getMainView().getAllBallots();
						ObservableList<CitizenModel> allVoters = UIHandler.getMainView().getAllCitizens();
						ObservableList<PartyModel> allParties = UIHandler.getMainView().getAllParties();
						TreeMap<String, Integer> resultsInBallot;
						BallotModel voterBallot;
						String chosenParty;
						int partyVotes;

						if (allBallots.isEmpty()) {
							UIHandler
									.showWarning("Make sure to have at least one ballot before starting the elections");
							return;
						}
						if (allVoters.isEmpty()) {
							UIHandler.showWarning("Make sure to have at least one voter before starting the elections");
							return;
						}
						if (allParties.isEmpty()) {
							UIHandler.showWarning("Make sure to have at least one party before starting the elections");
							return;
						}

						for (BallotModel ballot : allBallots) {
							ballot.setResults(new TreeMap<String, Integer>());
							for (PartyModel party : allParties)
								ballot.getResults().put(party.getTextualName(), 0);
						}

						for (CitizenModel voter : allVoters) {
							chosenParty = UIHandler.vote(voter, allParties);
							if (chosenParty != null && chosenParty != "<UNKNOWN>") {
								voterBallot = voter.getActualAssociatedBallot();
								resultsInBallot = voterBallot.getResults();
								partyVotes = resultsInBallot.get(chosenParty);

								resultsInBallot.replace(chosenParty, ++partyVotes);
							}
						}

						setElectionsOccoured(true);
						UIHandler.showSuccess("The elections process has complete!");
					}

				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> showResultsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// Validations
					if (!electionsOccoured) {
						UIHandler.showError("Elections results will be visible once the process is complete");

						return;
					}

					ObservableList<BallotModel> allBallots = UIHandler.getMainView().getAllBallots();
					ObservableList<PartyModel> allParties = UIHandler.getMainView().getAllParties();

					PieChart finalResultsPieChart = tabView.getFinalResultsPieChart();
					TreeMap<String, Integer> finalResults = getFinalResults(allBallots);
					ArrayList<PieChart.Data> finalResultsPieChartData = new ArrayList<PieChart.Data>();

					// Displays the final results
					finalResultsPieChart.setTitle("Final Results");
					for (Map.Entry<String, Integer> resultEntry : finalResults.entrySet())
						finalResultsPieChartData.add(new PieChart.Data(resultEntry.getKey(), resultEntry.getValue()));
					finalResultsPieChart.setData(FXCollections.observableList(finalResultsPieChartData));

					// Displays the results by ballots
					tabView.setResultsByBallotBarChart(buildResultsByBallotBarChart(allBallots, allParties));
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};

		tabView.getRunElectionsButton().setOnAction(runElectionsButtonEventHandler);
		tabView.getShowResultsButton().setOnAction(showResultsButtonEventHandler);
	}

	// Methods
	private ObservableList<String> getAllBallotIDs(ObservableList<BallotModel> allBallots) {
		ArrayList<String> ballotIDs = new ArrayList<String>();

		for (int i = 1; i <= allBallots.size(); i++)
			ballotIDs.add(String.format("#%d", i));

		return FXCollections.observableList(ballotIDs);
	}

	private TreeMap<String, Integer> getFinalResults(ObservableList<BallotModel> allBallots) {
		TreeMap<String, Integer> finalResults = new TreeMap<String, Integer>(), currentBallotResults;
		int currentTotalVotes;

		for (BallotModel ballot : allBallots) {
			currentBallotResults = ballot.getResults();
			if (!currentBallotResults.isEmpty()) {
				for (String partyName : currentBallotResults.keySet()) {
					int currentBallotVotes = currentBallotResults.get(partyName);
					if (finalResults.containsKey(partyName)) {
						currentTotalVotes = finalResults.get(partyName);
						finalResults.replace(partyName, currentTotalVotes + currentBallotVotes);
					} else
						finalResults.put(partyName, currentBallotVotes);
				}
			}
		}

		return finalResults;
	}

	private BarChart<String, Number> buildResultsByBallotBarChart(ObservableList<BallotModel> allBallots,
			ObservableList<PartyModel> allParties) {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
		ArrayList<Series<String, Number>> partySeries = new ArrayList<XYChart.Series<String, Number>>();
		Series<String, Number> currentPartySeries;
		String currentPartyName, currentBallotTextualID;

		// Building the Bar chart
		barChart.setTitle("Votes by Ballots");
		barChart.setOpacity(0.8);

		// Building the axis
		xAxis.setCategories(getAllBallotIDs(allBallots));
		xAxis.setLabel("Ballot IDs");
		yAxis.setLabel("Votes");

		// Setting the chart data
		for (PartyModel party : allParties) {
			currentPartySeries = new Series<String, Number>();
			currentPartySeries.setName(currentPartyName = party.getTextualName());
			for (BallotModel ballot : allBallots) {
				currentBallotTextualID = String.format("#%d", ballot.getNumericID());
				currentPartySeries.getData()
						.add(new XYChart.Data<>(currentBallotTextualID, ballot.getResults().get(currentPartyName)));
			}
			partySeries.add(currentPartySeries);
		}

		barChart.getData().addAll(partySeries);

		return barChart;
	}
}
