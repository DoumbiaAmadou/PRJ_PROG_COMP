package Factory

import Gvote.FactoryCoutingSystem
import Gvote.ScrutinCST
import implementationCondorcet._
import Gvote.ModeScrutin

object FactoryCondorcet extends FactoryCoutingSystem {
	
	def generateSystem(modeScrutin : ModeScrutin) : SystemeDecomptageCondorcet =
	    new SystemeDecomptageCondorcet("Election condorcet", new ElectionCondorcet(modeScrutin))
	
	def createCountingSystem(nbGagnant : Int, visibilite : String) : SystemeDecomptageCondorcet =
	  generateSystem(ScrutinCST.paramCondorcet(nbGagnant, visibilite))
}