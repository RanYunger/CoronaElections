package ID318783479_ID316334473.Models;

import java.time.LocalDate;
import java.util.ArrayList;

import ID318783479_ID316334473.UIHandler;

public class BallotModel<E extends CitizenModel> {
	// Fields
	private static int IDGenerator = 0;

	private int ID;
	private String ballotType;
	private String address;
	private ArrayList<CitizenModel> voterRegistry;
	private LocalDate votingDate;
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
			throw new Exception("BallotModel's address must contain at least 1 letter.");
		this.address = address;
	}

	public ArrayList<CitizenModel> getVoterRegistry() {
		return voterRegistry;
	}

	private void setVoterRegistry(ArrayList<CitizenModel> voterRegistry) {
		this.voterRegistry = voterRegistry;
	}

	public LocalDate getVotingDate() {
		return votingDate;
	}

	private void setVotingDate(LocalDate votingDate) throws Exception {
		if (votingDate.compareTo(LocalDate.now()) > 0)
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
	public BallotModel(String ballotType, LocalDate votingDate) {
		this(ballotType, "<UNKNOWN>", votingDate);
	}

	public BallotModel(String ballotType, String address, LocalDate votingDate) {
		try {
			setID(IDGenerator++);
			setBallotType(ballotType);
			setAddress(address);
			setVoterRegistry(new ArrayList<CitizenModel>());
			setVotingDate(votingDate);
			setVotersPercentage(0);
			setResults(null);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	// Methods
	public CitizenModel getCitizenModelByID(int CitizenModelID) {
		for (int i = 0; i < voterRegistry.size(); i++) {
			if (voterRegistry.get(i).getID() == CitizenModelID)
				return voterRegistry.get(i);
		}

		return null;
	}

	public boolean addVoter(CitizenModel voter) {
		try {
			// Validations
			if ((isCoronaBallot()) && (!voter.isIsolated())) {
				System.err.println("Cannot add a non-isolated voter to a Corona ballot.");

				return false;
			}
			if ((isMilitaryBallot()) && !(voter instanceof SoldierModel)) {
				System.err.println("Cannot add a non-soldier voter to a military ballot.");

				return false;
			}

			voterRegistry.add((E) voter);
			if (voter.getAssociatedBallot() != this)
				voter.setAssociatedBallot((BallotModel<? extends CitizenModel>) this);

			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());

			return false;
		}
	}

	public ArrayList<Integer> vote(ArrayList<PartyModel> candidateParties) {
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
		return ballotType.contains("SoldierModel");
	}

	@Override
	public boolean equals(Object obj) {
		BallotModel<E> other;

		if (this == obj)
			return true;
		if (!(obj instanceof BallotModel))
			return false;

		other = (BallotModel<E>) obj;

		return ID == other.ID;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(
				String.format("BallotModel [ID: %d | Address: %s | Type: %s]\n", ID, address, ballotType));

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