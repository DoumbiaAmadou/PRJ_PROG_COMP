package implementationVoteSimple

import scala.util.control.Breaks
import Gvote.Candidat

class  SystemeDecomptageUninominal(_nom : String, election : Election) extends SystemeDecomptageSimple(election, _nom){
        
		type ImplElecteur = Electeur
        type returnList = List[Candidat]
        
		//override protected val election : Election = _election
		protected var currentListCandidat : List[Candidat] = List()
        //liste des candidats (non elimines), a chaque tour, associes a leur nombre de vote
		var listdeslistedeCandidat:List[List[(Candidat,Int)]] = List()
        var tabCandidatVote : List[(Candidat,Int)] = List()
        protected var nbvotant = 0 ; 
        
        def initElection(){
        	var nb : Int = election.modeScrutin.nbTour
        	election.tourList = List()
            while(nb>0){
            	election.tourList = election.tourList:+(new Tour(election))
            	nb-=1
            }
		}
        
        def initCurrentListCandidat(){
            currentListCandidat = election.listCandidat
        }
        
        def cloturerCandidature(){
            election.fermerCandidature()
            election.ouvertureVote()
            initCurrentListCandidat()
            election.tourList.apply(tourCourant).lancerTour()
        }
        
        def ajouterVote(vote : Vote) : Boolean = {
        	for(candidat <- currentListCandidat){
        	    if(vote.candidat.id == candidat.id)
                    return election.tourList.apply(tourCourant).addVote(vote)
        	}
            return false
        }
        
        
    	def comptabiliser (numeroTour : Int) : Boolean = {
    	    var cpt : Int = 0
    	    val tour = election.tourList.apply(numeroTour)
    	    
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
    		var tour = election.tourList.apply(tourCourant)
    		tour.cloturer()
    		
    		var candidatGagnants : List[Candidat] = getGagnantsTour(tourCourant)
    		
    		var scoreList : List[(Candidat,Int)] = List()
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
    		
    		var nbVotePremier : Float = tour.getNbVote(candidatGagnants.head)
		    var nbVoteTotal : Float = tour.getNbVoteTotal 
		    var pourcentage : Float = nbVotePremier/nbVoteTotal*100
		    if(pourcentage>=50 && getCandidatAtPos(0, tourCourant).length==1){
		        candidatGagnants = List(candidatGagnants.head)
		        tourCourant = election.tourList.length
		    }
    		
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
    	
    	def getGagnantsTour(numeroTour : Int):List[Candidat] = {
    	 
	    	val tour = election.tourList.apply(numeroTour)
	    	
	    	if(tour == null){
	    	    println("null")
	    	    return List()
	    	}
	    	
	    	var gagnants : List[Candidat] = List()
	    	
	    	var pos : Int = 0
	    	var nbCandidat : Int = election.modeScrutin.getNbGagnant(numeroTour)

	    	while(gagnants.length<nbCandidat && gagnants.length != currentListCandidat.length){
	    		gagnants = gagnants++getCandidatAtPos(pos,numeroTour)
	    		pos+=1
	    	}
	  
	    	return gagnants
		}
    def contains(vote : Vote): Boolean  ={
    		comptabiliser(tourCourant )
    		for(v <- tabCandidatVote ) {
    		  if(v._1.id == vote.candidat.id  && v._2>= nbvotant )
    		    return false 
    		} 
    		return true
    }
    def getGagnants():List[Candidat] = {
        if(terminer){
            return currentListCandidat
        }
        return List()
    }
}
