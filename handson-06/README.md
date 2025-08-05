データベースを更新してみよう
==================================

## 演習内容
ユーザに入力していただいたデータをデータベースに反映し、その後完了画面を表示する方法を学習します。
  本ハンズオンではウェブExampleアプリケーションのプロジェクト変更確認画面及び、プロジェクト変更完了画面を題材にします。

## 作成する機能について

プロジェクト変更確認画面に表示されている内容でDBを更新し、プロジェクト変更完了画面へ遷移する機能を作成します。

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

    $cd handson-06
    $mvn clean compile
    $mvn waitt:run

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書
- [7.19. JSPカスタムタグ](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/tag.html#jsp)
	- [二重サブミットを防ぐ](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/tag.html#tag-double-submission)
      - クライアント側の二重サブミット防止
      - サーバ側の二重サブミット防止
- [6.3.4. OnDoubleSubmissionインターセプター](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/handlers/web_interceptor/on_double_submission.html#ondoublesubmission)
  - [OnDoubleSubmissionを利用する](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/handlers/web_interceptor/on_double_submission.html#id4)
  - [OnDoubleSubmissionのデフォルト値を指定する](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/handlers/web_interceptor/on_double_submission.html#id5)
- [7.17. セッションストア](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/session_store.html#session-store)
  - [更新機能での実装例](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/session_store/update_example.html#id1)

### APIドキュメント(アプリケーションプログラマ向け)
- [SessionUtil](https://nablarch.github.io/docs/5u21/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/common/web/session/SessionUtil.html)
- [UniversalDao](https://nablarch.github.io/docs/5u21/javadoc/nablarch/common/dao/UniversalDao.html)
- [ResourceLocator](https://nablarch.github.io/docs/5u21/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/fw/web/ResourceLocator.html)


## 演習
では、以下の手順で、入力データをデータベースに反映してみましょう。

### 1. JSP（update.jsp）を作成する。
DBに対するコミットを伴う処理では、二重サブミット対策が必要となります。
  [update.jsp](./src/main/webapp/WEB-INF/view/project/update.jsp)に二重サブミット対策を実装してください。

実装すべき内容の詳細は雛形に記載してあります。

### 2. アクション（ProjectAction.java）を作成する。
[ProjectAction.java](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java)に以下を実装してください。

- セッションから前画面より引き継いだエンティティを取得後し、その後削除する処理
- DB更新処理
- プロジェクト変更完了画面の表示処理
- 二重サブミット防止(サーバ側)

実装すべき内容の詳細は雛形に記載してあります。
  実装内容がやや多いですが、雛形中に「handson-06  step○○」と記載してありますので、step1から実装してみてください。

実装対象のメソッドは、ProjectActionのupdateメソッドです。

## 動作確認方法

1. [web プロジェクト起動](#web-プロジェクト起動)を参考に handson-06 を起動します。
2. ログインします。
3. プロジェクト検索一覧画面が表示され、画面下部に検索結果が表示されます。
4. 検索結果のいずれかの行のプロジェクトIDをクリックします。
5. プロジェクト詳細画面が表示されるので、変更ボタンをクリックします。
6. プロジェクト変更画面が表示されます。ここでプロジェクト名を変更して、更新ボタンをクリックしてください。このときにプロジェクト名をメモしておいてください。
7. プロジェクト変更確認画面が表示されますので、確定ボタンをクリックしてください。
8. プロジェクト変更完了画面が表示されます。画面上部のリンクを使用して、プロジェクト検索画面を表示してください。
9. メモしておいたプロジェクト名で検索してください。検索結果にそれが表示されれば成功です。


※ログイン時に利用できるユーザは以下です。

| ログインID | パスワード |
|:-------- |:---------|
| 10000001 | pass123- |
