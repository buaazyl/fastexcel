package cn.idev.excel.test.temp.bug;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
@EqualsAndHashCode
public class DataType {
    /**
     * 任务id
     */
    @ExcelProperty("任务ID")
    private Integer id;

    @ExcelProperty("多余字段1")
    private String firstSurplus;

    @ExcelProperty("多余字段2")
    private String secSurplus;

    @ExcelProperty("多余字段3")
    private String thirdSurplus;

    @ExcelProperty(value = "备注1")
    private String firstRemark;

    @ExcelProperty(value = "备注2")
    private String secRemark;
}
