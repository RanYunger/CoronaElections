package ID318783479_ID316334473.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.stage.Stage;

// An abstract class containing integral components for derivative views
public abstract class View {
	// Constants

	// Fields
	protected Stage stage;

	// Properties (Getters and Setters)
	protected final void setStage(Stage stage) {
		this.stage = stage;
	}

	// Constructors
	public View() {
		setStage(new Stage());
	}

	public View(Stage stage) {
		setStage(stage);
	}

	// Methods
	protected abstract void buildScene();

	protected abstract void addEffects();

	public ObservableList<Node> getChildren(){
		ObservableList<Node> childrenUnmodifiable = stage.getScene().getRoot().getChildrenUnmodifiable();
		ObservableList<Node> allNodes = FXCollections.observableArrayList();
		
		for (Node node : childrenUnmodifiable)
			allNodes.add(node);
		
		return allNodes;
	}

	public Node getHighestNodeInView() {
		ObservableList<Node> allNodes = getChildren();
		Node highestNode = allNodes.get(0);

		for (int i = 1; i < allNodes.size(); i++)
			highestNode = allNodes.get(i).getTranslateY() < highestNode.getTranslateY() ? allNodes.get(i) : highestNode;

		return highestNode;
	}
	
	public void removeNode(Node node) {
		stage.getScene().getRoot().getChildrenUnmodifiable().remove(node);
	}

	public void close() {
		stage.close();
	}
}
