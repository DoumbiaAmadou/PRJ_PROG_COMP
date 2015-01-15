package implementationVoteSimple

import Gvote.SystemGeneralDecomptage
import Gvote.Eligible
import GUIAbstractComponent.GUIComponentCST
import Gvote.Candidat
import Gvote.AbstractElecteur

abstract class SystemeDecomptageSimple(_election : Election , _nom : String) extends SystemGeneralDecomptage(_nom){
    type ImplElection = Election
    type ImplElecteur = Electeur
    type ImplVote = Vote
    type Candidate = Candidat
    
    var GUIType = GUIComponentCST.radio
      
    override protected val election = _election
    
    def ajouterCandidat(candidat : Eligible) : Boolean = {
    	candidat match{
            case cand : Candidat => return election.addCandidat(cand)
        }
            
        return false
    }
    
    protected def ajouterVoteByGUIElecteur(electeur : Electeur, candidats : List[(Int,Eligible)]*):Boolean = {
    		candidats.apply(0) match{
    	  	case candidat : List[(Int,Candidat)] =>
    	  		if(candidat.length == 1){
    	  			 return electeur.voter(this, candidat.apply(0)._2)
    	  		}
    	}
    	
    	return false
    }
    
}