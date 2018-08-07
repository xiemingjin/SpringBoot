/**
 * 登录
 * @returns {Login}
 * @constructor
 */
var Login = function ()
{
    return  this;
};

/**
 * 初始化方法
 */
Login.prototype.init =function(path)
{
    this.registerBtn(path);
};

/**
 * 按钮注册事件
 */
Login.prototype.registerBtn = function(path)
{
    /**
     * 登录按钮
     */
    $("#submitForm").click(function(){

        var param = {
            url:path+"/index",
            data:{username:$("#username").val(),password:$("#password").val()}
        };
        SysTool.ajax(param,function (data) {

            if(data.resultCode==true)
            {
                window.location.href=path+"main"
            }else
            {
                window.location.href=path+"login"
            }
            console.log(data);
        });

    });
};