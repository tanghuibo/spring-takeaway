package io.github.tanghuibo.springtakeawaybaseinfo.controller;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.github.tanghuibo.result.entity.Result;
import io.github.tanghuibo.result.util.ResultTool;
import io.github.tanghuibo.springtakeawaybaseinfo.config.ConstantConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.dto.DowmloadSwaggerapiForm;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger2.web.Swagger2Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * @description: swagger文档controller
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-02-03 01:50
 **/
@RestController
@RequestMapping(ConstantConfig.CONTENT_PATH + "/swagger-docment")
@Lazy
@ApiIgnore
public class SwaggerDocmentController {

    @PostMapping("download")
    public Result<String> dowmloadSwaggerapi(@RequestBody DowmloadSwaggerapiForm dowmloadSwaggerapiForm, HttpServletResponse response) throws IOException {
        String fileName = "swagger-api" + "." + dowmloadSwaggerapiForm.getFileType();
        //    输出Ascii到单文件
        MarkupLanguage markupLanguage = null;
        switch (dowmloadSwaggerapiForm.getFileType()) {
            case "md":
                markupLanguage = MarkupLanguage.MARKDOWN;
                break;
            case "adoc":
                markupLanguage = MarkupLanguage.ASCIIDOC;
                break;
            case "txt":
                markupLanguage = MarkupLanguage.CONFLUENCE_MARKUP;
                break;
            default:
                fileName = fileName + "md";
                markupLanguage = MarkupLanguage.MARKDOWN;
                break;

        }
        Language language = "ZH".equals(dowmloadSwaggerapiForm.getLanguage()) ? Language.ZH : Language.EN;
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(markupLanguage)
                .withOutputLanguage(language)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        String txt = Swagger2MarkupConverter.from(new URL(dowmloadSwaggerapiForm.getHostUrl() + Swagger2Controller.DEFAULT_URL))
                .withConfig(config)
                .build()
                .toString();
        return ResultTool.successData(txt);


    }


}
