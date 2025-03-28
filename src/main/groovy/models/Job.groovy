package models

class Job {
    String title, description, location;
    Business company;
    List<Competence> requiredCompetences = []

    Job(String title, String description, String location, Business company) {
        this.title = title
        this.description = description
        this.location = location
        this.company = company
    }

    void listRequiredCompetences() {
        requiredCompetences.each { println it.getName() }
    }

    void addRequiredCompetence(Competence competence) {
        requiredCompetences << competence
    }


    @Override
    public String toString() {
        return String.format(
                "----------------------------------\n" +
                        "🏢 Empresa: %s\n" +
                        "📌 Localização: %s\n" +
                        "💼 Cargo: %s\n" +
                        "📝 Descrição: %s\n" +
                        "🛠️ Competências Requeridas: %s\n" +
                        "----------------------------------",
                company.getName(),
                location,
                title,
                description,
                requiredCompetences.collect { it.getName() }.join(", ")
        );
    }

}
