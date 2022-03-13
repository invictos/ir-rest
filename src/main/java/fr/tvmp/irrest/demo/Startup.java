package fr.tvmp.irrest.demo;

import fr.tvmp.irrest.CPAMApplication;
import fr.tvmp.irrest.common.Adresse;
import fr.tvmp.irrest.medical.TraitementEntity;
import fr.tvmp.irrest.medical.TraitementService;
import fr.tvmp.irrest.medical.donneemedicale.DonneeMedicaleEntity;
import fr.tvmp.irrest.medical.donneemedicale.DonneeMedicaleType;
import fr.tvmp.irrest.user.UserService;
import fr.tvmp.irrest.user.administratif.AdministratifEntity;
import fr.tvmp.irrest.user.medecin.MedecinEntity;
import fr.tvmp.irrest.user.medecin.MedecinType;
import fr.tvmp.irrest.user.patient.PatientEntity;
import fr.tvmp.irrest.user.patient.PatientService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


@ApplicationScoped
public class Startup {

    @Inject
    private UserService userService;

    @Inject
    private PatientService patientService;

    @Inject
    private TraitementService traitementService;

    @Inject
    private Logger logger;

    static final String PERSISTANCE_MODE = "update"; // vs create-drop

    static final Integer FLAG_USER_NSS = 965247536;

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object pointless) {
        if(PERSISTANCE_MODE != "update" || !alreadyInitialized()){
            logger.warning("WE ARE ADDING FAKE DATA");
            addFakeData();
        }
    }

    private boolean alreadyInitialized() {
        return patientService.getPatientByNSS(FLAG_USER_NSS)
                .isPresent();
    }

    public void addFakeData(){
        addFlagUser();
        addFakeUsers();
        addTraitements();
    }

    /**
     * DO NOT DELETE, USED TO KNOW IF WE NEED TO ADD FAKE DATA
     */
    public void addFlagUser(){
        userService.addUser(new PatientEntity("testPrenom", "testNom", "1234",
                new Adresse("rue Test", "TestCity"),
                FLAG_USER_NSS));
    }

    public void addFakeUsers(){
        //Patients
        userService.addUser(new PatientEntity("paul", "mcCain", "1234",
                new Adresse("rue de Lille", "Lille"),
                1123456));
        userService.addUser(new PatientEntity("adrien", "lapierre", "1234",
                new Adresse("rue de Dijon", "Dijon"),
                1234567));

        //Administratif
        userService.addUser(new AdministratifEntity("eric", "dupont", "1234",
                5));

        //Medecins
        userService.addUser(new MedecinEntity("didier", "raoult", "1234",
                MedecinType.SPECIALISTE,
                new Adresse("IHU méditerranée", "Marseille"),
                "1234656"));



        logger.info("Added fake users");
    }


    public void addTraitements(){
        PatientEntity patient = (PatientEntity) userService.addUser(new PatientEntity("manon", "dumont", "1234",
                new Adresse("rue de Marseille", "Marseille"),
                2345678));

        MedecinEntity medecin = (MedecinEntity) userService.addUser(new MedecinEntity("Docteur", "Maboul", "1234",
                MedecinType.GENERALISTE,
                new Adresse("Hasbro", "Paris"),
                "9874654"));

        TraitementEntity traitement = new TraitementEntity();
        traitement.setMedecin(medecin);
        traitement.setPatient(patient);
        traitement.setDonneesMedicales(Set.of(new DonneeMedicaleEntity[]{
                new DonneeMedicaleEntity(traitement, DonneeMedicaleType.NOTE, "NOTE 1"),
                new DonneeMedicaleEntity(traitement, DonneeMedicaleType.ORDONANCE, "ORDONNANCE 1")
        }));
        traitementService.addTraitement(traitement);

        logger.info("Added fake treatments");
    }
}

