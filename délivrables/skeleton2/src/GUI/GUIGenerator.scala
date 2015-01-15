package GUI

import Gvote._
import GUIAbstractComponent._

class GUIGenerator(_electeurAndSysteme : List[(AbstractElecteur,SystemGeneralDecomptage)], _GUIGeneratorToolkit : GUIAbstractGeneratorToolkit) {
	val electeurAndSysteme = _electeurAndSysteme
    //val systemeList : List[SystemGeneralDecomptage] = _systemeList
    val GUIGeneratorToolkit : GUIAbstractGeneratorToolkit = _GUIGeneratorToolkit
    var frame : Frame = null

    protected def initializeFrame(layout : String){

		frame = new Frame(electeurAndSysteme.apply(0)._1.nom ,electeurAndSysteme, layout)
		var interfaceDeVote : FrameContent = null
	  
	    for(elecAndSyst <- electeurAndSysteme){
		    var listChoix : List[(String,Eligible)] = List()
		    for(candidat <- elecAndSyst._2.getCandidats){
		    	listChoix = listChoix :+ (candidat.nom,candidat)
		    }
			
		    interfaceDeVote = new FrameContent(elecAndSyst, listChoix)
		    
		    frame.addContent(interfaceDeVote)
		    
	    }
		
	}
	def generateFrame(){
		generateFrame("")
	}
	def generateFrame(frameLayout : String){
	    initializeFrame(frameLayout)
	    GUIGeneratorToolkit.generateFrame(frame)
	}
}