package com.pb.controller;

import com.pb.common.vo.JsonResult;
import com.pb.pojo.League;
import com.pb.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("league")
public class LeagueController {

    @Autowired
    private LeagueService le;

    @RequestMapping("write")
    public String write(){
        return "/sys/league/league_write";
    }


    @RequestMapping("saveFile")
    @ResponseBody
    public JsonResult saveFile(@RequestParam("html") String html){
        League league = new League();
        league.setHtml(html);



        int state = 0;
        try {
            state = le.saveFlie(league);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (state == 1) {
            return new JsonResult(1, "保存成功");
        } else {
            return new JsonResult(0, "保存失败");
        }

    }

    @RequestMapping(value = "show",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectHtml(){
        League league = le.selectHtml();
        JsonResult result = new JsonResult();
        result.setState(1);
        result.setData(league.getHtml());
        return result;
    }

    @RequestMapping("cooperation")
    public String about(){
        return "wx/League/cooperation";
    }
}

