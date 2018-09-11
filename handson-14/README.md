ETLを使って、CSVファイルの内容をDBに登録してみよう
===============

## 演習内容
バッチExampleアプリケーションを題材に、NablarchのETL機能とJSR352に準拠したバッチアプリケーションを利用してCSVファイルの内容をDBに登録する方法を学習します。

## 作成するバッチについて

ETL機能とJSR352に準拠したバッチアプリケーションを組み合わせて、CSV形式の郵便番号データをDBに登録します。

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

本ハンズオンを開始する前にデータベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書



#### Nablarchアプリケーションフレームワークの解説書
- 7.4.1. データバインド
	- 使用方法
		- CSVファイルのフォーマットを指定する
			- Java Beansクラスにバインドする場合

#### Nablarch拡張コンポーネントの解説書

- 3.ETL
	- 3.2.ETLの各フェーズの使用
		- 3.2.1.Extractフェーズ
			- JSR352 のChunkステップを使用したデータのロード
        - 3.2.2. Transformフェーズ
        	- データのバリデーション
        - 3.2.3. Loadフェーズ
    		- データベース出力
	- 3.3. 使用方法
		- 3.3.2. ETL用設定ファイルを作成する

#### ETLデザイナーの解説書
ETLデザイナーの操作方法はGetting Startedを参照してください。
- [Getting Started](https://alfort.adc-tis.com/gitbucket/shaft/etl-designer/blob/master/doc/getting-started.md)
- [利用方法](https://alfort.adc-tis.com/gitbucket/shaft/etl-designer/blob/master/doc/operation-guide.md)

## 実装する機能

- 郵便番号データの1レコードに対応するDTOを作成してください。
- ETLデザイナー上でJOB定義ファイル及びETL用JOB設定ファイルを作成してください。

## 演習

では、ETLを利用したCSVデータのDB登録を実装していきましょう。

### Java部分

#### DTO([ZipCodeDto.java](./src/main/java/com/nablarch/example/app/batch/ee/dto/ZipCodeDto.java))

- このDTOは、ワークテーブル(ZIP_CODE_DATA_WORK)に対するエンティティクラスでもあります。
- アノテーションを用いて、CSVファイルのフォーマットを設定してください。
    - type属性はCUSTOMです。
	- プロパティ名と順番は以下の通りです。

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

    - フォーマットは以下の通りです。

|設定項目|設定値|
|:-----|:------|
|空フィールドをnullに置き換えるかどうか|false|
|列区切り|カンマ(,)|
|行区切り|改行(\r\n)|
|フィールド囲み文字|ダブルクォート(")|
|空行を無視|false|
|ヘッダ行あり|false|
|文字コード|MS932|
|クォートモード|NORMAL|

### JOB定義ファイル及びETL用JOB設定ファイル

ETLデザイナーで作成します。

- テンプレートの読み込み
    - ETLデザイナーで使用するテンプレートを選択します。「file2db_chunk_chunk」を開いてください。

- JOBの編集
  
図形の削除はキャンバス上で行ってください。  
値の設定はプロパティパネルで行ってください。設定する値は下表を参考にしてください。

    - Jobのname属性
        - 「etl-zip-code-csv-to-db-chunk」を設定してください。
    - 「truncate」ステップ
        - truncate entity: テーブルクリーニングの対象テーブル(今回はワークテーブルのみ)のエンティティクラスを設定してください。
    - 「extract」ステップ
        - Processorを使用しないため図形を削除してください。
        - fileName: 入力ファイル名を設定してください。
        - bean: ワークテーブルのエンティティクラスを完全修飾名で設定してください。
    - 「validation」ステップ
        - bean: バリデーション対象のワークテーブルのエンティティクラスの完全修飾名を設定してください。
        - errorEntity: エラーデータを格納するワークテーブルのエンティティクラスを完全修飾名で記述してください。
        - mode: ABORTを選択してください。
    - 「load」ステップ
        - Processorを使用しないため図形を削除してください。
        - bean: 本番テーブルのエンティティクラスを完全修飾名で記述してください。
        - SQLId: ワークテーブルから本番テーブルに登録する際に使用するSQLのSQL_IDを記述してください。

|項目|値|
|:--|:--|
|入力ファイル|KEN_ALL.CSV|
|エラーデータを格納するワークテーブルのエンティティクラス|com.nablarch.example.app.batch.ee.dto.ZipCodeErrorEntity|
|本番テーブルのエンティティクラス|com.nablarch.example.app.entity.ZipCodeData|
|ワークテーブルのエンティティクラス|上記「Java部分」参照|
|SQL_ID|SELECT_ZIPCODE_FROM_WORK|

- JOB定義ファイル(XML)とETL用JOB設定ファイル(JSON)の出力
    - JOB定義のバリデーションを行ってください。
    - 設定ファイルを出力してください。
    - 出力した設定ファイルを、下記のディレクトリ配下に移動させてください。
        - XMLファイル： <チェックアウトディレクトリ>/handson-14/src/main/resources/META-INF/batch-jobs/
        - JSONファイル: <チェックアウトディレクトリ>/handson-14/src/main/resources/META-INF/etl-config/


## 動作確認方法

### 実行前のテーブルの内容を確認

1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
2. ブラウザから http://localhost:8082 を開きます。H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
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

    $cd handson-14
    $mvn clean package

ここまでの操作で、targetディレクトリにjarが作成されます。

<チェックアウトディレクトリ>/handson-14 ディレクトリにて以下のコマンドを実行すると、アプリケーションを動作させることができます。

    $mvn exec:java -Dexec.mainClass=nablarch.fw.batch.ee.Main -Dexec.args=etl-zip-code-csv-to-db-chunk

実行すると、以下のようなログがコンソールに出力されますが、問題はありません。

    (中略)
    WARN  o.j.w.Interceptor WELD-001700: Interceptor annotation class javax.ejb.PostActivate not found, interception based on it is not enabled
    WARN  o.j.w.Interceptor WELD-001700: Interceptor annotation class javax.ejb.PrePassivate not found, interception based on it is not enabled
    (中略)

### バッチ実行結果の確認

1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
2. ブラウザから http://localhost:8082 を開きます。H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
1. 左側のペインに表示されているZIP_CODE_DATAをクリックします。
1. SELECT文が画面に表示されますので、そのままRunボタンをクリックします。
1. 郵便番号データ(住所データ)が表示されることを確認します。
1. 手順1を実行した際に開いたコマンドプロンプトを終了して、H2のConsoleを終了します。

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |

## 解答例について

解答例は、[nablarch-handson-app-batdh-ee](../nablarch-handson-app-batch-ee/README.md)を参照してください。
