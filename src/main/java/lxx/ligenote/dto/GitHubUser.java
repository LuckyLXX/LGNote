package lxx.ligenote.dto;

/**
 * ClassName:GitHubUser
 * Package:lxx.ligenote.dto
 * Description:
 *
 * @Date:2019/12/11 15:01
 * @Author:857251389@qq.com
 */
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
