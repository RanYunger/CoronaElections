package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddPartyModel;
import ID318783479_ID316334473.Models.PartiesTabModel;
import ID318783479_ID316334473.Views.AddPartyView;
import ID318783479_ID316334473.Views.PartiesTabView;
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

		view.refresh(model);

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
		EventHandler<ActionEvent> removePartyButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TableView<String> partiesTableView = (TableView<String>) partiesTabView.getNodeByName("partiesTableView");
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
		EventHandler<ActionEvent> addCandidateToPartyButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};

		view.addEventHandlerToButton("addPartyButton", addPartyButtonEventHandler);
		view.addEventHandlerToButton("removePartyButton", removePartyButtonEventHandler);
		view.addEventHandlerToButton("addCandidateToPartyButton", addCandidateToPartyButtonEventHandler);
	}

	// Methods
}
