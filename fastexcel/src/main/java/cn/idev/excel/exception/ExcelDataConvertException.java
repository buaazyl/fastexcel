package cn.idev.excel.exception;

import cn.idev.excel.metadata.data.CellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;
import cn.idev.excel.write.builder.ExcelWriterBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Data convert exception
 *
 *
 */
@Getter
@Setter
@EqualsAndHashCode
public class ExcelDataConvertException extends ExcelRuntimeException {
    /**
     * NotNull.
     */
    private Integer rowIndex;
    /**
     * NotNull.
     */
    private Integer columnIndex;
    /**
     * NotNull.
     */
    private CellData<?> cellData;
    /**
     * Nullable.Only when the header is configured and when the class header is used is not null.
     *
     * @see ExcelWriterBuilder#head(Class)
     */
    private ExcelContentProperty excelContentProperty;

    public ExcelDataConvertException(
            Integer rowIndex,
            Integer columnIndex,
            CellData<?> cellData,
            ExcelContentProperty excelContentProperty,
            String message) {
        super(message);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.cellData = cellData;
        this.excelContentProperty = excelContentProperty;
    }

    public ExcelDataConvertException(
            Integer rowIndex,
            Integer columnIndex,
            CellData<?> cellData,
            ExcelContentProperty excelContentProperty,
            String message,
            Throwable cause) {
        super(message, cause);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.cellData = cellData;
        this.excelContentProperty = excelContentProperty;
    }
}
