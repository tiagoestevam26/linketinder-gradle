package View

import database.CompetenceDAO
import models.Business
import models.Candidate
import models.Competence
import services.BusinessService
import services.CandidatesService
import services.JobService

import java.text.SimpleDateFormat

class CadastroView {

    static void telaCriarConta(Scanner scanner, BusinessService businessManager, CandidatesService candidatesManager, JobService jobManager){
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
        print "Nome: "
        String name = scanner.nextLine()
        print "Data de nascimento (dd/MM/yyyy): "
        String dataString = scanner.nextLine()
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(dataString);
        print "Email: "
        String email = scanner.nextLine()
        print "CPF: "
        String cpf = scanner.nextLine()
        print "País: "
        String country = scanner.nextLine()
        print "CEP: "
        String cep = scanner.nextLine()
        print "Descrição: "
        String description = scanner.nextLine()
        print "Senha: "
        String password = scanner.nextLine()

        Candidate candidato = new Candidate(name, date, email, cpf, country, cep, description, password);

        while (true) {
            print "Adicionar competência (ou pressione Enter para finalizar): "
            String comp = scanner.nextLine()
            if (comp.isEmpty()) break
            Competence competence = new Competence(comp)
            candidato.addCompetence(competence)
            CompetenceDAO.salvar(competence)
        }

        candidateManager.addCandidato(candidato)
        println "Cadastro concluído com sucesso!"
        boolean foiCurtido = true;
        UserView.telaCandidato(scanner, foiCurtido)

    }

    static void telaCadastroEmpresa(Scanner scanner, BusinessService businessManager, JobService jobManager) {
        println "\n♡CADASTRO EMPRESA♡:"
        print "Nome da Empresa: "
        String name = scanner.nextLine()
        print "Email: "
        String email = scanner.nextLine()
        print "CNPJ: "
        String cnpj = scanner.nextLine()
        print "País: "
        String country = scanner.nextLine()
        print "CEP: "
        String cep = scanner.nextLine()
        print "Descrição: "
        String description = scanner.nextLine()
        print "Senha: "
        String password = scanner.nextLine()

        Business empresa = new Business(name, email, cnpj, country, cep, description, password)
        businessManager.addBusiness(empresa)
        println "Empresa cadastrada com sucesso!"

        while (true) {
            print "\nAdicionar uma vaga? (Digite sim ou pressione Enter para finalizar): "
            String comp = scanner.nextLine()
            if (comp.isEmpty()) break
            AddJobView.telaAdicionarVaga(empresa, jobManager, scanner)
        }

        boolean foiCurtido = true;
        UserView.telaEmpresa(scanner, foiCurtido)
    }

}
