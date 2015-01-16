package doodle

import java.awt.Color
import java.awt.Font
import scala.swing._
import scala.swing.TabbedPane.Page
import java.awt.Dimension
import scala.swing.event.ButtonClicked
import scala.swing.ListView
import Gvote.FactoryCoutingSystem
import implementationVoteSimple.SystemeDeComptageSemiProportionel
import Factory.FactorySemiProportionnel
import Gvote._
import collection.mutable.ArrayBuffer

class  AddCandidate(_system:SystemGeneralDecomptage,_tab : TabbedPane) extends BoxPanel(Orientation.Vertical){

  var system = _system;
  var tab = _tab;

	//title = "Liste des candidats";  
	preferredSize_=(new Dimension(500,500));
	 
			var list : List[(Int,String,String,String)] = List();
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

			val parti = new TextArea{
        background = Color.green;
				editable_=(false);
				text_=("Parti");
			}     

			val textParti = new TextField{
				preferredSize_=(new Dimension(50,20)) 
			}

			val gridPanel = new GridPanel(2,4){
				contents += nom;
				contents += prenom;
				contents += parti;
				contents += textNom;
				contents += textPrenom;
				contents += textParti;
			}

			val listView = new ListView(list){
        background = Color.green;
        
				enabled_=(false);
        selectionBackground_=(Color.BLACK)
				preferredSize_=(new Dimension(50,600))
			};
			val buttonAdd = new Button("Ajouter");
			listenTo(buttonAdd);

      val buttonContinue = new Button("Continuer");
      listenTo(buttonContinue);

			contents += gridPanel;
			contents += buttonAdd;
			contents += listView;
      contents += buttonContinue;
			var nbrCandidat = 0;
      var nbrParti = 0;
			reactions += {
			case ButtonClicked(component) if component == buttonAdd =>{
				if(textNom.text.equals("") || textPrenom.text.equals("") || textParti.text.equals(""))
					Dialog.showMessage(this, "Veuillez remplir tous les champs textes", "Erreur", Dialog.Message.Error)

					else{
						nbrCandidat+=1;
						list = list:+(nbrCandidat,textNom.text,textPrenom.text,textParti.text);
            new Candidat(nbrCandidat,textNom.text,textPrenom.text,new Parti(nbrParti,textParti.text)).sePresenter(system);
						listView.listData_=(list);
						textNom.text ="";
						textPrenom.text ="";
						textParti.text ="";
            
					}
			}
      
      case ButtonClicked(component) if component == buttonContinue => {
     
        val p = new Page("Ajouter Electeur",new AddElector(system,tab))
        tab.pages += p;
        tab.selection.index_=(tab.selection.index+1);
        println("eeeee")
    }




			}


}