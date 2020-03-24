package ID318783479_ID316334473;

import java.time.LocalDate;

public class Citizen {
	// Constants
	public static final int VOTING_AGE = 18;
	public static final int SOLDIER_AGE = 21;
	
	// Fields
	protected int ID;
	protected String fullName;
	protected LocalDate dateOfBirth;
	protected int associatedBallotID;
	protected boolean canVote;
	protected boolean isIsolated;
	protected boolean isSoldier;
	protected boolean iswearingSuit;
	
	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		int age = LocalDate.now().getYear() - dateOfBirth.getYear();
		
		this.dateOfBirth = dateOfBirth;
		
		setIsSoldier((age >= VOTING_AGE) && (age <= SOLDIER_AGE));
	}
	public int getAssociatedBallotID() {
		return associatedBallotID;
	}
	public void setAssociatedBallotID(int associatedBallotID) {
		this.associatedBallotID = associatedBallotID;
	}
	public boolean isCanVote() {
		return canVote;
	}
	public void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}
	public boolean isIsolated() {
		return isIsolated;
	}
	public void setIsIsolated(boolean isIsolated) {
		this.isIsolated = isIsolated;
	}
	public boolean isSoldier() {
		return isSoldier;
	}
	public void setIsSoldier(boolean isSoldier) {
		this.isSoldier = isSoldier;
	}
	public boolean isIswearingSuit() {
		return iswearingSuit;
	}
	public void setIswearingSuit(boolean iswearingSuit) {
		this.iswearingSuit = iswearingSuit;
	}
	
	// Constructors
	public Citizen(int ID) {
		this(ID, "<UNKNOWN>", LocalDate.now(), -1, false, false);
	}

	public Citizen(int ID, String fullName, LocalDate dateOfBirth, int associatedBallotID, boolean isIsolated,
			boolean isWearingSuit) {
		setID(ID);
		setFullName(fullName);
		setDateOfBirth(dateOfBirth);
		setAssociatedBallotID(associatedBallotID);
		setCanVote(true);
		setIsIsolated(isIsolated);
		setIswearingSuit(isWearingSuit);
	}
	
	// Methods
	@Override
	public boolean equals(Object obj) {
		Citizen other = null;
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		other = (Citizen) obj;
		if (ID != other.ID)
			return false;
		if (associatedBallotID != other.associatedBallotID)
			return false;
		if (canVote != other.canVote)
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (isIsolated != other.isIsolated)
			return false;
		if (isSoldier != other.isSoldier)
			return false;
		if (iswearingSuit != other.iswearingSuit)
			return false;
		
		return true;
	}
	@Override
	public String toString() {
		String desctiptionStr = "";

		desctiptionStr += isSoldier ? "Soldier," : "";
		desctiptionStr += isIsolated ? "Isolated, " : "Not isolated, ";
		desctiptionStr += iswearingSuit ? "Wearing suit" : "Not wearing suit";
		
		return String.format("Citizen [ID:%d | Full name: %s | DOB: %s | Status: %s | Associated Ballot ID: %d]",
				ID, fullName, dateOfBirth.toString(), desctiptionStr, associatedBallotID);
	}
}
