/**
 * 前端工具类
 * @type {{ajax: SysTool.ajax}}
 */
var SysTool = {
    ajax : function(param, callback) {
        $.ajax({
            type : "POST",
            url : param.url,
            data : {
                params : JSON.stringify(param.data)
            },
            dataType : "JSON",
            timeout : 50000,
            success : function(data, status) {
                if ($.isFunction(callback)) {
                    callback(data, status);
                }
            },
            error : function(request, status, err) {
                console.log(err);
            }
        });

    }
}