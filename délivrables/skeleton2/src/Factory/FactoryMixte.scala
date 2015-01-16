package Factory

import Gvote.FactoryCoutingSystem
import Gvote.ScrutinCST
import implementationVoteSimple._
import implementationProportionnel._
import implementationMixte.SystemeDecomptageMixte
import Gvote.ModeScrutin

object FactoryMixte  extends FactoryCoutingSystem{
 
	def generateSystem(modeScrutinProportionnel : ModeScrutin) : SystemeDecomptageMixte = {
		val systemeUninominal = FactoryUninominal.createCountingSystem(1,List(1),modeScrutinProportionnel.visibiliteVote)
		val systemeProportionnel = FactoryProportionnel.generateSystem(modeScrutinProportionnel)
		new SystemeDecomptageMixte("Election mixte",systemeUninominal,systemeProportionnel)
		
	}

	def createCountingSystem(numberOfSeat : Int, visibilite : String) : SystemeDecomptageMixte =
		generateSystem(ScrutinCST.paramProportionnel(numberOfSeat, visibilite))
	
}