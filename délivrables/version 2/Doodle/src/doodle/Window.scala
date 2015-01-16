package doodle

import scala.swing._
import swing.event._
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import scala.swing.TabbedPane.Page
import scala.swing.TabbedPane.Page
import Factory._

object Window extends SimpleSwingApplication{
	val fontEvent = new Font("event",Font.BOLD,20);

	def initialize(begin : Int, end : Int) : Seq[Int] = {
			var a = 0;
			var list : List[Int] = List();
			for(a <- begin to end){
				list = list.+:(a);
			}
			return list;
	}
	def top = new Frame{
		//title = "OK";
		title= "Doodle Gvote";
    minimumSize_=(new Dimension(300,300));

		//preferredSize= toolkit.getScreenSize;
		// size = new Dimension(500,500);
		background = Color.green;
		//
		//java.awt.Toolkit.getDefaultToolkit.getScreenSize;
		//preferredSize = screenSize;  

		val textEvent = new TextArea {
			background = Color.green;
			editable_=(false);   
			font_=(fontEvent);
			text_=("Titre");
		} 
		val event = new TextField 
		val textPlace = new TextArea {
      background = Color.green;
			editable_=(false);      
			text_=("Lieu");
			font_=(fontEvent);
		} 
		val place = new TextField;

		val textDescription = new TextArea {
      background = Color.green;
			editable_=(false);      
			text_=("Description");
			font_=(fontEvent);  
		} 
		val description = new TextField;

		var listTour : List[Int] = List();

		val chooseDate = new TextArea{
      background = Color.green;
			editable_=(false);
			text_=("Choissisez une date :");
		}

		val date = new TextField{
			preferredSize_=(new Dimension(100,20));
		}

		val addDate = new Button{
			text_=("Ajouter une date");
		} 


		val panelDate = new BoxPanel(Orientation.Horizontal){
			contents += chooseDate;
			contents += date;
			contents += addDate;

		}
		val continue = new Button{
			text_=("Continuer")
		}
		val param = new TextArea{
			editable_=(false);
			text_=("Paramètres : ");
		}
		val buttonCondorcet = new RadioButton("Condorcet");
		val buttonPlurinominal = new RadioButton("Semi Proportionnel");
		val choiceSystem = new ButtonGroup{
			buttons += buttonCondorcet;
			buttons += buttonPlurinominal;
		}

		val textPrivate = new TextArea{
			text_=("Sondage privée");

		}
		val cbPrivateYes = new CheckBox("Oui");
		val cbPrivateNo = new CheckBox("Non");
		val yesNo = new ButtonGroup{
			buttons += cbPrivateNo;
			buttons += cbPrivateYes;
		}


		listenTo(addDate);
		listenTo(continue);
		val generalPanel = new BoxPanel(Orientation.Vertical){
			//presize_=new Dimension(500,500);
			//background = Color.;
			contents += textEvent;
			contents += event;
			contents += textPlace;
			contents += place;
			contents += textDescription;
			contents += description;
			//contents += panelDate;
			//contents += param;
			//contents += textPrivate;
			// contents += cbPrivateYes;
			// contents += cbPrivateNo;
			//contents += buttonCondorcet;
			// contents += buttonPlurinominal;
			contents += continue;

		}
		var tab = new TabbedPane
				tab.pages += new Page("Genéral",generalPanel);

		val textJour = new TextField("Jour"){  
      background = Color.green;
      editable_=(false);
		};
		val textMois = new TextField("Mois"){
      background = Color.green;
    editable_=(false);  
    }
		val textAnnee = new TextField("Annee"){
      background = Color.green;
     editable_=(false); 
    }
		val comboJour = new ComboBox(initialize(1, 31).reverse);
		val comboMois = new ComboBox(initialize(1,12).reverse);
		val comboAnnee = new ComboBox(initialize(2015,2057).reverse);
		val continueDate = new Button("Continue");

		listenTo(continueDate);

		val gridDate = new GridPanel(2,3){
			contents += textJour;
			contents += textMois;
			contents += textAnnee;
			contents +=  comboJour; 
			contents += comboMois;
			contents += comboAnnee; 

		}

		val datePanel = new BoxPanel(Orientation.Vertical){
			contents += gridDate;
			contents += continueDate;
		}



		val YesNoBox = new CheckBox("Sondage Oui/ Non / Si nécessaire");
		val boxSemiProp = new CheckBox("Semi Proportionnel");
		val boxMixte = new CheckBox("Mixte");
		val boxPropotionnel = new CheckBox("Proportionnel");
		val boxCondorcet = new CheckBox("Condorcet");
		val boxPlurinominal = new CheckBox("Plurinominal"); 
		val continueParam = new Button("Continuer");

		listenTo(YesNoBox);
		listenTo(boxSemiProp);
		listenTo(boxMixte);
		listenTo(boxPropotionnel);
		listenTo(boxCondorcet);
		listenTo(boxPlurinominal);
		listenTo(continueParam);

		val addCand = new Button("Ajouter un candidat");
		val addElec = new Button("Ajouter un electeur");
		listenTo(addCand);
		listenTo(addElec);

		val panelChoidCandElec = new BoxPanel(Orientation.Horizontal){
			contents += addCand;
			contents += addElec;
		}
		val groupSystem = new ButtonGroup(YesNoBox,boxSemiProp,boxCondorcet,boxMixte,boxPropotionnel,boxPlurinominal);
		// val hidePoll = new CheckBox("Sondage Caché");
		// val oneChoice = new CheckBox("Le participant ne peut k")
		val optionSeat = new TextArea{
      preferredSize_=(new Dimension(2,10));
      background = Color.green;
			editable_=(false);
			text_=("Nombre de sièges : ");

		}

		val textOptionSeat = new TextField;

		val paramPanel = new BoxPanel(Orientation.Vertical){
			contents += YesNoBox;
			contents += boxSemiProp;
			contents += boxMixte;
			contents += boxPropotionnel;
			contents += boxCondorcet;
			contents += boxPlurinominal;
			contents += optionSeat;
			contents += textOptionSeat;
			contents += continueParam;
		}

		contents = new BoxPanel(Orientation.Vertical){
			contents += tab;
		}



		reactions += {
		case ButtonClicked(component) if component == continue =>{
			val p = new Page("Date",datePanel);
			tab.pages += p;   
			tab.selection.index_=(tab.selection.index+1);
		}
		case ButtonClicked(component) if component == continueDate =>{
			val p = new Page("Paramètres",paramPanel);
			tab.pages += p;
			tab.selection.index_=(tab.selection.index+1);
			println("OK"+tab.toString());
		}

		case ButtonClicked(component) if component == continueParam => {
			if(textOptionSeat.text.isEmpty() && (boxSemiProp.selected == true || boxMixte.selected == true || boxPropotionnel.selected == true)){
				Dialog.showMessage(this.paramPanel, "Veuillez saisir le champ nombres de sièges", "Erreur", Dialog.Message.Error);
			}else{
				if(boxSemiProp.selected){					
					val nbr = textOptionSeat.text.toInt; 
					val p = new Page("Ajouter Candidat",new AddCandidate(FactorySemiProportionnel.createCountingSystem(nbr, "public"),tab));
					tab.pages += p;
					tab.selection.index_=(tab.selection.index+1);
				}
				else if(boxCondorcet.selected){         
					val p = new Page("Ajouter Candidat",new AddCandidate(FactoryCondorcet.createCountingSystem(1, "public"),tab));
					tab.pages += p;
					tab.selection.index_=(tab.selection.index+1);
				}
				else if(boxMixte.selected){         
					val nbr = textOptionSeat.text.toInt; 
					val p = new Page("Ajouter Candidat",new AddCandidate(FactoryMixte.createCountingSystem(nbr, "public"),tab));
					tab.pages += p;
					tab.selection.index_=(tab.selection.index+1);
				}
				else if(boxPropotionnel.selected){          
					val nbr = textOptionSeat.text.toInt; 
					val p = new Page("Ajouter Candidat",new AddCandidate(FactoryProportionnel.createCountingSystem(nbr, "public"),tab));
					tab.pages += p;
					tab.selection.index_=(tab.selection.index+1);
				}
				else {         
					val p = new Page("Ajouter Candidat",new AddCandidate(FactoryPlurinominal.createCountingSystem(10,listTour),tab));
					tab.pages += p;
					tab.selection.index_=(tab.selection.index+1);
				}
			}
		}
		}







		//pack();
	}

}