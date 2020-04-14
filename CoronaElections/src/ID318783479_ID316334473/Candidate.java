package ID318783479_ID316334473;

import java.util.Objects;

public class Candidate extends Citizen {
	// Constants

	// Fields
	private Party associatedParty;
	private int rank;

	// Properties (Getters and Setters)
	public Party getAssociatedParty() {
		return associatedParty;
	}

	public void setAssociatedParty(Party associatedParty) {
		this.associatedParty = associatedParty;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) throws Exception {
		if (rank < 0)
			throw new Exception("Candidate's rank must be a positive number.");
		this.rank = rank;
	}

	// Constructors
	public Candidate(int ID, String fullName, int yearOfBirth, Ballot associatedBallot, boolean isIsolated,
			boolean isWearingSuit, Party associatedParty, int rank) {
		super(ID, fullName, yearOfBirth, associatedBallot, isIsolated, isWearingSuit);
		try {
			setRank(rank);
			setAssociatedParty(associatedParty);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
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
		return Objects.equals(associatedParty, other.associatedParty) && rank == other.rank;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String citizenStr = super.toString();

		citizenStr = citizenStr.replaceFirst("Citizen", "Candidate");
		citizenStr = citizenStr.replace("]", "");

		sb.append(citizenStr);
		sb.append(String.format(" | Party: %s (ranked #%d)]", associatedParty.getName(), rank));

		return sb.toString();
	}
}
