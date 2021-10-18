一覧画面を表示してみよう
==================================

## 演習内容
データベースから取得したデータを元に、ページング付きの一覧画面を表示する方法を学習します。
  本ハンズオンではウェブExampleアプリケーションのプロジェクト検索一覧画面を題材にします。

## 作成する機能について

ウェブExampleアプリケーションのプロジェクト検索一覧画面では、画面から検索条件を指定できますが、本ハンズオンでは、アクションの中で固定の検索条件を与え、その結果を一覧表示します。

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

    $cd handson-02
    $mvn clean compile
    $mvn waitt:run

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書
- [7.3.1. データベースアクセス(JDBCラッパー)](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/database.html#jdbc)
	- [Beanオブジェクトを入力としてSQLを実行する](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/database.html#database-input-bean)

- [7.3.2. ユニバーサルDAO](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#dao)
	- [任意のSQL(SQLファイル)で検索する](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#sql-sql)
	- [条件を指定して検索する](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#universal-dao-search-with-condition)
	- [ページングを行う](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#universal-dao-paging)

### APIドキュメント(アプリケーションプログラマ向け)
- [UniversalDao](https://nablarch.github.io/docs/5u19/javadoc/nablarch/common/dao/UniversalDao.html)
- [ExecutionContext](https://nablarch.github.io/docs/5u19/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/fw/ExecutionContext.html)

## 演習
では、以下の手順で、データベースから取得したデータを元に一覧画面を作成しましょう。

### 1. SQL文（Project.sql）を作成する
ユニバーサルDAOは、一覧画面用のSQLは生成してくれないので、自分でSQLを書く必要が有ります。
  [Project.sql](./src/main/resources/com/nablarch/example/app/entity/Project.sql)にWHERE句を追加して、SQLを完成させてください。

実装すべき内容の詳細は雛形に記載してあります。


### 2. アクション（ProjectAction.java）を作成する
[ProjectAction.java](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java)に、検索処理と、取得結果の設定を実装してください。

実装すべき内容の詳細は雛形に記載してあります。

実装対象のメソッドは、ProjectActionのlistメソッドです。


## 動作確認方法

1. [web プロジェクト起動](#web-プロジェクト起動)を参考に handson-02 を起動します。
2. ログインします。
3. プロジェクト検索一覧画面が表示されるので、検索ボタンをクリックします。
4. 検索結果として、新規開発PJの一覧が表示されることを確認します。
5. 検索結果の右上に存在する「>>」をクリックして、検索結果が切り替わることを確認します。
6. 検索結果の右上に存在する「<<」をクリックして、検索結果が切り替わることを確認します。

※ログイン時に利用できるユーザは以下です。

| ログインID | パスワード |
|:-------- |:---------|
| 10000001 | pass123- |
