package com.nablarch.example.app.batch.reader;

import com.nablarch.example.app.batch.form.ZipCodeForm;
import com.nablarch.example.app.batch.reader.iterator.ObjectMapperIterator;
import nablarch.common.databind.ObjectMapperFactory;
import nablarch.core.util.FilePathSetting;
import nablarch.core.util.annotation.Published;
import nablarch.fw.DataReader;
import nablarch.fw.ExecutionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 住所ファイルを読み込むためのデータリーダクラス。
 *
 * @author Nabu Rakutaro
 */
@Published
public class ZipCodeFileReader implements DataReader<ZipCodeForm> {

    /**
     * 読み込むファイルの名称
     */
     // 読み込むファイルの名称は「importZipCode」としてください。
    private static final String FILE_NAME = "";

    /**
     * 処理対象のデータを返すイテレータ
     */
    private ObjectMapperIterator<ZipCodeForm> iterator;

    /**
     * 業務ハンドラが処理する一行分のデータを返却する。
     *
     * @param ctx 実行コンテキスト
     * @return 一行分のデータ
     */
    @Override
    public ZipCodeForm read(ExecutionContext ctx) {

        // イテレータが存在しない場合は、initializeメソッドを呼び出して、
        // イテレータを生成する処理を実装してください。


        // 一行分のデータを返却してください。
        return null;
    }

    /**
     * 次行があるかどうかを返す。
     *
     * @param ctx 実行コンテキスト
     * @return 次行がある場合は {@code true} 、ない場合は {@code false}
     */
    @Override
    public boolean hasNext(ExecutionContext ctx) {

        // イテレータが存在しない場合は、initializeメソッドを呼び出して、
        // イテレータを生成する処理を実装してください。


        // 次行の有無を返却してください。
        return false;
    }

    /**
     * 終了処理。
     *
     * @param ctx 実行コンテキスト
     */
    @Override
    public void close(ExecutionContext ctx) {
        // ObjectMapperIterator#close()を呼び出して、イテレータをclose処理するを実装してください。

    }

    /**
     * 初期化処理。
     * <p/>
     * イテレータを生成する。
     * @throws RuntimeException ファイルの読み込みに失敗した場合
     */
    private void initialize() {
        FilePathSetting filePathSetting = FilePathSetting.getInstance();
        File zipCodeFile = filePathSetting.getFileWithoutCreate("csv-input", FILE_NAME);

        // ファイルの読み出しに利用するイテレータを生成
        try {
            iterator = new ObjectMapperIterator<>(ObjectMapperFactory.create(ZipCodeForm.class,
                    new FileInputStream(zipCodeFile)));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

}
