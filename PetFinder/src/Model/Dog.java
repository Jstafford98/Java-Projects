package Model ;

import Controller.Controller;

import java.io.Serializable;

public class Dog extends FourLeggedFriend implements Serializable {
    //Instance Variables
    private String mGroup, mBreed ;
    private int mPopularity ;
    private boolean isWatchdog,isIntelligent ;
    //Constructor
    public Dog(String breed,double weight, String size, String temperament, String group, boolean intelligent, int popularity, boolean watchDog) {
        super(weight,size, temperament);
        setBreed(breed) ;
        setGroup(group) ;
        setIntelligence(intelligent);
        setPopularity(popularity);
        setWatchdog(watchDog);
    }
    //Getters
    public String getBreed() { return mBreed; }
    public String getGroup() {
        return mGroup;
    }
    public boolean isIntelligent() {
        return isIntelligent;
    }
    public int getPopularity() {
        return mPopularity;
    }
    public boolean isWatchdog() {
        return isWatchdog;
    }
    //Setters
    public void setBreed(String breed) { mBreed = breed; }
    public void setGroup(String group) { mGroup = group; }
    public void setIntelligence(boolean intelligence) {
        isIntelligent = intelligence;
    }
    public void setPopularity(int popularity) {
        mPopularity = popularity;
    }
    public void setWatchdog(boolean watchdog) {
        isWatchdog = watchdog;
    }
    //Equals
    public boolean equals(Object o){
        if (o == this){
            return true ;
        }
        if (o instanceof Dog){
            Dog other = (Dog) o ;
            return super.equals(other)
                    && mGroup.equals(other.getGroup())
                    && mPopularity == other.getPopularity()
                    && isIntelligent == other.isIntelligent()
                    && isWatchdog == other.isWatchdog() ;
        }
        return false ;
    }
    //toString
    public String toString(){
        String[] traits = getTemperament() ;
        String output = "Dog [Breed = " + getBreed() + " , Size = " + super.getSize() + ", Weight = " + super.getWeight() + ", Temperament = " + Controller.arrayToString(getTemperament())
                + ", Canine Group = " + getGroup() + ", Watch Dog = " + isWatchdog() + ", Intelligence Score = " + isIntelligent() + ", U.S Popularity = " + getPopularity() + "]";
        return output ;
    }
}
