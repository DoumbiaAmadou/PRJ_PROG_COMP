package main

import Factory.FactoryUninominal
import Gvote._
import implementationVoteSimple._
import GUI._

object testUninominal {
  
    def main(args: Array[String]): Unit = {

        var  systeme : SystemeDecomptageUninominal = FactoryUninominal.createCoutingSystem(2, List(2,1), ScrutinCST.prive)
		println(systeme.nom)
        systeme.initElection
        
        val parti : Parti = new Parti(0,"PARTI")
        
		val candidat1 = new Candidat(1 , "CNOM1" ,"CPRENOM1", parti)
		candidat1.sePresenter(systeme)
		val candidat2 = new Candidat(2 , "CNOM2" ,"CPRENOM2", parti)
		candidat2.sePresenter(systeme)
		val candidat3 = new Candidat(3 , "CNOM3" ,"CPRENOM3", parti)
		candidat3.sePresenter(systeme)
		
		systeme.cloturerCandidature
		
		val elec1 = new  Electeur (1 , "login1","nom1","prenom1","password1")
		val elec2 = new  Electeur (2 , "login2","nom2","prenom2","password2")
		val elec3 = new  Electeur (3 , "login3","nom3","prenom3","password3")
		val elec4 = new  Electeur (4 , "login4","nom4","prenom4","password4")
		val elec5 = new  Electeur (5 , "login5","nom5","prenom5","password5")
        val elec6 = new  Electeur (6 , "login6","nom6","prenom6","password6")
        val elec7 = new  Electeur (7 , "login7","nom7","prenom7","password7")

        println("premier tour")
        
        GUI_IHMSwing.runVote(List((elec1,systeme)))
        GUI_IHMSwing.runVote(List((elec2,systeme)))
        GUI_IHMSwing.runVote(List((elec3,systeme)))
        GUI_IHMSwing.runVote(List((elec4,systeme)))
        GUI_IHMSwing.runVote(List((elec5,systeme)))
        GUI_IHMSwing.runVote(List((elec6,systeme)))
        GUI_IHMSwing.runVote(List((elec7,systeme)))
		
		systeme.runTour()
		
		//si l'election est termine au bout d'un tour, alors un candidat a obtenu plus de 50% des voix
		if(systeme.terminer){
		    var listGagnants : List[Candidat] = systeme.getGagnants()
		    listGagnants = systeme.getGagnants
		}
		else{
			
			println("deuxieme tour")
			
		GUI_IHMSwing.runVote(List((elec1,systeme)))
        GUI_IHMSwing.runVote(List((elec2,systeme)))
        GUI_IHMSwing.runVote(List((elec3,systeme)))
        GUI_IHMSwing.runVote(List((elec4,systeme)))
        GUI_IHMSwing.runVote(List((elec5,systeme)))
        GUI_IHMSwing.runVote(List((elec6,systeme)))
        GUI_IHMSwing.runVote(List((elec7,systeme)))
		
			systeme.runTour()
		
			var listGagnants : List[Candidat] = systeme.getGagnants
			println(listGagnants.length+" gagnant(s)")
		}
    }
     
}
