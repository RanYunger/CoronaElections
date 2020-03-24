package ID318783479_ID316334473;

import java.time.LocalDateTime;

public class Citizen {
	// Constants
	public static final int VOTING_AGE = 18;
	
	// Fields
	protected int ID;
	protected String fullName;
	protected LocalDateTime dateOfBirth;
	protected Ballot associatedBallot;
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
	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		int age = LocalDateTime.now().getYear() - dateOfBirth.getYear();
		
		this.dateOfBirth = dateOfBirth;
		
		setIsSoldier((age >= VOTING_AGE) && (age <= 21));
	}
	public Ballot getAssociatedBallot() {
		return associatedBallot;
	}
	public void setAssociatedBallot(Ballot associatedBallot) {
		this.associatedBallot = associatedBallot;
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
		this(ID, "<UNKNOWN>", LocalDateTime.now(), null, false, false);
	}
	public Citizen(int ID, String fullName, LocalDateTime dateOfBirth, Ballot associatedBallot, boolean isIsolated, boolean isWearingSuit) {
		setID(ID);
		setFullName(fullName);
		setDateOfBirth(dateOfBirth);
		setAssociatedBallot(associatedBallot);
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
		if (associatedBallot == null) {
			if (other.associatedBallot != null)
				return false;
		} else if (!associatedBallot.equals(other.associatedBallot))
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
		
		return true;
	}
	@Override
	public String toString() {
		// TODO: Add IsIsolated, IsWearingSuits, IsSoldier descriptions
		return String.format("Citizen [ID:%d | Full name: %s | DOB: %s | Associated Ballot: %d]",
				ID, fullName, dateOfBirth, associatedBallot.getID());
	}
}
