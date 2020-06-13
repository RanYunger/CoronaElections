package ID318783479_ID316334473.Views;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.MainModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainView {
	// Constants

	// Fields
	private Group root;
	private Stage stage;
	private TabPane tabPane;
	private Button fileAComplaintButton;

	private ElectionsTabView electionsTabView;
	private BallotsTabView ballotsTabView;
	private CitizensTabView citizensTabView;
	private PartiesTabView partiesTabView;
	private AboutTabView aboutTabView;

	// Properties (Getters and Setters)
	private void setRoot(Group root) {
		this.root = root;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	private void setStage(Stage stage) {
		this.stage = stage;
	}

	public Button getFileAComplaintButton() {
		return fileAComplaintButton;
	}
	
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

	// Constructors
	public MainView(Stage stage, LocalDate electionsDate) {
		setRoot(new Group());

		setStage(stage);
		setElectionsTabView(new ElectionsTabView());
		setBallotsTabView(new BallotsTabView());
		setCitizensTabView(new CitizensTabView());
		setPartiesTabView(new PartiesTabView());
		setAboutTabView(new AboutTabView());
		
		buildScene(electionsDate);
		
		addEffects();
		electionsTabView.addEffects(stage);
		ballotsTabView.addEffects(stage);
		citizensTabView.addEffects(stage);
		partiesTabView.addEffects(stage);
		aboutTabView.addEffects(stage);
	}

	// Methods
	public void refresh(MainModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(LocalDate electionsDate) {
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
	
	public Node getNodeByName(String nodeName) {
		try {
			return (Node) getClass().getDeclaredField(nodeName).get(this);
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}

		return null;
	}
	
	public Object getPropertyByName(String nodeName, String propertyName) {
		Node node = getNodeByName(nodeName);

		return node.getProperties().get(propertyName);
	}
	
	public void addEventHandlerToButton(String buttonName, EventHandler<ActionEvent> eventHandler) {
		Button requiredButton = (Button) getNodeByName(buttonName);
		
		requiredButton.setOnAction(eventHandler);
	}
	
	private void addEffects() {
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
