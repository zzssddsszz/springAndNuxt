package com.modoodesigner.web.apis;

import com.modoodesigner.domain.model.part.pendant.Pendant;
import com.modoodesigner.domain.model.post.Post;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.Result;
import com.modoodesigner.web.results.data.PendantData;
import com.modoodesigner.web.results.data.PostData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PostListResult {

    public static ResponseEntity<ApiResult> created(List<Post> postAll) {
        List<PostData> postDataList = new ArrayList<>();
        postAll.stream().forEach(post->{
            postDataList.add(new PostData(post));
        });

        ApiResult apiResult = ApiResult.blank()
                .add("data", postDataList);
        return Result.ok(apiResult);
    }
}
