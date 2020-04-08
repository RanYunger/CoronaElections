package ID318783479_ID316334473;

import java.util.Arrays;

public class BallotRegistry {
	// Constants

	// Fields
	private Ballot[] ballotRegistry;
	private int ballotCount;

	// Properties (Getters and Setters)
	public Ballot[] getBallotRegistry() {
		return ballotRegistry;
	}

	private void setBallotRegistry(Ballot[] ballotRegistry) {
		this.ballotRegistry = ballotRegistry;
	}

	public int getBallotCount() {
		return ballotCount;
	}

	private void setBallotCount(int ballotCount) {
		this.ballotCount = ballotCount;
	}

	// Constructors
	public BallotRegistry() {
		setBallotRegistry(new Ballot[Program.MAX_ARRAY_SIZE]);
		setBallotCount(0);
	}

	// Methods
	public Ballot get(int ballotID) {
		return (indexOf(ballotID) == -1) ? null : ballotRegistry[indexOf(ballotID)];
	}

	public int indexOf(int ballotID) {
		return indexOf(0, ballotCount - 1, ballotID);
	}

	private int indexOf(int leftIndex, int rightIndex, int ballotID) {
		int mid;

		if (rightIndex >= leftIndex) {
			mid = leftIndex + (rightIndex - leftIndex) / 2;
			if (ballotRegistry[mid].getID() == ballotID)
				return mid;
			if (ballotRegistry[mid].getID() > ballotID)
				return indexOf(leftIndex, mid - 1, ballotID);

			return indexOf(mid + 1, rightIndex, ballotID);
		}

		return -1;
	}

	public boolean add(Ballot ballot) {
		int i;

		// Validations
		if (ballotCount == Program.MAX_ARRAY_SIZE) {
			System.out.println(String.format("Cannot add more ballots (%d ballots max)", Program.MAX_ARRAY_SIZE));
			
			return false;
		}
		if (ballotCount == 0) {
			ballotRegistry[ballotCount] = ballot;
			ballotCount++;
			System.out.println("Ballot successfully added to the ballot registry.");
			
			return true;
		}
		if (indexOf(ballot.getID()) != -1) {
			System.out.println("Ballot already exists in the ballot registry.");
			
			return false;
		}

		i = ballotCount;
		while ((i > 0) && (ballotRegistry[i - 1].getID() > ballot.getID())) {
			ballotRegistry[i] = ballotRegistry[i - 1];
			i--;
		}
		ballotRegistry[i] = ballot;
		ballotCount++;
		System.out.println("Ballot successfully added to the ballot registry.");
		
		return true;
	}

	public boolean remove(int ballotID) {
		int ballotOffset = indexOf(ballotID), i;

		// Validations
		if (ballotCount == 0) {
			System.out.println("Cannot remove any ballot (non-existing).");
			
			return false;
		}
		if(ballotOffset != -1) {
			System.out.println("Ballot already doesn't exist in the ballot registry.");
			
			return false;
		}
			
		ballotRegistry[ballotOffset] = null;
		i = ballotOffset;
		while ((i > 0) && (ballotRegistry[i - 1].getID() < ballotRegistry[i].getID())) {
			ballotRegistry[i] = ballotRegistry[i + 1];
			i++;
		}
		ballotCount--;
		System.out.println("Ballot successfully removed from the ballot registry.");

		return true;
	}

	public void vote(PartyRegistry parties) {
		for (int i = 0; i < ballotCount; i++)
			ballotRegistry[i].vote(parties);
	}

	public int countVotes(int partyOffset) {
		int votes = 0;

		for (Ballot ballot : ballotRegistry)
			votes += ballot.getResults()[partyOffset];

		return votes;
	}

	public String showResults(PartyRegistry parties) {
		StringBuilder sb = new StringBuilder();
		int[] currBallotResults;
		int[] finalResults = new int[parties.getPartyCount()];

		for (int ballotOffset = 0; ballotOffset < ballotCount; ballotOffset++) {
			currBallotResults = ballotRegistry[ballotOffset].getResults();
			sb.append(String.format("Ballot #%d:\n", ballotRegistry[ballotOffset].getID()));
			for (int i = 0; i < currBallotResults.length; i++) {
				sb.append(String.format("%s: %d\t", parties.get(i).getName(), currBallotResults[i]));
				finalResults[i] += currBallotResults[i];
			}
			sb.append("voter percentage: " + ballotRegistry[ballotOffset].getVotersPercentage() + "\n");
		}

		Elections.sortParties(finalResults.clone(), parties);
		Elections.sortResults(finalResults);

		sb.append("Final Results:\n");
		for (int i = 0; i < finalResults.length; i++)
			sb.append(String.format("%s : %d\n", parties.get(i).getName(), finalResults[i]));
		sb.deleteCharAt(sb.length() - 1); // Removes last \n

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		BallotRegistry other;

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		other = (BallotRegistry) obj;

		return (ballotCount == other.ballotCount) && (Arrays.equals(ballotRegistry, other.ballotRegistry));
	}

	@Override
	public String toString() {
		StringBuilder sb;

		if (ballotCount == 0)
			return "Nothing to See here..";

		sb = new StringBuilder();
		for (int i = 0; i < ballotCount; i++)
			sb.append(ballotRegistry[i].toString() + "\n");			
		sb.deleteCharAt(sb.length() - 1); // Removes last \n

		return sb.toString();
	}
}