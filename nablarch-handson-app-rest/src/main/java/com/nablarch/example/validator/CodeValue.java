package com.nablarch.example.validator;

import com.nablarch.example.code.CodeEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link CodeEnum}のコード値のバリデーションを行うクラス。
 *
 * @author Nabu Rakutaro
 */
@Documented
@Constraint(validatedBy = CodeValueValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface CodeValue {

    /**
     * バリデーションエラー発生時に設定するメッセージ。
     * @return デフォルトのエラーメッセージ
     */
    String message() default "{com.nablarch.example.app.entity.core.validation.validator.CodeValue.message}";

    /**
     * コードenumを取得する。
     * @return コードが定義されているenum
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * 複数指定用のアノテーション
     *
     * @author Nabu Rakutaro
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        /**
         * CodeValueの配列を取得する。
         * @return {@link CodeValue}の配列
         */
        CodeValue[] value();
    }

    /**
     * グループを取得する。
     * @return グループ
     */
    Class<?>[] groups() default { };

    /**
     * Payloadを取得する。
     * @return Payload
     */
    Class<? extends Payload>[] payload() default { };

}
