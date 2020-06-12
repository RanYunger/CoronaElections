package ID318783479_ID316334473.Controllers;

import java.util.ArrayList;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddCandidateToPartyModel;
import ID318783479_ID316334473.Models.AddPartyModel;
import ID318783479_ID316334473.Models.BallotModel;
import ID318783479_ID316334473.Models.CandidateModel;
import ID318783479_ID316334473.Models.CitizenModel;
import ID318783479_ID316334473.Models.CitizensTabModel;
import ID318783479_ID316334473.Models.PartiesTabModel;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Views.AddCandidateToPartyView;
import ID318783479_ID316334473.Views.AddPartyView;
import ID318783479_ID316334473.Views.PartiesTabView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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

		partiesTabView.refresh(partiesTabModel);

		EventHandler<ActionEvent> addPartyButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					AddPartyModel model = new AddPartyModel();
					AddPartyView view = new AddPartyView(new Stage(), partiesTabModel.getElectionsDate());
					AddPartyController controller = new AddPartyController(model, view);
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> addCandidateToPartyButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					CitizensTabModel citizensTabModel = (CitizensTabModel) UIHandler.getModelByName("CitizensTabModel");
					AddCandidateToPartyModel model = new AddCandidateToPartyModel(citizensTabModel.getCitizens());
					AddCandidateToPartyView view = new AddCandidateToPartyView(new Stage(),
							partiesTabModel.getElectionsDate());
					AddCandidateToPartyController controller = new AddCandidateToPartyController(model, view);
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> removePartyButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TableView<String> partiesTableView = (TableView<String>) partiesTabView
						.getNodeByName("partiesTableView");
				int selectedIndex = partiesTableView.getSelectionModel().getSelectedIndex();

				try {
					// Validations
					if (selectedIndex == -1)
						throw new IllegalStateException("Choose a party to remove.");

					partiesTableView.getItems().remove(selectedIndex);

				} catch (IllegalStateException ex) {
					UIHandler.showError(ex.getMessage());
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};
		ChangeListener<? super Number> partiesTableViewEventHandler = new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSelectedIndex,
					Number newSelectedIndex) {
				TableView<CandidateModel> candidatesInPartyTableView = (TableView<CandidateModel>) partiesTabView
						.getNodeByName("candidatesInPartyLabel");
				PartyModel selectedParty = partiesTabModel.getParties().get((int) newSelectedIndex);
				ArrayList<CandidateModel> candidateRegistry = selectedParty.getCandidates();

				candidatesInPartyTableView.setItems(FXCollections.observableList(candidateRegistry));
			}
		};

		partiesTabView.addEventHandlerToButton("addPartyButton", addPartyButtonEventHandler);
		partiesTabView.addEventHandlerToButton("addCandidateToPartyButton", addCandidateToPartyButtonEventHandler);
		partiesTabView.addEventHandlerToButton("removePartyButton", removePartyButtonEventHandler);
		partiesTabView.addEventHandlerToTableView("partiesTableView", partiesTableViewEventHandler);
	}

	// Methods
}
