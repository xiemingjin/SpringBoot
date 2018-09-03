package com.xiemj.javasestudy.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

    public static void main(String[] args)
    {


        Document doc = getDocument("https://www.biqugexsw.com/30_30505/");

        Elements elements1 = doc.getElementsByTag("dd");
        System.out.println(elements1.html());

    }


     public  static  Document getDocument (String url)
     {
        try {
                 return Jsoup.connect(url).get();
            } catch (IOException e) {
                 e.printStackTrace();
            }
         return null;
     }

}
