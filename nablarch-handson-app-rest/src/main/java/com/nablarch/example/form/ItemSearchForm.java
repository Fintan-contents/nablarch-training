package com.nablarch.example.form;

import nablarch.core.validation.ee.Domain;

import java.io.Serializable;

/**
 * 商品検索フォーム
 *
 * @author Nabu Rakutaro
 */
public class ItemSearchForm implements Serializable {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** 商品名 */
    @Domain("itemName")
    private String itemName;

    /** カテゴリー */
    @Domain("itemCategory")
    private String category;

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
     * @param itemName 商品名
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * カテゴリーを取得する。
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
}
