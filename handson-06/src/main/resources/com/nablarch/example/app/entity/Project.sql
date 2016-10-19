SEARCH_PROJECT_BY_TYPE =
SELECT
    PROJECT_ID,
    PROJECT_NAME,
    PROJECT_TYPE,
    PROJECT_CLASS,
    PROJECT_START_DATE,
    PROJECT_END_DATE,
    VERSION
FROM
    PROJECT
-- handson-06
-- WHERE句を記述して、Java側からprojectTypeというパラメータ名で値を渡して、PROJECT_TYPEカラムの値と一致するレコードのみ取得するように実装してください。
-- 実装方法は、Nablarchアプリケーションフレームワークの解説書の「Beanオブジェクトを入力としてSQLを実行する」を参照してください。
ORDER BY
    PROJECT_ID