package cn.idev.excel.metadata.data;

import cn.idev.excel.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * data format
 *
 *
 */
@Getter
@Setter
@EqualsAndHashCode
public class DataFormatData {
    /**
     * index
     */
    private Short index;

    /**
     * format
     */
    private String format;

    /**
     * The source is not empty merge the data to the target.
     *
     * @param source source
     * @param target target
     */
    public static void merge(DataFormatData source, DataFormatData target) {
        if (source == null || target == null) {
            return;
        }
        if (source.getIndex() != null) {
            target.setIndex(source.getIndex());
        }
        if (StringUtils.isNotBlank(source.getFormat())) {
            target.setFormat(source.getFormat());
        }
    }

    @Override
    public DataFormatData clone() {
        DataFormatData dataFormatData = new DataFormatData();
        dataFormatData.setIndex(getIndex());
        dataFormatData.setFormat(getFormat());
        return dataFormatData;
    }
}
