package com.nablarch.example.app.validation;

import nablarch.core.validation.ee.DomainManager;

/**
 * {@link DomainManager}の実装クラス。
 */
public class DomainBeanManager implements DomainManager<DomainBean> {

    @Override
    public Class<DomainBean> getDomainBean() {
        return DomainBean.class;
    }
}
