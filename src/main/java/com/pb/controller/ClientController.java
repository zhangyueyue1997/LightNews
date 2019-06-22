package com.pb.controller;

import com.pb.common.vo.JsonResult;
import com.pb.pojo.Client;
import com.pb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService service;

    @RequestMapping("getClientsByPages")
    @ResponseBody
    public JsonResult getClientsByPages(@RequestParam("pageCurrent") Integer pageCurrent) {
        return new JsonResult(1,service.getClientsByPage(pageCurrent));
    }

    @RequestMapping("getClientsByFuzzyName")
    @ResponseBody
        public JsonResult getClientByFuzzyName(@RequestParam("username") String username) {
        Client client = new Client();
        client.setUsername(username);
        return new JsonResult(1,service.getClientPageByFuzzyName(client));
    }

    @RequestMapping(value = "verify",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult verify(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        Client tmp = new Client();
        tmp.setUsername(username);
        tmp.setPassword(password);
        List<Client> resultList = service.getClientsByUsernamePassword(tmp);
        int result = resultList.size();
        return result == 1 ? new JsonResult(1,"登录成功") :
                             new JsonResult(0,"登录失败");
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveObject(@RequestParam("username") String username,
                                 @RequestParam("password") String password) {
        Client tmp = new Client(username, password);

        return service.save(tmp) == 1 ? new JsonResult(1,"保存成功"):
                new JsonResult(0,"保存失败");

    }
}
