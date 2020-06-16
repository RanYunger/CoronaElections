package ID318783479_ID316334473.Controllers;

import java.util.TreeMap;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Views.ElectionsTabView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ElectionsTabController {
	// Constants

	// Fields
	private ElectionsTabView tabView;

	// Properties (Getters and Setters)
	public ElectionsTabView getElectionsTabView() {
		return tabView;
	}

	public void setElectionsTabView(ElectionsTabView electionsTabView) {
		this.tabView = electionsTabView;
	}

	// Constructors
	public ElectionsTabController(ElectionsTabView view) {
		setElectionsTabView(view);

		EventHandler<ActionEvent> runElectionsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					ObservableList<BallotModel> allBallots = UIHandler.getMainView().getAllBallots();
					ObservableList<CitizenModel> allVoters = UIHandler.getMainView().getAllCitizens();
					ObservableList<PartyModel> allParties = UIHandler.getMainView().getAllParties();
					TreeMap<String, Integer> resultsInBallot;
					BallotModel voterBallot;
					String chosenParty;
					int partyVotes;

					for (BallotModel ballot : allBallots) {
						ballot.setResults(new TreeMap<String, Integer>());
						for (PartyModel party : allParties)
							ballot.getResults().put(party.getTextualName(), 0);
					}

					for (CitizenModel voter : allVoters) {
						chosenParty = UIHandler.vote(voter, allParties);
						voterBallot = voter.getActualAssociatedBallot();
						resultsInBallot = voterBallot.getResults();
						partyVotes = resultsInBallot.get(chosenParty);

						resultsInBallot.replace(chosenParty, ++partyVotes);
					}
					partyVotes = 0;

				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> showResultsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// TODO: COMPLETE

				} catch (IllegalStateException ex) {
					UIHandler.showError(ex.getMessage());
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};

		tabView.getRunElectionsButton().setOnAction(runElectionsButtonEventHandler);
		tabView.getShowResultsButton().setOnAction(showResultsButtonEventHandler);
	}

	// Methods
}
