package ID318783479_ID316334473;

public interface CoronaChecker {
	public default boolean checkIsolation(Citizen citizen) {
		return citizen.isIsolated();
	}
}
