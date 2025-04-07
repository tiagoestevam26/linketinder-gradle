package View

import services.BusinessService
import services.CandidatesService

import services.JobService

class MainMenu {
    Scanner scanner = new Scanner(System.in)
    CandidatesService candidateManager = new CandidatesService()
    BusinessService businessManager = new BusinessService()
    JobService jobManager = new JobService()


    void exibirMenu() {
        while (true) {
            println "\n♡LINKETINDER♡:"
            println "1 - Fazer login"
            println "2 - Criar conta"
            println "3 - Deletar algo"
            println "4 - Sair"
            print "Escolha uma opção: "

            String choice = scanner.nextLine()

            switch (choice) {
                case "1":
                    LoginView.telaLogin(scanner)
                    break
                case "2":
                    CadastroView.telaCriarConta(scanner, businessManager, candidateManager, jobManager)
                    break
                case "3":
                    DeleteView.telaDeletar(scanner)
                    break
                case "4":
                    println "Saindo..."
                    return
                default:
                    println "Opção inválida, tente novamente."
            }
        }
    }

}
