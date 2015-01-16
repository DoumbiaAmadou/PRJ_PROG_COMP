package implementationCondorcet

import Gvote.AbstractTour
import Gvote.Candidat
import scala.util.control.Breaks

class TourCondorcet(_election : ElectionCondorcet) extends AbstractTour(_election) {
	type ImplVote = VoteCondorcet
		
	def compareVote(c1 : Candidat, c2 : Candidat) : Int = {
	    var ptC1 : Int = 0
	    var ptC2 : Int = 0
	    val loop = new Breaks
	    
	    for(vote <- voteList){
	    	loop.breakable{
	    		for(candidatEtPosition <- vote.candidats){
    				if(candidatEtPosition._1.id == c1.id){
    					ptC1+=1
    					loop.break;
    				}
    				else if(candidatEtPosition._1.id == c2.id){
    					ptC2+=1
    					loop.break;
    				}
    			}
    		}
    	}
	  
	  return ptC1-ptC2
	}
}