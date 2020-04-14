package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class UIHandler {
	// Constants
	public static final int MAX_ARRAY_SIZE = 100;

	// Methods
	public static int showMenu(boolean electionsOccurred) {
		int selection;

		try {
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
			selection = Elections.scanner.nextInt();
			if (electionsOccurred) { // don't allow options 1, 2, 3, 4, or 8 if the elections have taken place
				if ((1 <= selection) && (selection <= 4))
					throw new Exception("Updating registries is unavailable once the election already occoured.");
				else if (selection == 8)
					throw new Exception("Election process already occoured.");
			} else if (selection == 9)
				throw new Exception("Seeing elections results is available once the process is complete.");
			return selection;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return -1;
	}

	public static int vote(ArrayList<Party> candidateParties, Citizen citizen) {
		int voterChoice;
		String selection;

		try {
			// Validations
			if ((citizen.isIsolated()) && (citizen.associatedBallot instanceof CoronaBallot)
					&& (!citizen.iswearingSuit()))
				throw new Exception(
						String.format("Greetings, %s. You can't vote without a suit, so we have to turn you back.",
								citizen.fullName));

			System.out.println(String.format("Greetings, %s. Do you want to vote? (Y/N)", citizen.fullName));
			while (true) {
				selection = Elections.scanner.next();
				if (selection.equalsIgnoreCase("Y")) {
					for (int i = 0; i < candidateParties.size(); i++)
						System.out.println(String.format("%d = %s", i + 1, candidateParties.get(i).getName()));
					while (true) {
						System.out.println("Enter the code for your chosen party:");
						voterChoice = Elections.scanner.nextInt();
						if ((1 <= voterChoice) && (voterChoice <= candidateParties.size())) {
							selection = Elections.scanner.nextLine();

							return voterChoice;
						}
						System.out.println("Invalid choice.");
					}
				} else if (!selection.equalsIgnoreCase("N")) {
					Elections.scanner.nextLine();
					System.out.println("Please enter a valid choice - Y to vote, N to skip voting.");
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return -1;
	}

	// When entering 1
	public static boolean addNewBallotToElections(YearMonth votingDate, ArrayList<Ballot> ballots) {
		String address;

		try {
			// Validations
			if (ballots.size() == MAX_ARRAY_SIZE)
				throw new Exception("Cannot add any more ballots!\nplease try again in the next elections.");
			System.out.println("Enter ballot's address: ");
			address = Elections.scanner.nextLine();
			if (address.trim().length() == 0)
				throw new Exception("Ballot's address must contain at least 1 letter.");

			while (true) {
				System.out.println(
						"For a regular ballot, enter 1\nFor a military ballot, enter 2\nFor a corona ballot, enter 3");
				switch (Elections.scanner.nextInt()) {
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
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return false;
	}

	// When entering 2
	public static boolean addCitizen(ArrayList<Citizen> voterRegistry, YearMonth votingDate,
			ArrayList<Ballot> ballotRegistry) {
		Citizen citizen;
		int citizenID, yearOfBirth, associatedBallotID;
		boolean isIsolated, isWearingSuit = false;
		String fullName;

		try {
			// Validations
			System.out.println("Enter voter's ID:");
			citizenID = Elections.scanner.nextInt();
			Elections.scanner.nextLine();
			if ((citizenID < Citizen.MIN_ID_VALUE) || (citizenID > Citizen.MAX_ID_VALUE))
				throw new Exception("Citizen's ID must contain exactly 9 digits.");
			if (Elections.getCitizenByID(voterRegistry, citizenID) != null)
				throw new Exception("This Citizen is already in the registry, try something else.\n");
			System.out.println("Enter year of birth:");
			yearOfBirth = Elections.scanner.nextInt();
			Elections.scanner.nextLine();
			if (yearOfBirth > votingDate.getYear())
				throw new Exception("Time paradox prevented.\n");
			if ((votingDate.getYear() - yearOfBirth) < Citizen.VOTER_AGE)
				throw new Exception("Sorry, this citizen is too young to vote, try something else.\n");
			System.out.println("Enter voter's name:");
			fullName = Elections.scanner.nextLine();
			if (fullName.trim().length() == 0)
				throw new Exception("Citizen's name must contain at least 1 letter.");
			System.out.println("Enter associated Ballot ID:");
			associatedBallotID = Elections.scanner.nextInt();
			Elections.scanner.nextLine();
			if ((associatedBallotID < 0) || (ballotRegistry.size() < associatedBallotID))
				throw new Exception(String.format("Ballot not in registry, there are only %d ballots, with IDs 0-%d.\n",
						ballotRegistry.size(), ballotRegistry.size() - 1));
			if (Elections.getBallotByID(ballotRegistry, associatedBallotID) == null)
				throw new Exception("Citizen must be associated to a ballot.");

			System.out.println("Is the voter in isolation (Y/N)?");
			isIsolated = Elections.scanner.next().toUpperCase() == "Y";
			Elections.scanner.nextLine();
			if (isIsolated) {
				System.out.println("Is the voter wearing a protective suit (Y/N)?");
				isWearingSuit = Elections.scanner.next().toUpperCase() == "Y";
				Elections.scanner.nextLine();
			}

			citizen = new Citizen(citizenID, fullName, yearOfBirth, ballotRegistry.get(associatedBallotID - 1),
					isIsolated, isWearingSuit);
			ballotRegistry.get(associatedBallotID).addVoter(citizen);
			voterRegistry.add(citizen);

			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return false;
	}

	// When entering 3
	public static boolean addPartyToElections(ArrayList<Party> partyRegistry) {
		String partyName;
		Party.PartyAssociation wing;
		LocalDate foundationDate;

		try {
			// Validations
			if (partyRegistry.size() == MAX_ARRAY_SIZE)
				throw new Exception("Cannot add any more parties! Please try again in the next elections.");
			System.out.println("Enter party's name:");
			partyName = Elections.scanner.nextLine();
			if (partyName.trim().length() == 0)
				throw new Exception("Party's name must contain at least 1 letter.");
			if (Elections.getPartyByName(partyRegistry, partyName) != null) {
				System.out.println("Looks like this party is already in the lists.");

				return true;
			}
			System.out.println("Enter party's association (Left, Center, or Right):");
			wing = Party.PartyAssociation.valueOf(Elections.scanner.next());
			Elections.scanner.nextLine();
			System.out.println("Enter year, month and day of the party's foundation, in that order:");
			foundationDate = LocalDate.of(Elections.scanner.nextInt(), Elections.scanner.nextInt(),
					Elections.scanner.nextInt());
			Elections.scanner.nextLine();
			if (foundationDate.compareTo(LocalDate.now()) > 0)
				throw new Exception("Time paradox prevented.");

			return partyRegistry.add(new Party(partyName, wing, foundationDate));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return false;
	}

	// When entering 4
	public static boolean addCandidateToAParty(ArrayList<Citizen> voterRegistry, ArrayList<Party> partyRegistry) {
		Candidate candidate;
		int addedCandidateID;
		String partyName;

		try {
			// Validations
			System.out.println("Enter candidate's ID");
			addedCandidateID = Elections.scanner.nextInt();
			Elections.scanner.nextLine();
			if ((addedCandidateID < Citizen.MIN_ID_VALUE) || (addedCandidateID > Citizen.MAX_ID_VALUE))
				throw new Exception("Citizen's ID must contain exactly 9 digits.");
			if (Elections.getCitizenByID(voterRegistry, addedCandidateID) == null)
				throw new Exception("Candidate not registered, so he can't be added.");
			System.out.println("Enter party's name");
			partyName = Elections.scanner.nextLine().trim();
			if (partyName.trim().length() == 0)
				throw new Exception("Party's name must contain at least 1 letter.");
			// TODO: FIX (can't find the party for some reason)
			if (Elections.getPartyByName(partyRegistry, partyName) == null)
				throw new Exception(String.format("There's no registered party going by the name %s.\n", partyName));

			Elections.updateCitizenToCandidate(voterRegistry,
					Elections.getCitizenByID(voterRegistry, addedCandidateID));
			candidate = (Candidate) Elections.getCitizenByID(voterRegistry, addedCandidateID);
			if (!Elections.getPartyByName(partyRegistry, partyName).addCandidate(candidate))
				throw new Exception(String.format("Sorry, candidate %s couldn't have been added to %s party.\n",
						candidate.getFullName(), partyName));

			System.out.printf("Candidate %s was added to %s party. He's ranked #%d.\n", candidate.getFullName(),
					partyName, candidate.getRank());

			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return false;
	}
}
