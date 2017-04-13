nablarch-handson-app-batch-ee
===============
Nablarch Framework（nablarch-fw-batch-ee、nablarch-etl）のバッチExampleアプリケーションです。

## アプリケーションのビルドと実行

### 事前準備
本Exampleアプリケーションをビルドする前に、データベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

### アプリケーションのビルド、依存するライブラリの取得

次に、アプリケーションをビルドします。チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    cd nablarch-handson-app-batch-ee
    $mvn clean package

ビルド後、以下のコマンドを実行し依存するライブラリを取得します。

    $mvn dependency:copy-dependencies


### 実行

ビルド、依存ライブラリの取得が終わったら、以下のコマンドを実行するとサンプルアプリケーションを動作させることができます。


    $java -cp ./target/*;./target/dependency/* nablarch.fw.batch.ee.Main <batch-job名>


＜batch-job名＞の指定例を示します。

    java -cp ./target/*;./target/dependency/* nablarch.fw.batch.ee.Main zip-code-truncate-table


＜batch-job名＞を変えることで、CSVからDBおよびDBからCSVへのデータ保存と、DBのTRUNCATE処理を行うことができます。
動作させることができる処理は、次の通りです。実行後、以下の説明に出てくるCSVファイルやテーブルを見て、処理結果を確認してください。

* JBatchのみ利用（ETLなし）
    * (ハンズオン13)賞与計算バッチ(DB→DB)
        * EMPLOYEEテーブルから社員情報を取得し、賞与を計算してBONUSテーブルに登録する、Chunkステップのバッチです。
    * (ハンズオン12)郵便番号テーブルTRUNCATEバッチ
        * ZIP_CODE_DATAテーブル と ZIP_CODE_DATA_WORKテーブルのデータを削除する、Batchletステップのバッチです。
* ETLとJBatchを利用
    * (ハンズオン14)郵便番号登録ETLバッチ(SQL*Loaderを使わないCSV→DB)
        * <チェックアウトディレクトリ>/testdata/input/KEN_ALL.CSV を入力元とし、ZIP_CODE_DATA テーブルにデータを登録します。
        * SQL*Loaderは使いません。
    * 郵便番号出力バッチ(DB→CSV)
        * ZIP_CODE_DATAテーブルのデータを <チェックアウトディレクトリ>/testdata/output 以下に出力します。

動作させる処理は、引数の<batch-job名>を変更することで選択できます。

* JBatchのみ利用（ETLなし）
    * ＜batch-job名＞に「bonus-calculate」を指定すると、賞与計算バッチが実行されます。
    * ＜batch-job名＞に「zip-code-truncate-table」を指定すると、郵便番号テーブルTRUNCATEバッチが実行されます。
* ETLとJBatchを利用
    * ＜batch-job名＞に「etl-zip-code-csv-to-db-chunk」を指定すると、郵便番号登録ETLバッチ(SQL*Loaderを使わないCSV→DB)が実行されます。
    * ＜batch-job名＞に「etl-zip-code-db-to-csv-chunk」を指定すると、郵便番号出力バッチが実行されます。


なお、インプットのCSVデータは、下記サイトより取得できる郵便番号データ（全国一括）を元にしています。

* http://www.post.japanpost.jp/zipcode/dl/kogaki-zip.html

以下の項目が入っています。
* 全国地方公共団体コード（JIS X0401、X0402）
* （旧）郵便番号（5桁）
* 郵便番号（7桁）
* 都道府県名　半角カタカナ
* 市区町村名　半角カタカナ
* 町域名　半角カタカナ
* 都道府県名　漢字
* 市区町村名　漢字
* 町域名　漢字
* 一町域が二以上の郵便番号で表される場合の表示　（「1」は該当、「0」は該当せず）
* 小字毎に番地が起番されている町域の表示　（「1」は該当、「0」は該当せず）
* 丁目を有する町域の場合の表示　（「1」は該当、「0」は該当せず）
* 一つの郵便番号で二以上の町域を表す場合の表示　（「1」は該当、「0」は該当せず）
* 更新の表示　（「0」は変更なし、「1」は変更あり、「2」廃止（廃止データのみ使用））
* 変更理由　（「0」は変更なし、「1」市政・区政・町政・分区・政令指定都市施行、「2」住居表示の実施、 「3」区画整理、「4」郵便区調整等、「5」訂正、「6」廃止（廃止データのみ使用））

### DBの確認方法

1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
2. ブラウザから http://localhost:8082 を開きます。H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
1. 中央のSQLを入力するフィールドに確認のためのSQLを入力し、Runボタンをクリックします。

   **注意**
   h2.bat実行中はバッチアプリケーションからDBへアクセスすることができないため、**バッチを実行できません。**

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |
