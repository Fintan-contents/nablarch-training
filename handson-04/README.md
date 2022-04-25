画面から値を受け取ってみよう
==================================

## 演習内容
以下について学習します。

 - ユーザに値を入力してもらった値を、精査して受け取る方法
 - 入力画面と確認画面の共通化を支援する機能を使用した、確認画面の作成方法

本ハンズオンではウェブExampleアプリケーション のプロジェクト変更画面を題材にします。

## 作成する機能について

入力値の精査と、入力画面を利用した確認画面の作成を行います。

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

    $cd handson-04
    $mvn clean compile
    $mvn waitt:run

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書

- [7.19. JSPカスタムタグ](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/tag.html#jsp)
	- [エラー表示を行う](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/tag.html#tag-write-error)
	- [入力画面と確認画面を共通化する](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/tag.html#tag-make-common)

- [7.10.1. Bean Validation](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/validation/bean_validation.html#bean-validation)
	- [ドメインバリデーションを使う](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/validation/bean_validation.html#bean-validation-domain-validation)
		- 各Beanでドメインバリデーションを使う
  - [ウェブアプリケーションのユーザ入力値のチェックを行う](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/libraries/validation/bean_validation.html#bean-validation-web-application)

- [6.3.1. InjectForm インターセプター](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/handlers/web_interceptor/InjectForm.html#injectform)
	- [InjectFormを利用する](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/handlers/web_interceptor/InjectForm.html#id4)
	- [バリデーションエラー時の遷移先を指定する](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/handlers/web_interceptor/InjectForm.html#id5)

- [6.3.2. OnErrorインターセプター](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/handlers/web_interceptor/on_error.html#onerror)
  - [OnErrorを利用する](https://nablarch.github.io/docs/5u21/doc/application_framework/application_framework/handlers/web_interceptor/on_error.html#id4)

### APIドキュメント(アプリケーションプログラマ向け)
- [注釈型 Required](https://nablarch.github.io/docs/5u21/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/core/validation/ee/Required.html)
- [注釈型 Domain](https://nablarch.github.io/docs/5u21/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/core/validation/ee/Domain.html)
- [注釈型 OnError](https://nablarch.github.io/docs/5u21/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/fw/web/interceptor/OnError.html)


## 演習
では、以下の手順で入力画面と確認画面を実装しましょう。

### 1. 入力画面のJSP（update.jsp）を作成する
[update.jsp](./src/main/webapp/WEB-INF/view/project/update.jsp) にエラー出力領域を作成してください。
  実装すべき内容の詳細は雛形に記載してあります。


### 2. 確認画面のJSP（confirmOfUpdate.jsp）を作成する
[confirmOfUpdate.jsp](./src/main/webapp/WEB-INF/view/project/confirmOfUpdate.jsp) を実装して、確認画面を完成させてください。
  実装すべき内容の詳細は雛形に記載してあります。


### 3. Form（ProjectUpdateForm.java）に精査処理を実装する
[ProjectUpdateForm.java](./src/main/java/com/nablarch/example/app/web/form/ProjectUpdateForm.java)に以下を実装してください。

- ドメイン指定によるバリデーションに使用するアノテーションの付与(今回は、projectNameプロパティに対して付与します。他のプロパティには付与済みです)

実装すべき内容の詳細は雛形に記載してあります。


### 4. アクション（ProjectAction.java）に精査処理の呼び出しを実装する
[ProjectAction.java](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java)のconfirmOfUpdateメソッドに以下を実装してください。

- 精査処理呼び出し
- 精査失敗時の遷移先の指定

実装すべき内容の詳細は雛形に記載してあります。


## 動作確認方法

1. [web プロジェクト起動](#web-プロジェクト起動)を参考に handson-05 を起動します。
2. ログインします。
3. 検索結果の、いずれかの行のプロジェクトIDをクリックします。
4. プロジェクト詳細画面が表示されるので、変更ボタンをクリックします。
5. プロジェクト変更画面が表示されます。想定どおりのボタンが表示されていることを確認してください。
6. プロジェクト変更画面で、プロジェクト名を空にして更新ボタンをクリックしてください。
7. プロジェクト変更画面に、エラーメッセージが表示されることを確認してください。
8. プロジェクト変更画面で、プロジェクト名に全角でなにか入力して、更新ボタンをクリックしてください。
9.  入力内容の確認画面(テキストボックス等が、単なる文字列として表示される画面)に遷移することを確認してください。

※ログイン時に利用できるユーザは以下です。

| ログインID | パスワード |
|:-------- |:---------|
| 10000001 | pass123- |
