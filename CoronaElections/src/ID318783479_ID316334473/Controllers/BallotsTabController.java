package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Views.AddBallotView;
import ID318783479_ID316334473.Views.BallotsTabView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

@SuppressWarnings({ "unused" })
public class BallotsTabController {
	public BallotsTabController(BallotsTabView tabView) {
		EventHandler<ActionEvent> addBallotButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				UIHandler.getMainView().AllButtonsAndTabsSetDisable(true);
				AddBallotView addView = new AddBallotView();
				AddBallotController controller = new AddBallotController(tabView, addView);
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
		tabView.getBallotsTableView().getSelectionModel().selectedIndexProperty()
				.addListener(ballotsTableViewEventHandler);
	}
}
