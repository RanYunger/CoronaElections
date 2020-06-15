package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Views.AddCitizenView;
import ID318783479_ID316334473.Views.BallotsTabView;
import ID318783479_ID316334473.Views.CitizensTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CitizensTabController {
	// Constants

	// Fields
	private CitizensTabView tabView;

	// Properties (Getters and Setters)

	public CitizensTabView getCitizensTabView() {
		return tabView;
	}

	public void setCitizensTabView(CitizensTabView citizensTabView) {
		this.tabView = citizensTabView;
	}

	// Constructors
	public CitizensTabController(CitizensTabView view) {
		setCitizensTabView(view);

		EventHandler<ActionEvent> addCitizenButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					BallotsTabView ballotsTabView = (BallotsTabView) UIHandler.getViewByName("BallotsTabView");

					TableView<BallotModel> ballotsTableView = ballotsTabView.getBallotsTableView();
					if (ballotsTableView.getItems().isEmpty())
						UIHandler.showError("Make sure to have at least 1 ballot before creating a new citizen");
					else {
						AddCitizenView addView = new AddCitizenView();
						AddCitizenController controller = new AddCitizenController(tabView, addView);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};

		tabView.getAddCitizenButton().setOnAction(addCitizenButtonEventHandler);
	}

	// Methods
}
