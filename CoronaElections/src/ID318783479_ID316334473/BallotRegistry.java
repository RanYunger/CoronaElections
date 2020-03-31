package ID318783479_ID316334473;

import java.util.Arrays;
import java.util.Scanner;

public class BallotRegistry {
	private Ballot[] ballotRegistry;
	private int ballotCount;

	// Properties (Getters and Setters)
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
		setBallotRegistry(new Ballot[Elections.MAX_ARRAY_SIZE]);
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
		if (ballotCount == Elections.MAX_ARRAY_SIZE)
			return false;
		if (ballotCount == 0) {
			ballotRegistry[ballotCount++] = ballot;

			return true;
		}
		if (indexOf(ballot.getID()) != -1)
			return false;

		i = ballotCount;
		while (i > 0 && ballotRegistry[i - 1].getID() > ballot.getID()) {
			ballotRegistry[i] = ballotRegistry[i - 1];
			i--;
		}
		ballotRegistry[i] = ballot;
		ballotCount++;
		System.out.println("Ballot successfully added to the ballot registry!");

		return true;
	}

	public void vote(Scanner scan, PartyRegistry parties) {
		for (int i = 0; i < ballotCount; i++)
			ballotRegistry[i].vote(scan, parties);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BallotRegistry other = (BallotRegistry) obj;
		return ballotCount == other.ballotCount && Arrays.equals(ballotRegistry, other.ballotRegistry);
	}

	public String showResults(PartyRegistry parties) {
		StringBuilder results = new StringBuilder();
		int[] currBallotResults, finalResults = new int[parties.getPartyCount()];
		for (int i = 0; i < ballotCount; i++) {
			currBallotResults = ballotRegistry[i].getResults();
			results.append(String.format("Ballot #%d:\n", ballotRegistry[i].getID()));
			for (int j = 0; j < currBallotResults.length; j++) {
				results.append(String.format("%s: %d\n", parties.get(j).getName(), currBallotResults[j]));
				finalResults[j] += currBallotResults[j];
			}
		}
		finalResults = Elections.sortResults(finalResults);

		results.append("Final Results:\n");
		for (int i = 0; i < finalResults.length; i++)
			results.append(String.format("%s : %d\n", parties.get(i).getName(), finalResults[i]));

		return results.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb;
		
		if (ballotCount == 0)
			return "Nothing to See here..";

		sb = new StringBuilder();
		for (int i = 0; i < ballotCount; i++)
			sb.append(ballotRegistry[i].toString() + "\n");

		return sb.toString();
	}
}