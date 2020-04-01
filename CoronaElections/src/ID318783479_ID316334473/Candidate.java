package ID318783479_ID316334473;

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

	public void setRank(int rank) {
		this.rank = rank;
	}

	// Constructors
	public Candidate(int ID, String fullName, int yearOfBirth, Ballot associatedBallot, boolean isIsolated,
			boolean isWearingSuit, Party associatedParty, int rank) {
		super(ID, fullName, yearOfBirth, associatedBallot, isIsolated, isWearingSuit);
		setRank(rank);
		setAssociatedParty(associatedParty);
	}

	// Methods
	@Override
	public boolean equals(Object obj) {
		Candidate other;

		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;

		other = (Candidate) obj;
		if (associatedParty == null) {
			if (other.associatedParty != null)
				return false;
		} else if (!associatedParty.equals(other.associatedParty))
			return false;
		if (rank != other.rank)
			return false;

		return true;
	}

	@Override
	public String toString() {
		String citizenStr = super.toString();
		
		citizenStr = citizenStr.replaceFirst("Citizen ", "Candidate ");
		citizenStr = citizenStr.replace("]", "");
		
		return String.format("%s | Party: %s (ranked #%d)]", citizenStr, associatedParty.getName(), rank);
	}
}
