Nablarchバッチを作成してみよう
===========================

## 演習内容

本ハンズオンでは、Nablarchバッチアプリケーションを利用してCSVファイルの内容をDBに登録する方法を学習します。

## 作成するバッチについて

Nablarchバッチアプリケーションを利用して、CSV形式の郵便番号データをデータバインドを使用して読み込み、DBに登録します。

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
- [4.2.1. アーキテクチャ概要](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/batch/nablarch_batch/architecture.html#id1)
    - [Nablarchバッチアプリケーションで使用するデータリーダ](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/batch/nablarch_batch/architecture.html#nablarch-batch-data-reader)
    - [Nablarchバッチアプリケーションで使用するアクション](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/batch/nablarch_batch/architecture.html#nablarch-batch-action)
- [7.4.1. データバインド](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/data_io/data_bind.html#data-bind)
    - [CSVファイルのフォーマットを指定する](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/data_io/data_bind.html#csv)
    - [ファイルのデータの論理行番号を取得する](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/data_io/data_bind.html#data-bind-line-number)
- [7.10.1. Bean Validation](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/validation/bean_validation.html#bean-validation)
	- [ドメインバリデーションを使う](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/validation/bean_validation.html#bean-validation-domain-validation)
		- 各Beanでドメインバリデーションを使う
- [4.2.3. Getting Started](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/batch/nablarch_batch/getting_started/getting_started.html#getting-started)
    - [ファイルをDBに登録するバッチの作成](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/batch/nablarch_batch/getting_started/nablarch_batch/index.html#db)

### APIドキュメント(アプリケーションプログラマ向け)
- [DataReader](https://nablarch.github.io/docs/5u19/javadoc/nablarch/fw/DataReader.html)
- [BatchAction](https://nablarch.github.io/docs/5u19/javadoc/nablarch/fw/action/BatchAction.html)
- [UniversalDao](https://nablarch.github.io/docs/5u19/javadoc/nablarch/common/dao/UniversalDao.html)
- [注釈型 Csv](https://nablarch.github.io/docs/5u19/javadoc/nablarch/common/databind/csv/Csv.html)
- [注釈型 CsvFormat](https://nablarch.github.io/docs/5u19/javadoc/nablarch/common/databind/csv/CsvFormat.html)

## 実装する機能

* データバインド を用いてCSV(住所ファイル)をバインドするフォームを実装してください。
* ファイルを読み込んで一行ずつ業務アクションメソッドへ引き渡す、 DataReader の実装クラスを実装してください。
* BatchAction を継承し、業務アクションクラスを実装してください。

## 演習

### CSVをバインドするForm([ZipCodeForm.java](./src/main/java/com/nablarch/example/app/batch/form/ZipCodeForm.java))

* @CsvFormatと@Csvアノテーションを用いて、CSVファイルのフォーマットを設定してください。
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

   * type属性はCUSTOMです。
   * 入力となるCSVのプロパティ名と順番は以下の通りです。

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
   
* Bean Validation を実施するために、バリデーション用のアノテーションを付与してください。設定するアノテーションは下表を参照してください。  

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
    
* 行数プロパティのゲッタに @LineNumber を付与してください。


### データリーダ([ZipCodeFileReader.java](./src/main/java/com/nablarch/example/app/batch/reader/ZipCodeFileReader.java))

* 読み込むファイルの名称は「importZipCode.csv」としてください。

* read メソッド
    * 一行分のデータを返却する処理を実装してください。

* hasNext メソッド
    * 次行の有無を判定する処理を実装してください。
    
* close メソッド
    * ファイルの読み込み終了後のストリームのclose処理を実装してください。


### 業務アクションクラス([ImportZipCodeFileAction](./src/main/java/com/nablarch/example/app/batch/action/ImportZipCodeFileAction.java))

* handle メソッド
    * データリーダから渡された一行分のデータをUniversalDao#insert を使用して住所エンティティをデータベースに登録してください。
　　
* createReader メソッド
    * 上記で作成したデータリーダクラスのインスタンスを返却してください。

## 動作確認方法

### 実行前のテーブルの内容を確認
 
1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. (ブラウザが自動的に起動しない場合)  
ブラウザから http://localhost:8082 を開きます。  
アクセスできない場合は、 http://<自分のPCのIPアドレス>:8082 を開きます。
2. H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
3. 左側のペインに表示されているZIP_CODE_DATAをクリックします。
4. SELECT文が画面に表示されますので、そのままRunボタンをクリックします。
5. データが何も入っていないことを確認します。何か入っていた場合は、Clearボタンをクリックしてから、中央のSQLを入力するフィールドに以下のように入力し、Runボタンをクリックします。
    ```
    DELETE FROM ZIP_CODE_DATA;
    ```
6. 最初に開いたコマンドプロンプトを終了して、h2.batを終了します。


**注意**
h2.bat実行中はバッチプログラムからDBへアクセスすることができないため、**バッチを実行できません。**

### バッチ実行

チェックアウトディレクトリに移動後、以下を実行してjarの作成を行います。

    $cd handson-12
    $mvn clean package
 
ここまでの操作で、targetディレクトリにjarが作成されます。  
<チェックアウトディレクトリ>/handson-12 ディレクトリにて以下のコマンドを実行すると、アプリケーションを動作させることができます。

    $mvn exec:java -Dexec.mainClass=nablarch.fw.launcher.Main -Dexec.args="'-requestPath' 'ImportZipCodeFileAction/ImportZipCodeFile' '-diConfig' 'classpath:import-zip-code-file.xml' '-userId' '105'"

下記のようなログがコンソールに出力されれば、バッチは正常終了してます。

    Thread Status: normal end.
    Thread Result:[200 Success] The request has succeeded.
 
### バッチ実行結果の確認
 
1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. (ブラウザが自動的に起動しない場合)  
ブラウザから http://localhost:8082 を開きます。  
アクセスできない場合は、 http://<自分のPCのIPアドレス>:8082 を開きます。
2. H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
4. 左側のペインに表示されているZIP_CODE_DATAをクリックします。
5. SELECT文が画面に表示されますので、そのままRunボタンをクリックします。
6. ZIP_CODE_DATAに登録されている住所情報の件数が**62**件であることを確認します。
7. 手順1を実行した際に開いたコマンドプロンプトを終了して、H2のConsoleを終了します。

**注意**
h2.bat実行中はバッチプログラムからDBへアクセスすることができないため、**バッチを実行できません。**

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |
 
## 解答例について
 
解答例は、[nablarch-handson-app-batch](../nablarch-handson-app-batch/README.md)を参照してください。


## 参考

- [ObjectMapper](https://nablarch.github.io/docs/5u19/javadoc/nablarch/common/databind/ObjectMapper.html) は、hasNext メソッドを持たないため、本ハンズオンでは、イテレータを用意しています。イテレータの実装に関しては、[ObjectMapperIterator.java](./src/main/java/com/nablarch/example/app/batch/reader/iterator/ObjectMapperIterator.java)の実装を参照してください。イテレータを作成することでバッチごとにデータ読み込み処理を実装しなくてもよい利点があります。

- Bean Validationを実行するロジックにバッチごとの差はないため、本ハンズオンではインターセプタを用意してバリデーション処理を共通化しています。インターセプタの実装に関しては、[ValidateData.java](./src/main/java/com/nablarch/example/app/batch/interceptor/ValidateData.java) の実装を参照してください。
