package implementationPlurinominale
import Gvote._
import GUIAbstractComponent.GUIComponentCST



class SystemeDecomptagePlurinomial(_nom : String ,_election : ElectionPlurinominale)  
	extends SystemGeneralDecomptage("Plurinominale"){
	
	type ImplElection = ElectionPlurinominale
	type ImplElecteur = Electeur
	type ImplVote  = VotePlurinominale
	type ImplTour  = TourPlurinominale
	type returnList = List[Candidat]
	type Candidate = Candidat
	override val nom : String = _nom
      
	var GUIType = GUIComponentCST.checkbox
    protected var tabCandidatVote : List[(Candidat,Int)] = List()
		
		// a mettre en cas de soucis
        override protected val election :ElectionPlurinominale = _election
        def initElection(){
        	var a :TourPlurinominale = new TourPlurinominale(tourCourant  , election )
	    	election.tourList  =List(a);
		}
        
        def initCurrentListCandidat(){
            currentListCandidat = election.listCandidat
           election.tourList :+ new TourPlurinominale(tourCourant , election )
        }
        
        def ajouterCandidat(candidat : Eligible) : Boolean ={
          candidat match {
        	case candi: Candidat => return  election .addCandidat(candi)
        	
          }
          return false ; 
        }       
        def cloturerCandidature(){
            election.fermerCandidature()
            election.ouvertureVote()
            initCurrentListCandidat()
            election.getTour(tourCourant).lancerTour()
        }
        
        protected def ajouterVoteByGUIElecteur(electeur : Electeur, candidats : List[(Int,Eligible)]*):Boolean = {
        	
            var candidatList : List[Eligible] = List()
        	
            for(candidat <- candidats.apply(0)){
        		candidatList = candidatList :+ candidat._2
        	}
        	
            candidatList match{	  
        		case candidatListC : List[Candidat] =>
        			return electeur.voter(this, candidatListC)
        		case _ => return false
        	}
        }
        
        def ajouterVote(vote : ImplVote ) : Boolean = {
        // return  election.getTour(tourCourant).addVote(vote)
        	type ImplVote = VotePlurinominale
        	if( vote.listCandidat.length != election.modeScrutin.listGagnantParTour.apply(tourCourant ) ){
        	  println ("le nombre de andidat n'est pas comformea ce tour")
        	  return false
        	}
        	for(elec <- vote.listCandidat ){
        	 var vrai = false 
        	 for(curr <- currentListCandidat )
        	 if( curr.id ==elec.id ) vrai = true  ; 
        	 
        	 if(!vrai)
        	 {
        	   println("les candidats ne figurent pas dans la liste des candidats")
        	   return false 
        	 }
        	}
         
          return  election.getTour(tourCourant ).addVote(vote) ; 
        }
    	def comptabiliser(numeroTour : Int) : Boolean = {
    	    var cpt : Int = 0
    	    val tour = election.getTour(numeroTour)
    	    
    	    if(tour==null) return false
    	    
    	    tabCandidatVote = List()
    	    for(candidat <- currentListCandidat){
    	    	cpt = tour.getNbVote(candidat)
    	    	tabCandidatVote = tabCandidatVote:+(candidat,cpt)
    	    }
    	    return true
    	}

    	def getCandidatAtPos(pos : Int, numeroTour: Int):List[Candidat] = {
    		var position : Int = 0
    		var listCandidatAtPos : List[Candidat] = List()

    		if(!comptabiliser(numeroTour))	return List()

    		for(candidatVoteCourant <- tabCandidatVote){
    			position = 0

    		    for(candidatVote <- tabCandidatVote){
    				if(candidatVoteCourant._1.id != candidatVote._1 .id
				      && candidatVoteCourant._2 < candidatVote._2){
    					position+=1
    				}
    			}
    			
				if(position==pos){
					listCandidatAtPos = listCandidatAtPos:+candidatVoteCourant._1
				}
    		}
    		return listCandidatAtPos
    	}
    	
    	def runTour(){
    		election.getTour(tourCourant).cloturer()
    		currentListCandidat = getGagnantsTour(tourCourant)
    		for(candi <- currentListCandidat )
    		{
    		  println("gagnant tours Courant  "+candi.nom)
    		}
    		tourCourant+=1
    		
    		if(tourCourant == election.modeScrutin.nbTour  ){
    		    terminer = true
    		    println("election finie")
    		}
    		else{
    		  election.tourList :+=new TourPlurinominale(tourCourant  , election);
    		  election.tourList.last.lancerTour();
    		}
    	}

    	final override 	def getGagnantsTour(numeroTour : Int):List[Candidat]={
    	 
	    	var g  = new scala.collection.mutable.Queue[Candidat]
	    	var nbGagnant =  election.modeScrutin.listGagnantParTour.apply(tourCourant) ; 
   			var i = 0 ; 
   		
   			while( nbGagnant > g.length){
   				for(ca <- getCandidatAtPos(i,tourCourant)){
   				  	println("pos = "+i+"  candidat =  "+ca.nom )	
   				}
   			  g ++= getCandidatAtPos(i,tourCourant)
   				
   				i+=1
	  	}
	  
		return  g.toList; 
    	}
     
   override    def getGagnants():List[Candidat] = {
            return currentListCandidat 
   }
}