app = (function () {
    function getAlgo(){
        var promise = $.get({
            url: "/Apps/customers",
            contentType: "application/json"
        });
        promise.then(function(data){
            showCustomers(data);
        }, function(error) {
            console.log(22);
        });
    }
    function showCustomers(data){
        for (let i = 0; i < data.split("%").length; i++) {
            data.split("%")[i].split("#");
            $("#customers").append("<li class='temporal'>"+data.split("%")[i].split("#")[0]+"<li/>")
        }

    }
    function insertData(){
        var name = $("#Name").val()
        var num = $("#Age").val()
        var promise = $.get({
            url: "/Apps/insert",
            data: JSON.stringify(name+"!"+num),
            contentType: "application/json"
        });
        promise.then(function(data){
            console.log(data);
        }, function(error) {
            console.log(22);
        });
    }
    return{
        getAlgo:getAlgo,
        insertData:insertData
    }
})();