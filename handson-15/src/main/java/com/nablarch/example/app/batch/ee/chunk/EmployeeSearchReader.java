package com.nablarch.example.app.batch.ee.chunk;

import com.nablarch.example.app.batch.ee.form.EmployeeForm;
import nablarch.common.dao.DeferredEntityList;
import nablarch.fw.batch.ee.chunk.BaseDatabaseItemReader;

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
public class EmployeeSearchReader extends BaseDatabaseItemReader {

    /** 社員情報のリスト */
    private DeferredEntityList<EmployeeForm> list;

    /** 社員情報を保持するイテレータ */
    private Iterator<EmployeeForm> iterator;

    @Override
    public void doOpen(Serializable checkpoint) throws Exception {
        // 遅延ロードを使用して、社員情報(処理対象)を取得し、「list」に代入してください。
        // SQL IDは、「SELECT_EMPLOYEE」を使用してください。
        list = null;
        // listからイテレータを取得してください。
        iterator = null;
    }

    @Override
    public Object readItem() {
        // イテレータを使用して、検索結果を1レコードずつ返却してください。
        // 返却するレコードがない場合は、「null」を返却してください。
        return null;
    }

    @Override
    public void doClose() throws Exception {
        // 「list」をクローズしてください。
    }
}
