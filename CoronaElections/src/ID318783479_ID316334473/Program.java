package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Program {
	// Constants
	public static final int MAX_ARRAY_SIZE = 100;

	// Fields
	public static final Scanner Scanner = new Scanner(System.in);

	// Methods
	public static void main(String args[]) {
		Elections elections;
		YearMonth votingDate;
		boolean loop = true, electionsOccurred = false;
		int selection;

		System.out.println("Welcome to our voting system.");
		System.out.println("Please enter the year and month of the elections, in that order:");
		votingDate = YearMonth.of(Scanner.nextInt(), Scanner.nextInt());
		Scanner.nextLine();

		elections = new Elections(votingDate);

		while (loop) {
			selection = showMenu(electionsOccurred);
			Scanner.nextLine();
			switch (selection) {
			case 1:
				addNewBallotToElections(votingDate, elections.getBallotRegistry());
				break;
			case 2:
				addCitizen(elections.getVoterRegistry(), votingDate, elections.getBallotRegistry());
				break;
			case 3:
				addPartyToElections(elections.getPartyRegistry());
				break;
			case 4:
				addCandidateToAParty(elections.getVoterRegistry(), elections.getPartyRegistry());
				break;
			case 5:
				System.out.println(elections.getBallotRegistry().toString());
				break;
			case 6:
				System.out.println(elections.getVoterRegistry().toString());
				break;
			case 7:
				System.out.println(elections.getPartyRegistry().toString());
				break;
			case 8:
				elections.runElections();
				electionsOccurred = true;
				break;
			case 9:
				System.out.println(elections.getBallotRegistry().showResults(elections.getPartyRegistry()));
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

	public static int showMenu(boolean electionsOccurred) {
		int selection;
		
		System.out.println("========== MENU ==========");
		if (!electionsOccurred) { // only add if the elections haven't taken place
			System.out.println("1 = Add ballot");
			System.out.println("2 = Add citizen");
			System.out.println("3 = Add party");
			System.out.println("4 = Add candidate to party");
		}
		System.out.println("5 = Show all ballots");
		System.out.println("6 = Show all citizens");
		System.out.println("7 = Show all parties");
		if (!electionsOccurred) // the elections should run precisely once
			System.out.println("8 = Start elections");
		else // show results only if the elections have taken place
			System.out.println("9 = Show elections results");
		System.out.println("10 = Exit");
		System.out.println("========== MENU ==========");

		System.out.println("Enter the code for your desired option:");
		selection = Scanner.nextInt();
		if (electionsOccurred) { // don't allow options 1, 2, 3, 4, or 8 if the elections have taken place
			if (((1 <= selection) && (selection <= 4)) || (selection == 8))
				return 0;
		} else if (selection == 9)
			return 0;

		return selection;
	}

	public static int vote(PartyRegistry candidateParties, Citizen citizen) {
		int voterChoice;
		String selection;

		// Validations
		if ((citizen.isIsolated()) && (citizen.associatedBallot instanceof CoronaBallot) && (!citizen.iswearingSuit())) {
			System.out.println(String.format("Greetings, %s. You can't vote without a suit, so we have to turn you back.", citizen.fullName));

			return -1;
		}

		System.out.println(String.format("Greetings, %s. Do you want to vote? (Y/N)", citizen.fullName));
		while (true) {
			selection = Scanner.next();
			if (selection.equalsIgnoreCase("Y")) {
				for (int i = 0; i < candidateParties.getPartyCount(); i++)
					System.out.println(String.format("%d = %s", i + 1, candidateParties.get(i).getName()));
				while (true) {
					System.out.println("Enter the code for your chosen party:");
					voterChoice = Scanner.nextInt();
					if ((1 <= voterChoice) && (voterChoice <= candidateParties.getPartyCount())) {
						selection = Scanner.nextLine();

						return voterChoice;
					}
					System.out.println("Invalid choice.");
				}
			} else if (!selection.equalsIgnoreCase("N")) {
				Scanner.nextLine();
				System.out.println("Please enter a valid choice - Y to vote, N to skip voting.");
			} else
				return -1;
		}
	}

	// When entering 1
	private static boolean addNewBallotToElections(YearMonth votingDate, BallotRegistry ballots) {
		String address;

		// Validations
		if (ballots.getBallotCount() == MAX_ARRAY_SIZE) {
			System.out.println("Cannot add any more ballots!\nplease try again in the next elections.");

			return false;
		}
		System.out.println("Enter ballot's address: ");
		address = Scanner.nextLine();
		while (true) {
			System.out.println("For a regular ballot, enter 1\nFor a military ballot, enter 2\nFor a corona ballot, enter 3");
			switch (Scanner.nextInt()) {
			case 1:
				return ballots.add(new Ballot(address, votingDate));
			case 2:
				return ballots.add(new MilitaryBallot(address, votingDate));
			case 3:
				return ballots.add(new CoronaBallot(address, votingDate));
			default:
				System.out.println("Invalid input!");
			}
		}
	}

	// When entering 2
	private static boolean addCitizen(VoterRegistry voterRegistry, YearMonth votingDate, BallotRegistry ballots) {
		Citizen citizen;
		int citizenID, yearOfBirth, associatedBallotID;
		boolean isIsolated, isWearingSuit = false;
		String fullName;

		// Validations
		System.out.println("Enter voter's ID:");
		citizenID = Scanner.nextInt();
		Scanner.nextLine();
		if (voterRegistry.getByID(citizenID) != null) {
			System.out.println("This Citizen is already in the registry, try something else.\n");

			return false;
		}
		System.out.println("Enter year of birth:");
		yearOfBirth = Scanner.nextInt();
		Scanner.nextLine();
		if ((votingDate.getYear() - yearOfBirth) < Citizen.VOTER_AGE) {
			System.out.println("Sorry, this citizen is too young to vote, try something else.\n");

			return false;
		}

		System.out.println("Enter voter's name:");
		fullName = Scanner.nextLine();
		System.out.println("Enter associated Ballot ID:");
		associatedBallotID = Scanner.nextInt();
		Scanner.nextLine();
		if ((associatedBallotID < 0) || (ballots.getBallotCount() < associatedBallotID)) {
			System.out.printf("Ballot not in registry, there are only %d ballots, with IDs 0-%d.\n",
					ballots.getBallotCount(), ballots.getBallotCount() - 1);
			
			return false;
		}

		System.out.println("Is the voter in isolation (Y/N)?");
		isIsolated = Scanner.next().toUpperCase() == "Y";
		Scanner.nextLine();
		if (isIsolated) {
			System.out.println("Is the voter wearing a protective suit (Y/N)?");
			isWearingSuit = Scanner.next().toUpperCase() == "Y";
			Scanner.nextLine();
		}

		citizen = new Citizen(citizenID, fullName, yearOfBirth, ballots.get(associatedBallotID - 1), isIsolated, isWearingSuit);
		ballots.get(associatedBallotID).addVoter(citizen);
		voterRegistry.add(citizen);

		return true;
	}

	// When entering 3
	private static boolean addPartyToElections(PartyRegistry parties) {
		String partyName;
		Party.PartyAssociation wing;
		LocalDate foundationDate;

		// Validations
		if (parties.getPartyCount() == MAX_ARRAY_SIZE) {
			System.out.println("Cannot add any more parties! Please try again in the next elections.");

			return false;
		}
		System.out.println("Enter party's name:");
		partyName = Scanner.nextLine();
		if (parties.get(partyName) != null) {
			System.out.println("Looks like this party is already in the lists.");

			return true;
		}

		System.out.println("Enter party's association (Left, Center, or Right):");
		wing = Party.PartyAssociation.valueOf(Scanner.next());
		Scanner.nextLine();
		System.out.println("Enter year, month and day of the party's foundation, in that order:");
		foundationDate = LocalDate.of(Scanner.nextInt(), Scanner.nextInt(), Scanner.nextInt());
		Scanner.nextLine();

		return parties.add(new Party(partyName, wing, foundationDate));
	}

	// When entering 4
	private static boolean addCandidateToAParty(VoterRegistry voterRegistry, PartyRegistry parties) {
		Candidate candidate;
		int addedCandidateID;
		String partyName;

		System.out.println("Enter candidate's ID and party's name, in that order:");
		addedCandidateID = Scanner.nextInt();
		Scanner.nextLine();
		partyName = Scanner.nextLine();

		if (voterRegistry.getByID(addedCandidateID) == null) {
			System.out.println("Candidate not registered, so he can't be added");
			return false;
		}

		voterRegistry.updateCitizenToCandidate(voterRegistry.getByID(addedCandidateID));
		candidate = (Candidate) voterRegistry.getByID(addedCandidateID);
		
		return parties.get(partyName).addCandidate(candidate);
	}
}
