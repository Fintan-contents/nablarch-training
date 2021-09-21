Nablarch ハンズオン
===========================
Nablach Application Frameworkの基本的なプログラミング方法を自習することを目的とした、
ハンズオンコンテンツです。

Nablarch について体系的に学ぶことができます。

## Nablarch ハンズオン実施手順

### 1. ハンズオン実施環境を準備する。

自習をするための環境を準備します。
後述する環境設定手順に従って環境を構築しましょう。

### 2. ハンズオンの内容を理解する。

自習教材として以下のハンズオンを取り揃えています。
handson-01から順番に実施することで Nablarch を体系的に学習することができます。

| No                         | レベル | ハンズオン| 説明 |
|:---------------------------|:-------|:----------------------|:--------|
| [01](./handson-01/README.md) | 入門   | 画面表示してみよう | JSPの作成と作成したJSPを画面表示する方法を学びます。 |
| [02](./handson-02/README.md) | 入門   | データベースから情報を取得して画面表示してみよう | データベースからデータを取得する方法と取得した情報を画面に表示する方法を学びます。 |
| [03](./handson-03/README.md) | 入門   | 画面からの入力値を受け取って確認画面を表示してみよう | 画面で入力した値の受取り方や入力値を精査する方法を学びます。合わせて、エラーが発生した場合の画面遷移方法も学習することができます。 |
| [04](./handson-04/README.md) | 入門   | 入力した情報を復元して入力画面に戻ってみよう | 画面入力した情報を復元する方法を学びます。 |
| [05](./handson-05/README.md) | 入門   | データベースを更新してみよう | データベースを更新する方法を学びます。 |
| [06](./handson-06/README.md) | 入門   | 一覧画面を表示してみよう | データベースから一覧の情報を取得し、その情報を画面に表示する方法を学びます。 |
| [07](./handson-07/README.md) | 入門   | 画面から検索条件を受け取って検索結果を表示しよう | 画面で入力された条件に一致する情報をデータベースから取得し、一覧表示する方法を学びます。 |
| [08](./handson-08/README.md) | 入門   | JUnitを使用した単体テストをしよう | アプリケーションプログラマーが実施する単体テスト（クラス単体テスト）を実施する方法を学びます。 |
| [09](./handson-09/README.md) | 基本   | 登録画面を作ろう | マスタメンテでよく扱う登録処理の作り方を学びます。 |
| [10](./handson-10/README.md) | 基本   | 更新・削除画面を作ろう | マスタメンテでよく扱う更新・削除処理の作り方を学びます。 |
| [11](./handson-11/README.md) | 基本   | 検索画面と詳細画面を作ろう | マスタメンテでよく扱う、検索処理と詳細画面の作り方を学びます。 |
| [12](./handson-12/README.md) | 基本   | Nablarchバッチを使って、CSVファイルの内容をDBに登録してみよう | NablarchバッチアプリケーションでCSVファイルをDBに登録する方法を学びます。 |
| [13](./handson-13/README.md) | 基本   | RESTfulウェブサービスを作成してみよう | NablarchのJAX-RSサポートを利用した、RESTfulウェブサービスの作り方を学びます。|
| [14](./handson-14/README.md) | 基本   | Batchletを作ってみよう | JSR352に準拠したバッチアプリケーションの一つであるBatchletの作り方を学びます。 |
| [15](./handson-15/README.md) | 基本   | Chunkタイプのバッチを作ってみよう | JSR352に準拠したバッチアプリケーションの一つであるChunkの作り方を学びます。 |
| [nablarch-handson-app-web](./nablarch-handson-app-web/README.md) | -   | 解答 | 01～11の解答(完成品)のプログラムです。 |
| [nablarch-handson-app-batch](./nablarch-handson-app-batch/README.md) | -   | 解答 | 12の解答(完成品)のプログラムです。 |
| [nablarch-handson-app-rest](./nablarch-handson-app-rest/README.md) | -   | 解答 | 13の解答(完成品)のプログラムです。 |
| [nablarch-handson-app-batch-ee](./nablarch-handson-app-batch-ee/README.md) | -   | 解答 | 14～15の解答(完成品)のプログラムです。 |

### 3. ハンズオンを実施する。

各々の説明に従ってハンズオンを実施してください。

## 開発環境構築

### 0. 環境

* Java 11 以降
* Git クライアント
* Maven 3.0.4以降
* Chrome(ハンズオン1～11で使用します。IEでも実施可能ですが、レイアウトが崩れることがあります)

### 1. リポジトリの作成
以下のGitリポジトリを作成してください。
ローカルで動作させるだけの場合は適切なブランチのZipをダウンロードし、ディレクトリに展開するだけでもかまいません。

    $git clone -b master http://nablarch.intra.tis.co.jp/gitbucket/git/nablarch-handson/nablarch-handson.git


### 2. mavenのプロキシ設定
ネットワークにプロキシが存在する場合は、mavenのsetting.xmlを修正し、適切にプロキシの設定をしてください。

### 3. データベース、エンティティクラスの生成

本ハンズオン教材では、どのハンズオンでも共通しているデータベースやエンティティクラスをentityディレクトリ配下に切りだし、1つのプロジェクトとしてまとめています。以下のコマンドを実行し、データベースのセットアップ、及びエンティティクラスの生成を行ってください。

    $cd <チェックアウトディレクトリ>
    $cd entity
    $mvn clean install

DBは、組み込みDBのH2を使用しています。
データファイルは、以下のファイルです。

- <チェックアウトディレクトリ>/entity/h2/db/nablarch_example.*.db

### 4. ウェブアプリケーション共通ライブラリの準備

本ハンズオン教材では、ハンズオン01～11で共通している資源をウェブアプリケーション共通ライブラリとしてnablarch-handson-app-web-commonディレクトリ配下に切りだし、1つのプロジェクトとしてまとめています。以下のコマンドを実行し、ウェブアプリケーション共通ライブラリの生成を行ってください。

    $cd <チェックアウトディレクトリ>
    $cd nablarch-handson-app-web-common
    $mvn clean install




## IDEへの取り込み

本コンテンツは maven プロジェクトですので、IDEにmavenプロジェクトとしてインポートしてください。

## 補足

- ハンズオン01～11については、ホットデプロイが有効になっていますので、クラスのコンパイルを行えば、アプリの再起動をしなくても変更がすぐに反映されます。

## ライセンス
Nablarch 学習コンテンツはApache License 2.0 で提供します。
Apache License 2.0 の詳細については、[LICENSE](./LICENSE)を参照してください。
