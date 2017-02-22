package com.nablarch.example.handler.login;

import java.util.Objects;

import nablarch.common.web.session.SessionUtil;
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
        Object userContext = SessionUtil.orNull(context, "userContext");

        if (userContext == null
                && !Objects.equals(request.getRequestPath(), "/login")) {
            //未認証エラー
            throw new UnauthenticationException();
        }
        return context.handleNext(request);
    }
}
