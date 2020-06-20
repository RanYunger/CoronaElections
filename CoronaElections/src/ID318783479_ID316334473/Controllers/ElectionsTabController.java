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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Tooltip;

public class ElectionsTabController {
	// Fields
	private ElectionsTabView tabView;
	private boolean electionsOccoured;

	// Properties (Getters and Setters)
	public void setElectionsTabView(ElectionsTabView electionsTabView) {
		this.tabView = electionsTabView;
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
						UIHandler.getMainView().disableAllButtons(true);
						boolean noOneHasVoted = true;
						ObservableList<BallotModel> allBallots = UIHandler.getMainView().getAllBallots();
						ObservableList<CitizenModel> allVoters = UIHandler.getMainView().getAllCitizens();
						ObservableList<PartyModel> allParties = UIHandler.getMainView().getAllParties();
						TreeMap<String, Integer> resultsInBallot;
						BallotModel voterBallot;
						String chosenParty;
						int partyVotes;

						if (allBallots.isEmpty()) {
							UIHandler.showWarning(
									"Make sure to have at least one ballot before starting the elections!");
							return;
						}
						if (allVoters.isEmpty()) {
							UIHandler
									.showWarning("Make sure to have at least one voter before starting the elections!");
							return;
						}
						if (allParties.isEmpty()) {
							UIHandler
									.showWarning("Make sure to have at least one party before starting the elections!");
							return;
						}

						for (BallotModel ballot : allBallots) {
							for (PartyModel party : allParties)
								ballot.getResults().put(party.getTextualName(), 0);
						}

						for (CitizenModel voter : allVoters) {
							chosenParty = UIHandler.vote(voter, allParties);
							if ((chosenParty != null) && (!chosenParty.equalsIgnoreCase("<UNKNOWN>"))
									&& (!chosenParty.equalsIgnoreCase("I don't want to vote"))) {
								noOneHasVoted = false;
								voterBallot = voter.getActualAssociatedBallot();
								resultsInBallot = voterBallot.getResults();
								partyVotes = resultsInBallot.get(chosenParty);
								resultsInBallot.replace(chosenParty, ++partyVotes);
								voterBallot.voteConfirmed();
							}
						}

						if (noOneHasVoted) {
							UIHandler.showWarning("No one has voted! please start the election process again..");
							UIHandler.getMainView().disableAllButtons(false);
						} else {
							setElectionsOccoured(true);
							UIHandler.showSuccess("The elections process has complete!");
							UIHandler.getMainView().getTabPane().setDisable(false);
							tabView.getShowResultsButton().setDisable(false);
						}
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
						UIHandler.showWarning("Elections results will be visible once the process is complete.");
						return;
					}

					ObservableList<BallotModel> allBallots = UIHandler.getMainView().getAllBallots();
					ObservableList<PartyModel> allParties = UIHandler.getMainView().getAllParties();

					PieChart finalResultsPieChart = tabView.getFinalResultsPieChart();
					TreeMap<String, Integer> finalResults = getFinalResults(allBallots);
					ArrayList<PieChart.Data> finalResultsPieChartData = new ArrayList<PieChart.Data>();
					double totalPieValue = 0;

					// Displays the final results
					finalResultsPieChart.setTitle("Final Results");
					for (Map.Entry<String, Integer> resultEntry : finalResults.entrySet()) {
						totalPieValue += resultEntry.getValue();
						if (resultEntry.getValue() > 0)
							finalResultsPieChartData
									.add(new PieChart.Data(resultEntry.getKey(), resultEntry.getValue()));
					}
					finalResultsPieChart.setData(FXCollections.observableList(finalResultsPieChartData));

					for (Data data : finalResultsPieChart.getData()) {
						Tooltip.install(data.getNode(),
								new Tooltip(String.format("%.2f%%", (data.getPieValue() / totalPieValue) * 100)));
					}

					tabView.buildResultsByBallotBarChart(allBallots, allParties);
					tabView.getShowResultsButton().setDisable(true);
				} catch (Exception ex) {
					ex.printStackTrace();
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};

		tabView.getRunElectionsButton().setOnAction(runElectionsButtonEventHandler);
		tabView.getShowResultsButton().setOnAction(showResultsButtonEventHandler);
	}

	// Methods

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
}
