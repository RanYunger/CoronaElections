package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainView extends View {
	// Fields
	private TabPane tabPane;
	private Button fileAComplaintButton;
	private ImageView audioImageView;
	private ElectionsTabView electionsTabView;
	private BallotsTabView ballotsTabView;
	private CitizensTabView citizensTabView;
	private PartiesTabView partiesTabView;
	private AboutTabView aboutTabView;

	// Properties (Getters and Setters)
	public Stage getStage() {
		return stage;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public Tab getSelectedTab() {
		return tabPane.getSelectionModel().getSelectedItem();
	}

	public Button getFileAComplaintButton() {
		return fileAComplaintButton;
	}

	private void setFileAComplaintButton(Button fileAComplaintButton) {
		this.fileAComplaintButton = fileAComplaintButton;
	}

	public ImageView getAudioImageView() {
		return audioImageView;
	}

	public void setAudioImageView(ImageView audioImageView) {
		this.audioImageView = audioImageView;
	}

	public ElectionsTabView getElectionsTabView() {
		return electionsTabView;
	}

	public void setElectionsTabView(ElectionsTabView electionsTabView) {
		this.electionsTabView = electionsTabView;
	}

	public BallotsTabView getBallotsTabView() {
		return ballotsTabView;
	}

	public void setBallotsTabView(BallotsTabView ballotsTabView) {
		this.ballotsTabView = ballotsTabView;
	}

	public CitizensTabView getCitizensTabView() {
		return citizensTabView;
	}

	public void setCitizensTabView(CitizensTabView citizensTabView) {
		this.citizensTabView = citizensTabView;
	}

	public PartiesTabView getPartiesTabView() {
		return partiesTabView;
	}

	public void setPartiesTabView(PartiesTabView partiesTabView) {
		this.partiesTabView = partiesTabView;
	}

	public AboutTabView getAboutTabView() {
		return aboutTabView;
	}

	public void setAboutTabView(AboutTabView aboutTabView) {
		this.aboutTabView = aboutTabView;
	}

	public ObservableList<BallotModel> getAllBallots() {
		return ballotsTabView.getAllBallots();
	}

	public ObservableList<CitizenModel> getAllCitizens() {
		return citizensTabView.getAllCitizens();
	}

	public ObservableList<PartyModel> getAllParties() {
		return partiesTabView.getAllParties();
	}

	// Constructors
	public MainView(Stage stage) {
		super(stage);

		setElectionsTabView(new ElectionsTabView(stage));
		setBallotsTabView(new BallotsTabView(stage));
		setCitizensTabView(new CitizensTabView(stage));
		setPartiesTabView(new PartiesTabView(stage));
		setAboutTabView(new AboutTabView(stage));

		buildScene();

		addEffects();
		electionsTabView.addEffects();
		ballotsTabView.addEffects();
		citizensTabView.addEffects();
		partiesTabView.addEffects();
		aboutTabView.addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		String[] tabNames = new String[] { "Elections", "Ballots", "Citizens", "Parties", "About" };
		Node[] tabContents = { electionsTabView.asNode(), ballotsTabView.asNode(), citizensTabView.asNode(),
				partiesTabView.asNode(), aboutTabView.asNode() };
		double sceneWidth = 1500, sceneHeight = 700, fontSize = 50;
		Tab currentTab;

		tabPane = new TabPane();
		for (int i = 0; i < tabNames.length; i++) {
			currentTab = new Tab(tabNames[i], tabContents[i]);
			currentTab.setClosable(false);
			tabPane.getTabs().add(currentTab);
		}

		UIHandler.setGeneralFeatures(stage);
		stage.setScene(new Scene(UIHandler.buildBackground(tabPane, sceneWidth, sceneHeight, fontSize, true),
				sceneWidth, sceneHeight));

		stage.show();
	}

	protected void addEffects() {
		Scene scene = stage.getScene();
		ObservableList<Node> rootNodes = scene.getRoot().getChildrenUnmodifiable();
		Node currentNode;

		for (int i = 0; i < rootNodes.size(); i++) {
			currentNode = rootNodes.get(i);
			if ((currentNode instanceof Button) && (((Button) currentNode).getText() == "התלונן עלינו!")) {
				setFileAComplaintButton((Button) currentNode);
				UIHandler.addCursorEffectsToNode(fileAComplaintButton);
			} else if ((currentNode instanceof ImageView) && ((ImageView) currentNode).getImage().getWidth() == 30) {
				setAudioImageView((ImageView) currentNode);
				UIHandler.addAudioToImageView(audioImageView, "Yayyy.mp3");
			}
		}
	}

	public void AllButtonsAndTabsSetDisable(boolean value) {
		tabPane.setDisable(value);
		AllButtonsSetDisable(value);
	}

	public void AllButtonsSetDisable(boolean value) {
		ballotsTabView.getAddBallotButton().setDisable(value);
		citizensTabView.getAddCitizenButton().setDisable(value);
		electionsTabView.getRunElectionsButton().setDisable(value);
		electionsTabView.getShowResultsButton().setDisable(value);
		partiesTabView.getAddPartyButton().setDisable(value);
		partiesTabView.getAddCandidateToPartyButton().setDisable(value);
	}
}
