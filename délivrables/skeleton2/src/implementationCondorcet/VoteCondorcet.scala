package implementationCondorcet

import Gvote.AbstractVote
import Gvote.Candidat

class VoteCondorcet(_electeur : ElecteurCondorcet, _system : SystemeDecomptageCondorcet, _candidats : List[(Candidat,Int)]) extends AbstractVote(_electeur, _system){
	val candidats = _candidats
}
