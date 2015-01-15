package implementationVoteSimple

import Gvote.AbstractElecteur
import Gvote.SystemGeneralDecomptage
import Gvote.Candidat

class Electeur(_id: Int, var login : String, _nom : String , _prenom: String, var password : String)
		extends AbstractElecteur(_id,_nom,_prenom){
	type ImplVote = Vote
	type ImplSystemeDecomptage = SystemeDecomptageSimple
	type Candidate = Candidat
  
	def voter(systemeElection : SystemeDecomptageSimple, candidat : Candidat) : Boolean = {
		val vote : Vote = new Vote(this,systemeElection,candidat)
		if(systemeElection.ajouterVote(vote)){
			mesVotes = mesVotes:+vote
			return true
		}
		else{
			return false
		}
	}
	
	def voter(systemeElection : SystemeDecomptageSimple, candidats : List[Candidat]) : Boolean = {
	    return false
	}
	
	
}