package ID318783479_ID316334473;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Ballots.CoronaBallotModel;
import ID318783479_ID316334473.Models.Ballots.CoronaMilitaryBallotModel;
import ID318783479_ID316334473.Models.Ballots.MilitaryBallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Models.Citizens.SickCitizenModel;
import ID318783479_ID316334473.Models.Citizens.SickSoldierModel;
import ID318783479_ID316334473.Models.Citizens.SoldierModel;

// This class contains methods which aren't necessarily related to UI
public class SearchHandler {
	private static <T, U> T binarySearch(List<T> collection, U key) {
		return binarySearch(collection, key, 0, collection.size() - 1);
	}

	private static <T, U> T binarySearch(List<T> array, U key, int start, int end) {
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

	public static BallotModel getBallotByID(int ballotID, List<? extends BallotModel> list) {
		Collections.sort(list);
		return binarySearch(list, ballotID);
	}

	public static CitizenModel getCitizenByID(int citizenID, List<? extends CitizenModel> list) {
		Collections.sort(list);
		return binarySearch(list, citizenID);
	}

	public static PartyModel getPartyByName(String partyName, List<PartyModel> list) {
		Collections.sort(list);
		return binarySearch(list, partyName);
	}
}
