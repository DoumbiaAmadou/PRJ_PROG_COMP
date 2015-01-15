package Gvote


class Candidat(_id : Int, _nom :String , _prenom : String , _parti : Parti) extends Eligible(_id, (_nom+" "+_prenom)){

	var parti : Parti = _parti
	
	def this(_id : Int, _nom :String , _prenom : String){
	  this(_id, _nom, _prenom, null)
	}
	
	def sePresenter(systemeElection : SystemGeneralDecomptage) : Boolean = {
		return systemeElection.ajouterCandidat(this)
	}
	
}