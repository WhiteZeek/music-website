package com.domain;

import java.util.*;

public class Recommender {
    private Map<Integer, List<Integer>> userSongs;

    public Recommender(List<Map<String, Object>> data) {
        // 初始化用户收藏记录
        userSongs = new HashMap<>();
        for (Map<String, Object> item : data) {
            int userId = (int) item.get("userId");
            int songId = (int) item.get("songId");
            if (!userSongs.containsKey(userId)) {
                userSongs.put(userId, new ArrayList<>());
            }
            userSongs.get(userId).add(songId);
        }
    }

    public List<Integer> recommend(int userId) {
        // 找到与当前用户收藏记录最相似的用户
        int mostSimilarUser = -1;
        double highestSimilarity = -1.0;
        for (Map.Entry<Integer, List<Integer>> entry : userSongs.entrySet()) {
            int otherUserId = entry.getKey();
            if (otherUserId == userId) {
                continue; // 不计算与自己的相似度
            }
            double similarity = cosineSimilarity(userSongs.get(userId), entry.getValue());
            if (similarity > highestSimilarity) {//寻找最高相似度用户
                highestSimilarity = similarity;
                mostSimilarUser = otherUserId;
            }
        }

        // 返回最相似用户的收藏记录
        if (mostSimilarUser != -1) {
            return userSongs.get(mostSimilarUser);
        } else {
            return new ArrayList<>();
        }
    }

    private double cosineSimilarity(List<Integer> a, List<Integer> b) {
        Set<Integer> setA = new HashSet<>(a);
        Set<Integer> setB = new HashSet<>(b);
        int sizeA = setA.size();
        int sizeB = setB.size();
        setA.retainAll(setB);//取交集
        int intersectionSize = setA.size();//setA为交集
        return (double) intersectionSize / Math.sqrt(sizeA * sizeB);//余弦相似度计算
    }
}