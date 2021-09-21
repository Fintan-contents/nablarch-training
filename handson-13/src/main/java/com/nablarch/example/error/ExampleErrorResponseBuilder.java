package com.nablarch.example.error;

import nablarch.common.dao.NoDataException;
import nablarch.fw.ExecutionContext;
import nablarch.fw.jaxrs.ErrorResponseBuilder;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpResponse;

import javax.persistence.OptimisticLockException;

/**
 * Example用のエラーレスポンス生成クラス。
 *
 * @author Nabu Rakutaro
 */
public class ExampleErrorResponseBuilder extends ErrorResponseBuilder {

    /**
     * エラーレスポンスを生成する。
     * <p/>
     * 発生したエラーが{@link NoDataException}の場合は{@code 404}、
     * {@link OptimisticLockException}の場合は{@code 409}を生成する。
     * それ以外のエラーの場合には、上位クラスに処理を委譲する。
     *
     * @param request {@link HttpRequest}
     * @param context {@link ExecutionContext}
     * @param throwable 発生したエラーの情報
     * @return エラーレスポンス
     */
    @Override
    public HttpResponse build(HttpRequest request, ExecutionContext context, Throwable throwable) {
        final HttpResponse response;
        if (throwable instanceof NoDataException) {
            response = new HttpResponse(404);
        } else if (throwable instanceof OptimisticLockException) {
            response = new HttpResponse(409);
        } else {
            response = super.build(request, context, throwable);
        }
        return response;
    }
}
