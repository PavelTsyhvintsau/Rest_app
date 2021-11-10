/*function startTimer()
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
}*/
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
//Секундомер

//Оставляем вашу функцию
function init() {

    var timer = document.getElementById("timer")
    var time = timer.innerHTML;
    var arr = time.split(":");
    hour = arr[0];
    min = arr[1];
    sec = arr[2];

    setInterval((tick), 1000);
}

//Основная функция tick()
function tick() {

    sec++;
    if (sec >= 60) { //задаем числовые параметры, меняющиеся по ходу работы программы
        min++;
        sec = sec - 60;
    }
    if (min >= 60) {
        hour++;
        min = min - 60;
    }
    if (sec < 10) { //Визуальное оформление
        if (min < 10&&min!=0) {
            if (hour < 10&&hour!=0) {
                document.getElementById("timer").innerHTML ='0' + hour + ':0' + min + ':0' + sec;
            } else {
                document.getElementById("timer").innerHTML = hour + ':0' + min + ':0' + sec;
            }
        } else {
            if (hour < 10&&hour!=0) {
                document.getElementById("timer").innerHTML = '0' + hour + ':' + min + ':0' + sec;
            } else {
                document.getElementById("timer").innerHTML = hour + ':' + min + ':0' + sec;
            }
        }
    } else {
        if (min < 10&&min!=0) {
            if (hour < 10&&hour!=0) {
                document.getElementById("timer").innerHTML = '0' + hour + ':0' + min + ':' + sec;
            } else {
                document.getElementById("timer").innerHTML = hour + ':0' + min + ':' + sec;
            }
        } else {
            if (hour < 10&&hour!=0) {
                document.getElementById("timer").innerHTML = '0' + hour + ':' + min + ':' + sec;
            } else {
                document.getElementById("timer").innerHTML = hour + ':' + min + ':' + sec;
            }
        }
    }
}
/*
function init(objectId) {
    let timer = document.getElementById(objectId).innerHTML
    let arr = timer.split(":");
    let hour = arr[0];
    let min = arr[1];
    let sec = arr[2];

    setInterval(() => {

        sec++;
        if (sec >= 60) { //задаем числовые параметры, меняющиеся по ходу работы программы
            min++;
            sec = sec - 60;
        }
        if (min >= 60) {
            hour++;
            min = min - 60;
        }
        if (sec < 10) { //Визуальное оформление
            if (min < 10&&min!=0) {
                if (hour < 10&&hour!=0) {
                    timer ='0' + hour + ':0' + min + ':0' + sec;
                } else {
                    timer = hour + ':0' + min + ':0' + sec;
                }
            } else {
                if (hour < 10&&hour!=0) {
                    timer = '0' + hour + ':' + min + ':0' + sec;
                } else {
                    timer = hour + ':' + min + ':0' + sec;
                }
            }
        } else {
            if (min < 10&&min!=0) {
                if (hour < 10&&hour!=0) {
                    timer = '0' + hour + ':0' + min + ':' + sec;
                } else {
                    timer = hour + ':0' + min + ':' + sec;
                }
            } else {
                if (hour < 10&&hour!=0) {
                    timer = '0' + hour + ':' + min + ':' + sec;
                } else {
                    timer = hour + ':' + min + ':' + sec;
                }
            }
        }

    }, 1000);
}*/
