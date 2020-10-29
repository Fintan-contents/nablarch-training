package com.nablarch.example.action;

import com.nablarch.example.app.entity.Item;
import com.nablarch.example.dto.ItemSearchDto;
import com.nablarch.example.form.ItemForm;
import com.nablarch.example.form.ItemSearchForm;
import com.nablarch.example.form.ItemUpdateForm;
import nablarch.common.dao.UniversalDao;
import nablarch.core.beans.BeanUtil;
import nablarch.core.validation.ee.ValidatorUtil;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpResponse;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 商品検索・登録・更新機能。
 *
 * @author Nabu Rakutaro
 */
@Path("/items")
public class ItemAction {

    /**
     * 商品情報を検索する。
     *
     * @param req HTTPリクエスト
     * @return 商品情報リスト
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> find(HttpRequest req) {

        // リクエストパラメータをBeanに変換
        ItemSearchForm form = BeanUtil.createAndCopy(ItemSearchForm.class, req.getParamMap());

        // BeanValidation実行
        ValidatorUtil.validate(form);

        ItemSearchDto searchCondition = BeanUtil.createAndCopy(ItemSearchDto.class, form);
        return UniversalDao.findAllBySqlFile(Item.class, "FIND_ITEM", searchCondition);
    }

    /** 商品情報を登録する。
     *
     * @param form 商品情報
     * @return HTTPレスポンス
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Valid
    public HttpResponse save(ItemForm form) {
        UniversalDao.insert(BeanUtil.createAndCopy(Item.class, form));

        return new HttpResponse(HttpResponse.Status.CREATED.getStatusCode());
    }

    /**
     * 商品情報を更新する。
     *
     * @param form 商品情報
     * @return HTTPレスポンス
     */
    @PUT
    @Path("{itemId:\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Valid
    public HttpResponse update(HttpRequest req, ItemUpdateForm form) {

        int id = Integer.parseInt(req.getParam("itemId")[0]);
        Item item = BeanUtil.createAndCopy(Item.class, form);
        item.setItemId(id);

        UniversalDao.update(item);
        return new HttpResponse(HttpResponse.Status.OK.getStatusCode());
    }
}
