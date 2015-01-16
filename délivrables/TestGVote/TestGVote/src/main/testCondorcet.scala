package main

import Factory.FactoryCondorcet
import Gvote._
import implementationCondorcet._

object testCondorcet {
  
    def main(args: Array[String]): Unit = {
	   
        var  systeme : SystemeDecomptageCondorcet = FactoryCondorcet.createCountingSystem(1, ScrutinCST.prive)
		println(systeme.nom)
        systeme.initElection
        
        val parti : Parti = new Parti(0, "PARTI")
        
		val candidat1 = new Candidat(1 , "candidat1" ,"candidat1" ,parti)
		candidat1.sePresenter(systeme)
		val candidat2 = new Candidat(2 , "candidat2" ,"candidat2" ,parti)
		candidat2.sePresenter(systeme)
		val candidat3 = new Candidat(3 , "candidat3" ,"candidat3" ,parti)
		candidat3.sePresenter(systeme)
		
		systeme.cloturerCandidature
		
		val elec1 = new  ElecteurCondorcet (1 , "login1","nom1","prenom1","password1")
		val elec2 = new  ElecteurCondorcet (2 , "login2","nom2","prenom2","password2")
		val elec3 = new  ElecteurCondorcet (3 , "login3","nom3","prenom3","password3")
		val elec4 = new  ElecteurCondorcet (4 , "login4","nom4","prenom4","password4")
		val elec5 = new  ElecteurCondorcet (5 , "login5","nom5","prenom5","password5")
        val elec6 = new  ElecteurCondorcet (6 , "login6","nom6","prenom6","password6")
        
		elec1.voter(systeme, List(candidat1,candidat2,candidat3))
		elec2.voter(systeme, List(candidat1,candidat2,candidat3))
		elec3.voter(systeme, List(candidat3,candidat2,candidat1))
		elec4.voter(systeme, List(candidat3,candidat2,candidat1))
		elec5.voter(systeme, List(candidat3,candidat1,candidat2))
		elec6.voter(systeme, List(candidat2,candidat3,candidat1))
		
		//le candidat 3 gagne
		
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