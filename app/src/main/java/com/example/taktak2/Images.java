package com.example.taktak2;

public class Images {

    public String img1="";
    public String img2="";
    public String img3="";
    public String img4="";
    public String img5="";

    public Images(){

    }

    public Images(String im1,String im2,String im3,String im4,String im5){

        this.img1 = im1;
        this.img2 = im2;
        this.img3 = im3;
        this.img4 = im4;
        this.img5 = im5;
    }

    public String getImage1(){return img1;}
    public void setImage1(String im){this.img1 = im;}

    public String getImage2(){return img2;}
    public void setImage2(String im){this.img2 = im;}

    public String getImage3(){return img3;}
    public void setImage3(String im){this.img3 = im;}

    public String getImage4(){return img4;}
    public void setImage4(String im){this.img4 = im;}

    public String getImage5(){return img5;}
    public void setImage5(String im){this.img5 = im;}

}
