package ID318783479_ID316334473;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.Objects;

public class Ballot {
	// Constants

	// Fields
	protected static int IDGenerator = 0;

	protected int ID;
	protected String address;
	protected VoterRegistry voterRegistry;
	protected double votersPercentage;
	protected int[] results;

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

	private void setAddress(String address) {
		this.address = address;
	}
	
	public VoterRegistry getVoterRegistry() {
		return voterRegistry;
	}

	private void setVoterRegistry(VoterRegistry voterRegistry) {
		this.voterRegistry = voterRegistry;
	}

	public double getVotersPercentage() {
		return votersPercentage;
	}

	protected void setVotersPercentage(int numOfVoters) {
		this.votersPercentage = (voterRegistry.getVoterCount() > 0) ? 100 * numOfVoters / voterRegistry.getVoterCount()	: 0;
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
		setID(IDGenerator++);
		setAddress(address);
		setVoterRegistry(new VoterRegistry(votingDate));
		setVotersPercentage(0);
		setResults(null);
	}

	// Methods
	public Citizen getCitizenByID(int citizenID) {
		return voterRegistry.getByID(citizenID);
	}

	public boolean addVoter(Citizen citizen) {
		if (voterRegistry.add(citizen)) {
			if (citizen.getAssociatedBallot() != this)
				citizen.setAssociatedBallot(this);
			
			return true;
		}	

		return false;
	}

	public boolean removeVoter(int citizenID) {
		Citizen citizen = getCitizenByID(citizenID);

		if (citizen == null)
			return false;
		if (citizen.getAssociatedBallot().getID() == ID) {
			if (voterRegistry.remove(citizenID)) {
				citizen.setAssociatedBallot(null);
				return true;
			}
		}

		return false;
	}

	public int[] vote(PartyRegistry candidateParties) {
		int voterCount = voterRegistry.getVoterCount(), currVoterChoice;
		int numOfVoters = 0;
		
		setResults(new int[candidateParties.getPartyCount()]);
		for (int i = 0; i < voterCount; i++) {
			currVoterChoice = Program.vote(candidateParties, voterRegistry.get(i));
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
		
		return (ID == other.ID) && (Objects.equals(address, other.address)) && (Arrays.equals(results, other.results))
				&& (Objects.equals(voterRegistry, other.voterRegistry))
				&& (Double.doubleToLongBits(votersPercentage) == Double.doubleToLongBits(other.votersPercentage));
	}

	@Override
	public String toString() {
		return String.format("Ballot [ID: %d | Address: %s]\n%s", ID, address, voterRegistry.toString());
	}
}