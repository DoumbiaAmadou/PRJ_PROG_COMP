package Gvote


abstract class AbstractElection(_modeScrutin : ModeScrutin){
	type ImplTour <: AbstractTour
	type Candidate <: Eligible
	
	val modeScrutin = _modeScrutin
	var listCandidat : List[Candidate] = List()
	var tourList : List[ImplTour] = List()
	var isFinish : Boolean = false ;
	var sePresenter = true ; 
	var voter = false ;
	
	def this(modeScrutin : String, nbTour : Int, listGagnantParTour : List[Int]  ,visibiliteVote : String ){
		this(new  ModeScrutin(modeScrutin, nbTour, listGagnantParTour,visibiliteVote));
	}
	
	def fermerCandidature() { sePresenter = false }

	def ouvertureVote() {voter=true }
	def fermetureVote():Boolean ={
	  if(voter){
	    voter = false ; 
	    return true ; 
	  }else 
	    return false 
	}
	
	def addCandidat(c : Candidate) : Boolean = {
	    if(sePresenter && !listCandidat.contains(c)){
	    	listCandidat = listCandidat:+c
	    	return true
	    }
	    return false
	}

	def removeCandidat(c : Candidate){
		listCandidat.dropWhile(_.id == c.id)
	}
	
}