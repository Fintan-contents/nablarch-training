package com.nablarch.example.app.batch.ee.chunk;

import java.io.Serializable;
import java.util.Iterator;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import nablarch.common.dao.DeferredEntityList;
import nablarch.common.dao.UniversalDao;
import nablarch.fw.batch.ee.chunk.BaseDatabaseItemReader;
import nablarch.fw.batch.ee.progress.ProgressManager;

import com.nablarch.example.app.batch.ee.form.EmployeeForm;

/**
 * 社員情報をDBから取得する{@link javax.batch.api.chunk.ItemReader}実装クラス。
 *
 * @author Nabu Rakutaro
 */
@Dependent
@Named
public class EmployeeSearchReader extends BaseDatabaseItemReader {

    /** 社員情報のリスト */
    private DeferredEntityList<EmployeeForm> list;

    /** 社員情報を保持するイテレータ */
    private Iterator<EmployeeForm> iterator;

    /** 進捗管理Bean */
    private final ProgressManager progressManager;

    /**
     * コンストラクタ。
     *
     * @param progressManager 進捗管理Bean
     */
    @Inject
    public EmployeeSearchReader(ProgressManager progressManager) {
        this.progressManager = progressManager;
    }

    @Override
    public void doOpen(Serializable checkpoint) throws Exception {

        progressManager.setInputCount(UniversalDao.countBySqlFile(EmployeeForm.class, "SELECT_EMPLOYEE"));

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
    public void doClose() throws Exception {
        list.close();
    }
}
