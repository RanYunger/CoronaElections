package ID318783479_ID316334473;

public class Citizen {
	// Constants
	public static final int VOTER_AGE = 18;
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
		if (associatedBallot == null) {
			if (other.associatedBallot != null)
				return false;
		} else if (!associatedBallot.equals(other.associatedBallot))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (isIsolated != other.isIsolated)
			return false;
		if (isWearingSuit != other.isWearingSuit)
			return false;
		if (yearOfBirth != other.yearOfBirth)
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("Citizen [ID:%d | Full name: %s | Born: %s | Status: ", ID, fullName, yearOfBirth));
		sb.append(isIsolated ? "Isolated, " : "Not isolated, ");
		sb.append(isWearingSuit ? "Wearing suit]" : "Not wearing suit]");
		
		return sb.toString();
	}
}
