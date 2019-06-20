package com.pb.controller;

import com.pb.common.vo.JsonResult;
import com.pb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/message")
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/writeMessage")
    public String write(){
        return "/sys/MessageInformation/MessageInformation";
    }




    @RequestMapping("/saveMessage")
    @ResponseBody
    public JsonResult saveFile(@RequestParam("html") String html){




        int state = 0;
        try {
            state = messageService.updateMessage(html);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (state == 1) {
            return new JsonResult(1, "保存成功");
        } else {
            return new JsonResult(0, "保存失败");
        }

    }

    @RequestMapping(value = "/selectMessage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectHtml(){

        JsonResult result = new JsonResult();
        result.setState(1);
        result.setData(messageService.selectMessage());
        return result;
    }

    @RequestMapping("/showWrite")
    public String about(){
        return "/wx/League/about";
    }

}
