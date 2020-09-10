nablarch-handson-app-batch 
===========================

Nablarchアプリケーションフレームワークを利用して作成したNablarchバッチのExampleです。

## アプリケーションのビルドと実行
 
### 事前準備
本Exampleをビルドする前に、データベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。
 
    $cd entity
    $mvn clean install
 
### アプリケーションのビルド
 
次に、アプリケーションをビルドします。チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    cd nablarch-handson-app-batch
    $mvn clean package 
   
ここまでの操作で、targetディレクトリにjarが作成されます。

### 実行

targetディレクトリにjarの作成が終わったら、以下のコマンドを実行するとサンプルアプリケーションを動作させることができます。

javaコマンドで Mainクラス を指定してアプリケーションを起動します。

    $mvn exec:java -Dexec.mainClass=nablarch.fw.launcher.Main -Dexec.args="'-requestPath' '(業務アクションクラス名) + / + (リクエストID)' '-diConfig' '(設定ファイルのパス)' '-userId' '(ユーザID)'"
   

* データバインドを使用した住所登録バッチ(CSV→DB)
    * (ハンズオンXX)指定されたフォルダ内(/work/input)の住所情報CSVファイル(importZipCode.csv)を、データバインドを使用して読み込み、DB(ZIP_CODE_DATAテーブル)に登録します。


動作させる処理は、引数のを変更することで選択できます。

* データバインドを使用した住所登録バッチ(CSV→DB)  
    * (業務アクションクラス名) + / + (リクエストID) に「ImportZipCodeFileAction/ImportZipCodeFile」を指定する。
    * (設定ファイルのパス)に「classpath:import-zip-code-file.xml」を指定する。
    * (ユーザID)に「105」を指定する。
    
    
    $mvn exec:java -Dexec.mainClass=nablarch.fw.launcher.Main -Dexec.args="'-requestPath' 'ImportZipCodeFileAction/ImportZipCodeFile' '-diConfig' 'classpath:import-zip-code-file.xml' '-userId' '105'"


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


