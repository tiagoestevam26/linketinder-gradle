package View

import database.BusinessDAO
import database.CandidateDAO
import database.CompetenceDAO
import database.JobDAO

class DeleteView {

    static void telaDeletar(Scanner scanner){
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
}
