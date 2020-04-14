package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Elections {
	// Constants

	// Fields
	protected static Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) {
		YearMonth votingDate;
		ArrayList<Citizen> voterRegistry;
		ArrayList<Party> partyRegistry;
		ArrayList<Ballot> ballotRegistry;
		boolean loop = true, electionsOccurred = false;
		int selection;

		System.out.println("Welcome to our voting system.");
		System.out.println("Please enter the year and month of the elections, in that order:");
		votingDate = YearMonth.of(scanner.nextInt(), scanner.nextInt());
		scanner.nextLine();

		voterRegistry = new ArrayList<Citizen>();
		ballotRegistry = new ArrayList<Ballot>();
		partyRegistry = new ArrayList<Party>();
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
				System.out.println(showBallotRegistry(ballotRegistry));
				break;
			case 6:
				System.out.println(showVoterRegistry(voterRegistry, votingDate));
				break;
			case 7:
				System.out.println(showPartyRegistry(partyRegistry));
				break;
			case 8:
				runElections(ballotRegistry, partyRegistry);
				electionsOccurred = true;
				break;
			case 9:
				System.out.println(showResults(ballotRegistry, partyRegistry));
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

	public static String showPartyRegistry(ArrayList<Party> partyRegistry) {
		if (partyRegistry.size() == 0)
			return "Nothing to See here..";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < partyRegistry.size(); i++)
			sb.append("\n" + partyRegistry.get(i).toString());

		return sb.toString();
	}

	public static String showVoterRegistry(ArrayList<Citizen> voterRegistry, YearMonth votingDate) {
		if (voterRegistry.size() == 0)
			return "Nothing to See here..";

		StringBuilder sb = new StringBuilder("Date of voting: " + votingDate + "\nVoter list:");
		for (int i = 0; i < voterRegistry.size(); i++) {
			voterRegistry.get(i).calculateAge(votingDate);
			sb.append("\n\t" + voterRegistry.get(i).toString());
		}

		return sb.toString();
	}

	public static String showBallotRegistry(ArrayList<Ballot> ballotRegistry) {
		if (ballotRegistry.size() == 0)
			return "Nothing to See here..";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ballotRegistry.size(); i++)
			sb.append("\n" + ballotRegistry.get(i).toString().replaceAll("\n", "\n\t"));

		return sb.toString();
	}

	// Methods
	private static void init(YearMonth votingDate, ArrayList<Ballot> ballotRegistry, ArrayList<Party> partyRegistry,
			ArrayList<Citizen> voterRegistry) {
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
		getPartyByName(partyRegistry, "Halikud").addCandidate((Candidate) getCitizenByID(voterRegistry, 678901234));
		getPartyByName(partyRegistry, "Halikud").addCandidate((Candidate) getCitizenByID(voterRegistry, 789012345));
		getPartyByName(partyRegistry, "Blue and White")
				.addCandidate((Candidate) getCitizenByID(voterRegistry, 890123456));
		getPartyByName(partyRegistry, "Blue and White")
				.addCandidate((Candidate) getCitizenByID(voterRegistry, 901234567));
		getPartyByName(partyRegistry, "Israel is Our Home")
				.addCandidate((Candidate) getCitizenByID(voterRegistry, 901234568));
		getPartyByName(partyRegistry, "Israel is Our Home")
				.addCandidate((Candidate) getCitizenByID(voterRegistry, 901234566));
		getPartyByName(partyRegistry, "Israeli Labor Party")
				.addCandidate((Candidate) getCitizenByID(voterRegistry, 901234569));
		getPartyByName(partyRegistry, "Israeli Labor Party")
				.addCandidate((Candidate) getCitizenByID(voterRegistry, 901234565));
	}

	public static void runElections(ArrayList<Ballot> ballotRegistry, ArrayList<Party> partyRegistry) {
		for (Ballot ballot : ballotRegistry) {
			ballot.vote(partyRegistry);
		}
	}

	public static Citizen getCitizenByID(ArrayList<Citizen> voterRegistry, int citizenID) {
		for (int i = 0; i < voterRegistry.size(); i++) {
			if (voterRegistry.get(i).getID() == citizenID)
				return voterRegistry.get(i);
		}

		return null;
	}

	public static Party getPartyByName(ArrayList<Party> partyRegistry, String partyName) {
		for (int i = 0; i < partyRegistry.size(); i++) {
			if (partyRegistry.get(i).getName() == partyName)
				return partyRegistry.get(i);
		}

		return null;
	}

	public static Ballot getBallotByID(ArrayList<Ballot> ballotRegistry, int ballotID) {
		for (int i = 0; i < ballotRegistry.size(); i++) {
			if (ballotRegistry.get(i).getID() == ballotID)
				return ballotRegistry.get(i);
		}

		return null;
	}

	public static boolean updateCitizenToCandidate(ArrayList<Citizen> voterRegistry, Citizen citizen) {
		int index = voterRegistry.indexOf(getCitizenByID(voterRegistry, citizen.getID()));

		if (index != -1) {
			if (citizen.getClass() == Citizen.class) // "morphs" the Citizen into a Candidate
				voterRegistry.set(index, new Candidate(citizen.getID(), citizen.getFullName(), citizen.getYearOfBirth(),
						citizen.getAssociatedBallot(), citizen.isIsolated(), citizen.iswearingSuit(), null, -1));

			return true;
		}

		return false;
	}

	public int countVotes(ArrayList<Ballot> ballotRegistry, int partyOffset) {
		int votes = 0;

		for (Ballot ballot : ballotRegistry)
			votes += ballot.getResults()[partyOffset];

		return votes;
	}

	public static String showResults(ArrayList<Ballot> ballotRegistry, ArrayList<Party> partyRegistry) {
		StringBuilder sb = new StringBuilder();
		Party[] sortedParties;
		int[] currBallotResults;
		int[] finalResults = new int[partyRegistry.size()];

		for (int i = 0; i < ballotRegistry.size(); i++) {
			currBallotResults = ballotRegistry.get(i).getResults();
			sb.append(String.format("Ballot #%d:\n", ballotRegistry.get(i).getID()));
			for (int j = 0; j < currBallotResults.length; j++) {
				sb.append(String.format("%s: %d\t", partyRegistry.get(j).getName(), currBallotResults[j]));
				finalResults[j] += currBallotResults[j];
			}
			sb.append("voter percentage: " + ballotRegistry.get(i).getVotersPercentage() + "\n");
		}

		sortedParties = sortParties(finalResults.clone(), partyRegistry);
		partyRegistry = new ArrayList<>(Arrays.asList(sortedParties));
		sortResults(finalResults);

		sb.append("Final Results:");
		for (int i = 0; i < finalResults.length; i++)
			sb.append(String.format("\n%s : %d", partyRegistry.get(i).getName(), finalResults[i]));
		return sb.toString();
	}

	// Methods used to sort the parties and the results in a descending order
	public static void sortResults(int[] results) {
		quickSort(results, 0, results.length - 1);
	}

	public static Party[] sortParties(int[] finalResults, ArrayList<Party> partyRegistry) {
		int maxValueOffset;
		int[] sortedPartyOffsets = new int[finalResults.length];
		Party[] sortedParties = new Party[partyRegistry.size()];

		sortedParties = partyRegistry.toArray(sortedParties);
		for (int i = 0; i < finalResults.length; i++) {
			maxValueOffset = indexOf(finalResults, getMaxValue(finalResults));
			sortedPartyOffsets[i] = maxValueOffset;
			finalResults[maxValueOffset] = -1;
		}
		for (int i = 0; i < sortedPartyOffsets.length; i++) {
			sortedParties[i] = partyRegistry.get(sortedPartyOffsets[i]);
		}

		return sortedParties; 
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