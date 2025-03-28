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
    public String toString() {
        return "Business{" +
                "CNPJ='" + CNPJ + '\'' +
                ", models_Person__name='" + models_Person__name + '\'' +
                ", models_Person__email='" + models_Person__email + '\'' +
                ", models_Person__country='" + models_Person__country + '\'' +
                ", models_Person__cep='" + models_Person__cep + '\'' +
                ", models_Person__description='" + models_Person__description + '\'' +
                ", models_Person__password='" + models_Person__password + '\'' +
                '}';
    }
}
