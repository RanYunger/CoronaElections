package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.BallotModel;
import ID318783479_ID316334473.Models.BallotsTabModel;
import ID318783479_ID316334473.Views.BallotsTabView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableView;

public class BallotsTabController {
	// Constants

	// Fields
	private BallotsTabModel ballotsTabModel;
	private BallotsTabView ballotsTabView;

	// Properties (Getters and Setters)
	public BallotsTabModel getBallotsTabModel() {
		return ballotsTabModel;
	}

	public void setBallotsTabModel(BallotsTabModel ballotsTabModel) {
		this.ballotsTabModel = ballotsTabModel;
	}

	public BallotsTabView getBallotsTabView() {
		return ballotsTabView;
	}

	public void setBallotsTabView(BallotsTabView ballotsTabView) {
		this.ballotsTabView = ballotsTabView;
	}

	// Constructors
	public BallotsTabController(BallotsTabModel model, BallotsTabView view) {
		setBallotsTabModel(model);
		setBallotsTabView(view);

		view.refresh(model);

		EventHandler<ActionEvent> addBallotButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// TODO: COMPLETE
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> removeBallotButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TableView<String> ballotsTableView = (TableView<String>) ballotsTabView.getNodeByName("ballotsTableView");
				int selectedIndex = ballotsTableView.getSelectionModel().getSelectedIndex();
				
				try {
					// Validations
					if (selectedIndex == -1)
						throw new IllegalStateException("Choose a ballot to remove.");
					
				} catch (IllegalStateException ex) {
					UIHandler.showError(ex.getMessage());
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};

		view.addEventHandlerToButton("addBallotButton", addBallotButtonEventHandler);
		view.addEventHandlerToButton("removeBallotButton", removeBallotButtonEventHandler);
	}

	// Methods
}
