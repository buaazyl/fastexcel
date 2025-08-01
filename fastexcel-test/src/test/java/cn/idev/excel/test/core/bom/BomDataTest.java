package cn.idev.excel.test.core.bom;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.read.listener.ReadListener;
import cn.idev.excel.support.ExcelTypeEnum;
import cn.idev.excel.test.util.TestFileUtil;
import cn.idev.excel.util.ListUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * bom test
 *
 *
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
@Slf4j
public class BomDataTest {

    @Test
    public void t01ReadCsv() {
        readCsv(TestFileUtil.readFile("bom" + File.separator + "no_bom.csv"));
        readCsv(TestFileUtil.readFile("bom" + File.separator + "office_bom.csv"));
    }

    @Test
    public void t02ReadAndWriteCsv() throws Exception {
        readAndWriteCsv(TestFileUtil.createNewFile("bom" + File.separator + "bom_default.csv"), null, null);
        readAndWriteCsv(TestFileUtil.createNewFile("bom" + File.separator + "bom_utf_8.csv"), "UTF-8", null);
        readAndWriteCsv(TestFileUtil.createNewFile("bom" + File.separator + "bom_utf_8_lower_case.csv"), "utf-8", null);
        readAndWriteCsv(TestFileUtil.createNewFile("bom" + File.separator + "bom_gbk.csv"), "GBK", null);
        readAndWriteCsv(TestFileUtil.createNewFile("bom" + File.separator + "bom_gbk_lower_case.csv"), "gbk", null);
        readAndWriteCsv(TestFileUtil.createNewFile("bom" + File.separator + "bom_utf_16be.csv"), "UTF-16BE", null);
        readAndWriteCsv(
                TestFileUtil.createNewFile("bom" + File.separator + "bom_utf_8_not_with_bom.csv"),
                "UTF-8",
                Boolean.FALSE);
    }

    private void readAndWriteCsv(File file, String charsetName, Boolean withBom) throws Exception {
        Charset charset = null;
        if (charsetName != null) {
            charset = Charset.forName(charsetName);
        }
        EasyExcel.write(new FileOutputStream(file), BomData.class)
                .charset(charset)
                .withBom(withBom)
                .excelType(ExcelTypeEnum.CSV)
                .sheet()
                .doWrite(data());

        EasyExcel.read(file, BomData.class, new ReadListener<BomData>() {

                    private final List<BomData> dataList = Lists.newArrayList();

                    @Override
                    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
                        String head = headMap.get(0).getStringValue();
                        Assertions.assertEquals("姓名", head);
                    }

                    @Override
                    public void invoke(BomData data, AnalysisContext context) {
                        dataList.add(data);
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {
                        Assertions.assertEquals(dataList.size(), 10);
                        BomData bomData = dataList.get(0);
                        Assertions.assertEquals("姓名0", bomData.getName());
                        Assertions.assertEquals(20, (long) bomData.getAge());
                    }
                })
                .charset(charset)
                .sheet()
                .doRead();
    }

    private void readCsv(File file) {
        EasyExcel.read(file, BomData.class, new ReadListener<BomData>() {

                    private final List<BomData> dataList = Lists.newArrayList();

                    @Override
                    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
                        String head = headMap.get(0).getStringValue();
                        Assertions.assertEquals("姓名", head);
                    }

                    @Override
                    public void invoke(BomData data, AnalysisContext context) {
                        dataList.add(data);
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {
                        Assertions.assertEquals(dataList.size(), 10);
                        BomData bomData = dataList.get(0);
                        Assertions.assertEquals("姓名0", bomData.getName());
                        Assertions.assertEquals(20L, (long) bomData.getAge());
                    }
                })
                .sheet()
                .doRead();
    }

    private List<BomData> data() {
        List<BomData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            BomData data = new BomData();
            data.setName("姓名" + i);
            data.setAge(20L);
            list.add(data);
        }
        return list;
    }
}
