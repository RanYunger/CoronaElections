package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.AddCandidateToPartyModel;
import ID318783479_ID316334473.Views.AddCandidateToPartyView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class AddCandidateToPartyController {
	// Constants

	// Fields
	private AddCandidateToPartyModel addCandidateToPartyModel;
	private AddCandidateToPartyView addCandidateToPartyView;

	// Properties (Getters and Setters)
	public AddCandidateToPartyModel getAddCandidateToPartyModel() {
		return addCandidateToPartyModel;
	}

	public void setAddCandidateToPartyModel(AddCandidateToPartyModel addCandidateToPartyModel) {
		this.addCandidateToPartyModel = addCandidateToPartyModel;
	}

	public AddCandidateToPartyView getAddCandidateToPartyView() {
		return addCandidateToPartyView;
	}

	public void setAddCandidateToPartyView(AddCandidateToPartyView addCandidateToPartyView) {
		this.addCandidateToPartyView = addCandidateToPartyView;
	}

	// Constructors
	public AddCandidateToPartyController(AddCandidateToPartyModel model, AddCandidateToPartyView view) {
		setAddCandidateToPartyModel(model);
		setAddCandidateToPartyView(view);

		addCandidateToPartyView.refresh(addCandidateToPartyModel);
		
		ChangeListener<String> candidateIDTextFieldChangeListener = new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				// TODO: COMPLETE
				
			}
		};
		ChangeListener<String> candidateNameTextFieldChangeListener = new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				// TODO: COMPLETE
				
			}
		};
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};

		view.addChangeListenerToTextField("candidateIDTextField", candidateIDTextFieldChangeListener);
		view.addChangeListenerToTextField("candidateNameTextField", candidateNameTextFieldChangeListener);
		view.addEventHandlerToButton("submitButton", submitButtonEventHandler);
	}

	// Methods
}
