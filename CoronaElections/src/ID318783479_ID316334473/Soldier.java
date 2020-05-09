package ID318783479_ID316334473;

public class Soldier extends Citizen {
	// Constants

	// Fields
	protected boolean isCarryingWeapon;

	// Properties (Getters and Setters)
	public boolean isCarryingWeapon() {
		return isCarryingWeapon;
	}

	public void setIsCarryingWeapon(boolean isCarryingWeapon) {
		this.isCarryingWeapon = isCarryingWeapon;
	}

	// Constructors
	public Soldier(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			Ballot<? extends Citizen> associatedBallot, boolean isIsolated, boolean isWearingSuit,
			boolean isCarryingWeapon) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
		setIsCarryingWeapon(isCarryingWeapon);
	}

	// Methods
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		boolean isSoldier = (age >= VOTER_AGE) && (age <= SOLDIER_AGE);
		int citizenIndex = sb.indexOf("Citizen");

		sb.replace(citizenIndex, citizenIndex + 7, "Soldier");
		sb.deleteCharAt(sb.length() - 1); // removes "]" at the end of the string
		
		if (isSoldier)
			sb.append(isCarryingWeapon ? ", Soldier (Carrying weapon)] " : ", Soldier (Not carrying weapon)] ");

		return sb.toString();
	}
}
