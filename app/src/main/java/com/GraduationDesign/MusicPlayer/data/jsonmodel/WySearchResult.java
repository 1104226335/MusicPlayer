package com.GraduationDesign.MusicPlayer.data.jsonmodel;

import java.util.List;

public class WySearchResult {

    /**
     * Code : OK
     * Body : [{"id":411214279,"title":"雅俗共赏","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=411214279","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=411214279","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=411214279"},{"id":167870,"title":"如果当时","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=167870","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=167870","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=167870"},{"id":167876,"title":"有何不可","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=167876","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=167876","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=167876"},{"id":28854182,"title":"惊鸿一面","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=28854182","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=28854182","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=28854182"},{"id":167937,"title":"断桥残雪","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=167937","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=167937","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=167937"},{"id":27646687,"title":"玫瑰花的葬礼","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=27646687","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=27646687","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=27646687"},{"id":167850,"title":"庐州月","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=167850","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=167850","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=167850"},{"id":167882,"title":"清明雨上","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=167882","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=167882","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=167882"},{"id":167873,"title":"多余的解释","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=167873","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=167873","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=167873"},{"id":167827,"title":"素颜","author":"许嵩","url":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=167827","pic":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=167827","lrc":"https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=167827"}]
     */

    public String Code;
    public List<BodyBean> Body;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public List<BodyBean> getBody() {
        return Body;
    }

    public void setBody(List<BodyBean> Body) {
        this.Body = Body;
    }
}
