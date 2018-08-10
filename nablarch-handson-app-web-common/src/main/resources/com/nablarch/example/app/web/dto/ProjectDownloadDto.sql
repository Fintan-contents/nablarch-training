SEARCH_PROJECT =
SELECT
    PROJECT.PROJECT_ID,
    PROJECT.PROJECT_NAME,
    PROJECT.PROJECT_TYPE,
    PROJECT.PROJECT_CLASS,
    PROJECT.PROJECT_START_DATE,
    PROJECT.PROJECT_END_DATE,
    PROJECT.CLIENT_ID,
    PROJECT.PROJECT_MANAGER,
    PROJECT.PROJECT_LEADER,
    PROJECT.USER_ID,
    PROJECT.NOTE,
    PROJECT.SALES,
    PROJECT.COST_OF_GOODS_SOLD,
    PROJECT.SGA,
    PROJECT.ALLOCATION_OF_CORP_EXPENSES,
    CLIENT.CLIENT_NAME
FROM
    PROJECT INNER JOIN CLIENT ON PROJECT.CLIENT_ID = CLIENT.CLIENT_ID
WHERE
    PROJECT.USER_ID = :userId
    AND $if(clientId)     {PROJECT.CLIENT_ID = :clientId}
    AND $if(projectName) {PROJECT.PROJECT_NAME LIKE  :%projectName%}
    AND $if(projectType) {PROJECT.PROJECT_TYPE = :projectType}
    AND $if(projectClass) {PROJECT.PROJECT_CLASS IN (:projectClass[])}
    AND $if(projectStartDateBegin) {PROJECT.PROJECT_START_DATE >= :projectStartDateBegin}
    AND $if(projectStartDateEnd) {PROJECT.PROJECT_START_DATE <= :projectStartDateEnd}
    AND $if(projectEndDateBegin) {PROJECT.PROJECT_END_DATE >= :projectEndDateBegin}
    AND $if(projectEndDateEnd) {PROJECT.PROJECT_END_DATE <= :projectEndDateEnd}
$sort(sortId){
    (idAsc PROJECT.PROJECT_ID)
    (idDesc PROJECT.PROJECT_ID DESC)
    (nameAsc PROJECT.PROJECT_NAME, PROJECT.PROJECT_ID)
    (nameDesc PROJECT.PROJECT_NAME DESC, PROJECT.PROJECT_ID DESC)
    (startDateAsc PROJECT.PROJECT_START_DATE, PROJECT.PROJECT_ID)
    (startDateDesc PROJECT.PROJECT_START_DATE DESC, PROJECT.PROJECT_ID DESC)
    (endDateAsc PROJECT.PROJECT_END_DATE, PROJECT.PROJECT_ID)
    (endDateDesc PROJECT.PROJECT_END_DATE DESC, PROJECT.PROJECT_ID DESC)
}