package main

import Factory.FactorySemiProportionnel
import implementationVoteSimple._
import Gvote.Parti
import Gvote.Candidat
import Gvote.ScrutinCST
import GUI.GUI_IHMSwing

object ExampleSemiProportionnel {

  def main(args : Array[String]) : Unit = {
   
   /* 
    * On doit obligatoirement définir le nombre de siège
    */

  var system : SystemeDeComptageSemiProportionel = FactorySemiProportionnel.createCountingSystem(2, ScrutinCST.prive);

  system.initElection;

  val parti : Parti = new Parti(0, "PARTI")
  
  val candidat1 = new Candidat(1 , "candidat1" ,"candidat1" ,parti)
  candidat1.sePresenter(system);
  val candidat2 = new Candidat(2 , "candidat2" ,"candidat2" ,parti)
  candidat2.sePresenter(system);
  val candidat3 = new Candidat(3 , "candidat3" ,"candidat3" ,parti)
  candidat3.sePresenter(system);
  
  system.cloturerCandidature;
  
  val elec1 = new  Electeur (1 , "login1","nom1","prenom1","password1");
  val elec2 = new  Electeur (2 , "login2","nom2","prenom2","password2");
  val elec3 = new  Electeur (3 , "login3","nom3","prenom3","password3");
  val elec4 = new  Electeur (4 , "login4","nom4","prenom4","password4");
  val elec5 = new  Electeur (5 , "login5","nom5","prenom5","password5");
  val elec6 = new  Electeur (6 , "login6","nom6","prenom6","password6");
 
  GUI_IHMSwing.runVote(List((elec1,system)))
  GUI_IHMSwing.runVote(List((elec2,system)))
  GUI_IHMSwing.runVote(List((elec3,system)))
  GUI_IHMSwing.runVote(List((elec4,system)))
  GUI_IHMSwing.runVote(List((elec5,system)))
  GUI_IHMSwing.runVote(List((elec6,system)))
  
  system.runTour;
  
  for (gagnants <- system.getGagnants)
    println(gagnants.nom +" a obtenu un siege ")

    
    
  }
}