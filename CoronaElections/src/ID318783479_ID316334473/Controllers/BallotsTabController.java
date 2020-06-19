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

@SuppressWarnings({ "unused" })
public class BallotsTabController {
	// Constants

	// Fields
	private BallotsTabView tabView;

	// Properties (Getters and Setters)
	public BallotsTabView getBallotsTabView() {
		return tabView;
	}

	public void setBallotsTabView(BallotsTabView ballotsTabView) {
		this.tabView = ballotsTabView;
	}

	// Constructors
	public BallotsTabController(BallotsTabView view) {
		setBallotsTabView(view);

		EventHandler<ActionEvent> addBallotButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {						
					AddBallotView addView = new AddBallotView();
					AddBallotController controller = new AddBallotController(tabView, addView);
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured", ex.getMessage());
				}
			}
		};
		ChangeListener<? super Number> ballotsTableViewEventHandler = new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSelectedIndex,
					Number newSelectedIndex) {
				TableView<CitizenModel> votersInBallotTableView = tabView.getVotersInBallotTableView();
				if (newSelectedIndex.intValue() != -1) {
					BallotModel selectedBallot = tabView.getAllBallots().get((int) newSelectedIndex);

					votersInBallotTableView.setItems(selectedBallot.getVoterRegistry());
				}
			}
		};

		tabView.getAddBallotButton().setOnAction(addBallotButtonEventHandler);
		tabView.getBallotsTableView().getSelectionModel().selectedIndexProperty().addListener(ballotsTableViewEventHandler);
	}
}
