package ID318783479_ID316334473;

import java.time.YearMonth;

public class Citizen implements Comparable<Citizen> {
	// Constants
	public static final int VOTER_AGE = 18;
	public static final int SOLDIER_AGE = 21;

	// Fields
	protected int ID;
	protected String fullName;
	protected int yearOfBirth, age;
	protected int daysOfSickness; // declared here, instead of declaring in each "Sick_" class ~Ran
	protected Ballot<? extends Citizen> associatedBallot;
	protected boolean isIsolated; // declared here, instead of declaring in each "Sick_" class ~Ran
	protected boolean isWearingSuit; // declared here, instead of declaring in each "Sick_" class ~Ran

	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}

	private void setID(int ID) throws Exception {
		if (String.valueOf(ID).length() != 9)
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
			throw new Exception("Time paradox prevented - I mean, come on");
		this.yearOfBirth = yearOfBirth;
	}

	public int getAge() {
		return age;
	}

	protected void setAge(int age) throws Exception {
		if (age <= 0)
			throw new Exception("Time paradox prevented - I mean, come on");
		this.age = age;
	}

	public int getDaysOfSickness() {
		return daysOfSickness;
	}

	protected void setDaysOfSickness(int daysOfSickness) throws Exception {
		if (daysOfSickness < 0)
			throw new Exception("Citizen can only have non-negative amount of sickness days.");
		if ((isIsolated) && (daysOfSickness < 1))
			throw new Exception("An Isolated Citizen must've been sick for at least 1 day.");
		this.daysOfSickness = daysOfSickness;
	}

	public Ballot<? extends Citizen> getAssociatedBallot() {
		return associatedBallot;
	}

	public void setAssociatedBallot(Ballot<? extends Citizen> associatedBallot) throws NullPointerException {
		this.associatedBallot = associatedBallot;
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
	public Citizen(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			Ballot<? extends Citizen> associatedBallot, boolean isIsolated, boolean isWearingSuit) {
		try {
			setID(ID);
			setFullName(fullName);
			setYearOfBirth(yearOfBirth);
			setDaysOfSickness(daysOfSickness);
			setIsIsolated(isIsolated);
			setIswearingSuit(isWearingSuit);
			setAssociatedBallot(associatedBallot);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void calculateAge(YearMonth votingDate) {
		try {
			setAge(votingDate.getYear() - yearOfBirth);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public int compareTo(Citizen other) {
		return Integer.compare(ID, other.ID);
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
		sb.append(isIsolated ? String.format("Isolated (%d Day(s) so far), ", daysOfSickness) : "Not isolated, ");
		sb.append(isWearingSuit ? "Wearing suit]" : "Not wearing suit]");

		return sb.toString();
	}
}