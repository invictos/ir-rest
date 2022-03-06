package fr.tvmp.irrest;

public class CPAMException extends Exception {
    public CPAMException(){
        super();
    }

    public CPAMException(String exception){
        super(exception);
    }
}