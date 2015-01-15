package implementationVoteSimple

import Gvote.AbstractElection
import Gvote.ModeScrutin
import Gvote.Candidat

class Election(_modeScrutin : ModeScrutin) extends AbstractElection(_modeScrutin){
	type ImplTour = Tour
	type Candidate = Candidat
	
	def this(modeScrutin : String, nbTour : Int, listGagnantParTour : List[Int]  ,visibiliteVote : String ){
		this(new  ModeScrutin(modeScrutin, nbTour, listGagnantParTour,visibiliteVote));
	}
	
}

