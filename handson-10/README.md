更新・削除画面を作ろう
==================================

## 演習内容
本ハンズオンでは、マスタメンテでよく扱う更新処理及び削除処理の作り方を学習します。
  ウェブExampleアプリケーションのプロジェクト変更機能を題材にします。

基本編では、入門編で学んだことを踏まえつつ、Nablarchの解説書を参照しながら機能を作りこんでいきます。

## 作成する機能について

プロジェクト変更(削除含む)機能を作成します。

## 演習を開始する為の準備

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

    $cd handson-10
    $mvn clean compile
    $mvn waitt:run

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

- 入門編で参照したドキュメント全般

### 解説書

#### Nablarchアプリケーションフレームワークの解説書
2.2と7.10.1の「データベースとの相関バリデーションを行う」を読むことで、どの精査をFormで行い、どの精査をActionで行うかがわかります。

- [2.2. アプリケーションの責務配置](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/web/application_design.html#application-design)
- [7.10.1. Bean Validation](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/validation/bean_validation.html#bean-validation)
	- [データベースとの相関バリデーションを行う](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/validation/bean_validation.html#bean-validation-database-validation)
- [7.3.2. ユニバーサルDAO](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#dao)
  - [楽観的ロックを行う](https://nablarch.github.io/docs/5u19/doc/application_framework/application_framework/libraries/database/universal_dao.html#universal-dao-jpa-optimistic-lock)

### APIドキュメント(アプリケーションプログラマ向け)
- [UniversalDao](https://nablarch.github.io/docs/5u19/publishedApi/nablarch-all/publishedApiDoc/programmer/nablarch/common/dao/UniversalDao.html)
    (レコードの存在チェックに使用できるメソッドが記載されています)

## 実装内容
以下の機能を実装していきます。

- ユーザの入力を元に、既存のプロジェクトを更新する機能を実装してください。更新先のテーブルはPROJECTテーブルになります。
    入力項目については、提供するJSPを参照してください。
- ユーザが選択したプロジェクトを削除する機能を実装してください。PROJECTテーブルのレコードを物理削除します。


## 画面遷移
画面遷移は以下の通りにしてください。

### 画面遷移(戻り遷移は除く)
![画面遷移1](./fig/fig01.png "画面遷移1")
### 画面遷移(戻り遷移のみ)
![画面遷移2](./fig/fig02.png "画面遷移2")


## ER図
本ハンズオンで使用するテーブルのER図です。

![ER図](./fig/fig03.png "ER図")


## 実装済みの箇所
### 画面(Java側、JSP側双方含む)
以下の画面については実装済みです。

- プロジェクト検索
- 顧客検索

### Form、DTO
以下のFormやDTOは実装済みです。

- プロジェクト変更画面への遷移用Form([ProjectTargetForm](../nablarch-handson-app-web-common/src/main/java/com/nablarch/example/app/web/form/ProjectTargetForm.java))
- プロジェクト情報DTO([ProjectDto](../nablarch-handson-app-web-common/src/main/java/com/nablarch/example/app/web/dto/ProjectDto.java))
- プロジェクト変更画面用Form([ProjectUpdateForm](./src/main/java/com/nablarch/example/app/web/form/ProjectUpdateForm.java))

### JSPファイル
JSPファイルは実装済みです。以下のファイルを使用してください。

- プロジェクト変更画面([update.jsp](./src/main/webapp/WEB-INF/view/project/update.jsp))
- プロジェクト更新確認画面([confirmOfUpdate.jsp](./src/main/webapp/WEB-INF/view/project/confirmOfUpdate.jsp))
- プロジェクト更新完了画面([completeOfUpdate.jsp](./src/main/webapp/WEB-INF/view/project/completeOfUpdate.jsp))
- プロジェクト削除完了画面([completeOfDelete.jsp](./src/main/webapp/WEB-INF/view/project/completeOfDelete.jsp))


## 仕様について
### 画面間の連携に関する仕様
#### ログインユーザのIDについて
- ログインユーザのIDはセッションストアで保持しています。セッションストアへ登録する処理は、ログイン処理([AuthenticationAction#login](./src/main/java/com/nablarch/example/app/web/action/AuthenticationAction.java))で行っています。

#### 画面から送信される値及び、画面表示する値に関するFormとDTOの利用ルール
- 以下の値の連携は、Formを使用してください。
    - プロジェクト詳細画面([detail.jsp](./src/main/webapp/WEB-INF/view/project/detail.jsp))から、プロジェクト変更初期画面の表示処理([ProjectAction#edit](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))へ送信された値の取り出しは、プロジェクト変更画面への遷移用Form([ProjectTargetForm](../nablarch-handson-app-web-common/src/main/java/com/nablarch/example/app/web/form/ProjectTargetForm.java))を使用してください。
    - プロジェクト変更画面([update.jsp](./src/main/webapp/WEB-INF/view/project/update.jsp))から、更新確認画面の表示処理([ProjectAction#confirmOfUpdate](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))へ送信された値の取り出しは、プロジェクト変更画面用Form([ProjectUpdateForm](./src/main/java/com/nablarch/example/app/web/form/ProjectUpdateForm.java))を使用してください。

- 以下の値の連携は、DTOを使用してください。
    - プロジェクト変更画面([update.jsp](./src/main/webapp/WEB-INF/view/project/update.jsp))へ表示する値は、プロジェクト情報DTO([ProjectDto](../nablarch-handson-app-web-common/src/main/java/com/nablarch/example/app/web/dto/ProjectDto.java))を介して設定してください。

#### セッションストアを用いた値の連携を使用する箇所
以下の値の連携は、セッションストア上のProjectのエンティティを介して行ってください。セッションストアに登録する際のキー名は"project"としてください。

| 遷移元 | 遷移先 |
|:-------|:-------|
| プロジェクト更新確認画面([confirmOfUpdate.jsp](./src/main/webapp/WEB-INF/view/project/confirmOfUpdate.jsp)) （セッションストアへの値の登録自体は、更新確認画面表示処理([ProjectAction#confirmOfUpdate](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))で実施）| 変更画面へ戻る処理([ProjectAction#backToEdit](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java)) |
| 同上 | 更新処理([ProjectAction#update](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java)) |
| プロジェクト変更画面([update.jsp](./src/main/webapp/WEB-INF/view/project/update.jsp)) （セッションストアへの値の登録自体は、更新初期画面表示処理([ProjectAction#edit](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))で実施） | 削除処理([ProjectAction#delete](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java)) |

補足：セッションストアからの値に取り出しについては、[handson-04](../handson-04/README.md)と[handson-05](../handson-05/README.md)で登場しました。セッションストアへの値の登録については、APIドキュメント(アプリケーションプログラマ向け)を参照してください。


### システム全般で共通する仕様
#### DB更新(及び削除)を伴うリクエストのルール
- DB更新(及び削除)を伴うリクエストは以下のルールを守ってください。
    - 更新完了画面(及び削除完了画面)の表示について、リロードした際に値がPOSTされるのを防ぐために、リダイレクトを使用してください。
        表示にリダイレクトを使用する方法は、[handson-05](../handson-05/README.md)で登場しました。
    - 二重サブミット対策を実装してください。エラー時の遷移先はデフォルト(web-component-configuration.xmlで定義されています)とします。
        二重サブミット対策は、[handson-05](../handson-05/README.md)で登場しました。


#### トランザクション系テーブル(PROJECTテーブル)にUPDATEを行う際のルール
- トランザクション系テーブル(PROJECTテーブル)にUPDATEを行う際は、楽観的ロックを使用してください。

#### プロジェクトのデータに関するルール
- 存在しない顧客に紐づくプロジェクトは作成してはいけません。チェックのルールは以下の通りとします。
    - 更新確認画面表示時のチェック
        - この場合は、業務例外として処理します。顧客テーブルに顧客IDが存在しない場合は、ApplicationExceptionを送出してください。ApplicationExceptionに設定するメッセージは、MessageUtil#createMessageで生成してください。パラメータは以下の通りです。
            - エラーレベル：MessageLevel.ERROR
            - メッセージID："errors.nothing.client"
            - メッセージの置換文字列1：顧客ID
    - 更新画面へ戻る際のチェック
        - この場合は、システム例外として処理します（今回題材としているシステムには顧客の削除機能が存在しておらず、戻る遷移の際にデータが存在しないケースは通常存在しないため）。UniversalDao#findByIdが送出するNoDataExceptionをそのまま送出すればシステム例外として扱われます。
    - 補足：更新処理時に顧客テーブルに顧客IDが存在しない場合については、外部キー制約のエラーにより、システム例外が送出されます。


## 演習
では、更新処理及び削除処理を実装していきましょう。

### Java部分

#### 更新処理及び削除処理に共通する処理の実装内容

##### プロジェクト変更初期画面の表示処理([ProjectAction#edit](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))
- 本メソッドには以下の処理を実装します。
    - Formに実装した入力値の精査処理の呼び出し。精査処理の呼び出し方法については、[handson-03](../handson-03/README.md)で登場しました。
    - 画面から受け取ったプロジェクトIDと、セッションストアから取り出したログインユーザのIDを元に、PROJECTテーブルを検索し、更新対象のエンティティを取得してください。
    - エンティティの値を設定した[ProjectDto](../nablarch-handson-app-web-common/src/main/java/com/nablarch/example/app/web/dto/ProjectDto.java)のインスタンスを用意してください。
    - 次画面の表示に使用するために、ProjectDtoをリクエストスコープに設定してください。キーは"form"です。
    - 更新対象のエンティティをセッションストアに登録してください。登録時の名前は、"project"としてください。このエンティティは画面間の値の受け渡しで使用します。
    - 正常終了時、プロジェクト変更画面([update.jsp](./src/main/webapp/WEB-INF/view/project/update.jsp))に遷移する処理を実装してください。

#### 更新処理に関する実装内容

##### 更新確認画面の表示処理([ProjectAction#confirmOfUpdate](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))
- 本メソッドには以下の処理を実装します。
    - Formに実装した入力値の精査処理の呼び出し。
    - 入力精査エラー時の遷移先設定の実装。エラー発生時の遷移先はプロジェクト変更画面([update.jsp](./src/main/webapp/WEB-INF/view/project/update.jsp))にしてください。
    - 顧客IDの存在チェック(「プロジェクトのデータに関するルール」の項を参照)。SQL IDは"FIND_BY_CLIENT_ID"です。
    - Formの内容を、セッションストア中のProjectのエンティティ(ProjectAction#edit内で格納したもの)に設定してください。
    - 正常終了時、プロジェクト変更画面([confirmOfUpdate.jsp](./src/main/webapp/WEB-INF/view/project/confirmOfUpdate.jsp))に遷移する処理を実装してください。

##### 変更画面へ戻る処理([ProjectAction#backToEdit](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))
- 本メソッドには以下の処理を実装します。
    - セッションストアの情報を元に、ProjectDtoを生成します。
    - 生成したProjectDtoを画面表示に使用するため、リクエストスコープに設定します。キーは"form"です。
    - 正常終了時、プロジェクト変更画面([update.jsp](./src/main/webapp/WEB-INF/view/project/update.jsp))に遷移する処理を実装してください。


##### 更新処理([ProjectAction#update](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))
- 本メソッドには以下の処理を実装します。
    - PROJECTテーブルを更新します。
    - 更新処理と同時に不要になるセッションストア上のオブジェクトを削除します。
    - 正常終了時、更新完了画面を表示する処理([ProjectAction#completeOfUpdate](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))に遷移する処理を実装してください。


##### 更新完了画面を表示する処理([ProjectAction#completeOfUpdate](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))
- 本メソッドには以下の処理を実装します。
    - 正常終了時、プロジェクト更新完了([completeOfUpdate.jsp](./src/main/webapp/WEB-INF/view/project/completeOfUpdate.jsp))に遷移する処理を実装してください。


#### 削除処理に関する実装内容

##### 削除処理([ProjectAction#delete](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))
- 本メソッドには以下の処理を実装します。
    - PROJECTテーブルからレコードを1件削除します。削除対象は、セッションストアから"project"という名前で取り出したProjectオブジェクトに格納されているプロジェクトIDのレコードです。
    - 正常終了時、削除完了画面の表示処理([ProjectAction#completeOfDelete](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))に遷移する処理を実装してください。


##### 削除完了画面の表示処理([ProjectAction#completeOfDelete](./src/main/java/com/nablarch/example/app/web/action/ProjectAction.java))
- 本メソッドには以下の処理を実装します。
    - 正常終了時、プロジェクト削除完了([completeOfDelete.jsp](./src/main/webapp/WEB-INF/view/project/completeOfDelete.jsp))に遷移する処理を実装してください。


## 動作確認方法

### 更新処理の動作確認
1. [web プロジェクト起動](#web-プロジェクト起動)を参考に handson-10 を起動します。
2. ログインします。
3. プロジェクト検索一覧画面が表示されるので、検索ボタンをクリックします。
4. 検索結果が表示されるので、プロジェクトID列のリンクをクリックします。
5. プロジェクト詳細画面に遷移し、クリックした行の情報が表示されます。
6. 変更ボタンをクリックします。
7. 必須項目を消し、更新ボタンをクリックした際、エラーが表示されることを確認します。
8. 全ての項目を適切に入力して更新ボタンをクリックした際、プロジェクト更新確認画面(入力した値が表示されている画面)が表示されることを確認してください。
9. 入力へ戻るボタンをクリックすると、プロジェクト変更画面が表示され、全ての項目が項番8で更新ボタンをクリックする前の状態に戻っていること
(入力値が復元されていること)を確認してください。
10. 更新ボタンをクリックすると、プロジェクト更新確認画面が表示されることを確認してください。
11. 確定ボタンをクリックすると、プロジェクト更新完了画面が表示されることを確認してください。


### 削除処理の動作確認
1. 更新処理の動作確認の項番6まで進めます。
2. 削除ボタンをクリックします。
3. プロジェクト削除完了画面が表示されますので、次へをクリックします。
4. プロジェクト検索一覧画面に、削除したプロジェクトが表示されないことを確認します。


※ログイン時に利用できるユーザは以下です。

| ログインID | パスワード |
|:-------- |:---------|
| 10000001 | pass123- |


## 解答例について
解答例は、[nablarch-handson-app-web](../nablarch-handson-app-web/README.md)を参照してください。
