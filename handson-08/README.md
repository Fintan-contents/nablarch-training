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

#### データベース・エンティティクラスの準備
本ハンズオンを開始する前にデータベースの作成及びエンティティクラスの生成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd entity
    $mvn clean install

#### ウェブアプリケーション共通ライブラリの準備
本ハンズオンを開始する前にウェブアプリケーション共通ライブラリの作成を行っていない(以下のコマンドを実行していない)場合、チェックアウトディレクトリに移動し、以下のコマンドを実行してください。

    $cd nablarch-handson-app-web-common
    $mvn clean install

## 演習内容に関するリファレンスマニュアル
本演習中に実装方法で不明な点が存在した場合は、以下のドキュメントを参照してください。

### 解説書

#### テスティングフレームワークの解説書
- [3.2.1. 自動テストフレームワーク](https://nablarch.github.io/docs/5u21/doc/development_tools/testing_framework/guide/development_guide/06_TestFWGuide/01_Abstract.html#auto-test-framework)
	- [テストメソッド記述方法](https://nablarch.github.io/docs/5u21/doc/development_tools/testing_framework/guide/development_guide/06_TestFWGuide/01_Abstract.html#id5)
	- [Excelによるテストデータ記述](https://nablarch.github.io/docs/5u21/doc/development_tools/testing_framework/guide/development_guide/06_TestFWGuide/01_Abstract.html#excel)
	  - [命名規約](https://nablarch.github.io/docs/5u21/doc/development_tools/testing_framework/guide/development_guide/06_TestFWGuide/01_Abstract.html#id6)
- [3.2.9. 目的別API使用方法](https://nablarch.github.io/docs/5u21/doc/development_tools/testing_framework/guide/development_guide/06_TestFWGuide/03_Tips.html#api)
	- [Excelファイルから、入力パラメータや戻り値に対する期待値などを取得したい](https://nablarch.github.io/docs/5u21/doc/development_tools/testing_framework/guide/development_guide/06_TestFWGuide/03_Tips.html#excel)
	- [同じテストメソッドをテストデータを変えて実行したい](https://nablarch.github.io/docs/5u21/doc/development_tools/testing_framework/guide/development_guide/06_TestFWGuide/03_Tips.html#how-to-run-the-same-test)

## 演習
では、自動テストを実装しましょう。

### 1. ProjectForm.java に対するテストを作成する。
[ProjectForm.java](./src/main/java/com/nablarch/example/app/web/form/ProjectForm.java) のテストクラスは、[ProjectFormTest.java](./src/test/java/com/nablarch/example/app/web/form/ProjectFormTest.java) です。
[ProjectFormTest.java](./src/test/java/com/nablarch/example/app/web/form/ProjectFormTest.java) を参照して自動テストを作成してみましょう。
テストメソッドのJavadocに「【handson-08】 stepX」と記載してありますので、step1から順にハンズオンを実施してみてください。


## 動作確認方法
JUnit を実行して動作確認を行います。

コマンドプロンプトで「チェックアウトディレクトリ/handson-08」に移動し、以下のコマンドを実行します。

    $mvn test -Dtest=ProjectFormTest

以下の2点を確認してください。
- 以下のようにBUILD SUCCESS と表示されていること  
    ```
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------
    ```
- テスト結果が以下のように表示されること  
    ```
        Results :
        
        Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
    ```

