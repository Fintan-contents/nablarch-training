RESTfulウェブサービスを作成してみよう
==================================

## 演習内容
本ハンズオンでは、NablarchのJAX-RSサポートを利用して、RESTfulウェブサービスを作成する方法を学習します。

## 作成するウェブサービスについて
商品テーブルの一覧検索、商品の追加、更新を提供するウェブサービスです。

## 演習を開始するための準備

### 事前準備
本ハンズオンを開始する前にデータベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### Nablarchアプリケーションフレームワークの解説書
- 3.1. RESTfulウェブサービス編
    - 3.1.1. アーキテクチャ概要
    - 3.1.3. Getting Started

- 7.4.2. ユニバーサルDAO
	- 使用方法
		- 任意のSQL(SQLファイル)で検索する
		- 条件を指定して検索する

## 実装する機能
- ルート定義ファイルを作成してください。
- 商品テーブルの検索、商品の追加、更新を行う3つのメソッドを実装してください。

## 演習
では、RESTfulウェブサービスを実装していきましょう。

### ルート定義ファイルを作成する
ウェブサービスを提供するURLと対応するアクションクラス、メソッドをマッピングするルート定義ファイル([routes.xml](./src/main/resources/routes.xml))を完成させてください。

実装すべき内容の詳細は雛形に記載してあります。

### アクションを作成する（[ItemAction.java](./src/main/java/com/nablarch/example/action/ItemAction.java)）
ItemAction.javaに、検索処理、レコードの追加処理、レコードの更新処理を実装してください。

実装すべき内容の詳細は雛形に記載してあります。


## 動作確認方法

### アプリケーションのビルド
まず、アプリケーションをビルドします。チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    cd handson-15
    $mvn clean compile

### アプリケーションの起動
次に、waitt-maven-pluginを実行し、ウェブサービスを起動します。以下のコマンドを実行してください。

    $mvn waitt:run-headless

### テスト用クライアントクラスからのアクセス

最後に、以下のクラスのmainメソッドを実行します。

* com.nablarch.example.client.ItemClient

このクラスでは、以下のリクエストを起動したウェブサービスに対して順番に送信し、結果を標準出力に表示しています。

1. 商品テーブルの全件検索(10件出力されます)。
2. カテゴリーが「hardware」である商品の検索(4件出力されます)。
3. 商品を下表1の内容で追加(「insert status:201」が出力されます)。
4. 商品テーブルの全件検索(11件出力されます)。
5. 商品名に「商品９９９」を含む商品を検索し、下表2の内容で更新(「update status:200」が出力されます)。
7. 商品テーブルの全件検索(11件出力されます)。


- 表1

| 項目        | 値               |
|:------------|:-----------------|
| ItemId      | 自動採番         |
| ItemName    | 商品９９９       |
| Category    | hardware         |
| Explanation | 商品９９９の説明 |
| Price       | 20000            |

- 表2

| 項目        | 値               |
|:------------|:-----------------|
| ItemId      | (変更なし)       |
| ItemName    | 商品８８８       |
| Category    | software         |
| Explanation | 商品８８８の説明 |
| Price       | 18000            |

初期状態のデータでは、標準出力に以下の内容が表示されれば問題ありません。

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
    ---- items (size: 4) ----
    Item(ItemId: 1, ItemName: 商品１, Category: hardware, Explanation: 商品１の説明, Price: 100000)
    Item(ItemId: 2, ItemName: 商品２, Category: hardware, Explanation: 商品２の説明, Price: 110000)
    Item(ItemId: 4, ItemName: 商品４, Category: hardware, Explanation: 商品４の説明, Price: 90000)
    Item(ItemId: 7, ItemName: 商品７, Category: hardware, Explanation: 商品７の説明, Price: 1000000)
    insert status:201
    ---- items (size: 11) ----
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
    Item(ItemId: 11, ItemName: 商品９９９, Category: hardware, Explanation: 商品９９９の説明, Price: 20000)
    update status:200
    ---- items (size: 11) ----
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
    Item(ItemId: 11, ItemName: 商品８８８, Category: software, Explanation: 商品８８８の説明, Price: 18000)

## DBの確認方法

1. ウェブサービスが起動している場合は終了させます。
1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
2. ブラウザから http://localhost:8082 を開きます。H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
1. 中央のSQLを入力するフィールドに確認のためのSQLを入力し、Runボタンをクリックします。なお、本ハンズオンでは、ITEMテーブルを操作しています。

   **注意**
   h2.bat実行中はアプリケーションからDBへアクセスすることができないため、**ウェブサービスを実行できません。**

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |

## 解答例について

解答例は、[nablarch-handson-app-rest](../nablarch-handson-app-rest/README.md)を参照してください。
