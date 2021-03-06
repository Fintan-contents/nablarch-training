-- SEARCH_PROJECTを実装します。
--
-- IN句は、以下のように記述します。
-- WHERE $if(projectClass) {PROJECT_CLASS IN (:projectClass[])}
--
-- 可変のORDERで、ソートに使用する項目を複数指定する場合は以下のように記述します。
-- $sort(sortId){(nameAsc PROJECT_NAME, PROJECT_ID)}

-------------------------------------------------------------------------------
-- 一括更新用プロジェクト検索
-------------------------------------------------------------------------------
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

