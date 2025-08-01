package cn.idev.excel;

import cn.idev.excel.analysis.ExcelAnalyser;
import cn.idev.excel.analysis.ExcelAnalyserImpl;
import cn.idev.excel.analysis.ExcelReadExecutor;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.metadata.ReadSheet;
import cn.idev.excel.read.metadata.ReadWorkbook;
import java.io.Closeable;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Excel readers are all read in event mode.
 *
 */
@Slf4j
public class ExcelReader implements Closeable {

    /**
     * Analyser
     */
    private final ExcelAnalyser excelAnalyser;

    public ExcelReader(ReadWorkbook readWorkbook) {
        excelAnalyser = new ExcelAnalyserImpl(readWorkbook);
    }

    /**
     * Parse all sheet content by default
     *
     * @deprecated lease use {@link #readAll()}
     */
    @Deprecated
    public void read() {
        readAll();
    }

    /***
     * Parse all sheet content by default
     */
    public void readAll() {
        excelAnalyser.analysis(null, Boolean.TRUE);
    }

    /**
     * Parse the specified sheet，SheetNo start from 0
     *
     * @param readSheet Read sheet
     */
    public ExcelReader read(ReadSheet... readSheet) {
        return read(Arrays.asList(readSheet));
    }

    /**
     * Read multiple sheets.
     *
     * @param readSheetList
     * @return
     */
    public ExcelReader read(List<ReadSheet> readSheetList) {
        excelAnalyser.analysis(readSheetList, Boolean.FALSE);
        return this;
    }

    /**
     * Context for the entire execution process
     *
     * @return
     */
    public AnalysisContext analysisContext() {
        return excelAnalyser.analysisContext();
    }

    /**
     * Current executor
     *
     * @return
     */
    public ExcelReadExecutor excelExecutor() {
        return excelAnalyser.excelExecutor();
    }

    /**
     * @return
     * @deprecated please use {@link #analysisContext()}
     */
    @Deprecated
    public AnalysisContext getAnalysisContext() {
        return analysisContext();
    }

    /**
     * Complete the entire read file.Release the cache and close stream.
     */
    public void finish() {
        if (excelAnalyser != null) {
            excelAnalyser.finish();
        }
    }

    @Override
    public void close() {
        finish();
    }

    /**
     * Prevents calls to {@link #finish} from freeing the cache
     *
     */
    @Override
    protected void finalize() {
        try {
            finish();
        } catch (Throwable e) {
            log.warn("Destroy object failed", e);
        }
    }
}
