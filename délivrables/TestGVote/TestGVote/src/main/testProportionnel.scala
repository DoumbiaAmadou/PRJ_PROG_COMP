package main

import implementationProportionnel._
import Factory.FactoryProportionnel
import Gvote._
import scala.collection.mutable.MutableList
import org.scalatest.FunSuite

class testProportionnel extends FunSuite{
  
    var system : SystemDeComptageProportionel = FactoryProportionnel.createCoutingSystem(9, ScrutinCST.prive);
    
    system.initElection;
   /*
    * PARTI 
    */
    val partiA : Parti = new Parti(0, "PartiA");
    partiA.sePresenter(system);
    val partiB : Parti = new Parti(1,"PartiB");
    partiB.sePresenter(system);
    val partiC : Parti = new Parti(2,"PartiC");
    partiC.sePresenter(system);
    val partiD : Parti = new Parti(3,"PartiD");
    partiD.sePresenter(system);
    
   
    
    /*
     * CANDIDAT
     */
  val candidat1 = new Candidat(1 , "candidat1" ,"candidat1" ,partiA);
	//candidat1.sePresenter(system);
	val candidat2 = new Candidat(2 , "candidat2" ,"candidat2" ,partiA);
	//candidat2.sePresenter(system);
	val candidat3 = new Candidat(3 , "candidat3" ,"candidat3" ,partiA);
	//candidat3.sePresenter(system);
  val candidat4 = new Candidat(4, "candidat4" ,"candidat4" ,partiA); 
	//candidat4.sePresenter(system);
  
  partiA.addCandidat(candidat1);
  partiA.addCandidat(candidat2);
  partiA.addCandidat(candidat3);
  partiA.addCandidat(candidat4);
  
  val candidat5 = new Candidat(5 , "candidat5" ,"candidat5" ,partiB);
  //candidat5.sePresenter(system);
  val candidat6 = new Candidat(6 , "candidat6" ,"candidat6" ,partiB);
  //candidat6.sePresenter(system);
  val candidat7 = new Candidat(7 , "candidat7" ,"candidat7" ,partiB);
  //candidat7.sePresenter(system);
  
  partiB.addCandidat(candidat5);
  partiB.addCandidat(candidat6);
  partiB.addCandidat(candidat7);
  
  val candidat8 = new Candidat(8 , "candidat8" ,"candidat8" ,partiC);
  //candidat8.sePresenter(system);
  val candidat9 = new Candidat(9 , "candidat9" ,"candidat9" ,partiC);
  //candidat9.sePresenter(system);
  
  partiC.addCandidat(candidat8);
  partiC.addCandidat(candidat9);
  
  val candidat10 = new Candidat(10,"candidat10","candidat10",partiD);
   val candidat11 = new Candidat(11,"candidat11","candidat11",partiD);
  //candidat10.sePresenter(system);
  partiD.addCandidat(candidat10);
  partiD.addCandidat(candidat11);
  
  system.cloturerCandidature;
    
  val elec1 = new  ElecteurProportionnel (1 , "login1","nom1","prenom1","password1");
	val elec2 = new  ElecteurProportionnel (2 , "login2","nom2","prenom2","password2");
	val elec3 = new  ElecteurProportionnel (3 , "login3","nom3","prenom3","password3");
	val elec4 = new  ElecteurProportionnel (4 , "login4","nom4","prenom4","password4");
	val elec5 = new  ElecteurProportionnel (5 , "login5","nom5","prenom5","password5");
	val elec6 = new  ElecteurProportionnel (6 , "login6","nom6","prenom6","password6");
  val elec7 = new  ElecteurProportionnel (7 , "login7","nom7","prenom7","password7");
	val elec8 = new  ElecteurProportionnel (8 , "login8","nom8","prenom8","password8");
	val elec9 = new  ElecteurProportionnel (9 , "login9","nom9","prenom9","password9");
	val elec10 = new  ElecteurProportionnel (10 , "login10","nom10","prenom10","password10");
	
  	elec1.voter(system, partiA);
    elec2.voter(system, partiA);
    elec3.voter(system, partiA);
    elec4.voter(system, partiA);
    elec5.voter(system, partiA);
    elec6.voter(system, partiB);
    elec7.voter(system, partiB);
    elec8.voter(system, partiC);
    elec9.voter(system, partiC);
    elec10.voter(system, partiD);
    
    system.runTour;
 
    
    /*
     * EXPECTED 
     */
    val expectedList : MutableList[(Parti,BigDecimal,BigDecimal,List[Candidat])] = MutableList(
       (partiA,4.0,0.5555555555555556,List(candidat1,candidat2,candidat3,candidat4)),
       (partiB,2.0,0.8888888888888888,List(candidat5,candidat6)),
       (partiC,2.0,0.8888888888888888,List(candidat8,candidat9)),
       (partiD,1.0,1.0,List(candidat10))
     
     );
    

    
    test("OK"){
      assert(system.getGagnants() == expectedList)
    }
    
 // }
}