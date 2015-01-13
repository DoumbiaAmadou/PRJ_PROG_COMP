package implementationVoteSimple


import Gvote._
import scala.actors.remote.Apply0

class SystemeDecomptageVotePlanning(_nom : String, election : Election , nbvotantParPlanning : Int) 
	extends SystemeDecomptageUninominal(_nom : String, election : Election){
	type ImplTour = Tour
	nbvotant  = nbvotantParPlanning
	
	
	override def ajouterVote(vote : Vote) : Boolean = {
			
		  val a = super.contains(vote)
			if(a)   
			return  super.ajouterVote(vote) 
			else return false 
	}
        
    
}
