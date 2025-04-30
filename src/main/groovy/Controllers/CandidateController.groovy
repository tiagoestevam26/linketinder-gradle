package Controllers

import com.sun.net.httpserver.*
import groovy.json.JsonSlurper
import models.*
import services.CandidatesService

class CandidateController implements HttpHandler {
    void handle(HttpExchange exchange) throws IOException {
        println "Requisição recebida: ${exchange.requestMethod}"
        if (exchange.requestMethod == "POST") {
            String requestBody = exchange.requestBody.text
            def json = new JsonSlurper().parseText(requestBody)

            Candidate candidate = new Candidate(
                    json.name,
                    new Date(json.bornDate as Long),
                    json.email,
                    json.cpf,
                    json.country,
                    json.cep,
                    json.description,
                    json.password
            )

            new CandidatesService().addCandidato(candidate)

            String response = "Candidato ${json.name} cadastrado com sucesso."
            exchange.sendResponseHeaders(201, response.length())
            exchange.responseBody.withWriter { it << response }
        }
    }
}
