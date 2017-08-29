package com.example.mimi.povely;
//MarketItem
public class Item {
    int image;
    int price;
    String title;

    int getImage() {
        return this.image;
    }
    int getPrice(){
        return this.price;
    }
    String getTitle() {
        return this.title;
    }

    Item(int image, String title) {
        this.image = image;
        this.price = price;
        this.title = title;
    }
}
