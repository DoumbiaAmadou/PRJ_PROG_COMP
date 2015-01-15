package GUI

import Gvote.AbstractElecteur
import Gvote.SystemGeneralDecomptage

object GUI_IHMSwing{
     
    def runVote(electeurAndSystemes : List[(AbstractElecteur, SystemGeneralDecomptage)]) {
      	var generator = new GUIGenerator(electeurAndSystemes, new GUIGeneratorSwing)
      	generator.generateFrame()
    }
}