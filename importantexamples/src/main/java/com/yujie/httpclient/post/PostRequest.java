package com.yujie.httpclient.post;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;

public class PostRequest {

    private static final String POST_URL = "";

    public static void main(String [] args){

    }

    public void HttpPostRequest(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(POST_URL);
        httpPost.setEntity(buildFileEntity());
    }

    public HttpEntity buildFileEntity(){
        File file = new File("somefile.txt");
        FileEntity entity = new FileEntity(file,
                ContentType.create("text/plain", "UTF-8"));
        return entity;
    }
}
