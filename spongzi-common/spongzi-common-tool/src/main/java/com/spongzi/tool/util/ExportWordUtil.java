package com.spongzi.tool.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

/**
 * 导出Word实用程序
 *
 * @author spong
 * @date 2023/11/08
 */
public class ExportWordUtil {

    private static Configuration configuration = null;

    private static final String SUFFIX = ".doc";

    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(ExportWordUtil.class, "/template/word");
    }

    public static void exportWord(Map map, String title, String ftlName) throws Exception {
        Template template = configuration.getTemplate(ftlName);
        File file = null;
        InputStream inputStream = null;
        ServletOutputStream out = null;
        file = createDocFile(map, template);
        inputStream = Files.newInputStream(file.toPath());
        String fileName = title + SUFFIX;
        HttpServletResponse response = SpringContextUtils.getHttpServletResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        out = response.getOutputStream();
        byte[] buffer = new byte[512];
        int bytesToRead = -1;
        while ((bytesToRead = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, bytesToRead);
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (out != null) {
            out.close();
        }
        if (file != null) {
            file.delete();
        }
    }

    private static File createDocFile(Map dataMap, Template template) throws Exception {
        File file = new File("init.doc");
        OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8);
        template.process(dataMap, writer);
        writer.close();
        return file;
    }

}
