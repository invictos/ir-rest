# IR-rest
Projet d'Informatique Répartit a l'INSA Rouen Normandie, système d'assurance maladie ! 

Le projet utilise: 
- JPA
- JAX-RS


## Lancement
### Création de l'image serveur
```
sudo docker-compose up
```

### Accès

| Nom      | URL                                         |
|----------|---------------------------------------------|
| root     | http://127.0.0.1:8080/ir-rest-1.0-SNAPSHOT  |
| admin    | http://127.0.0.1:9990/                      |
| database | postgres://database1:5432     (admin:admin) |

### Sans Docker (h2 dans wildfly)
```java
//CPAMApplication.java
//  Le serveur wildfly doit avoir un utilisateur admin:admin
class CPAMApplication{
    static private final String PERSISTANCE_UNIT_NAME = "devPU";
}
```


## Documentation
- [Tutoriel youtube JAX-RS & Java EE](https://www.youtube.com/watch?v=DRxeW8R7VuE&list=PLzzeuFUy_CniPG4Nj_4_lbfaejM2_ScCe&index=18) 
- [JSONB Annotations](https://javaee.github.io/jsonb-spec/users-guide.html)