var replyManager =  (function () {

    // 댓글 목록
    var getAll = function (obj, callback) {
        console.log("get all ...");
        $.getJSON("/reply/" + obj, callback);
    };

    // 댓글 추가
    var add = function (obj, callback) {
        console.log("add ...");
        $.ajax({
            type: "post",
            url: "/reply/" + obj.articleNo,
            data : JSON.stringify(obj),
            dataType : "JSON",
            contentType : "application/json",
            success:callback
        });
    };

    // 댓글 수정
    var modify = function (obj, callback) {
        console.log("modify ...");
        $.ajax({
            type: "put",
            url: "/reply/" + obj.articleNo,
            data : JSON.stringify(obj),
            dataType : "JSON",
            contentType : "application/json",
            success:callback
        });
    };

    // 댓글 삭제
    var remove = function (obj, callback) {
        console.log("remove ...");
        $.ajax({
            type: "delete",
            url: "/reply/" + obj.articleNo + "/" + obj.replyNo,
            dataType : "JSON",
            contentType : "application/json",
            success:callback
        });
    };

    return {
        getAll : getAll,
        add : add,
        modify : modify,
        remove : remove
    }
})();