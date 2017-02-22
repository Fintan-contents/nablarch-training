package com.nablarch.example.handler.login;

import nablarch.core.message.ApplicationException;
import nablarch.core.util.annotation.Published;

/**
 * ユーザが未認証の場合に発生する例外。<br>
 * @author Nabu Rakutaro
 */
@Published
public class UnauthenticationException extends ApplicationException {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

}
