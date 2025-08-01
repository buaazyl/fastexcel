package cn.idev.excel.analysis.v07.handlers;

import cn.idev.excel.context.xlsx.XlsxReadContext;
import org.xml.sax.Attributes;

/**
 * Tag handler
 */
public interface XlsxTagHandler {

    /**
     * Whether to support
     *
     * @param xlsxReadContext
     * @return
     */
    boolean support(XlsxReadContext xlsxReadContext);

    /**
     * Start handle
     *
     * @param xlsxReadContext
     *            xlsxReadContext
     * @param name
     *            Tag name
     * @param attributes
     *            Tag attributes
     */
    void startElement(XlsxReadContext xlsxReadContext, String name, Attributes attributes);

    /**
     * End handle
     *
     * @param xlsxReadContext
     *            xlsxReadContext
     * @param name
     *            Tag name
     */
    void endElement(XlsxReadContext xlsxReadContext, String name);

    /**
     * Read data
     *
     * @param xlsxReadContext
     * @param ch
     * @param start
     * @param length
     */
    void characters(XlsxReadContext xlsxReadContext, char[] ch, int start, int length);
}
