package services

import database.JobDAO
import models.Job

class JobService {
    List<Job> jobs = []

    List<Job> getJobs() {
        return jobs
    }

    void addJob(Job job, int id) {
        jobs << job
        JobDAO.salvar(job, id)
    }

    void listJobs() {
        jobs.each { println it }
    }
}
