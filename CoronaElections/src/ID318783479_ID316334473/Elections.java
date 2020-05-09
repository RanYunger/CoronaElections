package ID318783479_ID316334473;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Elections {
	// Fields
	protected static final Scanner scanner = new Scanner(System.in);
	protected static final int INITIAL_CAPACITY = 10;

	public static void main(String args[]) {

		String finalResultsString = "";
		ArrayList<ArrayList<Integer>> resultsByBallot = new ArrayList<>();
		int voterCapacity = INITIAL_CAPACITY;
		int partyCapacity = INITIAL_CAPACITY;
		int ballotCapacity = INITIAL_CAPACITY;
		YearMonth votingDate = null;
		Set<Citizen> voters;
		ArrayList<Party> parties;
		ArrayList<Ballot<? extends Citizen>> allBallots;
		boolean loop = true, electionsOccurred = false;
		int selection;

		System.out.println("Welcome to our voting system.");
		System.out.println("Please enter the year and month of the elections, in that order:");
		boolean validYearMonth = false;
		while (!validYearMonth) {
			try {
				votingDate = YearMonth.of(scanner.nextInt(), scanner.nextInt());
				validYearMonth = true;
			} catch (DateTimeException e) {
				System.out.println("Please enter a valid year-month pair");
			}
		}
		scanner.nextLine();

		voters = new Set<Citizen>();
		allBallots = new ArrayList<Ballot<? extends Citizen>>();
		parties = new ArrayList<Party>();
		init(votingDate, allBallots, parties, voters);

		while (loop) {
			selection = UIHandler.showMenu(electionsOccurred);
			scanner.nextLine();
			switch (selection) {
			case 1:
				UIHandler.addNewBallot(allBallots, votingDate);
				ballotCapacity = ensureCapacity(allBallots, ballotCapacity);
				break;
			case 2:
				UIHandler.addNewCitizen(voters, votingDate, allBallots);
				voterCapacity = ensureCapacity(voters.getElements(), voterCapacity);
				break;
			case 3:
				UIHandler.addNewParty(parties);
				partyCapacity = ensureCapacity(parties, partyCapacity);
				break;
			case 4:
				UIHandler.addCandidateToAParty(voters, parties);
				break;
			case 5:
				System.out.println(UIHandler.showBallotRegistry(allBallots));
				break;
			case 6:
				System.out.println(UIHandler.showVoterRegistry(voters, votingDate));
				break;
			case 7:
				System.out.println(UIHandler.showPartyRegistry(parties));
				break;
			case 8:
				UIHandler.runElections(allBallots, resultsByBallot, parties);
				break;
			case 9:
				UIHandler.showElectionsResults(finalResultsString, resultsByBallot, parties);
				break;
			case 10:
				UIHandler.showExitMessage();
				loop = false;
				break;
			default:
				System.out.println("Please choose a valid option.");
			}

		}
	}

	// Methods
	private static void init(YearMonth votingDate, ArrayList<Ballot<? extends Citizen>> allBallots,
			ArrayList<Party> parties, Set<Citizen> voters) {
		// Initiates 4 ballots
		allBallots.add(new Ballot<Citizen>("21st Road Street, Town City", votingDate));
		allBallots.add(new Ballot<Citizen>(votingDate));
		allBallots.add(new Ballot<Soldier>("Area 51, Nevada", votingDate));
		allBallots.add(new Ballot<SickCitizen>(votingDate));

		// Initiates 4 parties
		parties.add(new Party("Halikud", Party.PartyAssociation.Right, LocalDate.of(1973, 9, 13)));
		parties.add(new Party("Blue and White", Party.PartyAssociation.Center, LocalDate.of(2019, 2, 21)));
		parties.add(new Party("Israel is Our Home", Party.PartyAssociation.Center, LocalDate.of(1999, 3, 29)));
		parties.add(new Party("Israeli Labor Party", Party.PartyAssociation.Left, LocalDate.of(1968, 1, 21)));

		// Initiates 5 citizen
		voters.add(new Citizen(123456789, "Charles Foster Kane", 1941, 0, allBallots.get(0), false, false));
		voters.add(new Citizen(234567890, "Donald John Trump", 1946, 1, allBallots.get(1), true, true));
		voters.add(new Citizen(345678901, "Tonny Stark", 1970, 0, allBallots.get(1), false, false));
		voters.add(new SickCitizen(456789012, "Steve Rogers", 1918, 1, allBallots.get(3), true, true));
		voters.add(new Soldier(567890123, "Childish Gambino", 2001, 1, allBallots.get(2), false, false, true));

		// Initiates 8 candidates (2 per party)
		voters.add(new Candidate(678901234, "Benjamin Netanyahu", 1949, 50, allBallots.get(0), true, false));
		voters.add(new SickCandidate(789012345, "Miri Regev", 1965, 38, allBallots.get(3), true, false));
		voters.add(new SickCandidate(890123456, "Benny Gantz", 1959, 40, allBallots.get(3), true, true));
		voters.add(new SickCandidate(901234567, "Yair Lapid", 1963, 1, allBallots.get(3), true, true));
		voters.add(new SickCandidate(901234568, "Avigdor Lieberman", 1958, 1, allBallots.get(0), true, true));
		voters.add(new Candidate(901234566, "Oded Forer", 1977, 1, allBallots.get(0), false, true));
		voters.add(new Candidate(901234569, "Tamar Zandberg", 1976, 1, allBallots.get(0), false, false));
		voters.add(new Candidate(901234565, "Nitzan Horowitz", 1965, 1, allBallots.get(1), false, false));

		// Associates candidates to their parties
		getPartyByName(parties, "Halikud").addCandidate((Candidate) getCitizenByID(voters, 678901234));
		getPartyByName(parties, "Halikud").addCandidate((Candidate) getCitizenByID(voters, 789012345));
		getPartyByName(parties, "Blue and White").addCandidate((Candidate) getCitizenByID(voters, 890123456));
		getPartyByName(parties, "Blue and White").addCandidate((Candidate) getCitizenByID(voters, 901234567));
		getPartyByName(parties, "Israel is Our Home").addCandidate((Candidate) getCitizenByID(voters, 901234568));
		getPartyByName(parties, "Israel is Our Home").addCandidate((Candidate) getCitizenByID(voters, 901234566));
		getPartyByName(parties, "Israeli Labor Party").addCandidate((Candidate) getCitizenByID(voters, 901234569));
		getPartyByName(parties, "Israeli Labor Party").addCandidate((Candidate) getCitizenByID(voters, 901234565));
	}

	public static Citizen getCitizenByID(Set<Citizen> voters, int citizenID) {
		Collections.sort(voters.getElements());
		return binarySearch(voters.getElements(), citizenID);
	}

	public static Party getPartyByName(ArrayList<Party> parties, String partyName) {
		Collections.sort(parties);
		return binarySearch(parties, partyName);
	}

	// Binary Search
	public static Ballot<? extends Citizen> getBallotByID(ArrayList<Ballot<? extends Citizen>> ballots, int ballotID) {
		// Collections.sort(ballots);
		return binarySearch(ballots, ballotID);
	}

	public static boolean updateCitizenToCandidate(Set<Citizen> voters, Citizen citizen) {
		int index = voters.indexOf(getCitizenByID(voters, citizen.getID()));

		if (index != -1) {
			if (citizen.getClass() == Citizen.class) // "morphs" the Citizen into a Candidate
				voters.set(index,
						citizen.isIsolated()
								? new SickCandidate(citizen.getID(), citizen.getFullName(), citizen.getYearOfBirth(),
										citizen.getDaysOfSickness(), citizen.getAssociatedBallot(),
										citizen.isIsolated(), citizen.iswearingSuit())
								: new Candidate(citizen.getID(), citizen.getFullName(), citizen.getYearOfBirth(),
										citizen.getDaysOfSickness(), citizen.getAssociatedBallot(),
										citizen.isIsolated(), citizen.iswearingSuit()));
			return true;
		}
		return false;
	}

	public int countVotes(ArrayList<Ballot<Citizen>> ballots, int partyOffset) {
		int votes = 0;

		for (Ballot<Citizen> ballot : ballots)
			votes += ballot.getResults().get(partyOffset);

		return votes;
	}

	private static <T, U> T binarySearch(ArrayList<T> array, U key) {
		return binarySearch(array, key, 0, array.size() - 1);
	}

	private static <T, U> T binarySearch(ArrayList<T> array, U key, int start, int end) {
		if (start <= end) {
			int mid = (start + end) / 2;

			T element = array.get(mid);
			if (array.get(0) instanceof Ballot) {
				int ballotID = ((Ballot<? extends Citizen>) array.get(mid)).getID();
				if (ballotID == (int) key)
					return element;

				if (ballotID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof Citizen) {
				int citizenID = ((Citizen) array.get(mid)).getID();
				if (citizenID == (int) key)
					return element;

				if (citizenID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof Party) {
				String partyName = ((Party) array.get(mid)).getName();
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