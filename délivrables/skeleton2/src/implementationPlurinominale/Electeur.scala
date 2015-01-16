package implementationPlurinominale
import Gvote._



class Electeur(_id: Int, var login : String, _nom : String , _prenom: String, var password : String)extends AbstractElecteur(_id,_nom,_prenom){
	type ImplVote = VotePlurinominale
	type ImplSystemeDecomptage = SystemeDecomptagePlurinomial
	type Candidate = Candidat
	
	def voter(systemeElection : SystemeDecomptagePlurinomial, candidat : Candidat) : Boolean = {
	return false 
	}
	
	def voter(systemeElection : SystemeDecomptagePlurinomial, candidats : List[Candidat]) : Boolean = {
		var vote : VotePlurinominale = new VotePlurinominale(this,systemeElection,candidats)
		if(systemeElection.ajouterVote(vote)){
			mesVotes = mesVotes:+vote
			return true
		}
		else{
			return false
		}
	}

}