package models

class Business implements Person{
    String CNPJ
    List<Job> openJobs = []

    Business(String name, String email, String cnpj, String country, String cep, String description, String password) {
        this.name = name
        this.email = email
        this.country = country
        this.cep = cep
        this.description = description
        this.CNPJ = cnpj
        this.password = password
    }

    void addJob(Job job) {
        openJobs << job
    }

    @Override
    String toString() {
        return """\
======== Empresa ========
Nome       : $name
Email      : $email
CNPJ       : $CNPJ
País       : $country
CEP        : $cep
Descrição  : $description
Vagas Abertas: ${openJobs.size()}
=========================
"""
    }


}
