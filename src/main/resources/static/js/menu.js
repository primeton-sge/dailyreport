/**
 * JSON生成菜单
 */
var menu = {
    setTree: function () {
        $.ajax({
            type: 'POST',
            url: contextPath + '/common/getMenuTree.json',
            dataType: 'json',
            success: function (treeData) {
                //生成树
                $('#tree').treeview({
                    data: treeData,
                    enableLinks: true,
                    isNoUnSelect: true,
                    levels: 1,
                    collapseIcon: 'glyphicon glyphicon-chevron-down',
                    expandIcon: 'glyphicon glyphicon-chevron-right',
                    onhoverColor: '#fbe8cc',

                    selectedBackColor: '#daa353',
                    backColor: '#fafafa',
                    onNodeSelected: function (event, node) {
                        if (node.requestURI) {
                            $('html').scrollTop(0);
                            $("#mainFrame").attr('src', contextPath + node.requestURI);
                        }
                    }
                });
                /*绑定click事件*/
                $('#tree').on('click', 'ul', function (e) {
                    var nodeId = parseInt($(e.target).attr('data-nodeid'));
                    if (nodeId) {
                        var node = $('#tree').treeview('getNode', nodeId);
                        if (node.requestURI) {
                            $('#tree').treeview('selectNode', [nodeId]);
                        }
                    }
                });
            },
            error: function (err) {
                alert('菜单生成失败');
            }
        });
    },
    /*菜单栏折叠*/
    collapse: function () {
        var icon = $('.toggle-icon');
        var lw = $('.left-content').width();
        var rw = $('.right-content').width();
        var ml = $('.right-content').css('margin-left');
        $(icon).on('click', function () {
            if (icon.hasClass('on')) {
                ml = $('.right-content').css('margin-left');
                $('.left-content').css('left', -lw + 50 + 'px');
                $('.right-content').css({
                    'width': rw + lw - 50 + 'px',
                    'margin-left': '55px'
                });
                $('.leftMenu').hide();
                icon.removeClass('on');
            } else {
                $('.left-content').css('left', 0);
                $('.right-content').css({
                    'width': rw,
                    'margin-left': ml
                });
                $('.leftMenu').show();
                icon.addClass('on');
            }
        })
    }
};