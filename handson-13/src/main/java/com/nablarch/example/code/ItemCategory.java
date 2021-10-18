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
    private String code;

    /**
     * コンストラクタ。
     * @param code コード値
     * @param label ラベル
     */
    ItemCategory(String code, String label) {
        this.label = label;
        this.code = code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getCode() {
        return code;
    }
}
