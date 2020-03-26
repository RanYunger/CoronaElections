package ID318783479_ID316334473;

public class MilitaryBallot extends Ballot /*implements iAssociateVoters*/ {
	// Constants
	
	// Fields
	
	// Properties (Getters and Setters)
	
	// Constructors
	public MilitaryBallot() {
		super("<UNKNOWN>", new Citizen[Program.MAX_ARRAY_SIZE], new int[Elections.getParties().length]);
	}
	public MilitaryBallot(String address) {
		super(address, new Citizen[Program.MAX_ARRAY_SIZE], new int[Elections.getParties().length]);
	}
	public MilitaryBallot(String address, Citizen[] voterRegister, int[] results) {
		super(address, voterRegister, results);
	}
	
	// Methods
//	@Override
//	public void associateVoters() {
//		TODO: COMPLETE
//	}
}
