package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.AddPartyModel;
import ID318783479_ID316334473.Views.AddPartyView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddPartyController {
	// Constants

	// Fields
	private AddPartyModel addPartyModel;
	private AddPartyView addPartyView;

	// Properties (Getters and Setters)
	public AddPartyModel getAddPartyModel() {
		return addPartyModel;
	}

	public void setAddPartyModel(AddPartyModel addPartyModel) {
		this.addPartyModel = addPartyModel;
	}

	public AddPartyView getAddPartyView() {
		return addPartyView;
	}

	public void setAddPartyView(AddPartyView addPartyView) {
		this.addPartyView = addPartyView;
	}

	// Constructors
	public AddPartyController(AddPartyModel model, AddPartyView view) {
		setAddPartyModel(model);
		setAddPartyView(view);

		addPartyView.refresh(model);
		
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};

		view.addEventHandlerToButton("submitButton", submitButtonEventHandler);
	}

	// Methods
}
