package ID318783479_ID316334473.Controllers;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.ElectionsDateModel;
import ID318783479_ID316334473.Models.MainModel;
import ID318783479_ID316334473.Views.ElectionsDateView;
import ID318783479_ID316334473.Views.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ElectionsDateController {
	// Constants

	// Fields
	private ElectionsDateModel electionsDateModel;
	private ElectionsDateView electionsDateView;

	// Properties (Getters and Setters)
	public ElectionsDateModel getElectionsDateModel() {
		return electionsDateModel;
	}

	private void setElectionsDateModel(ElectionsDateModel electionsDateModel) {
		this.electionsDateModel = electionsDateModel;
	}

	public ElectionsDateView getElectionsDateView() {
		return electionsDateView;
	}

	private void setElectionsDateView(ElectionsDateView electionsDateView) {
		this.electionsDateView = electionsDateView;
	}

	// Constructors
	public ElectionsDateController(ElectionsDateModel model, ElectionsDateView view) {
		setElectionsDateModel(model);
		setElectionsDateView(view);

		electionsDateView.refresh(model);

		EventHandler<ActionEvent> enterButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LocalDate electionsDate = electionsDateView.getElectionsDate();
				MainModel mainModel = new MainModel(electionsDate);
				MainView mainView = new MainView(new Stage(), electionsDate);
				MainController mainController;

				electionsDateModel.setElectionsDate(electionsDate);
				electionsDateView.refresh(electionsDateModel);
				
				electionsDateView.close();

				mainController = new MainController(mainModel, mainView, mainModel.getElectionsTabModel(),
						mainView.getElectionsTabView(), mainModel.getBallotsTabModel(), mainView.getBallotsTabView(),
						mainModel.getCitizensTabModel(), mainView.getCitizensTabView(), mainModel.getPartiesTabModel(),
						mainView.getPartiesTabView());

				// These bindings will help to retrieve generic Model/Controller/View in need
				UIHandler.mainModel = mainModel;
				UIHandler.mainController = mainController;
				UIHandler.mainView = mainView;
			}
		};
		electionsDateView.addEventHandlerToEnterButton(enterButtonEventHandler);
	}

	// Methods
}
