/**
 * Created by ZHANGJA4 on 8/14/2015.
 */
$(function () {
    var bodyHtml = $("body");
    var statusMap = bodyHtml.data("statusMap");
    if (typeof(statusMap) == "undefined") {
        jQuery.get("/StatusServlet", {}, function (data) {
            console.log(data.statusMap);
            bodyHtml.data("statusMap",data);
        })
    }

    var foodType = bodyHtml.data("foodTypeMap");
    if (typeof(foodType) == "undefined") {
        jQuery.get("/FoodTypeServlet", {}, function (data) {
            console.log(data.foodTypeMap);
            bodyHtml.data("foodTypeMap",data);
            console.log(bodyHtml.data("foodTypeMap"));
            console.log(bodyHtml)
        })
    }
})
