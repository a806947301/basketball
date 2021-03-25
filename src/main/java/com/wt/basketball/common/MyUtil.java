package com.wt.basketball.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyUtil {
    /** 文章点赞 */
    private static Map<String, Integer> userGoodArticle = new HashMap<>();

    /** 评论点赞 */
    private static Map<String, Integer> userGoodCommon = new HashMap<>();

    /** 喜欢评论 */
    private static Map<String, Integer> userLikeCommon = new HashMap<>();

    /** 不喜欢评论 */
    private static Map<String, Integer> userUnlikeCommon = new HashMap<>();

    public static void addGood(String username, Integer articleId) {
        Integer article = userGoodArticle.get(username + "-" + articleId);
        if (null != article) {
            throw new AppException("您已点赞");
        }

        userGoodArticle.put(username + "-" + articleId, articleId);
    }

    public static void addGoodCommon(String username, Integer id) {
        Integer article = userGoodCommon.get(username + "-" + id);
        if (null != article) {
            throw new AppException("您已点赞");
        }

        userGoodCommon.put(username + "-" + id, id);
    }


    public static void addLikeCommon(String username, Integer id) {
        Integer article = userLikeCommon.get(username + "-" + id);
        if (null != article) {
            throw new AppException("您已喜欢");
        }

        userLikeCommon.put(username + "-" + id, id);
    }

    public static void addUnlikeCommon(String username, Integer id) {
        Integer article = userUnlikeCommon.get(username + "-" + id);
        if (null != article) {
            throw new AppException("您已不喜欢");
        }

        userUnlikeCommon.put(username + "-" + id, id);
    }
}
