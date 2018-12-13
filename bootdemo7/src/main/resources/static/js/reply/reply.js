var replyManager = (function () {

    var getAll = function (obj, callback) {
        console.log("get all ...");
        $.getJSON("/reply/" + obj, callback);
    };

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

    var modify = function (obj, callback) {
        console.log("modify ...");
    };

    var remove = function (obj, callback) {
        console.log("remove ...");
    };

    return {
        getAll : getAll,
        add : add,
        modify : modify,
        remove : remove
    }

})();