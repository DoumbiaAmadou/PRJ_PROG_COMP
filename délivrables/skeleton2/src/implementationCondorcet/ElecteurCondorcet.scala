package implementationCondorcet

import Gvote.AbstractElecteur
import Gvote.Candidat

class ElecteurCondorcet(_id: Int, var login : String, _nom : String , _prenom: String, var password : String)
		extends AbstractElecteur(_id,_nom,_prenom){
	type ImplVote = VoteCondorcet
	type ImplSystemeDecomptage = SystemeDecomptageCondorcet
	type Candidate = Candidat
  
	def voter(systemeElection : SystemeDecomptageCondorcet, candidat : Candidat) : Boolean = {
		return false
	}
	
	def voter(systemeElection : SystemeDecomptageCondorcet, candidats : List[Candidat]) : Boolean = {
	    var voteList : List[(Candidat,Int)] = List()
	    var numero : Int = 0;
	    
	    for(candidat <- candidats){
	        voteList = voteList:+(candidat,numero)
	        numero+=1
	    }
	    
	    val voteCondorcet = new VoteCondorcet(this,systemeElection,voteList) 
	    
	    if(systemeElection.ajouterVote(voteCondorcet)){
			mesVotes = mesVotes:+voteCondorcet
			return true
		}
		else{
		    println("error vote electeur")
			return false
		}
	}
	
}