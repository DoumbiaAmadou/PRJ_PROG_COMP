package implementationMixte

import Gvote._
import implementationProportionnel._
import scala.collection.mutable.MutableList
import implementationVoteSimple.SystemeDecomptageUninominal
import scala.math.BigDecimal.int2bigDecimal
import GUIAbstractComponent.GUIComponentCST
class SystemeDecomptageMixte(_nom : String,uninomi : SystemeDecomptageUninominal, propor : implementationProportionnel.SystemDeComptageProportionel ) extends SystemGeneralDecomptage(_nom){

	type ImplElection = ElectionProportionnel
	type returnList = List[(Parti,Int)];
	type ImplElecteur = ElecteurMixte
	
	val uninominal = uninomi;
	val proportionnel = propor;
	override protected val election = null
	var GUIType = GUIComponentCST.undefined
	var unigagnant : List[Candidat] = List();
	var proprgagnant : MutableList[(Parti,BigDecimal,BigDecimal,List[Candidat])] = MutableList();
	var listResultat : List[(Parti,Int)] = List();
	var nbrSiege : BigDecimal = proportionnel.numberOfSeat*2;
	var p : Parti = null;
	var listParti : List[(Parti)] = List();
	//var listWinners : MutableList[(Parti,BigDecimal,BigDecimal,List[Candidat])] = MutableList();
	var i : Int = 0;

	def initElection(){
		uninominal.initElection();
		proportionnel.initElection();
	}

	def ajouterCandidat(candidat : Eligible) : Boolean = {
			candidat match {
			case candi: Candidat => return uninominal.ajouterCandidat(candi);
			case parti: Parti => listParti:+parti;  return proportionnel.ajouterCandidat(parti);
			}
			return false;
	}

	protected def ajouterVoteByGUIElecteur(electeur : ElecteurMixte, candidats : List[(Int,Eligible)]*):Boolean = {
    	
	    var ok1 = true
	    var ok2 = true
	    
	    if(candidats.length != 2) return false
	    
	    for(candidatOrPartiList <- candidats){
    		candidatOrPartiList match{
    	  	case parti : List[(Int,Parti)] =>
    	  		if(parti.length == 1){
    	  		    ok1 = electeur.elecPro.voter(proportionnel, parti.apply(0)._2)
    	  		}
    	  		else return false
    	  	case candidat : List[(Int,Candidat)] =>
    	  		if(candidat.length == 1){
    	  			 ok2 = electeur.elecUni.voter(uninominal, candidat.apply(0)._2)
    	  		}
    	  		else return false
    		}
    	}
    	
    	return (ok1 && ok2)
    }
	
	def ajouterVote(vote : ImplVote ) :Boolean = { 
			return true;
	}
	def cloturerCandidature: Unit = {
			uninominal.cloturerCandidature();
			proportionnel.cloturerCandidature();
	}
	def comptabiliser(numeroTour: Int): Boolean = {
			uninominal.comptabiliser(0);
			return true;
	}

	def runTour(){

		uninominal.runTour();
		unigagnant = uninominal.getGagnants();
		proportionnel.runTour();    
		proprgagnant = proportionnel.getGagnants();
		for(candidat <- unigagnant){
			p = candidat.parti;
		}
		for(parti <- proprgagnant){
			if(parti._1.nom == p.nom){
				i = parti._2.intValue();
				listResultat= listResultat:+(parti._1,(i+nbrSiege/2).intValue());
			}
      if(parti._1.nom != p.nom && parti._2.intValue() > 0)
				listResultat=listResultat:+(parti._1,parti._2.intValue());
    }
	}
	def getGagnantsTour(numeroTour : Int):List[(Parti,Int)] = {
			return null;
	}
	def getGagnants() : List[(Parti,Int)] = {
			return listResultat;

	}

	def getCandidatAtPos(pos: Int,numeroTour: Int): List[Candidate] = { 
			return null;
	}


}