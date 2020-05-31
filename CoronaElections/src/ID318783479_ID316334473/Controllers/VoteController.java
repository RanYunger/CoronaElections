package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.VoteModel;
import ID318783479_ID316334473.Views.VoteView;

public class VoteController {
	// Constants
	
	// Fields
	private VoteModel voteModel;
	private VoteView voteView;
	
	// Properties (Getters and Setters)
	public VoteModel getVoteModel() {
		return voteModel;
	}
	
	public void setVoteModel(VoteModel voteModel) {
		this.voteModel = voteModel;
	}
	
	public VoteView getVoteView() {
		return voteView;
	}
	
	public void setVoteView(VoteView voteView) {
		this.voteView = voteView;
	}
	
	// Constructors
	public VoteController(VoteModel model, VoteView view) {
		setVoteModel(model);
		setVoteView(view);
		
		view.refresh(model);
	}
	
	// Methods
}
