package services

import database.BusinessDAO
import models.Business

class BusinessService {
    List<Business> businesses = []

    static void addBusiness(Business buss){
        BusinessDAO.salvar(buss)
    }

    void listBusinesses(){
        businesses.each {it->
            BusinessDAO.listarTodos().each { println it }
        }
    }

}
