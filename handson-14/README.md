Batchletを作ってみよう
===============

## 演習内容
JSR352に準拠したバッチアプリケーションには、BatchletとChunkの2種類があります。本ハンズオンでは、バッチExampleアプリケーションを題材にして、Batchletの作り方を学習します。

## 作成するバッチについて

ZIP_CODE_DATAテーブル と ZIP_CODE_DATA_WORKテーブル のデータを削除するバッチです。

## 演習を開始するための準備

### 事前準備
本ハンズオンを開始する前にデータベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書

- [4.1.1. アーキテクチャ概要](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/architecture.html#jsr352-architecture)
	- [バッチアプリケーションの処理の流れ](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/architecture.html#id3)
		- [Batchlet](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/architecture.html#batchlet)
    - [リスナーの指定方法](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/architecture.html#jsr352-listener-definition)
      - ジョブ定義ファイルへの設定
- [4.1.3. Getting Started](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/getting_started/getting_started.html#getting-started)
	- [対象テーブルのデータを削除するバッチの作成(Batchletステップ)](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/getting_started/batchlet/index.html#batchlet)

## 実装する機能

- ジョブ定義ファイルで指定したテーブルをTRUNCATEするBatchletを実装してください。
- ジョブ定義ファイルを作成してください。

## 演習

では、Batchletを実装していきましょう。

### Java部分([TruncateTableBatchlet.java](./src/main/java/com/nablarch/example/app/batch/ee/batchlet/TruncateTableBatchlet.java))

- TRUNCATE対象テーブルをジョブ定義ファイルのproperty要素で指定できるよう、プロパティにアノテーションを追加してください。

### XML部分([zip-code-truncate-table.xml](./src/main/resources/META-INF/batch-jobs/zip-code-truncate-table.xml))

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

1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
2. ブラウザから http://localhost:8082 を開きます。H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
3. 中央のSQLを入力するフィールドに以下のように入力し、Runボタンをクリックします。
```
    insert into ZIP_CODE_DATA
        select * from
        csvread('..\..\handson-14\src\test\resources\data\zip_code_data.csv',
                null,
                'Shift-JIS');
    insert into ZIP_CODE_DATA_WORK
        select * from
        csvread('..\..\handson-14\src\test\resources\data\zip_code_data_work.csv',
                null,
                'Shift-JIS');
```

### 実行前のテーブルの内容を確認

引き続き、ブラウザ上で作業します。

1. Clearボタンをクリックします。
1. 左側のペインに表示されているZIP_CODE_DATAをクリックします。
1. SELECT文が画面に表示されますので、そのままRunボタンをクリックします。
1. データが表示されることを確認します。
1. Clearボタンをクリックします。
1. 同様に、ZIP_CODE_DATA_WORKにデータが入っていることを確認します。
1. 最初に開いたコマンドプロンプトを終了して、h2.batを終了します。  
**注意**
h2.bat実行中はBatchletからDBへアクセスすることができないため、**バッチを実行できません。**

### バッチ実行

チェックアウトディレクトリに移動後、以下を実行してjarの作成を行います。

    $cd handson-14
    $mvn clean package

ここまでの操作で、targetディレクトリにjarが作成されます。

<チェックアウトディレクトリ>/handson-14 ディレクトリにて以下のコマンドを実行すると、アプリケーションを動作させることができます。

    $mvn exec:java -Dexec.mainClass=nablarch.fw.batch.ee.Main -Dexec.args=zip-code-truncate-table



### バッチ実行結果の確認

1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. (ブラウザが自動的に起動しない場合)  
ブラウザから http://localhost:8082 を開きます。  
アクセスできない場合は、 http://<自分のPCのIPアドレス>:8082 を開きます。
1. H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
1. 中央のSQLを入力するフィールドに以下のように入力し、Runボタンをクリックします。
   ```
       SELECT count(*) FROM ZIP_CODE_DATA
   ```
1. SQLの実行結果が0であることを確認します。
1. 同様にして、ZIP_CODE_DATA_WORKの件数が0件であることを確認します。
1. 手順1を実行した際に開いたコマンドプロンプトを終了して、H2のConsoleを終了します。

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |

## 解答例について

解答例は、[nablarch-handson-app-batch-ee](../nablarch-handson-app-batch-ee/README.md)を参照してください。
