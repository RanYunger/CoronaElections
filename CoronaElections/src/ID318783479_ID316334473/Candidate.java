package ID318783479_ID316334473;

import java.time.LocalDate;

public class Candidate extends Citizen {
	// Constants
	
	// Fields
	private static int RANK_GENERATOR = 1; //breaks it all, but OK for now 
	
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
		setRank(RANK_GENERATOR++);
	}
	public Candidate(int ID, String fullName, LocalDate dateOfBirth, Ballot associatedBallot, boolean isIsolated, boolean isWearingSuit) {
		super(ID, fullName, dateOfBirth, associatedBallot, isIsolated, isWearingSuit);
		setRank(RANK_GENERATOR++);
	}
	
	// Methods
	@Override
	public boolean equals(Object obj) {
		Candidate other = null;
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		other = (Candidate) obj;
//		if (RANK_GENERATOR != other.RANK_GENERATOR)
//			return false;
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
