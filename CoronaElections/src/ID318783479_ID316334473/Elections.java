package ID318783479_ID316334473;

import java.time.LocalDate;
import java.time.YearMonth;

public class Elections {
	// Constants

	// Fields
	private YearMonth votingDate;
	private VoterRegistry voterRegistry;
	private PartyRegistry partyRegistry;
	private BallotRegistry ballotRegistry;
	
	// Properties (Getters and Setters)
	public YearMonth getVotingDate() {
		return votingDate;
	}
	
	private void setVotingDate(YearMonth votingDate) {
		this.votingDate = votingDate;
	}

	public VoterRegistry getVoterRegistry() {
		return voterRegistry;
	}
	
	private void setVoterRegistry(VoterRegistry voterRegistry) {
		this.voterRegistry = voterRegistry;
	}
	
	public PartyRegistry getPartyRegistry() {
		return partyRegistry;
	}
	
	private void setPartyRegistry(PartyRegistry partyRegistry) {
		this.partyRegistry = partyRegistry;
	}
	
	public BallotRegistry getBallotRegistry() {
		return ballotRegistry;
	}
	
	private void setBallotRegistry(BallotRegistry ballotRegistry) {
		this.ballotRegistry = ballotRegistry;
	}
		
	// Constructors
	public Elections(YearMonth votingDate) {
		setVotingDate(votingDate);
		setVoterRegistry(new VoterRegistry(votingDate));
		setPartyRegistry(new PartyRegistry());
		setBallotRegistry(new BallotRegistry());
		
		init();
	}

	// Methods
	private void init() {
		System.out.println("========== INITIATION (4 ballots, 4 parties, 13 citizens (8 candidates) ==========");
		
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
		voterRegistry.add(new Candidate(678901234, "Benjamin Netanyahu", 1949, ballotRegistry.get(0), true, false, partyRegistry.get(0), 1));
		voterRegistry.add(new Candidate(789012345, "Miri Regev", 1965, ballotRegistry.get(3), true, false, partyRegistry.get(0), 5));
		voterRegistry.add(new Candidate(890123456, "Benny Gantz", 1959, ballotRegistry.get(3), true, true, partyRegistry.get(1), 1));
		voterRegistry.add(new Candidate(901234567, "Yair Lapid", 1963, ballotRegistry.get(3), true, true, partyRegistry.get(1), 2));
		voterRegistry.add(new Candidate(901234568, "Avigdor Lieberman", 1958, ballotRegistry.get(0), true, true, partyRegistry.get(2), 1));
		voterRegistry.add(new Candidate(901234566, "Oded Forer", 1977, ballotRegistry.get(0), false, true, partyRegistry.get(2), 1));
		voterRegistry.add(new Candidate(901234569, "Tamar Zandberg", 1976, ballotRegistry.get(0), false, false, partyRegistry.get(3), 1));
		voterRegistry.add(new Candidate(901234565, "Nitzan Horowitz", 1965, ballotRegistry.get(1), false, false, partyRegistry.get(3), 2));
		
		// Associates candidates to their parties
		partyRegistry.get("Halikud").addCandidate((Candidate) voterRegistry.getByID(678901234));
		partyRegistry.get("Halikud").addCandidate((Candidate) voterRegistry.getByID(789012345));
		partyRegistry.get("Blue and White").addCandidate((Candidate) voterRegistry.getByID(890123456));
		partyRegistry.get("Blue and White").addCandidate((Candidate) voterRegistry.getByID(901234567));
		partyRegistry.get("Israel is Our Home").addCandidate((Candidate) voterRegistry.getByID(901234568));
		partyRegistry.get("Israel is Our Home").addCandidate((Candidate) voterRegistry.getByID(901234566));
		partyRegistry.get("Israeli Labor Party").addCandidate((Candidate) voterRegistry.getByID(901234569));
		partyRegistry.get("Israeli Labor Party").addCandidate((Candidate) voterRegistry.getByID(901234565));
		
		System.out.println("========== INITIATION COMPLETE ==========");
	}
	
	public void runElections() {
		ballotRegistry.vote(partyRegistry);
	}
	
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