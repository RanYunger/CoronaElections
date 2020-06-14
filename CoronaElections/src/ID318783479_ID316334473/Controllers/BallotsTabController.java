package ID318783479_ID316334473.Controllers;

import java.util.ArrayList;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Views.AddBallotView;
import ID318783479_ID316334473.Views.BallotsTabView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

@SuppressWarnings({ "unchecked", "unused" })
public class BallotsTabController {
	// Constants

	// Fields
	private BallotsTabView ballotsTabView;

	// Properties (Getters and Setters)
	public BallotsTabView getBallotsTabView() {
		return ballotsTabView;
	}

	public void setBallotsTabView(BallotsTabView ballotsTabView) {
		this.ballotsTabView = ballotsTabView;
	}

	// Constructors
	public BallotsTabController(BallotsTabView view) {
		setBallotsTabView(view);

		EventHandler<ActionEvent> addBallotButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					AddBallotView addView = new AddBallotView(new Stage(), UIHandler.getElectionsDate());
					AddBallotController controller = new AddBallotController(ballotsTabView, addView);
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
				if (newSelectedIndex.intValue() != -1) {
					BallotModel selectedBallot = ballotsTabView.getAllBallots().get((int) newSelectedIndex);

					votersInBallotTableView.setItems(selectedBallot.getVoterRegistry());
				}
			}
		};

		ballotsTabView.addEventHandlerToButton("addBallotButton", addBallotButtonEventHandler);
		ballotsTabView.addEventHandlerToButton("removeBallotButton", removeBallotButtonEventHandler);
		ballotsTabView.addEventHandlerToTableView("ballotsTableView", ballotsTableViewEventHandler);
	}
}
