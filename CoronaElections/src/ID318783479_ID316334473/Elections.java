package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Elections {
	// Constants
	public static final int MAX_ARRAY_SIZE = 100;

	// Fields

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		final YearMonth votingDate;
		VoterRegistry voterRegistry;
		PartyRegistry partyRegistry = new PartyRegistry();
		BallotRegistry ballotRegistry = new BallotRegistry();
		int selection;
		boolean loop = true, electionsOccurred = false;

		System.out.println("Welcome to our voting system.");
		System.out.println("Please enter the year and month of the elections, in that order:");
		votingDate = YearMonth.of(scan.nextInt(), scan.nextInt());
		scan.nextLine();

		voterRegistry = new VoterRegistry(votingDate);
		partyRegistry = new PartyRegistry();
		ballotRegistry = new BallotRegistry();

		init(voterRegistry, ballotRegistry, partyRegistry);

		while (loop) {
			selection = TUI.menu(scan, electionsOccurred);
			scan.nextLine();
			switch (selection) {
			case 1:
				addNewBallotToElections(scan, votingDate, ballotRegistry);
				break;
			case 2:
				addCitizen(scan, voterRegistry, votingDate, ballotRegistry);
				break;
			case 3:
				addPartyToElections(scan, partyRegistry);
				break;
			case 4:
				addCandidateToAParty(scan, voterRegistry, partyRegistry);
				break;
			case 5:
				System.out.println(ballotRegistry.toString());
				break;
			case 6:
				System.out.println(voterRegistry.toString());
				break;
			case 7:
				System.out.println(partyRegistry.toString());
				break;
			case 8:
				startElections(scan, ballotRegistry, partyRegistry);
				electionsOccurred = true;
				break;
			case 9:
				showResults(ballotRegistry, partyRegistry);
				break;
			case 10:
				System.out.println("Bye bye");
				loop = false;
				break;
			default:
				System.out.println("please choose a valid option");
			}
		}
	}

	private static void init(VoterRegistry voterRegistry, BallotRegistry ballots, PartyRegistry parties) {
		System.out.println("===== Initiation (4 ballots, 4 parties, 13 citizens (6 candidates)) =====");

		// Initiates 4 ballots
		ballots.add(new Ballot("21st Road Street, Town City", voterRegistry.getVotingDate()));
		ballots.add(new Ballot(voterRegistry.getVotingDate()));
		ballots.add(new MilitaryBallot("Top Secret", voterRegistry.getVotingDate()));
		ballots.add(new CoronaBallot(voterRegistry.getVotingDate()));

		// Initiates 4 parties
		parties.add(new Party("Halikud", Party.PartyAssociation.Right, LocalDate.of(1973, 9, 13)));
		parties.add(new Party("Blue and White", Party.PartyAssociation.Center, LocalDate.of(2019, 2, 21)));
		parties.add(new Party("Israel is Our Home", Party.PartyAssociation.Center, LocalDate.of(1999, 3, 29)));
		parties.add(new Party("Israeli Labor Party", Party.PartyAssociation.Left, LocalDate.of(1968, 1, 21)));

		// Initiates 5 citizen
		voterRegistry.add(new Citizen(123456789, "Charles Foster Kane", 1941, ballots.get(0), false, false));
		voterRegistry.add(new Citizen(234567890, "Donald John Trump", 1946, ballots.get(1), true, true));
		voterRegistry.add(new Citizen(345678901, "Tonny Stark", 1970, ballots.get(2), false, false));
		voterRegistry.add(new Citizen(456789012, "Steve Rogers", 1918, ballots.get(3), false, false));
		voterRegistry.add(new Citizen(567890123, "Noa Kirel", 2001, ballots.get(2), false, false));

		// Initiates 8 candidates (2 per party)
		voterRegistry.add(
				new Candidate(678901234, "Benjamin Netanyahu", 1949, ballots.get(0), true, false, parties.get(0), 1));
		voterRegistry.add(new Candidate(789012345, "Miri Regev", 1965, ballots.get(3), true, false, parties.get(0), 5));
		voterRegistry.add(new Candidate(890123456, "Benny Gantz", 1959, ballots.get(3), true, true, parties.get(1), 1));
		voterRegistry.add(new Candidate(901234567, "Yair Lapid", 1963, ballots.get(3), true, true, parties.get(1), 2));
		voterRegistry.add(
				new Candidate(901234568, "Avigdor Lieberman", 1958, ballots.get(0), true, true, parties.get(2), 1));
		voterRegistry.add(new Candidate(901234566, "Oded Forer", 1977, ballots.get(0), true, true, parties.get(2), 1));
		voterRegistry
				.add(new Candidate(901234569, "Tamar Zandberg", 1976, ballots.get(2), false, false, parties.get(3), 1));
		voterRegistry.add(
				new Candidate(901234565, "Nitzan Horowitz", 1965, ballots.get(1), false, false, parties.get(3), 1));

		// Adds initiated candidates to parties
		parties.get("Halikud").addCandidate((Candidate) voterRegistry.getByID(678901234));
		parties.get("Halikud").addCandidate((Candidate) voterRegistry.getByID(789012345));
		parties.get("Blue and White").addCandidate((Candidate) voterRegistry.getByID(890123456));
		parties.get("Blue and White").addCandidate((Candidate) voterRegistry.getByID(901234567));
		parties.get("Israel is Our Home").addCandidate((Candidate) voterRegistry.getByID(901234568));
		parties.get("Israel is Our Home").addCandidate((Candidate) voterRegistry.getByID(901234566));
		parties.get("Israeli Labor Party").addCandidate((Candidate) voterRegistry.getByID(901234569));
		parties.get("Israeli Labor Party").addCandidate((Candidate) voterRegistry.getByID(901234565));

		System.out.println("===== Initiation complete =====");
	}

	// When entering 1
	private static boolean addNewBallotToElections(Scanner scan, YearMonth votingDate, BallotRegistry ballots) {
		return TUI.addNewBallotToElections(scan, votingDate, ballots);
	}

	// When entering 2
	private static boolean addCitizen(Scanner scan, VoterRegistry registry, YearMonth votingDate,
			BallotRegistry ballots) {
		return TUI.addCitizen(scan, registry, votingDate, ballots);
	}

	// When entering 3
	private static boolean addPartyToElections(Scanner scan, PartyRegistry parties) {
		return TUI.addPartyToElections(scan, parties);
	}

	// When entering 4
	private static boolean addCandidateToAParty(Scanner scan, VoterRegistry voterRegistry, PartyRegistry parties) {
		return TUI.addCandidateToParty(scan, voterRegistry, parties);
	}

	// When entering 5
	private static void showResults(BallotRegistry ballots, PartyRegistry parties) {
		TUI.showResults(ballots, parties);
	}

	// When entering 8
	private static void startElections(Scanner scan, BallotRegistry ballots, PartyRegistry parties) {
		ballots.vote(scan, parties);
	}

	// When entering 9
	public static void sortResults(int[] results) {
		quickSort(results, 0, results.length - 1);
	}

	public static void sortParties(int[] finalResults, PartyRegistry parties) {
		sortPartiesByResults(finalResults, parties);
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
		int pivot = arr[high], i = low - 1, temp;
		for (int j = low; j < high; j++) {
			if (arr[j] > pivot) {
				i++;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	private static void sortPartiesByResults(int[] finalResults, PartyRegistry parties) {
		int maxValueOffset;
		int[] sortedPartyOffsets = new int[finalResults.length];
		Party[] sortedParties = parties.getPartyRegistry().clone();

		for (int i = 0; i < finalResults.length; i++) {
			maxValueOffset = indexOf(finalResults, getMaxValue(finalResults));
			sortedPartyOffsets[i] = maxValueOffset;
			finalResults[maxValueOffset] = -1;
		}

		for (int i = 0; i < sortedPartyOffsets.length; i++) {
			sortedParties[i] = parties.getPartyRegistry()[sortedPartyOffsets[i]];
		}
		parties.setPartyRegistry(sortedParties);
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
}