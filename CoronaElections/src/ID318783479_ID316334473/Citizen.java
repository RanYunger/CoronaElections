package ID318783479_ID316334473;

import java.time.Year;
import java.util.Scanner;

public class Citizen {
	// Constants
	public static final int VOTING_AGE = 18;
	public static final int SOLDIER_AGE = 21;

	// Fields
	protected int ID;
	protected String fullName;
	protected int yearOfBirth;
	protected Ballot associatedBallot;
	protected boolean isIsolated;
	protected boolean iswearingSuit;

	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}

	private void setID(int ID) {
		this.ID = ID;
	}

	public String getFullName() {
		return fullName;
	}

	private void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	private void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public Ballot getAssociatedBallot() {
		return associatedBallot;
	}

	public void setAssociatedBallot(Ballot associatedBallot) {
		this.associatedBallot = associatedBallot;
		if (associatedBallot != null && associatedBallot.getCitizenByID(ID) == null)
			associatedBallot.addVoter(this);
	}

	public boolean isIsolated() {
		return isIsolated;
	}

	private void setIsIsolated(boolean isIsolated) {
		this.isIsolated = isIsolated;
	}

	public boolean isIswearingSuit() {
		return iswearingSuit;
	}

	private void setIswearingSuit(boolean iswearingSuit) {
		this.iswearingSuit = iswearingSuit;
	}

	// Constructors
	public Citizen(int ID, String fullName, int yearOfBirth, Ballot associatedBallot, boolean isIsolated,
			boolean isWearingSuit) {
		setID(ID);
		setFullName(fullName);
		setYearOfBirth(yearOfBirth);
		setAssociatedBallot(associatedBallot);
		setIsIsolated(isIsolated);
		setIswearingSuit(isWearingSuit);
	}

	// Methods
	public int vote(Scanner scan, PartyRegistry candidateParties) {
		return TUI.vote(scan, candidateParties, this);
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
		if (associatedBallot != other.associatedBallot)
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
		if (yearOfBirth != other.yearOfBirth)
			return false;

		return true;
	}

	@Override
	public String toString() {
		StringBuilder desctiption = new StringBuilder();
		int age = Year.now().getValue() - yearOfBirth;

		desctiption.append((VOTING_AGE <= age) && (age <= SOLDIER_AGE) ? "Soldier, " : "");
		desctiption.append(isIsolated ? "Isolated, " : "Not isolated, ");
		desctiption.append(iswearingSuit ? "Wearing suit" : "Not wearing suit");
		int ballotID = (associatedBallot != null) ? associatedBallot.getID() : -1;
		return String.format(
				getClass().getSimpleName()
						+ " | ID:%d | Full name: %s | Born: %d | Status: %s | Associated Ballot ID: %d",
				ID, fullName, yearOfBirth, desctiption, ballotID);
	}
}
