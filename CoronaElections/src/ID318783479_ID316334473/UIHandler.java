package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/*
 * Helper class which contains all UI methods, plus extra helpful
 * methods.
 * Most of the methods here will be changed when switching to GUI, so there will
 * be no need to change anything in the main method.
 */
public class UIHandler {

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
				throw new Exception("Seeing the elections's results will be available once the process is complete.");
			return selection;
		} catch (Exception e) {
			showExceptionMessage(e);
		}

		return -1;
	}

	// When entering 1
	public static boolean addNewBallot(YearMonth votingDate) {
		String address;
		boolean validInput = false;

		try {
			// Validations
			System.out.println("Enter ballot's address: ");
			address = Elections.scanner.nextLine();
			if (address.trim().length() == 0)
				throw new Exception("Ballot's address must contain at least 1 letter.");

			while (!validInput) {
				validInput = true;
				System.out.println("1 = Regular Ballot (Citizens / Candidates)");
				System.out.println("2 = Military Ballot (Soldiers)");
				System.out.println("3 = Corona Ballot (Citizens / Candidates / Soldiers)");
				switch (Elections.scanner.nextInt()) {
				case 1: {

					if (Elections.citizenBallots.add(new Ballot<Citizen>("Citizen", address, votingDate))) {
						System.out.println("Regular Ballot added to registry.");

						return true;
					}
				}
				case 2: {
					if (Elections.soldierBallots.add(new Ballot<Soldier>("Soldier", address, votingDate))) {
						System.out.println("Military Ballot added to registry.");

						return true;
					}
				}
				case 3:
					validInput = false;
					while (!validInput) {
						validInput = true;
						System.out.println("1 = Corona-Citizens Ballot");
						System.out.println("2 = Corona-Candidates Ballot");
						System.out.println("3 = Corona-Soldiers Ballot");
						switch (Elections.scanner.nextInt()) {
						case 1: {
							if (Elections.sickCitizenBallots
									.add(new Ballot<SickCitizen>("Sick Citizen", address, votingDate))) {
								System.out.println("Corona-Citizens Ballot added to registry.");

								return true;
							}
						}
						case 2: {
							if (Elections.sickCandidateBallots
									.add(new Ballot<SickCandidate>("Sick Candidate", address, votingDate))) {
								System.out.println("Corona-Candidates Ballot added to registry.");

								return true;
							}
						}
						case 3: {
							if (Elections.sickSoldierBallots
									.add(new Ballot<SickSoldier>("Sick Soldier", address, votingDate))) {
								System.out.println("Corona-Soldiers Ballot added to registry.");

								return true;
							}
						}
						default:
							validInput = false;
							System.out.println("Invalid input!");
							break;
						}
					}
					break;
				default:
					validInput = false;
					System.out.println("Invalid input!");
				}
			}
		} catch (Exception e) {
			showExceptionMessage(e);
		}
		return false;
	}

	// When entering 2
	public static boolean addNewCitizen(Set<Citizen> voterRegistry, YearMonth votingDate) {
		Citizen citizen;
		Ballot<? extends Citizen> associatedBallot;
		int citizenID, yearOfBirth, daysOfSickness = 0, associatedBallotID, voterAge;
		boolean isCarryingWeapon = false, isIsolated, isWearingSuit = false;
		String fullName, outputMessage = "";

		try {
			// Validations
			System.out.println("Enter voter's ID:");
			citizenID = Elections.scanner.nextInt();
			Elections.scanner.nextLine();
			if (String.valueOf(citizenID).length() != 9)
				throw new Exception("Citizen's ID must contain exactly 9 digits.");
			if (Elections.getCitizenByID(voterRegistry, citizenID) != null)
				throw new Exception("This Citizen is already in the registry, try something else.\n");

			System.out.println("Enter year of birth:");
			yearOfBirth = Elections.scanner.nextInt();
			Elections.scanner.nextLine();
			if (yearOfBirth > votingDate.getYear())
				throw new Exception("Time paradox prevented - I mean, come on");
			voterAge = (votingDate.getYear() - yearOfBirth);
			if (voterAge < Citizen.VOTER_AGE)
				throw new Exception("Sorry, this citizen is too young to vote, try something else.\n");

			System.out.println("Enter voter's name:");
			fullName = Elections.scanner.nextLine();
			if (fullName.trim().length() == 0)
				throw new Exception("Citizen's name must contain at least 1 letter.");
			System.out.println("Enter associated Ballot ID (starting 0):");
			associatedBallotID = Elections.scanner.nextInt();
			associatedBallot = Elections.getBallotByID(Elections.getAllBallots(), associatedBallotID);
			if (associatedBallot == null)
				throw new Exception(String.format("Ballot #%d not found.", associatedBallotID));

			if (associatedBallot.isCoronaBallot()) {
				isIsolated = true;
				System.out.println("This is a Corona Ballot:");
				System.out.println("Is the voter wearing a protective suit (Y/N)?");
				isWearingSuit = getValidYesNoAnswer();
				Elections.scanner.nextLine();
				System.out.println("Enter amount of days the voter has been sick:");
				daysOfSickness = Elections.scanner.nextInt();
				Elections.scanner.nextLine();
				if (daysOfSickness < 1)
					throw new Exception("An Isolated Citizen must've been sick for at least 1 day.");

				if (voterAge < Citizen.SOLDIER_AGE) {
					System.out.println("This is a Military Ballot:");
					System.out.println("Is the soldier carrying a weapon (Y/N)?");
					isCarryingWeapon = getValidYesNoAnswer();
					Elections.scanner.nextLine();
					citizen = new SickSoldier(citizenID, fullName, yearOfBirth, daysOfSickness, associatedBallot,
							isIsolated, isWearingSuit, isCarryingWeapon);
					outputMessage = String.format("Sick Soldier named '%s' added to Ballot #%d.", fullName,
							associatedBallotID);
				} else {
					citizen = new SickCitizen(citizenID, fullName, yearOfBirth, daysOfSickness, associatedBallot,
							isIsolated, isWearingSuit);
					outputMessage = String.format("Sick Citizen named '%s' added to Ballot #%d.", fullName,
							associatedBallotID);
				}
			} else {
				isIsolated = false;
				citizen = new Citizen(citizenID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated,
						isWearingSuit);
				outputMessage = String.format("Citizen named '%s' added to Ballot #%d.", fullName, associatedBallotID);
			}

			System.out.println(outputMessage);
			associatedBallot.addVoter(citizen);
			voterRegistry.add(citizen);

			return true;
		} catch (Exception e) {
			showExceptionMessage(e);
		}
		return false;
	}

	// When entering 3
	public static boolean addNewParty(ArrayList<Party> parties) {
		String partyName;
		Party.PartyAssociation wing;
		LocalDate foundationDate;

		try {
			// Validations
			System.out.println("Enter party's name:");
			partyName = Elections.scanner.nextLine();
			if (partyName.trim().length() == 0)
				throw new Exception("Party's name must contain at least 1 letter.");
			if (Elections.getPartyByName(parties, partyName) != null) {
				System.out.println("This party already exists.");

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
				throw new Exception("Time paradox prevented - I mean, come on");

			if (parties.add(new Party(partyName, wing, foundationDate))) {
				System.out.println(String.format("Party '%s' added to registry.", partyName));

				return true;
			}
		} catch (Exception e) {
			showExceptionMessage(e);
		}

		return false;
	}

	// When entering 4
	public static boolean addCandidateToAParty(Set<Citizen> voterRegistry, ArrayList<Party> parties) {
		Citizen citizen;
		Candidate candidate;
		Party party;
		int addedCandidateID;
		String partyName;

		try {
			// Validations
			System.out.println("Enter candidate's ID");
			addedCandidateID = Elections.scanner.nextInt();
			Elections.scanner.nextLine();
			if (String.valueOf(addedCandidateID).length() != 9)
				throw new Exception("Citizen's ID must contain exactly 9 digits.");
			citizen = Elections.getCitizenByID(voterRegistry, addedCandidateID);
			if (citizen == null)
				throw new Exception("Candidate not registered, so he can't be added.");
			if (citizen instanceof Soldier)
				throw new Exception("Soldier cannot be a candidate in party.");
			System.out.println("Enter party's name");
			partyName = Elections.scanner.nextLine().trim();
			if (partyName.trim().length() == 0)
				throw new Exception("Party's name must contain at least 1 letter.");
			party = Elections.getPartyByName(parties, partyName);
			if (party == null)
				throw new Exception(String.format("There's no registered party going by the name %s.\n", partyName));

			Elections.updateCitizenToCandidate(voterRegistry,
					Elections.getCitizenByID(voterRegistry, addedCandidateID));
			candidate = (Candidate) Elections.getCitizenByID(voterRegistry, addedCandidateID);
			if (!party.addCandidate(candidate))
				throw new Exception(String.format("Sorry, candidate %s couldn't have been added to %s party.\n",
						candidate.getFullName(), partyName));
			System.out.println(
					String.format("Candidate %s was added to '%s' party.", candidate.getFullName(), partyName));

			return true;
		} catch (Exception e) {
			showExceptionMessage(e);
			return false;
		}
	}

	// When entering 5
	public static String showBallotRegistry(ArrayList<Ballot<?>> ballots) {
		if (ballots.size() == 0)
			return "Nothing to See here..";

		StringBuilder sb = new StringBuilder();
		for (Ballot<? extends Citizen> ballot : ballots)
			sb.append("\n" + ballot.toString());
		sb.deleteCharAt(0); // removes first /n

		return sb.toString();
	}

	// When entering 6
	public static String showVoterRegistry(Set<? extends Citizen> voterRegistry, YearMonth votingDate) {
		if (voterRegistry.size() == 0)
			return "Nothing to See here..";

		StringBuilder sb = new StringBuilder("Date of voting: " + votingDate + "\nVoter list:");
		for (int i = 0; i < voterRegistry.size(); i++) {
			voterRegistry.get(i).calculateAge(votingDate);
			sb.append("\n\t" + voterRegistry.get(i).toString());
		}

		return sb.toString();
	}

	// When entering 7
	public static String showPartyRegistry(ArrayList<Party> parties) {
		if (parties.size() == 0)
			return "Nothing to See here..";

		StringBuilder sb = new StringBuilder();
		for (Party party : parties)
			sb.append("\n" + party.toString());
		sb.deleteCharAt(0); // removes first /n

		return sb.toString();
	}

	// When entering 8
	public static void runElections(ArrayList<ArrayList<Integer>> resultsByBallot, ArrayList<Party> parties) {
		runElections(Elections.getAllBallots(), resultsByBallot, parties);
	}

	public static void runElections(ArrayList<Ballot<? extends Citizen>> ballots,
			ArrayList<ArrayList<Integer>> resultsByBallot, ArrayList<Party> parties) {
		for (Ballot<? extends Citizen> ballot : ballots)
			resultsByBallot.add(ballot.vote(parties));
	}

	public static int vote(ArrayList<Party> parties, Citizen citizen) {
		int voterChoice;
		String selection;

		try {
			// Validations
			if ((citizen.isIsolated()) && (citizen.getAssociatedBallot().isCoronaBallot())
					&& (!citizen.iswearingSuit()))
				throw new Exception(
						String.format("Greetings, %s. You can't vote without a suit, so we have to turn you back.",
								citizen.getFullName()));

			System.out.println(String.format("Greetings, %s. Do you want to vote? (Y/N)", citizen.getFullName()));
			if (getValidYesNoAnswer()) {
				for (int i = 0; i < parties.size(); i++)
					System.out.println(String.format("%d = %s", i + 1, parties.get(i).getName()));
				while (true) {
					System.out.println("Enter the code for your chosen party:");
					voterChoice = Elections.scanner.nextInt();
					if ((1 <= voterChoice) && (voterChoice <= parties.size())) {
						selection = Elections.scanner.nextLine();

						return voterChoice;
					}
					System.out.println("Invalid choice.");
				}
			}
		} catch (Exception e) {
			showExceptionMessage(e);
		}
		return -1;
	}

	// When entering 9
	public static void showElectionsResults(String finalResultsString, ArrayList<ArrayList<Integer>> resultsByBallot,
			ArrayList<Party> parties) {
		if (finalResultsString.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			ArrayList<Ballot<? extends Citizen>> allBallots = Elections.getAllBallots();
			int ballotCount = resultsByBallot.size();
			int partyCount = resultsByBallot.get(0).size();
			TreeMap<String, Integer> partyVotes = new TreeMap<>();

			for (Party party : parties) {
				partyVotes.put(party.getName(), 0);
			}

			for (int ballot = 0; ballot < ballotCount; ballot++) {
				sb.append(String.format("\nBallot #%d (%.2f %% voted):\n", ballot,
						allBallots.get(ballot).getVotersPercentage()));
				for (int party = 0; party < partyCount; party++) {
					String partyName = parties.get(party).getName();
					int resultInBallot = resultsByBallot.get(ballot).get(party);
					sb.append(String.format("\t%s: %d\t", partyName, resultInBallot));
					partyVotes.put(partyName, partyVotes.get(partyName) + resultInBallot);
				}
			}
			sb.append("\nFinal Results:");
			for (Map.Entry<String, Integer> entry : partyVotes.entrySet())
				sb.append(String.format("\n\t%s : %d", entry.getKey(), entry.getValue()));
			System.out.println(sb.toString());
			finalResultsString = sb.toString();
		}
		System.out.println(finalResultsString);
	}

	// When entering 10
	public static void showExitMessage() {
		System.out.println("Thank you for your vote (or not). See you again in 3 months!");
	}

	private static void showExceptionMessage(Exception e) {
		System.out.println(e.getMessage());
	}

	private static boolean getValidYesNoAnswer() {
		while (true) {
			String answer = Elections.scanner.nextLine();
			if (answer.equalsIgnoreCase("Y"))
				return true;

			if (answer.equalsIgnoreCase("N"))
				return false;

			System.out.println("please enter a valid answer (Y/N)");
		}
	}
}