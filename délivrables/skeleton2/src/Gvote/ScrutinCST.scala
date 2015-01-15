package Gvote

object ScrutinCST {
    
	val uninominal = "UNINOMINAL";
	val plurinominal = "PLURINOMINAL";
	val semiProportionnel = "SEMI_PROPORTIONNEL";
	val proportionnel = "PROPORTIONNEL";
	val condorcet = "CONDORCET";

	val public = "public";
	val prive = "prive";
	  
	def paramUninominal() : ModeScrutin = {
		return new ModeScrutin(uninominal,2,List(2,1),prive)
	}
	// Pour le semi proportionnel liste de gagnant par tour égale nombre de sièges
	/*
	 * Cette fonction est crée pour le semi proportionnel car on n'a bseoin de savoi
	 * le nombre de siège à pourvoir
	 */
	def paramSemiProportionnel(x : Int) : ModeScrutin = {
		return new ModeScrutin(semiProportionnel,1,List(x),prive);
	}
	def paramCondorcet() : ModeScrutin = {
		return new ModeScrutin(condorcet,1,List(1),prive)
	}
	
	def paramProportionnel(x : Int) : ModeScrutin = {
	  return new ModeScrutin(proportionnel,1,List(x),prive)
	}
  def paramMixte(): ModeScrutin = {
    return new ModeScrutin(uninominal,1,List(2,1),prive)
  }
	
	def paramPlurinominale(nbTour : Int , listgagnantTour:List[Int]) : ModeScrutin = 
	// if(nbTour == listgagnantTour.length /*&& validePlurinominal(list)*/)
	return  new ModeScrutin(plurinominal, 2, listgagnantTour, prive)
	
	//def validePlurinominal(list: List[Int] ): Boolean
	
}
