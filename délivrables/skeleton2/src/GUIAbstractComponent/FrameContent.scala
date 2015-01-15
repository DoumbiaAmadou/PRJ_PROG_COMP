package GUIAbstractComponent

import Gvote.Eligible
import Gvote.SystemGeneralDecomptage
import Gvote.Candidat
import Gvote.AbstractElecteur

class FrameContent(_electeurAndSysteme : (AbstractElecteur,SystemGeneralDecomptage), _listChoix : List[(String,Eligible)]){
    val electeurAndSysteme = _electeurAndSysteme
    var listChoix : List[(String,Eligible)] = _listChoix
}