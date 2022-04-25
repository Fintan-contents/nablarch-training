画面から検索条件を受け取って検索結果を表示しよう
==================================

## 演習内容
ユーザからの入力を元に検索をする方法を学習します。
  本ハンズオンではウェブExampleアプリケーションのプロジェクト検索一覧画面を題材にします。

## 作成する機能について

プロジェクト一覧検索機能を作成します。

## 演習を開始するための準備

### 事前準備

#### データベース・エンティティクラスの準備
本ハンズオンを開始する前にデータベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

#### ウェブアプリケーション共通ライブラリの準備
本ハンズオンを開始する前にウェブアプリケーション共通ライブラリの作成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd nablarch-handson-app-web-common
    $mvn clean install

### web プロジェクト起動
チェックアウトディレクトリに移動し、以下のコマンドを実行してください。ブラウザが自動的に起動します。
正常に「ログイン画面」が表示されることを確認してください。

    $cd handson-07
    $mvn clean compile
    $mvn waitt:run

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書
- [7.3.1. データベースアクセス(JDBCラッパー)](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/database/database.html#jdbc)
	- [可変条件を持つSQLを実行する](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/database/database.html#database-use-variable-condition)
	- [order byのソート項目を実行時に動的に切り替えてSQLを実行する](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/database/database.html#order-bysql)

### APIドキュメント(アプリケーションプログラマ向け)
- [BeanUtil](https://nablarch.github.io/docs/5u21/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/core/beans/BeanUtil.html)

## 演習
では、以下の手順で、データベースから取得したデータを元に検索一覧画面を作成しましょう。


### 1. SQL文（Project.sql）を作成する
[Project.sql](./src/main/resources/com/nablarch/example/app/entity/Project.sql) に以下を実装してください。

- Java側から指定した可変個の条件を使用するWHERE句
- Java側から指定した条件でのソート

実装すべき内容の詳細は雛形に記載してあります。


### 2. アクション（ProjectAction.java）を作成する
[ProjectAction.java](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java)のlistメソッドに、検索条件を表すオブジェクトの生成を実装してください。

実装すべき内容の詳細は雛形に記載してあります。


## 動作確認方法

1. [web プロジェクト起動](#web-プロジェクト起動)を参考に handson-07 を起動します。
2. ログインします。
3. プロジェクト検索一覧画面が表示されるので、プロジェクト名に「プロジェクト００」と入力して、検索ボタンをクリックします。
4. 検索結果として、「プロジェクト００」が含まれるプロジェクトのみ表示されていることを確認します。
5. 検索結果の右上に存在するソート条件を「プロジェクトID」「昇順」にして検索ボタンをクリックしてください。
6. 検索結果がプロジェクトID順にソートされることを確認します。


※ログイン時に利用できるユーザは以下です。

| ログインID | パスワード |
|:-------- |:---------|
| 10000001 | pass123- |
