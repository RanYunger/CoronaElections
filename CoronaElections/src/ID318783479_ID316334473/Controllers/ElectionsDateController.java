package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.ElectionsDateView;
import ID318783479_ID316334473.Views.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ElectionsDateController {
	// Constants

	// Fields
	private ElectionsDateView electionsDateView;

	// Properties (Getters and Setters)
	public ElectionsDateView getElectionsDateView() {
		return electionsDateView;
	}

	private void setElectionsDateView(ElectionsDateView electionsDateView) {
		this.electionsDateView = electionsDateView;
	}

	// Constructors
	public ElectionsDateController(ElectionsDateView view) {
		setElectionsDateView(view);

		EventHandler<ActionEvent> enterButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainView mainView;
				MainController mainController;

				UIHandler.setElectionsDate(electionsDateView.getElectionsDate());
				
				mainView = new MainView(new Stage());
				mainController = new MainController(mainView, mainView.getElectionsTabView(),
						mainView.getBallotsTabView(), mainView.getCitizensTabView(), mainView.getPartiesTabView());
				
				UIHandler.setMainController(mainController);
				UIHandler.setMainView(mainView);
				UIHandler.initViewStatuses();
				
				mainView.getBallotsTabView().initBallots();
				mainView.getCitizensTabView().initCitizens();
				mainView.getPartiesTabView().initParties();
				
				electionsDateView.close();		
			}
		};
		
		electionsDateView.getEnterButton().setOnAction(enterButtonEventHandler);
	}

	// Methods
}
