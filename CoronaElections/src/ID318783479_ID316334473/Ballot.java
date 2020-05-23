package ID318783479_ID316334473;

import java.time.YearMonth;
import java.util.ArrayList;

public class Ballot<E extends Citizen> {
	// Fields
	private static int IDGenerator = 0;

	private int ID;
	private String ballotType;
	private String address;
	private Set<E> voterRegistry;
	private YearMonth votingDate;
	private double votersPercentage;
	private ArrayList<Integer> results;

	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}

	private void setID(int ID) {
		this.ID = ID;
	}

	private void setBallotType(String ballotType) {
		this.ballotType = ballotType;
	}

	public String getAddress() {
		return address;
	}

	private void setAddress(String address) throws Exception {
		if (address.trim().length() == 0)
			throw new Exception("Ballot's address must contain at least 1 letter.");
		this.address = address;
	}

	public Set<E> getVoterRegistry() {
		return voterRegistry;
	}

	private void setVoterRegistry(Set<E> voterRegistry) {
		this.voterRegistry = voterRegistry;
	}

	public YearMonth getVotingDate() {
		return votingDate;
	}

	private void setVotingDate(YearMonth votingDate) throws Exception {
		if (votingDate.compareTo(YearMonth.now()) > 0)
			throw new Exception("Time paradox prevented.");
		this.votingDate = votingDate;
	}

	public double getVotersPercentage() {
		return votersPercentage;
	}

	private void setVotersPercentage(int numOfVoters) {
		this.votersPercentage = (voterRegistry.size() > 0) ? (100 * numOfVoters) / voterRegistry.size() : 0;
	}

	public ArrayList<Integer> getResults() {
		return results;
	}

	public void setResults(ArrayList<Integer> results) {
		this.results = results;
	}

	// Constructors
	public Ballot(String ballotType, YearMonth votingDate) {
		this(ballotType, "<UNKNOWN>", votingDate);
	}

	public Ballot(String ballotType, String address, YearMonth votingDate) {
		try {
			setID(IDGenerator++);
			setBallotType(ballotType);
			setAddress(address);
			setVoterRegistry(new Set<E>());
			setVotingDate(votingDate);
			setVotersPercentage(0);
			setResults(null);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	// Methods
	public Citizen getCitizenByID(int citizenID) {
		for (int i = 0; i < voterRegistry.size(); i++) {
			if (voterRegistry.get(i).getID() == citizenID)
				return voterRegistry.get(i);
		}

		return null;
	}

	public boolean addVoter(Citizen voter) {
		try {
			// Validations
			if ((isCoronaBallot()) && (!voter.isIsolated())) {
				System.err.println("Cannot add a non-isolated voter to a Corona ballot.");

				return false;
			}
			if ((isMilitaryBallot()) && !(voter instanceof Soldier)) {
				System.err.println("Cannot add a non-soldier voter to a military ballot.");

				return false;
			}

			voterRegistry.add((E) voter);
			if (voter.getAssociatedBallot() != this)
				voter.setAssociatedBallot((Ballot<? extends Citizen>) this);

			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());

			return false;
		}
	}

	public ArrayList<Integer> vote(ArrayList<Party> candidateParties) {
		int currVoterChoice, numOfVoters = 0;

		setResults(new ArrayList<Integer>());
		for (int i = 0; i < candidateParties.size(); i++) {
			results.add(0);
		}
		for (int i = 0; i < voterRegistry.size(); i++) {
			currVoterChoice = UIHandler.vote(candidateParties, voterRegistry.get(i));
			if (currVoterChoice != -1) {
				results.set(currVoterChoice - 1, results.get(currVoterChoice - 1) + 1);
				numOfVoters++;
			}
		}
		setVotersPercentage(numOfVoters);

		return results;
	}

	public boolean isCoronaBallot() {
		return ballotType.contains("Sick");
	}

	public boolean isMilitaryBallot() {
		return ballotType.contains("Soldier");
	}

	@Override
	public boolean equals(Object obj) {
		Ballot<E> other;

		if (this == obj)
			return true;
		if (!(obj instanceof Ballot))
			return false;

		other = (Ballot<E>) obj;

		return ID == other.ID;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(
				String.format("Ballot [ID: %d | Address: %s | Type: %s]\n", ID, address, ballotType));

		if (voterRegistry.size() == 0)
			return sb.append("Nothing else to See here..").toString();

		sb.append("\tDate of voting: " + votingDate + "\n\tVoter list:");
		for (int i = 0; i < voterRegistry.size(); i++) {
			voterRegistry.get(i).calculateAge(votingDate);
			sb.append("\n\t\t" + voterRegistry.get(i).toString());
		}

		return sb.toString();
	}
}