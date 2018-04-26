/**
 * Created by main on 2018/4/25.
 */

function Details() {
    this.scrollable = $('.scrollable');
    this.window = $(window);
}

Details.prototype.init = function () {
    var self = this;
    self.window.on({
        'resize':function(){
            var width = $(document).width();
            self.scrollable.find('.img-rounded').css('width',width -40);
        }
    });
    self.window.resize();

    $("#input-id").rating({
        displayOnly:true,
        showClear:false,
        max:10
    });
    var starEl = $("#input-id").parents('div.rating-stars');
    $('<span>总评分：</span>').insertBefore(starEl);
    $('#star-caption').insertAfter(starEl);

    $('.stars').rating({displayOnly:true,showClear:false,max:10});
};

$(function(){
    new Details().init();
});