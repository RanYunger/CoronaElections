package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.Models.MainModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView {
	// Constants

	// Fields
	private Group root;
	private TabPane tabPane;
	private ElectionsTabView electionsTabView;
	private BallotsTabView ballotsTabView;
	private CitizensTabView citizensTabView;
	private PartiesTabView partiesTabView;
	private AboutTabView aboutTabView;

	// Properties (Getters and Setters)
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
	public MainView(Stage stage) {
		root = new Group();

		setElectionsTabView(new ElectionsTabView());
		setBallotsTabView(new BallotsTabView());
		setCitizensTabView(new CitizensTabView());
		setPartiesTabView(new PartiesTabView());
		setAboutTabView(new AboutTabView());

		buildScene(stage);
	}

	// Methods
	public Node getControlByName(String controlName) {
		return root.lookup(controlName);
	}

	public Object getPropertyByName(String controlName, String propertyName) {
		Node control = getControlByName(controlName);

		return control.getProperties().get(propertyName);
	}

	public void refresh(MainModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(Stage stage) {
		String[] tabNames = new String[] { "Elections", "Ballots", "Citizens", "Parties", "About" };
		Node[] tabContents = { electionsTabView.asNode(), ballotsTabView.asNode(), citizensTabView.asNode(),
				partiesTabView.asNode(), aboutTabView.asNode() };
		Tab currentTab;

		tabPane = new TabPane();
		for (int i = 0; i < tabNames.length; i++) {
			currentTab = new Tab(tabNames[i], tabContents[i]);
			currentTab.setClosable(false);
			tabPane.getTabs().add(currentTab);
		}

		stage.setTitle("Corona Elections");
		stage.setResizable(false);
		// TODO: Set icon + background image
		stage.setScene(new Scene(tabPane, 1500, 700));
		stage.show();
	}

	private HBox buildStatusHBox() {
		HBox statusHBox = new HBox();
		CheckBox isolatedCheckBox = new CheckBox("Isolated"), wearingSuitCheckBox = new CheckBox("Wearing suit"),
				soldierCheckBox = new CheckBox("Soldier"), carryingWeaponCheckBox = new CheckBox("Carrying weapon");

		statusHBox.getChildren().addAll(isolatedCheckBox, wearingSuitCheckBox, soldierCheckBox, carryingWeaponCheckBox);
		HBox.setMargin(isolatedCheckBox, new Insets(0, 10, 0, 0));
		HBox.setMargin(wearingSuitCheckBox, new Insets(0, 10, 0, 10));
		HBox.setMargin(soldierCheckBox, new Insets(0, 10, 0, 10));
		HBox.setMargin(carryingWeaponCheckBox, new Insets(0, 0, 0, 10));
		statusHBox.setAlignment(Pos.CENTER);

		return statusHBox;
	}
}
