package ID318783479_ID316334473.Controllers;

import java.util.ArrayList;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddBallotModel;
import ID318783479_ID316334473.Models.BallotModel;
import ID318783479_ID316334473.Models.BallotsTabModel;
import ID318783479_ID316334473.Models.CitizenModel;
import ID318783479_ID316334473.Views.AddBallotView;
import ID318783479_ID316334473.Views.BallotsTabView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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

		ballotsTabView.refresh(ballotsTabModel);

		EventHandler<ActionEvent> addBallotButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					AddBallotModel model = new AddBallotModel();
					AddBallotView view = new AddBallotView(new Stage(), ballotsTabModel.getElectionsDate());
					AddBallotController controller = new AddBallotController(model, view);
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> removeBallotButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TableView<String> ballotsTableView = (TableView<String>) ballotsTabView
						.getNodeByName("ballotsTableView");
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
		ChangeListener<? super Number> ballotsTableViewEventHandler = new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSelectedIndex,
					Number newSelectedIndex) {
				TableView<CitizenModel> votersInBallotTableView = (TableView<CitizenModel>) ballotsTabView
						.getNodeByName("votersInBallotTableView");
				BallotModel<?> selectedBallot = ballotsTabModel.getAllBallots().get((int) newSelectedIndex);
				ArrayList<CitizenModel> voterRegistry = selectedBallot.getVoterRegistry();

				votersInBallotTableView.setItems(FXCollections.observableList(voterRegistry));
			}
		};

		ballotsTabView.addEventHandlerToButton("addBallotButton", addBallotButtonEventHandler);
		ballotsTabView.addEventHandlerToButton("removeBallotButton", removeBallotButtonEventHandler);
		ballotsTabView.addEventHandlerToTableView("ballotsTableView", ballotsTableViewEventHandler);
	}

	// Methods
}
