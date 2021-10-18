<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="n" uri="http://tis.co.jp/nablarch" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- javascript --%>
        <n:script type="text/javascript" src="/javascripts/lib/jquery-1.11.2.min.js"></n:script>
        <n:script type="text/javascript" src="/javascripts/projectInput.js"></n:script>
        <%-- stylesheet --%>
        <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
        <link rel="stylesheet" href="//fonts.googleapis.com/icon?family=Material+Icons">

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
                <div class="form-group">
                    <label for="loginId" class="col-md-2 control-label">ログインID</label>
                    <div class="col-md-10">
                        <%-- handson-03 input タグは Nablarch のカスタムタグがあるのでそちらを使用する 。--%>

                        <n:error errorCss="message-error" name="loginId" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="userPassword" class="col-md-2 control-label">パスワード</label>
                    <div class="col-md-10">
                        <%-- handson-03 input タグは Nablarch のカスタムタグがあるのでそちらを使用する 。--%>

                        <n:error errorCss="message-error" name="userPassword" />
                    </div>
                </div>

                <div class="title-nav page-footer" >
                    <div class="button-nav">
                        <div class="button-block real-button-block" style="float:right">
                            <%-- handson-03 button タグは Nablarch のカスタムタグがあるのでそちらを使用する 。--%>
                            <%-- 指定する uri は"/action/login"。--%>

                        </div>
                    </div>
                </div>

            <n:include path="/WEB-INF/view/common/footer.jsp" />
        </div>
    </body>
</html>
