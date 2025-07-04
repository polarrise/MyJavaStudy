package com.jinbiao.javaStudy.WorkTest;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
/**
 * @author: rise
 * @date: 2025-03-24 11:18:06
 * @Description: pdf测试
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class PdfTest {

        public static void main(String[] args) throws IOException {
            try {
                // 1️⃣ 解析 JSON 数据
                String json = "{\"title\":\"AI 报告\", \"content\":\"这是 AI 生成的报告内容...\"}";
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(json);

                String title = rootNode.get("title").asText();
                String content = rootNode.get("content").asText();

                // 2️⃣ 创建 PDF 文件
                String pdfPath = "report.pdf";
                PdfWriter writer = new PdfWriter(pdfPath);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // 3️⃣ 设置支持中文的字体（默认字体不支持中文）
                PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);

                // 4️⃣ 写入内容到 PDF
                document.add(new Paragraph(title).setFont(font).setBold().setFontSize(16));  // 标题加粗
                document.add(new Paragraph(content).setFont(font).setFontSize(12));  // 正文

                // 5️⃣ 关闭文档，确保内容写入
                document.close();
                System.out.println("✅ PDF 生成成功: " + pdfPath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}
