package ID318783479_ID316334473;

import java.util.ArrayList;

import ID318783479_ID316334473.Models.BallotModel;
import ID318783479_ID316334473.Models.CitizenModel;
import ID318783479_ID316334473.Models.PartyModel;
import javafx.scene.shape.Rectangle;

// This class will contain methods which aren't necessarily related to UI
public class TBN {
	// Constants

	// Fields
	
	// Properties
	
	// Methods
	public static boolean isInRectangle(Rectangle rect, double mouseX, double mouseY) {
		// Validations
		if((mouseX < rect.getX()) || (mouseX > rect.getWidth()))
			return false;
		if((mouseY < rect.getY()) || (mouseY > rect.getHeight()))
			return false;
		
		return true;
	}
	
	public static <T, U> T binarySearch(ArrayList<T> array, U key) {
		return binarySearch(array, key, 0, array.size() - 1);
	}

	private static <T, U> T binarySearch(ArrayList<T> array, U key, int start, int end) {
		if (start <= end) {
			int mid = (start + end) / 2;

			T element = array.get(mid);
			if (array.get(0) instanceof BallotModel) {
				int ballotID = ((BallotModel<? extends CitizenModel>) array.get(mid)).getID();
				if (ballotID == (int) key)
					return element;

				if (ballotID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof CitizenModel) {
				int citizenID = ((CitizenModel) array.get(mid)).getID();
				if (citizenID == (int) key)
					return element;

				if (citizenID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof PartyModel) {
				String partyName = ((PartyModel) array.get(mid)).getName();
				if (partyName.equals((String) key))
					return element;

				if (partyName.compareTo((String) key) > 0)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}
		}
		return null;
	}
}
