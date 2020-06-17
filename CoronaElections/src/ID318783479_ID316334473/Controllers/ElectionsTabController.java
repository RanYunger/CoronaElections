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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ElectionsTabController {
	// Constants

	// Fields
	private ElectionsTabView tabView;
	private boolean electionsOccoured;
	private Label captionLabel;

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

	public Label getCaptionLabel() {
		return captionLabel;
	}
	
	public void setCaptionLabel(Label captionLabel) {
		this.captionLabel = captionLabel;
	}
	
	// Constructors
	public ElectionsTabController(ElectionsTabView view) {
		setElectionsTabView(view);
		setElectionsOccoured(false);
		setCaptionLabel(new Label());

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
								voterBallot.setVotersPercentage((int)voterBallot.getNumericVotersPercentage() + 1);
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
		EventHandler<MouseEvent> movePieChartEventHandler = new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {				
				Region region = (Region) event.getTarget();
				String str = region.getUserData().toString();
				
				// TODO: FIX
				captionLabel.setTranslateX(event.getX());
				captionLabel.setTranslateY(event.getY());
				captionLabel.setFont(new Font(15));
				captionLabel.setText(str);
				captionLabel.setVisible(true);
			}
		};
		EventHandler<MouseEvent> exitPieChartEventHandler = new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				captionLabel.setVisible(false);
				
			}
		};
		EventHandler<ActionEvent> showResultsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// Validations
					if (!electionsOccoured) {
						UIHandler.showError("Elections results will be visible once the process is complete.");

						return;
					}

					ObservableList<BallotModel> allBallots = UIHandler.getMainView().getAllBallots();
					ObservableList<PartyModel> allParties = UIHandler.getMainView().getAllParties();

					final Label captionLabel = new Label();
					PieChart finalResultsPieChart = tabView.getFinalResultsPieChart();
					TreeMap<String, Integer> finalResults = getFinalResults(allBallots);
					ArrayList<PieChart.Data> finalResultsPieChartData = new ArrayList<PieChart.Data>();
					PieChart.Data currentPieChartData;

					captionLabel.setTextFill(Color.DARKORANGE);
					captionLabel.setFont(new Font(15));

					// Displays the final results
					finalResultsPieChart.setTitle("Final Results");
					for (Map.Entry<String, Integer> resultEntry : finalResults.entrySet()) {
						currentPieChartData = new PieChart.Data(resultEntry.getKey(),
								resultEntry.getValue());
						
						finalResultsPieChartData.add(currentPieChartData);
					}
					finalResultsPieChart.setData(FXCollections.observableList(finalResultsPieChartData));
					
					for (Data data : finalResultsPieChart.getData()) {
						data.getNode().setUserData(String.valueOf(data.getPieValue())); // pass parameter to event handlers
						data.getNode().setOnMouseMoved(movePieChartEventHandler); 
						data.getNode().setOnMouseExited(exitPieChartEventHandler);
					}

					tabView.buildResultsByBallotBarChart(allBallots, allParties);
				} catch (Exception ex) {
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
