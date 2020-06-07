package ID318783479_ID316334473.Views;

import java.time.YearMonth;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.MainModel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainView {
	// Constants

	// Fields
	private Group root;
	private Stage stage;
	private TabPane tabPane;
	private ElectionsTabView electionsTabView;
	private BallotsTabView ballotsTabView;
	private CitizensTabView citizensTabView;
	private PartiesTabView partiesTabView;
	private AboutTabView aboutTabView;

	// Properties (Getters and Setters)
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
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
	public MainView(Stage stage, YearMonth electionsDate) {
		root = new Group();

		setStage(stage);
		setElectionsTabView(new ElectionsTabView());
		setBallotsTabView(new BallotsTabView());
		setCitizensTabView(new CitizensTabView());
		setPartiesTabView(new PartiesTabView());
		setAboutTabView(new AboutTabView());

		buildScene(stage, electionsDate);
	}

	// Methods
	public void refresh(MainModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(Stage stage, YearMonth electionsDate) {
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
		UIHandler.setIcon(stage);
		stage.setScene(new Scene(UIHandler.buildBackground(tabPane, sceneWidth, sceneHeight, fontSize, true), sceneWidth, sceneHeight));
		stage.show();
	}
}