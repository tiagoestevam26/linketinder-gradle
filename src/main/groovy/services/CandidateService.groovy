package services

import database.CandidateDAO
import models.Candidate

class CandidatesService {

    void listCandidates(){
        CandidateDAO.listarTodos().each { println it }
    }

    void addCandidato(Candidate candidato) {
        CandidateDAO.salvar(candidato)
    }

}
