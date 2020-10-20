package com.nablarch.example.domain;

import com.nablarch.example.code.ItemCategory;
import com.nablarch.example.validator.CodeValue;
import nablarch.core.validation.ee.Digits;
import nablarch.core.validation.ee.Length;
import nablarch.core.validation.ee.NumberRange;
import nablarch.core.validation.ee.SystemChar;

/**
 * ドメインを定義したBean。
 *
 * @author Nabu Rakutaro
 */
public class RestExampleDomain {
    /** ID */
    @NumberRange(min = 0)
    @Digits(integer = 19)
    private String id;

    /** 商品名 */
    @Length(max = 64)
    @SystemChar(charsetDef = "全角文字", allowLineSeparator = false)
    private String itemName;

    /** 商品カテゴリーを表すコード値 */
    @CodeValue(enumClass = ItemCategory.class)
    private String itemCategory;

    /** 商品説明 */
    @Length(max = 256)
    @SystemChar(charsetDef = "システム許容文字", allowLineSeparator = true)
    private String itemExplanation;

    /** 金額 */
    @NumberRange(min = 0, max = 99999999, message = "{nablarch.core.validation.ee.NumberRange.money.range.message}")
    private String amountOfMoney;
}
