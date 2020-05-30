package ID318783479_ID316334473.Models;

public class SickSoldierModel extends SoldierModel {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public SickSoldierModel(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			BallotModel<? extends CitizenModel> associatedBallot, boolean isIsolated, boolean isWearingSuit,
			boolean isCarryingWeapon) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit, isCarryingWeapon);
	}

	// Methods
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		int soldierIndex = sb.indexOf("SoldierModel");

		sb.replace(soldierIndex, soldierIndex + 7, "Sick SoldierModel");

		return sb.toString();
	}
}
