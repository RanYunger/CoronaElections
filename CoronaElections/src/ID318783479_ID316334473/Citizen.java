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
	protected boolean isWearingSuit;

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
		if (this.associatedBallot != null)
			this.associatedBallot.addVoter(this);
	}

	public boolean isIsolated() {
		return isIsolated;
	}

	private void setIsIsolated(boolean isIsolated) {
		this.isIsolated = isIsolated;
	}

	public boolean iswearingSuit() {
		return isWearingSuit;
	}

	private void setIswearingSuit(boolean iswearingSuit) {
		this.isWearingSuit = iswearingSuit;
	}

	// Constructors
	public Citizen(int ID, String fullName, int yearOfBirth, Ballot associatedBallot, boolean isIsolated,
			boolean isWearingSuit) {
		setID(ID);
		setFullName(fullName);
		setYearOfBirth(yearOfBirth);
		setIsIsolated(isIsolated);
		setIswearingSuit(isWearingSuit);
		setAssociatedBallot(associatedBallot);
	}

	// Methods
	public int vote(Scanner scan, PartyRegistry candidateParties) {
		return TUI.vote(scan, candidateParties, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Citizen))
			return false;
		Citizen other = (Citizen) obj;
		return ID == other.ID;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int age = Year.now().getValue() - yearOfBirth;

		sb.append((VOTING_AGE <= age) && (age <= SOLDIER_AGE) ? "Soldier, " : "");
		sb.append(isIsolated ? "Isolated, " : "Not isolated, ");
		sb.append(isWearingSuit ? "Wearing suit" : "Not wearing suit");

		return String.format("Citizen [ID:%d | Full name: %s | Born: %s | Status: %s]", ID, fullName, yearOfBirth,
				sb.toString());
	}
}
