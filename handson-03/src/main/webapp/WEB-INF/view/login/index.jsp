<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="n" uri="http://tis.co.jp/nablarch" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- javascript --%>
        <n:script type="text/javascript" src="/javascripts/lib/jquery-3.7.1.min.js"></n:script>
        <n:script type="text/javascript" src="/javascripts/projectInput.js"></n:script>

        <title>ログイン</title>
    </head>

    <body>
        <div class="mainContents">
            <n:include path="/WEB-INF/view/common/header.jsp" />

            <div class="title-nav">
                <span>ログイン</span>
            </div>
            <%-- handson-03 form タグは Nablarch のカスタムタグがあるのでそちらを使用する。 --%>

                <div class="message-area margin-top">
                    <n:errors filter="global" cssClass="message-error"/>
                </div>
                <h2 class="font-group">ログイン情報</h2>
                <div class="row m-3">
                    <label for="loginId" class="col-md-2 m-auto col-form-label">ログインID</label>
                    <div class="col-md-10">
                        <%-- handson-03 input タグは Nablarch のカスタムタグがあるのでそちらを使用する 。--%>

                        <n:error errorCss="message-error" name="loginId" />
                    </div>
                </div>
                <div class="row m-3">
                    <label for="userPassword" class="col-md-2 m-auto col-form-label">パスワード</label>
                    <div class="col-md-10">
                        <%-- handson-03 input タグは Nablarch のカスタムタグがあるのでそちらを使用する 。--%>

                        <n:error errorCss="message-error" name="userPassword" />
                    </div>
                </div>

                <div class="title-nav page-footer" >
                    <div class="button-nav">
                        <div class="button-block real-button-block">
                            <%-- handson-03 button タグは Nablarch のカスタムタグがあるのでそちらを使用する 。--%>
                            <%-- 指定する uri は"/action/login"。--%>

                        </div>
                    </div>
                </div>

            <n:include path="/WEB-INF/view/common/footer.jsp" />
        </div>
    </body>
</html>
