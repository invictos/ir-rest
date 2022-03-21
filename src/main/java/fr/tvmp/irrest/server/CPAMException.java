package fr.tvmp.irrest.server;

public class CPAMException extends Exception {
    public CPAMException(){
        super();
    }

    public CPAMException(String exception){
        super(exception);
    }
}