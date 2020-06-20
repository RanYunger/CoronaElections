package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Views.AddCitizenView;
import ID318783479_ID316334473.Views.BallotsTabView;
import ID318783479_ID316334473.Views.CitizensTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

public class CitizensTabController {
	public CitizensTabController(CitizensTabView tabView) {
		EventHandler<ActionEvent> addCitizenButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			@SuppressWarnings("unused")
			public void handle(ActionEvent event) {
				UIHandler.getMainView().disableAllButtons(true);
				BallotsTabView ballotsTabView = UIHandler.getMainView().getBallotsTabView();
				TableView<BallotModel> ballotsTableView = ballotsTabView.getBallotsTableView();
				if (ballotsTableView.getItems().isEmpty())
					UIHandler.showWarning("Make sure to have at least 1 ballot before creating a new citizen!");
				else {
					AddCitizenView addView = new AddCitizenView();
					AddCitizenController controller = new AddCitizenController(tabView, addView);
				}
			}
		};

		tabView.getAddCitizenButton().setOnAction(addCitizenButtonEventHandler);
	}
}
