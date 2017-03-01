$(document).ready(function (e) {
    setTime();
    setInterval(setTime, 1000);
    
    function setTime() {
            var now = new Date();

            var hours = now.getHours();
            var minutes = now.getMinutes();
            var seconds = now.getSeconds();


            if (hours < 10) {
                hours = "0" + hours;
            }
            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            if (seconds < 10) {
                seconds = "0" + seconds;
            }

            time_text = hours + ":" + minutes + ":" + seconds;


            $("#clockDiv").text(time_text);
        }

});

