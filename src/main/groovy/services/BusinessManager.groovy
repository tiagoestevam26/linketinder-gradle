package services

import database.BusinessDAO
import models.Business

class BusinessManager {
    List<Business> businesses = []

    void addBusiness(Business buss){
        BusinessDAO.salvar(buss)
    }

    void listBusinesses(){
        businesses.each {it->
            BusinessDAO.listarTodos().each { println it }
        }
    }

}
