SEARCH_PROJECT =
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
-- handson-07 step1
-- WHERE句を追加したうえで、以下の検索条件を追加してください。
-- USER_ID = :userId
--
-- 以下の検索条件について、「Java側から値が設定されていたら、AND検索する」という実装を行ってください。
-- 括弧内が検索条件です。
-- {CLIENT_ID = :clientId}
-- {PROJECT_NAME LIKE :%projectName%}
-- {PROJECT_TYPE = :projectType}
-- {PROJECT_CLASS IN (:projectClass[])}
-- {PROJECT_START_DATE >= :projectStartDateBegin}
-- {PROJECT_START_DATE <= :projectStartDateEnd}
-- {PROJECT_END_DATE >= :projectEndDateBegin}
-- {PROJECT_END_DATE <= :projectEndDateEnd}
--
-- 「Java側から値が設定されていたら」という要件を満たすためには、$if構文を使用してください。
-- $if構文の使用方法は、Nablarchアプリケーションフレームワークの解説書の「可変条件を持つSQLを実行する」を参照してください。

-- handson-07 step2
-- 可変ORDER BY句を実装して、以下の条件について、Java側から指定した条件でソートできるようにしてください。
-- 括弧内の左側がJavaから渡す値、右側がORDER BY句で指定するソート条件です。
--    (idAsc PROJECT_ID)
--    (idDesc PROJECT_ID DESC)
--    (nameAsc PROJECT_NAME, PROJECT_ID)
--    (nameDesc PROJECT_NAME DESC, PROJECT_ID DESC)
--    (startDateAsc PROJECT_START_DATE, PROJECT_ID)
--    (startDateDesc PROJECT_START_DATE DESC, PROJECT_ID DESC)
--    (endDateAsc PROJECT_END_DATE, PROJECT_ID)
--    (endDateDesc PROJECT_END_DATE DESC, PROJECT_ID DESC)
--
-- Java側からは、sortIdというパラメータで検索条件を渡します。
-- 可変ORDER BY句の実現には、$sort構文を使用してください。
-- $sort構文の使用方法は、Nablarchアプリケーションフレームワークの解説書の「order byのソート項目を実行時に動的に切り替えてSQLを実行する」を参照してください

SEARCH_PROJECT_FOR_BULK_UPDATE =
SELECT
    *
FROM
    PROJECT
WHERE
    USER_ID = :userId
    AND $if(clientId)     {CLIENT_ID = :clientId}
    AND $if(projectName) {PROJECT_NAME LIKE  :%projectName%}
    AND $if(projectType) {PROJECT_TYPE = :projectType}
    AND $if(projectClass) {PROJECT_CLASS IN (:projectClass[])}
    AND $if(projectStartDateBegin) {PROJECT_START_DATE >= :projectStartDateBegin}
    AND $if(projectStartDateEnd) {PROJECT_START_DATE <= :projectStartDateEnd}
    AND $if(projectEndDateBegin) {PROJECT_END_DATE >= :projectEndDateBegin}
    AND $if(projectEndDateEnd) {PROJECT_END_DATE <= :projectEndDateEnd}
$sort(sortId){
    (idAsc PROJECT_ID)
    (idDesc PROJECT_ID DESC)
    (nameAsc PROJECT_NAME, PROJECT_ID)
    (nameDesc PROJECT_NAME DESC, PROJECT_ID DESC)
    (startDateAsc PROJECT_START_DATE, PROJECT_ID)
    (startDateDesc PROJECT_START_DATE DESC, PROJECT_ID DESC)
    (endDateAsc PROJECT_END_DATE, PROJECT_ID)
    (endDateDesc PROJECT_END_DATE DESC, PROJECT_ID DESC)
}

