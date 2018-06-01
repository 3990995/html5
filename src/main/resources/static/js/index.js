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
       'input':function () {
           var keyword = self.input.val();
           if (keyword.length > 0){
               self.search(keyword);
           }else{
               self.resultList.children.remove();
           }
       }
    });
};

Index.prototype.search = function (keyword) {
    var self = this;
    $.ajax({
        url: self.searchUrl,
        data: JSON.stringify({'keyword':keyword}),
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            console.log(data);
        }
    });
};
