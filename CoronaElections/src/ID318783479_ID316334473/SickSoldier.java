package ID318783479_ID316334473;

public class SickSoldier extends Soldier {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public SickSoldier(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			Ballot<? extends Citizen> associatedBallot, boolean isIsolated, boolean isWearingSuit,
			boolean isCarryingWeapon) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit, isCarryingWeapon);
	}

	// Methods
	@Override
	public String toString() {
		return super.toString();
	}
}
