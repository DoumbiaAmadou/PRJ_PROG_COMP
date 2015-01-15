package Gvote

class ModeScrutin (_typeScrutin : String , _nbTour  : Int, _listGagnantParTour  : List[Int], _visibiliteVote : String){
	val typeScrutin = _typeScrutin
	val nbTour = _nbTour
	val listGagnantParTour = _listGagnantParTour
	val visibiliteVote = _visibiliteVote
	
	def getNbGagnant(numeroTour : Int) : Int = {
	    getNthElement(numeroTour, listGagnantParTour)
	}
	
	private def getNthElement(n : Int, list : List[Int]) : Int = {
	    list match {
	      case List(a) => if(n==0) a else 0
	      case a::tail => if(n==0) a else getNthElement(n-1, tail)
	    }
	}
}