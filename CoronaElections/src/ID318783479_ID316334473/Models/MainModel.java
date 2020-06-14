package ID318783479_ID316334473.Models;

import java.time.LocalDate;

public class MainModel {
	// Constants

	// Fields
	private LocalDate electionsDate;

	// Properties (Getters and Setters)
	public LocalDate getElectionsDate() {
		return electionsDate;
	}

	private void setElectionsDate(LocalDate electionsDate) {
		this.electionsDate = electionsDate;
	}

	// Constructors
	public MainModel(LocalDate electionsDate) {
		setElectionsDate(electionsDate);
	}

	// Methods
}
