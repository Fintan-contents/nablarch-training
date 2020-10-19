package com.nablarch.example.validator;

import com.nablarch.example.code.CodeEnum;
import nablarch.core.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * CodeValueの検証を行う実装クラス。
 *
 * @author Nabu Rakutaro
 */
public class CodeValueValidator implements ConstraintValidator<CodeValue, String> {

    /** コードを定義したEnumの配列 */
    private Object[] enumValues;

    /**
     * CodeValueValidator を初期化する。
     * @param constraintAnnotation 対象プロパティに付与されたアノテーション
     */
    @Override
    public void initialize(CodeValue constraintAnnotation) {
        enumValues = constraintAnnotation.enumClass().getEnumConstants();
    }

    /**
     * 検証対象の値が指定したenumクラスに含まれるかどうかを検証する。
     * @param value 検証対象の値
     * @param context バリデーションコンテキスト
     * @return 含まれる場合 {@code true}
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtil.isNullOrEmpty(value)) {
            return true;
        }

        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (value.equals(((CodeEnum) enumValue).getCode())) {
                    return true;
                }
            }
        }
        return false;
    }
}
