package com.pb.controller;

import com.pb.common.vo.JsonResult;
import com.pb.service.IntegralSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/integralSetting")
@Controller
public class IntegralSettingController {
    @Autowired
    private IntegralSettingService integralSettingService;

    /***
     * 跳转到默认积分设置页
     * @return 具体页
     */
    @RequestMapping("doIntegral")
    public String doIntegral() {
        return "sys/Integral/setting";
    }

    /***
     * 更新默认积分
     * @param integral 默认积分
     * @return 结果集
     */
    @RequestMapping(value = "updateDefaultIntegral")
    @ResponseBody
    public JsonResult updateDefaultIntegral(@RequestParam("integral") int integral) {
        int result = integralSettingService.updateDefaultIntegral(integral);
        return result == 1? new JsonResult(1, "更新成功") :
                new JsonResult(0, "更新失败");
    }

    /***
     * 查询默认积分
     * @return 结果集
     */
    @RequestMapping(value = "selDefaultIntegral")
    @ResponseBody
    public JsonResult selDefaultIntegral() {
        int result = integralSettingService.selDefaultIntegral();
        return new JsonResult(1, result);
    }
    
    /***
     * 更新预览所需积分
     * @param integral 所需积分
     * @return 结果集
     */
    @RequestMapping(value = "updatePrevIntegral")
    @ResponseBody
    public JsonResult updatePrevIntegral(@RequestParam("integral") int integral) {
        int result = integralSettingService.updatePrewIntegral(integral);
        return result == 1? new JsonResult(1, "更新成功") :
                new JsonResult(0, "更新失败");
    }

    /***
     * 查询预览所需积分
     * @return 结果集
     */
    @RequestMapping(value = "selPrewIntegral")
    @ResponseBody
    public JsonResult selPrewIntegral() {
        int result = integralSettingService.selPrewIntegral();
        return new JsonResult(1, result);
    }

    /***
     * 更新下载所需积分
     * @param integral 所需积分
     * @return 结果集
     */
    @RequestMapping(value = "updateDownloadIntegral")
    @ResponseBody
    public JsonResult updateDownloadIntegral(@RequestParam("integral") int integral) {
        int result = integralSettingService.updateDownloadIntegral(integral);
        return result == 1? new JsonResult(1, "更新成功") :
                new JsonResult(0, "更新失败");
    }

    /***
     * 查询下载所需积分
     * @return 结果集
     */
    @RequestMapping(value = "selDownloadIntegral")
    @ResponseBody
    public JsonResult selDownloadIntegral() {
        int result = integralSettingService.selDownloadIntegral();
        return new JsonResult(1, result);
    }

    /***
     * 更新发布所得积分
     * @param integral 所得积分
     * @return 结果集
     */
    @RequestMapping(value = "updatePublishIntegral")
    @ResponseBody
    public JsonResult updatePublishIntegral(@RequestParam("integral") int integral) {
        int result = integralSettingService.updatePublishIntegral(integral);
        return result == 1? new JsonResult(1, "更新成功") :
                new JsonResult(0, "更新失败");
    }

    /***
     * 查询发布所得积分
     * @return 结果集
     */
    @RequestMapping(value = "selPublishIntegral")
    @ResponseBody
    public JsonResult selPublishIntegral() {
        int result = integralSettingService.selPublishIntegral();
        return new JsonResult(1, result);
    }

    /***
     * 更新推荐所得积分
     * @param integral 所得积分
     * @return 结果集
     */
    @RequestMapping(value = "updateReferIntegral")
    @ResponseBody
    public JsonResult updateReferIntegral(@RequestParam("integral") int integral) {
        int result = integralSettingService.updateReferIntegral(integral);
        return result == 1? new JsonResult(1, "更新成功") :
                new JsonResult(0, "更新失败");
    }

    /***
     * 查询推荐所得积分
     * @return 结果集
     */
    @RequestMapping(value = "selReferIntegral")
    @ResponseBody
    public JsonResult selReferIntegral() {
        int result = integralSettingService.selReferIntegral();
        return new JsonResult(1, result);
    }
}
