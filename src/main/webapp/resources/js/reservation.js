$(document).ready(function(){


    $('#checkall').click(function() {
        $('input[name=idReservation]').prop('checked', true);
    });

   /* $('#check_all').click(function() {
        $('input[name=vehicle]').prop('checked', false);
    });*/
    $('#confirmerOeuvre').click(function(){

        var data = "data=";
        $.each($("input[name='idReservation']:checked"), function(){
            console.log($(this).val());
            data += $(this).val() + ",";
        });
        var res = $.getData("submitConfirmationVente.htm", "POST",data);
        window.location = '../index.htm';
        res.done(function(json){
            console.log("in");

        });

    });
});

jQuery.extend(
    {
        getData:function(p, m, d){
            return $.ajax({
                method: m,url: p, data: d,error:function(){},success:function(json){	}
            });
        }
    });