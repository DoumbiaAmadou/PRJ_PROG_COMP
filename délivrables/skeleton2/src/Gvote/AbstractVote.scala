package Gvote


abstract class AbstractVote(_electeur : AbstractElecteur, _systeme : SystemGeneralDecomptage){
		val electeur = _electeur
		val systeme = _systeme
}