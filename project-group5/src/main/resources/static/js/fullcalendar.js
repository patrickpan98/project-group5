/*var evenements = [{
    "title": "Live coding - Demo98",
    "start": "2022-02-17T09:00:00",
    "end": "2022-02-17T11:00:00",
    "backgroundColor": "blue"
}, {
    "title": "Live coding - Demo2",
    "start": "2022-02-17T14:00:00",
    "end": "2022-02-17T15:00:00"
}]*/

// var evenements = [
//     {"id":1,"start":"2022-02-18T09:00:00","end":"2022-02-18T15:00:00","title":"TEST"},
//     {"id":2,"start":"2022-02-17T09:00:00","end":"2022-02-17T15:00:00","title":"TEST2"}
// ];

// var listId = [1, 2];
// var listStart = ["2022-02-20T09:00:00", "2022-02-21T09:00:00"];
// var listEnd = ["2022-02-20T15:00:00", "2022-02-21T15:00:00"];
// var listTitle = ["T2", "T2"];

var listId;
var listStart;
var listEnd;
var listTitle;

function getJson() {
    let event = [];
    for (let i=0;i<listId.length;i++) {
        event.push({"id":listId[i],"start":listStart[i],"end":listEnd[i],"title":listTitle[i]})
    }
    return event;
}
 

//alert(jsonTest(listId,listStart,listEnd,listTitle));

//var evenements = fetch('http://localhost:8080/rdv/liste').then(res => res.json());

//$.getJSON('http://localhost:8080/rdv/liste', function(data) {});

/*var getJSON = function(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';
    xhr.onload = function() {
      var status = xhr.status;
      if (status === 200) {
        callback(null, xhr.response);
      } else {
        callback(status, xhr.response);
      }
    };
    xhr.send();
};

getJSON('http://localhost:8080/rdv/liste', function(err, data) {
    if (err !== null) {
      alert('Something went wrong: ' + err);
    } else {
      alert('Your query count: ' + data.query.count);
    }
});*/

/*let url = 'http://localhost:8080/rdv/liste';
fetch(url)
.then(res => res.json())
.then(out =>
  console.log('Checkout this JSON! ', out))
.catch(err => {throw err});*/


document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    
    /*var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = () => {
        if(xmlhttp.readyState==4) {
            if(xmlhttp.status==200) {
                
                var evenements = JSON.parse(xmlhttp.responseText);
                
                var calendar = new FullCalendar.Calendar(calendarEl, {
                    initialView: 'timeGridWeek',
                    //locale: 'fr'
                    nowIndicator: true,
            
                    headerToolbar: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'timeGridWeek,dayGridMonth'
                    },
                    
                    events: evenements,
                    editable: true,
                    eventDrop: (infos) => {
                        //console.log(infos.event.start)
                        if(!confirm("Etes-vous sûr de déplacer ?")) {
                            infos.revert();
                        }
                    },
                    
                    
                });
                calendar.render();
                
            };
        };
    };
    xmlhttp.open('GET', 'http://localhost:8080/rdv/liste', true);
    xmlhttp.responseType='json';
    xmlhttp.send();*/
    
    
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'timeGridWeek',
        //locale: 'fr'
        nowIndicator: true,

        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'timeGridWeek,dayGridMonth'
        },
        
        events: getJson(),
        
        editable: true,
        eventDrop: (infos) => {
            //console.log(infos.event.start)
            if(!confirm("Etes-vous sûr de déplacer ?")) {
                infos.revert();
            }
        }

    });
    calendar.render();
});


//============================================================================
//============================================================================
//============================================================================


// Plugin options and our code
$("#modal_trigger1").leanModal({
    top: 100,
    overlay: 0.6,
    closeButton: ".modal_close"
});

$("#modal_trigger2").leanModal({
    top: 100,
    overlay: 0.6,
    closeButton: ".modal_close"
});

$("#modal_trigger3").leanModal({
    top: 100,
    overlay: 0.6,
    closeButton: ".modal_close"
});


//Pour l'admin
$(function() {
    // Calling Login Form
    $("#login_form").click(function() {
        $(".social_login").hide();
        $(".user_login").show();
        return false;
    });

    // Calling Register Form
    $("#register_form").click(function() {
        $(".social_login").hide();
        $(".user_register").show();
        $(".header_title").text('Register');
        return false;
    });

    // Going back to Social Forms
    $(".back_btn").click(function() {
        $(".user_login").hide();
        $(".user_register").hide();
        $(".social_login").hide();
        $(".header_title").text('Login');
        return false;
    });
});

//Pour le client
$(function() {
    // Calling Login Form
    $("#login_form_client").click(function() {
        $(".social_login_client").hide();
        $(".user_login_client").show();
        return false;
    });

    // Calling Register Form
    $("#register_form_client").click(function() {
        $(".social_login_client").hide();
        $(".user_register_client").show();

        $(".header_title").text('Register');
        return false;
    });

    // Going back to Social Forms
    $(".back_btn_client").click(function() {
        $(".user_login_client").hide();
        $(".user_register_client").hide();
        $(".social_login_client").show();

        $(".header_title").text('Login');
        return false;
    });
});

//Pour le coiffeur
$(function() {
    // Calling Login Form
    $("#login_form_coiffeur").click(function() {
        $(".social_login_coiffeur").hide();
        $(".user_login_coiffeur").show();
        return false;
    });

    // Going back to Social Forms
    $(".back_btn_coiffeur").click(function() {
        $(".user_login_coiffeur").hide();
        $(".social_login_coiffeur").show();

        $(".header_title").text('Login');
        return false;
    });
});