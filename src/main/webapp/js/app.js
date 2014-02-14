(function($){
    $('.chosen-select' ).chosen({});
    $('.input-group.date').datepicker({autoclose: true});

    function ShowResults(data){
        $(".row.result").html(Mustache.render($("#convert-result").html(), data));
    }

    $("#form-currency").submit(function(event){
        $.ajax({ type: "POST", url: "/convert", data:$(this).serialize()})
            .done(function(data) { ShowResults(window.data || {}); })
            .fail(function(data) { ShowResults(window.response || {}); });
        event.preventDefault();
    });
})(jQuery);