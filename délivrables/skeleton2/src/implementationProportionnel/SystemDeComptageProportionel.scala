package implementationProportionnel

import scala.collection.mutable.MutableList
import scala.collection.AbstractSeq
import Factory.FactoryProportionnel
import scala.util.control._
import Gvote.Candidat
import Gvote.Parti
import implementationVoteSimple.Electeur
import implementationVoteSimple.Election
import implementationVoteSimple.SystemeDecomptageSimple
import implementationVoteSimple.Vote
import implementationVoteSimple.Tour
import Gvote.SystemGeneralDecomptage
import Gvote.Eligible
import Gvote.Candidat
import Gvote.Candidat
import GUIAbstractComponent.GUIComponentCST

final class SystemDeComptageProportionel(_nom : String, electionProp : ElectionProportionnel) extends SystemGeneralDecomptage(_nom) {
	type ImplElecteur = ElecteurProportionnel;
	type returnList = AbstractSeq[_];
	type Candidate = Parti;
  	type ImplElection = ElectionProportionnel
	type ImplVote = VoteProportionnel;

  	var GUIType = GUIComponentCST.radio;
	var tabCandidatVote : List[(Parti,BigDecimal)] = List(); //Same Uninomial
	var numberOfVote : BigDecimal = 0;
	var numberOfSeat : BigDecimal =  election.modeScrutin.listGagnantParTour.apply(0)
	var electoralQuot : BigDecimal = 0;
	var listSortByQuot : MutableList[(Parti,BigDecimal,BigDecimal,BigDecimal)] = MutableList();
	var listSortByRemind : MutableList[(Parti,BigDecimal,BigDecimal,BigDecimal)] = MutableList();
	var listWinners : MutableList[(Parti,BigDecimal,BigDecimal,List[Candidat])] = MutableList();
	override protected val election : ElectionProportionnel = electionProp;
	tourCourant = 0;


	def ajouterCandidat(candidat : Eligible) : Boolean = {

    candidat match{
        case cand : Parti => {
          return election.addCandidat(cand);
        }
      }
			return false;
	}
	 def initElection() = {
		election.tourList = List(new TourProportionnel(election));
	}

	def initCurrentListCandidat(){
		currentListCandidat = election.listCandidat;
		//println("length "+currentListCandidat.length);
	}

	def cloturerCandidature(){
		election.fermerCandidature();
		election.ouvertureVote();
		initCurrentListCandidat();
		election.tourList.apply(tourCourant).lancerTour();
	}

	protected def ajouterVoteByGUIElecteur(electeur : ElecteurProportionnel, candidats : List[(Int,Eligible)]*):Boolean = {
    	candidats.apply(0) match{
    	  	case candidat : List[(Int,Parti)] =>
    	  		if(candidat.length == 1){
    	  			 return electeur.voter(this, candidat.apply(0)._2)
    	  		}
    	}
    	
    	return false
    }
	
	def ajouterVote(vote : VoteProportionnel) : Boolean = {
    type ImplVote = VoteProportionnel;
			numberOfVote += 1.0;
			//return election.tourList.apply(tourCourant).addVote(vote);
      return election.tourList.apply(tourCourant).addVote(vote);
	}
	def comptabiliser (numeroTour : Int) : Boolean ={
			electoralQuot  = numberOfVote / numberOfSeat ;
			var cpt : BigDecimal = 0;
			val tour = election.tourList.apply(numeroTour);
			if(tour == null)
				return false;
			for(candidat <- currentListCandidat){
				cpt = tour.getNbVote(candidat);
				tabCandidatVote = tabCandidatVote:+(candidat,cpt);

			}
			return true;
	}

	def getCandidatAtPos(pos : Int, numeroTour : Int):List[Candidate] = return null;

	def runTour(){	
		election.tourList.apply(tourCourant).cloturer();
		if(tourCourant == election.tourList.length){
			terminer = true;
		}
	}

   //   def getGUIType = GUIType


	def getGagnantsTour(i : Int): List[Candidat]= {
			return null;
	}

	def getGagnants(): MutableList[(Parti,BigDecimal,BigDecimal,List[Candidat])] = {
			var candidatWithQuotAndRemind : MutableList[(Parti,BigDecimal,BigDecimal,BigDecimal)] = MutableList();

	comptabiliser(tourCourant);

	var remainder : BigDecimal = 0;
	var quot : BigDecimal = 0;
	for(candidats <- tabCandidatVote ){	
		remainder = candidats._2 .remainder(electoralQuot );
		quot = candidats._2 .quot(electoralQuot );
		candidatWithQuotAndRemind.+=:((candidats._1 ,candidats._2 , remainder, quot.doubleValue));

	}

	listSortByQuot = candidatWithQuotAndRemind.sortBy(x => x._4);
	listSortByRemind  = candidatWithQuotAndRemind.sortBy(x => x._3 ).reverse;
	val loop = new Breaks;
	var foundIt = false;
	var seat : BigDecimal = 0;
	loop.breakable{
		for(quot <- listSortByQuot ){		
			numberOfSeat -= quot._4;
			listWinners.+=:((quot._1,quot._4,quot._3,quot._1.listCandidat.take(quot._4.intValue())));	
			if(numberOfSeat == 0)
				return listWinners ;		
		}
	}



	var exList = listWinners .sortBy(x => x._3).reverse;
	var win : MutableList[(Candidat,BigDecimal)] = MutableList();
	loop.breakable{
		for(i  <- 0 to exList.length ){
			seat = exList.apply(i)._2 + 1; 
			numberOfSeat -= 1;
			exList.update(i,(exList.apply(i)._1,seat,exList.apply(i)._3,exList.apply(i)._1.listCandidat.take(seat.intValue())))

			if(numberOfSeat.equals(0.0)){
				//println("no2");
				loop.break;
			}
		}

	}
  var listOfWinner : MutableList[(Parti,BigDecimal,BigDecimal,List[Candidat])] = MutableList();
 for(e <- exList){ 
    listOfWinner.+=:((e._1,e._2,e._3.doubleValue(),e._1.listCandidat.take(e._2.intValue())));
  //  for(d <- e._1.listCandidat.take(e._2.intValue()).reverse)
    //  println(d.nom)
  }
	return listOfWinner;
	}

}