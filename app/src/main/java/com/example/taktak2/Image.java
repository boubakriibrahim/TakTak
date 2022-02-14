package com.example.taktak2;

public class Image {

    public String ImageName="",ImageUrl;

    public Image(){

    }

    public Image(String ImageName,String ImageUrl){

        this.ImageName = ImageName;
        this.ImageUrl = ImageUrl;
    }

    public String getImageName(){return ImageName;}
    public void setImageName(String ImageName){this.ImageName = ImageName;}

    public String getImageUrl(){return ImageUrl;}
    public void setImageUrl(String ImageUrl){this.ImageUrl = ImageUrl;}

}
