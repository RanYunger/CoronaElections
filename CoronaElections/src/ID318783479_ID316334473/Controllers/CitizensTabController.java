package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.CitizensTabModel;
import ID318783479_ID316334473.Views.CitizensTabView;

public class CitizensTabController {
	// Constants
	
	// Fields
	private CitizensTabModel citizensTabModel;
	private CitizensTabView citizensTabView;

	// Properties (Getters and Setters)
	public CitizensTabModel getCitizensTabModel() {
		return citizensTabModel;
	}

	public void setCitizensTabModel(CitizensTabModel citizensTabModel) {
		this.citizensTabModel = citizensTabModel;
	}

	public CitizensTabView getCitizensTabView() {
		return citizensTabView;
	}

	public void setCitizensTabView(CitizensTabView citizensTabView) {
		this.citizensTabView = citizensTabView;
	}

	// Constructors
	public CitizensTabController(CitizensTabModel model, CitizensTabView view) {
		setCitizensTabModel(model);
		setCitizensTabView(view);
		
		view.refresh(model);
	}
	
	// Methods
}
