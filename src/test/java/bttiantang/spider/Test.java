package bttiantang.spider;

import com.shishen.spider.PageProcesser.BtTianTangPageProcesser;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

/**
 * Created by shishen on 2016/10/11.
 */
public class Test {

    @org.junit.Test
    public void test(){
        Spider.create(new BtTianTangPageProcesser())
                .addUrl("http://www.bttiantang99.com/")
                .addPipeline(new JsonFilePipeline("D://btTianTangRepository"))
                .thread(5)
                .run();
    }
}
