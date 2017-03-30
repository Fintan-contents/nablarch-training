package com.nablarch.example.app.batch.ee.chunk;

import com.nablarch.example.app.batch.ee.form.EmployeeForm;
import nablarch.common.dao.DeferredEntityList;
import nablarch.common.dao.UniversalDao;

import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Iterator;

/**
 * 社員情報をDBから取得する{@link javax.batch.api.chunk.ItemReader}実装クラス。
 *
 * @author Nabu Rakutaro
 */
@Dependent
@Named
public class EmployeeSearchReader extends AbstractItemReader {

    /** 社員情報のリスト */
    private DeferredEntityList<EmployeeForm> list;

    /** 社員情報を保持するイテレータ */
    private Iterator<EmployeeForm> iterator;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        list = (DeferredEntityList<EmployeeForm>) UniversalDao.defer()
                .findAllBySqlFile(EmployeeForm.class, "SELECT_EMPLOYEE");
        iterator = list.iterator();
    }

    @Override
    public Object readItem() {
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        list.close();
    }
}
