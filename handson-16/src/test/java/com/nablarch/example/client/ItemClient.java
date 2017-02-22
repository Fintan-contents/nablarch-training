package com.nablarch.example.client;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.nablarch.example.app.entity.Item;
import com.nablarch.example.form.LoginForm;

public class ItemClient {

    private static final String targetUrl = "http://localhost:9080/items";
    private static Cookie nablarchSid;
    private static Cookie jsessionid;

    /**
     * 商品情報の操作を行う。
     * @param args 引数
     */
    public static void main(String[] args) throws Exception {
        //ログイン処理
        Response loginResponse = login();
        System.out.println("login http status:" + loginResponse.getStatus());
        Map<String, NewCookie> cookies = loginResponse.getCookies();
        //セッション維持に必要な情報を持ったcookieを生成する。
        nablarchSid = new Cookie(cookies.get("NABLARCH_SID").getName(),
                cookies.get("NABLARCH_SID").getValue(),
                cookies.get("NABLARCH_SID").getPath(),
                cookies.get("NABLARCH_SID").getDomain());
        jsessionid = new Cookie(cookies.get("JSESSIONID").getName(),
                cookies.get("JSESSIONID").getValue(),
                cookies.get("JSESSIONID").getPath(),
                cookies.get("JSESSIONID").getDomain());
        
        // 検索
        // 全件検索
        System.out.print(makeDataString(ItemClient.getItems()));
    }


    /**
     * ログイン処理を行う
     * @return セッションクッキーを含むレスポンス。
     */
    private static Response login() {
        LoginForm form =new LoginForm();
        form.setLoginId("10000001");
        form.setUserPassword("pass123-");
        return ClientBuilder.newClient()
                .target("http://localhost:9080/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(form));
    }

    
    /**
     * HTTP GETメソッドを使用したクライアント操作を行う。
     * @return 商品情報リスト
     */
    private static List<Item> getItems() {
        Invocation.Builder invocationBuilder = ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON)
                .cookie(nablarchSid)
                .cookie(jsessionid);
        return invocationBuilder.get(new GenericType<List<Item>>() {});
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
