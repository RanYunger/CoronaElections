package ID318783479_ID316334473;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Elections {

	// Constants
	public static final int MAX_ARRAY_SIZE = 100;
	
	// Fields

	// These are a bit awful in my opinion, but it works for now, so... ~Shy
	public static final Party[] partyRegistry = new Party[MAX_ARRAY_SIZE];
	private static int partyCount = 0;
	public static final Ballot[] ballots = new Ballot[MAX_ARRAY_SIZE];
	private static int ballotCount = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		final YearMonth votingDate;
		VoterRegistry voterRegistry;
		int selection;
		boolean loop = true, electionsOccurred = false;
		
		System.out.println("Welcome to our voting system.");
		System.out.println("Please enter the year and month of the elections, in that order:");
		votingDate = YearMonth.of(scan.nextInt(), scan.nextInt());
		voterRegistry = new VoterRegistry(votingDate);
		
		init(voterRegistry);
		
		while (loop) {
			selection = TUI.Menu(scan, electionsOccurred);
			switch (selection) {
			case 1:
				addNewBallotToElections(scan, votingDate);
				break;
			case 2:
				addCitizen(scan, voterRegistry, votingDate);
				break;
			case 3:
				addPartyToElections(scan);
				break;
			case 4:
				addCandidateToAParty(scan, voterRegistry);
				break;
			case 5:
				showAllBallots();
				break;
			case 6:
				System.out.println(voterRegistry.toString());
				break;
			case 7:
				showAllParties();
				break;
			case 8:
				startElections();
				electionsOccurred = true;
				break;
			case 9:
				if (electionsOccurred) // control flow - only allow to show results after they were calculated
					showResults();
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
	private static void init(VoterRegistry voterRegistry) {
		// Initiates AT LEAST 2 ballots
		ballots[0] = new Ballot(voterRegistry.getVotingDate());
		ballots[1] = new Ballot(voterRegistry.getVotingDate());
		
		// Initiates AT LEAST 3 parties
		partyRegistry[0] = new Party("Halikud", Party.PartyAssociation.Right, YearMonth.of(1973, 9));
		partyRegistry[1] = new Party("Blue and White", Party.PartyAssociation.Center, YearMonth.of(2019, 1));
		partyRegistry[2] = new Party("Israeli Labor Party", Party.PartyAssociation.Left, YearMonth.of(1968, 1));
		
		// Initiates AT LEAST 5 citizen
		voterRegistry.addCitizen(new Citizen(123456789, "Charles Foster Kane", YearMonth.of(1941, 5), 2, false, false));
		voterRegistry.addCitizen(new Citizen(234567890, "Donald John Trump", YearMonth.of(1946, 6), 2, true, true));
		voterRegistry.addCitizen(new Citizen(345678901, "Tonny Stark", YearMonth.of(1970, 5), 2, false, false));
		voterRegistry.addCitizen(new Citizen(456789012, "Steve Rogers", YearMonth.of(1918, 7), 2, false, false));
		voterRegistry.addCitizen(new Citizen(567890123, "Noa Kirel", YearMonth.of(2001, 4), 1, false, false));
		
		// Initiates AT LEAST 6 candidates (2 per party)
		voterRegistry.addCitizen(new Candidate(678901234, "Benjamin Netanyahu", YearMonth.of(1949, 10), 1, true, false, 1));
		voterRegistry.addCitizen(new Candidate(789012345, "Miri Regev", YearMonth.of(1965, 5), 1, true, false, 5));		
		voterRegistry.addCitizen(new Candidate(890123456, "Benny Gantz", YearMonth.of(1959, 6), 2, true, true, 1));
		voterRegistry.addCitizen(new Candidate(901234567, "Yair Lapdid", YearMonth.of(1963, 11), 2, true, true, 2));
		voterRegistry.addCitizen(new Candidate(901234568, "Avigdor Lieberman", YearMonth.of(1958, 7), 2, true, true, 1));		
		voterRegistry.addCitizen(new Candidate(901234569, "Tamar Zandberg", YearMonth.of(1976, 4), 3, false, false, 1));
	}
	// When entering 1
	private static boolean addNewBallotToElections(Scanner scan, YearMonth votingDate) {
		String address;
		
		// Validations
		if (ballotCount == MAX_ARRAY_SIZE) {
			System.out.println("Cannot add any more ballots!\nplease try again in the next elections.");
			
			return false;
		}
		
		System.out.println("Enter ballot's address: ");
		address = scan.nextLine();
		ballots[ballotCount++] = new Ballot(address, votingDate);
		
		return true;
	}
	// When entering 2
	private static boolean addCitizen(Scanner scan, VoterRegistry registry, YearMonth votingDate) {
		int ID, year, month;
		int associatedBallotID;
		YearMonth dateOfBirth;
		String fullName;
		boolean isIsolated, isWearingSuit = false;
		
		// Validations
		System.out.println("Enter voter's ID:");
		ID = scan.nextInt();
		scan.nextLine();
		if (registry.getCitizenByID(ID) != null) {
			System.out.println("This Citizen is already in the registry, try something else.\n");
			
			return false;
		}
		System.out.println("Enter year and month of birth, in that order:");
		year = scan.nextInt();
		month = scan.nextInt();
		dateOfBirth = YearMonth.of(year, month);
		if (dateOfBirth.until(votingDate, ChronoUnit.YEARS) < Citizen.VOTING_AGE) {
			System.out.println("sorry, this citizen is too young to vote, try something else.\n");
			
			return false;
		}

		System.out.println("Enter voter's name:");
		fullName = scan.nextLine();
		System.out.println("Enter associated Ballot ID:");
		associatedBallotID = scan.nextInt();
		scan.nextLine();
		System.out.println("Is the voter in isolation?");
		isIsolated = scan.nextBoolean();
		scan.nextLine();
		if (isIsolated) {
			System.out.println("Is the voter wearing a protective suit?");
			isWearingSuit = scan.nextBoolean();
			scan.nextLine();
		}

		registry.addCitizen(new Citizen(ID, fullName, dateOfBirth, associatedBallotID, isIsolated, isWearingSuit));
		System.out.println("Voter successfully added to the voter registry!");
		
		return true;
	}
	// When entering 3
	// adding a party's candidates is yet to be implemented
	private static boolean addPartyToElections(Scanner scan) {
		String partyName;
		Party.PartyAssociation wing;
		YearMonth foundationDate;
		Party party;
		int i;

		// Validations
		if (partyCount == MAX_ARRAY_SIZE) {
			System.out.println("Cannot add any more parties!\nplease try again in the next elections.");
			
			return false;
		}
		System.out.println("Enter party's name:");
		partyName = scan.nextLine();
		if (getPartyByName(partyName) != null) {
			System.out.println("Loos like this party is already in the lists.");
			
			return true;
		}
		
		System.out.println("Enter party's association (Left, Center, or Right):");
		wing = Party.PartyAssociation.valueOf(scan.next());
		scan.nextLine();
		System.out.println("Enter year and month of the party's foundation, in that order:");
		foundationDate = YearMonth.of(scan.nextInt(), scan.nextInt());
		scan.nextLine();

		party = new Party(partyName, wing, foundationDate);
		if (partyCount == 0) {
			partyRegistry[partyCount++] = party;
			
			return true;
		}

		i = partyCount - 1;
		while (i >= 0 && partyRegistry[i].getName().compareTo(partyName) > 0) {
			partyRegistry[i + 1] = partyRegistry[i];
			i--;
		}
		partyRegistry[i] = party;
		partyCount++;

		return true;
	}
	// When entering 4
	private static boolean addCandidateToAParty(Scanner scan, VoterRegistry voterRegistry) {
		Candidate addedCandidate;
		int addedCandidateID;
		String partyName;
		
		System.out.println("Enter candidate's ID and party's name, in that order:");
		addedCandidateID = scan.nextInt();
		partyName = scan.nextLine();
		addedCandidate = (Candidate) voterRegistry.getCitizenByID(addedCandidateID);
		
		if(addedCandidate == null)
			return false;
		
		getPartyByName(partyName).addCandidate(addedCandidate);
		
		return true;
	}
	// When entering 5
	private static void showAllBallots() {
		if (ballotCount == 0) {
			System.out.println("Nothing to See here..");
			
			return;
		}
		
		for (int i = 0; i < ballotCount; i++)
			System.out.println(ballots[i].toString() + "\n");
	}
	// When entering 7
	private static void showAllParties() {
		if (partyCount == 0) {
			System.out.println("Nothing to See here..");
			
			return;
		}
		for (int i = 0; i < partyCount; i++)
			System.out.println(partyRegistry[i].toString() + "\n");
	}
	private static void showResults() {
		int[] currBallotResults, finalResults = new int[partyRegistry.length];
		
		for (Ballot ballot : ballots) {
			currBallotResults = ballot.getResults();
			for (int i = 0; i < currBallotResults.length; i++)
				finalResults[i] += currBallotResults[i];
		}
		finalResults = sortResults(finalResults);
		
		System.out.println("The voice have been counted. Here are the final results:");
		for (int i = 0; i < finalResults.length; i++)
			System.out.println(String.format("%s : %d", partyRegistry[i].getName(), finalResults[i]));
	}
	private static void startElections() {
		// TODO: Implement this method
	}

	// Helper functions
	public static int countParticipatingParties() {
		int i = 0;
		
		while (i < partyRegistry.length && partyRegistry[i] != null)
			i++;
		
		return i;
	}
	public static int getPartyOffsetByName(String partyName) {
		for (int i = 0; i < partyRegistry.length; i++)
			if(partyRegistry[i].getName() == partyName)
				return i;
		
		return -1;
	}
	public static Party getPartyByName(String partyName) {
		return (partyCount > 0) ? getPartyByName(0, partyCount - 1, partyName) : null;
	}
	private static Party getPartyByName(int leftIndex, int rightIndex, String partyName) {
		int mid;
		
		if (rightIndex >= leftIndex) {
			mid = leftIndex + (rightIndex - leftIndex) / 2;
			if (partyRegistry[mid].getName().equalsIgnoreCase(partyName))
				return partyRegistry[mid];
			if (partyRegistry[mid].getName().compareTo(partyName) > 0)
				return getPartyByName(leftIndex, mid - 1, partyName);

			return getPartyByName(mid + 1, rightIndex, partyName);
		}
		
		return null;
	}
	private static int[] sortResults(int[] results) {
		return TUI.quickSort(results, 0, results.length - 1);
	}
}
