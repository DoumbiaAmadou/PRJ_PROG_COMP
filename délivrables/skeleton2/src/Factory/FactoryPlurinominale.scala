package Factory

import Gvote._

import implementationPlurinominale._



object FactoryPlurinominal extends FactoryCoutingSystem {

	def generateSystem(modeScrutin : ModeScrutin) : SystemeDecomptagePlurinomial =
		new SystemeDecomptagePlurinomial("Election Plurinominale",new ElectionPlurinominale(modeScrutin))
  
	def createCountingSystem(nbTour : Int , listgagnantTour : List[Int]) : SystemeDecomptagePlurinomial = { 
	  assert(nbTour == listgagnantTour.size)  
	 return  generateSystem(ScrutinCST.paramPlurinominale(nbTour,listgagnantTour.sortWith(_>_)));
	}
}
