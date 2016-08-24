nablarch-example-batch-ee
===============
Nablarch Framework（nablarch-fw-batch-ee、nablarch-etl）のサンプルアプリケーションです。<br />

## 開発環境構築

### 0. 環境

* H2
* Java 8
* Maven 3.2.5

### 1. リポジトリの作成
以下のGitリポジトリを同ディレクトリ内に作成してください。<br />
ローカルで動作させるだけの場合は対象リポジトリの適切なブランチのZipをダウンロードし、ディレクトリに展開するだけでもかまいません。<br />

    $git clone http://nablarch.intra.tis.co.jp/gitbucket/git/nablarch-example/nablarch-example-batch-ee.git

※ リポジトリのディレクトリ名は変更しないでください。

全てのブランチを開発に使用したいブランチに変更してください。

```リモートブランチへの切り替え例
    $cd nablarch-example-batch-ee
    $git branch <ブランチ名> origin/<ブランチ名>
    $git checkout <ブランチ名>
```

### 2. mavenのプロキシ設定
mavenのsetting.xmlにTISプロキシの設定を追加してください。
設定例は、nablarch-example-app-web下のREADME.mdをご覧ください。


### 4. アプリケーションのビルド・実行

#### 4.1. データベースのセットアップ及びエンティティクラスの作成
まず、データベースのセットアップ及びエンティティクラスの作成を行います。以下のコマンドを実行してください。

    $cd nablarch-example-batch-ee
    $mvn -P gsp generate-resources

実行に成功すると、以下のようなログがコンソールに出力され、nablarch-example-batchディレクトリの下にgsp-targetディレクトリが作成されます。

    (中略)
    [INFO] --- gsp-dba-maven-plugin:3.2.0:export-schema (default-cli) @ nablarch-example-batch-ee ---
    [INFO] PUBLICスキーマのExportを開始します。:C:\Users\tikK49797\Desktop\Example_fix\nablarch-example-batch-ee\gsp-target\output\PUBLIC.dmp
    [INFO] Building jar: C:\Users\tikK49797\Desktop\Example_fix\nablarch-example-batch-ee\gsp-target\output\nablarch-example-batch-ee-testdata-1.0.1.jar
    [INFO] PUBLICスキーマのExport完了
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    (中略)

#### 4.2. アプリケーションのビルド、依存するライブラリの取得

次に、アプリケーションをビルドします。以下のコマンドを実行してください。

    $mvn clean package

実行に成功すると、以下のようなログがコンソールに出力されます。

    (中略)
    [INFO]
    [INFO] --- maven-jar-plugin:2.5:jar (default-jar) @ nablarch-example-batch-ee ---
    [INFO] Building jar: C:\Users\tikK49797\Desktop\Example_fix\nablarch-example-batch-ee\target\nablarch-example-batch-ee-1.0.1.jar
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    (中略)

ビルド後、以下のコマンドを実行し依存するライブラリを取得します。

    $mvn dependency:copy-dependencies -DoutputDirectory=target/dependency

ライブラリの取得に成功すると、以下のようなログがコンソールに出力されます。

    (中略)
    [INFO] Copying weld-api-2.2.SP4.jar to C:\Users\tikK49797\Desktop\Example_fix\nablarch-example-batch-ee\target\dependency\weld-api-2.2.SP4.jar
    [INFO] Copying stax-api-1.0.1.jar to C:\Users\tikK49797\Desktop\Example_fix\nablarch-example-batch-ee\target\dependency\stax-api-1.0.1.jar
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    (中略)

### 5. 動作確認

チェックアウトディレクトリにて以下のコマンドを実行すると、サンプルアプリケーションを動作させることができます。

```
    $java -cp .\target\*;.\target\dependency\* com.nablarch.example.app.main.ExampleMain <batch-job名>
```

<batch-job名>の指定例を示します。

```    
    java -cp .\target\*;.\target\dependency\* com.nablarch.example.app.main.ExampleMain zip-code-truncate-table
```

<batch-job名>を変えることで、CSVからDBおよびDBからCSVへのデータ保存と、DBのTRUNCATE処理を行うことができます。
実行後、次のCSVファイルやテーブルを見て処理結果を確認してください。

動作させることができる処理は、次の通りです。

* JBatchのみ利用（ETLなし）
    * 賞与計算バッチ(DB→DB)
        * EMPLOYEEテーブルから社員情報を取得し、賞与を計算してBONUSテーブルに登録する、Chunkステップのバッチです。
    * 郵便番号テーブルTRUNCATEバッチ
        * ZIP_CODE_DATAテーブル と ZIP_CODE_DATA_WORKテーブル のデータを削除する、Batchletステップのバッチです。
* ETLとJBatchを利用
    * 郵便番号登録ETLバッチ(SQL*LoaderによるCSV→DB)
        * <チェックアウトディレクトリ>/testdata/input/KEN_ALL.CSV を入力元とし、SQL*Loaderにより ZIP_CODE_DATA テーブルにデータを登録します。
    * 郵便番号登録ETLバッチ(SQL*Loaderを使わないCSV→DB)
        * <チェックアウトディレクトリ>/testdata/input/KEN_ALL.CSV を入力元とし、ZIP_CODE_DATA テーブルにデータを登録します。
        * SQL*Loaderは使いません。
    * 郵便番号出力バッチ(DB→CSV)
        * ZIP_CODE_DATAテーブルのデータを <チェックアウトディレクトリ>/testdata/output 以下に出力します。

動作させる処理は、引数の<batch-job名>を変更することで選択できます。

* JBatchのみ利用（ETLなし）
    * <batch-job名>に「bonus-calculate」を指定すると、賞与計算バッチが実行されます。
    * <batch-job名>に「zip-code-truncate-table」を指定すると、郵便番号テーブルTRUNCATEバッチが実行されます。
* ETLとJBatchを利用
    * <batch-job名>に「etl-zip-code-csv-to-db-insert-batchlet」を指定すると、郵便番号登録ETLバッチ(SQL*LoaderによるCSV→DB)が実行されます。
    * <batch-job名>に「etl-zip-code-csv-to-db-chunk」を指定すると、郵便番号登録ETLバッチ(SQL*Loaderを使わないCSV→DB)が実行されます。
    * <batch-job名>に「etl-zip-code-db-to-csv-chunk」を指定すると、郵便番号出力バッチが実行されます。


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

### 6.DBの確認方法

1. http://www.h2database.com/html/cheatSheet.html からH2インストーラをインストールしてください。

2. {インストールフォルダ}/bin/h2.bat を実行してください(コマンドプロンプトが開く)。
  ※h2.bat実行中はExampleアプリケーションからDBへアクセスすることができないため、バッチを実行できません。

3. ブラウザから http://localhost:8082 を開き、以下の情報でH2コンソールにログインしてください。
  ユーザ名：NABLARCH_EXAMPLE
  パスワード：NABLARCH_EXAMPLE

**以下はExample開発者に向けた情報です。**

## IDEへの取り込み
nablarch-example-batch-eeプロジェクトですので、IDEにmavenプロジェクトとしてインポートしてください。
