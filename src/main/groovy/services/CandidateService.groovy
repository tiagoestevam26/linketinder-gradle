package services

import database.CandidateDAO
import models.Candidate

class CandidatesService {

    void addCandidato(Candidate candidato) {
        CandidateDAO.getInstance().salvar(candidato)
    }

}
