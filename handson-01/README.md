データベースから情報を取得して画面表示してみよう
==================================

## 演習内容
本ハンズオンでは データベースのデータ表示を学習します。
  ウェブExampleアプリケーション のプロジェクト変更画面を題材にして、データベースの内容を表示する方法を学習します。

## 作成する機能について

プロジェクト変更画面の初期表示処理を作成します。

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
チェックアウトディレクトリに移動し、以下のコマンドを実行してください。ブラウザが自動的に起動します。正常に「ログイン画面」が表示されることを確認してください。

    $cd handson-01
    $mvn clean compile
    $mvn waitt:run

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書
- [7.3.2. ユニバーサルDAO](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#dao)
	- [任意のSQL(SQLファイル)で検索する](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#sql-sql)
	- [テーブルをJOINした検索結果を取得する](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#join)
	- [条件を指定して検索する](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#universal-dao-search-with-condition)

### APIドキュメント(アプリケーションプログラマ向け)
- [UniversalDao](https://nablarch.github.io/docs/5u19/javadoc/nablarch/common/dao/UniversalDao.html)
- [ExecutionContext](https://nablarch.github.io/docs/5u19/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/fw/ExecutionContext.html)


## 演習
では、以下の手順でデータベースの内容を表示してみましょう。

### 1. アクション（ProjectAction.java）を作成する
[ProjectAction.java](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java) に、DBから値を取得して表示する処理を実装してください。
  実装するメソッドはeditメソッドです。実装すべき内容は雛形に記載してあります。


## 動作確認方法

1. [web プロジェクト起動](#web-プロジェクト起動)を参考に handson-01 を起動します。
2. ログインします。
3. プロジェクト検索一覧画面が表示されるので、画面中央の検索結果について、いずれかの行のプロジェクトIDをクリックします。
5. プロジェクト詳細画面が表示されるので、変更ボタンをクリックします。
6. プロジェクト変更画面が表示されます。ここでプロジェクト詳細画面と同一のデータが表示されていれば、成功です。

※ログイン時に利用できるユーザは以下です。

| ログインID | パスワード |
|:-------- |:---------|
| 10000001 | pass123- |
