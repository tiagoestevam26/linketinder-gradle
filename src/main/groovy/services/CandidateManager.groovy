package services

import database.CandidateDAO
import models.Candidate

class CandidatesManager {

    void listCandidates(){
        CandidateDAO.listarTodos().each { println it }
    }

    void addCandidato(Candidate candidato) {
        CandidateDAO.salvar(candidato)
    }

}
