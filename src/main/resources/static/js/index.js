/**
 * Created by main on 2018/6/1.
 */

function Index(url) {
    this.searchUrl = url;
    this.input = $('#search');
    this.resultList = $('#search_result');
}

Index.prototype.init = function () {
    var self = this;
    self.input.on({
        'input': function () {
            var keyword = self.input.val();
            if (keyword.length > 0) {
                self.search(keyword);
            } else {
                self.resultList.empty();
            }
        }
    });
};

Index.prototype.search = function (keyword) {
    var self = this;
    $.ajax({
        url: self.searchUrl,
        data: JSON.stringify({'keyword': keyword}),
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        success: function (ack) {
            console.log(ack);
            self.resultList.empty();
            if (ack.status === 200) {
                console.log('ok');
                var list = ack.extraMap.list;
                if (list && list.length > 0) {
                    var li = '';
                    for (var i = 0; i < list.length; i++) {
                        var game = list[i];
                        li += '<li class="media">' +
                                '<div class="media-left">' +
                                    '<a href="' + ctxPath + '/game/show/' + game.id + '">' +
                                        '<img class="media-object" src="' + ctxPath + '/html/' + game.logo + '" style="width: 64px; height: 64px; border-radius: 10px;">' +
                                    '</a>' +
                                '</div>' +
                                '<div class="media-body" style="vertical-align: middle;">' +
                                    '<h4 class="media-heading">' + game.name + '</h4>' +
                                        '<span>' + game.details + '</span>' +
                                    '</div>' +
                                    '<div class="media-right media-middle">' +
                                        '<div class="media-right media-middle">' +
                                            '<a href="' + ctxPath + '/game/show/' + game.id + '" class="btn btn-default">进入</a>' +
                                        '</div>' +
                                    '</div>' +
                                '</li>';
                    }
                    self.resultList.html(li);
                }
            }
        }
    });
};
