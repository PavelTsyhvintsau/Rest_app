function startTimer()
{
    var timer = document.getElementById("timer");
    var time = timer.innerHTML;
    var arr = time.split(":");
    var hh = arr[0];
    var mm = arr[1];
    var ss = arr[2];
    if (ss == 0) {
        if (mm == 0) {
            if (hh == 0) {
            }
            hh--;
            mm = 60;
            if (hh < 10)
                hh = "0" + hh;
        }
        mm--;
        if (mm < 10)
            mm = "0" + mm;
        ss = 59;
    } else ss--;
    if (ss < 10)
        ss = "0" + ss;
    document.getElementById("timer").innerHTML = hh+":"+mm+":"+ss;
    setTimeout(startTimer, 1000);
}
/*
function startTimerUp()
{
    var timer = document.getElementById("timer");
    var time = timer.innerHTML;
    var arr = time.split(":");
    var hh = arr[0];
    var mm = arr[1];
    var ss = arr[2];
    if (ss == 59){
        if(mm==59){
            ss=0; mm=0; hh++;
        }
        else{ss=0;
        mm++;}
    }else ss++;
    if (hh < 10)
        hh = "0" + hh;
    if (mm < 10)
        mm = "0" + mm;
    if (ss < 10)
        ss = "0" + mm;
    document.getElementById("timer").innerHTML = hh+":"+mm+":"+ss;
    setTimeout(startTimerUp, 1000);


}*/
