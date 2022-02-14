package com.example.taktak2;

public class Space {

    public String spaceName = "";
    public String spaceDescription = "";
    public String spaceSector = "";
    public String spaceImage = "";
    public String isFavorite = "false";
    public String nbPersone="0";
    public int nbReviews=1;
    public int temps=0;
    public double rate=4.5;

    public Space(){

    }

    public Space(String spaceImage,String spaceName,String spaceDescription,String spaceSector,String isFavorite,String nbPersone,int nbReviews,Double rate){

        this.spaceImage = spaceImage;
        this.spaceName = spaceName;
        this.spaceDescription = spaceDescription;
        this.spaceSector = spaceSector;
        this.isFavorite = isFavorite;
        this.nbPersone = nbPersone;
        this.nbReviews = nbReviews;
        this.rate = rate;
    }

    public String getSpaceName(){return spaceName;}
    public void setSpaceName(String spaceName){this.spaceName = spaceName;}

    public String getSpaceDescription(){return spaceDescription;}
    public void setSpaceDescription(String spaceDescription){this.spaceDescription = spaceDescription;}

    public String getSpaceSector(){return spaceSector;}
    public void setSpaceSector(String spaceSector){this.spaceSector = spaceSector;}

    public String getIsFavorite(){return isFavorite;}
    public void setIsFavorite(String isFavorite){this.isFavorite = isFavorite;}

    public String getImageView(){return spaceImage;}
    public void setImageView(String imageView){this.spaceImage = imageView;}

    public String getNbPersone(){return nbPersone;}
    public void setNbPersone(String nb){this.nbPersone = nb;}

    public int getNbReviews(){return nbReviews;}
    public void setNbReviews(int nb){this.nbReviews= nb;}

    public double getRate(){return rate;}
    public void setRate(double rate){this.rate = rate;}


}

