package ID318783479_ID316334473;

import java.time.LocalDate;
import java.util.ArrayList;

import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Ballots.CoronaBallotModel;
import ID318783479_ID316334473.Models.Ballots.CoronaMilitaryBallotModel;
import ID318783479_ID316334473.Models.Ballots.MilitaryBallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Models.Citizens.SickCitizenModel;
import ID318783479_ID316334473.Models.Citizens.SickSoldierModel;
import ID318783479_ID316334473.Models.Citizens.SoldierModel;
import javafx.collections.ObservableList;

// This class contains methods which aren't necessarily related to UI
public class TBN {
	// Constants
	public static final String VALID_CITIZEN_ID_PATTERN = "^[0-9]{9}$";
	public static final String VALID_CITIZEN_FULL_NAME_PATTERN = "^([A-Z][a-z]+ ?){2}$";
	public static final String VALID_PARTY_NAME_PATTERN = "^([A-Z][a-z]+ ?)+$";
	public static final String VALID_BALLOT_ADDRESS_PATTERN = "^([0-9]{2} ([A-Z][a-z]+ ?){1,3}, ([A-Z][a-z]+ ?){1,2})$";

	// Fields

	// Properties

	// Methods
	public static <T, U> T binarySearch(ArrayList<T> array, U key) {
		return binarySearch(array, key, 0, array.size() - 1);
	}

	private static <T, U> T binarySearch(ArrayList<T> array, U key, int start, int end) {
		if (start <= end) {
			int mid = (start + end) / 2;

			T element = array.get(mid);
			if (array.get(0) instanceof BallotModel) {
				int ballotID = ((BallotModel) array.get(mid)).getNumericID();
				if (ballotID == (int) key)
					return element;

				if (ballotID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof CitizenModel) {
				int citizenID = ((CitizenModel) array.get(mid)).getNumericID();
				if (citizenID == (int) key)
					return element;

				if (citizenID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof PartyModel) {
				String partyName = ((PartyModel) array.get(mid)).getTextualName();
				if (partyName.equals((String) key))
					return element;

				if (partyName.compareTo((String) key) > 0)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}
		}

		return null;
	}

	public static BallotModel createBallotByType(String type, String address, LocalDate electionDate) {
		if (type.contains("Military"))
			return new MilitaryBallotModel(address, electionDate);
		if (type.contains("Sick Citizens"))
			return new CoronaBallotModel(address, electionDate);
		if (type.contains("Sick Soldiers"))
			return new CoronaMilitaryBallotModel(address, electionDate);

		return new BallotModel(address, electionDate);
	}

	public static CitizenModel createCitizen(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			BallotModel associatedBallot, boolean isIsolated, boolean isWearingSuit, boolean isSoldier,
			boolean isCarryingWeapon) {
		if (isIsolated) {
			if (isSoldier) {
				return new SickSoldierModel(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated,
						isWearingSuit, isCarryingWeapon);
			} else
				return new SickCitizenModel(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated,
						isWearingSuit);
		} else {
			if (isSoldier) {
				return new SoldierModel(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated,
						isWearingSuit, isCarryingWeapon);
			} else
				return new CitizenModel(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated,
						isWearingSuit);
		}
	}

	public static BallotModel getBallotByID(int ballotID) {
		// TODO: FIX SO IT'LL USE BINARY SEARCH (WITHOUT THE ZERO BASED THING!)
		ObservableList<BallotModel> allBallots = UIHandler.getMainView().getAllBallots();

		for (int i = 0; i < allBallots.size(); i++)
			if (allBallots.get(i).getNumericID() == ballotID)
				return allBallots.get(i);

		return null;
	}

	public static CitizenModel getCitizenByID(int citizenID) {
		// TODO: FIX SO IT'LL USE BINARY SEARCH
		ObservableList<CitizenModel> allCitizens = UIHandler.getMainView().getAllCitizens();

		for (int i = 0; i < allCitizens.size(); i++)
			if (allCitizens.get(i).getNumericID() == citizenID)
				return allCitizens.get(i);

		return null;
	}

	public static PartyModel getPartyByName(String partyName) {
		// TODO: FIX SO IT'LL USE BINARY SEARCH
		ObservableList<PartyModel> allParties = UIHandler.getMainView().getAllParties();

		for (int i = 0; i < allParties.size(); i++) {
			if (allParties.get(i).getTextualName().equals(partyName))
				return allParties.get(i);
		}

		return null;
	}

}
