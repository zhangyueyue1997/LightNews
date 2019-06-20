package com.pb.service;

public interface IntegralSettingService {
    /***
     * 更新默认积分
     * @param integral 积分
     * @return 受影响行数
     */
    int updateDefaultIntegral(int integral);

    /***
     * 查询默认积分
     * @return 默认积分值
     */
    int selDefaultIntegral();

    /***
     * 更新预览所需积分
     * @param integral 积分
     * @return 受影响行数
     */
    int updatePrewIntegral(int integral);

    /***
     * 查询预览所需积分
     * @return 默认积分值
     */
    int selPrewIntegral();
    /***
     * 更新下载所需积分
     * @param integral 积分
     * @return 受影响行数
     */
    int updateDownloadIntegral(int integral);

    /***
     * 查询下载所需积分
     * @return 默认积分值
     */
    int selDownloadIntegral();
    /***
     * 更新发布所需积分
     * @param integral 积分
     * @return 受影响行数
     */
    int updatePublishIntegral(int integral);

    /***
     * 查询发布所需积分
     * @return 默认积分值
     */
    int selPublishIntegral();
    /***
     * 更新推荐所得积分
     * @param integral 积分
     * @return 受影响行数
     */
    int updateReferIntegral(int integral);

    /***
     * 查询推荐所得积分
     * @return 默认积分值
     */
    int selReferIntegral();
}
