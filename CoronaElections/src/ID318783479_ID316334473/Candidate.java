package ID318783479_ID316334473;

import java.time.YearMonth;

public class Candidate extends Citizen {
	// Constants
	
	// Fields
	private String associatedPartyName;
	private int rank;
	
	// Properties (Getters and Setters)
	public String getAssociatedPartyName() {
		return associatedPartyName;
	}
	public void setAssociatedPartyName(String associatedPartyName) {
		this.associatedPartyName = associatedPartyName;
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
	public Candidate(int ID, String fullName, YearMonth dateOfBirth, int associatedBallotID, boolean isIsolated, boolean isWearingSuit, int rank) {
		super(ID, fullName, dateOfBirth, associatedBallotID, isIsolated, isWearingSuit);
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
		if (associatedPartyName == null) {
			if (other.associatedPartyName != null)
				return false;
		} else if (!associatedPartyName.equals(other.associatedPartyName))
				return false;		
		if (rank != other.rank)
			return false;
		
		return true;
	}	
	@Override
	public String toString() {
		return String.format("Candidate [Full Name: %s | Party: %s (ranked #%d)]", fullName, associatedPartyName, rank);
	}
}
