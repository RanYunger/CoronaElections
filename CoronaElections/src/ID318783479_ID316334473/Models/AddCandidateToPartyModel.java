package ID318783479_ID316334473.Models;

import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Group;

public class AddCandidateToPartyModel {
	// Constants

	// Fields
	private ObservableList<CitizenModel> citizens;
	private Predicate<CitizenModel> unionPredicate, candidateIDPredicate, candidateNamePredicate;
	private FilteredList<CitizenModel> filteredCitizens;

	// Properties (Getters and Setters)
	public ObservableList<CitizenModel> getCitizens() {
		return citizens;
	}

	public void setCitizens(ArrayList<CitizenModel> citizens) {
		this.citizens = FXCollections.observableArrayList(citizens);
	}

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

	// Constructors
	public AddCandidateToPartyModel(ArrayList<CitizenModel> citizens) {
		setCitizens(citizens);
		setUnionPredicate(p -> true);
		setCandidateIDPredicate(p -> true);
		setCandidateIDPredicate(p -> true);
		setFilteredCitizens(new FilteredList<CitizenModel>(getCitizens(), unionPredicate));
	}

	// Methods
	public void show(Group root) {
		// TODO: COMPLETE
	}

	public void refreshFilter(String newCandidateIDStr, String newCandidateNameStr) {
		setCandidateIDPredicate(new Predicate<CitizenModel>() {
			@Override
			public boolean test(CitizenModel citizen) {
				String citizenIDStr = "" + citizen.getID();

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

				return citizen.getFullName().toLowerCase().contains(newCandidateNameStr.toLowerCase());
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
