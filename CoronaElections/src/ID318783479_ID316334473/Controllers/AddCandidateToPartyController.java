package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.AddCandidateToPartyModel;
import ID318783479_ID316334473.Models.CitizenModel;
import ID318783479_ID316334473.Views.AddCandidateToPartyView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
				TextField candidateNameTextField = (TextField) view.getNodeByName("candidateNameTextField");

				model.refreshFilter(newText, candidateNameTextField.getText());

				refreshCitizensTableView();
			}
		};
		ChangeListener<String> candidateNameTextFieldChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				TextField candidateIDTextField = (TextField) view.getNodeByName("candidateIDTextField");

				model.refreshFilter(candidateIDTextField.getText(), newText);

				refreshCitizensTableView();
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
	private void refreshCitizensTableView() {
		TableView<String> citizensTableView = (TableView<String>) addCandidateToPartyView
				.getNodeByName("citizensTableView");
		ObservableList<CitizenModel> citizens = addCandidateToPartyModel.getCitizens();
		ObservableList<String> citizensObservableList = FXCollections.emptyObservableList();

		for (CitizenModel citizen : citizens)
			citizensObservableList.add(String.format("%d|%s", citizen.getID(), citizen.getFullName()));

		citizensTableView.setItems(citizensObservableList);
	}
}
