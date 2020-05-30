package ID318783479_ID316334473.Models;

public class SoldierModel extends CitizenModel {
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
	public SoldierModel(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			BallotModel<? extends CitizenModel> associatedBallot, boolean isIsolated, boolean isWearingSuit,
			boolean isCarryingWeapon) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
		setIsCarryingWeapon(isCarryingWeapon);
	}

	// Methods
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		boolean isSoldier = (age >= VOTER_AGE) && (age <= SOLDIER_AGE);
		int citizenIndex = sb.indexOf("CitizenModel");

		sb.replace(citizenIndex, citizenIndex + 7, "SoldierModel");
		sb.deleteCharAt(sb.length() - 1); // removes "]" at the end of the string
		
		if (isSoldier)
			sb.append(isCarryingWeapon ? ", SoldierModel (Carrying weapon)] " : ", SoldierModel (Not carrying weapon)] ");

		return sb.toString();
	}
}
