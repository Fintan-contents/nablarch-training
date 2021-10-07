nablarch-handson-app-batch-ee
===============
Nablarchアプリケーションフレームワークを利用して作成したJSR352に準拠したバッチExampleアプリケーションです。

## アプリケーションのビルドと実行

### 事前準備
本Exampleアプリケーションをビルドする前に、データベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

### アプリケーションのビルド、依存するライブラリの取得

次に、アプリケーションをビルドします。チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    cd nablarch-handson-app-batch-ee
    $mvn clean package

ここまでの操作で、targetディレクトリにjarが作成されます。

### 実行

targetディレクトリにjarの作成が終わったら、以下のコマンドを実行するとサンプルアプリケーションを動作させることができます。

    $mvn exec:java -Dexec.mainClass=nablarch.fw.batch.ee.Main -Dexec.args=<batch-job名>

＜batch-job名＞の指定例を示します。

    mvn exec:java -Dexec.mainClass=nablarch.fw.batch.ee.Main -Dexec.args=zip-code-truncate-table

＜batch-job名＞を変えることで、用意しているいくつかの処理を実行することができます。
用意している処理と指定する＜batch-job名＞は次の通りです。実行後、以下の説明に出てくるCSVファイルやテーブルを見て、処理結果を確認してください。

* (ハンズオン14)郵便番号テーブルTRUNCATEバッチ
    * ＜batch-job名＞：zip-code-truncate-table
    * ZIP_CODE_DATAテーブル と ZIP_CODE_DATA_WORKテーブルのデータを削除する、Batchletステップのバッチです。
* (ハンズオン15)賞与計算バッチ(DB→DB)
    * ＜batch-job名＞：bonus-calculate
    * EMPLOYEEテーブルから社員情報を取得し、賞与を計算してBONUSテーブルに登録する、Chunkステップのバッチです。
    * 本バッチ処理自身の仕様上、2回目以降の実行では主キー制約違反エラーが発生します。
        * エラー無く実行させるためには、[DBの確認方法](#DBの確認方法)を参考に、BONUSテーブルに対するDELETE文を実行してください。

### DBの確認方法

1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. (ブラウザが自動的に起動しない場合)  
ブラウザから http://localhost:8082 を開きます。  
アクセスできない場合は、 http://<自分のPCのIPアドレス>:8082 を開きます。
2. H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
3. 中央のSQLを入力するフィールドに確認のためのSQLを入力し、Runボタンをクリックします。

**注意**
h2.bat実行中はバッチアプリケーションからDBへアクセスすることができないため、**バッチを実行できません。**

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |
