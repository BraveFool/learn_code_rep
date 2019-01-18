package com.yujie.httpclient.get;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @autor wangyujie
 * @since 2019-01-18
 * httpclient实现get请求
 */
public class GetRequest {

    private static final Log log = LogFactory.getLog(GetRequest.class);

    private static final String GET_URL = "http://bodhi-data.sankuai.com/api/api/monitor/alive";

    public static void main(String [] args){
        httpclientGetRequest();
    }


    public static void httpclientGetRequest(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(GET_URL);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            if(httpResponse != null){
                System.out.println(httpResponse.getStatusLine().getStatusCode());
                System.out.println(httpResponse.getProtocolVersion().getProtocol());
                System.out.println(httpResponse.getProtocolVersion().getMajor());
                System.out.println(httpResponse.getProtocolVersion().getMinor());
                HttpEntity httpEntity = httpResponse.getEntity();
                long len = httpEntity.getContentLength();
                if(len != -1 && len < 2048){
                    System.out.println(EntityUtils.toString(httpEntity));
                }
                //InputStream inputStream = httpEntity.getContent();
            }
        } catch (IOException e) {
            log.error("httpclientGetRequest exception: ", e);
        } finally {
            close(httpClient);
        }
    }

    public static void close(CloseableHttpClient httpClient){
        try {
            httpClient.close();
        } catch (IOException e) {
            log.error("close eception: ", e);
        }
    }


    public URI buildURI() throws URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.google.com")
                .setPath("/search")
                .setParameter("q", "httpclient")
                .setParameter("btnG", "Google Search")
                .setParameter("aq", "f")
                .setParameter("oq", "")
                .build();
        return uri;
    }

    public StringEntity build() throws IOException {
        StringEntity myEntity = new StringEntity("important message",
                ContentType.create("text/plain", "UTF-8"));

        System.out.println(myEntity.getContentType());
        System.out.println(myEntity.getContentLength());
        System.out.println(EntityUtils.toString(myEntity));
        System.out.println(EntityUtils.toByteArray(myEntity).length);
        return myEntity;

    }

    /**
     * 当需要不止一次的读取实体内容时，可以将结果放入内存缓冲区中
     * @param entity
     * @throws IOException
     */
    public void buildBufferedHttpEntity(HttpEntity entity) throws IOException {
        if (entity != null) {
            entity = new BufferedHttpEntity(entity);
        }

    }
}
