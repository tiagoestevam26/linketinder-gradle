package Controllers

import com.sun.net.httpserver.*
import database.CandidateDAO
import database.CompetenceDAO
import groovy.json.JsonSlurper
import models.Candidate
import services.CandidatesService
import models.Competence

class CompetenceController implements HttpHandler {
    void handle(HttpExchange exchange) throws IOException {
        if (exchange.requestMethod == "POST") {
            def json = new JsonSlurper().parseText(exchange.requestBody.text)
            println "Requisição recebida: ${exchange.requestMethod}"
            CandidatesService service = new CandidatesService()
            Candidate candidate = CandidateDAO.getInstance().buscarPorCPF(json.cpf)

            if (candidate) {
                Competence competence = new Competence(json.competence)
                candidate.addCompetence(competence)
                println(candidate.toString())
                CompetenceDAO.getInstance().salvar(competence)

                String response = "Competências adicionadas para o CPF ${json.cpf}"
                exchange.sendResponseHeaders(201, response.length())
                exchange.responseBody.withWriter { it << response }
            } else {
                String response = "Candidato não encontrado"
                exchange.sendResponseHeaders(404, response.length())
                exchange.responseBody.withWriter { it << response }
            }
        }
    }
}
