package com.example.zhang.abercrombiedemo.Items;

/**
 * This a Product Model Class
 *
 * @author Andrew Zhang
 * @version 1.0
 * @since 2016-10-31
 */

public class ProductItem
{
    private String backgroundImage;
    private String topDescription;
    private String title;
    private String promoMessage;
    private String bottomDescription;
    private String selectButton_one;
    private String getSelectButton_two;
    private String button_one_link;
    private String button_two_link;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopDescription() {
        return topDescription;
    }

    public void setTopDescription(String topDescription) {
        this.topDescription = topDescription;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getPromoMessage() {
        return promoMessage;
    }

    public void setPromoMessage(String promoMessage) {
        this.promoMessage = promoMessage;
    }

    public String getBottomDescription() {
        return bottomDescription;
    }

    public void setBottomDescription(String bottomDescription) {
        this.bottomDescription = bottomDescription;
    }

    public String getSelectButton_one() {
        return selectButton_one;
    }

    public void setSelectButton_one(String selectButton_one) {
        this.selectButton_one = selectButton_one;
    }

    public String getGetSelectButton_two() {
        return getSelectButton_two;
    }

    public void setGetSelectButton_two(String getSelectButton_two) {
        this.getSelectButton_two = getSelectButton_two;
    }


    public String getButton_two_link() {
        return button_two_link;
    }

    public void setButton_two_link(String button_two_link) {
        this.button_two_link = button_two_link;
    }

    public String getButton_one_link() {
        return button_one_link;
    }

    public void setButton_one_link(String button_one_link) {
        this.button_one_link = button_one_link;
    }
}
