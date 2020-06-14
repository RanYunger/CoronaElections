package ID318783479_ID316334473.Controllers;

import java.util.ArrayList;
import java.util.function.Predicate;

import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Citizens.CandidateModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Views.AddCandidateToPartyView;
import ID318783479_ID316334473.Views.CitizensTabView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddCandidateToPartyController {
	// Constants

	// Fields
	private CitizensTabView tabView;
	private AddCandidateToPartyView addView;
	private Predicate<CitizenModel> unionPredicate, candidateIDPredicate, candidateNamePredicate;
	private FilteredList<CitizenModel> filteredCitizens;

	// Properties (Getters and Setters)

	public FilteredList<CitizenModel> getFilteredCitizens() {
		return filteredCitizens;
	}

	public void setFilteredCitizens(FilteredList<CitizenModel> filteredCitizens) {
		this.filteredCitizens = filteredCitizens;
	}

	public Predicate<CitizenModel> getCandidateIDPredicate() {
		return candidateIDPredicate;
	}

	public void setCandidateIDPredicate(Predicate<CitizenModel> candidateIDPredicate) {
		this.candidateIDPredicate = candidateIDPredicate;
	}

	public Predicate<CitizenModel> getCandidateNamePredicate() {
		return candidateNamePredicate;
	}

	public void setCandidateNamePredicate(Predicate<CitizenModel> candidateNamePredicate) {
		this.candidateNamePredicate = candidateNamePredicate;
	}

	public Predicate<CitizenModel> getUnionPredicate() {
		return unionPredicate;
	}

	public void setUnionPredicate(Predicate<CitizenModel> unionCandidate) {
		this.unionPredicate = unionCandidate;
	}

	public AddCandidateToPartyView getAddCandidateToPartyView() {
		return addView;
	}

	public void setAddCandidateToPartyView(AddCandidateToPartyView view) {
		this.addView = view;
	}

	public void setCitizensTabView(CitizensTabView view) {
		this.tabView = view;
	}

	// Constructors
	public AddCandidateToPartyController(PartyModel selectedParty, AddCandidateToPartyView addCandidateView,
			CitizensTabView citizensTabView) {

		setCitizensTabView(citizensTabView);
		setAddCandidateToPartyView(addCandidateView);

		setUnionPredicate(p -> true);
		setCandidateIDPredicate(p -> true);
		setCandidateIDPredicate(p -> true);
		setFilteredCitizens(new FilteredList<CitizenModel>(citizensTabView.getAllCitizens(), unionPredicate));

		ChangeListener<String> candidateIDTextFieldChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				TextField candidateNameTextField = (TextField) addCandidateView.getNodeByName("candidateNameTextField");

				refreshCitizensTableView();
			}
		};
		ChangeListener<String> candidateNameTextFieldChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				TextField candidateIDTextField = (TextField) addCandidateView.getNodeByName("candidateIDTextField");

				refreshCitizensTableView();
			}
		};
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent event) {
				TableView<CitizenModel> citizensTableView = ((TableView<CitizenModel>) addCandidateView
						.getNodeByName("citizensTableView"));
				if (citizensTableView.getSelectionModel().getSelectedIndex() != -1) {
					CitizenModel selectedCitizen = citizensTableView.getSelectionModel().getSelectedItem();
					CandidateModel candidate = citizensTabView.morphCitizenToCandidate(selectedCitizen);
					selectedParty.addCandidate(candidate);
				}
				addCandidateView.close();
			}
		};

		addView.addChangeListenerToTextField("candidateIDTextField", candidateIDTextFieldChangeListener);
		addView.addChangeListenerToTextField("candidateNameTextField", candidateNameTextFieldChangeListener);
		addView.addEventHandlerToButton("submitButton", submitButtonEventHandler);
	}

	// Methods
	@SuppressWarnings("unchecked")
	private void refreshCitizensTableView() {
		TableView<String> citizensTableView = (TableView<String>) addView.getNodeByName("citizensTableView");
		ObservableList<CitizenModel> citizens = tabView.getAllCitizens();
		ArrayList<String> citizensStringDetails = new ArrayList<String>();

		for (CitizenModel citizen : citizens)
			citizensStringDetails.add(String.format("%d|%s", citizen.getNumericID(), citizen.getTextualFullName()));

		citizensTableView.setItems(FXCollections.observableList(citizensStringDetails));
	}

	public void refreshFilter(String newCandidateIDStr, String newCandidateNameStr) {
		setCandidateIDPredicate(new Predicate<CitizenModel>() {
			@Override
			public boolean test(CitizenModel citizen) {
				String citizenIDStr = "" + citizen.getNumericID();

				// filter text is empty, display all citizens
				if ((newCandidateIDStr == null) || (newCandidateIDStr.isEmpty()))
					return true;

				return citizenIDStr.contains(newCandidateIDStr);
			}
		});
		setCandidateNamePredicate(new Predicate<CitizenModel>() {
			@Override
			public boolean test(CitizenModel citizen) {
				// filter text is empty, display all citizens
				if ((newCandidateNameStr == null) || (newCandidateNameStr.isEmpty()))
					return true;

				return citizen.getTextualFullName().toLowerCase().contains(newCandidateNameStr.toLowerCase());
			}
		});
		setUnionPredicate(new Predicate<CitizenModel>() {
			@Override
			public boolean test(CitizenModel citizen) {
				return candidateIDPredicate.test(citizen) && candidateNamePredicate.test(citizen);
			}
		});

		filteredCitizens.setPredicate(unionPredicate);
	}

}
