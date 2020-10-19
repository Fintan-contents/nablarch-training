package com.nablarch.example.dto;

import java.io.Serializable;

/**
 * 商品検索のDto
 *
 * @author Nabu Rakutaro
 */
public class ItemSearchDto implements Serializable {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** 商品名 */
    private String itemName;

    /** カテゴリー */
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
     * @return 等級コード
     */
    public String getCategory() {
        return category;
    }

    /**
     * カテゴリーを設定する。
     *
     * @param category 等級コード
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
