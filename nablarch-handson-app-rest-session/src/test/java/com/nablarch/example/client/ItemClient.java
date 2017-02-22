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

    /**
     * 商品情報の操作を行う。
     * @param args 引数
     */
    public static void main(String[] args) throws Exception {
        //ログイン処理(ログイン失敗)
        Response loginFailResponse = login("10000001", "hoge");
        System.out.println("start login(expect http status code 400.)");
        System.out.println("http status:" + loginFailResponse.getStatus());

        // 検索(未認証であるため失敗する)
        // 全件検索
        try {
            System.out.println("start getitem(expect http status code 401.)");
            System.out.print(makeDataString(ItemClient.getItems(null, null)));
        } catch (javax.ws.rs.NotAuthorizedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------");

        //ログイン処理(ログイン成功)
        Response loginSuccessResponse = login("10000001", "pass123-");
        System.out.println("start login(expect http status code 200.)");
        System.out.println("http status:" + loginSuccessResponse.getStatus());

        Map<String, NewCookie> loginSuccessCookies = loginSuccessResponse.getCookies();
        //セッション維持に必要な情報を持ったcookieを生成する。
        Cookie nablarchSid = new Cookie(loginSuccessCookies.get("NABLARCH_SID").getName(),
                loginSuccessCookies.get("NABLARCH_SID").getValue(),
                loginSuccessCookies.get("NABLARCH_SID").getPath(),
                loginSuccessCookies.get("NABLARCH_SID").getDomain());
        Cookie jsessionid = new Cookie(loginSuccessCookies.get("JSESSIONID").getName(),
                loginSuccessCookies.get("JSESSIONID").getValue(),
                loginSuccessCookies.get("JSESSIONID").getPath(),
                loginSuccessCookies.get("JSESSIONID").getDomain());
        
        // 検索
        // 全件検索
        System.out.println("start getitem(expect successful get data)");
        System.out.print(makeDataString(ItemClient.getItems(nablarchSid, jsessionid)));
    }


    /**
     * ログイン処理を行う
     * @return セッションクッキーを含むレスポンス。
     */
    private static Response login(String id, String password) {
        LoginForm form =new LoginForm();
        form.setLoginId(id);
        form.setUserPassword(password);
        return ClientBuilder.newClient()
                .target("http://localhost:9080/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(form));
    }

    
    /**
     * HTTP GETメソッドを使用したクライアント操作を行う。
     * @return 商品情報リスト
     */
    private static List<Item> getItems(Cookie nablarchSid, Cookie jsessionid) {
        Invocation.Builder invocationBuilder = ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON);

        if (nablarchSid != null) {
            invocationBuilder.cookie(nablarchSid);
        }
        if (jsessionid != null) {
            invocationBuilder.cookie(jsessionid);
        }
        
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
