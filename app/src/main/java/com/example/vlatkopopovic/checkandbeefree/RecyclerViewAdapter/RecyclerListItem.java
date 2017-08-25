package com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter;

import com.example.vlatkopopovic.checkandbeefree.Database.SQLite;
import com.squareup.picasso.Picasso;

/**
 * Created by Vlatko Popovic on 19-Aug-17.
 */

public class RecyclerListItem {

    String title;
    String question;
    int image;
    int switchButton;
    String date;

    public RecyclerListItem(String title, String question, int image, int switchButton) {
        this.title = title;
        this.question = question;
        this.image = image;
        this.switchButton = switchButton;
    }
    public RecyclerListItem() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RecyclerListItem(String title, String question, int image, int switchButton, String date) {
        this.title = title;
        this.question = question;
        this.image = image;
        this.switchButton = switchButton;

        this.date = date;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }



    public int getSwitchButton() {
        return switchButton;
    }

    public void setSwitchButton(int switchButton) {
        this.switchButton = switchButton;
    }


}
