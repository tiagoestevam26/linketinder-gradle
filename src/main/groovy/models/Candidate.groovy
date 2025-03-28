package models

class Candidate implements Person{

    String CPF;
    Date bornDate;
    List<Competence> competences = []

    Candidate(String name, Date born, String email, String cpf, String country, String cep, String description, String password) {
        this.name = name
        this.bornDate = born
        this.email = email
        this.country = country
        this.cep = cep
        this.description = description
        this.CPF = cpf
        this.password = password
    }

    void addCompetence(Competence comp) {
        competences << comp
    }


    @Override
    public String toString() {
        return String.format(
                "----------------------------------\n" +
                        "👤 Candidato\n" +
                        "📋 Descrição: %s\n" +
                        "🛠️ Competências: %s\n" +
                        "----------------------------------",
                this.description,
                competences.collect { it.getName() }.join(", ")
        );
    }

}
