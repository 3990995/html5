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
        min:1,
        max:10,
        stars:10,
        starCaptions: function(val) {
            return val;
        }
    });
};

$(document).on('ready', function(){
    new Details().init();
});