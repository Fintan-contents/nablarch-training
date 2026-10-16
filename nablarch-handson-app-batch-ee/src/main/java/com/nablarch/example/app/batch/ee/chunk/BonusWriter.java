package com.nablarch.example.app.batch.ee.chunk;

import nablarch.common.dao.UniversalDao;

import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import java.util.List;

/**
 * 賞与の計算結果をDBに登録する{@link jakarta.batch.api.chunk.ItemWriter}実装クラス。
 *
 * @author Nabu Rakutaro
 */
@Dependent
@Named
public class BonusWriter extends AbstractItemWriter {

    @Override
    public void writeItems(List<Object> items) {
        UniversalDao.batchInsert(items);
    }
}
