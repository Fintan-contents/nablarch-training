package com.nablarch.example.app.validation;

import nablarch.core.validation.ee.Length;
import nablarch.core.validation.ee.SystemChar;

/**
 * ドメインバリデーション用のBeanクラス。
 *
 * @author Nabu Rakutaro
 */
@SuppressWarnings("all")
public class DomainBean {

    /** 公共団体コード */
    @Length(min = 5, max = 5)
    @SystemChar(charsetDef = "数字")
    private String localGovernmentCode;

    /** 旧郵便番号 */
    @Length(min = 3, max = 5)
    @SystemChar(charsetDef = "数字とスペース")
    private String oldZipCode;

    /** 郵便番号 */
    @Length(max = 7, min = 7)
    @SystemChar(charsetDef = "数字")
    private String zipCode;

    /** 都道府県名 */
    @Length(max = 10)
    @SystemChar(charsetDef = "地名漢字")
    private String prefecture;

    /** 市区町村名漢字 */
    @Length(max = 50)
    @SystemChar(charsetDef = "地名漢字")
    private String city;

    /** 町域 */
    @Length(max = 100)
    private String address;


    /** 都道府県名(カナ) */
    @Length(max = 10)
    @SystemChar(charsetDef = "半角カタカナ")
    private String prefectureKana;

    /** 市区町村名(カナ) */
    @Length(max = 50)
    @SystemChar(charsetDef = "半角カタカナ")
    private String cityKana;

    /** フラグ */
    @Length(min = 1, max = 1)
    @SystemChar(charsetDef = "フラグ")
    private String flag;

    /** コード */
    @Length(min = 1, max = 1)
    @SystemChar(charsetDef = "数字")
    private String code;

}
