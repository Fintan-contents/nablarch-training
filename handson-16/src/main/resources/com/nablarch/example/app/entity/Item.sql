FIND_ITEM =
SELECT
    *
FROM
    ITEM
WHERE
    $if(itemName)     {ITEM_NAME LIKE :%itemName%}
    AND $if(category) {CATEGORY = :category}
