package com.nablarch.example.client;

import com.nablarch.example.app.entity.Item;
import com.nablarch.example.form.ItemForm;
import com.nablarch.example.form.ItemUpdateForm;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ItemClient {

    private static final String targetUrl = "http://localhost:9080/items";

    /**
     * 商品情報の操作を行う。
     * @param args 引数
     */
    public static void main(String[] args) throws Exception {

        // 検索
        // 全件検索
        System.out.print(makeDataString(ItemClient.getItems()));
        // 指定条件検索
        String searchParam = "?category=hardware";
        System.out.print(makeDataString(getItems(searchParam)));

        // 登録
        ItemForm item = createInsertItem();
        System.out.println("insert status:" + postItem(item));
        System.out.print(makeDataString(ItemClient.getItems()));

        // 更新対象プロジェクト取得
        String updateSearchParam = "?itemName=商品９９９";
        Item updateItem = getItems(updateSearchParam).get(0);
        ItemUpdateForm updateForm = setUpdateItem(updateItem);

        // 更新
        System.out.println("update status:" + putItem(updateForm));
        System.out.print(makeDataString(ItemClient.getItems()));
    }

    /**
     * 登録用商品情報生成
     * @return 登録用商品情報
     */
    private static ItemForm createInsertItem() {
        ItemForm form = new ItemForm();
        form.setItemName("商品９９９");
        form.setCategory("hardware");
        form.setExplanation("商品９９９の説明");
        form.setPrice("20000");
        return form;
    }

    /**
     * 更新用商品情報設定
     * @param item 更新対象商品情報
     * @return 更新用商品情報
     */
    private static ItemUpdateForm setUpdateItem(Item item) {
        ItemUpdateForm form = new ItemUpdateForm();
        form.setItemId(item.getItemId().toString());
        form.setItemName("商品８８８");
        form.setCategory("software");
        form.setExplanation("商品８８８の説明");
        form.setPrice("18000");

        return form;
    }

    /**
     * HTTP GETメソッドを使用したクライアント操作を行う。
     * @return 商品情報リスト
     */
    private static List<Item> getItems() {
        return ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Item>>() {});
    }

    /**
     * HTTP GETメソッドを使用したクライアント操作を行う。
     * @param param 検索条件パラメータ
     * @return 商品情報リスト
     */
    private static List<Item> getItems(String param) throws UnsupportedEncodingException {

        return ClientBuilder.newClient()
                .target(targetUrl + param)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Item>>() {});
    }

    /**
     * HTTP POSTメソッドを使用したクライアント操作を行う。
     * @param item 登録用商品情報
     * @return ステータスコード
     */
    private static Integer postItem(ItemForm item) {
        return ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(item)).getStatus();
    }

    /**
     * HTTP PUTメソッドを使用したクライアント操作を行う。
     * @param item 更新用商品情報
     * @return ステータスコード
     */
    private static Integer putItem(ItemUpdateForm item) {
        return ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(item)).getStatus();
    }

    /**
     * 商品情報の文字列変換を行う。
     * @param items 商品情報List
     * @return 商品情報
     */
    private static String makeDataString(List<Item> items) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("---- items (size: %s) ----", items.size())).append('\n');
        for (Item item : items) {
            sb.append(String.format("Item(ItemId: %s, ItemName: %s, Category: %s, Explanation: %s, Price: %s)",
                    item.getItemId(), item.getItemName(), item.getCategory(), item.getExplanation(), item.getPrice())).append('\n');
        }
        return sb.toString();
    }
}
