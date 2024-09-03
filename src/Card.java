import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Card {
    private int value;
    private String suit, name;
    private ImageIcon imgCard;

    //constructor for the back of a card
    protected Card() {
        this.imgCard = new ImageIcon(getClass().getResource("/images/back.png"));
        this.value = 0;
        this.suit = "";
        this.name = "";
    }

    //constructor for a card
    Card(ImageIcon imgCard, int value, String suit) {
        this.imgCard = imgCard;
        this.value = value;
        this.suit = suit;
        this.name = getName();
    }

    //method to get the image icon of the card
    public ImageIcon getImage() {
        return imgCard;
    }

    //method to get the name of the card
    public String getName() {
        if (value == 11) {
            this.name = "JACK of<br/>" + getSuit();
        }
        else if (value == 12) {
            this.name = "QUEEN of<br/>" + getSuit();
        }
        else if (value == 13) {
            this.name = "KING of<br/>" + getSuit();
        }
        else if (value == 14) {
            this.name = "ACE of<br/>" + getSuit();
        }
        else {
            this.name = value + " of<br/>" + getSuit();
        }
        return name;
    }

    //method to get the suit of the card
    public String getSuit() {
        if (suit.equals("H")) {
            return "HEARTS";
        }
        else if (suit.equals("D")) {
            return "DIAMONDS";
        }
        else if (suit.equals("S")) {
            return "SPADES";
        }
        else {
            return "CLUBS";
        }
    }

    //method to clone a card
    public Card clone() {
        return new Card(this.imgCard, this.value, this.suit);
    }

    //method to get the value of a card
    public int getValue() {
        return value;
    }

    //method to set the image of a card
    public void setImage(ImageIcon img) {
        imgCard = img;
    }

    //method to set the name of a card
    public void setName(String name) {
        this.name = name;
    }

    //method to set the suit of a card
    public void setSuit(String suit) {
        this.suit = suit;
    }

    //method to set the value of a card
    public void setValue(int val) {
        value = val;
    }
}