package ID318783479_ID316334473;

import java.util.Arrays;
import java.util.Objects;

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
		setBallotRegistry(new Ballot[UIHandler.MAX_ARRAY_SIZE]);
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
		if (ballotCount == UIHandler.MAX_ARRAY_SIZE) {
			return false;
		}
		if (ballotCount == 0) {
			ballotRegistry[ballotCount] = ballot;
			ballotCount++;
			return true;
		}
		if (indexOf(ballot.getID()) != -1) {
			return false;
		}

		i = ballotCount;
		while ((i > 0) && (ballotRegistry[i - 1].getID() > ballot.getID())) {
			ballotRegistry[i] = ballotRegistry[i - 1];
			i--;
		}
		ballotRegistry[i] = ballot;
		ballotCount++;
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

		for (int i = 0; i < ballotCount; i++) {
			currBallotResults = ballotRegistry[i].getResults();
			sb.append(String.format("Ballot #%d:\n", ballotRegistry[i].getID()));
			for (int j = 0; j < currBallotResults.length; j++) {
				sb.append(String.format("%s: %d\t", parties.get(j).getName(), currBallotResults[j]));
				finalResults[j] += currBallotResults[j];
			}
			sb.append("voter percentage: " + ballotRegistry[i].getVotersPercentage() + "\n");
		}

		Elections.sortParties(finalResults.clone(), parties);
		Elections.sortResults(finalResults);

		sb.append("Final Results:");
		for (int i = 0; i < finalResults.length; i++)
			sb.append(String.format("\n%s : %d", parties.get(i).getName(), finalResults[i]));
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BallotRegistry))
			return false;
		BallotRegistry other = (BallotRegistry) obj;
		return ballotCount == other.ballotCount && Arrays.equals(ballotRegistry, other.ballotRegistry);
	}

	@Override
	public String toString() {
		if (ballotCount == 0)
			return "Nothing to See here..";

		StringBuilder sb = new StringBuilder(ballotRegistry[0].toString());
		for (int i = 1; i < ballotCount; i++)
			sb.append("\n" + ballotRegistry[i].toString().replaceAll("\n", "\n\t"));

		return sb.toString();
	}
}