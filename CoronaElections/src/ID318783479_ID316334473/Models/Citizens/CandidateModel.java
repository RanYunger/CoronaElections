package ID318783479_ID316334473.Models.Citizens;

import java.util.Objects;

import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class CandidateModel extends CitizenModel {
	// Fields
	private PartyModel associatedParty;
	private ObservableValue<String> rank;

	// Properties (Getters and Setters)
	public PartyModel getAssociatedParty() {
		return associatedParty;
	}

	public boolean joinParty(PartyModel associatedParty) {
		return setParty(associatedParty, -1);
	}

	private boolean setParty(PartyModel associatedParty, int rank) {
		if ((associatedParty == null) || (this.associatedParty != null))
			return false;

		this.associatedParty = associatedParty;
		setRank(associatedParty.getTextualLastRank());

		return true;
	}

	public ObservableValue<String> getObservableRank() {
		return rank;
	}

	public int getNumericRank() {
		return Integer.parseInt(rank.getValue());
	}

	private void setRank(String rank) {
		this.rank = new SimpleStringProperty(rank);
	}

	// Constructors
	public CandidateModel(int ID, String fullName, int yearOfBirth, int daysOfSickness, BallotModel associatedBallot,
			boolean isIsolated, boolean isWearingSuit) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof CandidateModel))
			return false;
		CandidateModel other = (CandidateModel) obj;
		return Objects.equals(associatedParty, other.associatedParty);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		int citizenIndex = sb.indexOf("Citizen");

		sb.replace(citizenIndex, citizenIndex + 7, "Candidate");
		sb.deleteCharAt(sb.length() - 1); // removes "]" at the end of the string

		if (associatedParty == null)
			sb.append(" | Is not associated with party yet");
		else
			sb.append(String.format(" | Party: %s]", associatedParty.getTextualName()));

		return sb.toString();
	}
}
