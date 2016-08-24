package com.nablarch.example.app.batch.ee.chunk;

import com.nablarch.example.app.batch.ee.form.EmployeeForm;
import com.nablarch.example.app.entity.Bonus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * {@link BonusCalculateProcessor}のテストクラス。
 */
public class BonusCalculateProcessorTest {

    private BonusCalculateProcessor sut = new BonusCalculateProcessor();

    /**
     * 固定賞与のケース。
     */
    @Test
    public void testFixedBonus() {

        final EmployeeForm dto = new EmployeeForm();
        dto.setEmployeeId(1L);
        dto.setBasicSalary(100000L);
        dto.setFixedBonus(50000L);

        Bonus result = (Bonus) sut.processItem(dto);

        assertThat(result.getEmployeeId(), is(1L));
        assertThat(result.getPayments(), is(50000L));
    }

    /**
     * 基本給をもとに賞与計算を行うケース。
     */
    @Test
    public void testMagnification() {

        final EmployeeForm dto = new EmployeeForm();
        dto.setEmployeeId(2L);
        dto.setBasicSalary(250000L);
        dto.setBonusMagnification(200L);

        Bonus result = (Bonus) sut.processItem(dto);

        assertThat(result.getEmployeeId(), is(2L));
        assertThat(result.getPayments(), is(500000L));
    }
}