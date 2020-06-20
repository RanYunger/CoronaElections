package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Citizens.CandidateModel;
import ID318783479_ID316334473.Views.AddCandidateToPartyView;
import ID318783479_ID316334473.Views.AddPartyView;
import ID318783479_ID316334473.Views.CitizensTabView;
import ID318783479_ID316334473.Views.PartiesTabView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PartiesTabController {
	@SuppressWarnings("unused")
	public PartiesTabController(PartiesTabView tabView) {

		EventHandler<ActionEvent> addPartyButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				UIHandler.getMainView().AllButtonsAndTabsSetDisable(true);
				AddPartyView view = new AddPartyView();
				AddPartyController controller = new AddPartyController(tabView, view);
			}
		};
		EventHandler<ActionEvent> addCandidateToPartyButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CitizensTabView citizensTabView = UIHandler.getMainView().getCitizensTabView();
				PartyModel selectedParty = tabView.getPartiesTableView().getSelectionModel().getSelectedItem();

				// Validations
				if (citizensTabView.getAllCitizens().isEmpty()) {
					UIHandler.showWarning("Make sure to have at least one citizen before adding a new candidate");
					return;
				}
				if (selectedParty == null) {
					UIHandler.showError("Choose a party for adding a candidate.");
					return;
				}
				UIHandler.getMainView().AllButtonsAndTabsSetDisable(true);
				AddCandidateToPartyView addView = new AddCandidateToPartyView(new Stage(),
						citizensTabView.getAllCitizens());
				AddCandidateToPartyController controller = new AddCandidateToPartyController(selectedParty, addView,
						citizensTabView);
			}
		};

		ChangeListener<? super Number> partiesTableViewEventHandler = new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSelectedIndex,
					Number newSelectedIndex) {
				TableView<CandidateModel> candidatesInPartyTableView = tabView.getCandidatesInPartyTableView();
				PartyModel selectedParty;

				if (newSelectedIndex.intValue() != -1) {
					selectedParty = tabView.getAllParties().get((int) newSelectedIndex);
					candidatesInPartyTableView.setItems(selectedParty.getCandidates());
				}
			}
		};

		tabView.getAddPartyButton().setOnAction(addPartyButtonEventHandler);
		tabView.getAddCandidateToPartyButton().setOnAction(addCandidateToPartyButtonEventHandler);
		tabView.getPartiesTableView().getSelectionModel().selectedIndexProperty()
				.addListener(partiesTableViewEventHandler);
	}
}
