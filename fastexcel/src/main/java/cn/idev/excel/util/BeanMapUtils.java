package cn.idev.excel.util;

import cn.idev.excel.support.cglib.beans.BeanMap;
import cn.idev.excel.support.cglib.core.DefaultNamingPolicy;

/**
 * bean utils
 *
 *
 */
public class BeanMapUtils {

    /**
     * Helper method to create a new <code>BeanMap</code>.  For finer
     * control over the generated instance, use a new instance of
     * <code>BeanMap.Generator</code> instead of this static method.
     *
     * Custom naming policy to prevent null pointer exceptions.
     * see: https://github.com/fast-excel/fastexcel/issues/2064
     *
     * @param bean the JavaBean underlying the map
     * @return a new <code>BeanMap</code> instance
     */
    public static BeanMap create(Object bean) {
        BeanMap.Generator gen = new BeanMap.Generator();
        gen.setBean(bean);
        gen.setContextClass(bean.getClass());
        gen.setNamingPolicy(EasyExcelNamingPolicy.INSTANCE);
        return gen.create();
    }

    public static class EasyExcelNamingPolicy extends DefaultNamingPolicy {
        public static final EasyExcelNamingPolicy INSTANCE = new EasyExcelNamingPolicy();

        @Override
        protected String getTag() {
            return "ByEasyExcelCGLIB";
        }
    }
}
