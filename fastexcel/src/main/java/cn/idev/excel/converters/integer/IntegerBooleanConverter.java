package cn.idev.excel.converters.integer;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;

/**
 * Integer and boolean converter
 *
 *
 */
public class IntegerBooleanConverter implements Converter<Integer> {
    private static final Integer ONE = 1;
    private static final Integer ZERO = 0;

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.BOOLEAN;
    }

    @Override
    public Integer convertToJavaData(
            ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        if (cellData.getBooleanValue()) {
            return ONE;
        }
        return ZERO;
    }

    @Override
    public WriteCellData<?> convertToExcelData(
            Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        if (ONE.equals(value)) {
            return new WriteCellData<>(Boolean.TRUE);
        }
        return new WriteCellData<>(Boolean.FALSE);
    }
}
