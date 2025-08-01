package cn.idev.excel.test.core.noncamel;

import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import com.alibaba.fastjson2.JSON;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

/**
 *
 */
@Slf4j
public class UnCamelDataListener extends AnalysisEventListener<UnCamelData> {
    List<UnCamelData> list = new ArrayList<>();

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.debug("Head is:{}", JSON.toJSONString(headMap));
        Assertions.assertEquals(headMap.get(0), "string1");
        Assertions.assertEquals(headMap.get(1), "string2");
        Assertions.assertEquals(headMap.get(2), "STring3");
        Assertions.assertEquals(headMap.get(3), "STring4");
        Assertions.assertEquals(headMap.get(4), "STRING5");
        Assertions.assertEquals(headMap.get(5), "STRing6");
    }

    @Override
    public void invoke(UnCamelData data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        Assertions.assertEquals(list.size(), 10);
        UnCamelData unCamelData = list.get(0);
        Assertions.assertEquals(unCamelData.getString1(), "string1");
        Assertions.assertEquals(unCamelData.getString2(), "string2");
        Assertions.assertEquals(unCamelData.getSTring3(), "string3");
        Assertions.assertEquals(unCamelData.getSTring4(), "string4");
        Assertions.assertEquals(unCamelData.getSTRING5(), "string5");
        Assertions.assertEquals(unCamelData.getSTRing6(), "string6");
        log.debug("First row:{}", JSON.toJSONString(list.get(0)));
    }
}
