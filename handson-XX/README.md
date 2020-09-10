nablarchバッチを作成してみよう
===========================

## 演習内容

本ハンズオンでは、Nablarchバッチの作成方法を学習します。

## 作成するバッチについて

指定されたフォルダ内の住所情報CSVファイルを、データバインドを使用して読み込み、DBに登録します。

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

## 演習を開始するための準備

### 事前準備
本ハンズオンを開始する前にデータベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity  
    $mvn clean install  

## 演習内容に関するリファレンスマニュアル

本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書

* 4.2.1. アーキテクチャ概要
    * Nablarchバッチアプリケーションで使用するデータリーダ
    * Nablarchバッチアプリケーションで使用するアクション

* 7.4.1. データバインド
    * CSVファイルのフォーマットを指定する
    * ファイルのデータの論理行番号を取得する

* 7.10.1. Bean Validation

* 4.2.3. Getting Started
    * ファイルをDBに登録するバッチの作成

## 実装する機能

* データバインド を用いてCSV(住所ファイル)をバインドするフォームを実装してください。
* ファイルを読み込んで一行ずつ業務アクションメソッドへ引き渡す、 DataReader の実装クラスを実装してください。
* BatchAction を継承し、業務アクションクラスを実装してください。

## 演習

### CSVをバインドするForm([ZipCodeForm.java](./src/main/java/com/nablarch/example/app/batch/form/ZipCodeForm.java))

* アノテーションを用いて、CSVファイルのフォーマットを設定してください。
    * type属性はCUSTOMです。
	* プロパティ名と順番は以下の通りです。
 
          "localGovernmentCode",
          "zipCode5digit",
          "zipCode7digit",
          "prefectureKana",
          "cityKana",
          "addressKana",
          "prefectureKanji",
          "cityKanji",
          "addressKanji",
          "multipleZipCodes",
          "numberedEveryKoaza",
          "addressWithChome",
          "multipleAddress",
          "updateData",
          "updateDataReason"
   
	* 入力となるCSVのフォーマットは以下の通りです。

| 設定項目 | 設定値 |
|:---|:---|
| 列区切り | カンマ(,) |
| 行区切り | 改行(\r\n) |
| フィールド囲み文字 | ダブルクォート(”) |
| 空行を無視 | true |
| ヘッダ行あり | false |
| 文字コード | UTF-8 |
| クォートモード | NORMAL |


* Bean Validation を実施するために、バリデーション用のアノテーションを付与してください。
* ドメインの設定。ドメインは、[ExampleDomainType](./src/main/java/com/nablarch/example/app/entity/core/validation/validator/ExampleDomainType.java)で定義されているものを使用してください。どのプロパティにどのドメインを設定するかは、下表を参照してください。  

| 項目 | 必須 | ドメイン |
|:----|:-----|:----|
| 全国地方公共団体コード | ○ | localGovernmentCode |
| 郵便番号（5桁） | ○ | oldZipCode |
| 郵便番号（7桁） | ○ | zipCode |
| 都道府県名カナ | ○ | prefectureKana |
| 市区町村名カナ | ○ | cityKana |
| 町域名カナ | ○ | address |
| 都道府県名漢字 | ○ | prefecture |
| 市区町村名漢字 | ○ | city |
| 町域名漢字 | ○ | address |
| 一町域が二以上の郵便番号 | ○ | flag |
| 小字毎に番地が起番されている町域 | ○ | flag |
| 丁目を有する町域 | ○ | flag |
| 一つの郵便番号で二以上の町域 | ○ | flag |
| 更新 | ○ | code |
| 変更理由 | ○ | code |
    
* 行数プロパティのゲッタに @LineNumber を付与する。


### データリーダ([ZipCodeFileReader.java](./src/main/java/com/nablarch/example/app/batch/reader/ZipCodeFileReader.java))

* 読み込むファイルの名称は「importZipCode.csv」としてください。

* read メソッド
    * 一行分のデータを返却する処理を実装してください。

* hasNext メソッド
    * 次行の有無を判定する処理を実装してください。。
    
* read メソッド
    * ファイルの読み込み終了後のストリームのclose処理を実装してください。


### 業務アクションクラス([ImportZipCodeFileAction](./src/main/java/com/nablarch/example/app/batch/action/ImportZipCodeFileAction.java))

* handle メソッド
    * データリーダから渡された一行分のデータをUniversalDao#insert を使用して住所エンティティをデータベースに登録してください。
　　
* createReader メソッド
    * 使用するデータリーダクラスのインスタンスを返却してください。

## 動作確認方法

### 実行前のテーブルの内容を確認
 
1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. ブラウザから http://localhost:8082 を開きます。H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
1. 左側のペインに表示されているZIP_CODE_DATAをクリックします。
1. SELECT文が画面に表示されますので、そのままRunボタンをクリックします。
1. データが何も入っていないことを確認します。何か入っていた場合は、Clearボタンをクリックしてから、中央のSQLを入力するフィールドに以下のように入力し、Runボタンをクリックします。
    ```
    DELETE FROM ZIP_CODE_DATA;
    ```
1. 最初に開いたコマンドプロンプトを終了して、h2.batを終了します。


   **注意**
   h2.bat実行中はバッチプログラムからDBへアクセスすることができないため、**バッチを実行できません。**

### バッチ実行

チェックアウトディレクトリに移動後、以下を実行してjarの作成を行います。

    $cd handson-XX
    $mvn clean package
 
ここまでの操作で、targetディレクトリにjarが作成されます。  
<チェックアウトディレクトリ>/handson-XX ディレクトリにて以下のコマンドを実行すると、アプリケーションを動作させることができます。

    $mvn exec:java -Dexec.mainClass=nablarch.fw.launcher.Main -Dexec.args="'-requestPath' 'ImportZipCodeFileAction/ImportZipCodeFile' '-diConfig' 'classpath:import-zip-code-file.xml' '-userId' '105'"
   
実行すると、以下のようなログがコンソールに出力されますが、問題はありません。
 
### バッチ実行結果の確認
 
1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. ブラウザから http://localhost:8082 を開きます。H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
1. 左側のペインに表示されているZIP_CODE_DATAをクリックします。
1. SELECT文が画面に表示されますので、そのままRunボタンをクリックします。
1. 住所情報が表示されることを確認します。
1. 手順1を実行した際に開いたコマンドプロンプトを終了して、H2のConsoleを終了します。

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |
 
## 解答例について
 
解答例は、[nablarch-handson-app-batch](../nablarch-handson-app-batch/README.md)を参照してください。
