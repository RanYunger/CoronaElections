package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

// TODO: Debug all menu methods, making sure everything works as expected ~Ran
public class Elections {

	// Constants
	public static final int MAX_ARRAY_SIZE = 100;
	
	// Fields

	// These are a bit awful in my opinion, but it works for now, so... ~Shy
	public static final Party[] partyRegistry = new Party[MAX_ARRAY_SIZE];
	private static int partyCount = 0;
	public static final Ballot[] ballots = new Ballot[MAX_ARRAY_SIZE];
	private static int ballotCount = 0;

	public static void main(String args[]) {
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
			selection = TUI.menu(scan, electionsOccurred);
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
				startElections(scan);
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
		ballotCount = 2;
		
		// Initiates AT LEAST 3 parties
		partyRegistry[0] = new Party("Halikud", Party.PartyAssociation.Right, LocalDate.of(1973, 9,13));
		partyRegistry[1] = new Party("Blue and White", Party.PartyAssociation.Center, LocalDate.of(2019, 2, 21));
		partyRegistry[2] = new Party("Israeli Labor Party", Party.PartyAssociation.Left, LocalDate.of(1968, 1, 21));
		partyCount = 3;
		
		// Initiates AT LEAST 5 citizen
		voterRegistry.addCitizen(new Citizen(123456789, "Charles Foster Kane", 1941, ballots[2], false, false));
		voterRegistry.addCitizen(new Citizen(234567890, "Donald John Trump", 1946, ballots[2], true, true));
		voterRegistry.addCitizen(new Citizen(345678901, "Tonny Stark", 1970, ballots[2], false, false));
		voterRegistry.addCitizen(new Citizen(456789012, "Steve Rogers", 1918, ballots[2], false, false));
		voterRegistry.addCitizen(new Citizen(567890123, "Noa Kirel", 2001, ballots[1], false, false));
		
		// Initiates AT LEAST 6 candidates (2 per party)
		voterRegistry.addCitizen(new Candidate(678901234, "Benjamin Netanyahu", 1949, ballots[1], true, false, 1));
		voterRegistry.addCitizen(new Candidate(789012345, "Miri Regev", 1965, ballots[1], true, false, 5));		
		voterRegistry.addCitizen(new Candidate(890123456, "Benny Gantz", 1959, ballots[2], true, true, 1));
		voterRegistry.addCitizen(new Candidate(901234567, "Yair Lapdid", 1963, ballots[2], true, true, 2));
		voterRegistry.addCitizen(new Candidate(901234568, "Avigdor Lieberman", 1958, ballots[2], true, true, 1));		
		voterRegistry.addCitizen(new Candidate(901234569, "Tamar Zandberg", 1976, ballots[3], false, false, 1));
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
		int citizenID, yearOfBirth, associatedBallotID;
		boolean isIsolated, isWearingSuit = false;
		String fullName;
		
		// Validations
		System.out.println("Enter voter's ID:");
		citizenID = scan.nextInt();
		scan.nextLine();
		if (registry.getCitizenByID(citizenID) != null) {
			System.out.println("This Citizen is already in the registry, try something else.\n");
			
			return false;
		}
		System.out.println("Enter year of birth, in that order:");
		yearOfBirth = scan.nextInt();
		if ((votingDate.getYear() - yearOfBirth) < Citizen.VOTING_AGE) {
			System.out.println("Sorry, this citizen is too young to vote, try something else.\n");
			
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

		registry.addCitizen(new Citizen(citizenID, fullName, yearOfBirth, ballots[associatedBallotID - 1], isIsolated, isWearingSuit));
		System.out.println("Voter successfully added to the voter registry!");
		
		return true;
	}
	// When entering 3
	// adding a party's candidates is yet to be implemented
	private static boolean addPartyToElections(Scanner scan) {
		String partyName;
		Party.PartyAssociation wing;
		LocalDate foundationDate;
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
			System.out.println("Looks like this party is already in the lists.");
			
			return true;
		}
		
		System.out.println("Enter party's association (Left, Center, or Right):");
		wing = Party.PartyAssociation.valueOf(scan.next());
		scan.nextLine();
		System.out.println("Enter year, month and day of the party's foundation, in that order:");
		foundationDate = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
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
		
		System.out.println("The votes have been counted. Here are the final results:");
		for (Ballot ballot : ballots) {
			currBallotResults = ballot.getResults();
			System.out.println(String.format("Ballot #%d:", ballot.getID()));
			for (int i = 0; i < currBallotResults.length; i++) {
				System.out.println(String.format("%s: %d", partyRegistry[i].getName(), currBallotResults[i]));
				finalResults[i] += currBallotResults[i];
			}
		}
		finalResults = sortResults(finalResults);
			
		for (int i = 0; i < finalResults.length; i++)
			System.out.println(String.format("%s : %d", partyRegistry[i].getName(), finalResults[i]));
	}
	private static void startElections(Scanner scan) {
		for (Ballot ballot : ballots)
			ballot.vote(scan, partyRegistry);
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
