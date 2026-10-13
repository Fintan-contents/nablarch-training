package com.nablarch.example.code;

/**
 * 本Exampleで使用する商品カテゴリーを定義したEnum。
 *
 * @author Nabu Rakutaro
 */
public enum ItemCategory implements CodeEnum {
    /** ハードウェア */
    HARDWARE("hardware", "ハードウェア"),
    /** ソフトウェア */
    SOFTWARE("software", "ソフトウェア");

    /** 商品カテゴリーのラベル */
    private String label;
    /** 商品カテゴリーのコード */
    private String value;

    /**
     * コンストラクタ。
     * @param code コード値
     * @param label ラベル
     */
    ItemCategory(String code, String label) {
        this.label = label;
        this.value = code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getValue() {
        return value;
    }
}
