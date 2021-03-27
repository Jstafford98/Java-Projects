package Model ;

import java.io.Serializable;
import Controller.Controller ;

public abstract class FourLeggedFriend implements Serializable {
    //Instance Variables
    private double mWeight ;
    private String[] mTemperament ;
    private String mSize ;
    //Constructor
    public FourLeggedFriend(double weight,String size, String temperament){
        setWeight(weight); ;
        setSize(size);
        setTemperament(temperament);
    }
    //Getters
    public double getWeight() {
        return mWeight;
    }
    public String[] getTemperament() {
        return mTemperament;
    }
    public String getSize() {
        return mSize;
    }
    public abstract String getBreed() ;
    public abstract int getPopularity() ;
    //Setters
    public void setWeight(double weight) {
        mWeight = weight;
    }
    public void setTemperament(String temperament) {
        mTemperament = temperament.split(",");
        for (String s : mTemperament){
            s.replace('\"',' ') ;
            s.trim() ;
        }
    }
    public void setSize(String size) {
       mSize = size;
    }
    //Equals
    public boolean equals(Object o){
        if (this == o)
            return true ;
        if (o instanceof FourLeggedFriend){
            FourLeggedFriend other = (FourLeggedFriend) o ;
            return mWeight == other.getWeight()
                    & mTemperament.equals(other.getTemperament())
                    & mSize.equals(other.getSize()) ;
        }
        return false ;
    }
    //ToString
    public String toString(){
        String output = "Four Legged Friend[Size = " + getSize() + ", Weight = " + getWeight() + ", Temperament = " + Controller.arrayToString(getTemperament())
                + "]";
        return output ;
    }
}
