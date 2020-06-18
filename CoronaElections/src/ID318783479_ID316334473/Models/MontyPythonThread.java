package ID318783479_ID316334473.Models;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.MainView;
import ID318783479_ID316334473.Views.View;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class MontyPythonThread implements Runnable {

	// Methods
	@Override
	public void run() {
		MainView mainView = UIHandler.getMainView();

		double stageWidth = mainView.getStage().getWidth(), stageHeight = mainView.getStage().getHeight();
		ImageView montyPythonLegImageView = UIHandler.buildImage("MontyPythonLeg.png", stageWidth, stageHeight);
		Point2D legPosition, highestNodePosition;
		ObservableList<Node> viewChildren = null;
		View selectedView = null;
		Node highestNode;

		try {
			switch (mainView.getSelectedTab().getText()) {
			case "About":
				selectedView = mainView.getAboutTabView();
				break;
			case "Ballots":
				selectedView = mainView.getBallotsTabView();
				break;
			case "Citizens":
				selectedView = mainView.getCitizensTabView();
				break;
			case "Elections":
				selectedView = mainView.getElectionsTabView();
				break;
			case "Parties":
				selectedView = mainView.getPartiesTabView();
				break;
			}

			viewChildren = selectedView.getChildren();
			viewChildren.add(montyPythonLegImageView);

			legPosition = new Point2D(0, -stageHeight - 10);

			// TODO: FIX
			do {
				highestNode = selectedView.getHighestNodeInView();
				highestNodePosition = new Point2D(highestNode.getTranslateX(), highestNode.getTranslateY());
				while (!UIHandler.isPointInRectangle(highestNodePosition, legPosition, stageWidth, stageHeight)) {
					legPosition.add(0, 50);
					montyPythonLegImageView.relocate(legPosition.getX(), legPosition.getY());

					Thread.sleep(100);
				}
				selectedView.removeNode(highestNode);
			} while ((!viewChildren.isEmpty()) || (montyPythonLegImageView.getY() != 0));
		} catch (Exception ex) {
			UIHandler.showError("Thread exception");
		}
	}
}
