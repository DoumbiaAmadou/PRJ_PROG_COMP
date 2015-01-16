package implementationPlurinominale

import Gvote._

class ElectionPlurinominale(_modeScrutin : ModeScrutin ) extends AbstractElection(_modeScrutin){
	  type ImplTour = TourPlurinominale
	 type Candidate = Candidat
	var systemeDecompt : SystemeDecomptagePlurinomial= null 
	//var tourList : List[AbstractTour] = List()
	
	def this(modeScrutin : String, nbTour : Int, listGagnantParTour : List[Int]  ,visibiliteVote : String , _systemeDecompteage : SystemeDecomptagePlurinomial){
		this(new  ModeScrutin(modeScrutin, nbTour, listGagnantParTour,visibiliteVote) );
	}	
	
	def getTour(n : Int) : ImplTour = {
	  return getTour(n,tourList)
	}
	
	def getTour (n:Int, list :List[ImplTour]) : ImplTour = {
	// on peut acceder directement a la list avec des index 
	  return  list.apply(n);
	}
	
	def addSysdecompte(sys : SystemeDecomptagePlurinomial ) : Boolean= {
	  if( systemeDecompt  == null) 
	  { 
	    systemeDecompt =sys
	      true 
	  } 
	  else  
	     false
		}
}