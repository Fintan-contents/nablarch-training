nablarch-handson-app-web
===========================
Nablarchアプリケーションフレームワークを利用して作成したウェブExampleアプリケーションです。

## アプリケーションのビルドと実行

### 事前準備
本Exampleアプリケーションをビルドする前に、データベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

### アプリケーションのビルド

次に、アプリケーションをビルドします。チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    cd nablarch-handson-app-web
    $mvn clean compile

実行に成功すると、以下のようなログがコンソールに出力されます。

    (中略)
    [INFO] --- maven-compiler-plugin:3.2:compile (default-compile) @ nablarch-example-app-web ---
    [INFO] Changes detected - recompiling the module!
    [INFO] Compiling 56 source files to c:\example\nablarch-example-web\target\classes
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    (中略)

### アプリケーションの起動
最後にwaitt-maven-pluginを実行し、組み込みTomcatを起動させます。以下のコマンドを実行してください。

    $mvn waitt:run

起動に成功すると、自動的にアプリケーションのログイン画面が表示されます。
以下のログインID、パスワードでログインできます。

| ログインID | パスワード |
|:------|:--------|
| 10000001 | pass123-|

### DBの確認方法

1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. (ブラウザが自動的に起動しない場合)  
ブラウザから http://localhost:8082 を開きます。  
アクセスできない場合は、 http://<自分のPCのIPアドレス>:8082 を開きます。
1. H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
1. 中央のSQLを入力するフィールドに確認のためのSQLを入力し、Runボタンをクリックします。

**注意**
h2.bat実行中はサンプルアプリケーションからDBへアクセスすることができないため、**サンプルアプリケーションを実行できません。**

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |