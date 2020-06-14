package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.MainModel;
import ID318783479_ID316334473.Views.BallotsTabView;
import ID318783479_ID316334473.Views.CitizensTabView;
import ID318783479_ID316334473.Views.ComplaintView;
import ID318783479_ID316334473.Views.ElectionsTabView;
import ID318783479_ID316334473.Views.MainView;
import ID318783479_ID316334473.Views.PartiesTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class MainController {
	// Constants

	// Fields
	private MainView mainView;
	private ElectionsTabController electionsTabController;
	private BallotsTabController ballotsTabController;
	private CitizensTabController citizensTabController;
	private PartiesTabController partiesTabController;

	// Properties (Getters and Setters)
	public MainView getMainView() {
		return mainView;
	}

	private void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public ElectionsTabController getElectionsTabController() {
		return electionsTabController;
	}

	private void setElectionsTabController(ElectionsTabController electionsTabController) {
		this.electionsTabController = electionsTabController;
	}

	public BallotsTabController getBallotsTabController() {
		return ballotsTabController;
	}

	private void setBallotsTabController(BallotsTabController ballotsTabController) {
		this.ballotsTabController = ballotsTabController;
	}

	public CitizensTabController getCitizensTabController() {
		return citizensTabController;
	}

	private void setCitizensTabController(CitizensTabController citizensTabController) {
		this.citizensTabController = citizensTabController;
	}

	public PartiesTabController getPartiesTabController() {
		return partiesTabController;
	}

	private void setPartiesTabController(PartiesTabController partiesTabController) {
		this.partiesTabController = partiesTabController;
	}

	// Constructors
	public MainController(MainModel mainModel, MainView mainView, ElectionsTabView electionsTabView,
			BallotsTabView ballotsTabView, CitizensTabView citizensTabView, PartiesTabView partiesTabView) {
		setMainView(mainView);
		setElectionsTabController(new ElectionsTabController(electionsTabView));
		setBallotsTabController(new BallotsTabController(ballotsTabView));
		setCitizensTabController(new CitizensTabController(citizensTabView));
		setPartiesTabController(new PartiesTabController(partiesTabView));

		EventHandler<ActionEvent> fileAComplaintButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					ComplaintView view = new ComplaintView(new Stage(), UIHandler.getElectionsDate());
//					ComplaintController controller = new ComplaintController(view);
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured", ex.getMessage());
				}
			}
		};

		mainView.addEventHandlerToButton("fileAComplaintButton", fileAComplaintButtonEventHandler);
	}

	// Methods
}
