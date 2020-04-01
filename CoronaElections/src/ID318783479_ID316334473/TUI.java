package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

// Helper class, should aid the Elections main class
public class TUI {

	public static int menu(Scanner scan, boolean electionsOccurred) {
		if (!electionsOccurred) { // only add if the elections haven't taken place
			System.out.println("To add a ballot, enter 1");
			System.out.println("To add a citizen, enter 2");
			System.out.println("To add a party, enter 3");
			System.out.println("To add a candidate, enter 4");
		}
		System.out.println("To see all ballots, enter 5");
		System.out.println("To see all citizens, enter 6");
		System.out.println("To see all parties, enter 7");

		if (!electionsOccurred) // the elections should run precisely once
			System.out.println("To start the election process, enter 8");
		else // show results only if the elections have taken place
			System.out.println("To see the election results, enter 9");

		System.out.println("To exit, enter 10");

		int selection = scan.nextInt();
		if (electionsOccurred) { // don't allow options 1, 2, 3, 4, or 8 if the elections have taken place
			if ((1 <= selection && selection <= 4) || selection == 8)
				return 0;
		}
		else if (selection == 9)
			return 0;

		return selection;
	}

	// when entering 1 in the menu
	public static boolean addNewBallotToElections(Scanner scan, YearMonth votingDate, BallotRegistry ballots) {
		String address;

		// Validations
		if (ballots.getBallotCount() == Elections.MAX_ARRAY_SIZE) {
			System.out.println("Cannot add any more ballots!\nplease try again in the next elections.");

			return false;
		}
		System.out.println("Enter ballot's address: ");
		address = scan.nextLine();
		while (true) {
			System.out.println("For a regular ballot, enter 1\nFor a military ballot, enter 2\nFor a corona ballot, enter 3");
			switch (scan.nextInt()) {
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

	// when entering 2 in the menu
	public static boolean addCitizen(Scanner scan, VoterRegistry registry, YearMonth votingDate,
			BallotRegistry ballots) {
		int citizenID, yearOfBirth, associatedBallotID;
		boolean isIsolated, isWearingSuit = false;
		String fullName;

		// Validations
		System.out.println("Enter voter's ID:");
		citizenID = scan.nextInt();
		scan.nextLine();
		if (registry.get(citizenID) != null) {
			System.out.println("This Citizen is already in the registry, try something else.\n");

			return false;
		}
		System.out.println("Enter year of birth:");
		yearOfBirth = scan.nextInt();
		scan.nextLine();
		if ((votingDate.getYear() - yearOfBirth) < Citizen.VOTING_AGE) {
			System.out.println("Sorry, this citizen is too young to vote, try something else.\n");

			return false;
		}

		System.out.println("Enter voter's name:");
		fullName = scan.nextLine();
		System.out.println("Enter associated Ballot ID:");
		associatedBallotID = scan.nextInt();
		scan.nextLine();
		if (associatedBallotID < 0 || ballots.getBallotCount() < associatedBallotID) {
			System.out.printf("Ballot not in registry, there are only %d ballots, with IDs 0-%d.\n\n",
					ballots.getBallotCount(), ballots.getBallotCount() - 1);
		}

		System.out.println("Is the voter in isolation (Y/N)?");
		isIsolated = scan.next().toUpperCase() == "Y";
		scan.nextLine();
		if (isIsolated) {
			System.out.println("Is the voter wearing a protective suit (Y/N)?");
			isWearingSuit = scan.next().toUpperCase() == "Y";
			scan.nextLine();
		}

		registry.add(new Citizen(citizenID, fullName, yearOfBirth, ballots.get(associatedBallotID - 1), isIsolated,	isWearingSuit));
		System.out.println("Voter successfully added to the voter registry!");

		return true;
	}

	// When entering 3 in the menu
	public static boolean addPartyToElections(Scanner scan, PartyRegistry parties) {
		String partyName;
		Party.PartyAssociation wing;
		LocalDate foundationDate;

		// Validations
		if (parties.getPartyCount() == Elections.MAX_ARRAY_SIZE) {
			System.out.println("Cannot add any more parties! Please try again in the next elections.");

			return false;
		}
		System.out.println("Enter party's name:");
		partyName = scan.nextLine();
		if (parties.get(partyName) != null) {
			System.out.println("Looks like this party is already in the lists.");

			return true;
		}

		System.out.println("Enter party's association (Left, Center, or Right):");
		wing = Party.PartyAssociation.valueOf(scan.next());
		scan.nextLine();
		System.out.println("Enter year, month and day of the party's foundation, in that order:");
		foundationDate = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
		scan.nextLine();

		return parties.add(new Party(partyName, wing, foundationDate));
	}

	// When entering 4 in the menu
	public static boolean addCandidateToParty(Scanner scan, VoterRegistry voterRegistry, PartyRegistry parties) {
		Candidate candidate;
		int addedCandidateID;
		String partyName;

		System.out.println("Enter candidate's ID and party's name, in that order:");
		addedCandidateID = scan.nextInt();
		scan.nextLine();
		partyName = scan.nextLine();

		if (voterRegistry.get(addedCandidateID) == null) {
			System.out.println("Candidate not registered, so he can't be added");
			return false;
		}

		voterRegistry.updateCitizenToCandidate(voterRegistry.get(addedCandidateID));
		candidate = (Candidate) voterRegistry.get(addedCandidateID);
		parties.get(partyName).addCandidate(candidate);
		System.out.println("Candidate successfully added to the party!");
		
		return true;
	}

	// When entering 8 in the menu
	public static int vote(Scanner scan, PartyRegistry candidateParties, Citizen citizen) {
		int voterChoice;
		
		System.out.println(String.format("Greetings, %s. Do you want to vote? (Y/N)", citizen.getFullName()));
		if (scan.next().toUpperCase() == "Y") {
			for (int i = 0; i < candidateParties.getPartyCount(); i++)
				System.out.println(String.format("%d = %s", i + 1, candidateParties.get(i).getName()));
			System.out.println("Enter the code for your chosen party:");
			voterChoice = scan.nextInt();
			scan.nextLine();

			return voterChoice;
		}

		return -1;
	}

	// When entering 9 in the menu
	public static void showResults(BallotRegistry ballots, PartyRegistry parties) {
		System.out.println(ballots.showResults(parties));
	}
}