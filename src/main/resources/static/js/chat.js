var stompClient = null;

$(document).ready(function(){

    $("#panel_messages").hide();
    $("#chat_form").hide();
    $("#search").hide();

    $("#btn_enter").on('click', function (e) {
        e.preventDefault();
        connectWebSockets();
        $("#enter_form").hide();
        $("#chat_form").show();
        $("#search").show();
        var name = $("#txt_from").val();
        $("#navbar_container").append("<p class=\"navbar-text navbar-right\">Signed in as "+name+"</p>");
    });

    $("#chat_form").on('submit', function (e) {
        e.preventDefault();
        var from = $("#txt_from").val();
        var text = $("#txt_message").val();

        if (text !== ""){
            $("#txt_message").val("");
            stompClient.send("/app/send", {}, JSON.stringify({'from':from, 'text':text}));
        }
    });

    $("#search_form").on('submit', function(e) {
        e.preventDefault();
        var gender = $("input[name=radio_gender]:checked").val() || "";
        var city = $("input[name=radio_city]:checked").val() || "";

        $.get("/api/users?gender="+gender+"&city="+city, function(data){
            $("#search_results ul").empty();
            $.each(data, function(index, user){
                var name = user.name;
                var city = user.city;
                var gender = user.gender;
                $("#search_results ul").append("<li><div><label>Name:</label>"+name+"</div><span><label>City:</label>"+city+"</span>  <span><label>Gender:</label>"+gender+"</span></li>");
            });
        });
    });

});

function connectWebSockets(){
    var socket = new SockJS('/chat-simple');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/messages', function (message) {
            addMessage(JSON.parse(message.body));
            $("#panel_messages").show();
        });
    });
}

function addMessage(message){
    $("#messages").append("<div class=\"message\"><div><strong>"+message.from+"</strong> ("+message.dateTime+"): "+message.text+"</div></div><p class=\"separator\"></p>");
}