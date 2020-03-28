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
	public Candidate(int ID) {
		super(ID);
		setRank(-1);
	}
	public Candidate(int ID, String fullName, int yearOfBirth, Ballot associatedBallot, boolean isIsolated, boolean isWearingSuit, int rank) {
		super(ID, fullName, yearOfBirth, associatedBallot, isIsolated, isWearingSuit);
		setRank(rank);
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
		return String.format("Candidate [Full Name: %s | Party: %s (ranked #%d)]", fullName, associatedParty.getName(), rank);
	}
}
