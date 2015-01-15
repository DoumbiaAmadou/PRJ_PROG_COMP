package GUI

import GUIAbstractComponent._
import javax.swing._
import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.Component
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import java.awt.Dimension

class GUIGeneratorSwing extends GUIAbstractGeneratorToolkit {
	type ImplFrame = JDialog
	
	var mainFrame: JDialog = null
	
	def init(frame : Frame){
	    mainFrame = new JDialog()
	    mainFrame.setModal(true)
	    mainFrame.setTitle(frame.title)
	    mainFrame.setMinimumSize(new Dimension(300 , 200));
	    mainFrame.setLayout(new BorderLayout)
	    
	    val mainPanel = new JPanel()
	    mainPanel.setLayout(new GridLayout(frame.contentList.length/2+1,2))
	    
	    
	    var panel : GUISwingContent = null
	  
	    for(content <- frame.contentList){
	        
	        content.electeurAndSysteme._2.getGUIType match{
	            case GUIComponentCST.checkbox =>{
	                panel = new CheckboxContent(content.listChoix)
	            }
	          
	            case GUIComponentCST.radio =>{
	                panel = new RadioContent(content.listChoix)
	            }
	          
	            case GUIComponentCST.listeDeroulante =>{
	                panel = new RadioContent(content.listChoix)
	            }
	          
	        }
	        
	        panel.initialize
	        mainPanel.add(panel)
	    }
	    
	    mainFrame.add(mainPanel, BorderLayout.CENTER)
	    
	    val mainButton : JButton = new JButton("Voter")
	    mainButton.addActionListener(new ActionListener(){
	        	def actionPerformed(e : ActionEvent){
	        		for(i <- 0 to frame.contentList.length-1){
	        			var content : Component = mainPanel.getComponent(i);
	        			content match {
	        				case content : GUISwingContent =>
	        				    val electeur = frame.contentList.apply(i).electeurAndSysteme._1
	        				    val systeme = frame.contentList.apply(i).electeurAndSysteme._2
	        				    systeme.ajouterVoteByGUIAbsElecteur(electeur, content.applyResult())
	        			}	        		
	        		}
	        		mainFrame.dispose()
	        	}
	        }
	    )
	    val panelButton : JPanel = new JPanel()
	    panelButton.add(mainButton)
	    mainFrame.add(panelButton, BorderLayout.SOUTH)
	    mainFrame.pack()
	    mainFrame.setVisible(true)
	}
	
}