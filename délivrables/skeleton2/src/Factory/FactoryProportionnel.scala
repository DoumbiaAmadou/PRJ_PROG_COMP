package Factory

import Gvote.FactoryCoutingSystem
import implementationProportionnel.SystemDeComptageProportionel
import implementationVoteSimple.Election
import Gvote.ScrutinCST
import implementationProportionnel.ElectionProportionnel
import Gvote.ModeScrutin

object FactoryProportionnel extends FactoryCoutingSystem {

	def generateSystem(modeScrutin : ModeScrutin) : SystemDeComptageProportionel =
		new SystemDeComptageProportionel("Election proportionnel", new ElectionProportionnel(modeScrutin));
	
	def createCountingSystem(numberOfSeat : Int, visibilite : String) : SystemDeComptageProportionel =
		generateSystem(ScrutinCST.paramProportionnel(numberOfSeat, visibilite));
	
}