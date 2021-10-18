package com.nablarch.example.app.batch.action;

import com.nablarch.example.app.batch.form.ZipCodeForm;
import com.nablarch.example.app.batch.interceptor.ValidateData;
import com.nablarch.example.app.batch.reader.ZipCodeFileReader;
import nablarch.core.util.annotation.Published;
import nablarch.fw.DataReader;
import nablarch.fw.ExecutionContext;
import nablarch.fw.Result;
import nablarch.fw.action.BatchAction;

/**
 * 住所ファイルをDBに登録するバッチクラス。
 * @author Nabu Rakutaro
 */
@Published
public class ImportZipCodeFileAction extends BatchAction<ZipCodeForm> {

    /**
     * {@link ZipCodeFileReader} から渡された一行分の情報をDBに登録する。
     * <p/>
     * メソッド実行時に{@link ValidateData} がインターセプトされるため、
     * このメソッドには常にバリデーション済みの {@code inputData} が引き渡される。
     *
     * @param inputData 一行分の住所情報
     * @param ctx       実行コンテキスト
     * @return 結果オブジェクト
     */
    @Override
    @ValidateData
    public Result handle(ZipCodeForm inputData, ExecutionContext ctx) {

        // ZipCodeFileReaderから渡された一行分の情報(inputData)をZIP_CODE_DATAテーブルにinsertする処理を実装してください。


        // 処理結果としてハンドラの処理が正常終了したことを表す結果オブジェクトを返却してください。
        return null;
    }

    /**
     * リーダを作成する。
     *
     * @param ctx 実行コンテキスト
     * @return リーダーオブジェクト
     */
    @Override
    public DataReader<ZipCodeForm> createReader(ExecutionContext ctx) {

        // データリーダクラス(ZipCodeFileReader)のインスタンスを返却してください。
        return null;
    }
}
