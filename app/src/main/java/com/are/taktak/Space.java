package com.are.taktak;

import android.widget.ImageView;

public class Space {

    //public ImageView spaceImage;
    public String spaceName,spaceDescription,spaceSector,spaceImage;
    public String isFavorite = "false";

    public Space(){

    }

    public Space(String spaceImage,String spaceName,String spaceDescription,String spaceSector,String isFavorite){

        this.spaceImage = spaceImage;
        this.spaceName = spaceName;
        this.spaceDescription = spaceDescription;
        this.spaceSector = spaceSector;
        this.isFavorite = isFavorite;
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


}
