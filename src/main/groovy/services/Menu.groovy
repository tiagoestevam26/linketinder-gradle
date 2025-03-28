package services

import database.BusinessDAO
import database.CandidateDAO
import database.CompetenceDAO
import database.JobDAO
import models.Candidate
import models.Business
import models.Competence
import models.Job

import java.text.SimpleDateFormat

class Menu {
    CandidatesManager candidateManager = new CandidatesManager()
    BusinessManager businessManager = new BusinessManager()
    JobManager jobManager = new JobManager()
    Scanner scanner = new Scanner(System.in)
    Boolean continuar = true

    void exibirMenu() {
        while (true) {
            println "\n♡LINKETINDER♡:"
            println "1 - Fazer login"
            println "2 - Criar conta"
            println "3 - Deletar algo"
            println "4 - Editar algo"
            println "5 - Sair"
            print "Escolha uma opção: "

            String choice = scanner.nextLine()

            switch (choice) {
                case "1":
                    telaLogin()
                    break
                case "2":
                    telaCriarConta()
                    break
                case "3":
                    telaDeletar()
                    break
                case "4":
                    telaEditar()
                    break
                case "5":
                    println "Saindo..."
                    return
                default:
                    println "Opção inválida, tente novamente."
            }
        }
    }

    void telaLogin(){
        println "\n♡FAZER LOGIN:"
        println "Email: "
        String email = scanner.nextLine()
        println "Senha: "
        String senha = scanner.nextLine()
    }

    void telaCriarConta(){
        println "\n♡CRIAR CONTA♡:"
        println "1 - Procuro vagas"
        println "2 - Procuro candidatos"
        println "3 - Voltar"
        print "Escolha uma opção: "

        String choice = scanner.nextLine()

        switch (choice) {
            case "1":
                telaCadastroCandidato()
                break
            case "2":
                telaCadastroEmpresa()
                break
            case "3":
                println "Voltando..."
                break
            default:
                println "Opção inválida, tente novamente."
        }
    }

    void telaCadastroCandidato() {
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
        telaCandidato()

    }

    void telaCadastroEmpresa() {
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
            telaAdicionarVaga(empresa)
        }



        telaEmpresa()
    }

    void telaAdicionarVaga(Business empresa){
        println "\n♡ADICIONAR VAGA♡:"
        print "Título da vaga: "
        String title = scanner.nextLine()
        print "Descrição da vaga: "
        String description = scanner.nextLine()
        print "Localização: "
        String loc = scanner.nextLine()

        Job vaga = new Job(title, description, loc, empresa)
        Integer id = BusinessDAO.findIdByCNPJ(empresa.getCNPJ())
        JobDAO.salvar(vaga, id)


        while (true) {
            print "Adicionar competência desejada (ou pressione Enter para finalizar): "
            String comp = scanner.nextLine()
            if (comp.isEmpty()) break
            Competence competence = new Competence(comp)
            vaga.addRequiredCompetence(competence)
            CompetenceDAO.salvar(competence)
        }

        jobManager.addJob(vaga)
        empresa.addJob(vaga)
        println "Vaga adicionada com sucesso!"

    }

    void telaCandidato(){
        println "\n♡LISTA DE VAGAS♡:"
        JobDAO.listarTodos().each {job->
            if(continuar){
                print(job.toString())
                println "\n1 - Curtir - ♡"
                println "2 - Passar - X"
                print "Escolha uma opção: "

                String choice = scanner.nextLine()

                switch (choice) {
                    case "1":
                        println "Curtiu... ♡"
                        continuar = false
                        break
                    case "2":
                        println "Passou... X"
                        break
                    default:
                        println "Opção inválida, tente novamente."
                }
            }
        }
        continuar = true

    }

    void telaEmpresa(){
        println "\n♡LISTA DE CANDIDATOS♡:"
        CandidateDAO.listarTodos().each { candidate->
            if(continuar){
                print(candidate.toString())
                println "\n1 - Curtir - ♡"
                println "2 - Passar - X"
                print "Escolha uma opção: "

                String choice = scanner.nextLine()

                switch (choice) {
                    case "1":
                        println "Curtiu... ♡"
                        continuar = false
                        return
                    case "2":
                        println "Passou... X"
                        break
                    default:
                        println "Opção inválida, tente novamente."
                }
            }
        }
        continuar = true

    }


    void telaDeletar(){
        println "\n♡DELETAR O QUE♡:"
        println "1 - Candidato"
        println "2 - Empresa"
        println "3 - Vaga"
        println "4 - Competencia"
        print "Escolha uma opção: "

        String choice = scanner.nextLine()

        switch (choice) {
            case "1":
                print "Digite o CPF: "
                String cpf = scanner.nextLine()
                CandidateDAO.deletar(cpf)
                break
            case "2":
                print "Digite o CNPJ: "
                String cnpj = scanner.nextLine()
                BusinessDAO.deletar(cnpj)
                break
            case "3":
                print "Digite o nome da vaga: "
                String nome = scanner.nextLine()
                JobDAO.deletar(JobDAO.findIdByName(nome))
                break
            case "4":
                print "Digite o nome da competência: "
                String comp = scanner.nextLine()
                CompetenceDAO.deletar(CompetenceDAO.findIdByName(comp))
                break
            default:
                println "Opção inválida, tente novamente."
        }
    }

    void telaEditar(){
        println "\n♡EDITAR O QUE♡:"
        println "1 - Candidato"
        println "2 - Empresa"
        println "3 - Vaga"
        println "4 - Competencia"
        print "Escolha uma opção: "

        String choice = scanner.nextLine()

        switch (choice) {
            case "1":
                print "Digite o CPF do candidato para editar: "
                String cpf = scanner.nextLine()
                Date data = new Date()
                Candidate candNovo = new Candidate('editado',data,'editado','editado','editado','editado','editado','editado')
                CandidateDAO.editar(cpf,candNovo)
                break
            case "2":
                print "Digite o CNPJ da empresa para editar: "
                String cnpj = scanner.nextLine()
                Business empNova = new Business('editado','editado','editado','editado','editado','editado','editado')
                BusinessDAO.editar(cnpj, empNova)
                break
            case "3":
                print "Digite o nome da vaga para editar: "
                String nome = scanner.nextLine()
                Business empAAAA = new Business('editado','editado','editado','editado','editado','editado','editado')
                Job vagaNova = new Job('editado','editado','editado',empAAAA)
                JobDAO.editar(JobDAO.findIdByName(nome),vagaNova)
                break
            case "4":
                print "Digite o nome da competência: "
                String comp = scanner.nextLine()
                CompetenceDAO.editar(CompetenceDAO.findIdByName(comp), "EDITADO")
                break
            default:
                println "Opção inválida, tente novamente."
        }
    }

}