package services

import models.Job

class JobManager {
    List<Job> jobs = []

    List<Job> getJobs() {
        return jobs
    }

    void addJob(Job job) {
        jobs << job
    }

    void listJobs() {
        jobs.each { println it }
    }
}
