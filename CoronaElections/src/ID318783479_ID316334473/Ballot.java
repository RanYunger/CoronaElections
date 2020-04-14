package ID318783479_ID316334473;

import java.time.YearMonth;
import java.util.ArrayList;

public class Ballot {
	// Constants

	// Fields
	protected static int IDGenerator = 0;

	protected int ID;
	protected String address;
	protected ArrayList<Citizen> voterRegistry;
	protected YearMonth votingDate;
	protected double votersPercentage;
	protected int[] results;

	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}

	private void setID(int ID) throws Exception {
		if (ID < 0)
			throw new Exception("Ballot's ID must be a positive number.");
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

	public ArrayList<Citizen> getVoterRegistry() {
		return voterRegistry;
	}

	private void setVoterRegistry(ArrayList<Citizen> voterRegistry) {
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

	public int[] getResults() {
		return results;
	}

	public void setResults(int[] results) {
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
			setVoterRegistry(new ArrayList<Citizen>());
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
			if (voterRegistry.add(citizen)) {
				if (citizen.getAssociatedBallot() != this)
					citizen.setAssociatedBallot(this);

				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return false;
	}

	public int[] vote(ArrayList<Party> candidateParties) {
		int voterCount = voterRegistry.size(), currVoterChoice;
		int numOfVoters = 0;

		setResults(new int[candidateParties.size()]);
		for (int i = 0; i < voterCount; i++) {
			currVoterChoice = UIHandler.vote(candidateParties, voterRegistry.get(i));
			if (currVoterChoice != -1) {
				results[currVoterChoice - 1]++;
				numOfVoters++;
			}
		}
		setVotersPercentage(numOfVoters);

		return results;
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
		return String.format("Ballot [ID: %d | Address: %s]\n%s", ID, address,
				Elections.showVoterRegistry(voterRegistry, votingDate));
	}
}