package ID318783479_ID316334473;

import java.time.YearMonth;

public class Citizen {
	// Constants
	public static final int VOTER_AGE = 18;
	public static final int SOLDIER_AGE = 21;
	public static final int MIN_ID_VALUE = 100000000;
	public static final int MAX_ID_VALUE = 999999999;

	// Fields
	protected int ID;
	protected String fullName;
	protected int yearOfBirth;
	protected Ballot associatedBallot;
	protected boolean isSoldier;
	protected boolean isIsolated;
	protected boolean isWearingSuit;

	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}

	private void setID(int ID) throws Exception {
		if ((ID < MIN_ID_VALUE) || (ID > MAX_ID_VALUE))
			throw new Exception("Citizen's ID must contain exactly 9 digits.");
		this.ID = ID;
	}

	public String getFullName() {
		return fullName;
	}

	private void setFullName(String fullName) throws Exception {
		if (fullName.trim().length() == 0)
			throw new Exception("Citizen's name must contain at least 1 letter.");
		this.fullName = fullName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	private void setYearOfBirth(int yearOfBirth) throws Exception {
		if (yearOfBirth > YearMonth.now().getYear())
			throw new Exception("Time paradox prevented.");
		this.yearOfBirth = yearOfBirth;
	}

	public Ballot getAssociatedBallot() {
		return associatedBallot;
	}

	public void setAssociatedBallot(Ballot associatedBallot) throws Exception {
		if (associatedBallot == null)
			throw new Exception("Citizen must be associated to a ballot.");
		this.associatedBallot = associatedBallot;
		this.associatedBallot.addVoter(this);
	}

	public boolean isSoldier() {
		return isSoldier;
	}

	private void setIsSoldier(boolean isSoldier) {
		this.isSoldier = isSoldier;
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
		try {
			setID(ID);
			setFullName(fullName);
			setYearOfBirth(yearOfBirth);
			setIsIsolated(isIsolated);
			setIswearingSuit(isWearingSuit);
			setAssociatedBallot(associatedBallot);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void calculateAge(YearMonth votingDate) {
		int age = votingDate.getYear() - yearOfBirth;
		setIsSoldier((age >= VOTER_AGE) && (age <= SOLDIER_AGE));
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

		sb.append(String.format("Citizen [ID:%d | Full name: %s | Born: %s | Status: ", ID, fullName, yearOfBirth));
		sb.append(isSoldier ? "Soldier, " : "");
		sb.append(isIsolated ? "Isolated, " : "Not isolated, ");
		sb.append(isWearingSuit ? "Wearing suit]" : "Not wearing suit]");

		return sb.toString();
	}
}
