package ID318783479_ID316334473.Models;

import java.util.Objects;

public class CandidateModel extends CitizenModel {
	// Fields
	private PartyModel associatedParty;

	// Properties (Getters and Setters)
	public PartyModel getAssociatedParty() {
		return associatedParty;
	}

	public boolean joinParty(PartyModel associatedParty) {
		return joinParty(associatedParty, -1);
	}

	public boolean joinParty(PartyModel associatedParty, int rank) {
		if ((associatedParty == null) || (this.associatedParty != null))
			return false;

		this.associatedParty = associatedParty;

		return true;
	}

	// Constructors
	public CandidateModel(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			BallotModel<? extends CitizenModel> associatedBallot, boolean isIsolated, boolean isWearingSuit) {
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
		int citizenIndex = sb.indexOf("CitizenModel");

		sb.replace(citizenIndex, citizenIndex + 7, "CandidateModel");
		sb.deleteCharAt(sb.length() - 1); // removes "]" at the end of the string

		if (associatedParty == null)
			sb.append(" | Is not associated with party yet");
		else
			sb.append(String.format(" | PartyModel: %s]", associatedParty.getName()));

		return sb.toString();
	}
}
