package implementationPlurinominale;

import Gvote._

class TourPlurinominale(num: Int , _election : AbstractElection) extends AbstractTour(_election) {
	type ImplVote = VotePlurinominale
	
	def getNbVote(_candidat : Candidat) : Int = {
			  
		var cpt : Int = 0
		for(vote <- voteList){
		 for (elec <- vote.listCandidat ) 
			 if(elec.id ==_candidat.id ){
				 cpt+= 1; 
				// println(vote.electeur.nom +" vote pour "+elec.nom ) 
			 }
		}
		
		return cpt
	}
	
}