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
import nablarch.common.web.session.SessionUtil;
import nablarch.core.message.ApplicationException;
import nablarch.core.util.annotation.Published;
import nablarch.fw.ExecutionContext;
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
    public HttpResponse login(LoginForm form, ExecutionContext context) {
        try {
            AuthenticationUtil.authenticate(form.getLoginId(), form.getUserPassword());
        } catch (AuthenticationException ignore) {
            // パスワード不一致、その他認証エラー（ユーザーが存在しない等）
            throw new ApplicationException();
        }

        
        //認証に成功したらセッションにオブジェクトを詰めてステータスコード200を返す。
        LoginUserPrincipal userContext = createLoginUserContext(form.getLoginId());
        
        SessionUtil.invalidate(context);
        SessionUtil.put(context, "userContext", userContext, "httpSession");

        return new HttpResponse(HttpResponse.Status.OK.getStatusCode());
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
