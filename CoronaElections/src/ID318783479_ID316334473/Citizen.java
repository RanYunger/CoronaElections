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
	private void setID(int iD) {
		ID = iD;
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
	public Citizen(int ID) {
		this(ID, "<UNKNOWN>", Year.now().getValue(), null, false, false);
	}
	public Citizen(int ID, String fullName, int yearOfBirth, Ballot associatedBallot, boolean isIsolated, boolean isWearingSuit) {
		setID(ID);
		setFullName(fullName);
		setYearOfBirth(yearOfBirth);
		setAssociatedBallot(associatedBallot);
		setIsIsolated(isIsolated);
		setIswearingSuit(isWearingSuit);
	}
	
	// Methods
	public int vote(Scanner scan, Party[] candidateParties) {
		System.out.println(String.format("Greetings, %d. Do you want to vote? (Y/N)", fullName));
		if(scan.nextLine().toUpperCase() == "Y") {
			for (int i = 0; i < candidateParties.length; i++)
				System.out.println(String.format("%d = %s", i + 1, candidateParties[i].getName()));
			System.out.println("Enter the code for your chosen party:");
			return scan.nextInt();
		}
		
		return -1;
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
		String desctiptionStr = "";
		int age = Year.now().getValue() - yearOfBirth;
		
		desctiptionStr += (age >= VOTING_AGE) && (age <= SOLDIER_AGE) ? "Soldier," : "";
		desctiptionStr += isIsolated ? "Isolated, " : "Not isolated, ";
		desctiptionStr += iswearingSuit ? "Wearing suit" : "Not wearing suit";
		
		return String.format("Citizen [ID:%d | Full name: %s | Born: %d | Status: %s | Associated Ballot ID: %d]", ID,
				fullName, yearOfBirth, desctiptionStr, associatedBallot.getID());
	}
}
