JUnitを使用した単体テストをしよう
===========================

## 演習内容
Nablarchの自動テストフレームワークは、JUnit4をベースとしています。
各種アノテーション、assertメソッドやMatcherクラスなど、JUnit4で提供されている機能を使用しますが、テストデータをExcelファイルに記述できる点が大きな特徴です。
自動テストフレームワークのAPIを通じてテストを作成する方法を学習します。

## 作成する機能について

ProjectFormのテストクラスを完成させます。

## 演習を開始する為の準備

### 事前準備
本ハンズオンを開始する前にデータベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください(コンパイルのためにEntityが必要です)。

    $cd entity
    $mvn clean install

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### テスティングフレームワークの解説書
- 2.1. 自動テストフレームワーク
	- 2.1.3. テストメソッド記述方法
	- 2.1.4. Excelによるテストデータ記述
- 2.8. 目的別API使用方法
	- 2.8.1. Excelファイルから、入力パラメータや戻り値に対する期待値などを取得したい
	- 2.8.2. 同じテストメソッドをテストデータを変えて実行したい

## 演習
では、自動テストを実装しましょう。

### 1. ProjectForm.java に対するテストを作成する。
[ProjectForm.java](./src/main/java/com/nablarch/example/app/web/form/ProjectForm.java) のテストクラスは、[ProjectFormTest.java](./src/test/java/com/nablarch/example/app/web/form/ProjectFormTest.java) です。
[ProjectFormTest.java](./src/test/java/com/nablarch/example/app/web/form/ProjectFormTest.java) を修正して自動テストを完成させましょう。
テストメソッドのJavadocに「【handson-08】 stepX」と記載してありますので、step1から実装してみてください。


## 動作確認方法
JUnit を実行して動作確認を行います。

### Eclipseの場合
[ProjectFormTest.java] を右クリック → 実行 → JUnit テスト

※Eclipse上で実行した際に原因不明のエラーが出る場合、ワークスペース上のnablarch-handson08(handson-08のプロジェクト)以外を閉じた状態で実行してみてください。

### mavenの場合
コマンドプロンプトでhandson-08に移動し、以下のコマンドを実行します。

$mvn test -Dtest=ProjectFormTest
