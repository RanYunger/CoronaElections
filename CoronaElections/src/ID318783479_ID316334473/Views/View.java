package ID318783479_ID316334473.Views;

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

	public void close() {
		stage.close();
	}
}
