package ID318783479_ID316334473.Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import ID318783479_ID316334473.TBN;
import ID318783479_ID316334473.Models.Citizens.CandidateModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PartyModel implements Comparable<PartyModel> {
	// Constants
	public enum PartyAssociation {
		Left, Center, Right
	}

	// Fields
	private ObservableValue<String> name;
	private PartyAssociation wing;
	private LocalDate foundationDate;
	private ObservableList<CandidateModel> candidates;

	// Properties (Getters and Setters)
	public ObservableValue<String> getObservableName() {
		return name;
	}

	public String getTextualName() {
		return name.getValue();
	}

	private void setName(String name) throws Exception {
		if (name.isBlank())
			throw new Exception("PartyModel's name must contain at least 1 letter.");
		this.name = new SimpleStringProperty(name);
	}

	public PartyAssociation getWing() {
		return wing;
	}

	private void setWing(PartyAssociation wing) {
		this.wing = wing;
	}

	public ObservableValue<String> getObservableFoundationDate() {
		return new SimpleStringProperty(foundationDate.format(DateTimeFormatter.ofPattern("MM/dd/uuuu")));
	}

	public LocalDate getTemporalFoundationDate() {
		return foundationDate;
	}

	private void setFoundationDate(LocalDate foundationDate) throws Exception {
		if (foundationDate.compareTo(LocalDate.now()) > 0)
			throw new Exception("Time paradox prevented - creation time can't be in the future.");
		this.foundationDate = foundationDate;
	}

	public ObservableList<CandidateModel> getCandidates() {
		return candidates;
	}

	private void setCandidates(ArrayList<CandidateModel> candidates) {
		this.candidates = FXCollections.observableArrayList(candidates);
	}

	// Constructors
	public PartyModel() {
		this("<UNKNOWN>", PartyAssociation.Center, LocalDate.now());
	}

	public PartyModel(String name, PartyAssociation wing, LocalDate foundationDate) {
		try {
			setName(name);
			setWing(wing);
			setFoundationDate(foundationDate);
			setCandidates(new ArrayList<CandidateModel>());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	// Methods
	public CandidateModel getCandidateByID(int candidateID) {
		try {
			ArrayList<CandidateModel> candidatesArrayList = new ArrayList<CandidateModel>(candidates);

			Collections.sort(candidatesArrayList);

			return TBN.binarySearch(candidatesArrayList, candidateID);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public int getNumericLastRank() {
		return candidates.isEmpty() ? 1 : candidates.size();
	}

	public String getTextualLastRank() {
		return ordinal(getNumericLastRank());
	}

	public boolean addCandidate(CandidateModel candidate) {
		return addCandidate(candidate, candidates.size());
	}

	public boolean addCandidate(CandidateModel candidate, int rank) {
		int lastRank = getNumericLastRank();

		// TODO: FIX (something here makes the ranks start from 0
		if (getCandidateByID(candidate.getNumericID()) != null)
			return true;

		if ((rank > lastRank) || (rank == -1))
			candidates.add(candidate);
		else
			candidates.add(rank, candidate);

		candidate.joinParty(this);

		return true;
	}

	private static String ordinal(int rank) {
		switch (rank % 10) {
		case 1:
			if (rank % 100 != 11)
				return (rank + "st");
			break;
		case 2:
			if (rank % 100 != 12)
				return (rank + "nd");
			break;
		case 3:
			if (rank % 100 != 13)
				return (rank + "rd");
			break;
		}

		return rank + "th";
	}

	@Override
	public int compareTo(PartyModel other) {
		return getTextualName().compareToIgnoreCase(other.getTextualName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PartyModel))
			return false;
		PartyModel other = (PartyModel) obj;
		return getTextualName().equalsIgnoreCase(other.getTextualName()); // Two parties can't have the same name
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("PartyModel [Name: %s | Association: %s | Foundation: %s]\n", name, wing.toString(),
				foundationDate.toString()));
		sb.append("\tCandidates:");
		if (candidates.size() == 0)
			sb.append("\n\t\tNothing to see here...");
		else {
			sb.append(String.format("\n\t\tParty leader: %s", candidates.get(0).toString()));
			for (int i = 1; i < candidates.size(); i++)
				sb.append(String.format("\n\t\t%s runner: %s", ordinal(i), candidates.get(i).toString()));
		}
		return sb.toString();
	}
}
