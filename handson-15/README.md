Chunkタイプのバッチを作ってみよう
===============

## 演習内容
JSR352に準拠したバッチアプリケーションには、BatchletとChunkの2種類があります。本ハンズオンでは、バッチExampleアプリケーションを題材にして、Chunkタイプのバッチの作り方を学習します。

## 作成するバッチについて

社員テーブルから社員情報を抜き出し、各社員の設定に応じて固定賞与または月給ベースの賞与を計算し、賞与テーブルに書き込むバッチです。社員テーブルから社員情報を抜き出す部分、賞与を計算する部分、計算した賞与を賞与テーブルに書き込む部分の3つからできています。

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
		- [Chunk](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/architecture.html#chunk)
    - [リスナーの指定方法](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/architecture.html#jsr352-listener-definition)
      - ジョブ定義ファイルへの設定
- [4.1.3. Getting Started](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/getting_started/getting_started.html#getting-started)
	- [データを導出するバッチの作成(Chunkステップ)](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/batch/jsr352/getting_started/chunk/index.html#chunk)
- [7.3.2. ユニバーサルDAO](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/database/universal_dao.html#dao)
  - [任意のSQL(SQLファイル)で検索する](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/database/universal_dao.html#sql-sql)
  - [検索結果を遅延ロードする](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/database/universal_dao.html#universal-dao-lazy-load)
  - [バッチ実行（一括登録、更新、削除）を行う](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/database/universal_dao.html#universal-dao-batch-execute)

### APIドキュメント(アプリケーションプログラマ向け)
- [UniversalDao](https://nablarch.github.io/docs/5u21/javadoc/nablarch/common/dao/UniversalDao.html)

## 実装する機能
- 社員情報を抜き出すアイテムリーダを実装してください。
- 賞与を計算するアイテムプロセッサを実装してください。
- 計算した賞与を賞与テーブルに書き込むアイテムライタを実装してください。
- ジョブ定義ファイルを作成してください。

## 演習

では、Chunkバッチを実装していきましょう。

### Java部分

#### アイテムリーダ([EmployeeSearchReader.java](./src/main/java/com/nablarch/example/app/batch/ee/chunk/EmployeeSearchReader.java))

##### openメソッド

- DBから社員情報を検索し、イテレータを取得してください。

##### readItemメソッド

- イテレータを使用して、検索した社員情報を1レコードずつ返却してください。返却するレコードがない場合は、「null」を返却してください。

##### closeメソッド

- 検索した社員情報をクローズしてください。

#### アイテムプロセッサ([BonusCalculateProcessor.java](./src/main/java/com/nablarch/example/app/batch/ee/chunk/BonusCalculateProcessor.java))

##### processItemメソッド

- 賞与支給額を計算し、社員IDと共にBonusエンティティに設定、返却してください。

#### アイテムライタ([BonusWriter.java](./src/main/java/com/nablarch/example/app/batch/ee/chunk/BonusWriter.java))

##### writeItemsメソッド
- 引数のリストをBonusテーブルに登録してください。なお、このリストはBonusCalculateProcessor#processItemで返却したインスタンスのリストです。

### XML部分([bonus-calculate.xml](./src/main/resources/META-INF/batch-jobs/bonus-calculate.xml))

- ジョブレベル
    - ジョブレベルのリスナーを実行するNablarchのリスナーを定義してください。
- 「step1」ステップ
    - ステップレベルのリスナーを実行するNablarchのリスナーを定義してください。
    - ItemWriterレベルのリスナーを実行するNablarchのリスナーを定義してください。
    - Chunkを定義してください。アイテムライタのwriteItems一回当たりで処理する件数は1000件としてください。
        - reader、processor、writerは以下の表のように指定してください。

|要素名|指定するクラス|
|:----|:---------|
|reader|EmployeeSearchReader|
|processor|BonusCalculateProcessor|
|writer|BonusWriter|

## 動作確認方法

### 実行前のテーブルの内容を確認

1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. (ブラウザが自動的に起動しない場合)  
ブラウザから http://localhost:8082 を開きます。  
アクセスできない場合は、 http://<自分のPCのIPアドレス>:8082 を開きます。
2. H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
3. 左側のペインに表示されているBONUSをクリックします。
4. SELECT文が画面に表示されますので、そのままRunボタンをクリックします。
5. データが何も入っていないことを確認します。何か入っていた場合は、Clearボタンをクリックしてから、中央のSQLを入力するフィールドに以下のように入力し、Runボタンをクリックします。
    ```
        DELETE FROM BONUS;
    ```
1. 最初に開いたコマンドプロンプトを終了して、h2.batを終了します。  
   **注意** h2.bat実行中はバッチプログラムからDBへアクセスすることができないため、**バッチを実行できません。**

### バッチ実行

チェックアウトディレクトリに移動後、以下を実行してjarの作成を行います。

    $cd handson-15
    $mvn clean package

ここまでの操作で、targetディレクトリにjarが作成されます。

<チェックアウトディレクトリ>/handson-15 ディレクトリにて以下のコマンドを実行すると、アプリケーションを動作させることができます。

    $mvn exec:java -Dexec.mainClass=nablarch.fw.batch.ee.Main -Dexec.args=bonus-calculate

### バッチ実行結果の確認

1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. (ブラウザが自動的に起動しない場合)  
ブラウザから http://localhost:8082 を開きます。  
アクセスできない場合は、 http://<自分のPCのIPアドレス>:8082 を開きます。
2. H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
4. 左側のペインに表示されているBONUSをクリックします。
5. SELECT文が画面に表示されますので、そのままRunボタンをクリックします。
6. 賞与情報が表示されることを確認します。
7. 手順1を実行した際に開いたコマンドプロンプトを終了して、H2のConsoleを終了します。

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |

## 解答例について

解答例は、[nablarch-handson-app-batch-ee](../nablarch-handson-app-batch-ee/README.md)を参照してください。
