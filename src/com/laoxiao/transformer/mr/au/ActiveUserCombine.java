package com.laoxiao.transformer.mr.au;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

import com.laoxiao.transformer.model.dim.StatsUserDimension;
import com.laoxiao.transformer.model.value.map.TimeOutputValue;

/**
 * combine类
 * 
 * @author 肖斌
 *
 */
public class ActiveUserCombine extends Reducer<StatsUserDimension, TimeOutputValue, StatsUserDimension, TimeOutputValue> {
    @Override
    protected void reduce(StatsUserDimension key, Iterable<TimeOutputValue> values, Context context) throws IOException, InterruptedException {
        for (TimeOutputValue tov : values) {
            context.write(key, tov);
        }
    }
}
