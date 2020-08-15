package com.yuluo.auto.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.yuluo.auto.model.Column;
import com.yuluo.auto.model.Table;
import com.yuluo.auto.model.Templates;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * @Description 导出excle处理类
 * @Author 蚂蚁不是ant
 * @Date 2020/8/15 18:09
 * @Version V1.0
 */
public class ExcelService {
    Logger log = Logger.getLogger(ExcelService.class);
    private Templates templates;

    public ExcelService(Templates info) {
        this.templates = info;
    }

    public void process() {
        String exclePath = templates.getInfo().getExclePath();
        String excleName = getExcleName(exclePath);
        createExcle(excleName);
    }

    private void createExcle(String excleName) {
        ExcelWriter write = EasyExcel.write(excleName, Column.class).build();
        try {
            List<Table> tables = templates.getTable();
            tables.forEach(t -> {
                WriteSheet sheet = EasyExcel.writerSheet(t.getTableName() + "-" + t.getComment()).build();
                write.write(t.getColumns(), sheet);
            });
        } finally {
            if (write != null) {
                write.finish();
            }
        }
        log.info("excle create succe : " + excleName);
    }

    private String getExcleName(String exclePath) {
        if (null == exclePath || "".equals(exclePath)) {
            exclePath = System.getProperty("user.dir");
        }
        if (exclePath.contains("xlsx") || exclePath.contains("xls")) {
            return exclePath;
        }
        return exclePath + File.separator + System.currentTimeMillis() + ".xlsx";
    }
}
