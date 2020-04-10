package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Elections {
	// Constants

	// Fields
	protected static Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) {
		YearMonth votingDate;
		VoterRegistry voterRegistry;
		PartyRegistry partyRegistry;
		BallotRegistry ballotRegistry;
		boolean loop = true, electionsOccurred = false;
		int selection;

		System.out.println("Welcome to our voting system.");
		System.out.println("Please enter the year and month of the elections, in that order:");
		votingDate = YearMonth.of(scanner.nextInt(), scanner.nextInt());
		scanner.nextLine();

		voterRegistry = new VoterRegistry(votingDate);
		ballotRegistry = new BallotRegistry();
		partyRegistry = new PartyRegistry();
		init(votingDate, ballotRegistry, partyRegistry, voterRegistry);

		while (loop) {
			selection = UIHandler.showMenu(electionsOccurred);
			scanner.nextLine();
			switch (selection) {
			case 1:
				UIHandler.addNewBallotToElections(votingDate, ballotRegistry);
				break;
			case 2:
				UIHandler.addCitizen(voterRegistry, votingDate, ballotRegistry);
				break;
			case 3:
				UIHandler.addPartyToElections(partyRegistry);
				break;
			case 4:
				UIHandler.addCandidateToAParty(voterRegistry, partyRegistry);
				break;
			case 5:
				System.out.println(ballotRegistry);
				break;
			case 6:
				System.out.println(voterRegistry);
				break;
			case 7:
				System.out.println(partyRegistry);
				break;
			case 8:
				runElections(ballotRegistry, partyRegistry);
				electionsOccurred = true;
				break;
			case 9:
				System.out.println(ballotRegistry.showResults(partyRegistry));
				break;
			case 10:
				System.out.println("Thank you for your vote (or not). See you again in 3 months!");
				loop = false;
				break;
			default:
				System.out.println("Please choose a valid option.");
			}
		}
	}

	// Methods
	private static void init(YearMonth votingDate, BallotRegistry ballotRegistry, PartyRegistry partyRegistry,
			VoterRegistry voterRegistry) {
		// Initiates 4 ballots
		ballotRegistry.add(new Ballot("21st Road Street, Town City", votingDate));
		ballotRegistry.add(new Ballot(votingDate));
		ballotRegistry.add(new MilitaryBallot("Area 51, Nevada", votingDate));
		ballotRegistry.add(new CoronaBallot(votingDate));

		// Initiates 4 parties
		partyRegistry.add(new Party("Halikud", Party.PartyAssociation.Right, LocalDate.of(1973, 9, 13)));
		partyRegistry.add(new Party("Blue and White", Party.PartyAssociation.Center, LocalDate.of(2019, 2, 21)));
		partyRegistry.add(new Party("Israel is Our Home", Party.PartyAssociation.Center, LocalDate.of(1999, 3, 29)));
		partyRegistry.add(new Party("Israeli Labor Party", Party.PartyAssociation.Left, LocalDate.of(1968, 1, 21)));

		// Initiates 5 citizen
		voterRegistry.add(new Citizen(123456789, "Charles Foster Kane", 1941, ballotRegistry.get(0), false, false));
		voterRegistry.add(new Citizen(234567890, "Donald John Trump", 1946, ballotRegistry.get(1), true, true));
		voterRegistry.add(new Citizen(345678901, "Tonny Stark", 1970, ballotRegistry.get(1), false, false));
		voterRegistry.add(new Citizen(456789012, "Steve Rogers", 1918, ballotRegistry.get(3), true, true));
		voterRegistry.add(new Citizen(567890123, "Childish Gambino", 2001, ballotRegistry.get(2), false, false));

		// Initiates 8 candidates (2 per party)
		voterRegistry.add(new Candidate(678901234, "Benjamin Netanyahu", 1949, ballotRegistry.get(0), true, false,
				partyRegistry.get(0), 1));
		voterRegistry.add(new Candidate(789012345, "Miri Regev", 1965, ballotRegistry.get(3), true, false,
				partyRegistry.get(0), 5));
		voterRegistry.add(new Candidate(890123456, "Benny Gantz", 1959, ballotRegistry.get(3), true, true,
				partyRegistry.get(1), 1));
		voterRegistry.add(new Candidate(901234567, "Yair Lapid", 1963, ballotRegistry.get(3), true, true,
				partyRegistry.get(1), 2));
		voterRegistry.add(new Candidate(901234568, "Avigdor Lieberman", 1958, ballotRegistry.get(0), true, true,
				partyRegistry.get(2), 1));
		voterRegistry.add(new Candidate(901234566, "Oded Forer", 1977, ballotRegistry.get(0), false, true,
				partyRegistry.get(2), 1));
		voterRegistry.add(new Candidate(901234569, "Tamar Zandberg", 1976, ballotRegistry.get(0), false, false,
				partyRegistry.get(3), 1));
		voterRegistry.add(new Candidate(901234565, "Nitzan Horowitz", 1965, ballotRegistry.get(1), false, false,
				partyRegistry.get(3), 2));

		// Associates candidates to their parties
		partyRegistry.get("Halikud").addCandidate((Candidate) voterRegistry.getByID(678901234));
		partyRegistry.get("Halikud").addCandidate((Candidate) voterRegistry.getByID(789012345));
		partyRegistry.get("Blue and White").addCandidate((Candidate) voterRegistry.getByID(890123456));
		partyRegistry.get("Blue and White").addCandidate((Candidate) voterRegistry.getByID(901234567));
		partyRegistry.get("Israel is Our Home").addCandidate((Candidate) voterRegistry.getByID(901234568));
		partyRegistry.get("Israel is Our Home").addCandidate((Candidate) voterRegistry.getByID(901234566));
		partyRegistry.get("Israeli Labor Party").addCandidate((Candidate) voterRegistry.getByID(901234569));
		partyRegistry.get("Israeli Labor Party").addCandidate((Candidate) voterRegistry.getByID(901234565));
	}

	public static void runElections(BallotRegistry ballotRegistry, PartyRegistry partyRegistry) {
		ballotRegistry.vote(partyRegistry);
	}

	// Methods used to sort the parties and the results in a descending order
	public static void sortResults(int[] results) {
		quickSort(results, 0, results.length - 1);
	}

	public static void sortParties(int[] finalResults, PartyRegistry parties) {
		int maxValueOffset;
		int[] sortedPartyOffsets = new int[finalResults.length];
		Party[] sortedParties = parties.getPartyRegistry();

		for (int i = 0; i < finalResults.length; i++) {
			maxValueOffset = indexOf(finalResults, getMaxValue(finalResults));
			sortedPartyOffsets[i] = maxValueOffset;
			finalResults[maxValueOffset] = -1;
		}

		for (int i = 0; i < sortedPartyOffsets.length; i++) {
			sortedParties[i] = parties.getPartyRegistry()[sortedPartyOffsets[i]];
		}
	}

	private static int[] quickSort(int arr[], int low, int high) {
		int pi;

		if (low < high) {
			pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}

		return arr;
	}

	private static int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j] > pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);

		return i + 1;
	}

	private static int getMaxValue(int[] arr) {
		int max = arr[0];

		for (int i = 1; i < arr.length; i++)
			max = arr[i] > max ? arr[i] : max;

		return max;
	}

	private static int indexOf(int[] arr, int value) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value)
				return i;
		}

		return -1;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}