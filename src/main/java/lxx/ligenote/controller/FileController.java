package lxx.ligenote.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lxx.ligenote.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * ClassName:FileController
 * Package:lxx.ligenote.controller
 * Description:
 *
 * @Date:2020/2/15 15:24
 * @Author:857251389@qq.com
 */
@Controller
public class FileController {

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        //指定存储空间的位置
        Configuration cfg = new Configuration(Zone.zone0());

        //...生成上传凭证，然后准备上传
        UploadManager uploadManager = new UploadManager(cfg);
        //配置密匙，和存储空间信息
        String accessKey = "tmorGbVAnS80R4OyqmybOQBCfZvkygYEeT2qFQJH";
        String secretKey = "QLEjhx5VEShUPgp_t-IpHWmTX0SQaiUgTt4kLu1J";
        String bucket = "lxx-imgs";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        String imgURl = "";

        FileDTO fileDTO = new FileDTO();

        try {
            InputStream byteInputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
                imgURl = putRet.hash;


                fileDTO.setSuccess(1);
                fileDTO.setUrl("http://q5qw4ego6.bkt.clouddn.com/"+imgURl);
                fileDTO.setMessage("上传成功");
                return fileDTO;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }

        fileDTO.setSuccess(0);
        fileDTO.setUrl("");
        fileDTO.setMessage("上传失败");
        return fileDTO;
    }

}
