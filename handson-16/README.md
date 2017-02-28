RESTfulウェブサービスに認証認可を組み込んでみよう
=============================================

## 演習内容
本ハンズオンでは、NablarchのJAX-RSサポートを利用したアプリケーションで
セッションを扱う方法を学習します。

## 作成するウェブサービスについて
既存の商品テーブルの一覧検索のウェブサービスに対して、以下を追加します。
- ログイン機能
- ログインチェック機能


## 演習を開始するための準備

### 事前準備
本ハンズオンを開始する前にデータベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書
- 6.2.10. セッション変数保存ハンドラ
- 7.18. セッションストア

## 実装する機能
- セッションストアを使用できるように設定してください。以下のファイルの修正が必要です。
    - pom.xml
    - コンポーネント定義ファイル
    - 環境値設定ファイル。
- ログイン処理を追加してください。
- ログインチェック処理をハンドラの形式で追加してください。

  また、ログインチェック処理を動作させるためには、ハンドラキューにログインチェック処理の追加が必要になります。


## 実装済みの箇所
以下については実装済みです。
- URLと対応するアクションクラス、メソッドをマッピングするルート定義ファイル([routes.xml](./src/main/resources/routes.xml))
- 商品テーブルの一覧検索
- DBを使用した認証用のユーティリティクラス([AuthenticationUtil.java](./src/main/java/com/nablarch/example/common/authentication/AuthenticationUtil.java))
- DBストアが使用するテーブル(USER_SESSIONテーブル)
- Actionクラス内で例外が送出された場合の例外ハンドリング([ExampleErrorResponseBuilder.java](./src/main/java/com/nablarch/example/error/ExampleErrorResponseBuilder.java))

## 演習
では、ログイン機能及び、ログインチェック機能を実装していきましょう。

### セッションストアを使用できるように設定する
#### pom.xmlに依存関係を追加する
セッションストアを使用するために必要な依存関係を、pom.xml([pom.xml](./pom.xml))に追加し、完成させてください。

実装すべき内容の詳細は雛形に記載してあります。

#### コンポーネント定義ファイルにセッションストア機能と、ログインチェック機能に必要な設定を追加する
セッションストア機能に必要な設定を、コンポーネント定義ファイル([rest-component-configuration.xml](./src/main/resources/rest-component-configuration.xml))及び、
開発用ハンドラキュー定義([handler_dev.xml](./src/env/dev/resources/handler_dev.xml))に追加し、完成させてください。

実装すべき内容の詳細は雛形に記載してあります。

#### 全環境共通の環境設定値ファイルに設定を追加する。

セッションストア用コンポーネント定義が参照する環境設定値を、全環境用環境設定ファイル([common.config](./src/main/resources/common.config))に追加し、
完成させてください。

以下の環境設定値が必要となります。

| 環境設定値名                              | 説明                                     |
|:------------------------------------------|:-----------------------------------------|
| nablarch.sessionManager.defaultStoreName  | デフォルトのセッションストア名           |
| nablarch.sessionManager.expires           | セッションストアの有効期限(秒)           |
| nablarch.sessionStoreHandler.cookieName   | セッションストア用クッキーの名称         |
| nablarch.sessionStoreHandler.cookiePath   | セッションストア用クッキーのパス属性     |
| nablarch.sessionStoreHandler.cookieSecure | セッションストア用クッキーのセキュア属性 |

実装すべき内容の詳細は雛形に記載してあります。

### ログイン機能を追加する（[AuthenticationActionAction.java](./src/main/java/com/nablarch/example/action/AuthenticationAction.java)）

AuthenticationActionAction.javaに、ログイン機能を実装してください。

実装すべき内容の詳細は雛形に記載してあります。

### ログインチェック機能を追加する（[LoginUserPrincipalCheckHandler.java](./src/main/java/com/nablarch/example/handler/login/LoginUserPrincipalCheckHandler.java)）

LoginUserPrincipalCheckHandler.javaに、ログインチェック機能を実装してください。

実装すべき内容の詳細は雛形に記載してあります。


## 動作確認方法

### アプリケーションのビルド
まず、アプリケーションをビルドします。チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    cd handson-16
    $mvn clean compile

### アプリケーションの起動
次に、waitt-maven-pluginを実行し、ウェブサービスを起動します。以下のコマンドを実行してください。

    $mvn waitt:run-headless

### テスト用クライアントクラスからのアクセス

最後に、以下のクラスのmainメソッドを実行します。

* com.nablarch.example.client.ItemClient

このクラスでは、以下のリクエストを起動したウェブサービスに対して順番に送信し、結果を標準出力に表示しています。

1. ログイン要求を、誤ったパスワードを使用して行ないます

   認証に失敗し、ステータスコード400が返ってきます。

2. セッション用のクッキーなしで、商品テーブルの全件検索

   ログインしていないため、ステータスコード401が返ってきます。

3. ログイン要求を、正しいパスワードを使用して行ないます。

4. 商品テーブルの全件検索(10件出力されます)。


初期状態のデータでは、標準出力に以下の内容が表示されれば問題ありません。

    start login(expect http status code 400.)
    http status:400
    start getitem(expect http status code 401.)
    HTTP 401 Unauthorized
    --------
    start login(expect http status code 200.)
    http status:200
    start getitem(expect successful get data)
    ---- items (size: 10) ----
    Item(ItemId: 1, ItemName: 商品１, Category: hardware, Explanation: 商品１の説明, Price: 100000)
    Item(ItemId: 2, ItemName: 商品２, Category: hardware, Explanation: 商品２の説明, Price: 110000)
    Item(ItemId: 3, ItemName: 商品３, Category: software, Explanation: 商品３の説明, Price: 10000)
    Item(ItemId: 4, ItemName: 商品４, Category: hardware, Explanation: 商品４の説明, Price: 90000)
    Item(ItemId: 5, ItemName: 商品５, Category: software, Explanation: 商品５の説明, Price: 25000)
    Item(ItemId: 6, ItemName: 商品６, Category: software, Explanation: 商品６の説明, Price: 1500)
    Item(ItemId: 7, ItemName: 商品７, Category: hardware, Explanation: 商品７の説明, Price: 1000000)
    Item(ItemId: 8, ItemName: 商品８, Category: software, Explanation: 商品８の説明, Price: 120000)
    Item(ItemId: 9, ItemName: 商品９, Category: software, Explanation: 商品９の説明, Price: 115000)
    Item(ItemId: 10, ItemName: 商品１０, Category: software, Explanation: 商品１０の説明, Price: 300000)

## DBの確認方法

1. ウェブサービスが起動している場合は終了させます。
2. コマンドプロンプトを起動します。
3. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
4. h2.batを起動します。
5. ブラウザから http://localhost:8082 を開きます。H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
6. 中央のSQLを入力するフィールドに確認のためのSQLを入力し、Runボタンをクリックします。

   **注意**
   h2.bat実行中はアプリケーションからDBへアクセスすることができないため、**ウェブサービスを実行できません。**

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |

## 解答例について

解答例は、[nablarch-handson-app-rest-session](../nablarch-handson-app-rest-session/README.md)を参照してください。