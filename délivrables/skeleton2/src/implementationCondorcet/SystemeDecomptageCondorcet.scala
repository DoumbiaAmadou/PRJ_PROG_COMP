package implementationCondorcet

import Gvote.SystemGeneralDecomptage
import Gvote.Candidat
import Gvote.ModeScrutin
import Gvote.ScrutinCST
import scala.util.control.Breaks
import Gvote.Eligible
import Gvote.Candidat
import GUIAbstractComponent.GUIComponentCST

class  SystemeDecomptageCondorcet(_nom : String, _electionCondorcet : ElectionCondorcet) extends SystemGeneralDecomptage(_nom){
        type ImplElection = ElectionCondorcet
        type ImplElecteur = ElecteurCondorcet
        type Candidate = Candidat
        type ImplVote = VoteCondorcet
        type returnList = List[Candidat]
        
        var GUIType = GUIComponentCST.listeDeroulante
        
		override protected val election : ElectionCondorcet = _electionCondorcet
        //var listVotant : List[ElecteurCondorcet] = List()
         //liste des candidats (non elimines), a chaque tour, associes a leur pire et meilleur score face a un autre candidat
		var listdeslistedeCandidat:List[List[(Candidat,Int,Int)]] = List()
        var tabCandidatVote : List[(Candidat,Int,Int)] = List()
                
        def initElection(){
            var nb : Int = election.modeScrutin.nbTour
            
            while(nb>0){
            	election.tourList = election.tourList:+(new TourCondorcet(election))
            	nb-=1
            }
        }
        
        def initCurrentListCandidat(){
            currentListCandidat = election.listCandidat
        }
        
        def ajouterCandidat(candidat : Eligible) : Boolean = {
        	candidat match{
        	  case cand : Candidat => return election.addCandidat(cand)
        	}
            
            return false
        }
        
        def cloturerCandidature(){
            election.fermerCandidature()
            election.ouvertureVote()
            initCurrentListCandidat()
            election.tourList.apply(tourCourant).lancerTour()
        }
        
        protected def ajouterVoteByGUIElecteur(electeur : ElecteurCondorcet, candidats : List[(Int,Eligible)]*):Boolean = {
        	
            var candidatListFinal : List[Eligible] = List()
        	
        	for(candidat <- candidats.apply(0)){
        	    	candidatListFinal = candidatListFinal :+ candidat._2
        	 }
        	candidatListFinal match{	  
        		case candidatListC : List[Candidat] =>
        			return electeur.voter(this, candidatListC)
        		case _ => return false
        	}
        }
        
        def ajouterVote(vote : VoteCondorcet) : Boolean = {
            /*var ok : Boolean = false
            if(vote.candidats.length == currentListCandidat.length)
            	for(candidat <- currentListCandidat){
            	    for(cand <- vote.candidats){
            	    	
            	    }
            	}*/
                return election.tourList.apply(tourCourant).addVote(vote)
            //}
        	//}
            return false
        }
        
    	def comptabiliser (numeroTour : Int) : Boolean = {
        	
    	    val tour : TourCondorcet = election.tourList.apply(numeroTour)
    	    
    	    if(tour==null) return false
    	    
    	    var min : Int = 0
        	var max : Int = 0
    	    var v : Int = 0
    	    
    	    tabCandidatVote = List()
    	    for(cand1 <- currentListCandidat){
    	        for(cand2 <- currentListCandidat){
    	        	if(cand1.id != cand2.id){
    	        	    v = tour.compareVote(cand1, cand2)
    	        	    if(min>v) min = v;
    	        	    if(max<v) max = v;
    	        	}
    	        }
    	        
    	        tabCandidatVote = tabCandidatVote:+(cand1,min,max)
    	        min = 0
    	        max = 0
    		}
        	
        	return true
    	}
    	
    	def getCandidatAtPos(pos : Int, numeroTour: Int):List[Candidat] = {
    		var position : Int = 0
    		var listCandidat : List[Candidat] = election.listCandidat
    		var listCandidatAtPos : List[Candidat] = List()
    		
    		if(!comptabiliser(numeroTour))	return List()
		
    		for(scoreCand1 <- tabCandidatVote){
    		    position = 0
    		  
    	        for(scoreCand2 <- tabCandidatVote){
    	        	if(scoreCand1._1.id != scoreCand2._1.id){
    	        	    if(scoreCand1._2 < scoreCand2._2)
    	        	        position += 1
    	        	    else if(scoreCand1._2 == scoreCand2._2){
    	        	        if(scoreCand1._3 < scoreCand2._3)
    	        	            position += 1
    	        	    }
    	        	}
    	        }
    	        
    	        if(position == pos){
					listCandidatAtPos = listCandidatAtPos:+scoreCand1._1
				}  
    		}
    		return listCandidatAtPos
    	}
    	
    	def runTour(){
    	  
    	    election.tourList.apply(tourCourant).cloturer()
    		
    		var candidatGagnants : List[Candidat] = getGagnantsTour(tourCourant)
    	    var scoreList : List[(Candidat,Int,Int)] = List()
    		var loop : Breaks = new Breaks
    		
    		for(candGagnant <- currentListCandidat){
    		    loop.breakable{
    		    	for(scoreCandidat <- tabCandidatVote){
    		        	if(candGagnant.id == scoreCandidat._1.id){
    		            	scoreList = scoreList :+ scoreCandidat
    		            	loop.break
    		        	}
    		    	}
    		    }
    		}
    		
    		listdeslistedeCandidat = listdeslistedeCandidat:+scoreList
    		
    		currentListCandidat = candidatGagnants
    		
    		tourCourant+=1
    		
    		if(tourCourant < election.tourList.length){
    			for(candidat <-  currentListCandidat ){	
    		    	println(candidat.nom +" a passe le tour "+tourCourant)
    			}
    			election.tourList.apply(tourCourant).lancerTour()
    		}
    		
    		else{
    		    for(candidat <-  currentListCandidat ){	
    		    	println(candidat.nom +" a gagne")
    			}
    		    terminer = true
    		    println("l'election est termine")
    		}
    	}
    	
    	def getGagnantsTour(numeroTour : Int):List[Candidat]={
    		
    	  val tour = election.tourList.apply(numeroTour)
	    	
	    	if(tour == null){
	    	    println("null")
	    	    return List()
	    	}
	    	
	    	var gagnants : List[Candidat] = List()
	    	
	    	var pos : Int = 0
	    	var nbCandidat : Int = election.modeScrutin.listGagnantParTour.apply(numeroTour)

	    	while(gagnants.length<nbCandidat && gagnants.length != currentListCandidat.length){
	    		gagnants = gagnants++getCandidatAtPos(pos,numeroTour)
	    		pos+=1
	    	}
	  
	    	return gagnants
		}
    
    def getGagnants():List[Candidat] = {
        if(terminer){
            return currentListCandidat 
        }
        return List()
    }
}
