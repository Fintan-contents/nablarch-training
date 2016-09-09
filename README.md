Nablarch 研修コンテンツ
===========================
Nablach Application Frameworkの研修用コンテンツです。
Nablarch について体系的に学ぶことができます。

## 研修コンテンツ実施手順

### 1. 研修環境を構築する。

自習をするための環境を準備します。
後述する環境設定手順に従って環境を構築しましょう。

### 2. ハンズオンの内容を理解する。

自習教材として以下のハンズオンを取り揃えています。
handson-01から順番に実施することで Nablarch を体系的に学習することができます。

| No                         | レベル | ハンズオン| 説明 |
|:---------------------------|:-------|:----------------------|:--------|
| [01](../../../exercise-01) | 入門   | 画面表示してみよう | JSPの作成と作成したJSPを画面表示する方法を学びます。 |
| [02](../../../exercise-02) | 入門   | データベースから情報を取得して画面表示してみよう | データベースからデータを取得する方法と取得した情報を画面に表示する方法を学びます。 |
| [03](../../../exercise-03) | 入門   | 画面からの入力値を受け取って確認画面を表示してみよう | 画面で入力した値の受取り方や入力値を精査する方法を学びます。合わせて、エラーが発生した場合の画面遷移方法も学習することができます。 |
| [04](../../../exercise-04) | 入門   | 入力した情報を復元して入力画面に戻ってみよう | 画面入力した情報を復元する方法を学びます。 |
| [05](../../../exercise-05) | 入門   | データベースを更新してみよう | データベースを更新する方法を学びます。 |
| [06](../../../exercise-06) | 入門   | 一覧画面を表示してみよう | データベースから一覧の情報を取得し、その情報を画面に表示する方法を学びます。 |
| [07](../../../exercise-07) | 入門   | 画面から検索条件を受け取って検索結果を表示しよう | 画面で入力された条件に一致する情報をデータベースから取得し、一覧表示する方法を学びます。 |
| [08](../../../exercise-08) | 入門   | JUnitを使用した単体テストをしよう | アプリケーションプログラマーが実施する単体テスト（クラス単体テスト）を実施する方法を学びます。 |
| [09](../../../exercise-09) | 基本   | 登録画面を作ろう | マスタメンテでよく扱う登録処理の作り方を学びます。 |
| [10](../../../exercise-10) | 基本   | 更新・削除画面を作ろう | マスタメンテでよく扱う更新・削除処理の作り方を学びます。 |
| [11](../../../exercise-11) | 基本   | 検索画面と詳細画面を作ろう | マスタメンテでよく扱う、検索処理と詳細画面の作り方を学びます。 |
| [12](./handson-12/README.md) | 基本   | Batchletを作ってみよう | JSR352に準拠したバッチアプリケーションの一つであるBatchletの作り方を学びます。 |
| [13](./handson-13/README.md) | 基本   | Chunkタイプのバッチを作ってみよう | JSR352に準拠したバッチアプリケーションの一つであるChunkの作り方を学びます。 |
| [14](./handson-14/README.md) | 基本   | ETLを使って、CSVファイルの内容をDBに登録してみよう | ETLを利用したバッチアプリケーションでCSVファイルの内容をDBに登録する方法を学びます。 |

### 3. ハンズオンを実施する。

各々の説明に従ってハンズオンを実施してください。

## 開発環境構築

### 0. 環境

* Java 7 以降
* Git クライアント
* Maven 3.0.4以降

### 1. リポジトリの作成
以下のGitリポジトリを作成してください。
ローカルで動作させるだけの場合は適切なブランチのZipをダウンロードし、ディレクトリに展開するだけでもかまいません。

    $git clone -b master http://nablarch.intra.tis.co.jp/gitbucket/git/nablarch-handson/nablarch-handson.git

### 2. mavenのリポジトリ設定
指示があった場合は、各ディレクトリに存在するpom.xmlに記載されているMavenリポジトリのURLを書き換えてください。

#### 修正前の例

```xml
  <project>
    ・・・
      <repositories>
        <repository>
          <id>nablarch-handson-staging</id>
          <!-- MavenリポジトリのURL -->
          <url>http://nablarch.intra.tis.co.jp/repository/staging</url>
          ・・・
        </repository>
      </repositories>

       <pluginRepositories>
         <pluginRepository>
          <id>nablarch-handson-staging</id>
          <!-- MavenリポジトリのURL -->
          <url>http://nablarch.intra.tis.co.jp/repository/staging</url>
          ・・・
        </pluginRepository>
      </pluginRepositories>
    ・・・
  </project>
```

#### 修正後の例

```xml
  <project>
    ・・・
      <repositories>
        <repository>
          <id>nablarch-handson-staging</id>
          <!-- MavenリポジトリのURL -->
          <url>http://192.168.0.10:10080/repository/staging</url>
          ・・・
        </repository>
      </repositories>

       <pluginRepositories>
         <pluginRepository>
          <id>nablarch-handson-staging</id>
          <!-- MavenリポジトリのURL -->
          <url>http://192.168.0.10:10080/repository/staging</url>
          ・・・
        </pluginRepository>
      </pluginRepositories>
    ・・・
  </project>
```


### 3. mavenのプロキシ設定
ネットワークにプロキシが存在する場合は、mavenのsetting.xmlを修正し、ハンズオン用のmavenリポジトリについて、プロキシを経由しないように除外設定してください。

#### TIS(東京地区)のプロキシ設定の例

```xml
  <settings>
    ・・・
    <!-- プロキシ情報 -->
    <proxies>
      <proxy>
        <active>true</active>
        <protocol>http</protocol>
        <host>tkyproxy.intra.tis.co.jp</host>
        <port>8080</port>
        <!-- プロキシを経由しないホスト -->
        <nonProxyHosts>localhost|127.0.0.1|nablarch.intra.tis.co.jp</nonProxyHosts>
      </proxy>
      <proxy>
        <active>true</active>
        <protocol>https</protocol>
        <host>tkyproxy.intra.tis.co.jp</host>
        <port>8080</port>
        <!-- プロキシを経由しないホスト -->
        <nonProxyHosts>localhost|127.0.0.1|nablarch.intra.tis.co.jp</nonProxyHosts>
      </proxy>
    </proxies>
    ・・・
  </settings>
```

### 4. データベース、エンティティクラスの生成

本ハンズオン教材では、どのハンズオンでも共通しているデータベースやエンティティクラスをentityディレクトリ配下に切りだし、1つのプロジェクトとしてまとめています。以下のコマンドを実行し、データベースのセットアップ、及びエンティティクラスの生成を行ってください。

    $cd <チェックアウトディレクトリ>
    $cd entity
    $mvn clean install

DBは、組み込みDBのH2を使用しています。
データファイルは、以下のファイルです。

- <チェックアウトディレクトリ>/entity/h2/db/nablarch_example.*.db

### 5. 動作確認

`mvn waitt:run`を実行し、http://localhost:9080 にアクセスしてください。ログイン後、正常に「プロジェクト検索画面」が表示されることを確認してください。
ログイン時に利用できるユーザは以下の通りです。

| ユーザ名 | パスワード |
|:------|:--------|
| 10000001 | pass123- |

## IDEへの取り込み

本コンテンツは maven プロジェクトですので、IDEにmavenプロジェクトとしてインポートしてください。

以下にEclipseに取り込む方法を記載します。<br />
### 1. 必要プラグインのインストール<br />
m2eプラグインをインストールして下さい（最初からこのプラグインが入っているEclipseのバージョンもありますので、存在を確認してから導入してください）。

### 2. プロジェクトのインポート<br />
プロジェクト・エクスプローラ右クリック > インポート > Existing Maven Project<br />
クローンしたリポジトリをルート・ディレクトリとし完了を押してください。


## 補足

- 各ハンズオンについて、ホットデプロイが有効になっていますので、アプリの再起動をしなくても変更がすぐに反映されます。
- 題材にしているアプリの概要については、[doc/spec.md](./doc/spec.md)を参照してください。
- Nablarchでアプリを開発する際の責務配置については、[doc/responsibility.md](./doc/responsibility.md)を参照してください。
