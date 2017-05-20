package com.laoxiao.transformer.hive;


import java.io.IOException;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import com.laoxiao.common.DateEnum;
import com.laoxiao.transformer.model.dim.base.DateDimension;
import com.laoxiao.transformer.service.IDimensionConverter;
import com.laoxiao.transformer.service.impl.DimensionConverterImpl;
import com.laoxiao.util.TimeUtil;

/**
 * 操作日期dimension 相关的udf
 * 
 * @author 肖斌
 *
 */
public class DateDimensionUDF extends UDF {
    private IDimensionConverter converter = new DimensionConverterImpl();

   

    /**
     * 根据给定的日期（格式为:yyyy-MM-dd）至返回id
     * 
     * @param day
     * @return
     */
    public IntWritable evaluate(Text day) {
        DateDimension dimension = DateDimension.buildDate(TimeUtil.parseString2Long(day.toString()), DateEnum.DAY);
        try {
            int id = this.converter.getDimensionIdByValue(dimension);
            return new IntWritable(id);
        } catch (IOException e) {
            throw new RuntimeException("获取id异常");
        }
    }
}
