package services

import database.JobDAO
import models.Job

class JobService {
    List<Job> jobs = []

    void addJob(Job job, int id) {
        jobs << job
        JobDAO.getInstance().salvar(job, id)
    }

}
