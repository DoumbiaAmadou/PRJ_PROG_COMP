package Factory

import Gvote.FactoryCoutingSystem
import Gvote.ScrutinCST
import implementationVoteSimple.Election
import implementationVoteSimple.SystemeDeComptageSemiProportionel
import Gvote.ModeScrutin

object FactorySemiProportionnel extends FactoryCoutingSystem {
	
	def generateSystem(modeScrutin : ModeScrutin) : SystemeDeComptageSemiProportionel =
	    new SystemeDeComptageSemiProportionel("Election semi proportionnel",new Election(modeScrutin))
	
	def createCountingSystem(nbGagnant : Int, visibilite : String) : SystemeDeComptageSemiProportionel =
	    generateSystem(ScrutinCST.paramSemiProportionnel(nbGagnant, visibilite))
	
}