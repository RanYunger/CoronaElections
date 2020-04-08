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

	@Override
	public boolean equals(Object obj) {
		Candidate other;
		
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Candidate))
			return false;
		
		other = (Candidate) obj;
		
		return (Objects.equals(associatedParty, other.associatedParty)) && (rank == other.rank);
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
