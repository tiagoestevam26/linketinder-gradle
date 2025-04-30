package org.example

import Controllers.CandidateController
import Controllers.CompetenceController
import Controllers.JobController
import View.MainMenu
import com.sun.net.httpserver.HttpServer

static void main(String[] args) {

    def server = HttpServer.create(new InetSocketAddress(8080), 0)

    server.createContext("/candidato", new CandidateController())
    server.createContext("/vaga", new JobController())
    server.createContext("/competencia", new CompetenceController())

    server.executor = null
    println "Servidor rodando em http://localhost:8080"
    server.start()
    MainMenu menu = new  MainMenu()
    menu.exibirMenu()

}

