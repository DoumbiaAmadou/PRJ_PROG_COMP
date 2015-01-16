package test

import Factory.FactoryUninominal
import Gvote._
import implementationVoteSimple._
import GUI._
import org.scalatest.FunSuite

class testUninominal extends FunSuite {
  
        var  systeme : SystemeDecomptageUninominal = FactoryUninominal.createCountingSystem(2, List(2,1), ScrutinCST.prive)
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
        
        
        elec1.voter(systeme, candidat1)
		elec2.voter(systeme, candidat2)
		elec3.voter(systeme, candidat3)
		elec4.voter(systeme, candidat2)
		elec5.voter(systeme, candidat1)
		elec6.voter(systeme, candidat2)
		elec7.voter(systeme, candidat1)
        		
		systeme.runTour()
		
		//si l'election est termine au bout d'un tour, alors un candidat a obtenu plus de 50% des voix
		if(systeme.terminer){
		    var listGagnants : List[Candidat] = systeme.getGagnants()
		    listGagnants = systeme.getGagnants
		}
		else{
			
			println("deuxieme tour")
			
			elec1.voter(systeme, candidat1)
			elec2.voter(systeme, candidat2)
			elec3.voter(systeme, candidat2)
			elec4.voter(systeme, candidat2)
			elec5.voter(systeme, candidat1)
			elec6.voter(systeme, candidat2)
			elec7.voter(systeme, candidat1)
		
			systeme.runTour()
		
			var listGagnants : List[Candidat] = systeme.getGagnants
			println(listGagnants.length+" gagnant(s)")
		}
    
    /*
     * EXPECTED 
     */
    val expectedList : List[Candidat] = List(candidat2)
    
    test("le candidat 2 gagne"){
      assert(systeme.getGagnants() == expectedList)
    }
	
     
}
