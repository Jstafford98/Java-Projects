package Model ;

import Controller.Controller;

import java.io.Serializable;

public class Cat extends FourLeggedFriend implements Serializable {
    //Instance Variables
    private String mFurLength,mBreed ;
    private int mPopularity ;
    private boolean isLapCat ;
    //Constructor
    public Cat(double weight, String size, String temperament, String furLength, String breed, int popularity, boolean lapCat) {
        super(weight, size, temperament);
        setFurLength(furLength);
        setBreed(breed);
        setPopularity(popularity);
        setLapCat(lapCat);
    }
    //Getters
    public String getFurLength(){
        return mFurLength ;
    }
    public String getBreed(){ return mBreed ; }
    public int getPopularity(){
        return mPopularity ;
    }
    public boolean isLapCat(){
        return isLapCat ;
    }
    //Setters
    public void setFurLength(String furLength){
        mFurLength = furLength ;
    }
    public void setBreed(String breed){  mBreed = breed ; }
    public void setPopularity(int popularity){
        mPopularity = popularity ;
    }
    public void setLapCat(boolean bool){
        isLapCat = bool ;
    }
    //Equals
    public boolean equals(Object o){
        if (o == this){
            return true ;
        }
        if (o instanceof Cat){
            Cat other = (Cat) o ;
            return super.equals(other)
                    && mFurLength.equals(other.getFurLength())
                    && mPopularity == other.getPopularity()
                    && isLapCat == other.isLapCat() ;
        }
        return false ;
    }
    //ToString
    public String toString(){
        String output = "Cat [Breed = " + getBreed() + ", Size = " + super.getSize() + ", Weight = " + super.getWeight() + ", Temperament = " + Controller.arrayToString(getTemperament())
                + ", Fur Length = " + getFurLength() + ", Lap Cat = " + isLapCat() + ", U.S Popularity = " + getPopularity() + "]";
        return output ;
    }
}
