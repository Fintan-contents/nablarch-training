package com.nablarch.example.app.batch.ee.chunk;

import com.nablarch.example.app.batch.ee.form.EmployeeForm;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * 賞与計算を行う{@link ItemProcessor}実装クラス。
 *
 * @author Nabu Rakutaro
 */
@Dependent
@Named
public class BonusCalculateProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) {

        // 引数の「item」には、「EmployeeSearchReader#readItem」の戻り値が渡されます。
        // そこで、「item」を(Objectではなく)本来の型にキャストしてください。

        // Bonusクラスをインスタンス化し、社員IDと支給額を設定してください。支給額の
        // 計算には、calculateBonusメソッドを利用してください。


        // 賞与の計算結果を持つインスタンス(エンティティ)を返却してください。
        return null;
    }

    /**
     * 社員情報をもとに賞与計算を行う。
     *
     * @param form 社員情報Form
     * @return 賞与
     */
    private static Long calculateBonus(EmployeeForm form) {
        if (form.getFixedBonus() == null) {
            return form.getBasicSalary() * form.getBonusMagnification() / 100;
        } else {
            return form.getFixedBonus();
        }
    }
}
