package com.itbird.design.principle.imageloader.v4;

/**
 * download callback封装
 * Created by itbird on 2022/3/30
 */
public interface DownloadCallback<T> {
    /**
     * download成功返回
     *  @param url
     * @param result
     */
    void downloadSuccess(String url, T result);

    /**
     * download进度回调
     *
     * @param proecss
     */
    void downloadProcess(int proecss);

    /**
     * download失败
     *
     * @param errorCode
     */
    void downloadError(int errorCode, String errorMsg);
}
