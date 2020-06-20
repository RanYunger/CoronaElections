package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.BallotsTabView;
import ID318783479_ID316334473.Views.CitizensTabView;
import ID318783479_ID316334473.Views.ComplaintView;
import ID318783479_ID316334473.Views.ElectionsTabView;
import ID318783479_ID316334473.Views.MainView;
import ID318783479_ID316334473.Views.PartiesTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
	@SuppressWarnings("unused")
	public MainController(MainView mainView, ElectionsTabView electionsTabView, BallotsTabView ballotsTabView,
			CitizensTabView citizensTabView, PartiesTabView partiesTabView) {
		setMainView(mainView);
		setElectionsTabController(new ElectionsTabController(electionsTabView));
		setBallotsTabController(new BallotsTabController(ballotsTabView));
		setCitizensTabController(new CitizensTabController(citizensTabView));
		setPartiesTabController(new PartiesTabController(partiesTabView));

		EventHandler<ActionEvent> fileAComplaintButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					ComplaintView view = new ComplaintView();
					ComplaintController controller = new ComplaintController(view);
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured", ex.getMessage());
				}
			}
		};
		EventHandler<MouseEvent> audioImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ImageView audioImageView = mainView.getAudioImageView(), newImageView;

				UIHandler.toggleAudio();
				newImageView = UIHandler.buildImage(UIHandler.isAudioOn() ? "AudioOn.png" : "AudioOff.png", 30, 30);
				audioImageView.setImage(newImageView.getImage());
			}
		};

		mainView.getFileAComplaintButton().setOnAction(fileAComplaintButtonEventHandler);
		mainView.getAudioImageView().setOnMouseClicked(audioImageViewEventHandler);
	}

	// Methods
}
