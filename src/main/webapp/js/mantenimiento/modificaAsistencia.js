/**
 * Created by Christian Baron on 16/03/2015.
 */

    $('.clockpicker').clockpicker()
        .find('input').change(function () {
            console.log(this.value);
        });
    var input = $('#single-input').clockpicker({
        placement: 'bottom',
        align: 'left',
        autoclose: true,
        'default': 'now'
    });

    $('.clockpicker-with-callbacks').clockpicker({
        donetext: 'Done',
        init: function () {
            console.log("colorpicker initiated");
        },
        beforeShow: function () {
            console.log("before show");
        },
        afterShow: function () {
            console.log("after show");
        },
        beforeHide: function () {
            console.log("before hide");
        },
        afterHide: function () {
            console.log("after hide");
        },
        beforeHourSelect: function () {
            console.log("before hour selected");
        },
        afterHourSelect: function () {
            console.log("after hour selected");
        },
        beforeDone: function () {
            console.log("before done");
        },
        afterDone: function () {
            console.log("after done");
        }
    })
        .find('input').change(function () {
            console.log(this.value);
        });

// Manually toggle to the minutes view
    $('#check-minutes').click(function (e) {
        // Have to stop propagation here
        e.stopPropagation();
        input.clockpicker('show')
            .clockpicker('toggleView', 'minutes');
    });
    if (/mobile/i.test(navigator.userAgent)) {
        $('input').prop('readOnly', true);
    }

    hljs.configure({tabReplace: '    '});
    hljs.initHighlightingOnLoad();

    var ids = [];
    var vals = [];

    $('.form-control').change(function () {

        //alert($(this).val());
        if ($(this).val().match(/^([01][0-9]|2[0-3]):[0-5][0-9]$/) || $(this).val().match(/^([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$/)) {
            //alert("has ingresado un numero valido")
            ids.push($(this).attr("id"));
            vals.push($(this).val());
        } else{
            alert("El formato es incorrecto:\n hh:mm o hh:mm:ss")
        }
    });


