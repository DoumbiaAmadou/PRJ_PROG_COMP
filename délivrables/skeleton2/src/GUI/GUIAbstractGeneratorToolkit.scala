package GUI

import GUIAbstractComponent._

abstract class GUIAbstractGeneratorToolkit {
	type ImplFrame
  	
	var mainFrame : ImplFrame
	
	protected def init(frame : Frame)
	final def generateFrame(frame : Frame) : ImplFrame = {
	    init(frame)
	    return mainFrame
	}
		
}