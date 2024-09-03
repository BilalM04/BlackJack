import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DeckOfCards {
    private Card[] deck;
    private Card dealtCard, backOfCard;
    private int numCardsDealt, cardsRemaining;
    private Random rnd;
    public static final int TOTAL_CARDS = 52;

    //generates deck without shuffling
    DeckOfCards() {
        //initializing variables
        rnd = new Random();
        numCardsDealt = 0;
        cardsRemaining = TOTAL_CARDS;
        dealtCard = null;
        backOfCard = new Card();
        //generating deck
        generateDeck();
    }

    //generates deck with shuffling
    DeckOfCards(boolean shuffleDeck) {
        //initializing variable
        rnd = new Random();
        numCardsDealt = 0;
        cardsRemaining = TOTAL_CARDS;
        dealtCard = null;
        backOfCard = new Card();
        //generates deck
        generateDeck();
        //shuffles deck
        shuffleDeck();
    }

    //method to get the number of cards dealt
    public int getNumCardsDealt() {
        return numCardsDealt;
    }

    //method to get the number of cards remaining
    public int getCardsRemaining() {
        return cardsRemaining;
    }

    //method to generate deck
    public void generateDeck() {
        deck = new Card[TOTAL_CARDS]; //initializing the deck array
        int indexDeck = 0; //initializing the deck index variable

        //generates the hearts cards
        for (int i=2; i<=14; i++){
            ImageIcon img = new ImageIcon(getClass().getResource("/images/" + i + "H.gif"));
            deck [indexDeck] = new Card(img, i, "H");
            indexDeck++;
        }

        //generates the diamond cards
        for (int i=2; i<=14; i++){
            ImageIcon img = new ImageIcon(getClass().getResource("/images/" + i + "D.gif"));
            deck [indexDeck] = new Card(img, i, "D");
            indexDeck++;
        }

        //generates the clubs cards
        for (int i=2; i<=14; i++){
            ImageIcon img = new ImageIcon(getClass().getResource("/images/" + i + "C.gif"));
            deck [indexDeck] = new Card(img, i, "C");
            indexDeck++;
        }

        //generates the spades cards
        for (int i=2; i<=14; i++){
            ImageIcon img = new ImageIcon(getClass().getResource("/images/" + i + "S.gif"));
            deck [indexDeck] = new Card(img, i, "S");
            indexDeck++;
        }
    }

    //method to shuffle the deck
    public void shuffleDeck() {
        for (int i = 0; i < deck.length; i++){
            int randomIndex = (int)(TOTAL_CARDS*Math.random());
            Card temp = deck[randomIndex];
            deck[randomIndex] = deck [i];
            deck[i]=temp;
        }
    }

    //method to deal a card
    public Card dealCard() {
        Card toReturn = null;

        if (numCardsDealt < TOTAL_CARDS) {
            for (int i = 0; i < TOTAL_CARDS; i++) {
                if (deck[i] != null) {
                    toReturn = deck[i].clone();
                    deck[i] = null;
                    break;
                }
            }
        }
        numCardsDealt++;
        cardsRemaining--;
        return toReturn;
    }
}
