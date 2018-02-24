package com.philliphsu.clock2.alarms.ui;

import com.alibaba.fastjson.JSON;
import com.philliphsu.clock2.alarms.data.HolidayBean;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by shu.xinghu on 2018/2/24.
 */

public class HolidayManager {
    /**
     * 检查具体日期是否为节假日，工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2；
     *
     * @return
     */
    public static int checkWorkDay(Date date) {
        String jsonString = "{\"holiday\": [[1 ], [15, 16, 19, 20, 21 ], [], [5, 6, 30 ], [1 ], [18 ], [], [], [24 ], [1, 2, 3, 4, 5 ], [], [] ], \"deferred\": [[], [24 ], [], [8 ], [], [18 ], [], [], [24, 29, 30 ], [], [], [] ] }";
        HolidayBean holidayBean = JSON.parseObject(jsonString, HolidayBean.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        for (int h : holidayBean.holiday.get(m)) {
            if (h == d) {
                return 2;
            }
        }
        for (int h : holidayBean.deferred.get(m)) {
            if (h == d) {
                return 0;
            }
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return 1;
        }
        return 0;
    }
}
