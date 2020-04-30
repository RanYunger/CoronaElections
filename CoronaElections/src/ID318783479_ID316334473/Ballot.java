package ID318783479_ID316334473;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.TreeMap;

//TODO: check if TreeMap usage is allowed
public class Ballot implements Comparable<Ballot> {
	// Fields
	protected static int IDGenerator = 0;

	protected int ID;
	protected String address;
	protected Set<Citizen> voterRegistry;
	protected YearMonth votingDate;
	protected double votersPercentage;
	protected ArrayList<Integer> results;
	protected TreeMap<String, Integer> partyResults;
	protected int capacity;

	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}

	private void setID(int ID) {
		this.ID = ID;
	}

	public String getAddress() {
		return address;
	}

	private void setAddress(String address) throws Exception {
		if (address.trim().length() == 0)
			throw new Exception("Ballot's address must contain at least 1 letter.");
		this.address = address;
	}

	public Set<Citizen> getVoterRegistry() {
		return voterRegistry;
	}

	private void setVoterRegistry(Set<Citizen> voterRegistry) {
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
	public Ballot(YearMonth votingDate) {
		this("<UNKNOWN>", votingDate);
	}

	public Ballot(String address, YearMonth votingDate) {
		try {
			setID(IDGenerator++);
			setAddress(address);
			setVoterRegistry(new Set<Citizen>());
			setVotingDate(votingDate);
			setVotersPercentage(0);
			setResults(null);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
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

	public boolean addVoter(Citizen citizen) {
		try {
			voterRegistry.add(citizen);
			if (citizen.getAssociatedBallot() != this)
				citizen.setAssociatedBallot(this);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public ArrayList<Integer> vote(ArrayList<Party> candidateParties) {

		int currVoterChoice, numOfVoters = 0;

		setResults(new ArrayList<Integer>(candidateParties.size()));
		for (int i = 0; i < results.size(); i++) {
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

	@Override
	public int compareTo(Ballot other) {
		return Integer.compare(ID, other.ID);
	}

	@Override
	public boolean equals(Object obj) {
		Ballot other;

		if (this == obj)
			return true;
		if (!(obj instanceof Ballot))
			return false;

		other = (Ballot) obj;

		return ID == other.ID;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Ballot [ID: %d | Address: %s]\n");
		if (voterRegistry.size() == 0)
			return sb.append("Nothing else to See here..").toString();

		sb.append("Date of voting: " + votingDate + "\nVoter list:");
		for (int i = 0; i < voterRegistry.size(); i++) {
			voterRegistry.get(i).calculateAge(votingDate);
			sb.append("\n\t" + voterRegistry.get(i).toString());
		}

		return sb.toString();
	}
}