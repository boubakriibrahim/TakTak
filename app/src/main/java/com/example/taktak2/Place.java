package com.example.taktak2;

import java.util.ArrayList;

public class Place {

    public String PlaceName="";
    //public imageAdapter placeImages;
    public ArrayList<String> imagesList = new ArrayList<>();
    public String isFavorite = "false";

    public Place(){

    }

    public Place(String PlaceName,String isFavorite){

        this.PlaceName = PlaceName;
        this.isFavorite = isFavorite;
    }

    public String getPlaceName(){return PlaceName;}
    public void setPlaceName(String PlaceName){this.PlaceName = PlaceName;}

    public ArrayList<String>  getPlaceImages(){return imagesList;}
    public void setPlaceImages(ArrayList<String> placeImages){this.imagesList = placeImages ;}

    public String getIsFavorite(){return isFavorite;}
    public void setIsFavorite(String isFavorite){this.isFavorite = isFavorite;}


}
