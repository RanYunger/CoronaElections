package ID318783479_ID316334473;

public enum ValidPatterns {

	// The anonymous Country's citizen IDs consist of 9 digits
	CITIZEN_ID("^[0-9]{9}$"),
	// Citizen's name must have at least a first and a last name, and all words must be capitalized
	CITIZEN_FULL_NAME("^[A-Z][a-z]+(?: [A-Z][a-z]+){1,}$"),
	// Title capitalization rule - first word must always be capital
	// following words must be capital too, unless they're short words(1-3 letters)
	PARTY_NAME("^(?:[A-Z][a-z]+)(?: [A-Z][a-z]+| [a-z]{1,3})*$"),
	// Ballot address can start with building number;
	// all words follow the title capitalization rule mentioned above
	BALLOT_ADDRESS(
			"^(?:[0-9]{1,3} )?(?:[A-Z][a-z]+)(?: [A-Z][a-z]+| [a-z]{1,3})*, (?:[A-Z][a-z]+)(?: [A-Z][a-z]+| [a-z]{1,3})*$");

	private String pattern;

	ValidPatterns(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
