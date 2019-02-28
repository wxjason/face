package cn.wxj.face.api.service;

import cn.wxj.face.api.bean.*;
import cn.wxj.face.api.config.properties.FaceApiProperties;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import cn.wxj.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: FaceApiService
 * @Package cn.wxj.face.api.service
 * @Description:
 * @Author wuxinjian
 * @Date 2019/2/26 17:50
 * @Version V1.0
 */
@Slf4j
@Service
public class FaceApiService {


    private static final String IMAGE_TYPE = "BASE64";

    private final FaceApiProperties faceApiProperties;

    private final AipFace client;

    @Autowired
    public FaceApiService(FaceApiProperties faceApiProperties) {
        this.faceApiProperties = faceApiProperties;
        client = new AipFace(faceApiProperties.getAppId(), faceApiProperties.getApiKey(), faceApiProperties.getSecretKey());
    }

    public Double detect(String imageBase64){
        JSONObject result = client.detect(imageBase64, IMAGE_TYPE, null);
        log.info("detect:{}", result);
        if (Objects.isNull(result)) {
            return 0.0;
        }
        DetectResp detectResp = JsonUtils.json2Obj(result.toString(), DetectResp.class);
        if (Objects.isNull(detectResp) || Objects.isNull(detectResp.getError_code())) {
            return 0.0;
        }
        if (detectResp.getError_code() != 0) {
            return 0.0;
        }
        return detectResp.getResult().getFace_list().get(0).getFace_probability();
    }

    public Double compare(String imageBase64A, String imageBase64B){
        List<MatchRequest> matchRequestList = new ArrayList<>();
        MatchRequest imageA = new MatchRequest(imageBase64A, IMAGE_TYPE);
        MatchRequest imageB = new MatchRequest(imageBase64B, IMAGE_TYPE);
        matchRequestList.add(imageA);
        matchRequestList.add(imageB);
        JSONObject result = client.match(matchRequestList);
        log.info("compare:{}", result);
        if (Objects.isNull(result)) {
            return 0.0;
        }
        MatchResp matchResp = JsonUtils.json2Obj(result.toString(), MatchResp.class);
        if (Objects.isNull(matchResp) || Objects.isNull(matchResp.getError_code())) {
            return 0.0;
        }
        if (matchResp.getError_code() != 0) {
            return 0.0;
        }
        return matchResp.getResult().getScore() / 100.0;
    }

    public String addUser(String imageBase64){
        String userId = UUID.randomUUID().toString().replaceAll("-", "");
        JSONObject result = client.addUser(imageBase64, IMAGE_TYPE, faceApiProperties.getGroupId(), userId, null);
        log.info("addUser:{}", result);
        return userId;
    }

    public void updateUser(String userId, String imageBase64){
        JSONObject result = client.updateUser(imageBase64, IMAGE_TYPE, faceApiProperties.getGroupId(), userId, null);
        log.info("updateUser:{}", result);
    }

    public void deleteUser(String userId){
        JSONObject result = client.deleteUser(faceApiProperties.getGroupId(), userId, null);
        log.info("deleteUser:{}", result);
    }

    public SearchUser searchUser(String imageBase64){
        JSONObject result = client.search(imageBase64, IMAGE_TYPE, faceApiProperties.getGroupId(), null);
        log.info("searchUser:{}", result);
        if (Objects.isNull(result)) {
            return null;
        }
        SearchResp searchResp = JsonUtils.json2Obj(result.toString(), SearchResp.class);
        if (Objects.isNull(searchResp) || Objects.isNull(searchResp.getError_code())) {
            return null;
        }
        if (searchResp.getError_code() != 0) {
            return null;
        }
        List<User> userList = searchResp.getResult().getUser_list();
        User user = userList.stream().max(Comparator.comparing(User::getScore)).orElse(null);
        if (Objects.isNull(user)) {
            return null;
        }
        SearchUser searchUser = new SearchUser();
        searchUser.setScore(user.getScore() / 100.0);
        searchUser.setUserId(user.getUser_id());
        return searchUser;
    }
}
