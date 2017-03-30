package com.nablarch.example.app.batch.ee.batchlet;

import nablarch.core.db.connection.AppDbConnection;
import nablarch.core.db.connection.DbConnectionContext;
import nablarch.core.db.statement.SqlPStatement;

import javax.batch.api.AbstractBatchlet;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * テーブルをTRUNCATEするBatchlet。
 * <p/>
 * TRUNCATE対象のテーブルはBatchletの「tableName」プロパティで指定します。
 *
 * @author Nabu Rakutaro
 */
@Dependent
@Named
public class TruncateTableBatchlet extends AbstractBatchlet {

    /**
     * TRUNCATE対象テーブル。
     * ジョブ定義ファイルのproperty要素で指定できるようにしてください。
     */
    private String tableName;

    @Override
    public String process() {

        final AppDbConnection conn = DbConnectionContext.getConnection();
        final SqlPStatement statement = conn.prepareStatement("TRUNCATE TABLE " + tableName);
        statement.executeUpdate();

        return "SUCCESS";
    }
}

