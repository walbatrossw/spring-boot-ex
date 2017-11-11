var replyManager = (function () {

    var getAll = function (obj, callback) {
        console.log("get All...");
        $.getJSON("/replies/" + obj, callback);
    };

    // 댓글 추가
    var add = function (obj, callback) {
        console.log("add...");
        $.ajax({
            type: "post",
            url: "/replies/" + obj.bno,
            data: JSON.stringify(obj),
            dataType: "json",
            contentType: "application/json",
            beforeSend: function (xhr) {
              xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
            },
            success: callback
        });
    };

    // 댓글 수정
    var update = function (obj, callback) {
        console.log("update...");
        $.ajax({
            type: "put",
            url: "/replies/" + obj.rno,
            data: JSON.stringify(obj),
            dataType: "json",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
            },
            success: callback
        })
    };

    // 댓글 삭제
    var remove = function (obj, callback) {
        console.log("remove...");
        $.ajax({
            type: "delete",
            url: "/replies/" + obj.bno + "/" + obj.rno,
            dataType: "json",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
            },
            success: callback
        });
    };

    return {
        getAll: getAll,
        add: add,
        update: update,
        remove: remove
    };

})();
