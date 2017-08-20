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

    public RecyclerListItem(String title, String question, int image, int switchButton) {
        this.title = title;
        this.question = question;
        this.image = image;
        this.switchButton = switchButton;
    }
    public RecyclerListItem(String title, String question, int image) {
        this.title = title;
        this.question = question;
        this.image = image;

    }
    public RecyclerListItem(String title, String question) {
        this.title = title;
        this.question = question;


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
