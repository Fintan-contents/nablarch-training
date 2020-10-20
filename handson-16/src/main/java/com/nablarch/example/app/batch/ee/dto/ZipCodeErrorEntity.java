package com.nablarch.example.app.batch.ee.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 郵便番号情報の退避先テーブルに対応するEntityクラス。
 *
 * @author Nabu Rakutaro
 */
@Entity
@Table(name = "ZIP_CODE_DATA_ERROR")
public class ZipCodeErrorEntity extends ZipCodeDto {
}

