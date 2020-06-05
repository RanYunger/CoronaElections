package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.AddBallotModel;
import ID318783479_ID316334473.Views.AddBallotView;

public class AddBallotController {
	// Constants

	// Fields
	private AddBallotModel addBallotModel;
	private AddBallotView addBallotView;

	// Properties (Getters and Setters)
	public AddBallotModel getAddBallotModel() {
		return addBallotModel;
	}

	public void setAddBallotModel(AddBallotModel addBallotModel) {
		this.addBallotModel = addBallotModel;
	}

	public AddBallotView getAddBallotView() {
		return addBallotView;
	}

	public void setAddBallotView(AddBallotView addBallotView) {
		this.addBallotView = addBallotView;
	}

	// Constructors
	public AddBallotController(AddBallotModel model, AddBallotView view) {
		setAddBallotModel(model);
		setAddBallotView(view);

		addBallotView.refresh(addBallotModel);
	}

	// Methods
}
