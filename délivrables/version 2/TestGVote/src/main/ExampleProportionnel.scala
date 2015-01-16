package main

import implementationProportionnel.SystemDeComptageProportionel
import Factory.FactorySemiProportionnel
import Factory.FactoryProportionnel
import implementationVoteSimple._
import Gvote._
import implementationProportionnel.ElecteurProportionnel
import scala.collection.mutable.MutableList
import GUI.GUI_IHMSwing

object ExampleProportionnel {
  
  def main(args : Array[String]) : Unit = {
    
    var system : SystemDeComptageProportionel = FactoryProportionnel.createCountingSystem(9,ScrutinCST.prive);
    
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
  val candidat2 = new Candidat(2 , "candidat2" ,"candidat2" ,partiA);
  val candidat3 = new Candidat(3 , "candidat3" ,"candidat3" ,partiA);
  val candidat4 = new Candidat(4, "candidat4" ,"candidat4" ,partiA); 
  
  val candidat5 = new Candidat(5 , "candidat5" ,"candidat5" ,partiB);
  val candidat6 = new Candidat(6 , "candidat6" ,"candidat6" ,partiB);
  val candidat7 = new Candidat(7 , "candidat7" ,"candidat7" ,partiB);
    
  val candidat8 = new Candidat(8 , "candidat8" ,"candidat8" ,partiC);
  val candidat9 = new Candidat(9 , "candidat9" ,"candidat9" ,partiC);
    
  val candidat10 = new Candidat(10,"candidat10","candidat10",partiD);
  val candidat11 = new Candidat(11,"candidat11","candidat11",partiD);
  
  system.cloturerCandidature;
    
  val elec1 = new  ElecteurProportionnel (1 , "login1","nom1","prenom1","password1");
  val elec2 = new  ElecteurProportionnel (2 , "login2","nom2","prenom2","password2");
  val elec3 = new  ElecteurProportionnel (3 , "login3","nom3","prenom3","password3");
  val elec4 = new  ElecteurProportionnel (4 , "login4","nom4","prenom4","password4");
  val elec5 = new  ElecteurProportionnel (5 , "login5","nom5","prenom5","password5");
  val elec6 = new  ElecteurProportionnel (6 , "login6","nom6","prenom6","password6");
  val elec7 = new  ElecteurProportionnel (3 , "login7","nom7","prenom7","password7");
  val elec8 = new  ElecteurProportionnel (4 , "login8","nom8","prenom8","password8");
  val elec9 = new  ElecteurProportionnel (5 , "login9","nom9","prenom9","password9");
  val elec10 = new  ElecteurProportionnel (6 , "login10","nom10","prenom10","password10");
  
  	GUI_IHMSwing.runVote(List((elec1,system)))
    GUI_IHMSwing.runVote(List((elec2,system)))
    GUI_IHMSwing.runVote(List((elec3,system)))
    GUI_IHMSwing.runVote(List((elec4,system)))
    GUI_IHMSwing.runVote(List((elec5,system)))
    GUI_IHMSwing.runVote(List((elec6,system)))
    GUI_IHMSwing.runVote(List((elec7,system)))
    GUI_IHMSwing.runVote(List((elec8,system)))
    GUI_IHMSwing.runVote(List((elec9,system)))
    GUI_IHMSwing.runVote(List((elec10,system)))
    
    system.runTour;
    
    for(gagnants <- system.getGagnants){
      for(candidat <- gagnants._4)
        println(candidat.nom +" du "+gagnants._1.nom+" a obtenu un siege BRAVO !!!; "+gagnants._3)    
    }
    
  }
}