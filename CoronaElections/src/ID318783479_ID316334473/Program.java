package ID318783479_ID316334473;

import java.time.LocalDate;
import java.util.Scanner;

//import java.util.ArrayList;

public class Program {
	// Constants
	public static final int MAX_ARRAY_SIZE = 100;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Elections elections = new Elections();

		Citizen[] Registry = new Citizen[MAX_ARRAY_SIZE];

		Ballot[] ballots = new Ballot[MAX_ARRAY_SIZE];
		ballots[0] = new Ballot();
		ballots[1] = new Ballot();
		ballots[2] = new Ballot();
		ballots[3] = new CoronaBallot();
		ballots[4] = new MilitaryBallot();
//		Elections elections = new Elections();
//		ArrayList<Citizen> citizens = new ArrayList<Citizen>();
//		ArrayList<Party> parties = new ArrayList<Party>();
//		ArrayList<Ballot> ballots = new ArrayList<Ballot>();

		// TODO: Hard-coded Citizens, Parties, Candidates, Ballots
		// Citizens (5)
		Citizen kane = new Citizen(123456789, "Charles Foster Kane", LocalDate.of(1941, 5, 1), ballots[0], false,
				false);

		Citizen Israel = new Citizen(101010101, "Israel Israeli", LocalDate.of(1948, 5, 14), ballots[3], true, false);
		// Parties (3)

		// Candidates (2 Candidates per party)

		// Ballots (2)

		int selection;
		boolean loop = true;

		while (loop) {
			selection = Menu(scan);
			switch (selection) {
			case 1:
				addNewBallotToElections(elections, scan);
				break;

			case 2:
				addCitizenToElections(elections, scan);
				break;

			case 3:
				addPartyToElections(elections, scan);
				break;

			case 4:
				addCandidateToAParty(elections, scan);
				break;

			case 5:
				showAllBallots(elections);
				break;

			case 6:
				showAllVoters(elections);
				break;

			case 7:
				showAllParties(elections);
				break;

			case 8:
				startElections(elections);
				break;

			case 9:
				showResults(elections);
				break;

			case 10:
				System.out.println("Bye Bye");
				loop = false;
				break;

			default:
				System.out.println("please enter a number between 1 and 10");
			}
		}
	}

	private static int Menu(Scanner scan) {
		System.out.println("To add a ballot, press 1");
		System.out.println("To add a citizen, press 2");
		System.out.println("To add a party, press 3");
		System.out.println("To add a candidate, press 4");
		System.out.println("To see all ballots, press 5");
		System.out.println("To see all citizens, press 6");
		System.out.println("To see all parties, press 7");
		System.out.println("To start the election process, press 8");
		System.out.println("To see the election results, press 9");
		System.out.println("To exit, press 10");
		return scan.nextInt();
	}

	// when pressing 1
	private static void addNewBallotToElections(Elections elections, Scanner scan) {
		String address;
		String voterName;

		System.out.println("Enter ballot's address:");
		address = scan.nextLine();

		Ballot ballot = new Ballot(address);

		System.out.println("Enter ballot's voters' full names, one by one - enter DONE to finish:");
		voterName = scan.nextLine();
		while (!voterName.split(" ")[0].equals("DONE")) {

			if (!ballot.addVoterFromRegistry(elections)) {
				// TODO: check why the voter can't vote here
				System.out.println(
						"Voter not in registry, already registered to a ballot, or cannot vote in this ballot");
				System.out.println("Enter another voter's name:");
				voterName = scan.nextLine();
			}
		}
	}

	// when pressing 2
	private static void addCitizenToElections(Elections elections, Scanner scan) {
		int ID;
		String fullName;
		LocalDate dateOfBirth;
		int ballotNumber;
		boolean isIsolated;
		boolean isWearingSuit;

		System.out.println("Enter voter's ID:");
		ID = scan.nextInt();
		scan.nextLine();

		System.out.println("Enter voter's name:");
		fullName = scan.nextLine();

		System.out.println("Enter year, month, and day of birth, in that order:");
		dateOfBirth = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
		scan.nextLine();

		System.out.println("Enter Ballot number:");
		ballotNumber = scan.nextInt();
		scan.nextLine();

		System.out.println("Is the voter in isolation?");
		isIsolated = scan.nextBoolean();
		scan.nextLine();

		System.out.println("Is the voter wearing a protective suit?");
		isWearingSuit = scan.nextBoolean();
		scan.nextLine();

		elections.addCitizenToRegistry(new Citizen(ID, fullName, dateOfBirth, ballotNumber, isIsolated, isWearingSuit));
	}

	// when pressing 3
	private static void addPartyToElections(Elections elections, Scanner scan) {
		String name;
		Party.PartyAssociation association;
		LocalDate foundationDate;
		Candidate[] candidates;

		System.out.println("Enter party's name:");
		name = scan.nextLine();

		System.out.println("Enter party's association (Left, Center, or Right):");
		association = Party.PartyAssociation.valueOf(scan.next());
		scan.nextLine();

		System.out.println("Enter year, month, and day of the party's foundation, in that order:");
		foundationDate = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
		scan.nextLine();

		// TODO: no idea what now..

	}

	// when pressing 4
	private static void addCandidateToAParty(Elections elections, Scanner scan) {

		System.out.println("Enter Candidate's full name:");
		Citizen c = elections.getVoterFromRegister(scan.nextLine());
		if (c == null)
			System.out.println("Candidate not in registry");
		else {
			System.out.println("Enter Candidate's designated Party:");
			Party party = elections.getPartyByName(scan.nextLine());
			if (party == null)
				System.out.println("Party not taking place in the elections");
			else
				c = c.makeCandidate(party);
		}

	}

	// when pressing 5
	private static void showAllBallots(Elections elections) {
		Ballot[] ballots = elections.getBallots();

		for (int i = 0; i < elections.getBallotsCount(); i++)
			System.out.println(ballots[i]);
	}

	// when pressing 6
	private static void showAllVoters(Elections elections) {
		Citizen[] citizens = elections.getVoterRegister();
		for (int i = 0; i < elections.getVotersCount; i++)
			System.out.println(citizens[i]);

	}

	// when pressing 7
	private static void showAllParties(Elections elections) {
		Party[] parties = Elections.getParties(); // shouldn't the parties belong to the current elections, but not for
													// ALL elections?
		for (int i = 0; i < parties.length && parties[i] != null; i++)
			System.out.println(parties[i]);

	}

	// when pressing 8
	private static void startElections(Elections elections) {
		// TODO Auto-generated method stub

	}

	// when pressing 9
	private static void showResults(Elections elections) {

	}

}
