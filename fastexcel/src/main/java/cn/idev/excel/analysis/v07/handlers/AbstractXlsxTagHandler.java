package cn.idev.excel.analysis.v07.handlers;

import cn.idev.excel.context.xlsx.XlsxReadContext;
import org.xml.sax.Attributes;

/**
 * Abstract tag handler
 *
 *
 */
public abstract class AbstractXlsxTagHandler implements XlsxTagHandler {
    @Override
    public boolean support(XlsxReadContext xlsxReadContext) {
        return true;
    }

    @Override
    public void startElement(XlsxReadContext xlsxReadContext, String name, Attributes attributes) {}

    @Override
    public void endElement(XlsxReadContext xlsxReadContext, String name) {}

    @Override
    public void characters(XlsxReadContext xlsxReadContext, char[] ch, int start, int length) {}
}
