package ID318783479_ID316334473.Models.Ballots;

import ID318783479_ID316334473.Models.Citizens.CitizenModel;

interface Militarizable {
	default boolean checkMilitaryStatus(CitizenModel voter) {
		return voter.isSoldier();
	}
}
