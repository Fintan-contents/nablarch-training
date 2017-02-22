package com.nablarch.example.action;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import com.nablarch.example.app.entity.SystemAccount;
import com.nablarch.example.app.entity.Users;
import com.nablarch.example.common.authentication.AuthenticationUtil;
import com.nablarch.example.common.authentication.context.LoginUserPrincipal;
import com.nablarch.example.common.authentication.exception.AuthenticationException;
import com.nablarch.example.form.LoginForm;

import nablarch.common.dao.UniversalDao;
import nablarch.core.util.annotation.Published;
import nablarch.fw.web.HttpResponse;

/**
 * 認証アクション。
 * <pre>
 * システム利用者の認証を行う。
 * ログインの機能を有する。
 * </pre>
 * <pre>
 * 補足：ログアウトの機能については、研修で扱わないため割愛。
 * </pre>
 * @author Nabu Rakutaro
 */
@Published
public class AuthenticationAction {

    @Consumes(MediaType.APPLICATION_JSON)
    @Valid
    //handson-16 step7 引数にExecutionContextを追加します。
    public HttpResponse login(LoginForm form) {
        try {
            AuthenticationUtil.authenticate(form.getLoginId(), form.getUserPassword());
        } catch (AuthenticationException ignore) {
            // パスワード不一致、その他認証エラー（ユーザーが存在しない等）

            //handson-16 step8 ApplicationExceptionを送出してください。
            //ApplicationExceptionのコンストラクタについて、引数が無しのコンストラクタを使用してください。
        }

        
        //認証に成功したらセッションにオブジェクトを詰めてステータスコード200を返す。
        LoginUserPrincipal userContext = createLoginUserContext(form.getLoginId());
        
        //handson-16 step9
        //セッションを無効化したのちに、
        //セッションに、"userContext"をキーとしてuserContextオブジェクトを登録します。

        //handson-16 step10
        //ステータスコード200を返却します。
        return null;
    }

    /**
     *認証情報取得。
     *
     * @param loginId ログインID
     * @return 認証情報
     */
    private LoginUserPrincipal createLoginUserContext(String loginId) {
        SystemAccount account = UniversalDao
                .findBySqlFile(SystemAccount.class,
                        "FIND_SYSTEM_ACCOUNT_BY_AK", new Object[]{loginId});
        Users users = UniversalDao.findById(Users.class, account.getUserId());

        LoginUserPrincipal userContext = new LoginUserPrincipal();
        userContext.setUserId(account.getUserId());
        userContext.setKanjiName(users.getKanjiName());
        userContext.setLastLoginDateTime(account.getLastLoginDateTime());

        return userContext;

    }
}
