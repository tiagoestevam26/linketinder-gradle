package View

import database.CompetenceDAO
import java.text.ParseException
import models.Business
import models.Candidate
import models.Competence
import services.BusinessService
import services.CandidatesService
import services.JobService

import java.text.SimpleDateFormat

class CadastroView {

    static void telaCriarConta(Scanner scanner, BusinessService businessManager, CandidatesService candidatesManager, JobService jobManager) {
        println "\n♡CRIAR CONTA♡:"
        println "1 - Procuro vagas"
        println "2 - Procuro candidatos"
        println "3 - Voltar"
        print "Escolha uma opção: "

        String choice = scanner.nextLine()

        switch (choice) {
            case "1":
                telaCadastroCandidato(scanner, candidatesManager)
                break
            case "2":
                telaCadastroEmpresa(scanner, businessManager, jobManager)
                break
            case "3":
                println "Voltando..."
                break
            default:
                println "Opção inválida, tente novamente."
        }
    }

    static void telaCadastroCandidato(Scanner scanner, CandidatesService candidateManager) {
        println "\n♡CADASTRO CANDIDATO♡:"
        String name = solicitarInput(scanner, "Nome")
        Date birthDate
        while (true) {
            print "Data de nascimento (dd/MM/yyyy): "
            String dataString = scanner.nextLine()

            try {
                birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dataString)
                println "Data válida: ${birthDate}"
                break
            } catch (ParseException e) {
                println "Formato de data inválido! Use dd/MM/yyyy (ex: 25/12/2000)."
            }
        }
        String email = solicitarInput(scanner, "Email")
        String cpf = solicitarInput(scanner, "CPF")
        String country = solicitarInput(scanner, "País")
        String cep = solicitarInput(scanner, "CEP")
        String description = solicitarInput(scanner, "Descrição")
        String password = solicitarInput(scanner, "Senha")

        Candidate candidato = new Candidate(name, birthDate, email, cpf, country, cep, description, password)
        coletarCompetencias(scanner, candidato)

        candidateManager.addCandidato(candidato)
        println "Cadastro concluído com sucesso!"

        boolean foiCurtido = true
        UserView.telaCandidato(scanner, foiCurtido)
    }

    static void telaCadastroEmpresa(Scanner scanner, BusinessService businessManager, JobService jobManager) {
        println "\n♡CADASTRO EMPRESA♡:"
        String name = solicitarInput(scanner, "Nome da Empresa")
        String email = solicitarInput(scanner, "Email")
        String cnpj = solicitarInput(scanner, "CNPJ")
        String country = solicitarInput(scanner, "País")
        String cep = solicitarInput(scanner, "CEP")
        String description = solicitarInput(scanner, "Descrição")
        String password = solicitarInput(scanner, "Senha")

        Business empresa = new Business(name, email, cnpj, country, cep, description, password)
        businessManager.addBusiness(empresa)
        println "Empresa cadastrada com sucesso!"

        while (true) {
            print "\nAdicionar uma vaga? (Digite 'sim' ou pressione Enter para finalizar): "
            String resposta = scanner.nextLine()
            if (resposta.isEmpty()) break
            AddJobView.telaAdicionarVaga(empresa, jobManager, scanner)
        }

        boolean foiCurtido = true
        UserView.telaEmpresa(scanner, foiCurtido)
    }

    private static void coletarCompetencias(Scanner scanner, Candidate candidato) {
        while (true) {
            print "Adicionar competência (ou pressione Enter para finalizar): "
            String nomeCompetencia = scanner.nextLine()
            if (nomeCompetencia.isEmpty()) break

            Competence competence = new Competence(nomeCompetencia)
            candidato.addCompetence(competence)
            CompetenceDAO.salvar(competence)
        }
    }

    private static String solicitarInput(Scanner scanner, String campo) {
        print "${campo}: "
        return scanner.nextLine()
    }

}
