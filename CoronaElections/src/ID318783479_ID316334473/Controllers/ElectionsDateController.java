package ID318783479_ID316334473.Controllers;

import java.time.YearMonth;

import ID318783479_ID316334473.Models.ElectionsDateModel;
import ID318783479_ID316334473.Models.MainModel;
import ID318783479_ID316334473.Models.SetModel;
import ID318783479_ID316334473.Views.ElectionsDateView;
import ID318783479_ID316334473.Views.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
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

		view.refresh(model);

		EventHandler<ActionEvent> enterButtonListener = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				YearMonth electionsDate = electionsDateView.getElectionsDate();
				MainModel mainModel;
				MainView mainView;
				MainController mainController;

				electionsDateModel.setElectionsDate(electionsDate);
				electionsDateView.refresh(electionsDateModel);

				electionsDateView.close();
				mainModel = new MainModel(electionsDate);
				mainView = new MainView(new Stage());
				mainController = new MainController(mainModel, mainView);
			}
		};
		electionsDateView.addListenerToEnterButton(enterButtonListener);
	}

	// Methods
}
