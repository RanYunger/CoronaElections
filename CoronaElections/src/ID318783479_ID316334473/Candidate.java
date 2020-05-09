package ID318783479_ID316334473;

import java.util.Objects;

public class Candidate extends Citizen {
	// Fields
	private Party associatedParty;

	// Properties (Getters and Setters)
	public Party getAssociatedParty() {
		return associatedParty;
	}

	public boolean joinParty(Party associatedParty) {
		return joinParty(associatedParty, -1);
	}

	public boolean joinParty(Party associatedParty, int rank) {
		if ((associatedParty == null) || (this.associatedParty != null))
			return false;

		this.associatedParty = associatedParty;

		return true;
	}

	// Constructors
	public Candidate(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			Ballot<? extends Citizen> associatedBallot, boolean isIsolated, boolean isWearingSuit) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Candidate))
			return false;
		Candidate other = (Candidate) obj;
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
			sb.append(String.format(" | Party: %s]", associatedParty.getName()));

		return sb.toString();
	}
}
