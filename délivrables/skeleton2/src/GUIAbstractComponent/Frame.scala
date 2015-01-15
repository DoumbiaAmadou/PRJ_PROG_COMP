package GUIAbstractComponent

import Gvote.AbstractElecteur
import Gvote.SystemGeneralDecomptage

class Frame(_title : String, _electeurAndSysteme : List[(AbstractElecteur,SystemGeneralDecomptage)], _layout : String, _contentList : List[FrameContent]){
	private var electeurAndSysteme = _electeurAndSysteme
    var title = _title
    var layout : String = _layout
	var contentList : List[FrameContent] = _contentList
	
	def this(_title : String, _electeurAndSysteme : List[(AbstractElecteur,SystemGeneralDecomptage)], _layout : String){
		this(_title, _electeurAndSysteme, _layout, List())
    }
	
	def getAllElecteurAndSysteme() = electeurAndSysteme
	
	def addContent(interfaceDeVote : FrameContent){
	    contentList = contentList :+ interfaceDeVote
	}
}