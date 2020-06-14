package ID318783479_ID316334473.Models.Ballots;

import ID318783479_ID316334473.Models.Citizens.CitizenModel;

interface Sanitizable {
	default boolean checkIsolationStatus(CitizenModel voter) {
		return voter.isIsolated();
	}

	default boolean checkSuitStatus(CitizenModel voter) {
		return voter.isWearingSuit();
	}
}
