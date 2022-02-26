#IR-rest
- Rest via **JAX-RS**  
  

## Lancement
### lancement du serveur
```
docker build --tag=ir-test .
docker run -p 8080:8080 -p 9990:9990 -it ir-test
```
### url
http://127.0.0.1:8080/ir-rest-1.0-SNAPSHOT/api/hello-world

## Documentation
- [Tutoriel youtube JAX-RS & Java EE](https://www.youtube.com/watch?v=DRxeW8R7VuE&list=PLzzeuFUy_CniPG4Nj_4_lbfaejM2_ScCe&index=18) 