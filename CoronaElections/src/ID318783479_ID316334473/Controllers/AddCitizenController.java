package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.AddCitizenModel;
import ID318783479_ID316334473.Views.AddCitizenView;

public class AddCitizenController {
	// Constants

	// Fields
	private AddCitizenModel addCitizenModel;
	private AddCitizenView addCitizenView;

	// Properties (Getters and Setters)
	public AddCitizenModel getAddCitizenModel() {
		return addCitizenModel;
	}

	public void setAddCitizenModel(AddCitizenModel addCitizenModel) {
		this.addCitizenModel = addCitizenModel;
	}

	public AddCitizenView getAddCitizenView() {
		return addCitizenView;
	}

	public void setAddCitizenView(AddCitizenView addCitizenView) {
		this.addCitizenView = addCitizenView;
	}

	// Constructors
	public AddCitizenController(AddCitizenModel model, AddCitizenView view) {
		setAddCitizenModel(model);
		setAddCitizenView(view);

		addCitizenView.refresh(addCitizenModel);
	}

	// Methods
}
