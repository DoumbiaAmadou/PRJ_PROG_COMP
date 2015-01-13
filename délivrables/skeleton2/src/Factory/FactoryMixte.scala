package Factory

import Gvote.FactoryCoutingSystem
import Gvote.ScrutinCST
import implementationVoteSimple._
import implementationProportionnel._
import implementationMixte.SystemeDecomptageMixte

object FactoryMixte  extends FactoryCoutingSystem{
 def createCoutingSystem = new SystemeDecomptageMixte("Election mixte",new SystemeDecomptageUninominal("Election uninominal", new Election(ScrutinCST.paramMixte())), new implementationProportionnel.SystemDeComptageProportionel("Election proportionnel", new ElectionProportionnel(ScrutinCST.paramProportionnel(0))));
}