package Gvote

object ScrutinCST {
    
	val uninominal = "UNINOMINAL";
	val plurinominal = "PLURINOMINAL";
	val semiProportionnel = "SEMI_PROPORTIONNEL";
	val proportionnel = "PROPORTIONNEL";
	val condorcet = "CONDORCET";

	val public = "public";
	val prive = "prive";
	  
	def paramUninominal(nbTour : Int, nbGagnantParTour : List[Int], visibilite : String) : ModeScrutin = {
		return new ModeScrutin(uninominal,nbTour,nbGagnantParTour,visibilite)
	}
	
	// Pour le semi proportionnel liste de gagnant par tour egale nombre de sieges
	/*
	 * Cette fonction est cree pour le semi proportionnel car on n'a bseoin de savoi
	 * le nombre de siege a  pourvoir
	 */
	def paramSemiProportionnel(nbGagnant : Int, visibilite : String) : ModeScrutin = {
		return new ModeScrutin(semiProportionnel,1,List(nbGagnant),visibilite);
	}
	
	def paramCondorcet(nbGagnant : Int, visibilite : String) : ModeScrutin = {
		return new ModeScrutin(condorcet,1,List(nbGagnant),visibilite)
	}
	
	def paramProportionnel(nbGagnant : Int, visibilite : String) : ModeScrutin = {
	  return new ModeScrutin(proportionnel,1,List(nbGagnant),visibilite)
	}
	
	def paramPlurinominale(nbTour : Int , listgagnantTour:List[Int]) : ModeScrutin = 
		return  new ModeScrutin(plurinominal, 2, listgagnantTour, prive)
		
}
