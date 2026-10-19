package com.nablarch.example.app.batch.ee.batchlet;

import nablarch.core.db.connection.AppDbConnection;
import nablarch.core.db.connection.DbConnectionContext;
import nablarch.core.db.statement.SqlPStatement;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.api.BatchProperty;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

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
     * テーブル名
     */
    @Inject
    @BatchProperty
    private String tableName;

    @Override
    public String process() {

        final AppDbConnection conn = DbConnectionContext.getConnection();
        final SqlPStatement statement = conn.prepareStatement("TRUNCATE TABLE " + tableName);
        statement.executeUpdate();

        return "SUCCESS";
    }
}

