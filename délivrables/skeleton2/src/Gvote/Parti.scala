package Gvote


class Parti(_id : Int, _nom : String)  extends Eligible(_id, _nom){
	
    //type ImplSysteme = ??
  
    var listCandidat : List[Candidat] = List()
	
	def addCandidat(candidat : Candidat){
      	candidat.parti = this
	    listCandidat = listCandidat :+ candidat
	}
	
	def sePresenter(systemeElection : SystemGeneralDecomptage) : Boolean = {
		return systemeElection.ajouterCandidat(this);
	}
	
}