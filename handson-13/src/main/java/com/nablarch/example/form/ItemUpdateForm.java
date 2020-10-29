package com.nablarch.example.form;

import nablarch.core.validation.ee.Domain;
import nablarch.core.validation.ee.Required;

import java.io.Serializable;

/**
 * 商品更新フォーム。
 * @author Nabu Rakutaro
 */
public class ItemUpdateForm implements Serializable {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** 商品名 */
    @Required
    @Domain("itemName")
    private String itemName;

    /** カテゴリー */
    @Required
    @Domain("itemCategory")
    private String category;

    /** 説明 */
    @Domain("itemExplanation")
    private String explanation;

    /** 価格 */
    @Required
    @Domain("amountOfMoney")
    private String price;

    /**
     * 商品名を取得する。
     *
     * @return 商品名
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 商品名を設定する。
     *
     * @param itemName 商品名
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * カテゴリーを取得する。
     *
     * @return カテゴリー
     */
    public String getCategory() {
        return category;
    }

    /**
     * カテゴリーを設定する。
     *
     * @param category カテゴリー
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 説明を取得する。
     *
     * @return 説明
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * 説明を設定する。
     *
     * @param explanation 説明
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * 価格を取得する。
     *
     * @return 価格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 価格を設定する。
     *
     * @param price 価格
     */
    public void setPrice(String price) {
        this.price = price;
    }
}
