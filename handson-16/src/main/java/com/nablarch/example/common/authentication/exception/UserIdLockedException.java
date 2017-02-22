package com.nablarch.example.common.authentication.exception;


import nablarch.core.util.annotation.Published;

/**
 * ユーザの認証時にユーザIDがロックされている場合に発生する例外。
 * <p/>
 * 対象ユーザのユーザIDとユーザIDをロックする認証失敗回数を保持する。
 * @author Nabu Rakutaro
 */
@Published
public class UserIdLockedException extends AuthenticationException {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** ユーザID */
    private String userId;

    /** ユーザIDをロックする認証失敗回数 */
    private int failedCountToLock;

    /**
     * コンストラクタ。
     * @param userId ユーザID
     * @param failedCountToLock ユーザIDをロックする認証失敗回数
     */
    public UserIdLockedException(String userId, int failedCountToLock) {
        this.userId = userId;
        this.failedCountToLock = failedCountToLock;
    }

    /**
     * ユーザIDを取得する。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDをロックする認証失敗回数を取得する。
     * @return ユーザIDをロックする認証失敗回数
     */
    public int getFailedCountToLock() {
        return failedCountToLock;
    }
}
