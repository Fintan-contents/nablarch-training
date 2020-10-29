package com.nablarch.example.action;

import com.nablarch.example.app.entity.Item;
import com.nablarch.example.dto.ItemSearchDto;
import com.nablarch.example.form.ItemForm;
import com.nablarch.example.form.ItemSearchForm;
import com.nablarch.example.form.ItemUpdateForm;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpResponse;

import java.util.List;

/**
 * 商品検索・登録・更新機能。
 *
 * @author Nabu Rakutaro
 */

// handson-13 step1
// このアクションクラスがパス「/items」と紐づくよう、アノテーションを付与してください。
public class ItemAction {

    /**
     * 商品情報を検索する。
     *
     * @param req HTTPリクエスト
     * @return 商品情報リスト
     */
    // handson-13 step2
    // findメソッドとHTTPメソッドの「GET」が紐づくよう、アノテーションを付与してください。
    // handson-13 step3
    // レスポンスメディアタイプを「JSON」として、アノテーションを付与してください。
    public List<Item> find(HttpRequest req) {

        // 検索条件を設定するための変数
        ItemSearchForm form = null;

        // handson-13 step4
        // 検索条件を取得する為に、form変数に、リクエストパラメータを設定してください。
        // 以前のエクササイズでも使用した、BeanUtilクラスを使用して実装してください。

        // handson-13 step5
        // ValidatorUtilクラスを用いてform変数の精査を実行してください。

        // 検索条件Dto
        ItemSearchDto searchCondition = null;

        // handson-13 step6
        // 検索条件として使用するために、searchCondition変数にform変数のプロパティを設定してください。
        // 以前のエクササイズでも使用した、BeanUtilクラスを使用して実装してください。

        // handson-13 step7
        // ユニバーサルDAOを使用して、検索用のSQLを呼び出し、レコードを取得して返却してください。
        // ・使用するエンティティクラスはItemです。
        // ・SQL_IDはItem.sqlを参照してください。

        return null;
    }

    /**
     * 商品情報を登録する。
     * @param form 商品情報
     * @return HTTPレスポンス
     */
    // handson-13 step8
    // saveメソッドとHTTPメソッドの「POST」が紐づくよう、アノテーションを付与してください。
    // handson-13 step9
    // リクエストメディアタイプを「JSON」として、アノテーションを付与してください。
    // handson-13 step10
    // リクエストに対するBeanValidationの実行を指示するアノテーションを付与してください。
    public HttpResponse save(ItemForm form) {
        // handson-13 step11
        // form変数をItemエンティティにコピーしてください。
        // 以前のエクササイズでも使用した、BeanUtilクラスを使用して実装してください。

        // handson-13 step12
        // ユニバーサルDAOを使用して、コピーしたItemエンティティをDBに登録してください。

        // handson-13 step13
        // 「Created」に対応するステータスコードを返すよう修正してください。
        return new HttpResponse(HttpResponse.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }

    /**
     * 商品情報を更新する。
     *
     * @param form 商品情報
     * @return HTTPレスポンス
     */
    // handson-13 step14
    // updateメソッドとHTTPメソッドの「PUT」が紐づくよう、アノテーションを付与してください。
    // handson-13 step15
    // パスパラメータとして「itemId」(数値のみ)を取るよう、アノテーションを付与してください。
    // handson-13 step16
    // リクエストメディアタイプを「JSON」として、アノテーションを付与してください。
    // handson-13 step17
    // リクエストに対するBeanValidationの実行を指示するアノテーションを付与してください。
    public HttpResponse update(HttpRequest req, ItemUpdateForm form) {

        // パスパラメータを設定するための変数
        int id;
        // handson-13 step18
        // パスパラメータ「itemId」を取得し、変数idに代入してください。

        // handson-13 step19
        // 引数のform変数をItemエンティティにコピーしてください。
        // 以前のエクササイズでも使用した、BeanUtilクラスを使用して実装してください。

        // handson-13 step20
        // step19で作成したItemエンティティのitemIdプロパティに、step18で取得したidを設定してください。

        // handson-13 step21
        // ユニバーサルDAOを使用して、作成したItemエンティティでDBを更新してください。

        // handson-13 step22
        // 「OK」に対応するステータスコードを返すよう修正してください。
        return new HttpResponse(HttpResponse.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }
}
