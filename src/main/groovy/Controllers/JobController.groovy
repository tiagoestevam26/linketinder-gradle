package Controllers

import com.sun.net.httpserver.*
import database.BusinessDAO
import groovy.json.JsonSlurper
import models.*
import services.JobService

class JobController implements HttpHandler {
    void handle(HttpExchange exchange) throws IOException {
        if (exchange.requestMethod == "POST") {
            println "Requisição recebida: ${exchange.requestMethod}"
            def json = new JsonSlurper().parseText(exchange.requestBody.text)

            Business empresa = BusinessDAO.getInstance().buscarPorCNPJ(json.cnpj)


            Job job = new Job(json.title, json.description, json.location, empresa)

            json.requiredCompetences.each {
                job.addRequiredCompetence(new Competence(it))
            }
            empresa.addJob(job)
            new JobService().addJob(job, BusinessDAO.getInstance().findIdByCNPJ(json.cnpj))
            println(empresa.toString())
            String response = "Vaga '${json.title}' cadastrada com sucesso."
            exchange.sendResponseHeaders(201, response.length())
            exchange.responseBody.withWriter { it << response }
        }
    }
}
