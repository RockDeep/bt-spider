package github.spider;


import com.shishen.spider.PageProcesser.GithubRepoPageProcessor;
import org.junit.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

/**
 * Created by shishen on 2016/10/11.
 */
public class TestGitHub {

    @org.junit.Test
    public void testGitHubSpider(){
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft")
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\")).thread(5).run();
    }

}
