package com.nablarch.example.app.web.form;

import nablarch.core.util.StringUtil;
import nablarch.test.core.db.DbAccessTestSupport;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * {@link ProjectForm} のテストクラス。
 *
 * @author Nabu Rakutaro
 */
/*
 * 本クラスはFormクラスなので、EntityTestSupportを継承するのが一般的である。
 * しかし本ハンズオンでは、コンポーネントクラスの単体テストの理解に主眼をおいているため、
 * あえてDbAccessTestSupportを継承している。
 */
public class ProjectFormTest extends DbAccessTestSupport {

    /** テスト対象クラス */
    private ProjectForm target = new ProjectForm();

    /**
     * 【handson-08】 step1 <br />
     * {@link ProjectForm#getGrossProfit()} のテスト。
     * <pre>
     * 【TODO】
     * 自動テストフレームワークを使用したクラス単体テストでは、以下の条件を満たすようにテストクラスおよびテストデータを
     * 記述したExcelファイルを作成します。
     * </pre>
     * <ul>
     * <li>テストクラスのパッケージは、テスト対象のクラスと同じパッケージとする。</li>
     * <li><クラス名>Testというクラス名でテストクラスを作成する。</li>
     * <li>テストクラスは {@link DbAccessTestSupport} を継承する。</li>
     * <li>Excelファイル名は、テストクラスと同じ名前にする（拡張子のみ異なる）。</li>
     * <li>Excelファイルを、テストクラスと同じディレクトリに配置する。</li>
     * </ul>
     * <pre>
     * 上記を確認して、本テストメソッドを実行できるようにしてください。
     * 変更後はJUnitを実行し、動作確認をしてみましょう。
     * 変更が適切であれば本テストメソッドは正常終了します。
     * ※このテストメソッドの内容は変更しません。
     * </pre>
     */
    @Test
    public final void 売上総利益の取得テスト() {
        String sheetName = "売上総利益の取得テスト";
        String params = "params";

        // ExcelファイルからList-Map形式でデータを取得するには、DbAccessTestSupport#getListMap(String, String) を使用します。
        // 引数は、シート名とidです。idは、Excelファイルに記述したデータを特定するためキーで、「LIST_MAP=XXXXX」形式で記述した
        // "XXXXX"の部分です。
        for (Map<String, String> param : getListMap(sheetName, params)) {
            setUpTargetForm(param);

            Long actual = target.getGrossProfit();
            assertThat("no=" + param.get("no"), actual, is(convertToLong(param.get("expected"))));
        }
    }

    /**
     * 【handson-08】 step2 <br />
     * {@link ProjectForm#getProfitBeforeAllocation()} のテスト。
     * <pre>
     * 【TODO】
     * 本テストメソッドは異常終了します。
     * JUnitの障害トレースを確認して修正してください。
     * </pre>
     */
    @Test
    public final void 配賦前利益の取得テスト() {
        for (Map<String, String> param : getListMap("", "params")) {
            setUpTargetForm(param);

            Long actual = target.getProfitBeforeAllocation();
            assertThat("no=" + param.get("no"), actual, is(convertToLong(param.get("expected"))));
        }
    }

    /**
     * 【handson-08】 step3 <br />
     * {@link ProjectForm#getProfitRateBeforeAllocation()} のテスト。
     * <pre>
     * 【TODO】
     * 本テストメソッドは正常終了しますが、実際は意図するテストが実施できていません。
     * メソッド内のコメントを外して再度実行してみてください。問題点がわかります。
     * 問題点を確認したら、適切にテストが実施されるよう変更してください。
     * </pre>
     */
    @Test
    public final void 配賦前利益率の取得テスト() {
        String sheetName = "配賦前利益率の取得テスト";
        String params = "params";
        int assertCount = 0;

        for (Map<String, String> param : getListMap(sheetName, params)) {
            setUpTargetForm(param);

            BigDecimal actual = target.getProfitRateBeforeAllocation();
            assertThat("no=" + param.get("no"), actual, is(convertToBigDecimal(param.get("expected"))));
            assertCount++;
        }
        // TODO: 下のコメントを外してテストを実行してください。
        // assertThat("本当にassertできていますか？", assertCount, is(9));
    }

    /**
     * 【handson-08】 step4 <br />
     * {@link ProjectForm#getOperatingProfit()} のテスト。
     * <pre>
     * 【TODO】
     * 本テストメソッドはアサートに失敗しています。Excelファイルのテストデータに誤りはないようです。
     * 原因を特定して修正を行ってください。
     * </pre>
     */
    @Test
    public final void 営業利益の取得テスト() {
        String sheetName = "営業利益の取得テスト";
        String params = "params";

        for (Map<String, String> param : getListMap(sheetName, params)) {
            target.setSales(param.get("sales"));
            target.setCostOfGoodsSold(param.get("costOfGoodsSold"));
            target.setSga(param.get("sag"));
            target.setAllocationOfCorpExpenses(param.get("allocationOfCorpExpenses"));

            Long actual = target.getOperatingProfit();
            assertThat("no=" + param.get("no"), actual, is(convertToLong(param.get("expected"))));
        }
    }

    /**
     * 【handson-08】 step5 <br />
     * {@link ProjectForm#getOperatingProfitRate()} のテスト。
     * <pre>
     * 【TODO】
     * このテストメソッドでは、テストデータを直書きしています。
     * ProjectFormTest.xlsx にシートを追加し、テストデータを全てExcelファイルに記述するよう変更してください。
     */
    @Test
    public final void 営業利益率の取得テスト() {
        // テストデータ -----------------------------------------
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("no", "1");
        map.put("sales", "987654321");
        map.put("costOfGoodsSold", "123456789");
        map.put("sga", "12345678");
        map.put("allocationOfCorpExpenses", "1234567");
        map.put("expected", "0.861");
        list.add(map);
        map.clear();
        map.put("no", "2");
        map.put("sales", "999999999");
        map.put("costOfGoodsSold", "0");
        map.put("sga", "0");
        map.put("allocationOfCorpExpenses", "0");
        map.put("expected", "1.000");
        list.add(map);
        map.clear();
        map.put("no", "3");
        map.put("sales", "0");
        map.put("costOfGoodsSold", "999999999");
        map.put("sga", "999999999");
        map.put("allocationOfCorpExpenses", "999999999");
        map.put("expected", "0.000");
        list.add(map);
        // ここまで --------------------------------------------
        for (Map<String, String> param : list) {
            setUpTargetForm(param);

            BigDecimal actual = target.getOperatingProfitRate();
            assertThat("no=" + param.get("no"), actual, is(convertToBigDecimal(param.get("expected"))));
        }
    }

    /**
     * テスト対象クラスに準備データを設定する。
     *
     * @param param {@link DbAccessTestSupport#getListMap(String, String)} の結果
     */
    private void setUpTargetForm(Map<String, String> param) {
        target.setSales(param.get("sales"));
        target.setCostOfGoodsSold(param.get("costOfGoodsSold"));
        target.setSga(param.get("sga"));
        target.setAllocationOfCorpExpenses(param.get("allocationOfCorpExpenses"));
    }

    /**
     * 文字列を数値に変換する。<br />
     * 入力値がnullの場合は、nullを返却する。
     *
     * @param input 入力値
     * @return 変換した値
     */
    private Long convertToLong(String input) {
        return StringUtil.isNullOrEmpty(input) ? null : Long.parseLong(input);
    }

    /**
     * 文字列を数値に変換する。<br />
     * 入力値がnullの場合は、nullを返却する。
     *
     * @param input 入力値
     * @return 変換した値
     */
    private BigDecimal convertToBigDecimal(String input) {
        return StringUtil.isNullOrEmpty(input) ? null : new BigDecimal(input);
    }
}
