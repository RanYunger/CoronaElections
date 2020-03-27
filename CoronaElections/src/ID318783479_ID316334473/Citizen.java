package ID318783479_ID316334473;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class Citizen {
	// Constants
	public static final int VOTING_AGE = 18;
	public static final int SOLDIER_AGE = 21;

	// Fields
	protected int ID;
	protected String fullName;
	protected YearMonth dateOfBirth;
	protected int associatedBallotID; // can't have ballotID and not Ballot itself... fucks shit up a bit
	protected boolean isIsolated;
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
	public YearMonth getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(YearMonth dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public int getAssociatedBallotID() {
		return associatedBallotID;
	}
	public void setAssociatedBallotID(int associatedBallotID) {
		this.associatedBallotID = associatedBallotID;
	}
	public boolean setAssociatedBallotID(Ballot ballot) {
		if (associatedBallotID == ballot.getID())
			return true;

		if (associatedBallotID == -1) {
			if (ballot.addVoter(this)) {
				associatedBallotID = ballot.getID();
				
				return true;
			}
		}

		return false;
	}
	public boolean isIsolated() {
		return isIsolated;
	}
	public void setIsIsolated(boolean isIsolated) {
		this.isIsolated = isIsolated;
	}
	public boolean isIswearingSuit() {
		return iswearingSuit;
	}
	public void setIswearingSuit(boolean iswearingSuit) {
		this.iswearingSuit = iswearingSuit;
	}

	// Constructors
	public Citizen(int ID) {
		this(ID, "<UNKNOWN>", YearMonth.now(), -1, false, false);
	}
	public Citizen(int ID, String fullName, YearMonth dateOfBirth, int associatedBallotID, boolean isIsolated, boolean isWearingSuit) {
		setID(ID);
		setFullName(fullName);
		setDateOfBirth(dateOfBirth);
		setAssociatedBallotID(associatedBallotID);
		setIsIsolated(isIsolated);
		setIswearingSuit(isWearingSuit);
	}
	
	// Methods
	public int calculateAge(YearMonth votingDate) {
		YearMonth birthMonth = YearMonth.from(dateOfBirth);
		
		return (int) birthMonth.until(votingDate, ChronoUnit.YEARS);
	}
	@Override
	public boolean equals(Object obj) {
		Citizen other;
		
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
		if (iswearingSuit != other.iswearingSuit)
			return false;
		
		return true;
	}
	@Override
	public String toString() {
		String desctiptionStr = "";

//		desctiptionStr += calculateAge(votingDate) ? "Soldier," : "";
		desctiptionStr += isIsolated ? "Isolated, " : "Not isolated, ";
		desctiptionStr += iswearingSuit ? "Wearing suit" : "Not wearing suit";

		return String.format("Citizen [ID:%d | Full name: %s | DOB: %s | Status: %s | Associated Ballot ID: %d]", ID,
				fullName, dateOfBirth.toString(), desctiptionStr, associatedBallotID);
	}
}
