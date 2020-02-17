/**
 * 评论回复
 */
// function post() {
//     var parentId = $("#question_id").val();
//     var content = $("#comment_content").val();
//     if(content == null){
//         alert("评论不能为空");
//     }
//     $.ajax({
//         type: "POST",
//         url: "/comment",
//         data: JSON.stringify({
//             "parentId" : parentId,
//             "content" : content,
//             "type" : 1
//         }),
//         success : function(respone){
//             if(respone.code == 200){
//                 window.location.reload();
//                 // $("#comment").hide()
//             }else {
//                 if(respone.code == 2003){
//                     var isAccepted = window.confirm(respone.message);
//                     if(isAccepted) {
//                         window.open("https://github.com/login/oauth/authorize?client_id=6c08070d1ba8a658c619&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
//                         window.localStorage.setItem("closable", true);
//                     }
//                 }
//                 alert(respone.message)
//             }
//             console.log(respone)
//         },
//         dataType: "json",
//         contentType : "application/json"
//     });
// }

function comment(parentId, type, content) {
    if (content == null) {
        alert("评论不能为空");
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            "parentId": parentId,
            "content": content,
            "type": type
        }),
        success: function (respone) {
            if (respone.code == 200) {
                window.location.reload();
                // $("#comment").hide()
            } else {
                if (respone.code == 2003) {
                    var isAccepted = window.confirm(respone.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=6c08070d1ba8a658c619&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                }
                alert(respone.message)
            }
            console.log(respone)
        },
        dataType: "json",
        contentType: "application/json"
    });
}

function commentLv1() {
    var parentId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment(parentId, 1, content);
}

function commentLv2(e) {
    var parentId = e.getAttribute("data-id");
    var content = $("#input-" + parentId).val();
    comment(parentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comment = $("#comment-" + id);
    var collapse = e.getAttribute("data-collapse");
    if (!collapse) {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            comment.toggleClass("in", !collapse);
        } else {
            comment.toggleClass("in", !collapse);

            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement)
                });

            });
        }
    } else {
        comment.toggleClass("in", collapse)
    }
    e.setAttribute("data-collapse", !collapse);
    $("#icon").toggleClass("icon-color")
}

function showSelectTag() {
    $("#select-tag").show()
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}