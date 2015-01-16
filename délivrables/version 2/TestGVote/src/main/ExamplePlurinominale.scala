package main

import Factory.FactoryPlurinominal
import Gvote._
import implementationPlurinominale._
import GUI._
import scala.swing.Dialog

object  ExamplePlurinominale {
	 def main(args: Array[String]): Unit = {
	   
        var  systeme : SystemeDecomptagePlurinomial = FactoryPlurinominal.createCountingSystem(2, List(2,1))
		println(systeme.nom)
        systeme.initElection
        var ump : Parti =    new Parti(1 , "UMP")
        val candidat1 = new Candidat(1 , "candidat1" ,"candidat1"  , ump)
		candidat1.sePresenter(systeme)
		val candidat2 = new Candidat(2 , "candidat2" ,"candidat2" , ump)
		candidat2.sePresenter(systeme)
		val candidat3 = new Candidat(3 , "candidat3" ,"candidat3" , ump)
		candidat3.sePresenter(systeme)
		
		systeme.cloturerCandidature
		
		val elec1 = new  Electeur (1 , "login1","nom1","prenom1","password1")
		val elec2 = new  Electeur (2 , "login2","nom2","prenom2","password2")
		val elec3 = new  Electeur (3 , "login3","nom3","prenom3","password3")
		val elec4 = new  Electeur (4 , "login4","nom4","prenom4","password4")

        println("premier tour")
        
        GUI_IHMSwing.runVote(List((elec1,systeme)))
        GUI_IHMSwing.runVote(List((elec2,systeme)))
        GUI_IHMSwing.runVote(List((elec3,systeme)))
        GUI_IHMSwing.runVote(List((elec4,systeme)))
        
		systeme.runTour()
		println("Deuxieme tour")
		
		GUI_IHMSwing.runVote(List((elec1,systeme)))
        GUI_IHMSwing.runVote(List((elec2,systeme)))
        GUI_IHMSwing.runVote(List((elec3,systeme)))
        GUI_IHMSwing.runVote(List((elec4,systeme)))
        
        /**
         * verifier le vote de l'electeur 7 qui n'a pas voté
         *
         *
         */
        var msg = ""
        if(systeme.verifVote(7, 1)) msg = "vote 7 validé"
        else msg = "vote 7 non validé"
        Dialog.showMessage(null , msg , " VERIFICATION DE VALIDATION DE VOTE")
        
        
		systeme.runTour()
		if(systeme.terminer){
		    var listGagnants : List[Candidat] = systeme.getGagnants()
		    listGagnants = systeme.getGagnants
		    println(listGagnants.length+" gagnant(s)")
		}
		else{		
			println("probleme")
		}
		
		
    }
}