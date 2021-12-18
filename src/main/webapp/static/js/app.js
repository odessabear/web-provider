;
$(function () {
    var init = function () {
        initBuyBtn();
        $('#addToCart').on("click", addTariffToCart);
        $('#addTariffPopup .count').on("change", calculateCost);
        $('#loadMore').on("click", loadMoreTariffs);
        $('.remove-tariff').on("click", removeTariffFromCart);
    };

    var showAddTariffPopup = function () {
        var idTariff = $(this).attr('data-id-tariff');
        var tariff = $('#tariff' + idTariff);
        $('#addTariffPopup').attr('data-id-tariff', idTariff);
        $('#addTariffPopup .tariff-image').attr('src', tariff.find('.thumbnail img').attr('src'));
        $('#addTariffPopup .name').text(tariff.find('.name').text());
        var price = tariff.find('.price').text();
        $('#addTariffPopup .price').text(price);
        $('#addTariffPopup .category').text(tariff.find('.category').text());
        $('#addTariffPopup .count').val(1);
        $('#addTariffPopup .cost').text(price);
        $('#addToCart').removeClass('hidden');
        $('#addToCartIndicator').addClass('hidden');
        $('#addTariffPopup').modal({
            show: true
        });
    };
    var initBuyBtn = function () {
        $('.buy-btn').on("click", showAddTariffPopup);
    };
    var addTariffToCart = function () {
        var idTariff = $('#addTariffPopup').attr('data-id-tariff');
        var count = $('#addTariffPopup .count').val();
        $('#addToCart').addClass('hidden');
        $('#addToCartIndicator').removeClass('hidden');
        setTimeout(function () {
            var data = {
                totalCount: count,
                totalCost: 2000
            };
            $('#currentShoppingCart .total-count').text(data.totalCount);
            $('#currentShoppingCart .total-cost').text(data.totalCost);
            $('#currentShoppingCart').removeClass('hidden');
            $('#addTariffPopup').modal('hide');
        }, 800);
    };
    var calculateCost = function () {
        var priceStr = $('#addTariffPopup .price').text();
        var price = parseFloat(priceStr);
        var count = parseInt($('#addTariffPopup .count').val());
        var cost = price * count;
        $('#addTariffPopup .cost').text(cost);
    };
    var loadMoreTariffs = function () {
        $('#loadMore').addClass('hidden');
        $('#loadMoreIndicator').removeClass('hidden');
        $.ajax({
            url: '/ajax/html/more/tariffs',
            success: function (html) {
                $('#tariffList .text-center').prepend(html);
                $('#loadMoreIndicator').addClass('hidden');
                $('#loadMore').removeClass('hidden');
            }
        });
    };


    // var initsortingForm = function() {
    //     $('#allTariffs').on("click", function() {
    //         $('.tariffs .sorting-option').prop('checked', $(this).is(':checked'));
    //     });
    //     $('.tariffs .sorting-option').on("click", function() {
    //         $('#allTariffs').prop('checked', false);
    //     });
    //     $('#allTariffs').on("click", function() {
    //         $('.tariffs .sorting-option').prop('checked', $(this).is(':checked'));
    //     });
    //     $('.tariffs .sorting-option').on("click", function() {
    //         $('#allTariffs').prop('checked', false);
    //     });
    // };
    // var gosorting = function() {
    //     var isAllSelected = function(selector) {
    //         var unchecked = 0;
    //         $(selector).each(function(index, value) {
    //             if (!$(value).is(':checked')) {
    //                 unchecked++;
    //             }
    //         });
    //         return unchecked === 0;
    //     };
    //     if (isAllSelected('.tariffs .sorting-option')) {
    //         $('.tariffs .sorting-option').prop('checked', false);
    //     }
    //     if (isAllSelected('.tariffs .sorting-option')) {
    //         $('.tariffs .sorting-option').prop('checked', false);
    //     }
    //     $('form.sorting').submit();
    // };
    var confirm = function (msg, okFunction) {
        if (window.confirm(msg)) {
            okFunction();
        }
    };
    var removeTariffFromCart = function () {
        var btn = $(this);
        confirm('Are you sure?', function () {
            executeRemoveTariff(btn);
        });
    };
    var refreshTotalCost = function () {
        var total = 0;
        $('#shoppingCart .item').each(function (index, value) {
            var count = parseInt($(value).find('.count').text());
            var price = parseFloat($(value).find('.price').text());
            var val = price * count;
            total = total + val;
        });
        $('#shoppingCart .total').text(total);
    };
    var executeRemoveTariff = function (btn) {
        var idTariff = btn.attr('data-id-tariff');
        var count = btn.attr('data-count');
        btn.removeClass('btn-danger');
        btn.removeClass('btn');
        btn.addClass('load-indicator');
        var text = btn.text();
        btn.text('');
        btn.off('on', "click");

        setTimeout(function () {
            var data = {
                totalCount: 1,
                totalCost: 1
            };
            if (data.totalCount === 0) {
                window.location.href = 'tariffs.html';
            } else {
                var prevCount = parseInt($('#tariff' + idTariff + ' .count').text());
                var remCount = parseInt(count);
                if (remCount === prevCount) {
                    $('#tariff' + idTariff).remove();

                    //
                    if ($('#shoppingCart .item').length === 0) {
                        window.location.href = 'tariffs.html';
                    }
                    //
                }
                refreshTotalCost();
            }
        }, 1000);
    }

    init();
});