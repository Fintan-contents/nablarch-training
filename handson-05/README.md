入力した情報を復元して入力画面に戻ってみよう
===========================

## 演習内容
入力、確認、完了の3画面で構成される画面機能では、確認画面から入力画面へ戻る必要があります。
  この時、入力画面には確認画面に遷移する前の情報を復元するのが一般的です。
  本ハンズオンではウェブExampleアプリケーションのプロジェクト変更確認画面からプロジェクト変更画面への遷移を題材に入力情報を復元する方法を学習します。

## 作成する機能について

プロジェクト変更確認画面からプロジェクト変更入力画面へ戻る機能を作成します。

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

    $cd handson-05
    $mvn clean compile
    $mvn waitt:run

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書
- [7.17.セッションストア](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/session_store.html#session-store)
  - [入力～確認～完了画面間で入力情報を保持する](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/session_store.html#session-store-input-data)
  	- [更新機能での実装例](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/session_store/update_example.html#id1)

### APIドキュメント(アプリケーションプログラマ向け)
- [SessionUtil](https://nablarch.github.io/docs/5u19/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/common/web/session/SessionUtil.html)
- [BeanUtil](https://nablarch.github.io/docs/5u19/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/core/beans/BeanUtil.html)
- [UniversalDao](https://nablarch.github.io/docs/5u19/javadoc/nablarch/common/dao/UniversalDao.html)

## 演習
では、以下の手順で確認画面から入力画面へ戻る処理を実装してみましょう。

### 1. アクション(ProjectAction.java)を修正する。
プロジェクト変更確認画面からプロジェクト変更画面への遷移時には[ProjectAction.java](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java) の backToEdit メソッドが呼出されます。
  入力情報を復元する為にはこのメソッドでセッションストアに格納されている入力情報を取得後、DTOを生成しその値をコピーする必要があります。
  コピーしたDTOをリクエストスコープに格納して画面表示すれば、入力情報を復元できます。


## 動作確認方法
[web プロジェクト起動](#web-プロジェクト起動)を参考に handson-05 を起動し、以下の点を確認してください。
  プロジェクト変更画面までは、プロジェクト検索→プロジェクト詳細→プロジェクト変更の順に辿ってください。

- プロジェクト変更画面で入力した情報がプロジェクト変更確認画面に表示されている。
- プロジェクト変更確認画面から「入力へ戻る」ボタンを押下し、プロジェクト変更画面へ遷移した際には入力した情報が復元されている。
- 何度やっても同じ動作をする。 (前々回の入力情報が復元されたりしない。)

※ログイン時に利用できるユーザは以下です。

| ログインID | パスワード |
|:-------- |:---------|
| 10000001 | pass123- |
