package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.PartiesTabModel;
import ID318783479_ID316334473.Views.PartiesTabView;

public class PartiesTabController {
	// Constants
	
	// Fields
	private PartiesTabModel partiesTabModel;
	private PartiesTabView partiesTabView;

	// Properties (Getters and Setters)
	public PartiesTabModel getPartiesTabModel() {
		return partiesTabModel;
	}

	public void setPartiesTabModel(PartiesTabModel partiesTabModel) {
		this.partiesTabModel = partiesTabModel;
	}

	public PartiesTabView getPartiesTabView() {
		return partiesTabView;
	}

	public void setPartiesTabView(PartiesTabView partiesTabView) {
		this.partiesTabView = partiesTabView;
	}

	// Constructors
	public PartiesTabController(PartiesTabModel model, PartiesTabView view) {
		setPartiesTabModel(model);
		setPartiesTabView(view);
		
		view.refresh(model);
	}
	
	// Methods
}
