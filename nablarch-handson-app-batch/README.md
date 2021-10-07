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

    $cd nablarch-handson-app-batch
    $mvn clean package 
   
ここまでの操作で、targetディレクトリにjarが作成されます。

### 実行

targetディレクトリにjarの作成が終わったら、以下のコマンドを実行するとデータバインドを使用した住所登録バッチアプリケーションを動作させることができます。

    $mvn exec:java -Dexec.mainClass=nablarch.fw.launcher.Main -Dexec.args="'-requestPath' 'ImportZipCodeFileAction/ImportZipCodeFile' '-diConfig' 'classpath:import-zip-code-file.xml' '-userId' '105'"


### DBの確認方法
 
1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. (ブラウザが自動的に起動しない場合)  
ブラウザから http://localhost:8082 を開きます。  
アクセスできない場合は、 http://<自分のPCのIPアドレス>:8082 を開きます。
2. H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
3. 中央のSQLを入力するフィールドに確認のためのSQLを入力し、Runボタンをクリックします。  
住所登録バッチアプリケーションはZIP_CODE_DATAテーブルを操作しますので、次のSQLで確認できます。  
    ```
        SELECT * FROM ZIP_CODE_DATA;
    ```

**注意**
h2.bat実行中はバッチアプリケーションからDBへアクセスすることができないため、**バッチを実行できません。**

### H2への接続情報


   | 項目      | 値                         |
   |:----------|:---------------------------|
   | JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
   | User Name | NABLARCH_EXAMPLE           |
   | Password  | NABLARCH_EXAMPLE           |


## 参考

- [ObjectMapper](https://nablarch.github.io/docs/5u19/javadoc/nablarch/common/databind/ObjectMapper.html) は、hasNext メソッドを持たないため、本解答例では、イテレータを用意しています。イテレータの実装に関しては、[ObjectMapperIterator.java](./src/main/java/com/nablarch/example/app/batch/reader/iterator/ObjectMapperIterator.java)の実装を参照してください。イテレータを作成することでバッチごとにデータ読み込み処理を実装しなくてもよい利点があります。

- Bean Validationを実行するロジックにバッチごとの差はないため、本解答例ではインターセプタを用意してバリデーション処理を共通化しています。インターセプタの実装に関しては、[ValidateData.java](./src/main/java/com/nablarch/example/app/batch/interceptor/ValidateData.java) の実装を参照してください。