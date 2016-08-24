データベースの全件削除をしてみよう
===============

## 演習内容
JSR352に準拠したバッチアプリケーションには、BatchletとChunkの2種類があります。本エクササイズでは、nablarch-exampleを題材にして、Batchletの作り方を学習します。

## 演習を開始するための準備

### 1. 事前準備
本エクササイズを開始するまでにデータベースのセットアップ及びエンティティクラスの生成を行っていない場合(以下のコマンドを実行したことがない場合)は、以下のコマンドを実行してください。

    TODO：ディレクトリ名の修正
    $cd nablarch-example-batch-ee
    $mvn -P gsp generate-resources

## 演習内容に関するリファレンスマニュアル

### Nablarchドキュメント

TODO：ドキュメントの名前は「Nablarchドキュメント」でよいのか。

本演習中に実装方法で不明な点が存在した場合は、以下のNablarchドキュメントを参照してください。
TODO：参照先を書く。

## 実装する機能

ジョブ定義ファイルで指定したテーブルをTRUNCATEするBatchletを実装してください。

## 演習

では、Batchletを実装していきましょう。

### Java部分

- TRUNCATE対象テーブルをジョブ定義ファイルのproperty要素で指定できるよう、プロパティにアノテーションを追加してください。

### XML部分

- ジョブレベル
   - ジョブレベルのリスナーを実行するNablarchのリスナーを定義してください。
- 「step1」ステップ
    - ステップレベルのリスナーを実行するNablarchのリスナーを定義してください。
    - Batchletを定義してください。
        - 削除対象テーブル名「ZIP_CODE_DATA」を、Batchletクラスのプロパティにインジェクトするよう定義してください。
- 「step2」ステップ
    - ステップレベルのリスナーを実行するNablarchのリスナーを定義してください。
    - Batchletを定義してください。
        - 削除対象テーブル名「ZIP_CODE_DATA_WORK」を、Batchletクラスのプロパティにインジェクトするよう定義してください。

## 動作確認方法

### テーブルの初期化

TODO：ディレクトリ名の記述
XXX ディレクトリに移動後に、以下を実行してテーブルを初期化します。
```
    $mvn gsp-dba:import-schema
```
### 実行前のテーブルの内容を確認

TODO：ディレクトリ名の修正

1. コマンドプロンプトを起動します。
1. カレントディレクトリをbatch-exercise-01/h2/bin/に移動します。
1. h2.batを起動します。
1. ブラウザが起動し、H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
1. 左側のペインに表示されているZIP_CODE_DATAをクリックします。
1. SELECT文が画面に表示されますので、そのままRunボタンをクリックします。
1. データが表示されることを確認します。
1. Clearボタンをクリックします。
1. 同様に、ZIP_CODE_DATA_WORKにデータが入っていることを確認します。
1. 手順1を実行した際に開いたコマンドプロンプトを終了して、H2のConsoleを終了します。

### バッチ実行

TODO：ディレクトリ名の修正
XXX ディレクトリに移動後に、以下を実行してjarの作成及び関係するjarの取得を行います。

    $mvn clean install
    $mvn dependency:copy-dependencies -DoutputDirectory=target/dependency

ここまでの操作で、targetにjarが作成されます。
また、XXX/target/dependencyディレクトリに、関係するjarが格納されます。

XXX ディレクトリにて以下のコマンドを実行すると、アプリケーションを動作させることができます。

    java -cp .\target\*;.\target\dependency\* com.nablarch.example.app.main.ExampleMain zip-code-truncate-table

### バッチ実行結果の確認

TODO：ディレクトリ名の修正

1. コマンドプロンプトを起動します。
1. カレントディレクトリをXXX/h2/bin/に移動します。
1. h2.batを起動します。
1. ブラウザが起動し、H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
1. SQLを入力するフィールドに以下のように入力します。
```
    SELECT count(*) FROM ZIP_CODE_DATA
```
1. SQLの実行結果が0であることを確認します。
2. 同様にして、ZIP_CODE_DATA_WORKの件数が0件であることを確認します。
1. 手順1を実行した際に開いたコマンドプロンプトを終了して、H2のConsoleを終了します。

### H2への接続情報

TODO：JDBC URLの修正

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:XXX/nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |

## 解答例について

TODO：解答例へのリンクの記載
