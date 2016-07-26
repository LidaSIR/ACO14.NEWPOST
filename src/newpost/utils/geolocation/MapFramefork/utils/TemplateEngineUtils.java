package newpost.utils.geolocation.MapFramefork.utils;

/**
 * Created by Vladislav on 24.07.2016.
 */
import freemarker.core.ParseException;
import freemarker.template.*;
import freemarker.template.utility.StringUtil;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

/**
 * Created by serhii on 26.04.16.
 */
public class TemplateEngineUtils {

    private static final Configuration cfg;

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_24);
        cfg.setDefaultEncoding("UTF-8");
        try {
            URL resourceDirURL = TemplateEngineUtils.class.getResource("/resources/templates");
            String fileName = resourceDirURL.getFile();
            cfg.setDirectoryForTemplateLoading(
                    new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
    }

    /**
     * @param fileName relative name, begins from src/resources/resources.templates/
     *                 example googlemapmarker.html
     *
     *
     * */
    public static String merge(String fileName, Map proper) throws IOException, TemplateException {
        Template template = cfg.getTemplate(fileName,"UTF-8");
        try(StringWriter writer = new StringWriter()){
            template.process(proper, writer);
            return writer.toString();
        }
    }


}