package Gvote

abstract class Eligible(_id : Int, _nom :String){
  
    val id = _id
    val nom = _nom
    
    def sePresenter(systemeElection : SystemGeneralDecomptage) : Boolean

}