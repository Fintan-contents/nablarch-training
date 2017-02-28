package com.nablarch.example.handler.login;

import java.util.Objects;

import nablarch.fw.ExecutionContext;
import nablarch.fw.Handler;
import nablarch.fw.web.HttpRequest;

/**
 * ログイン状態チェックハンドラ 。
 *
 * @author Nabu Rakutaro
 */
public class LoginUserPrincipalCheckHandler implements Handler<HttpRequest, Object> {

    /**
     * 認証/認可のチェックを行なう。
     *
     * @param request リクエストデータ
     * @param context 実行コンテキスト
     * @return HTTPレスポンス
     */
    @Override
    public Object handle(HttpRequest request, ExecutionContext context) {
        Object userContext = null;
        //handson-16 step11
        // セッションからユーザ情報オブジェクトを取り出します。
        // キーは「userContext」です。
        //
        // セッションにオブジェクトが格納されていない場合もあります。
        // その場合に例外が送出されないメソッドを使用して、オブジェクトを取り出してください。

        if (userContext == null
                && !Objects.equals(request.getRequestPath(), "/login")) {
            //未認証エラー
            
            //handson-16 step12
            // com.nablarch.example.handler.login.UnauthenticationExceptionを送出してください。
            // UnauthenticationExceptionのコンストラクタについて、引数が無しのコンストラクタを使用してください。
        }
        
        //handson-16 step13
        // 後続のハンドラを呼び出してください。
        return null;
    }
}
