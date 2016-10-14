package com.shishen.spider.PageProcesser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by shishen on 2016/10/11.
 */
public class BtTianTangPageProcesser implements PageProcessor{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.933.400 QQBrowser/9.4.8699.400")
            .setDomain("www.bttiantang99.com")
            .addHeader("Referer","http://www.bttiantang99.com/sb/%E5%8A%A8%E7%94%BB.html");

    public void process(Page page) {
        List<String> links = page.getHtml().links().regex("http://www\\.bttiantang99\\.com/movie/\\d+.html").all();
        page.addTargetRequests(links);
        page.putField("name",page.getHtml().xpath("//*[@id=\"content\"]/div[1]/h1"));
        List<Map> magnetResults = getMagnetLinks(page);
        page.putField("links",magnetResults);
        if (page.getResultItems().get("name")==null){
            page.setSkip(true);
        }
    }

    private List<Map> getMagnetLinks(Page page) {
        List<Map> magnetResults = new LinkedList<Map>();
        String rawText = page.getRawText();
        Document document = Jsoup.parse(rawText);
        Elements elements = document.select("#download > ul>li>a[href^=\"magnet\"]");
        for (Element element:elements
             ) {
            Map<String,String> map = new HashMap<String, String>();
            map.put("name",element.text());
            map.put("magnetLinks",element.attr("href"));
            magnetResults.add(map);
        }
        return magnetResults;
    }

    public Site getSite() {
        return site;
    }
}
