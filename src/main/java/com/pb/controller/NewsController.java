package com.pb.controller;

import com.pb.common.Utils.FileHelper;
import com.pb.common.Utils.WangEditor;
import com.pb.common.vo.JsonResult;
import com.pb.common.vo.PageObject;
import com.pb.pojo.News;
import com.pb.service.NewsService;
import com.pb.service.PublishService;
import net.sf.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RequestMapping("/News")
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private PublishService publishService;

    /***
     * 上传文章包含的图片
     * @param files 待上传的文件组(包含图片)
     * @return 返回WangEditor特定JSON格式结果Bean
     */
    @RequestMapping(value = "doUploadImg",method = RequestMethod.POST)
    @ResponseBody
    public WangEditor doUpload(@RequestParam("uploadedFiles") MultipartFile[] files) {
        WangEditor wangEditor = new WangEditor();

        String[] imgUrls = new String[files.length];
        //声明上传文件成功个数
        int count = 0;
        try {
            if(files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    MultipartFile file = files[i];
                    /*
                     * servcie保存file
                     * 这里根据自己的上传服务器方法来实现
                     */
                    String url = publishService.uploadFile(file);
                    if (url == null) continue;
                    imgUrls[i] = url ;
                    count++;
                }
            }
            //成功的话
            wangEditor.setErrno(0);
            wangEditor.setData(imgUrls);
            return wangEditor;
        } catch (Exception e) {
            //这里errno随便设置多少,只要不是0
            wangEditor.setErrno(1);
            return wangEditor;
        }
    }
    /**添加新闻*/
    @RequestMapping(value = "insertNews",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult insertNews(@RequestParam("title") String title,
                                 @RequestParam("content") String content,
                                 @RequestParam("type") Integer type,
                                 @RequestParam(value = "thumbNail") Optional<MultipartFile> thumbNail){
        String src = null;
        if (thumbNail.isPresent()) {
            src = publishService.uploadFile(thumbNail.get());
        }
        int status = 1;
        News news = new News();
        news.setStatus(status);
        news.setContent(content);
        news.setTitle(title);
        news.setThumbNail(src);
        news.setType(type);
        int count = newsService.insertNews(news);
        return count == 1? new JsonResult(1,"保存成功"):new JsonResult(0,"保存失败");
    }

    /**分页显示新闻*/
    @RequestMapping(value = "selectNews")
    @ResponseBody
    public JsonResult selectNews(@RequestParam("currentPage") int pageCurrent,
                                 @RequestParam("type")  Optional<Integer> type,
                                 @RequestParam("title") Optional<String> title){

        int newsType = 0;
        if (type.isPresent()) {
            newsType = type.get();
        }
        String newsTitle = null;
        if (title.isPresent()) {
            newsTitle = title.get();
        }
        PageObject<News> news = newsService.selectNews(pageCurrent,newsType,newsTitle);
        return new JsonResult(1,news);
    }

    @RequestMapping("showNews")
    public String showNews(){
        return "sys/news/news_list";
    }

    @RequestMapping("addNews")
    public String addNews(){
        return "sys/news/news_add";
    }

    @RequestMapping("newsDetail")
    public String newsDetail(){
        return "sys/news/news_detail";
    }

    @RequestMapping(value = "deleteNews",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteFileByID(@RequestParam("checkedIds") String[] checkedIds) {
        JsonResult result = new JsonResult();

        FileHelper helper = newsService.deleteNews(checkedIds);
        if (helper.getCount() == 0) {
            result.setState(0);
            result.setMessage("删除失败,id为空");
        } else {
            result.setState(1);
            result.setMessage("删除成功"+"共删除" + helper.getCount() + "个文件");
        }

        return result;
    }

    /**前台显示新闻信息详情*/
    @RequestMapping(value = "selectNewsById",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult selectNewsById(@RequestParam("id") int id){
        News news = newsService.selectNewsById(id);
        return new JsonResult(1,news);
    }

    @RequestMapping("showNewsDetail")
    public String showNewsDetail(){
        return "wx/news/news_detail";
    }

    /**查询新闻条数*/
    @RequestMapping(value = "selectCountNews")
    @ResponseBody
    public JsonResult selectCountNews(){
        int count = newsService.getNewsCount();
        return new JsonResult(count,"成功");
    }

    @RequestMapping(value = "selectNewsInCount")
    @ResponseBody
    public JsonResult selectNewsInCount(@RequestParam("type") Optional<Integer> type,
                                        @RequestParam("count") Integer count,
                                        @RequestParam("flag") Optional<Integer> flag) {
        Integer newsType = null;
        if (type.isPresent()) {
            newsType = type.get();
        }
        Integer newsFlag = null;
        if (flag.isPresent()) {
            newsFlag = flag.get();
        }
        List<News> newsList = newsService.selectNewsInCount(newsType, count, newsFlag);
        return new JsonResult(1,"查询成功",newsList);
    }


    @RequestMapping(value = "setRecommendedNews")
    @ResponseBody
    public JsonResult setRecommendedNews(@RequestParam("id") Integer id) {
        int res = newsService.updateNewsFlag(id);
        return res == 1 ? new JsonResult(1,"设置成功")
                        : new JsonResult(2,"设置失败");
    }

    @RequestMapping(value = "getNewsDetail")
    @ResponseBody
    public JsonResult getNewsDetail(@RequestParam("id") Integer id) {
        News news = newsService.selectNewsById(id);
        return new JsonResult(1,"查询成功",news);
    }

    @RequestMapping(value = "doDetail")
    public String doDetail(@RequestParam("id") Integer id) {
        return "news_detail";
    }

}
