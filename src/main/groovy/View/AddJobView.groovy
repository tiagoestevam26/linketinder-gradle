package View

import database.BusinessDAO
import database.CompetenceDAO
import database.JobDAO
import models.Business
import models.Competence
import models.Job
import services.JobService

class AddJobView {

    static void telaAdicionarVaga(Business empresa, JobService jobManager, Scanner scanner){
        println "\n♡ADICIONAR VAGA♡:"
        print "Título da vaga: "
        String title = scanner.nextLine()
        print "Descrição da vaga: "
        String description = scanner.nextLine()
        print "Localização: "
        String loc = scanner.nextLine()

        Job vaga = new Job(title, description, loc, empresa)
        Integer id = BusinessDAO.findIdByCNPJ(empresa.getCNPJ())
        jobManager.addJob(vaga, id)


        while (true) {
            print "Adicionar competência desejada (ou pressione Enter para finalizar): "
            String comp = scanner.nextLine()
            if (comp.isEmpty()) break
            Competence competence = new Competence(comp)
            vaga.addRequiredCompetence(competence)
            CompetenceDAO.salvar(competence)

        }

        empresa.addJob(vaga)
        println "Vaga adicionada com sucesso!"

    }

}
