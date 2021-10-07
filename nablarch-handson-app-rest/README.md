nablarch-handson-app-rest
===============
Nablarchアプリケーションフレームワークを利用して作成したRESTfulウェブサービスのExampleです。

## アプリケーションのビルドと実行

### 事前準備
本Exampleをビルドする前に、データベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

### アプリケーションのビルド

次に、アプリケーションをビルドします。チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    cd nablarch-handson-app-rest
    $mvn clean compile

実行に成功すると、以下のようなログがコンソールに出力されます。

    (中略)
    [INFO] --- maven-compiler-plugin:3.2:compile (default-compile) @ nablarch-handson-app-rest ---
    [INFO] Changes detected - recompiling the module!
    [INFO] Compiling 12 source files to C:\example\nablarch-handson\nablarch-handson-app-rest\target\classes
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    (中略)

### アプリケーションの起動
最後にwaitt-maven-pluginを実行し、ウェブサービスを起動します。以下のコマンドを実行してください。

    $mvn waitt:run-headless

### テスト用クライアントクラスからのアクセス

以下のクラスのmainメソッドを実行します。

* com.nablarch.example.client.ItemClient  
(通常のJavaコンソールアプリです。)

このクラスでは、以下のリクエストを起動したウェブサービスに対して順番に送信し、結果を標準出力に表示しています。

1. 商品テーブルの全件検索(10件出力されます)。
2. カテゴリーが「hardware」である商品の検索(4件出力されます)。
3. 商品を下表1の内容で追加(「insert status:201」が出力されます)。
4. 商品テーブルの全件検索(11件出力されます)。
5. 商品名に「商品９９９」を含む商品を検索し、下表2の内容で更新(「update status:200」が出力されます)。
6. 商品テーブルの全件検索(11件出力されます)。

**注意** 上記の出力件数は、DBが初期状態の場合です。


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

### テーブルのデータの初期化手順
テーブルのデータを初期状態に戻したい場合は、ウェブサービスが起動している場合は終了させてから、事前準備の手順を再度実行します。

### DBの確認方法

1. ウェブサービスが起動している場合は終了させます。
1. コマンドプロンプトを起動します。
1. カレントディレクトリを<チェックアウトディレクトリ>/h2/bin/に移動します。
1. h2.batを起動します。
1. (ブラウザが自動的に起動しない場合)  
ブラウザから http://localhost:8082 を開きます。  
アクセスできない場合は、 http://<自分のPCのIPアドレス>:8082 を開きます。
2. H2に接続するための情報を入力する画面が表示されるので、後述の「H2への接続情報」に記載されている情報を入力し、Connectボタンをクリックします。
3. 中央のSQLを入力するフィールドに確認のためのSQLを入力し、Runボタンをクリックします。本ExampleアプリケーションはITEMテーブルを操作しますので、次のSQLで確認できます。  
    ```
        SELECT * FROM ITEM;
    ```

**注意**
h2.bat実行中はサンプルアプリケーションからDBへアクセスすることができないため、**サンプルアプリケーションを実行できません。**

### H2への接続情報

| 項目      | 値                         |
|:----------|:---------------------------|
| JDBC URL  | jdbc:h2:..\\..\entity\h2\db\nablarch_example |
| User Name | NABLARCH_EXAMPLE           |
| Password  | NABLARCH_EXAMPLE           |
