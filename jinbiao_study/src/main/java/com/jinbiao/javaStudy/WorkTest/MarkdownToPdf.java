package com.jinbiao.javaStudy.WorkTest;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author: rise
 * @date: 2025-03-24 13:30:48
 * @Description: Markdown转pdf
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class MarkdownToPdf {

    public static void main(String[] args) throws Exception {
        // 模拟 AI 返回的 Markdown 文本
        String markdown = "# AI 报告\n\n这是 **AI 生成** 的报告内容。\n\n- 关键点 1\n- 关键点 2\n";

        // 解析 Markdown 转 HTML
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = "<html><body>" + renderer.render(document) + "</body></html>";

        // HTML 转 PDF
        String pdfPath = "report.pdf";
        try (OutputStream os = new FileOutputStream(pdfPath)) {
            ITextRenderer pdfRenderer = new ITextRenderer();
            pdfRenderer.setDocumentFromString(html);
            pdfRenderer.layout();
            pdfRenderer.createPDF(os);
        }

        System.out.println("PDF 生成成功: " + pdfPath);
    }
}
