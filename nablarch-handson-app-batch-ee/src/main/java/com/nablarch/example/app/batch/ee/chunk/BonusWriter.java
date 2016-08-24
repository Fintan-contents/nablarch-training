package com.nablarch.example.app.batch.ee.chunk;

import nablarch.common.dao.UniversalDao;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.List;

/**
 * 賞与の計算結果をDBに登録する{@link javax.batch.api.chunk.ItemWriter}実装クラス。
 */
@Dependent
@Named
public class BonusWriter extends AbstractItemWriter {

    @Override
    public void writeItems(List<Object> items) {
        UniversalDao.batchInsert(items);
    }
}
