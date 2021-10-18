package com.nablarch.example.app.batch.ee.form;

/**
 * 社員情報Form。
 *
 * @author Nabu Rakutaro
 */
public class EmployeeForm {

    /** 社員ID */
    private Long employeeId;

    /** 氏名 */
    private String fullName;

    /** 基本給 */
    private Long basicSalary;

    /** 等級 */
    private Long gradeCode;

    /** 賞与倍率 */
    private Long bonusMagnification;

    /** 固定賞与 */
    private Long fixedBonus;

    /**
     * 社員IDを返します。
     *
     * @return 社員ID
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * 社員IDを設定します。
     *
     * @param employeeId 社員ID
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    /**
     * 氏名を返します。
     *
     * @return 氏名
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 氏名を設定します。
     *
     * @param fullName 氏名
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    /**
     * 基本給を返します。
     *
     * @return 基本給
     */
    public Long getBasicSalary() {
        return basicSalary;
    }

    /**
     * 基本給を設定します。
     *
     * @param basicSalary 基本給
     */
    public void setBasicSalary(Long basicSalary) {
        this.basicSalary = basicSalary;
    }
    /**
     * 等級コードを返します。
     *
     * @return 等級コード
     */
    public Long getGradeCode() {
        return gradeCode;
    }

    /**
     * 等級コードを設定します。
     *
     * @param gradeCode 等級コード
     */
    public void setGradeCode(Long gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * 賞与倍率を返します。
     *
     * @return 賞与倍率
     */
    public Long getBonusMagnification() {
        return bonusMagnification;
    }

    /**
     * 賞与倍率を設定します。
     *
     * @param bonusMagnification 賞与倍率
     */
    public void setBonusMagnification(Long bonusMagnification) {
        this.bonusMagnification = bonusMagnification;
    }

    /**
     * 固定賞与を返します。
     *
     * @return 固定賞与
     */
    public Long getFixedBonus() {
        return fixedBonus;
    }

    /**
     * 固定賞与を設定します。
     *
     * @param fixedBonus 固定賞与
     */
    public void setFixedBonus(Long fixedBonus) {
        this.fixedBonus = fixedBonus;
    }
}
