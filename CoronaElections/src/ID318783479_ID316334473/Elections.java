package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import ID318783479_ID316334473.Models.BallotModel;
import ID318783479_ID316334473.Models.CandidateModel;
import ID318783479_ID316334473.Models.CitizenModel;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.SetModel;
import ID318783479_ID316334473.Models.SickCandidateModel;
import ID318783479_ID316334473.Models.SickCitizenModel;
import ID318783479_ID316334473.Models.SickSoldierModel;
import ID318783479_ID316334473.Models.SoldierModel;

public class Elections {
	// Fields
	public static final Scanner scanner = new Scanner(System.in);
	public static final int INITIAL_CAPACITY = 10;
	// All the fields bellow moved out of the main method to allow free access to
	// them ~Ran
	public static YearMonth votingDate = null;
	public static SetModel<CitizenModel> voters = new SetModel<CitizenModel>();
	public static ArrayList<PartyModel> parties = new ArrayList<PartyModel>();
	public static ArrayList<BallotModel<CitizenModel>> citizenBallots = new ArrayList<BallotModel<CitizenModel>>();
	public static ArrayList<BallotModel<SoldierModel>> soldierBallots = new ArrayList<BallotModel<SoldierModel>>();
	public static ArrayList<BallotModel<SickCitizenModel>> sickCitizenBallots = new ArrayList<BallotModel<SickCitizenModel>>();
	public static ArrayList<BallotModel<SickCandidateModel>> sickCandidateBallots = new ArrayList<BallotModel<SickCandidateModel>>();
	public static ArrayList<BallotModel<SickSoldierModel>> sickSoldierBallots = new ArrayList<BallotModel<SickSoldierModel>>();

//	public static void main(String args[]) {
//		String finalResultsString = "";
//		int voterCapacity = INITIAL_CAPACITY;
//		int partyCapacity = INITIAL_CAPACITY;
//		int ballotCapacity = INITIAL_CAPACITY;
//		ArrayList<ArrayList<Integer>> resultsByBallot = new ArrayList<>();
//		boolean loop = true, electionsOccurred = false;
//		int selection;
//
//		System.out.println("Welcome to our voting system.");
//		System.out.println("Please enter the year and month of the elections, in that order:");
//		boolean validYearMonth = false;
//		while (!validYearMonth) {
//			try {
//				votingDate = YearMonth.of(scanner.nextInt(), scanner.nextInt());
//				validYearMonth = true;
//			} catch (DateTimeException e) {
//				System.out.println("Please enter a valid year-month pair");
//			}
//		}
//		scanner.nextLine();
//
//		init(votingDate, citizenBallots, soldierBallots, sickCitizenBallots, sickCandidateBallots, sickSoldierBallots,
//				parties, voters);
//
//		while (loop) {
//			selection = UIHandler.showMenu(electionsOccurred);
//			scanner.nextLine();
//			switch (selection) {
//			case 1: {
//				UIHandler.addNewBallot(votingDate);
//				ballotCapacity = ensureCapacity(citizenBallots, ballotCapacity);
//				ballotCapacity = ensureCapacity(soldierBallots, ballotCapacity);
//				ballotCapacity = ensureCapacity(sickCitizenBallots, ballotCapacity);
//				ballotCapacity = ensureCapacity(sickCandidateBallots, ballotCapacity);
//				ballotCapacity = ensureCapacity(sickSoldierBallots, ballotCapacity);
//			}
//				break;
//			case 2: {
//				UIHandler.addNewCitizen(voters, votingDate);
//				voterCapacity = ensureCapacity(voters.getElements(), voterCapacity);
//			}
//				break;
//			case 3:
//				UIHandler.addNewParty(parties);
//				partyCapacity = ensureCapacity(parties, partyCapacity);
//				break;
//			case 4:
//				UIHandler.addCandidateToAParty(voters, parties);
//				break;
//			case 5:
//				System.out.println(UIHandler.showBallotRegistry(getAllBallots()));
//				break;
//			case 6:
//				System.out.println(UIHandler.showVoterRegistry(voters, votingDate));
//				break;
//			case 7:
//				System.out.println(UIHandler.showPartyRegistry(parties));
//				break;
//			case 8:
//				UIHandler.runElections(resultsByBallot, parties);
//				electionsOccurred = true;
//				break;
//			case 9:
//				UIHandler.showElectionsResults(finalResultsString, resultsByBallot, parties);
//				break;
//			case 10:
//				UIHandler.showExitMessage();
//				loop = false;
//				break;
//			default:
//				System.out.println("Please choose a valid option.");
//			}
//		}
//	}

	// Methods
	private static void init(YearMonth votingDate, ArrayList<BallotModel<CitizenModel>> citizenBallots,
			ArrayList<BallotModel<SoldierModel>> soldierBallots, ArrayList<BallotModel<SickCitizenModel>> sickCitizenBallots,
			ArrayList<BallotModel<SickCandidateModel>> sickCandidatesBallots, ArrayList<BallotModel<SickSoldierModel>> sickSoldierBallots,
			ArrayList<PartyModel> parties, SetModel<CitizenModel> voters) {
		// Initiates 4 ballots
		citizenBallots.add(new BallotModel<CitizenModel>("CitizenModel", "21st Road Street, Town City", votingDate));
		soldierBallots.add(new BallotModel<SoldierModel>("SoldierModel", "Area 51, Nevada", votingDate));
		sickCitizenBallots.add(new BallotModel<SickCitizenModel>("Sick CitizenModel", votingDate));
		sickCandidateBallots.add(new BallotModel<SickCandidateModel>("Sick CandidateModel", votingDate));

		// Initiates 4 parties
		parties.add(new PartyModel("Halikud", PartyModel.PartyAssociation.Right, LocalDate.of(1973, 9, 13)));
		parties.add(new PartyModel("Blue and White", PartyModel.PartyAssociation.Center, LocalDate.of(2019, 2, 21)));
		parties.add(new PartyModel("Israel is Our Home", PartyModel.PartyAssociation.Center, LocalDate.of(1999, 3, 29)));
		parties.add(new PartyModel("Israeli Labor PartyModel", PartyModel.PartyAssociation.Left, LocalDate.of(1968, 1, 21)));

		// Initiates 5 citizen
		voters.add(new CitizenModel(123456789, "Charles Foster Kane", 1941, 0, citizenBallots.get(0), false, false));
		voters.add(new CitizenModel(234567890, "Donald John Trump", 1946, 1, citizenBallots.get(0), true, true));
		voters.add(new CitizenModel(345678901, "Tonny Stark", 1970, 0, citizenBallots.get(0), false, false));
		voters.add(new SickCitizenModel(456789012, "Steve Rogers", 1918, 1, sickCitizenBallots.get(0), true, true));
		voters.add(new SoldierModel(567890123, "Childish Gambino", 2001, 1, soldierBallots.get(0), false, false, true));

		// Initiates 8 candidates (2 per party)
		voters.add(new CandidateModel(678901234, "Benjamin Netanyahu", 1949, 50, citizenBallots.get(0), true, false));
		voters.add(new SickCandidateModel(789012345, "Miri Regev", 1965, 38, sickCandidatesBallots.get(0), true, false));
		voters.add(new SickCandidateModel(890123456, "Benny Gantz", 1959, 40, sickCandidatesBallots.get(0), true, true));
		voters.add(new SickCandidateModel(901234567, "Yair Lapid", 1963, 1, sickCandidatesBallots.get(0), true, true));
		voters.add(
				new SickCandidateModel(901234568, "Avigdor Lieberman", 1958, 1, sickCandidatesBallots.get(0), true, true));
		voters.add(new CandidateModel(901234566, "Oded Forer", 1977, 1, citizenBallots.get(0), false, true));
		voters.add(new CandidateModel(901234569, "Tamar Zandberg", 1976, 1, citizenBallots.get(0), false, false));
		voters.add(new CandidateModel(901234565, "Nitzan Horowitz", 1965, 1, citizenBallots.get(0), false, false));

		// Associates candidates to their parties
		getPartyByName(parties, "Halikud").addCandidate((CandidateModel) getCitizenByID(voters, 678901234));
		getPartyByName(parties, "Halikud").addCandidate((CandidateModel) getCitizenByID(voters, 789012345));
		getPartyByName(parties, "Blue and White").addCandidate((CandidateModel) getCitizenByID(voters, 890123456));
		getPartyByName(parties, "Blue and White").addCandidate((CandidateModel) getCitizenByID(voters, 901234567));
		getPartyByName(parties, "Israel is Our Home").addCandidate((CandidateModel) getCitizenByID(voters, 901234568));
		getPartyByName(parties, "Israel is Our Home").addCandidate((CandidateModel) getCitizenByID(voters, 901234566));
		getPartyByName(parties, "Israeli Labor PartyModel").addCandidate((CandidateModel) getCitizenByID(voters, 901234569));
		getPartyByName(parties, "Israeli Labor PartyModel").addCandidate((CandidateModel) getCitizenByID(voters, 901234565));
	}

	public static ArrayList<BallotModel<? extends CitizenModel>> getAllBallots() {
		ArrayList<BallotModel<? extends CitizenModel>> allBallots = new ArrayList<BallotModel<? extends CitizenModel>>();

		allBallots.addAll(citizenBallots);
		allBallots.addAll(soldierBallots);
		allBallots.addAll(sickCitizenBallots);
		allBallots.addAll(sickCandidateBallots);
		allBallots.addAll(sickSoldierBallots);

		return allBallots;
	}

	public static CitizenModel getCitizenByID(SetModel<CitizenModel> voters, int citizenID) {
		ArrayList<CitizenModel> sortedVoters = (ArrayList<CitizenModel>) voters.getElements().clone();
		Collections.sort(sortedVoters);
		return binarySearch(sortedVoters, citizenID);
	}

	public static PartyModel getPartyByName(ArrayList<PartyModel> parties, String partyName) {
		ArrayList<PartyModel> sortedParties = (ArrayList<PartyModel>) parties.clone();
		Collections.sort(sortedParties);
		return binarySearch(sortedParties, partyName);
	}

	// Binary Search
	public static BallotModel<? extends CitizenModel> getBallotByID(ArrayList<BallotModel<? extends CitizenModel>> ballots, int ballotID) {
		return binarySearch(ballots, ballotID);
	}

	public static boolean updateCitizenToCandidate(SetModel<CitizenModel> voters, CitizenModel citizen) {
		int index = voters.indexOf(getCitizenByID(voters, citizen.getID()));

		if (index != -1) {
			if (citizen.getClass() == CitizenModel.class) // "morphs" the CitizenModel into a CandidateModel
				voters.set(index,
						citizen.isIsolated()
								? new SickCandidateModel(citizen.getID(), citizen.getFullName(), citizen.getYearOfBirth(),
										citizen.getDaysOfSickness(), citizen.getAssociatedBallot(),
										citizen.isIsolated(), citizen.iswearingSuit())
								: new CandidateModel(citizen.getID(), citizen.getFullName(), citizen.getYearOfBirth(),
										citizen.getDaysOfSickness(), citizen.getAssociatedBallot(),
										citizen.isIsolated(), citizen.iswearingSuit()));
			return true;
		}
		return false;
	}

	private static <T, U> T binarySearch(ArrayList<T> array, U key) {
		return binarySearch(array, key, 0, array.size() - 1);
	}

	private static <T, U> T binarySearch(ArrayList<T> array, U key, int start, int end) {
		if (start <= end) {
			int mid = (start + end) / 2;

			T element = array.get(mid);
			if (array.get(0) instanceof BallotModel) {
				int ballotID = ((BallotModel<? extends CitizenModel>) array.get(mid)).getID();
				if (ballotID == (int) key)
					return element;

				if (ballotID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof CitizenModel) {
				int citizenID = ((CitizenModel) array.get(mid)).getID();
				if (citizenID == (int) key)
					return element;

				if (citizenID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof PartyModel) {
				String partyName = ((PartyModel) array.get(mid)).getName();
				if (partyName.equals((String) key))
					return element;

				if (partyName.compareTo((String) key) > 0)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}
		}
		return null;
	}

	private static int ensureCapacity(ArrayList<?> array, int capacity) {
		if (array.size() == capacity) {
			capacity *= 2;
			array.ensureCapacity(capacity);
		}

		return capacity;
	}
}