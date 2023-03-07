package com.powersi.utils;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * description http工具类
 * date 21/11/2018
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtil {


  private static final String CHARSET = "UTF-8";
  private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
  private static final ConnectionPool POOL = new ConnectionPool(5, 20, TimeUnit.MINUTES);

  /**
   * get请求
   *
   * @param url     请求链接
   * @param headers 请求头
   * @return ResponseBody响应
   */
  public static ResponseBody doGet(String url, Map<String, String> headers) {
    Request.Builder builder = new Request.Builder().url(url).get();
    return doHttp(builder, headers);
  }


  private static ResponseBody doHttp(Request.Builder builder, Map<String, String> headers) {
    if (headers != null) {
      headers.forEach(builder::addHeader);
    }
    try {
      return getClient()
        .newCall(builder.build())
        .execute()
        .body();
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }


  private static OkHttpClient getClient() {
    return new OkHttpClient.Builder()
      .connectTimeout(30, TimeUnit.SECONDS)
      .writeTimeout(1, TimeUnit.MINUTES)
      .readTimeout(1, TimeUnit.MINUTES)
      .retryOnConnectionFailure(false)
      .followRedirects(false)
      .connectionPool(POOL)
      .build();
  }


  /**
   * post请求
   *
   * @param url     地址
   * @param params  参数
   * @param headers 请求头
   * @return ResponseBody响应
   * @author SunYiBo
   * @since 21/11/2018
   */
  public static ResponseBody doPost(String url, Map<String, String> params, Map<String, String> headers) {
    FormBody.Builder formBodyBuilder = new FormBody.Builder(StandardCharsets.UTF_8);
    if (params != null) {
      params.forEach(formBodyBuilder::add);
    }
    Request.Builder builder = new Request.Builder().url(url).post(formBodyBuilder.build());
    return doHttp(builder, headers);
  }


  /**
   * post请求
   *
   * @param url     地址
   * @param params  参数
   * @param headers 请求头
   * @return ResponseBody响应
   * @author SunYiBo
   * @since 21/11/2018
   */
  public static ResponseBody doPostFile(
    String url,
    Map<String, String> headers,
    Map<String, String> params,
    String filePartName,
    List<MultipartFile> multipartFiles
  ) {
    MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
    multipartBodyBuilder.setType(MultipartBody.FORM);
    multipartFiles.forEach(multipartFile -> {
      String fileName = multipartFile.getOriginalFilename();
      try {
        multipartBodyBuilder.addFormDataPart(filePartName, getURLEncode(fileName), RequestBody.create(MultipartBody.FORM, multipartFile.getBytes()));
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    });
    if (params != null) {
      params.forEach(multipartBodyBuilder::addFormDataPart);
    }
    Request.Builder builder = new Request.Builder().url(url).post(multipartBodyBuilder.build());
    return doHttp(builder, headers);
  }


  /**
   * URL 转码
   *
   * @param s 被转码的字符串
   * @return 转码后的字符串
   * @author SunYiBo
   * @since 21/11/2018
   */
  private static String getURLEncode(String s) {
    if (null != s) {
      try {
        return URLEncoder.encode(s, CHARSET);
      } catch (UnsupportedEncodingException e) {
        log.error(e.getMessage(), e);
      }
    }
    return "";
  }


  /**
   * post请求提交json
   *
   * @param url     地址
   * @param subject 参数对象
   * @param headers 请求头
   * @return ResponseBody响应
   * @author SunYiBo
   * @since 21/11/2018
   */
  public static ResponseBody doPostJson(String url, Object subject, Map<String, String> headers) {
    String json = new Gson().toJson(subject);
    Request.Builder builder = new Request.Builder().url(url).post(RequestBody.create(JSON_TYPE, json));
    return doHttp(builder, headers);
  }


  /**
   * 从响应中得到字符串
   *
   * @param response 响应
   * @return 字符串
   * @author SunYiBo
   * @since 21/11/2018
   */
  public static String getStringFromResponse(ResponseBody response) {
    String str = null;
    if (response != null) {
      try {
        str = response.string();
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }
    return str;
  }
}