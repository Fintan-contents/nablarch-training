package com.nablarch.example.app.batch.form;


import nablarch.core.util.annotation.Published;

/**
 * {@link nablarch.fw.DataReader} が読み込んだデータレコードをバインドし、バリデーションするフォームクラス。
 * <p>
 * @author Nabu Rakutaro
 */
// @Csvと@CsvFormatを付与してください。
@Published
public class ZipCodeForm {

    // Bean Validation を実施するために、各項目にバリデーション用のアノテーションを付与してください。

    /**
     * 全国地方公共団体コード
     */
    private String localGovernmentCode;

    /**
     * 郵便番号（5桁）
     */
    private String zipCode5digit;

    /**
     * 郵便番号（7桁）
     */
    private String zipCode7digit;

    /**
     * 都道府県名カナ
     */
    private String prefectureKana;

    /**
     * 市区町村名カナ
     */
    private String cityKana;

    /**
     * 町域名カナ
     */
    private String addressKana;

    /**
     * 都道府県名漢字
     */
    private String prefectureKanji;

    /**
     * 市区町村名漢字
     */
    private String cityKanji;

    /**
     * 町域名漢字
     */
    private String addressKanji;

    /**
     * 一町域が二以上の郵便番号
     */
    private String multipleZipCodes;

    /**
     * 小字毎に番地が起番されている町域
     */
    private String numberedEveryKoaza;

    /**
     * 丁目を有する町域
     */
    private String addressWithChome;

    /**
     * 一つの郵便番号で二以上の町域
     */
    private String multipleAddress;

    /**
     * 更新
     */
    private String updateData;

    /**
     * 変更理由
     */
    private String updateDataReason;

    /**
     * 行数を保持するカラム
     */
    private Long lineNumber;

    /**
     * 行数を取得する。
     *
     * @return 行数
     */
    // 行数プロパティのゲッタに @LineNumber を付与してください。

    public Long getLineNumber() {
        return lineNumber;
    }

    /**
     * 全国地方公共団体コードを設定します。
     *
     * @param localGovernmentCode 全国地方公共団体コード
     */
    public void setLocalGovernmentCode(String localGovernmentCode) {
        this.localGovernmentCode = localGovernmentCode;
    }

    /**
     * 郵便番号（5桁）を返します。
     *
     * @return 郵便番号（5桁）
     */
    public String getZipCode5digit() {
        return zipCode5digit;
    }

    /**
     * 郵便番号（5桁）を設定します。
     *
     * @param zipCode5digit 郵便番号（5桁）
     */
    public void setZipCode5digit(String zipCode5digit) {
        this.zipCode5digit = zipCode5digit;
    }

    /**
     * 郵便番号（7桁）を返します。
     *
     * @return 郵便番号（7桁）
     */
    public String getZipCode7digit() {
        return zipCode7digit;
    }

    /**
     * 郵便番号（7桁）を設定します。
     *
     * @param zipCode7digit 郵便番号（7桁）
     */
    public void setZipCode7digit(String zipCode7digit) {
        this.zipCode7digit = zipCode7digit;
    }

    /**
     * 都道府県名カナを返します。
     *
     * @return 都道府県名カナ
     */
    public String getPrefectureKana() {
        return prefectureKana;
    }

    /**
     * 都道府県名カナを設定します。
     *
     * @param prefectureKana 都道府県名カナ
     */
    public void setPrefectureKana(String prefectureKana) {
        this.prefectureKana = prefectureKana;
    }

    /**
     * 市区町村名カナを返します。
     *
     * @return 市区町村名カナ
     */
    public String getCityKana() {
        return cityKana;
    }

    /**
     * 市区町村名カナを設定します。
     *
     * @param cityKana 市区町村名カナ
     */
    public void setCityKana(String cityKana) {
        this.cityKana = cityKana;
    }

    /**
     * 町域名カナを返します。
     *
     * @return 町域名カナ
     */
    public String getAddressKana() {
        return addressKana;
    }

    /**
     * 町域名カナを設定します。
     *
     * @param addressKana 町域名カナ
     */
    public void setAddressKana(String addressKana) {
        this.addressKana = addressKana;
    }

    /**
     * 都道府県名漢字を返します。
     *
     * @return 都道府県名漢字
     */
    public String getPrefectureKanji() {
        return prefectureKanji;
    }

    /**
     * 都道府県名漢字を設定します。
     *
     * @param prefectureKanji 都道府県名漢字
     */
    public void setPrefectureKanji(String prefectureKanji) {
        this.prefectureKanji = prefectureKanji;
    }

    /**
     * 市区町村名漢字を返します。
     *
     * @return 市区町村名漢字
     */
    public String getCityKanji() {
        return cityKanji;
    }

    /**
     * 市区町村名漢字を設定します。
     *
     * @param cityKanji 市区町村名漢字
     */
    public void setCityKanji(String cityKanji) {
        this.cityKanji = cityKanji;
    }

    /**
     * 町域名漢字を返します。
     *
     * @return 町域名漢字
     */
    public String getAddressKanji() {
        return addressKanji;
    }

    /**
     * 町域名漢字を設定します。
     *
     * @param addressKanji 町域名漢字
     */
    public void setAddressKanji(String addressKanji) {
        this.addressKanji = addressKanji;
    }

    /**
     * 一町域が二以上の郵便番号を返します。
     *
     * @return 一町域が二以上の郵便番号
     */
    public String getMultipleZipCodes() {
        return multipleZipCodes;
    }

    /**
     * 一町域が二以上の郵便番号を設定します。
     *
     * @param multipleZipCodes 一町域が二以上の郵便番号
     */
    public void setMultipleZipCodes(String multipleZipCodes) {
        this.multipleZipCodes = multipleZipCodes;
    }

    /**
     * 小字毎に番地が起番されている町域を返します。
     *
     * @return 小字毎に番地が起番されている町域
     */
    public String getNumberedEveryKoaza() {
        return numberedEveryKoaza;
    }

    /**
     * 小字毎に番地が起番されている町域を設定します。
     *
     * @param numberedEveryKoaza 小字毎に番地が起番されている町域
     */
    public void setNumberedEveryKoaza(String numberedEveryKoaza) {
        this.numberedEveryKoaza = numberedEveryKoaza;
    }

    /**
     * 丁目を有する町域を返します。
     *
     * @return 丁目を有する町域
     */
    public String getAddressWithChome() {
        return addressWithChome;
    }

    /**
     * 丁目を有する町域を設定します。
     *
     * @param addressWithChome 丁目を有する町域
     */
    public void setAddressWithChome(String addressWithChome) {
        this.addressWithChome = addressWithChome;
    }

    /**
     * 一つの郵便番号で二以上の町域を返します。
     *
     * @return 一つの郵便番号で二以上の町域
     */
    public String getMultipleAddress() {
        return multipleAddress;
    }

    /**
     * 一つの郵便番号で二以上の町域を設定します。
     *
     * @param multipleAddress 一つの郵便番号で二以上の町域
     */
    public void setMultipleAddress(String multipleAddress) {
        this.multipleAddress = multipleAddress;
    }

    /**
     * 更新を返します。
     *
     * @return 更新
     */
    public String getUpdateData() {
        return updateData;
    }

    /**
     * 更新を設定します。
     *
     * @param updateData 更新
     */
    public void setUpdateData(String updateData) {
        this.updateData = updateData;
    }

    /**
     * 変更理由を返します。
     *
     * @return 変更理由
     */
    public String getUpdateDataReason() {
        return updateDataReason;
    }

    /**
     * 変更理由を設定します。
     *
     * @param updateDataReason 変更理由
     */
    public void setUpdateDataReason(String updateDataReason) {
        this.updateDataReason = updateDataReason;
    }

    /**
     * 行数を設定する。
     *
     * @param lineNumber 行数
     */
    public void setLineNumber(Long lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * 全国地方公共団体コードを返します。
     *
     * @return 全国地方公共団体コード
     */
    public String getLocalGovernmentCode() {
        return localGovernmentCode;
    }

}
