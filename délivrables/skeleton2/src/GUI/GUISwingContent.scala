package GUI

import javax.swing.JPanel
import java.awt.GridLayout
import javax.swing.JCheckBox
import java.awt.Component
import javax.swing.ButtonGroup
import javax.swing.JRadioButton
import javax.swing.JComboBox
import javax.swing.JLabel

import Gvote.SystemGeneralDecomptage
import Gvote.Eligible
import Gvote.AbstractElecteur

abstract class GUISwingContent(_listeChoix : List[(String,Eligible)]) extends JPanel(){
	  
	    protected var listeChoix : List[(String,Eligible)] = _listeChoix
		protected var mesChoix : List[(Int,Eligible)] = List()
		
		def initialize
	    def applyResult() : List[(Int,Eligible)]
		
	}
	
	class CheckboxContent(_listeChoix : List[(String,Eligible)]) extends GUISwingContent(_listeChoix){
	    
	    def initialize(){
		  
	    	setLayout(new GridLayout(listeChoix.length+1,1))
	        var jcheckbox : JCheckBox = null
	        
	        for(choix <- listeChoix){
	            jcheckbox = new JCheckBox(choix._1)
	            jcheckbox.setSelected(false);
	            add(jcheckbox)
	        }
	    }
	    
	    def applyResult() : List[(Int,Eligible)] = {
	      
	        var i : Int = 0
	        mesChoix = List()
	        for(i <- 0 to listeChoix.length-1){
	        	var component : Component = getComponent(i);
	        		component match {
	        			case checkbox : JCheckBox =>
	        				if(checkbox.isSelected())
	        					mesChoix = mesChoix :+ (0,listeChoix.apply(i)._2)
	        		}	        		
	        }
	        return mesChoix
	    }
	    
	}
	
	class RadioContent(_listeChoix : List[(String,Eligible)]) extends GUISwingContent(_listeChoix){
	    
	    def initialize(){
	    var group : ButtonGroup = new ButtonGroup();
	    	
	        setLayout(new GridLayout(listeChoix.length+1,1))
	    	var jradio : JRadioButton = null
	        
	        for(choix <- listeChoix){
	            jradio = new JRadioButton(choix._1)
	            jradio.setSelected(false);
	            group.add(jradio)
	            add(jradio)
	            
	        }	    	
	    }
	    
	    def applyResult()  : List[(Int,Eligible)] = {
	      
	        var i : Int = 0
	        for(i <- 0 to listeChoix.length-1){
	        	var component : Component = getComponent(i);
	        	component match {
	        		case radio : JRadioButton =>
	        			if(radio.isSelected())
	        				mesChoix = List((0,listeChoix.apply(i)._2))
	        	}
	        }
	        return mesChoix
	    }
	    
	}
	
	class ListeDeroulanteContent(_listeChoix : List[(String,Eligible)]) extends GUISwingContent(_listeChoix){

	    def initialize(){
	      
	    	setLayout(new GridLayout(listeChoix.length+1,2))
	    	
	    	var listNumeroPos : List[String] = List()
	    	var i : Int = 0
	    	for(i <- 1 to listeChoix.length){
	    	    listNumeroPos = listNumeroPos:+ i.toString
	    	}
	        
	        for(choix <- listeChoix){
	            add(new JComboBox(listNumeroPos.toArray))
	            add(new JLabel(choix._1))
	        }
	    }
	    
	    def applyResult() : List[(Int,Eligible)] = {
	      
	        var i : Int = 0
	        var j : Int = 0
	        mesChoix = List()
	        for(i <- 0 to listeChoix.length-1){
	            if(i%2==0){
	            	var component : Component = getComponent(i);
	        		component match {
	        			case comboBox : JComboBox[String] =>
	        					mesChoix = mesChoix :+ (component.toString().toInt, listeChoix.apply(j)._2)
	        					j +=1
	        		}
	            }		
	        }
	        return mesChoix
	    }
	    
	}