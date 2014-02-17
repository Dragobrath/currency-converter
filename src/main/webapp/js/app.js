(function($){
	var dateFormat = "dd.mm.yyyy";
    $(".chosen-select" ).chosen({});
    $(".input-group.date").datepicker({
    	format: dateFormat,
    	autoclose: true});
    $(".input-group.date").datepicker("setDate", new Date());
    $(".input-group.date").datepicker("update");

    function ShowResults(data){
        $(".row.result").html(Mustache.render($("#convert-result").html(), data));
    }

    $("#form-currency").submit(function(event){
    	var originCode = $("[name=convert-from]").val().substring(0,3);
    	var destinationCode = $("[name=convert-to]").val().substring(0,3);
    	var date = $("[name=convert-date]").val();
    	var amount = $("[name=convert-amount]").val();
    	var request = "originCode=" + originCode + "&destinationCode=" + destinationCode + "&amount=" + amount + "&dateFormat=" + dateFormat + "&date=" + date;
        $.ajax({ type: "POST", url: "/currencyconverter/convert", data:request})
            .done(function(data) { ShowResults(data || {}); })
            .fail(function(data) { ShowResults(response || {}); });
        event.preventDefault();
    });
})(jQuery);