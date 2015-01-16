package Factory

import Gvote.FactoryCoutingSystem
import Gvote.ScrutinCST
import implementationVoteSimple.Election
import implementationVoteSimple.SystemeDecomptageUninominal
import Gvote.ModeScrutin

object FactoryUninominal extends FactoryCoutingSystem {
	
	def generateSystem(modeScrutin : ModeScrutin) : SystemeDecomptageUninominal =
	    new SystemeDecomptageUninominal("Election uninominal", new Election(modeScrutin))
	
	def createCountingSystem(nbTour : Int, nbGagnantParTour : List[Int], visibilite : String) : SystemeDecomptageUninominal ={
	  assert(nbTour == nbGagnantParTour.size)   
	 return  generateSystem(ScrutinCST.paramUninominal(nbTour, nbGagnantParTour.sortWith(_ >_), visibilite)) ; 
	}
	
}
