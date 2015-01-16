package doodle

import scala.swing._
import scala.swing.TabbedPane.Page
import java.awt.Dimension
import java.awt.Color
import java.awt.Font
import scala.swing.event.ButtonClicked
import scala.swing.ListView
import Gvote.FactoryCoutingSystem
import implementationVoteSimple.SystemeDeComptageSemiProportionel
import Factory.FactorySemiProportionnel
import Gvote._
import collection.mutable.ArrayBuffer
import implementationVoteSimple._
import implementationCondorcet.ElecteurCondorcet
import implementationProportionnel._
import implementationPlurinominale._


class  AddElector(_system:SystemGeneralDecomptage,_tab : TabbedPane) extends BoxPanel(Orientation.Vertical){

	var system = _system;
	var tab = _tab;


	//title = "Liste des candidats";  
	preferredSize_=(new Dimension(500,500));

	var list : List[(Int,String,String,String,String)] = List();

  val fontEvent = new Font("event",Font.BOLD,20);

	val id = new TextArea{
    background = Color.green;
		editable_=(false);
		text_=("Id");
	}     

	val textId = new TextField{
		preferredSize_=(new Dimension(50,20)) 
	}

	val login = new TextArea{
    background = Color.green;
		editable_=(false);
		text_=("Login");
	}     

	val textLogin = new TextField{
		preferredSize_=(new Dimension(50,20)) 
	}

	val password = new TextArea{
    background = Color.green;
		editable_=(false);
		text_=("Password");
	}     

	val textPassword = new TextField{
		preferredSize_=(new Dimension(50,20)) 
	}


	val nom = new TextArea{
    background = Color.green;
		editable_=(false);
		text_=("Nom");
	}

	val textNom = new TextField{
		preferredSize_=(new Dimension(50,20)) 
	}

	val prenom = new TextArea{
    background = Color.green;
		editable_=(false);
		text_=("Prenom");
	}

	val textPrenom = new TextField{
		preferredSize_=(new Dimension(50,20)) 
	}
	def resetText() : Unit = {

			textNom.text ="";
			textPrenom.text ="";
			textLogin.text ="";
			textPassword.text = "";
	}

	val gridPanel = new GridPanel(2,5){
		contents += id;
		contents += login;
		contents += nom;
		contents += prenom;
		contents += password;
		contents += textId;
		contents += textLogin;
		contents += textNom;
		contents += textPrenom;
		contents += textPassword;
	}

	val listView = new ListView(list){
		enabled_=(false);
		preferredSize_=(new Dimension(50,600))
	};
	val buttonAdd = new Button("Ajouter");
	listenTo(buttonAdd);

	val buttonContinueElec = new Button("Continuer");
	listenTo(buttonContinueElec);

	contents += gridPanel;
	contents += buttonAdd;
	contents += listView;
	contents += buttonContinueElec;
	var nbrElecteur = 0;
	var nbrParti = 0;
	var listElecteur : List[AbstractElecteur] = List();
  val panelToBeContinued = new BoxPanel(Orientation.Horizontal){
    background_=(Color.green);
    contents += new TextField("つづく"){
      background_=(Color.green);
      font_=(fontEvent);
    };
  }
	reactions += {
	case ButtonClicked(component) if component == buttonAdd =>{
		if(textNom.text.equals("") && textPrenom.text.equals("") && textPassword.text.equals("") && textLogin.text.endsWith(""))
			Dialog.showMessage(this, "Veuillez remplir les champs textes", "Erreur", Dialog.Message.Error)
			else{
				nbrElecteur+=1;
				list = list:+(nbrElecteur,textLogin.text,textNom.text,textPrenom.text,textPassword.text);
				listView.listData_=(list);
				if(system.nom.equals("Election condorcet")){
					listElecteur =listElecteur.+:(new ElecteurCondorcet(nbrElecteur,textLogin.text,textNom.text,textPrenom.text,textPassword.text)); 
					resetText();
				}
				else if(system.nom.equals("Election proportionnel")){
					listElecteur =listElecteur.+:(new ElecteurProportionnel(nbrElecteur,textLogin.text,textNom.text,textPrenom.text,textPassword.text)); 
					resetText();
				}
				else if(system.nom.equals("Election Plurinominale")){
					listElecteur =listElecteur.+:(new implementationPlurinominale.Electeur(nbrElecteur,textLogin.text,textNom.text,textPrenom.text,textPassword.text)); 
					resetText();
				}
				else{
					listElecteur =listElecteur.+:(new implementationVoteSimple.Electeur(nbrElecteur,textLogin.text,textNom.text,textPrenom.text,textPassword.text)); 
					resetText();
				}


			}
	}

	case ButtonClicked(component) if component == buttonContinueElec => {

		val p = new Page("To Be Continue",panelToBeContinued)
		tab.pages += p;
		tab.selection.index_=(tab.selection.index+1);
	}




	}


}