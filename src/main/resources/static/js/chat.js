var stompClient = null;

$(document).ready(function(){

    $("#panel_messages").hide();
    $("#chat_form").hide();

    $("#btn_enter").on('click', function (e) {
        e.preventDefault();
        connectWebSockets();
        $("#enter_form").hide();
        $("#chat_form").show();
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
    $("#messages").append("<p class=\"message\">"+message.from + ": " + message.text+"</p><p class=\"separator\"></p>");
}