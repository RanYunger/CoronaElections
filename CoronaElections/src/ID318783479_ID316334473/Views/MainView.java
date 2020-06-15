package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainView extends View {
	// Constants

	// Fields
	private TabPane tabPane;
	private Button fileAComplaintButton;
	private ElectionsTabView electionsTabView;
	private BallotsTabView ballotsTabView;
	private CitizensTabView citizensTabView;
	private PartiesTabView partiesTabView;
	private AboutTabView aboutTabView;

	// Properties (Getters and Setters)	
	
	private void setFileAComplaintButton(Button fileAComplaintButton) {
		this.fileAComplaintButton = fileAComplaintButton;
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
	
	public Button getFileAComplaintButton() {
		return fileAComplaintButton;
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
		LocalDate electionsDate = UIHandler.getElectionsDate();
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

		stage.setTitle(String.format("Corona Elections [%s %d]", electionsDate.getMonth().toString(), electionsDate.getYear()));
		stage.setResizable(false);
		stage.setScene(new Scene(UIHandler.buildBackground(tabPane, sceneWidth, sceneHeight, fontSize, true), sceneWidth, sceneHeight));
		
		UIHandler.setIcon(stage);
		
		stage.show();
	}

	protected void addEffects() {
		Scene scene = stage.getScene();
		ObservableList<Node> rootNodes = scene.getRoot().getChildrenUnmodifiable();
		Node currentNode;
		
		for (int i = 0; i < rootNodes.size(); i++) {
			currentNode = rootNodes.get(i);
			if((currentNode instanceof Button) && (((Button)currentNode).getText() == "התלונן עלינו!")){
				setFileAComplaintButton((Button)currentNode);
				
				break;
			}
		}
		
		UIHandler.addCursorEffectsToNode(scene, fileAComplaintButton);
	}
}
