package View

import database.CandidateDAO
import database.JobDAO

class UserView {

    static void telaCandidato(Scanner scanner, Boolean foiCurtido){
        println "\n♡LISTA DE VAGAS♡:"
        JobDAO.listarTodos().each { job->
            if(foiCurtido){
                print(job.toString())
                println "\n1 - Curtir - ♡"
                println "2 - Passar - X"
                print "Escolha uma opção: "

                String choice = scanner.nextLine()

                switch (choice) {
                    case "1":
                        println "Curtiu... ♡"
                        foiCurtido = false
                        break
                    case "2":
                        println "Passou... X"
                        break
                    default:
                        println "Opção inválida, tente novamente."
                }
            }
        }
        foiCurtido = true

    }

    static void telaEmpresa(Scanner scanner, Boolean foiCurtido){
        println "\n♡LISTA DE CANDIDATOS♡:"
        CandidateDAO.listarTodos().each { candidate->
            if(foiCurtido){
                print(candidate.toString())
                println "\n1 - Curtir - ♡"
                println "2 - Passar - X"
                print "Escolha uma opção: "

                String choice = scanner.nextLine()

                switch (choice) {
                    case "1":
                        println "Curtiu... ♡"
                        foiCurtido = false
                        return
                    case "2":
                        println "Passou... X"
                        break
                    default:
                        println "Opção inválida, tente novamente."
                }
            }
        }
        foiCurtido = true

    }

}
