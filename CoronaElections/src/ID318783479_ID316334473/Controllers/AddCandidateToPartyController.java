package ID318783479_ID316334473.Controllers;

import java.util.function.Predicate;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Views.AddCandidateToPartyView;
import ID318783479_ID316334473.Views.CitizensTabView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
		refreshFilter("", "");

		ChangeListener<String> candidateIDTextFieldChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldCandidateIDText,
					String newCandidateIDText) {
				refreshFilter(newCandidateIDText, addCandidateView.getCandidateNameTextField().getText());
			}
		};
		ChangeListener<String> candidateNameTextFieldChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldCandidateNameText,
					String newCandidateNameText) {
				refreshFilter(addCandidateView.getCandidateIDTextField().getText(), newCandidateNameText);
			}
		};
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CitizenModel selectedCitizen = addView.getCitizensTableView().getSelectionModel().getSelectedItem();

				// Validations
				if ((filteredCitizens.size() > 1) && (selectedCitizen == null)) {
					UIHandler.showError("Choose a citizen to add to the party.");
					return;
				}

				selectedCitizen = filteredCitizens.size() == 1 ? filteredCitizens.get(0) : selectedCitizen;
				selectedParty.addCandidate(citizensTabView.morphCitizenToCandidate(selectedCitizen));
				UIHandler.showSuccess("A new candidate was added successfully!");
				addCandidateView.close();
			}
		};

		addView.getCandidateIDTextField().textProperty().addListener(candidateIDTextFieldChangeListener);
		addView.getCandidateNameTextField().textProperty().addListener(candidateNameTextFieldChangeListener);
		addView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}

	// Methods
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
		addView.getCitizensTableView().setItems(filteredCitizens);
	}
}
